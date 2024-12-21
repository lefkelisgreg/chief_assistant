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

    //  private String combineQuantities(String existingQuantity, String newQuantity) {
    //    // Συνδυασμός ποσοτήτων
    //     return existingQuantity + " + " + newQuantity;
    //  }

    // Συνδυασμός ποσοτήτων (π.χ. 500%gr + 125%gr )
    public static String combineQuantities(String q1, String q2) {
        // Διαχωρισμός της ποσότητας και της μονάδας
        String[] parts1 = q1.split("%");
        String[] parts2 = q2.split("%");

        if (parts1.length == 2 && parts2.length == 2 && parts1[1].equals(parts2[1])) {
            // Αν οι μονάδες είναι ίδιες (π.χ. "gr")
            try {
                int quantity1 = Integer.parseInt(parts1[0]);
                int quantity2 = Integer.parseInt(parts2[0]);

                // Συνδυάζουμε τις ποσότητες
                int totalQuantity = quantity1 + quantity2;
                return totalQuantity + " " + parts1[1];
            } catch (NumberFormatException e) {
                // Αν δεν μπορούμε να μετατρέψουμε τις ποσότητες σε αριθμούς
                return q1 + " + " + q2;
            }
        } else {
            // Αν οι μονάδες είναι διαφορετικές ή το σχήμα δεν είναι σωστό
            return q1 + " + " + q2;
        }
    }
}
