package project.java4.talabat;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;

public class OwnerPage extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    ListView contactList;
    Button btnAdd;
    ResturantDb db;
    TextView numberOfItems;
    TextView itemsPrice;

    //for toolbar
    private Toolbar toolbar;
    private DrawerLayout drawer;
    private ActionBarDrawerToggle toggle;
    private NavigationView nav_view;

    // for customer
    boolean isCustomer;
    int numberOfItems_ = 0;
    double itemsPrice_ = 0.0f;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_owner_page);
        isCustomer = getIntent().getStringExtra("customer").equals("0");
        db = new ResturantDb(this);

        contactList = (ListView) findViewById(R.id.contactList);
        btnAdd = (Button) findViewById(R.id.btnAdd);
        numberOfItems = findViewById(R.id.numberOfItems);
        itemsPrice = findViewById(R.id.itemsPrice);
        nav_view = findViewById(R.id.nav_view);
        nav_view.setNavigationItemSelectedListener(this);




        if (!isCustomer) {

            btnAdd.setText("Add Meal");
            nav_view.getMenu().clear();
            nav_view.inflateMenu(R.menu.drawer_menu_restaurant);
            nav_view.setCheckedItem(R.id.home_nav);
            itemsPrice.setVisibility(View.GONE);
            numberOfItems.setVisibility(View.GONE);
            btnAdd.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Intent intent = new Intent(OwnerPage.this, AddMeal.class);
                    String ownerResturant = getIntent().getStringExtra("returantName");
                    intent.putExtra("resName", ownerResturant);
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
        } else {

            btnAdd.setText("Order now");
            nav_view.getMenu().clear();
            nav_view.inflateMenu(R.menu.drawer_menu_customer);
            nav_view.setCheckedItem(R.id.home_nav);

            itemsPrice.setVisibility(View.VISIBLE);
            numberOfItems.setVisibility(View.VISIBLE);

            btnAdd.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(getApplicationContext(), "ordered", Toast.LENGTH_SHORT).show();
                }
            });

            contactList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Meal selected_meal = (Meal) parent.getItemAtPosition(position);
                    Order order = new Order(getIntent().getStringExtra("Email1") , selected_meal.getMealName()
                    ,selected_meal.getMealPrice(),selected_meal.getImage(),getIntent().getStringExtra("res_name")) ;
                    itemsPrice_ += selected_meal.getMealPrice();
                    numberOfItems_++;
                    itemsPrice.setText(itemsPrice_ + "");
                    numberOfItems.setText(numberOfItems_ + "");
                    db.addOrder(order);
                }
            });
        }


        //initialization of drawer
        drawer = findViewById(R.id.drawer_layout);
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.open, R.string.close);

        drawer.addDrawerListener(toggle);
        //to save the state of drawer
        toggle.syncState();


    }

    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else
            super.onBackPressed();
    }

    @Override
    protected void onResume() {
        super.onResume();

        ArrayList<Meal> meals;
        // to get the name of the resturant from owner page and login page to the list
        String resturant_name = getIntent().getStringExtra("res_name");
        String ownerResturant = getIntent().getStringExtra("returantName");
        if (ownerResturant != null) {
            meals = db.getAllMeals(ownerResturant);
        } else {
            meals = db.getAllMeals(resturant_name);
        }

        MealAdapter mealAdapter = new MealAdapter(this, R.layout.meal_data, meals);
        contactList.setAdapter(mealAdapter);

        drawer.close();
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        Intent intent;
        switch (item.getItemId()) {
            case R.id.your_orders_nav:
                intent = new Intent(this, OrderActivity.class);
                if (isCustomer) {
                    intent.putExtra("customer", "0");
                    intent.putExtra("Email2",getIntent().getStringExtra("Email1"));
                }
                else {
                    intent.putExtra("customer", "1");
                    String ownerResturant = getIntent().getStringExtra("returantName");
                    intent.putExtra("resName", ownerResturant);
                }

                startActivity(intent);
                break;
            case R.id.logout:
                intent = new Intent(this , Login.class);
                startActivity(intent);
                break;
        }
        return true;
    }
}