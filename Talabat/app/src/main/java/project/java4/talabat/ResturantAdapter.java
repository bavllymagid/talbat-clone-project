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

public class ResturantAdapter extends RecyclerView.Adapter<ResturantAdapter.ResturantViewHolder> implements Filterable {
    private ArrayList<Resturant> resturantList ;
    private ArrayList<Resturant> allResturants ;
    private OnResListener onResListener ;


    public static class ResturantViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public ImageView imageView;
        public TextView textView1;
        public OnResListener onResClick ;

    // the item view holder
    public ResturantViewHolder(@NonNull View itemView , OnResListener onResClick) {
        super(itemView);
        imageView = itemView.findViewById(R.id.imageView);
        textView1 = itemView.findViewById(R.id.textview);
        this.onResClick = onResClick ;
        itemView.setOnClickListener(this);
    }
    // to get the postion of object in the recycler view
        @Override
        public void onClick(View v) {
        onResClick.OnResClick(getAdapterPosition());
        }
    }

    public interface OnResListener {
        void OnResClick(int position);
    }

    public ResturantAdapter(ArrayList<Resturant> resturantList , OnResListener onResListener){
        this.resturantList = resturantList ;
        this.onResListener = onResListener ;
        allResturants = new ArrayList<>();
        allResturants.addAll(resturantList);
    }


    @NonNull
    @Override
    public ResturantViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.restaurant_data, parent , false);
        ResturantViewHolder rvh = new ResturantViewHolder(v, onResListener);
        return rvh;
    }

    @Override
    public void onBindViewHolder(@NonNull ResturantViewHolder holder, int position) {
        Resturant currentResturant = resturantList.get(position);

        Bitmap bitmap = BitmapFactory.decodeByteArray(currentResturant.getImageResource(), 0, currentResturant.getImageResource().length);
        holder.imageView.setImageBitmap(bitmap);
        holder.textView1.setText(currentResturant.getName());

    }
  // to count the number of items
    @Override
    public int getItemCount() {
        return resturantList.size();
    }

 /* search algorithm */ /////////////////////
    @Override
    public Filter getFilter() {
        return new resturantFilter();
    }

    public class resturantFilter  extends Filter {

        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            List<Resturant>filteredRestuarants = new ArrayList<>();
            if(constraint==null || constraint.length()==0){
                filteredRestuarants.addAll(allResturants);
            }else {
                String filterpattern =constraint.toString().toLowerCase().trim();
                for(Resturant item :allResturants){
                    if(item.getName().toLowerCase().contains(filterpattern)){
                        filteredRestuarants.add(item);
                    }
                }
            }
            FilterResults results = new FilterResults();
            results.values = filteredRestuarants;
            results.count = filteredRestuarants.size();
            return results;
        }
        // clear the old list and display the new one
        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            resturantList.clear();
            resturantList.addAll((List) results.values);
            notifyDataSetChanged();
        }
    }

}
