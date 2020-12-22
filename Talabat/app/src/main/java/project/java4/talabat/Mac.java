package project.java4.talabat;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Gallery;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

public class Mac extends AppCompatActivity {

    Button btnAddMeal;
    ImageView viewMealImage;
    Button btnSelectMealImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mac);

        //VIEWS
        btnAddMeal = (Button) findViewById(R.id.btnAddMeal);
        btnSelectMealImage = (Button) findViewById(R.id.btnSelectMealImage);
        viewMealImage = (ImageView) findViewById(R.id.viewMealImage);

        //Add Meal Button
        btnAddMeal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Add Meal Body
                AlertDialog.Builder alertDialog = new AlertDialog.Builder(Mac.this);
                alertDialog.setTitle("Add Meal");
                alertDialog.setIcon(R.drawable.ic_launcher_foreground);
                alertDialog.setView(R.layout.add_meal_layout);

                //Add Meal Methods
                alertDialog.setPositiveButton("Add",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog,int which) {
                                Toast.makeText(getApplicationContext(),"You added a meal", Toast.LENGTH_SHORT).show();
                                dialog.cancel();
                            }
                        });

                alertDialog.setNegativeButton("Cancel",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                Toast.makeText(getApplicationContext(),"Nothing", Toast.LENGTH_SHORT).show();
                                dialog.cancel();
                            }
                        });
                alertDialog.show();
            }
        });


    }

}