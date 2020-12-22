package project.java4.talabat;


import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class MacOwner extends AppCompatActivity {

    ListView MealListView;
    private ArrayList<Meal> Mealslist = new ArrayList<>();
    private void DeleteMeal(int position){
        Mealslist.remove(position);
    }
    private void EditMeal(int position){
        Meal m = Mealslist.get(position);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_for_rest);
        Mealslist.add(new Meal(R.drawable.bigmac,"BigMac", "12.0$" , "mac" , "1" ));
        Mealslist.add(new Meal(R.drawable.bigmac,"BigMac", "12.0$", "mac" ,"2" ));
        Mealslist.add(new Meal(R.drawable.bigmac,"BigMac", "12.0$", "mac" , "3" ));
        Mealslist.add(new Meal(R.drawable.bigmac,"BigMac", "12.0$" , "mac" , "1" ));
        Mealslist.add(new Meal(R.drawable.bigmac,"BigMac", "12.0$", "mac" ,"2" ));
        Mealslist.add(new Meal(R.drawable.bigmac,"BigMac", "12.0$", "mac" , "3" ));
        Mealslist.add(new Meal(R.drawable.bigmac,"BigMac", "12.0$" , "mac" , "1" ));
        Mealslist.add(new Meal(R.drawable.bigmac,"BigMac", "12.0$", "mac" ,"2" ));
        Mealslist.add(new Meal(R.drawable.bigmac,"BigMac", "12.0$", "mac" , "3" ));
        Mealslist.add(new Meal(R.drawable.bigmac,"BigMac", "12.0$" , "mac" , "1" ));
        Mealslist.add(new Meal(R.drawable.bigmac,"BigMac", "12.0$", "mac" ,"2" ));
        Mealslist.add(new Meal(R.drawable.bigmac,"BigMac", "12.0$", "mac" , "3" ));
        Mealslist.add(new Meal(R.drawable.bigmac,"BigMac", "12.0$" , "mac" , "1" ));
        Mealslist.add(new Meal(R.drawable.bigmac,"BigMac", "12.0$", "mac" ,"2" ));
        Mealslist.add(new Meal(R.drawable.bigmac,"BigMac", "12.0$", "mac" , "3" ));


        MealListView = findViewById(R.id.Meals);
        MealAdaptor arr=new MealAdaptor(this,R.layout.single_item,Mealslist);
        MealListView.setAdapter(arr);




        MealListView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {

                Toast.makeText(MacOwner.this,"Hello",Toast.LENGTH_LONG).show();

                Mealslist.remove(position);
                return true;
            }
        });
    }
}
