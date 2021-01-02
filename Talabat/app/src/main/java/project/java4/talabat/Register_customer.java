package project.java4.talabat;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Register_customer extends AppCompatActivity {

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
                if(isUserNameValid(email.getText().toString()) && isPasswordValid(password.getText().toString())){
                    if(!personDb.searchEmail(email.getText().toString())){
                        if(personDb.createNewEmail(name.getText().toString(), email.getText().toString(), password.getText().toString()
                                , phone.getText().toString(), address.getText().toString(),null,"0")) {
                            Intent intent = new Intent(Register_customer.this , Login.class);
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