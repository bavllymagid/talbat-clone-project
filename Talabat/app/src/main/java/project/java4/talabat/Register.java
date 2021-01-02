package project.java4.talabat;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class Register extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register);
    }
    public void toRegister_customer(View view){
        Intent intent = new Intent(this, Register_customer.class);
        startActivity(intent);
        finish();
    }

    public void toRegister_restaurant(View view){
        Intent intent = new Intent(this, RegisterRestaurantOwner.class);
        startActivity(intent);
        finish();
    }

}