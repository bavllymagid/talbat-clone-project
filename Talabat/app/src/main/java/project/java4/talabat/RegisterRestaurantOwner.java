package project.java4.talabat;

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

import java.io.ByteArrayOutputStream;
import java.io.InputStream;

public class RegisterRestaurantOwner extends AppCompatActivity {

    private byte[] image = null;
    private ResturantDb db = new ResturantDb(this);
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
                if (isUserNameValid(email.getText().toString()) && isPasswordValid(password.getText().toString())) {
                    if (!personDb.searchEmail(email.getText().toString())) {
                        String ownerName = name.getText().toString();
                        String ownerEmail = email.getText().toString();
                        String ownerPassword = password.getText().toString();

                        String restaurantName = restaurant_name.getText().toString();
                        System.out.println(ownerEmail + ownerName + ownerPassword + restaurantName + "\n\n\n\n\n\n\n\n\n\n");

                        BitmapDrawable drawable = (BitmapDrawable) pickImage.getDrawable();
                        Bitmap bitmap = drawable.getBitmap();
                        image = getBytes(bitmap);

                        Resturant resturant = new Resturant(image, restaurantName);
                        db.addResturant(resturant);
                        personDb.createNewEmail(ownerName, ownerEmail, ownerPassword, "null ", "null", restaurantName, "1");
                        Intent intent = new Intent(RegisterRestaurantOwner.this, Login.class);
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