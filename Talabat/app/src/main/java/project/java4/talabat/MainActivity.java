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
    private Button logIn ;
    private Button register;
    private Button ownerLogin;

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

        //store reference to login and register buttons
        logIn = (Button) findViewById(R.id.login);
        register = (Button) findViewById(R.id.register);
        ownerLogin = (Button) findViewById(R.id.ownerLogin);
        //listening to the click of the button
        logIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickSwitchActivity(v);
            }
        });

        //the button ownerLogin
        ownerLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Intent intent = new Intent(MainActivity.this,OwnerLoginPage.class);
                Intent intent = new Intent(MainActivity.this,OwnerLoginPage.class);
                startActivity(intent);
            }
        });

    }

    public void onClickSwitchActivity(View view) {
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
    }

}