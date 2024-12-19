package gr.hua.dit.oop2;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class MainWindow extends JFrame {
    private DefaultListModel<String> recipeListModel;
    private List<Recipe> recipes;

    public MainWindow() {
        super("Διαχείριση Συνταγών");
        recipes = new ArrayList<>();
        recipeListModel = new DefaultListModel<>();

        // Λίστα συνταγών
        JList<String> recipeList = new JList<>(recipeListModel);
        recipeList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        JScrollPane recipeScrollPane = new JScrollPane(recipeList);

        // Κουμπιά λειτουργιών
        JButton loadRecipesButton = new JButton("Φόρτωση Συνταγών");
        JButton viewRecipeButton = new JButton("Προβολή Συνταγής");
        JButton generateShoppingListButton = new JButton("Λίστα Αγορών");
        JButton executeRecipeButton = new JButton("Εκτέλεση Συνταγής");

        // Panel για τα κουμπιά
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(1, 4));
        buttonPanel.add(loadRecipesButton);
        buttonPanel.add(viewRecipeButton);
        buttonPanel.add(generateShoppingListButton);
        buttonPanel.add(executeRecipeButton);

        // Διαμόρφωση παραθύρου
        setLayout(new BorderLayout());
        add(recipeScrollPane, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);

        // Event Listeners
        loadRecipesButton.addActionListener(e -> loadRecipes());
        viewRecipeButton.addActionListener(e -> viewRecipe(recipeList.getSelectedIndex()));
        generateShoppingListButton.addActionListener(e -> generateShoppingList());
        executeRecipeButton.addActionListener(e -> executeRecipe(recipeList.getSelectedIndex()));

        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    private void loadRecipes() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setMultiSelectionEnabled(true);
        int result = fileChooser.showOpenDialog(this);

        if (result == JFileChooser.APPROVE_OPTION) {
            File[] files = fileChooser.getSelectedFiles();
            for (File file : files) {
                try {
                    Recipe recipe = RecipeParser.parseCookFile(file.getAbsolutePath());
                    recipes.add(recipe);
                    recipeListModel.addElement(file.getName());
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(this, "Σφάλμα φόρτωσης: " + file.getName(), "Σφάλμα", JOptionPane.ERROR_MESSAGE);
                }
            }
        }
    }

    private void viewRecipe(int index) {
        if (index < 0 || index >= recipes.size()) {
            JOptionPane.showMessageDialog(this, "Παρακαλώ επιλέξτε μία συνταγή.", "Σφάλμα", JOptionPane.WARNING_MESSAGE);
            return;
        }

        RecipeViewer viewer = new RecipeViewer(this, recipes.get(index));
        viewer.setVisible(true);
    }

    private void generateShoppingList() {
        ShoppingListWindow shoppingListWindow = new ShoppingListWindow( this, recipes);
        shoppingListWindow.setVisible(true);
    }

    private void executeRecipe(int index) {
        if (index < 0 || index >= recipes.size()) {
            JOptionPane.showMessageDialog(this, "Παρακαλώ επιλέξτε μία συνταγή.", "Σφάλμα", JOptionPane.WARNING_MESSAGE);
            return;
        }

        RecipeExecutionWindow executionWindow = new RecipeExecutionWindow(this, recipes.get(index));
        executionWindow.setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(MainWindow::new);
    }
}

