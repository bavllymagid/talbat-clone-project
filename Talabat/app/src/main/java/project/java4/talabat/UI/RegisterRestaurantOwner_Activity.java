package project.java4.talabat.UI;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;
import project.java4.talabat.DataBases.PersonDb;
import project.java4.talabat.DataBases.RestaurantDb;
import project.java4.talabat.R;
import project.java4.talabat.Classes.RestaurantOwner;
import project.java4.talabat.Classes.Restaurant;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;

public class RegisterRestaurantOwner_Activity extends AppCompatActivity {

    private byte[] image = null;
    private RestaurantDb db = new RestaurantDb(this);
    private PersonDb personDb = new PersonDb(this);
    private ImageButton pickImage;
    private EditText name, password, email, restaurant_name;
    private Button add;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register_restaurant_owner);

        name = findViewById(R.id.textName);
        email = findViewById(R.id.textEmail);
        password = findViewById(R.id.textPassword);
        restaurant_name = findViewById(R.id.restaurantName);
        add = findViewById(R.id.registerButton);
        pickImage = findViewById(R.id.pickImg);

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RestaurantOwner restaurantOwner = new RestaurantOwner(name.getText().toString(), email.getText().toString(),
                        password.getText().toString(), restaurant_name.getText().toString());

                if (isUserNameValid(restaurantOwner.getEmail()) && isPasswordValid(restaurantOwner.getPassword())) {
                    if (!personDb.searchEmail(restaurantOwner.getEmail())) {
                        BitmapDrawable drawable = (BitmapDrawable) pickImage.getDrawable();
                        Bitmap bitmap = drawable.getBitmap();
                        image = getBytes(bitmap);

                        Restaurant resturant = new Restaurant(image, restaurantOwner.getRestaurantName());
                        db.addRestaurant(resturant);
                        personDb.createNewEmail(restaurantOwner , "1");
                        Intent intent = new Intent(RegisterRestaurantOwner_Activity.this, Login_Activity.class);
                        Toast.makeText(getApplicationContext(), "Registered successfully", Toast.LENGTH_SHORT).show();
                        startActivity(intent);
                        finish();
                    } else
                        Toast.makeText(getApplicationContext(), "The Email is already found", Toast.LENGTH_SHORT).show();
                } else Toast.makeText(getApplicationContext(), "Enter valid Input", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void openGalleries(View view) {

        Intent intentImg = new Intent(Intent.ACTION_GET_CONTENT);
        intentImg.setType("image/*");
        startActivityForResult(intentImg, 100);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK && requestCode == 100) {

            Uri uri = data.getData();

            try {
                InputStream inputStream = getContentResolver().openInputStream(uri);
                Bitmap decodeStream = BitmapFactory.decodeStream(inputStream);
                pickImage.setImageBitmap(decodeStream);

                image = getBytes(decodeStream);

            } catch (Exception ex) {
                Log.e("ex", ex.getMessage());
            }

        }
    }

    public static byte[] getBytes(Bitmap bitmap) {
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 0, stream);
        return stream.toByteArray();
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