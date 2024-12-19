package gr.hua.dit.oop2;

import javax.swing.*;
import java.awt.*;
import java.util.Timer;
import java.util.TimerTask;

public class RecipeExecutionWindow extends JDialog {
    private int stepIndex;

    public RecipeExecutionWindow(JFrame parent, Recipe recipe) {
        super(parent, "Εκτέλεση Συνταγής", true);

        stepIndex = 0;

        JTextArea stepArea = new JTextArea();
        stepArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(stepArea);
        add(scrollPane, BorderLayout.CENTER);

        JButton nextStepButton = new JButton("Επόμενο Βήμα");
        nextStepButton.addActionListener(e -> {
            if (stepIndex < recipe.getSteps().size()) {
                String step = recipe.getSteps().get(stepIndex++);
                stepArea.setText(step);

                if (step.contains("~")) {
                    int time = extractTime(step);
                    startTimer(time, nextStepButton);
                }
            } else {
                JOptionPane.showMessageDialog(this, "Η συνταγή ολοκληρώθηκε!", "Τέλος", JOptionPane.INFORMATION_MESSAGE);
                dispose();
            }
        });

        add(nextStepButton, BorderLayout.SOUTH);
        setSize(600, 400);
    }

    private int extractTime(String step) {
        // Εξαγωγή χρόνου από το βήμα
        return 10; // Εδώ βάζουμε παραδειγματική τιμή για τη δοκιμή
    }

    private void startTimer(int time, JButton button) {
        button.setEnabled(false);

        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                button.setEnabled(true);
            }
        }, time * 1000);
    }
}
