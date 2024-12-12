package gr.hua.dit.oop2;

import javax.sound.sampled.AudioFormat;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RecipeParser {
    public static Recipe parseCookFile(String filePath) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(filePath), StandardCharsets.UTF_8));
        //BufferedReader reader = new BufferedReader(new FileReader(filePath));
        String line;
        Recipe recipe = new Recipe();
        StringBuilder stepBuilder = new StringBuilder();

        while ((line = reader.readLine()) != null) {
            line = line.trim();

            if (!line.isEmpty()) {
                stepBuilder.append(line).append(" ");
                parseLineDetails(recipe, line);
            } else if (stepBuilder.length() > 0) {
                recipe.addStep(stepBuilder.toString().trim());
                stepBuilder.setLength(0);
            }
        }

        if (stepBuilder.length() > 0) {
            recipe.addStep(stepBuilder.toString().trim());
        }

        reader.close();
        return recipe;
    }

    private static void parseLineDetails(Recipe recipe, String line) {
        // Κανονική έκφραση για αναγνώριση υλικών με ποσότητες σε αγκύλες
        String regex = "@[^\\s]+\\{.*?\\}|@[^\\s]+(\\s[^\\s]+)+\\{.*?\\}";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(line);

        // Επεξεργασία της κάθε γραμμής
        String[] tokens = line.split("\\s+"); // Χωρίζουμε την γραμμή σε tokens εκεί που υπάρχουν κενά
        for (String token : tokens) {
            if (token.startsWith("@")) {
                // Έλεγχος αν το token είναι υλικό και περιέχει αγκύλες

                if (matcher.find()) {
                    // Εξαγωγή του ταιριασμένου token
                    String matchedToken = matcher.group();
                    // Έλεγχος για το αν το token περιέχει ανεπιθύμητα σύμβολα (# ή ~)
                    if (matchedToken.contains("#") || matchedToken.contains("~")) {
                        // Διαχωρίζουμε το token από τα ανεπιθύμητα σύμβολα (# ή ~)
                        String cleanToken = token.split("[#~]")[0]; // Κρατάμε μόνο το μέρος πριν το # ή ~
                        // Αν το cleanToken περιέχει κενά, κρατάμε μόνο το πρώτο μέρος
                        String ingredient = cleanToken.substring(1).split("\\s+")[0].trim(); // Αφαιρούμε το "@" και κρατάμε το υλικό
                        // Προσθήκη του υλικού στη συνταγή με κενή ποσότητα
                        recipe.addIngredient(ingredient, "");
                        // Συνεχίζουμε στο επόμενο token
                        continue;
                    }
                    String[] parts = matchedToken.substring(1).split("\\{");
                    String ingredient = parts[0];
                    String quantity = parts.length > 1 ? parts[1].replace("}", "") : "";

                    // Προσθήκη του υλικού στη συνταγή
                    recipe.addIngredient(ingredient.trim(), quantity.trim());
                }
            } else if (token.startsWith("#")) {
                // Αν το token δεν περιέχει αγκύλες, το θεωρούμε μια λέξη
                String utensil = token.substring(1).trim(); // Αφαιρούμε το "#"
                recipe.addUtensil(utensil); // Προσθήκη του σκεύους
                } else if (token.startsWith("~")) {
                    // Αν το token ξεκινά με "~", προσθέτουμε το χρόνο
                    recipe.addTime(token.substring(2, token.length() - 2));
                    // Αφαιρεί το "~{" από την αρχή και το "}" από το τέλος
                }
            }
        }


}

