package android.lee.cookingrecipes;

import android.app.Activity;
import android.content.Context;
import android.lee.cookingrecipes.recipes.Recipe;
import android.lee.cookingrecipes.recipes.RecipeLibrary;

import java.util.ArrayList;

/**
 * Created by jason on 3/27/18.
 */

public class UtilService {
    private static RecipeLibrary LIBRARY;

    private UtilService() { }

    public synchronized static void startRecipeLibrary(Activity activity) {
        if (LIBRARY == null) {
            LIBRARY = new RecipeLibrary(activity);
        }
    }

    // Nonblocking start async thread
    //TODO Put it in an async thread pool to control threading maxima
    public static void putRecipeInLibrary(final Recipe r) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                LIBRARY.insertSimpleRecipe(r);
            }
        }).start();
    }

    public static void getAllRecipesFromLibrary(final ArrayList<Recipe> destination, final ShortListAdapter recipeListAdapter) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                ArrayList<Recipe> recipes = LIBRARY.getAllRecipes();
                destination.addAll(recipes);
                recipeListAdapter.notifyDataSetChanged();
            }
        }).start();
    }

    public static void getRecipesFromLibrary(int n) {

    }
}
