package gr.hua.dit.oop2;

import java.util.*;

public class Recipe {
    private String title;
    private Map<String, String> ingredients;
    private List<String> utensils;
    private List<String> steps;
    private int totalTime;

    public Recipe() {
        this.ingredients = new LinkedHashMap<>();
        this.utensils = new ArrayList<>();
        this.steps = new ArrayList<>();
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Map<String, String> getIngredients() {
        return ingredients;
    }

    public void addIngredient(String ingredient, String quantity) {
        this.ingredients.put(ingredient, quantity);
    }

    public List<String> getUtensils() {
        return utensils;
    }

    public void addUtensil(String utensil) {
        this.utensils.add(utensil);
    }

    public List<String> getSteps() {
        return steps;
    }

    public void addStep(String step) {
        this.steps.add(step);
    }

    public int getTotalTime() {
        return totalTime;
    }

    public void addTime(String time) {
        String[] parts = time.split("%");
        if (parts.length > 1 && parts[1].equals("minutes")) {
            totalTime += Integer.parseInt(parts[0]);
        }
    }
}

