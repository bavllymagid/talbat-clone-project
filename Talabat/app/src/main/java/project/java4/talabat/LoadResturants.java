package project.java4.talabat;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import java.util.ArrayList;


public class LoadResturants extends AppCompatActivity implements ResturantAdapter.OnResListener , SearchView.OnQueryTextListener {
    // initialization of recycler view
    private RecyclerView recyclerView ;
    private ResturantAdapter adapter ;
    private RecyclerView.LayoutManager layoutManager ;
    private ArrayList<Resturant> resturantlist = new ArrayList<>();

    //initialization of drawer side bar
    private DrawerLayout mDrawerLayout ;
    private ActionBarDrawerToggle drawerToggle ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_load_resturants);
        initializeUI();
    }



    private void initializeUI() {
        //initialization of drawer
        mDrawerLayout = findViewById(R.id.drawer_layout);
        drawerToggle = new ActionBarDrawerToggle(this , mDrawerLayout, R.string.open , R.string.close);

        mDrawerLayout.addDrawerListener(drawerToggle);
        //to save the state of drawer
        drawerToggle.syncState();
        //the bar that we use for opening the drawer
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);




        //intializing of the list of resturants
        resturantlist.add(new Resturant(R.drawable.ic_android , "mac" , "1"));
        resturantlist.add(new Resturant(R.drawable.ic_baseline , "kfc" , "2"));
        resturantlist.add(new Resturant(R.drawable.ic_baseline_accessible , "zacks" , "3"));
        resturantlist.add(new Resturant(R.drawable.ic_android , "mac" , "1"));
        resturantlist.add(new Resturant(R.drawable.ic_baseline , "kfc" , "2"));
        resturantlist.add(new Resturant(R.drawable.ic_baseline_accessible , "zacks" , "3"));
        resturantlist.add(new Resturant(R.drawable.ic_android , "mac" , "1"));
        resturantlist.add(new Resturant(R.drawable.ic_baseline , "kfc" , "2"));
        resturantlist.add(new Resturant(R.drawable.ic_baseline_accessible , "zacks" , "3"));
        resturantlist.add(new Resturant(R.drawable.ic_android , "mac" , "1"));
        resturantlist.add(new Resturant(R.drawable.ic_baseline , "kfc" , "2"));
        resturantlist.add(new Resturant(R.drawable.ic_baseline_accessible , "zacks" , "3"));
        resturantlist.add(new Resturant(R.drawable.ic_android , "mac" , "1"));
        resturantlist.add(new Resturant(R.drawable.ic_baseline , "kfc" , "2"));
        resturantlist.add(new Resturant(R.drawable.ic_baseline_accessible , "zacks" , "3"));
        resturantlist.add(new Resturant(R.drawable.ic_android , "mac" , "1"));
        resturantlist.add(new Resturant(R.drawable.ic_baseline , "kfc" , "2"));
        resturantlist.add(new Resturant(R.drawable.ic_baseline_accessible , "zacks" , "3"));
        resturantlist.add(new Resturant(R.drawable.ic_android , "mac" , "1"));
        resturantlist.add(new Resturant(R.drawable.ic_baseline , "kfc" , "2"));
        resturantlist.add(new Resturant(R.drawable.ic_baseline_accessible , "zacks" , "3"));
        resturantlist.add(new Resturant(R.drawable.ic_android , "mac" , "1"));
        resturantlist.add(new Resturant(R.drawable.ic_baseline , "kfc" , "2"));
        resturantlist.add(new Resturant(R.drawable.ic_baseline_accessible , "zacks" , "3"));
        resturantlist.add(new Resturant(R.drawable.ic_android , "mac" , "1"));
        resturantlist.add(new Resturant(R.drawable.ic_baseline , "kfc" , "2"));
        resturantlist.add(new Resturant(R.drawable.ic_baseline_accessible , "zacks" , "3"));


        recyclerView = findViewById(R.id.recyclerview);
        recyclerView.setHasFixedSize(true);
        adapter = new ResturantAdapter(resturantlist , this);
        layoutManager = new LinearLayoutManager(this);

        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);


    }

    // To navigate to the menu of the resturant
    @Override
    public void OnResClick(int position) {

    }

    //to detect the click on the button of sidebar
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if(drawerToggle.onOptionsItemSelected(item)){
            return true ;
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