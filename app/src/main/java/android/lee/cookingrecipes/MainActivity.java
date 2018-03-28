package android.lee.cookingrecipes;

import android.app.ListActivity;
import android.lee.cookingrecipes.recipes.Recipe;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
// TODO REMOVE LISTACTIVITY DEP
public class MainActivity extends ListActivity {
    private Button mAddRecipe;
    private EditText mName;
    private EditText mIngredients;
    private EditText mInstructions;
    private ListView mListView;
    private ShortListAdapter mShortListAdapter;
    private ArrayList<Recipe> mDisplayRecipes = new ArrayList<Recipe>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mShortListAdapter = new ShortListAdapter();

        setListAdapter(mShortListAdapter);
        mAddRecipe = (Button) findViewById(R.id.add_recipe);
        mName = (EditText) findViewById(R.id.recipe_name);
        mIngredients = (EditText) findViewById(R.id.recipe_ingredients);
        mInstructions = (EditText) findViewById(R.id.recipe_instructions);
        mListView = (ListView) findViewById(android.R.id.list);
        UtilService.startRecipeLibrary(this);
        UtilService.getAllRecipesFromLibrary(mDisplayRecipes, mShortListAdapter);
    }

    public void addRecipe(View view) {
        Recipe recipe = new Recipe(mName.getText().toString(),
                StringUtils.parseIngredients(mIngredients.getText().toString()),
                mInstructions.getText().toString()
        );

        mDisplayRecipes.add(recipe);
        updateRecipeView();

    }
    // TODO Migrate to its own controller class set
    private void updateRecipeView() {
        mShortListAdapter.notifyDataSetChanged();
    }
//TODO MIGRATE TO OWN CLASS FILE
    public class ShortListAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return mDisplayRecipes.size();
        }

        @Override
        public Object getItem(int position) {
            return mDisplayRecipes.get(position);
        }

        @Override
        public long getItemId(int position) {
            return mDisplayRecipes.get(position).hashCode();
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if (convertView == null) {
                convertView = getLayoutInflater().inflate(R.layout.list_item, getListView(), false);
            }

            Recipe recipe = (Recipe) getItem(position);
            ((TextView) convertView.findViewById(R.id.item_name)).setText(recipe.getName());
            ((TextView) convertView.findViewById(R.id.item_ingredients)).setText(recipe.getIngredientsConcat());
            ((TextView) convertView.findViewById(R.id.item_description)).setText(recipe.getInstructions());

            return convertView;
        }
    }
}
