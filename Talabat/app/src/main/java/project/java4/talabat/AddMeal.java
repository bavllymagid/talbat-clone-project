package project.java4.talabat;

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

import java.io.ByteArrayOutputStream;
import java.io.InputStream;


public class AddMeal extends AppCompatActivity {

    EditText editMealName, editMealPrice,editMealDescription;
    Button btnConfirm;
    ImageButton pickImag;

    byte[] image = null;
    ResturantDb db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_meal_data);

        db = new ResturantDb(this);

        editMealName = (EditText) findViewById(R.id.editMealName);
        editMealDescription = (EditText) findViewById(R.id.editMealDescription);
        editMealPrice = (EditText) findViewById(R.id.editMealPrice);
        btnConfirm = (Button) findViewById(R.id.btnConfirm);
        pickImag = (ImageButton) findViewById(R.id.pickImg);


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

                BitmapDrawable drawable = (BitmapDrawable) pickImag.getDrawable();
                Bitmap bitmap = drawable.getBitmap();
                image = getBytes(bitmap);


                Meal meal = new Meal(MealName,MealDescription, MealPrice, image,restaurantName);

                db.addMeal(meal);

                Toast.makeText(AddMeal.this, "Data is Added", Toast.LENGTH_LONG).show();
                finish();
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
                pickImag.setImageBitmap(decodeStream);

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


}
