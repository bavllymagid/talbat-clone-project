package project.java4.talabat;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import java.util.ArrayList;

import project.java4.talabat.ui.login.LoginActivity;

public class LoadResturants extends AppCompatActivity implements ResturantAdapter.OnResListener {

    private RecyclerView recyclerView ;
    private RecyclerView.Adapter adapter ;
    private RecyclerView.LayoutManager layoutManager ;
    private ArrayList<Resturant> resturantlist = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_load_resturants);
        initializeUI();
    }

    //intializing of the list of resturants
    private void initializeUI() {
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
}