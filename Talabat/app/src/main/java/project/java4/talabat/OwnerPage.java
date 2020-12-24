package project.java4.talabat;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

public class OwnerPage extends AppCompatActivity {

    ListView contactList;
    Button btnAdd;
    DbContact db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_owner_page);

        db = new DbContact(this);

        contactList = (ListView) findViewById(R.id.contactList);
        btnAdd = (Button) findViewById(R.id.btnAdd);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(OwnerPage.this, AddMeal.class);
                startActivity(intent);

            }
        });


        contactList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Meal selected_meal = (Meal) parent.getItemAtPosition(position);

                Intent intent = new Intent(OwnerPage.this, UpdateMeal.class);

                intent.putExtra("id", selected_meal.getId());

                startActivity(intent);

            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();

        ArrayList<Meal> meals = db.getAllContacts();

        MealAdapter mealAdapter = new MealAdapter(this, R.layout.meal_data, meals);

        contactList.setAdapter(mealAdapter);

    }
}