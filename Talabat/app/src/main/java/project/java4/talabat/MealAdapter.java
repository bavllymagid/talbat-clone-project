package project.java4.talabat;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;


public class MealAdapter extends ArrayAdapter<Meal> {

    Context context;
    int resource;

    public MealAdapter(@NonNull Context context, int resource, @NonNull List<Meal> objects) {
        super(context, resource, objects);
        this.context = context;
        this.resource = resource;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        convertView = LayoutInflater.from(context).inflate(resource, parent, false  );

        TextView mealName = (TextView)convertView.findViewById(R.id.mealName);
        TextView mealDescription = (TextView)convertView.findViewById(R.id.mealDescription);
        TextView mealPrice = (TextView)convertView.findViewById(R.id.mealPrice);
        ImageView imgUser = (ImageView) convertView.findViewById(R.id.imgUser);



        Meal currentMeal = getItem(position);

        mealName.setText(currentMeal.getMealName());
        mealDescription.setText(currentMeal.getMealDescription());
//        mealPrice.setText(String.valueOf(currentContact.getPhone()));

        if ( currentMeal.getMealPrice() ==0){
            mealPrice.setText("$");
        }else {
            mealPrice.setText(String.valueOf(currentMeal.getMealPrice())+"$");
        }


        Bitmap bitmap = BitmapFactory.decodeByteArray(currentMeal.getImage(), 0, currentMeal.getImage().length);
        imgUser.setImageBitmap(bitmap);


        return convertView;
    }
}
