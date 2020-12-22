package project.java4.talabat;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;


public class Login extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        final EditText usernameEditText = findViewById(R.id.username);
        final EditText passwordEditText = findViewById(R.id.password);
        final Button loginButton = findViewById(R.id.login);
        final DataBase eDataBase = new DataBase(this);


        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //searching for the validity of the email
                if(eDataBase.searchEmail(usernameEditText.getText().toString()) && eDataBase.searchPassword(passwordEditText.getText().toString())){
                    Intent intent = new Intent(Login.this, LoadResturants.class);
                    startActivity(intent);
                }else if(passwordEditText.getText().toString() == null ||
                        !usernameEditText.getText().toString().contains("@"))  {
                    Toast.makeText(getApplicationContext() , "Invalid input" , Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(getApplicationContext() , "The email or the password is incorrect", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public void registering(View view) {
        Intent intent = new Intent(Login.this, register.class);
        startActivity(intent);
    }
}