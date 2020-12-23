package project.java4.talabat;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    private Button ownerLogin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //store reference to login and register buttons
        ownerLogin = (Button) findViewById(R.id.ownerLogin);
        

        //the button ownerLogin
        ownerLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, OwnerLoginPage.class);
                startActivity(intent);
            }
        });
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