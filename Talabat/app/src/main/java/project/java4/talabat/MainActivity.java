package project.java4.talabat;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
   // log in button function
    public void loggingIn(View view){
        Intent intent = new Intent(this,Login.class);
        startActivity(intent);
    }

    //register text function
    public void registering(View view){
        Intent intent = new Intent(this,register.class);
        startActivity(intent);
    }
}