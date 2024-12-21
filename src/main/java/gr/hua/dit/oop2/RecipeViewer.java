package gr.hua.dit.oop2;

import javax.swing.*;
import java.awt.*;

public class RecipeViewer extends JDialog {
    public RecipeViewer(JFrame parent, Recipe recipe) {
        super(parent, "Προβολή Συνταγής", true);

        // Δημιουργία μορφοποιημένου κειμένου για τη συνταγή
        String formattedRecipe = formatRecipe(recipe);

        JTextArea textArea = new JTextArea(formattedRecipe);
        textArea.setEditable(false);

        JScrollPane scrollPane = new JScrollPane(textArea);
        add(scrollPane, BorderLayout.CENTER);

        JButton closeButton = new JButton("Κλείσιμο");
        closeButton.addActionListener(e -> dispose());
        add(closeButton, BorderLayout.SOUTH);

        setSize(600, 400);
    }

    private String formatRecipe(Recipe recipe) {
        StringBuilder sb = new StringBuilder();

        sb.append("Υλικά:\n");
        recipe.getIngredients().forEach((name, quantity) ->
                sb.append("- ").append(name).append(": ").append(quantity).append("\n"));
        sb.append("\n");

        sb.append("Σκεύη:\n");
        recipe.getUtensils().forEach(utensil -> sb.append("- ").append(utensil).append("\n"));
        sb.append("\n");

        sb.append("Συνολικός Χρόνος: ").append(recipe.getTotalTime()).append("\n\n");

        sb.append("Βήματα:\n");
        int stepNumber = 1;
        for (String step : recipe.getSteps()) {
            sb.append(stepNumber++).append(". ").append(step).append("\n\n");
        }

        return sb.toString();
    }
}

