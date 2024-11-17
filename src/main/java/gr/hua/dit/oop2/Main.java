package gr.hua.dit.oop2;

import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        if (args.length == 0) {
            System.out.println("Παρακαλώ δώστε τα σωστά ορίσματα.");
            return;
        }

        // Έλεγχος λειτουργιών
        if (args[0].equals("-list")) {
            if (args.length < 2) {
                System.out.println("Παρακαλώ δώστε τουλάχιστον ένα αρχείο συνταγής.");
                return;
            }

            List<String> files = Arrays.asList(Arrays.copyOfRange(args, 1, args.length));
            try {
                ShoppingListGenerator.generateShoppingList(files);
            } catch (IOException e) {
                System.err.println("Σφάλμα κατά την ανάγνωση των αρχείων: " + e.getMessage());
            }
        } else {
            String recipeFile = args[0];
            try {
                Recipe recipe = RecipeParser.parseCookFile(recipeFile);
                RecipePrinter.printRecipe(recipe);
            } catch (IOException e) {
                System.err.println("Σφάλμα κατά την ανάγνωση του αρχείου: " + e.getMessage());
            }
        }
    }
}

