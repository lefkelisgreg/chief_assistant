package gr.hua.dit.oop2;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ShoppingListWindow extends JDialog {
    public ShoppingListWindow(JFrame parent, List<Recipe> recipes) {
        super(parent, "Λίστα Αγορών", true);

        JTextArea shoppingListArea = new JTextArea();
        shoppingListArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(shoppingListArea);

        // Δημιουργία λίστας αγορών
        Map<String, String> shoppingList = generateShoppingList(recipes);

        // Εμφάνιση λίστας αγορών
        StringBuilder listBuilder = new StringBuilder();
        shoppingList.forEach((ingredient, quantity) -> {
            listBuilder.append(ingredient).append(": ").append(quantity).append("\n");
        });

        shoppingListArea.setText(listBuilder.toString());

        // Κλείσιμο παραθύρου
        JButton closeButton = new JButton("Κλείσιμο");
        closeButton.addActionListener(e -> dispose());

        // Διαμόρφωση παραθύρου
        setLayout(new BorderLayout());
        add(scrollPane, BorderLayout.CENTER);
        add(closeButton, BorderLayout.SOUTH);

        setSize(600, 400);
        setLocationRelativeTo(parent);
    }

    private Map<String, String> generateShoppingList(List<Recipe> recipes) {
        Map<String, String> shoppingList = new HashMap<>();

        for (Recipe recipe : recipes) {
            for (Map.Entry<String, String> ingredient : recipe.getIngredients().entrySet()) {
                String ingredientName = ingredient.getKey();
                String quantity = ingredient.getValue();

                if (shoppingList.containsKey(ingredientName)) {
                    String existingQuantity = shoppingList.get(ingredientName);
                    shoppingList.put(ingredientName, combineQuantities(existingQuantity, quantity));
                } else {
                    shoppingList.put(ingredientName, quantity);
                }
            }
        }

        return shoppingList;
    }

    private String combineQuantities(String existingQuantity, String newQuantity) {
        // Συνδυασμός ποσοτήτων (μπορεί να γίνει πιο σύνθετη επεξεργασία αν χρειάζεται)
        return existingQuantity + " + " + newQuantity;
    }
}

