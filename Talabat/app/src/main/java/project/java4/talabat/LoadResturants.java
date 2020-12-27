package project.java4.talabat;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;


public class LoadResturants extends AppCompatActivity implements ResturantAdapter.OnResListener, SearchView.OnQueryTextListener, NavigationView.OnNavigationItemSelectedListener {
    // initialization of recycler view
    private RecyclerView recyclerView;
    private ResturantAdapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private ArrayList<Resturant> resturantlist = new ArrayList<>();
    //initialization of drawer side bar
    private DrawerLayout drawer;
    private ActionBarDrawerToggle toggle;
    NavigationView nav_view;
    private Toolbar toolbar;
    private ResturantDb dbase = new ResturantDb(this);
    PersonDb personDb = new PersonDb(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_load_resturants);
        initializeUI();
    }


    private void initializeUI() {
        //initialization of drawer
        drawer = findViewById(R.id.drawer_layout);
        nav_view = findViewById(R.id.nav_view);
        nav_view.setNavigationItemSelectedListener(this);
        nav_view.setCheckedItem(R.id.home_nav);
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.open, R.string.close);

        drawer.addDrawerListener(toggle);
        //to save the state of drawer
        toggle.syncState();



        recyclerView = (RecyclerView) findViewById(R.id.recyclerview);
        layoutManager = new LinearLayoutManager(this);
    }

    /// displaying the list view
    @Override
    protected void onResume() {
        super.onResume();

        resturantlist = dbase.getAllResturants();

        adapter = new ResturantAdapter(resturantlist, this);

        recyclerView.setLayoutManager(layoutManager);

        recyclerView.setAdapter(adapter);

        drawer.close();

    }

    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else
            super.onBackPressed();
    }

    // To navigate to the menu of the resturant
    @Override
    public void OnResClick(int position) {
        personDb.searchResturant(resturantlist.get(position).getName());
        Intent intent = new Intent(this, OwnerPage.class);
        intent.putExtra("res_name", resturantlist.get(position).getName());
        intent.putExtra("customer", "0");
        intent.putExtra("Email1" , getIntent().getStringExtra("Email"));
        Toast.makeText(getApplicationContext(), resturantlist.get(position).getName(), Toast.LENGTH_SHORT).show();
        startActivity(intent);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.search, menu);
        MenuItem searchItem = menu.findItem(R.id.action_search);
        SearchView searchView = (SearchView) searchItem.getActionView();
        searchView.setQueryHint("search for restaurants");
        searchView.setOnQueryTextListener(this);
        return true;
    }

    @Override
    public boolean onQueryTextSubmit(String newText) {
        adapter.getFilter().filter(newText);
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        adapter.getFilter().filter(newText);
        return false;
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        Intent intent;
        switch (item.getItemId()) {
            case R.id.your_orders_nav:
                intent = new Intent(this, OrderActivity.class);
                intent.putExtra("customer", "0");
                intent.putExtra("Email1" , getIntent().getStringExtra("Email"));
                startActivity(intent);
                break;
        }
        return true;
    }
}