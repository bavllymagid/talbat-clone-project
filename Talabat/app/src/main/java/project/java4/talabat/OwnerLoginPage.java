package project.java4.talabat;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class OwnerLoginPage extends AppCompatActivity {

    //variables
    EditText email, password,restaurantName;
    Button btnLogin;
    boolean isEmailValid, isPasswordValid,isRestaurantNameValid;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_owner_login_page);

        //set Id
        email = findViewById(R.id.ownerEmail);
        password = findViewById(R.id.ownerPassword);
        btnLogin = findViewById(R.id.btnOwnerLogin);
        restaurantName = findViewById(R.id.restaurantName);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SetValidation();
            }

            private void SetValidation() {

                // Check for a valid restaurantName.
                if (restaurantName.getText().toString().isEmpty()){
                    restaurantName.setError("An empty field");
                    isRestaurantNameValid = false;
                }
                else if (restaurantName.getText().toString().equals("Mac") || restaurantName.getText().toString().equals("Kfc")){
                    isRestaurantNameValid = true;
                }
                else  {
                    restaurantName.setError("Wrong Restaurant");
                    Toast.makeText(getApplicationContext(), "If you don't have your Restaurant registered contact us", Toast.LENGTH_SHORT).show();
                    isRestaurantNameValid = false;
                }

                // Check for a valid email.
                if (email.getText().toString().isEmpty()){
                    email.setError("An empty field");
                    isEmailValid = false;
                }
                else if (!Patterns.EMAIL_ADDRESS.matcher(email.getText().toString()).matches()){
                    email.setError("Invalid email");
                    isEmailValid = false;
                }
                else  {
                    isEmailValid = true;
                }

                // Check for a valid password.
                if (password.getText().toString().isEmpty()) {

                    password.setError("An empty field");
                    isPasswordValid = false;
                }
                else if (password.getText().length() < 6) {
                    password.setError("At least 6");
                    isPasswordValid = false;
                }
                else  {
                    isPasswordValid = true;
                }

                // Check for a valid
                if (isEmailValid && isPasswordValid && isRestaurantNameValid) {
                    Toast.makeText(getApplicationContext(), "Successfully", Toast.LENGTH_SHORT).show();
                    if (restaurantName.getText().toString().equals("Mac")){
                        Intent intent = new Intent(OwnerLoginPage.this, Mac.class);
                        intent.putExtra("Empty","Empty");
                        startActivity(intent);
                    }
                }


            }
        });

    }
}