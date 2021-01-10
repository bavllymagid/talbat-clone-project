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

public class OrdersAdapter extends ArrayAdapter<Order> {
    Context context;
    int resource;

    public OrdersAdapter(@NonNull Context context, int resource, @NonNull List<Order> objects) {
        super(context, resource, objects);
        this.context = context;
        this.resource = resource;
    }

    /**
     * waiting for bavlly to explain
     */
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        convertView = LayoutInflater.from(context).inflate(resource, parent, false  );

        TextView orderName = (TextView)convertView.findViewById(R.id.orderData);
        TextView orderQuantity = (TextView) convertView.findViewById(R.id.orderQuantity);
        TextView orderDate = (TextView) convertView.findViewById(R.id.orderDate);
        TextView orderPrice = (TextView)convertView.findViewById(R.id.mealPrice);
        ImageView imgUser = (ImageView) convertView.findViewById(R.id.mealImage);



        Order currentOrder = getItem(position);

        orderName.setText(currentOrder.getMealName());
        orderDate.setText(currentOrder.getDate());
        orderQuantity.setText("Quantity: " + currentOrder.getQuantity());

        if ( currentOrder.getMealPrice() ==0){
            orderPrice.setText("$");
        }else {
            orderPrice.setText(String.valueOf(currentOrder.getMealPrice()* currentOrder.getQuantity())+"$");
        }

        Bitmap bitmap = BitmapFactory.decodeByteArray(currentOrder.getImage(), 0, currentOrder.getImage().length);
        imgUser.setImageBitmap(bitmap);


        return convertView;
    }
}
