package gr.hua.dit.oop2;

import gr.hua.dit.oop2.countdown.Countdown;
import gr.hua.dit.oop2.countdown.CountdownFactory;
import gr.hua.dit.oop2.countdown.Notifier;

import javax.swing.*;
import java.awt.*;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RecipeExecutionWindow extends JDialog {
    private int stepIndex;
    private Countdown countdown;
    private JLabel timerLabel;
    private ScheduledExecutorService scheduler;

    public RecipeExecutionWindow(JFrame parent, Recipe recipe) {
        super(parent, "Εκτέλεση Συνταγής", true);

        stepIndex = 0;
        scheduler = Executors.newSingleThreadScheduledExecutor();

        // Περιοχή για την εμφάνιση των βημάτων
        JTextArea stepArea = new JTextArea();
        stepArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(stepArea);
        add(scrollPane, BorderLayout.CENTER);

        // Ετικέτα για εμφάνιση της αντίστροφης μέτρησης
        timerLabel = new JLabel("Χρόνος: -");
        timerLabel.setHorizontalAlignment(SwingConstants.CENTER);
        add(timerLabel, BorderLayout.NORTH);

        // Κουμπί "Επόμενο Βήμα"
        JButton nextStepButton = new JButton("Επόμενο Βήμα");
        nextStepButton.addActionListener(e -> handleNextStep(recipe, stepArea, nextStepButton));
        add(nextStepButton, BorderLayout.SOUTH);

        setSize(600, 400);

        // Εκκίνηση με το πρώτο βήμα
        handleNextStep(recipe, stepArea, nextStepButton);
    }

    private void handleNextStep(Recipe recipe, JTextArea stepArea, JButton nextStepButton) {
        if (countdown != null) {
            countdown.stop(); // Διακοπή της προηγούμενης αντίστροφης μέτρησης αν υπάρχει
            scheduler.shutdownNow(); // Τερματισμός οποιουδήποτε προηγούμενου χρονομέτρου
            scheduler = Executors.newSingleThreadScheduledExecutor(); // Νέος χρονοπρογραμματιστής
        }

        if (stepIndex < recipe.getSteps().size()) {
            String step = recipe.getSteps().get(stepIndex++);
            stepArea.setText(step);

            if (step.contains("~")) {
                int time = extractTime(step);
                startCountdown(time, nextStepButton);
            } else {
                timerLabel.setText("Χρόνος: -"); // Δεν υπάρχει χρόνος για αυτό το βήμα
                nextStepButton.setEnabled(true);
            }
        } else {
            JOptionPane.showMessageDialog(this, "Η συνταγή ολοκληρώθηκε!", "Τέλος", JOptionPane.INFORMATION_MESSAGE);
            dispose();
        }
    }

    private int extractTime(String step) {
        Pattern pattern = Pattern.compile("~\\{(\\d+)%minutes}");
        Matcher matcher = pattern.matcher(step);
        if (matcher.find()) {
            try {
                return Integer.parseInt(matcher.group(1)) * 60; // Επιστροφή σε δευτερόλεπτα
            } catch (NumberFormatException e) {
                System.err.println("Σφάλμα κατά την εξαγωγή του χρόνου από το βήμα: " + step);
            }
        }
        return 0; // Αν δεν υπάρχει χρόνος, επιστρέφει 0
    }

    private void startCountdown(int time, JButton button) {
        button.setEnabled(false);

        countdown = CountdownFactory.countdown(time);
        countdown.addNotifier(new Notifier() {
            @Override
            public void finished(Countdown c) {
                SwingUtilities.invokeLater(() -> {
                    button.setEnabled(true);
                    timerLabel.setText("Χρόνος: Ολοκληρώθηκε!"+" "+"Μπορείτε να προχωρήσετε στο επόμενο βήμα.");
                });
            }
        });

        countdown.start();

        // Ενημέρωση της ετικέτας κάθε δευτερόλεπτο
        scheduler.scheduleAtFixedRate(() -> {
            long remaining = countdown.secondsRemaining();
            if (remaining > 0) {
                SwingUtilities.invokeLater(() -> timerLabel.setText("Χρόνος: " + remaining + " δευτερόλεπτα"));
            } else {
                scheduler.shutdown(); // Σταματάμε τον χρονοπρογραμματιστή όταν λήξει ο χρόνος
            }
        }, 0, 1, TimeUnit.SECONDS);
    }

    @Override
    public void dispose() {
        if (scheduler != null && !scheduler.isShutdown()) {
            scheduler.shutdownNow(); // Τερματισμός του scheduler κατά την έξοδο
        }
        super.dispose();
    }
}
