package project.java4.talabat;

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

public class ResturantAdapter extends RecyclerView.Adapter<ResturantAdapter.ResturantViewHolder>  {
    private ArrayList<Resturant> resturantList ;
    private OnResListener onResListener ;
    public static class ResturantViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public ImageView imageView;
        public TextView textView1;
        public TextView textView2;
        public OnResListener onResClick ;
    // the item view holder
    public ResturantViewHolder(@NonNull View itemView , OnResListener onResClick) {
        super(itemView);
        imageView = itemView.findViewById(R.id.imageView);
        textView1 = itemView.findViewById(R.id.textview);
        textView2 = itemView.findViewById(R.id.textview2);
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
    }


    @NonNull
    @Override
    public ResturantViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.example , parent , false);
        ResturantViewHolder rvh = new ResturantViewHolder(v, onResListener);
        return rvh;
    }

    @Override
    public void onBindViewHolder(@NonNull ResturantViewHolder holder, int position) {
        Resturant currentResturant = resturantList.get(position);

        holder.imageView.setImageResource(currentResturant.getImageResource());
        holder.textView1.setText(currentResturant.getName());
        holder.textView2.setText(currentResturant.getNumber());

    }

    @Override
    public int getItemCount() {
        return resturantList.size();
    }


}
