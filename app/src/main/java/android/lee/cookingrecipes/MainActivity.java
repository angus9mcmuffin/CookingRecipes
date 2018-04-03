package android.lee.cookingrecipes;

import android.app.ListActivity;
import android.lee.cookingrecipes.recipes.Recipe;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;
public class MainActivity extends ListActivity {
    private Button mAddRecipe;
    private EditText mName;
    private EditText mIngredients;
    private EditText mInstructions;
    private ShortListAdapter mShortListAdapter;
    private ArrayList<Recipe> mDisplayRecipes = new ArrayList<Recipe>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mShortListAdapter = new ShortListAdapter(this, mDisplayRecipes);

        setListAdapter(mShortListAdapter);
        mAddRecipe = (Button) findViewById(R.id.add_recipe);
        mName = (EditText) findViewById(R.id.recipe_name);
        mIngredients = (EditText) findViewById(R.id.recipe_ingredients);
        mInstructions = (EditText) findViewById(R.id.recipe_instructions);
        RecipeUtils.startRecipeLibrary(this);
        RecipeUtils.getAllRecipesFromLibrary(mDisplayRecipes, mShortListAdapter);
    }

    public void addRecipe(View view) {
        Recipe recipe = new Recipe(mName.getText().toString(),
                StringUtils.parseIngredients(mIngredients.getText().toString()),
                mInstructions.getText().toString()
        );

        mDisplayRecipes.add(recipe);
        updateRecipeView();

    }

    private void updateRecipeView() {
        mShortListAdapter.notifyDataSetChanged();
    }
}
