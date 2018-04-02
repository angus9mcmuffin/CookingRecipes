package android.lee.cookingrecipes;

import android.app.Activity;
import android.app.ListActivity;
import android.lee.cookingrecipes.recipes.Recipe;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by jason on 4/1/18.
 */

public class ShortListAdapter extends BaseAdapter {
        private ArrayList<Recipe> mDisplayRecipes;
        private ListActivity mDisplayActivity;

    public ShortListAdapter(ListActivity displayActivity, ArrayList<Recipe> displayRecipes) {
        super();
        mDisplayRecipes = displayRecipes;
        mDisplayActivity = displayActivity;
    }

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
            convertView = mDisplayActivity.getLayoutInflater().inflate(R.layout.list_item, mDisplayActivity.getListView(), false);
        }

        Recipe recipe = (Recipe) getItem(position);
        ((TextView) convertView.findViewById(R.id.item_name)).setText(recipe.getName());
        ((TextView) convertView.findViewById(R.id.item_ingredients)).setText(recipe.getIngredientsConcat());
        ((TextView) convertView.findViewById(R.id.item_description)).setText(recipe.getInstructions());

        return convertView;
    }
}
