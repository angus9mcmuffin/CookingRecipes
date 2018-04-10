package android.lee.cookingrecipes.recipes;

import android.lee.cookingrecipes.RecipeUtils;
import android.lee.cookingrecipes.StringUtils;

import java.util.ArrayList;

/**
 * Created by Jason on 2/28/2018.
 */

public class Recipe {
    private String name;
    // TODO Expand instructions to a series of steps with similar techniques but with different times
    private String instructions;
    // TODO Add optional ingredients, change to hashmap to map name to quant
    private IngredientList ingredients = new IngredientList();

    private String imageUrl;

    private long uuid;

    // TODO Need to implement a service call to update the database with the new recipes
    public Recipe(String name) {
        this(null, name, null, "");
    }

    private Recipe(String name, String ingredients, String instructions, String imageUrl) {
        this(null, name, ingredients, instructions, imageUrl);
    }

    public Recipe(Long uuid, String name, String ingredients, String instructions, String imageUrl) {
        this.name = name;
        this.instructions = instructions;
        this.uuid = uuid != null ? uuid : this.uuid;
        this.imageUrl = imageUrl;
        ArrayList<String> stringToRecipes = StringUtils.parseIngredients(ingredients);

        if (stringToRecipes != null) {
            for (String i : stringToRecipes) {
                // TODO Check if ingredient is name of stored recipe, get it and connect it to this (not necessarily all at once lazily)
                // Assume all ingredients are new
                this.ingredients.addIngredient(new Recipe(i));
            }
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public IngredientList getIngredients() {
        return this.ingredients;
    }

    public String getIngredientsConcat() {
        if (ingredients.getIngredients().size() == 0) {
            return "";
        }

        StringBuilder sb = new StringBuilder();
        for (Recipe r : ingredients.getIngredients()) {
            int quantity = ingredients.getAmount(r);

            if (quantity == 0) {
                continue;
            }

            sb.append(quantity + " " + r.getName() + ", ");
        }

        sb.delete(sb.length() - 2, sb.length());
        return sb.toString();
    }

    public void addIngredient(Recipe recipe) {
        ingredients.addIngredient(recipe);
    }

    public String getInstructions() {
        return instructions;
    }

    public void setInstructions(String instructions) {
        this.instructions = instructions;
    }

    public void setImageUrl(String url) { this.imageUrl = url; }

    public void setUuid(long uuid) { this.uuid = uuid; }

    public String getImageUrl() { return this.imageUrl; }

    public static class Builder {
        private String name, instructions, ingredients, imageUrl;
        private long uuid;

        public Builder() { }

        public Builder setName(String name) {
            this.name = name;
            return this;
        }

        public Builder setInstructions(String instructions) {
            this.instructions = instructions;
            return this;
        }

        public Builder setIngredients(String ingredients) {
            this.ingredients = ingredients;
            return this;
        }

        public Builder setImageUrl(String imageUrl) {
            this.imageUrl = imageUrl;
            return this;
        }

        public Recipe build() {
            Recipe recipe = new Recipe(name, instructions, ingredients, imageUrl);
            RecipeUtils.putRecipeInLibrary(recipe);
            return recipe;
        }
    }
}
