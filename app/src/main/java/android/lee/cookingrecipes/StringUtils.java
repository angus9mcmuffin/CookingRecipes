package android.lee.cookingrecipes;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by Jason on 2/28/2018.
 */

public class StringUtils {
    private StringUtils() {}
    public static ArrayList<String> parseIngredients(String listOfIngredients) {
        return new ArrayList<String>(Arrays.asList(listOfIngredients.replaceAll(" ", "").split(",")));
    }

}
