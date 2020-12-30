package project.java4.talabat;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;


public class UpdateMeal extends AppCompatActivity {

    private ResturantDb db;

    private EditText editMealName, editMealPrice,editMealDescription;
    private Button btnUpdate;
    private ImageButton pickImag;

    byte[] image = null;
    int id;

    // for the delete button
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_meal_data);

        id = getIntent().getIntExtra("id", 0);

        db = new ResturantDb(this);

        Meal meal = db.getMealById2(id);


        editMealName = (EditText) findViewById(R.id.editMealName);
        editMealDescription = (EditText) findViewById(R.id.editMealDescription);
        editMealPrice = (EditText) findViewById(R.id.editMealPrice);
        pickImag = (ImageButton) findViewById(R.id.pickImg);

        btnUpdate = (Button) findViewById(R.id.btnUpdate);

        editMealName.setText(meal.getMealName());
        editMealDescription.setText(meal.getMealDescription());
        editMealPrice.setText(meal.getMealPrice() + "");

        Bitmap bitmap = BitmapFactory.decodeByteArray(meal.getImage(), 0, meal.getImage().length);
        pickImag.setImageBitmap(bitmap);
        image = getBytes(bitmap);


        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

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


                Meal newMeal = new Meal(id, MealName,MealDescription, MealPrice, image);

                db.updateMeal(newMeal);

                Toast.makeText(UpdateMeal.this, "Successfully updated", Toast.LENGTH_LONG).show();
                finish();

            }
        });


        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.delete_menu, menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.item_delet:

                showAlert();

                break;
        }

        return super.onOptionsItemSelected(item);
    }


    private void showAlert() {

        AlertDialog.Builder alertBilder = new AlertDialog.Builder(this);
        alertBilder.setTitle("Are you sure you want delete?")
                .setMessage("Are you sure?")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // delete contact
                        db.deletMeal(id);
                        finish();
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
        AlertDialog dialog = alertBilder.create();
        dialog.show();
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
