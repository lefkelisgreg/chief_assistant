package gr.hua.dit.oop2;

public class RecipePrinter {
    public static void printRecipe(Recipe recipe) {
        System.out.println("Υλικά:");
        recipe.getIngredients().forEach((ingredient, quantity) ->
                System.out.println(ingredient + " " + quantity)
        );

        System.out.println("\nΣκεύη:");
        recipe.getUtensils().forEach(System.out::println);

        System.out.println("\nΣυνολική ώρα:");
        System.out.println(recipe.getTotalTime());

        System.out.println("\nΒήματα:");
        int stepNumber = 1;
        for (String step : recipe.getSteps()) {
            System.out.println(stepNumber++ + ". " + step);
        }
    }
}

