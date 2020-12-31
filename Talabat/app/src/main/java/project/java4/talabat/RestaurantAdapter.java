package project.java4.talabat;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class RestaurantAdapter extends RecyclerView.Adapter<RestaurantAdapter.RestaurantViewHolder> implements Filterable {
    private ArrayList<Resturant> restaurantList;
    private ArrayList<Resturant> allRestaurants;
    private OnResListener onResListener ;


    public static class RestaurantViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public ImageView imageView;
        public TextView textView1;
        public OnResListener onResClick ;

    // the item view holder
    public RestaurantViewHolder(@NonNull View itemView , OnResListener onResClick) {
        super(itemView);
        imageView = itemView.findViewById(R.id.imageView);
        textView1 = itemView.findViewById(R.id.textview);
        this.onResClick = onResClick ;
        itemView.setOnClickListener(this);
    }
    // to get the position of object in the recycler view
        @Override
        public void onClick(View v) {
        onResClick.OnResClick(getAdapterPosition());
        }
    }
    public interface OnResListener {
        void OnResClick(int position);
    }
    public RestaurantAdapter(ArrayList<Resturant> restaurantList , OnResListener onResListener){
        this.restaurantList = restaurantList ;
        this.onResListener = onResListener ;
        allRestaurants = new ArrayList<>();
        allRestaurants.addAll(restaurantList);
    }


    @NonNull
    @Override
    public RestaurantViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.restaurant_data, parent , false);
        RestaurantViewHolder rvh = new RestaurantViewHolder(v, onResListener);
        return rvh;
    }

    @Override
    public void onBindViewHolder(@NonNull RestaurantViewHolder holder, int position) {
        Resturant currentRestaurant = restaurantList.get(position);

        Bitmap bitmap = BitmapFactory.decodeByteArray(currentRestaurant.getImageResource(), 0, currentRestaurant.getImageResource().length);
        holder.imageView.setImageBitmap(bitmap);
        holder.textView1.setText(currentRestaurant.getName());

    }
  // to count the number of items
    @Override
    public int getItemCount() {
        return restaurantList.size();
    }

 /* search algorithm */ /////////////////////
    @Override
    public Filter getFilter() {
        return new restaurantFilter();
    }

    public class restaurantFilter extends Filter {

        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            List<Resturant>filteredRestaurants = new ArrayList<>();
            if(constraint==null || constraint.length()==0){
                filteredRestaurants.addAll(allRestaurants);
            }else {
                String filterPattern =constraint.toString().toLowerCase().trim();
                for(Resturant item : allRestaurants){
                    if(item.getName().toLowerCase().contains(filterPattern)){
                        filteredRestaurants.add(item);
                    }
                }
            }
            FilterResults results = new FilterResults();
            results.values = filteredRestaurants;
            results.count = filteredRestaurants.size();
            return results;
        }
        // clear the old list and display the new one
        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            restaurantList.clear();
            restaurantList.addAll((List) results.values);
            notifyDataSetChanged();
        }
    }

}
