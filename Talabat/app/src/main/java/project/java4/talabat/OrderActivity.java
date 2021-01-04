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
import android.widget.TextView;

import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;

public class OrderActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    /**
     * declaration of drawer side bar
     */
    private DrawerLayout drawer;
    private ActionBarDrawerToggle toggle;
    private NavigationView nav_view;
    private Toolbar toolbar;
    private TextView emailView;

    /**
     * to check if the layout is opened by a customer
     */
    boolean isCustomer;

    /**
     * declaration of list view
     */
    ListView contactList;
    /**
     * restaurants database
     */
    ResturantDb resturantDb = new ResturantDb(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.orders);
        isCustomer = getIntent().getStringExtra("customer").equals("0");
        drawer = findViewById(R.id.drawer_layout);
        nav_view = findViewById(R.id.nav_view);
        nav_view.setNavigationItemSelectedListener(this);
        emailView = nav_view.getHeaderView(0).findViewById(R.id.username);
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
        contactList = findViewById(R.id.recyclerview);

    }


    @Override
    protected void onResume() {
        super.onResume();

        ArrayList<Order> orders;
        // to get the name of the restaurant from owner page and login page to the list
        String ownerOrders = getIntent().getStringExtra("resName");
        //to send the email to the order page
        String customerOrders = getIntent().getStringExtra("Email2");
        // to get the email from load restaurant to order page
        String loadToOwner = getIntent().getStringExtra("Email1");
        if (customerOrders != null) {
            orders = resturantDb.getAllCustomerOrders(customerOrders);
            emailView.setText(customerOrders);
        } else if (ownerOrders != null) {
            orders = resturantDb.getAllRestaurantOrders(ownerOrders);
            emailView.setText(ownerOrders);
        } else {
            orders = resturantDb.getAllCustomerOrders(loadToOwner);
            emailView.setText(loadToOwner);
        }

        OrdersAdapter orderAdapter = new OrdersAdapter(this, R.layout.order_data, orders);
        contactList.setAdapter(orderAdapter);

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
            case R.id.home_nav:
                finish();
                break;
            case R.id.logout:
                intent = new Intent(this, Login.class);
                startActivity(intent);
                finish();
                break;
        }
        return true;
    }
}