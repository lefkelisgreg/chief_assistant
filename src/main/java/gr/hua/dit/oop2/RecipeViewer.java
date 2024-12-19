package gr.hua.dit.oop2;

import javax.swing.*;
import java.awt.*;

public class RecipeViewer extends JDialog {
    public RecipeViewer(JFrame parent, Recipe recipe) {
        super(parent, "Προβολή Συνταγής", true);

        JTextArea textArea = new JTextArea(recipe.toString());
        textArea.setEditable(false);

        JScrollPane scrollPane = new JScrollPane(textArea);
        add(scrollPane, BorderLayout.CENTER);

        JButton closeButton = new JButton("Κλείσιμο");
        closeButton.addActionListener(e -> dispose());
        add(closeButton, BorderLayout.SOUTH);

        setSize(600, 400);
    }
}

