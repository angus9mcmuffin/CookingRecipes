package android.lee.cookingrecipes.recipes;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.io.File;
import java.util.ArrayList;

/**
 * Created by Jason on 3/5/2018.
 */
// Should interface with recipes and give back recipes from memory
    // Need to interface with Android external storage for pictures -> do text for now
public final class RecipeLibrary extends SQLiteOpenHelper {
//    private static RecipeLibrary SINGLETON;
//    private static String LOCAL_STORAGE = "localStorage.ks";
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "RecipeLibrary.db";

    public RecipeLibrary(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

//    public RecipeLibrary getRecipeLibrary(Activity activity) {
//        synchronized (SINGLETON) {
//            if (SINGLETON == null) {
//                SINGLETON = new RecipeLibrary(activity);
//            }
//        }
//
//        return SINGLETON;
//    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(RecipeReaderContract.CREATE_RECIPE_TABLE_SQL);
        db.execSQL(RecipeReaderContract.CREATE_RECIPE_INGREDIENTS_TABLE_SQL);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(RecipeReaderContract.DROP_RECIPE_INGREDIENTS_TABLE_SQL);
        db.execSQL(RecipeReaderContract.DROP_RECIPE_TABLE_SQL);
        onCreate(db);
    }

    @Override
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion, newVersion);
    }

    // TODO PUSH TO THREAD
    public void insertSimpleRecipe(Recipe recipe) {
        ContentValues cv = new ContentValues();
        cv.put(RecipeReaderContract.RecipeReaderEntry.COLUMN_NAME, recipe.getName());
        cv.put(RecipeReaderContract.RecipeReaderEntry.COLUMN_INSTRUCTIONS, recipe.getInstructions());
        SQLiteDatabase db = getWritableDatabase();
        long newRowId = db.insert(RecipeReaderContract.RecipeReaderEntry.TABLE_RECIPES, null, cv);
        recipe.setUuid(newRowId);
    }

    // TODO FETCH RECIPE WITH UUID
    public Recipe getRecipe(long uuid) {
        SQLiteDatabase db = getReadableDatabase();
        throw new RuntimeException("NOT IMPLEMENTED");
    }

    // TODO FETCH ALL RECIPES, TODO MAKE RECIPES COME IN A STREAM OR JUST RETURN THE CURSOR INTERFACE
    public ArrayList<Recipe> getAllRecipes() {
        SQLiteDatabase db = getReadableDatabase();
        String recipeTable = RecipeReaderContract.RecipeReaderEntry.TABLE_RECIPES;
        String ingredientTable = RecipeReaderContract.RecipeReaderEntry.TABLE_RECIPE_INGREDIENTS;
        String[] rowNames = null;

        Cursor data = db.query(recipeTable, rowNames, null, null, null, null, null);
        return mapRecipeData(data);
    }

    private ArrayList<Recipe> mapRecipeData(Cursor data) {
        ArrayList<Recipe> recipes = new ArrayList<Recipe>();
        String name, description;
        int nameIndex = RecipeReaderContract.RecipeReaderEntry.ROWS
                .indexOf(RecipeReaderContract.RecipeReaderEntry.COLUMN_NAME);
        int descriptionIndex = RecipeReaderContract.RecipeReaderEntry.ROWS
                .indexOf(RecipeReaderContract.RecipeReaderEntry.COLUMN_INSTRUCTIONS);

        while (data.moveToNext()) {
            name = data.getString(nameIndex);
            description = data.getString(descriptionIndex);
            recipes.add(new Recipe(name, null, description));
        }

        return recipes;
    }
}
