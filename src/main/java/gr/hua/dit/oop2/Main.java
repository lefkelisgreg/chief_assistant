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

        try {
            // Specify the correct encoding (e.g., UTF-8 or ISO-8859-7)
            Charset charset = Charset.forName("UTF-8"); // Replace with "ISO-8859-7" if needed
            Path path = Paths.get("src/main/resources/test.cook");

            // Read the file with the specified encoding
            String content = Files.readString(path, charset);

            System.out.println(content); // Print the correctly decoded content
        } catch (IOException e) {
            System.err.println("Error reading the file: " + e.getMessage());
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

    // Συνδυασμός ποσοτήτων (π.χ., "3 eggs" + "2 eggs")
    public static String mergeQuantities(String q1, String q2) {
        return q1 + " + " + q2;
    }
    

}
