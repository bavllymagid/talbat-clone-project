package project.java4.talabat;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ListView;

import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;

public class OrderActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    //initialization of drawer side bar
    private DrawerLayout drawer;
    private ActionBarDrawerToggle toggle;
    private Toolbar toolbar;
    private NavigationView nav_view;

    // for customer
    boolean isCustomer;

    //the recycler view
    ListView contactList ;
    ResturantDb resturantDb = new ResturantDb(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.orders);
        isCustomer = getIntent().getStringExtra("customer").equals("0");
        drawer = findViewById(R.id.drawer_layout);
        nav_view = findViewById(R.id.nav_view);
        nav_view.setNavigationItemSelectedListener(this);
        if (isCustomer) {
            nav_view.getMenu().clear();
            nav_view.inflateMenu(R.menu.drawer_menu_customer);
        } else {
            nav_view.getMenu().clear();
            nav_view.inflateMenu(R.menu.drawer_menu_restaurant);
        }
        nav_view.setCheckedItem(R.id.your_orders_nav);

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.open, R.string.close);

        drawer.addDrawerListener(toggle);
        //to save the state of drawer
        toggle.syncState();

        //the list view
        contactList = (ListView) findViewById(R.id.recyclerview);

    }


    @Override
    protected void onResume() {
        super.onResume();

        ArrayList<Order> orders;
        // to get the name of the resturant from owner page and login page to the list
        String ownerOrders = getIntent().getStringExtra("resName");
        //to send the email to the order page
        String customerOrders = getIntent().getStringExtra("Email2");
        // to get the email from load resturant to order page
        String loadToOwner = getIntent().getStringExtra("Email1");
        if (customerOrders != null ) {
            orders = resturantDb.getAllCustomerOrders(customerOrders);
        } else if (ownerOrders != null) {
            orders = resturantDb.getAllRestaurantOrders(ownerOrders);
        }else{
            orders = resturantDb.getAllCustomerOrders(loadToOwner);
        }

        OrdersAdapter orderAdapter = new OrdersAdapter(this, R.layout.order_data, orders);
        contactList.setAdapter(orderAdapter);

    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        Intent intent;
        switch (item.getItemId()) {
            case R.id.home_nav:
                finish();
                break;
            case R.id.logout:
                intent = new Intent(this , Login.class);
                startActivity(intent);
                break;
        }
        return true;
    }
}