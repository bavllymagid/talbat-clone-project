package project.java4.talabat;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class Login extends AppCompatActivity {
    EditText usernameEditText;
    EditText passwordEditText;
    Button loginButton;
    PersonDb ePersonDb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        usernameEditText = findViewById(R.id.username);
        passwordEditText = findViewById(R.id.password);
        loginButton = findViewById(R.id.login);
        ePersonDb = new PersonDb(this);


        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //searching for the validity of the email
                if (ePersonDb.searchEmail(usernameEditText.getText().toString()) && ePersonDb.searchPassword(passwordEditText.getText().toString())) {
                    // to send the owner to owner page
                    if (!ePersonDb.searchState(usernameEditText.getText().toString())) {
                        sendrestaurant(v);
                    } else {
                        Intent intent = new Intent(Login.this, LoadResturants.class);
                        intent.putExtra("Email" , usernameEditText.getText().toString());
                        startActivity(intent);
                    }
                } else if (passwordEditText.getText().toString() == null ||
                        !usernameEditText.getText().toString().contains("@")) {
                    Toast.makeText(getApplicationContext(), "Invalid input", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getApplicationContext(), "The email or the password is incorrect", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    //register_customer text function
    public void registering(View view) {
        Intent intent = new Intent(this, Register.class);
        intent.putExtra("Email" , usernameEditText.getText().toString());
        startActivity(intent);
    }

    // to send the owner to his resturant
    public void sendrestaurant(View view) {
        Intent intent = new Intent(this, OwnerPage.class);
        intent.putExtra("returantName", ePersonDb.getResturantName(usernameEditText.getText().toString()));
        intent.putExtra("customer", "1");
        Toast.makeText(getApplicationContext(), ePersonDb.getResturantName(usernameEditText.getText().toString()), Toast.LENGTH_SHORT).show();
        startActivity(intent);
    }

}