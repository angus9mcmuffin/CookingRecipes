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
        public static final String COLUMN_IMAGE_URL = "image_url";
        public static final ArrayList<String> ROWS = new ArrayList<String>(Arrays
                .asList(new String[]{RecipeReaderEntry.COLUMN_RECIPE_ID,
                RecipeReaderEntry.COLUMN_NAME,
                RecipeReaderEntry.COLUMN_INSTRUCTIONS,
                RecipeReaderEntry.COLUMN_IMAGE_URL}));
        private static final String CREATE_IF_NOT_EXISTS = "CREATE TABLE IF NOT EXISTS ";
        private static final String COMMA = ",";
        private static final String SPACE = " ";
        private static final String TAB_RIGHT = "\n\t";
        private static final String LEFT_PARA = " (";
        private static final String RIGHT_PARA = ")";

        public static String generateForeignKeyConstraint(String localField, String foreignTable, String foreignField) {
            return "FOREIGN KEY(" + localField + ") REFERENCES " + foreignTable + "(" + foreignField + ")";
        }

        // TODO TEST BEFORE USAGE
        public static String generateCreateTableClause(String tableName, ArrayList<String[]> clauses) {
            if (clauses.size() == 0) {
                return "";
            }

            StringBuilder sb = new StringBuilder();
            sb.append(CREATE_IF_NOT_EXISTS).append(tableName).append(LEFT_PARA).append(TAB_RIGHT);
            String noLegalClause = sb.toString();
            for (String[] pair : clauses) {
                if (pair.length == 2) {
                    sb.append(pair[0]).append(SPACE)
                            .append(pair[1]).append(COMMA)
                            .append(TAB_RIGHT);
                }
            }

            if (sb.toString().equals(noLegalClause)) {
                return "";
            } else {
                sb.delete(sb.length() - COMMA.length() - TAB_RIGHT.length(), sb.length());
                sb.append(RIGHT_PARA);
                return sb.toString();
            }
        }
    }

    public static final String CREATE_RECIPE_TABLE_SQL = "CREATE TABLE IF NOT EXISTS " + RecipeReaderEntry.TABLE_RECIPES + " (" +
            RecipeReaderEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL," +
            RecipeReaderEntry.COLUMN_NAME + " TEXT," +
            RecipeReaderEntry.COLUMN_INSTRUCTIONS + " TEXT," +
            RecipeReaderEntry.COLUMN_IMAGE_URL + " TEXT)";
    public static final String CREATE_RECIPE_INGREDIENTS_TABLE_SQL = "CREATE TABLE IF NOT EXISTS " + RecipeReaderEntry.TABLE_RECIPE_INGREDIENTS +
            " (\n\t" + RecipeReaderEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT,\n\t" +
            RecipeReaderEntry.COLUMN_RECIPE_ID + " INTEGER NOT NULL,\n\t" +
            RecipeReaderEntry.COLUMN_INGREDIENT_ID + " INTEGER NOT NULL,\n\t\t" +
            RecipeReaderEntry.generateForeignKeyConstraint(RecipeReaderEntry.COLUMN_INGREDIENT_ID,
                    RecipeReaderEntry.TABLE_RECIPES,
                    RecipeReaderEntry._ID) +
            ",\n\t" + RecipeReaderEntry.generateForeignKeyConstraint(RecipeReaderEntry.COLUMN_RECIPE_ID,
                    RecipeReaderEntry.TABLE_RECIPES,
                    RecipeReaderEntry._ID) + ")";
    public static final String DROP_RECIPE_TABLE_SQL = "DROP TABLE IF EXISTS " +
            RecipeReaderEntry.TABLE_RECIPES;
    public static final String DROP_RECIPE_INGREDIENTS_TABLE_SQL = "DROP TABLE IF EXISTS " +
            RecipeReaderEntry.TABLE_RECIPE_INGREDIENTS;
}
