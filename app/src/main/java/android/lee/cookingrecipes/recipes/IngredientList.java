package android.lee.cookingrecipes.recipes;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by jason on 3/20/18.
 */

public class IngredientList {
    private Map<String, Integer> listOfIngredients = Collections.synchronizedMap(new HashMap<String, Integer>());

    public IngredientList() { }


    public void addIngredient(Recipe recipe) {
        String name = recipe.getName();

        if (listOfIngredients.containsKey(name)) {
            listOfIngredients.put(name, listOfIngredients.get(name) + 1);
        } else {
            listOfIngredients.put(name, 1);
        }
    }

    public List<Recipe> getIngredients() {
        ArrayList<Recipe> listOfRecipes = new ArrayList<Recipe>();
        Set<String> recipeNames = listOfIngredients.keySet();
        for (String r : recipeNames) {
            listOfRecipes.add(new Recipe(r));
        }

        return listOfRecipes;
    }

    public int getAmount(Recipe r) {
        return listOfIngredients.containsKey(r.getName()) ? listOfIngredients.get(r) : 0;
    }
}
