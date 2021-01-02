package project.java4.talabat;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;

public class OwnerPage extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener, SearchView.OnQueryTextListener {

    private ListView contactList;
    private Button btnAdd;
    private ResturantDb db;
    private TextView numberOfItems;
    private TextView itemsPrice;
    private MealAdapter mealAdapter;
    private Order order;

    /**
     * declaration of drawer side bar
     */
    private Toolbar toolbar;
    private DrawerLayout drawer;
    private ActionBarDrawerToggle toggle;
    private NavigationView nav_view;


    /**
     * to check if the layout is opened by a customer
     */
    boolean isCustomer;
    int numberOfItems_ = 0;
    double itemsPrice_ = 0.0f;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_owner_page);
        isCustomer = getIntent().getStringExtra("customer").equals("0");
        db = new ResturantDb(this);

        contactList = findViewById(R.id.contactList);
        btnAdd = findViewById(R.id.btnAdd);
        numberOfItems = findViewById(R.id.numberOfItems);
        itemsPrice = findViewById(R.id.itemsPrice);
        nav_view = findViewById(R.id.nav_view);
        nav_view.setNavigationItemSelectedListener(this);

        // to set the view for the restaurant owner
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
                    String ownerRestaurant = getIntent().getStringExtra("restaurantName");
                    intent.putExtra("resName", ownerRestaurant);
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
        // to set the view for the customer
        else {

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
                    //to reset gui
                    itemsPrice.setText("0");
                    numberOfItems.setText("0");
                    // to reset actual value
                    itemsPrice_ = 0;
                    numberOfItems_ = 0;
                }
            });

            contactList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Meal selected_meal = (Meal) parent.getItemAtPosition(position);
                    order = new Order(getIntent().getStringExtra("Email1"), selected_meal.getMealName()
                            , selected_meal.getMealPrice(), selected_meal.getImage(), getIntent().getStringExtra("res_name"));
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

    /**
     * for when going back in the work flow (previous activity for example)
     */
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
        // to get the name of the restaurant from owner page and login page to the list
        String restaurant_name = getIntent().getStringExtra("res_name");
        String ownerRestaurant = getIntent().getStringExtra("restaurantName");
        if (ownerRestaurant != null) {
            meals = db.getAllMeals(ownerRestaurant);
        } else {
            meals = db.getAllMeals(restaurant_name);
        }

        mealAdapter = new MealAdapter(this, R.layout.meal_data, meals);
        contactList.setAdapter(mealAdapter);

        nav_view.setCheckedItem(R.id.home_nav);
        drawer.close();
    }

    /**
     * checks for selected items on the drawer
     *
     * @param item the selected item
     * @return the state of the selected item
     */
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        Intent intent;
        switch (item.getItemId()) {
            case R.id.your_orders_nav:
                intent = new Intent(this, OrderActivity.class);
                if (isCustomer) {
                    intent.putExtra("customer", "0");
                    intent.putExtra("Email2", getIntent().getStringExtra("Email1"));
                } else {
                    intent.putExtra("customer", "1");
                    String ownerRestaurant = getIntent().getStringExtra("restaurantName");
                    intent.putExtra("resName", ownerRestaurant);
                }

                startActivity(intent);
                break;
            case R.id.logout:
                intent = new Intent(this, Login.class);
                startActivity(intent);
                break;
        }
        return true;
    }

    /**
     * to add search view to toolbar
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.search, menu);
        MenuItem searchItem = menu.findItem(R.id.action_search);
        SearchView searchView = (SearchView) searchItem.getActionView();
        searchView.setQueryHint("search for meals");
        searchView.setOnQueryTextListener(this);
        return true;
    }

    /**
     * for search view and search algorithm
     */
    @Override
    public boolean onQueryTextSubmit(String newText) {
        mealAdapter.getFilter().filter(newText);
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        mealAdapter.getFilter().filter(newText);
        return false;
    }
}