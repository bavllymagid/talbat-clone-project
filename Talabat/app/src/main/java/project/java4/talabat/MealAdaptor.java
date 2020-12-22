package project.java4.talabat;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

public class MealAdaptor extends ArrayAdapter<Meal> {
    private Context mContext;
    private int mResource;
    public MealAdaptor(@NonNull Context context, int resource, @NonNull ArrayList<Meal> objects) {
        super(context, resource,objects);
        this.mContext=context;
        this.mResource=resource;

    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater layoutInflater=LayoutInflater.from(mContext);
        convertView = layoutInflater.inflate(mResource,parent,false);

        ImageView MealImage= convertView.findViewById(R.id.restimageView);
        TextView MealTitle= convertView.findViewById(R.id.resttitle);
        TextView MealPrice= convertView.findViewById(R.id.restprice);
        TextView MealDesc= convertView.findViewById(R.id.restdesc);

        MealImage.setImageResource(getItem(position).getImageResource());
        MealTitle.setText(getItem(position).getName());
        MealPrice.setText(getItem(position).getPrice());
        MealDesc.setText(getItem(position).getDescription());


        return convertView;
    }
}
