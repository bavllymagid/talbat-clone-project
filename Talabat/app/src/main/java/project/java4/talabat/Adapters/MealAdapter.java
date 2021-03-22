package project.java4.talabat.Adapters;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import project.java4.talabat.Classes.Meal;
import project.java4.talabat.R;

import java.util.ArrayList;
import java.util.List;


public class MealAdapter extends ArrayAdapter<Meal> implements Filterable {
    Context context;
    int resource;

    /**
     * for meal search
     */
    private ArrayList<Meal> mealsList;
    private ArrayList<Meal> allMealsList;

    public MealAdapter(@NonNull Context context, int resource, @NonNull List<Meal> objects) {
        super(context, resource, objects);
        this.context = context;
        this.resource = resource;
        this.mealsList = (ArrayList<Meal>) objects;
        allMealsList = new ArrayList<>();
        this.allMealsList.addAll(mealsList);
    }

    /**
     * waiting for bavlly to explain
     */
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        convertView = LayoutInflater.from(context).inflate(resource, parent, false);

        TextView mealName = convertView.findViewById(R.id.mealName);
        TextView mealDescription = convertView.findViewById(R.id.mealDescription);
        TextView mealPrice = convertView.findViewById(R.id.mealPrice);
        ImageView imgUser = convertView.findViewById(R.id.imgUser);


        Meal currentMeal = getItem(position);

        mealName.setText(currentMeal.getMealName());
        mealDescription.setText(currentMeal.getMealDescription());
        // mealPrice.setText(String.valueOf(currentContact.getPhone()));

        if (currentMeal.getMealPrice() == 0) {
            mealPrice.setText("$");
        } else {
            mealPrice.setText(currentMeal.getMealPrice() + "$");
        }


        Bitmap bitmap = BitmapFactory.decodeByteArray(currentMeal.getImage(), 0, currentMeal.getImage().length);
        imgUser.setImageBitmap(bitmap);


        return convertView;
    }

    /** search algorithm
     */
    @Override
    public Filter getFilter() {
        return new MealAdapter.mealFilter();
    }

    public class mealFilter extends Filter {

        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            List<Meal> filteredMeals = new ArrayList<>();
            if (constraint == null || constraint.length() == 0) {
                filteredMeals.addAll(allMealsList);
            } else {
                String filterPattern = constraint.toString().toLowerCase().trim();
                for (Meal item : allMealsList) {
                    if (item.getMealName().toLowerCase().contains(filterPattern)) {
                        filteredMeals.add(item);
                    }
                }
            }
            FilterResults results = new FilterResults();
            results.values = filteredMeals;
            results.count = filteredMeals.size();
            return results;
        }

        /** clear the old list and display the new one
         */
        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            mealsList.clear();
            mealsList.addAll((List) results.values);
            notifyDataSetChanged();
        }
    }
}
