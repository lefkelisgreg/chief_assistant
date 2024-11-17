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
        ingredients.put(name, quantity);
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
        String[] parts = time.split("%");
        if (parts.length > 1 && parts[1].equals("minutes")) {
            totalTime += Integer.parseInt(parts[0]);
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


