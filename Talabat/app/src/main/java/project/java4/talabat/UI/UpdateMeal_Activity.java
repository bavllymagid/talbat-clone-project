package project.java4.talabat.UI;

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
import project.java4.talabat.DataBases.RestaurantDb;
import project.java4.talabat.Classes.Meal;
import project.java4.talabat.R;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;


public class UpdateMeal_Activity extends AppCompatActivity {

    private RestaurantDb db;

    private EditText editMealName, editMealPrice,editMealDescription;
    private Button btnUpdate;
    private ImageButton pickImage;

    byte[] image = null;
    int id;

    // for the delete button
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_meal_data);

        id = getIntent().getIntExtra("id", 0);

        db = new RestaurantDb(this);

        Meal meal = db.getMealById2(id);


        editMealName =  findViewById(R.id.editMealName);
        editMealDescription =  findViewById(R.id.editMealDescription);
        editMealPrice =  findViewById(R.id.editMealPrice);
        pickImage =  findViewById(R.id.pickImg);

        btnUpdate =  findViewById(R.id.btnUpdate);

        editMealName.setText(meal.getMealName());
        editMealDescription.setText(meal.getMealDescription());
        editMealPrice.setText(meal.getMealPrice() + "");

        Bitmap bitmap = BitmapFactory.decodeByteArray(meal.getImage(), 0, meal.getImage().length);
        pickImage.setImageBitmap(bitmap);
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

                BitmapDrawable drawable = (BitmapDrawable) pickImage.getDrawable();
                Bitmap bitmap = drawable.getBitmap();
                image = getBytes(bitmap);


                Meal newMeal = new Meal(id, MealName,MealDescription, MealPrice, image);

                db.updateMeal(newMeal);

                Toast.makeText(UpdateMeal_Activity.this, "Successfully updated", Toast.LENGTH_LONG).show();
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

        if (item.getItemId() == R.id.item_delet) {
            showAlert();
        }
        return super.onOptionsItemSelected(item);
    }


    private void showAlert() {

        AlertDialog.Builder alertBuilder = new AlertDialog.Builder(this);
        alertBuilder.setTitle("Are you sure you want delete?")
                .setMessage("Are you sure?")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // delete contact
                        db.deleteMeal(id);
                        finish();
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
        AlertDialog dialog = alertBuilder.create();
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

}
