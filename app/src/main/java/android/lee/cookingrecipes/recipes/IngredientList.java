package android.lee.cookingrecipes.recipes;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by jason on 3/20/18.
 */

public class IngredientList {
    private Map<Recipe, Integer> listOfIngredients = Collections.synchronizedMap(new HashMap<Recipe, Integer>());

    public IngredientList() { }


    public void addIngredient(Recipe r) {
        if (listOfIngredients.containsKey(r)) {
            listOfIngredients.put(r, listOfIngredients.get(r) + 1);
        } else {
            listOfIngredients.put(r, 1);
        }
    }

    public List<Recipe> getIngredients() {
        return new ArrayList<Recipe>(Arrays.asList((Recipe[])listOfIngredients.keySet().toArray()));
    }

    public int getAmount(Recipe r) {
        return listOfIngredients.containsKey(r) ? listOfIngredients.get(r) : 0;
    }
}
