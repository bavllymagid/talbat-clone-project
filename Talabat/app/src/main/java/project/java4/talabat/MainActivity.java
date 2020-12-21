package project.java4.talabat;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


import project.java4.talabat.ui.login.LoginActivity;

public class MainActivity extends AppCompatActivity {

    private TextView talabatSlogan ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initializeUI();
    }
    /**
     * Initialize the UI.
     */
    private void initializeUI(){
        setContentView(R.layout.activity_main);

        //store reference to the talabat
        talabatSlogan = (TextView) findViewById(R.id.talabat);

    }
    public void logingin(View view){
        Intent intent = new Intent(this,LoginActivity.class);
        startActivity(intent);
    }
    public void registering(View view){
        Intent intent = new Intent(this,register.class);
        startActivity(intent);
    }
}