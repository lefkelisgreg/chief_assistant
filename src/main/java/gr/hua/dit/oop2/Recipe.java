package gr.hua.dit.oop2;

import java.util.*;

public class Recipe {
    private Map<String, String> ingredients;
    private List<String> utensils;
    private List<String> steps;
    private int totalTime;

    public Recipe() {
        this.ingredients = new LinkedHashMap<>();
        this.utensils = new ArrayList<>();
        this.steps = new ArrayList<>();
    }

    public void addIngredient(String name, String quantity) {

        if (quantity.contains("%")) {
            String[] parts = quantity.split("%");
            int value = Integer.parseInt(parts[0]);
            String unit = parts[1];

            // Μετατροπή αν η μονάδα είναι κιλά
            if (unit.equals("kg")) {
                value = UnitConverter.convertWeight(unit, "gr", value);
                unit = "gr";
            }
            // Μετατροπή αν η μονάδα είναι λίτρα
            if (unit.equals("l")) {
                value = UnitConverter.convertLiquid(unit, "ml", value);
                unit = "ml";
            }

            ingredients.put(name, value + " " + unit);
        } else {
            ingredients.put(name, quantity);
        }

    }

    public Map<String, String> getIngredients() {
        return ingredients;
    }

    public void addUtensil(String utensil) {
        utensils.add(utensil);
    }

    public List<String> getUtensils() {
        return utensils;
    }

    public void addStep(String step) {
        steps.add(step);
    }

    public List<String> getSteps() {
        return steps;
    }

    public void addTime(String time) {
        if (time.contains("%")) {
            String[] parts = time.split("%");
            int value = Integer.parseInt(parts[0]);
            String unit = parts[1];

            // Μετατροπή αν η μονάδα είναι ώρες
            if (unit.equals("hours")) {
                value = UnitConverter.convertTime(unit, "minutes", value);
                unit = "minutes";
            }

            totalTime += value;
        }

    }

    public int getTotalTime() {
        return totalTime;
    }

    public void print() {
        System.out.println("Υλικά:");
        ingredients.forEach((ingredient, quantity) ->
                System.out.println(ingredient + " " + quantity)
        );

        System.out.println("\nΣκεύη:");
        utensils.forEach(System.out::println);

        System.out.println("\nΣυνολική ώρα:");
        System.out.println(totalTime + " minutes");

        System.out.println("\nΒήματα:");
        int stepNumber = 1;
        for (String step : steps) {
            System.out.println(stepNumber++ + ". " + step);
        }
    }
}


