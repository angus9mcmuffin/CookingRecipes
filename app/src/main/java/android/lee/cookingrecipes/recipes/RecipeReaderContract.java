package android.lee.cookingrecipes.recipes;

import android.provider.BaseColumns;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by Jason on 3/5/2018.
 */

// TODO CREATE DIARY TABLE STORING RECIPE, DATE, AND EXECUTION

public final class RecipeReaderContract {
    private RecipeReaderContract() {}

    public static class RecipeReaderEntry implements BaseColumns {
        public static final String TABLE_RECIPES = "recipes";
        public static final String COLUMN_NAME = "name";
        public static final String COLUMN_INSTRUCTIONS = "instruction";
        public static final String TABLE_RECIPE_INGREDIENTS = "recipe_ingredients";
        public static final String COLUMN_RECIPE_ID = "recipe_id";
        public static final String COLUMN_INGREDIENT_ID = "ingredient_id";
        public static final ArrayList<String> ROWS = new ArrayList<String>(Arrays
                .asList(new String[]{RecipeReaderEntry.COLUMN_RECIPE_ID,
                RecipeReaderEntry.COLUMN_NAME,
                RecipeReaderEntry.COLUMN_INSTRUCTIONS}));

        public static String generateForeignKeyConstraint(String localField, String foreignTable, String foreignField) {
            return "FOREIGN KEY(" + localField + ") REFERENCES " + foreignTable + "(" + foreignField + ")";
        }
    }

    public static final String CREATE_RECIPE_TABLE_SQL = "CREATE TABLE " + RecipeReaderEntry.TABLE_RECIPES + " (" +
            RecipeReaderEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL," +
            RecipeReaderEntry.COLUMN_NAME + " TEXT," +
            RecipeReaderEntry.COLUMN_INSTRUCTIONS + " Text)";
    public static final String CREATE_RECIPE_INGREDIENTS_TABLE_SQL = "CREATE TABLE " + RecipeReaderEntry.TABLE_RECIPE_INGREDIENTS +
            " (" + RecipeReaderEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
            RecipeReaderEntry.COLUMN_RECIPE_ID + " INTEGER NOT NULL," +
            RecipeReaderEntry.COLUMN_INGREDIENT_ID + " INTEGER NOT NULL " +
            RecipeReaderEntry.generateForeignKeyConstraint(RecipeReaderEntry.COLUMN_INGREDIENT_ID,
                    RecipeReaderEntry.TABLE_RECIPES,
                    RecipeReaderEntry._ID) +
            " " + RecipeReaderEntry.generateForeignKeyConstraint(RecipeReaderEntry.COLUMN_RECIPE_ID,
                    RecipeReaderEntry.TABLE_RECIPES,
                    RecipeReaderEntry._ID) + ")";
    public static final String DROP_RECIPE_TABLE_SQL = "DROP TABLE IF EXISTS " +
            RecipeReaderEntry.TABLE_RECIPES;
    public static final String DROP_RECIPE_INGREDIENTS_TABLE_SQL = "DROP TABLE IF EXISTS " +
            RecipeReaderEntry.TABLE_RECIPE_INGREDIENTS;
}
