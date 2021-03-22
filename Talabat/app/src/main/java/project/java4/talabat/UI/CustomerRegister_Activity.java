package project.java4.talabat.UI;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import project.java4.talabat.Classes.Customer;
import project.java4.talabat.DataBases.PersonDb;
import project.java4.talabat.R;

public class CustomerRegister_Activity extends AppCompatActivity {

    private EditText name , email , password , phone ,address;
    private Button registerButton ;
    private PersonDb personDb = new PersonDb(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register_customer);

        name = findViewById(R.id.textName);
        email = findViewById(R.id.textEmail);
        password = findViewById(R.id.textPassword);
        phone = findViewById(R.id.textPhone);
        address = findViewById(R.id.textAddress);
        registerButton = findViewById(R.id.registerButton);




        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Customer customer = new Customer(name.getText().toString(), email.getText().toString(), password.getText().toString()
                        , phone.getText().toString(), address.getText().toString());

                if(isUserNameValid(customer.getEmail()) && isPasswordValid(customer.getPassword())){
                    if(!personDb.searchEmail(customer.getEmail())){
                        if(personDb.createNewEmail(customer,"0")) {
                            Intent intent = new Intent(CustomerRegister_Activity.this , Login_Activity.class);
                            startActivity(intent);
                            finish();
                            Toast.makeText(getApplicationContext(), "registration succeeded ", Toast.LENGTH_SHORT).show();
                        }
                    }else Toast.makeText(getApplicationContext(), "The Email is already found" ,Toast.LENGTH_SHORT).show();
                }else Toast.makeText(getApplicationContext(), "Enter valid Input" ,Toast.LENGTH_SHORT).show();
            }
        });
    }

    // A placeholder username validation check
    private boolean isUserNameValid(String username) {
        if (username == null) {
            return false;
        }
        if (username.contains("@")) {
            return Patterns.EMAIL_ADDRESS.matcher(username).matches();
        } else {
            return false;
        }
    }

    // A placeholder password validation check
    private boolean isPasswordValid(String password) {
        return password != null && password.trim().length() > 5;
    }

}