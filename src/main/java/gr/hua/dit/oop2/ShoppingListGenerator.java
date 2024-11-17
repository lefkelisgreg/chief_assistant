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

    private static String combineQuantities(String q1, String q2) {
        // Συνδυασμός ποσοτήτων - απλό άθροισμα για την επίδειξη
        return q1 + " + " + q2;
    }
}

