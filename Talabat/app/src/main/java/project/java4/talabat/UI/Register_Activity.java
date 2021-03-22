package project.java4.talabat.UI;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import project.java4.talabat.R;

public class Register_Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register);
    }

    public void toRegister_customer(View view) {
        Intent intent = new Intent(this, CustomerRegister_Activity.class);
        startActivity(intent);

    }

    public void toRegister_restaurant(View view) {
        Intent intent = new Intent(this, RegisterRestaurantOwner_Activity.class);
        startActivity(intent);

    }

}