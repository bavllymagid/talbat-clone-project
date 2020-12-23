package project.java4.talabat;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.ArrayList;

public class AddMeal extends AppCompatActivity {

    ImageView viewMealImage;
    Button btnSelectMealImage,btnAddMealDone,btnCancelAddMeal;
    EditText inputMealName,inputMealDescription,inputMealPrice;
    private static final int IMAGE_PICK_CODE = 123;


    public void setInputMealName(EditText inputMealName) {
        this.inputMealName = inputMealName;
    }

    public EditText getInputMealName() {
        return inputMealName;
    }

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_meal_layout);

        btnSelectMealImage = (Button) findViewById(R.id.btnSelectMealImage);
        btnAddMealDone = (Button) findViewById(R.id.btnAddMealDone);
        btnCancelAddMeal = (Button) findViewById(R.id.btnCancelAddMeal);
        viewMealImage = (ImageView) findViewById(R.id.viewMealImage);
        inputMealPrice = (EditText) findViewById(R.id.inputMealPrice);
        inputMealDescription = (EditText) findViewById(R.id.inputMealDescription);
        inputMealName = (EditText) findViewById(R.id.inputMealName);


        // Button Select Meal Image
        btnSelectMealImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent,"Pick an image"),IMAGE_PICK_CODE);
            }
        });

        // Button Add Meal Done
        btnAddMealDone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AddMeal.this, Mac.class);
                intent.putExtra("Empty","NotEmpty");
                intent.putExtra("MealName",inputMealName.getText().toString());
                intent.putExtra("MealDescription",inputMealDescription.getText().toString());
                intent.putExtra("MealPrice",inputMealPrice.getText().toString());
                startActivity(intent);
                //finish();


            }
        });



        // Button Cancel Meal Image
        btnCancelAddMeal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


    }


    // Select Meal Image Logic
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == IMAGE_PICK_CODE && resultCode == RESULT_OK && data != null)
        {
            Uri imageData = data.getData();
            viewMealImage.setImageURI(imageData);
        }
    }
}