package gr.hua.dit.oop2;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        if (args.length == 0) {
            System.out.println("Παρακαλώ εισάγετε ορίσματα για την εφαρμογή.");
            return;
        }

        if (args[0].equals("-list")) {
            // Λειτουργία για δημιουργία λίστας αγορών
            if (args.length < 2) {
                System.out.println("Παρακαλώ εισάγετε τουλάχιστον ένα αρχείο για την λίστα αγορών.");
                return;
            }

            try {
                List<String> files = Arrays.asList(Arrays.copyOfRange(args, 1, args.length));
                generateShoppingList(files);
            } catch (IOException e) {
                System.err.println("Σφάλμα κατά την ανάγνωση των αρχείων: " + e.getMessage());
            }
        } else {
            // Λειτουργία για εμφάνιση συνταγής
            try {
                displayRecipe(args[0]);
            } catch (IOException e) {
                System.err.println("Σφάλμα κατά την ανάγνωση του αρχείου: " + e.getMessage());
            }
        }

    }

    // Εμφάνιση συνταγής
    public static void displayRecipe(String fileName) throws IOException {
        Recipe recipe = RecipeParser.parseCookFile(fileName); // Δημιουργούμε αντικείμενο Recipe
        recipe.print(); // Καλούμε τη μη-στατική μέθοδο μέσω του αντικειμένου
    }


    // Δημιουργία λίστας αγορών
    public static void generateShoppingList(List<String> files) throws IOException {
        Map<String, String> shoppingList = new HashMap<>();

        for (String file : files) {
            Recipe recipe = RecipeParser.parseCookFile(file);
            recipe.getIngredients().forEach((ingredient, quantity) -> {
                shoppingList.merge(ingredient, quantity, Main::mergeQuantities);
            });
        }

        System.out.println("Λίστα Αγορών:");
        shoppingList.forEach((ingredient, quantity) ->
                System.out.println(ingredient + ": " + quantity)
        );
    }

    // Συνδυασμός ποσοτήτων (π.χ. 500%gr + 125%gr )
    public static String mergeQuantities(String q1, String q2) {
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
