package project.java4.talabat.UI;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import project.java4.talabat.DataBases.PersonDb;
import project.java4.talabat.R;


public class Login_Activity extends AppCompatActivity {
    /**
     * layout elements
     */
    EditText usernameEditText;
    EditText passwordEditText;
    Button loginButton;

    /**
     * users database
     */
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
                String tempUser = ePersonDb.md5(usernameEditText.getText().toString());
                final String password = ePersonDb.md5(passwordEditText.getText().toString()+tempUser);
                //searching for the validity of the email
                if (ePersonDb.searchEmail(usernameEditText.getText().toString()) && ePersonDb.searchPassword(password)) {
                    // to send the owner to owner page
                    if (!ePersonDb.searchState(usernameEditText.getText().toString())) {
                        sendRestaurant(v);
                    } else {
                        Intent intent = new Intent(Login_Activity.this, CustomerPage_Activity.class);
                        intent.putExtra("Email", usernameEditText.getText().toString());
                        startActivity(intent);
                        finish();
                    }
                } else if (!usernameEditText.getText().toString().contains("@")) {
                    Toast.makeText(getApplicationContext(), "Invalid input", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getApplicationContext(), "The email or the password is incorrect", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    /**
     * opens register layout
     */
    public void registering(View view) {
        Intent intent = new Intent(this, Register_Activity.class);
        intent.putExtra("Email", usernameEditText.getText().toString());
        startActivity(intent);
    }

    /**
     * opens owner page (restaurant page) layout
     */
    public void sendRestaurant(View view) {
        Intent intent = new Intent(this, OwnerPage_Activity.class);
        intent.putExtra("restaurantName", ePersonDb.getRestaurantName(usernameEditText.getText().toString()));
        intent.putExtra("customer", "1");
        startActivity(intent);
    }

    /**
     * when pressing back from login
     */
    @Override
    public void onBackPressed() {
        finishAffinity();
    }
}