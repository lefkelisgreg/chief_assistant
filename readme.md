## Table of Contents
- [Terminal App - Part1](#part1)
- [GUI App - Part 2](#part2)



# chief_assistantApp

---

The chief_assistantApp is a Java-based project designed to analyzes cooking recipes and prints the execution steps using .cook files.

It leverages UTF-8 encoding and Maven for project management.


# part1

## Terminal App

---

### 1. Installation

1.  Run the Main Class to create a `.target` folder in your project.

2.  Ensure you have Maven installed.

3.  Use the following command to create the necessary JAR file: `mvn clean install`


### 2. Run the Chief Assistant App?
First run ` mvn package` to create the JAR file.
#### Cooking Recipes

```bash
java -jar target/chefa-1.0-SNAPSHOT.jar <your-cook-file>
```

#### Let's Run our example `pancakes.cook` file.
`Just Copy the Code`
```bash
java -jar target/chefa-1.0-SNAPSHOT.jar src/main/resources/pancakes.cook
```
Replace `<your-cook-file>` with the .cook file name. If the file is not in the project folder, provide the absolute path. If the file doesn't exist, the app will create it and add new events/tasks; otherwise, it will update the existing file.

#### Shopping List
In this step, it displays a list of the ingredients we will need to make the recipes we specify as the target.
```bash 
java -jar target/chefa-1.0-SNAPSHOT.jar -list <your-cook-file1> <your-cook-file2> ...
```
#### Let's Run our example `pancakes.cook` and `pizza.cook` file.
`Just Copy the Code`
```bash
java -jar target/chefa-1.0-SNAPSHOT.jar -list src/main/resources/pizza.cook src/main/resources/pancakes.cook
```
Replace `<your-cook-file>` with the .cook file name. If the file is not in the project folder, provide the absolute path. If the file doesn't exist, the app will create it and add new events/tasks; otherwise, it will update the existing file.


---


# part2
## Recipes App (GUI Version)

---
The second part of the application provides a graphical user interface (GUI) for managing recipes. It is implemented using Java Swing and provides an intuitive, user-friendly way to interact with the recipes.

# 1. Running the Window App
To launch the GUI, execute the MainWindow class. This will open a window allowing you to perform various operations on the recipes.

# 2. Features of the Window App

## Recipe Management

* Load recipes from .cook files into the application.
* View loaded recipes in a list for easy selection. 

## View Recipe Details

Select a recipe from the list and click "Προβολή Συνταγής" (View Recipe) to display its details in a new window.
Details include:
* Ingredients with quantities
* Required utensils
* Total preparation time
* Step-by-step instructions

## Generate a Shopping List

Click "Λίστα Αγορών" (Generate Shopping List) to consolidate the ingredients required for all loaded recipes.
The shopping list window displays the ingredients and their combined quantities.

## Execute a Recipe

Select a recipe and click "Εκτέλεση Συνταγής" (Execute Recipe) to interactively execute its steps.
Each step is displayed one at a time in a dedicated window.
If a step involves a specific time duration (e.g., "Bake for 10 minutes"), a countdown timer is shown to guide the execution process.
Users can proceed to the next step after completing the current one.

# 3. Usage Workflow

### 1.    Loading Recipes

* Click the "Φόρτωση Συνταγών" (Load Recipes) button to select one or more .cook files.
* The selected recipes are added to the list in the main window.

### 2. Viewing a Recipe

* Select a recipe from the list and click "Προβολή Συνταγής" (View Recipe).
* A new window displays all the details of the selected recipe.

### 3. Generating a Shopping List

* Click "Λίστα Αγορών" (Generate Shopping List) to create a shopping list for all loaded recipes.
* A new window shows the consolidated list of ingredients.

### 4. Executing a Recipe

* Select a recipe from the list and click "Εκτέλεση Συνταγής" (Execute Recipe).
* Follow the steps interactively, with timers for specific actions when applicable.

---

# Project Structure

### Source Code Files

1. **`Main.java`**:

   - Entry point for the application.
   - Provides two main functionalities:
      - Display a recipe.
      - Generate a shopping list.

2. **`Recipe.java`**:

   - Represents a recipe with attributes such as ingredients, utensils, steps, and total time.
   - Contains methods to add and retrieve recipe details.

3. **`RecipeParser.java`**:

   - Handles parsing of recipe files to extract details like ingredients, utensils, and steps.

4. **`RecipePrinter.java`**:

   - Formats and prints the recipe details to the console.

5. **`ShoppingListGenerator.java`**:

   - Consolidates ingredients from multiple recipes into a single shopping list.

6. **`ΜainWindow.java`**:

   - The primary GUI class providing functionality for managing recipes.

7. **`RecipeViewer.java`**:

   -  Displays the details of a selected recipe in a new window.

8. **`ShoppingListWindow.java`**:

   - Displays the aggregated shopping list for all loaded recipes.

9. **`RecipeExecutionWindow.java`**:   

   - Guides the user through the interactive execution of a recipe.

10. **`UnitConvert.java`**:

    - It is responsible for converting kilos into grams , liters into milliliters and hours into minutes when an ingredient is saved in a recipe.

---

## License Information

---



The software developed under the auspices of the Department of Informatics and Telematics (DIT) at the Harokopeio University of Athens (HUA).

### HUA Developers
```
22097 Spanellis Vaggelis
219132 Palaiologos Ilias
```