package project.java4.talabat;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.navigation.NavigationView;

public class orders extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    //initialization of drawer side bar
    private DrawerLayout drawer;
    private ActionBarDrawerToggle toggle;
    private Toolbar toolbar;
    private NavigationView nav_view;

    // for customer
    boolean isCustomer;

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



    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        Intent intent;
        switch (item.getItemId()) {
            case R.id.home_nav:
                if (isCustomer) {
                    intent = new Intent(this, LoadResturants.class);
                    startActivity(intent);
                } else {
                    finish();
                }
                break;
        }
        return true;
    }
}