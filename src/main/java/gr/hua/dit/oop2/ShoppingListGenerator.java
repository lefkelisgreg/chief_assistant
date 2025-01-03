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
    public static String combineQuantities(String q1, String q2) {
        // Διαχωρισμός ποσότητας και μονάδας
        String[] parts1 = q1.split(" ");
        String[] parts2 = q2.split(" ");

        if (parts1.length == 2 && parts2.length == 2) {
            try {
                int quantity1 = Integer.parseInt(parts1[0]); // Ποσότητα 1
                int quantity2 = Integer.parseInt(parts2[0]); // Ποσότητα 2
                String unit1 = parts1[1].trim(); // Μονάδα 1
                String unit2 = parts2[1].trim(); // Μονάδα 2

                if (unit1.equals(unit2)) {
                    // Αν οι μονάδες είναι ίδιες
                    int totalQuantity = quantity1 + quantity2;
                    return totalQuantity + " " + unit1;
                } else {
                    // Αν οι μονάδες είναι διαφορετικές, επιχειρούμε μετατροπή
                    try {
                        int convertedQuantity2 = UnitConverter.convertWeight(unit2, unit1, quantity2);
                        int totalQuantity = quantity1 + convertedQuantity2;
                        return totalQuantity + " " + unit1;
                    } catch (IllegalArgumentException e) {
                        // Αν δεν υποστηρίζεται η μετατροπή
                        return q1 + " + " + q2;
                    }
                }
            } catch (NumberFormatException e) {
                // Αν οι ποσότητες δεν είναι αριθμοί
                return q1 + " +  " + q2;
            }
        } else {

            // Αν το σχήμα δεν είναι σωστό
            return q1 + " +  " + q2;
        }
    }
}

