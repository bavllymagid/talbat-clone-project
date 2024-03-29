package project.java4.talabat.UI;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import project.java4.talabat.DataBases.RestaurantDb;
import project.java4.talabat.Classes.Meal;
import project.java4.talabat.R;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;


public class AddMeal_Activity extends AppCompatActivity {

    private EditText editMealName, editMealPrice, editMealDescription;
    private Button btnConfirm;
    private ImageButton pickImage;

    /**
     * waiting for bavlly to explain
     */
    private byte[] image = null;
    /**
     * restaurants database
     */
    private RestaurantDb db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_meal_data);

        db = new RestaurantDb(this);


        editMealName = (EditText) findViewById(R.id.editMealName);
        editMealDescription = (EditText) findViewById(R.id.editMealDescription);
        editMealPrice = (EditText) findViewById(R.id.editMealPrice);
        btnConfirm = (Button) findViewById(R.id.btnConfirm);
        pickImage = (ImageButton) findViewById(R.id.pickImg);



        btnConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String restaurantName = getIntent().getStringExtra("resName");
                String MealName = editMealName.getText().toString();
                String MealDescription = editMealDescription.getText().toString();

                int MealPrice;
                if (editMealPrice.getText().toString().equals("")) {
                    MealPrice = 0;
                } else {
                    MealPrice = Integer.parseInt(editMealPrice.getText().toString());
                }

                BitmapDrawable drawable = (BitmapDrawable) pickImage.getDrawable();
                Bitmap bitmap = drawable.getBitmap();
                image = getBytes(bitmap);


                Meal meal = new Meal(MealName, MealDescription, MealPrice, image, restaurantName);

                db.addMeal(meal);

                Toast.makeText(AddMeal_Activity.this, "Data is Added", Toast.LENGTH_LONG).show();
                finish();
            }
        });

    }

    /**
     * to get the image from gallery
     *
     * @param view takes image button view
     */
    public void openGalleries(View view) {
        Intent intentImg = new Intent(Intent.ACTION_GET_CONTENT);
        intentImg.setType("image/*");
        startActivityForResult(intentImg, 100);

    }

    /**
     * waiting for bavlly to explain
     */
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

    /**
     * waiting for bavlly to explain
     */
    public static byte[] getBytes(Bitmap bitmap) {
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 0, stream);
        return stream.toByteArray();
    }


}
