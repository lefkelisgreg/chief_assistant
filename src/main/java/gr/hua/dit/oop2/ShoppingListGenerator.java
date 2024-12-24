package gr.hua.dit.oop2;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ShoppingListGenerator {
    public static void generateShoppingList(List<String> files) throws IOException {
        Map<String, String> shoppingList = new HashMap<>();

        for (String file : files) {
            Recipe recipe = RecipeParser.parseCookFile(file);
            recipe.getIngredients().forEach((ingredient, quantity) -> {
                shoppingList.merge(ingredient, quantity, ShoppingListGenerator::combineQuantities);
            });
        }

        System.out.println("Λίστα Αγορών:");
        shoppingList.forEach((ingredient, quantity) ->
                System.out.println(ingredient + ": " + quantity)
        );
    }
    // Συνδυασμός ποσοτήτων (π.χ. 500%gr + 125%gr )
    private static String combineQuantities(String q1, String q2) {
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

