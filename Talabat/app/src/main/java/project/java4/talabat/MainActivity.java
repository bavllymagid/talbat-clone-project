package project.java4.talabat;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    private Button loginEntry;
    private TextView talabat;
    private View main ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        loginEntry = findViewById(R.id.login);
        talabat = findViewById(R.id.talabat);

        main = findViewById(R.id.main);
        // to handle the loading screen
        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                loginEntry.setVisibility(View.VISIBLE);
                talabat.setVisibility(View.VISIBLE);
                // Set the color
                main.setBackgroundColor(getResources().getColor(android.R.color.background_light));
            }
        }, 3000);
    }

    /**
     * opens login layout
     */
    public void loggingIn(View view){
        Intent intent = new Intent(this,Login.class);
        startActivity(intent);
        finish();
    }
}