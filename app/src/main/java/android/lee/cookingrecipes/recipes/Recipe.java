package android.lee.cookingrecipes.recipes;

import java.util.ArrayList;

/**
 * Created by Jason on 2/28/2018.
 */

public class Recipe {
    private String name;
    // TODO Expand instructions to a series of steps with similar techniques but with different times
    private String instructions;
    // TODO Add optional ingredients, change to hashmap to map name to quant
    private ArrayList<Recipe> ingredients = new ArrayList<Recipe>();

    private long uuid;

    public Recipe(String name) {
        this.name = name;
    }
    public Recipe(String name, ArrayList<String> ingredients, String instructions) {
        this.name = name;
        this.instructions = instructions;

        for (String i : ingredients) {
            // TODO Check if ingredient is name of stored recipe, get it and connect it to this (not necessarily all at once lazily)
            // Assume all ingredients are new
            this.ingredients.add(new Recipe(i));
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<Recipe> getIngredients() {
        return this.ingredients;
    }

    public String getIngredientsConcat() {
        StringBuilder sb = new StringBuilder();
        for (Recipe r : ingredients) {
            sb.append(r.getName() + ", ");
        }

        sb.delete(sb.length() - 2, sb.length());
        return sb.toString();
    }

    public void addIngredient(Recipe recipe) {
        ingredients.add(recipe);
    }

    public String getInstructions() {
        return instructions;
    }

    public void setInstructions(String instructions) {
        this.instructions = instructions;
    }

    protected void setUuid(long uuid) { this.uuid = uuid; }
}
