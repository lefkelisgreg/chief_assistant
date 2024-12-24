chief_assistantApp
===========

The chief_assistantApp is a Java-based project designed to analyzes cooking recipes and prints the execution steps using .cook files.

It leverages UTF-8 encoding and Maven for project management.

Getting Started
---------------

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


# -------------------------------------------------------



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
   


License Information
-------------------

The software developed under the auspices of the Department of Informatics and Telematics (DIT) at the Harokopeio University of Athens (HUA).

### HUA Developers
```
22097 Spanellis Vaggelis
219132 Palaiologos Ilias
```