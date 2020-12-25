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

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import java.util.ArrayList;


public class LoadResturants extends AppCompatActivity implements ResturantAdapter.OnResListener, SearchView.OnQueryTextListener {
    // initialization of recycler view
    private RecyclerView recyclerView;
    private ResturantAdapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private ArrayList<Resturant> resturantlist = new ArrayList<>();
    private Toolbar toolbar;
    //initialization of drawer side bar
    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle drawerToggle;
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
        mDrawerLayout = findViewById(R.id.drawer_layout);
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        drawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout,toolbar ,R.string.open, R.string.close);

        mDrawerLayout.addDrawerListener(drawerToggle);
        //to save the state of drawer
        drawerToggle.syncState();
        //the bar that we use for opening the drawer
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);





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

    }
    @Override
    public void onBackPressed() {
        if(mDrawerLayout.isDrawerOpen(GravityCompat.START)){
            mDrawerLayout.closeDrawer(GravityCompat.START);
        }
        else
            super.onBackPressed();
    }

    // To navigate to the menu of the resturant
    @Override
    public void OnResClick(int position) {
        if(personDb.searchResturant(resturantlist.get(position).getName())){
            Intent intent = new Intent(this , OwnerPage.class);
            intent.putExtra("res_name" , resturantlist.get(position).getName());
            Toast.makeText(getApplicationContext(), resturantlist.get(position).getName(), Toast.LENGTH_SHORT).show();
            startActivity(intent);
        }
    }

    //to detect the click on the button of sidebar
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if (drawerToggle.onOptionsItemSelected(item)) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.search, menu);
        MenuItem searchItem = menu.findItem(R.id.action_search);
        SearchView searchView = (SearchView) searchItem.getActionView();
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
}