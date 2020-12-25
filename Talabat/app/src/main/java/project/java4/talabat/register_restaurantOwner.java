package project.java4.talabat;

import androidx.appcompat.app.AppCompatActivity;

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

import java.io.ByteArrayOutputStream;
import java.io.InputStream;

public class register_restaurantOwner extends AppCompatActivity {

    byte[] image = null;
    ResturantDb db = new ResturantDb(this);
    PersonDb personDb = new PersonDb(this);
    ImageButton pickImage;
    EditText name , password , email , resturant_name;
    Button add ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register_restaurant_owner);

        name = (EditText) findViewById(R.id.textname);
        email = (EditText) findViewById(R.id.textemail);
        password = (EditText) findViewById(R.id.textpassword);
        resturant_name = (EditText) findViewById(R.id.restaurantName);
        add = (Button) findViewById(R.id.registerButton);
        pickImage = (ImageButton) findViewById(R.id.pickImg);

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String ownerName = name.getText().toString();
                String ownerEmail = email.getText().toString();
                String ownerPassword = password.getText().toString();

                String resturantName = resturant_name.getText().toString();
                System.out.println(ownerEmail + ownerName + ownerPassword+resturantName+"\n\n\n\n\n\n\n\n\n\n");

                BitmapDrawable drawable = (BitmapDrawable) pickImage.getDrawable();
                Bitmap bitmap = drawable.getBitmap();
                image = getBytes(bitmap);

                Resturant resturant = new Resturant(image , resturantName);
                db.addResturant(resturant);
                personDb.createNewEmail(ownerName , ownerEmail , ownerPassword , "null ","null",resturantName ,"1");
                Intent intent = new Intent(register_restaurantOwner.this , Login.class);
                Toast.makeText(getApplicationContext() , "Registered successfully" , Toast.LENGTH_SHORT).show();
                startActivity(intent);

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
}