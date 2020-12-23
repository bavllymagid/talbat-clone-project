package project.java4.talabat;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Gallery;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class Mac extends AppCompatActivity {

    private ListView MealListView;
    private Button btnAddMeal;
    AddMeal addMeal = new AddMeal();
    EditText inputMealName,inputMealDescription,inputMealPrice;


    public ArrayList<Meal> Mealslist = new ArrayList<>();
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
        /*Mealslist.add(new Meal(R.drawable.bigmac,"BigMac", "12.0$", "mac" ,"2" ));
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
        Mealslist.add(new Meal(R.drawable.bigmac,"BigMac", "12.0$", "mac" , "3" ));*/


        //VIEWS
        btnAddMeal = (Button) findViewById(R.id.btnAddMeal);
        MealListView = findViewById(R.id.Meals);
        MealAdaptor arr=new MealAdaptor(this,R.layout.single_item,Mealslist);
        MealListView.setAdapter(arr);
        inputMealPrice = (EditText) findViewById(R.id.inputMealPrice);
        inputMealDescription = (EditText) findViewById(R.id.inputMealDescription);
        inputMealName = (EditText) findViewById(R.id.inputMealName);




        //Add Meal Button
        btnAddMeal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(Mac.this, AddMeal.class);
                startActivity(intent);
            }
        });

        //After Add Meal
        Bundle getInformation = getIntent().getExtras();
        if(getInformation.get("Empty").toString().equals("Empty")){
            Toast.makeText(Mac.this,"Yes",Toast.LENGTH_LONG).show();
        }
        if(!getInformation.get("Empty").toString().equals("Empty")){
            Mealslist.add( new Meal(R.drawable.bigmac,getInformation.get("MealName").toString(), getInformation.get("MealPrice").toString()+"$", getInformation.get("MealDescription").toString() , "3" ));
        }


        //Meal List View
        MealListView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(Mac.this,"Hello",Toast.LENGTH_LONG).show();
                Mealslist.remove(position);
                return true;
            }
        });
    }
}

