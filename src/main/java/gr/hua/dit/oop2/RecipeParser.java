package gr.hua.dit.oop2;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class RecipeParser {
    public static Recipe parseCookFile(String filePath) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(filePath));
        String line;
        Recipe recipe = new Recipe();
        StringBuilder stepBuilder = new StringBuilder();

        while ((line = reader.readLine()) != null) {
            line = line.trim();

            // Τίτλος
            if (recipe.getTitle() == null && !line.isEmpty()) {
                recipe.setTitle(line);
                continue;
            }

            // Βήματα και στοιχεία
            if (!line.isEmpty()) {
                stepBuilder.append(line).append(" ");
                parseLineForDetails(recipe, line);
            } else if (stepBuilder.length() > 0) {
                recipe.addStep(stepBuilder.toString().trim());
                stepBuilder.setLength(0);
            }
        }

        // Προσθήκη τελευταίου βήματος
        if (stepBuilder.length() > 0) {
            recipe.addStep(stepBuilder.toString().trim());
        }

        reader.close();
        return recipe;
    }

    private static void parseLineForDetails(Recipe recipe, String line) {
        String[] tokens = line.split("\\s+");
        for (String token : tokens) {
            if (token.startsWith("@")) {
                String[] parts = token.substring(1).split("\\{");
                String ingredient = parts[0];
                String quantity = parts.length > 1 ? parts[1].replace("}", "") : "";
                recipe.addIngredient(ingredient, quantity);
            } else if (token.startsWith("#")) {
                recipe.addUtensil(token.substring(1));
            } else if (token.startsWith("~")) {
                recipe.addTime(token.substring(1));
            }
        }
    }
}

