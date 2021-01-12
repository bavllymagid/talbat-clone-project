package project.java4.talabat;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


import androidx.annotation.Nullable;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Random;

public class PersonDb extends SQLiteOpenHelper {
    public static String databaseName = "emails";
    SQLiteDatabase database;

    public PersonDb(Context context) {
        super(context, databaseName, null, 1);
    }

    //initializing the table
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table emails(name text not null," + "email varchar(250) primary key ," + "password text not null," + "phone varchar(250) ," + " address var(250) ," + " res_name text unique," +
                "" + " key_d text not null )");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists emails");
        onCreate(db);
    }

    /**
     * for the data encryption
     */

    public String md5(String s) {
        String MD5 = "MD5";
        try {
            // Create MD5 Hash
            MessageDigest digest = java.security.MessageDigest
                    .getInstance(MD5);
            digest.update(s.getBytes());
            byte messageDigest[] = digest.digest();
            // Create Hex String
            StringBuilder hexString = new StringBuilder();
            for (byte aMessageDigest : messageDigest) {
                String h = Integer.toHexString(0xFF & aMessageDigest);
                while (h.length() < 2)
                    h = "0" + h;
                hexString.append(h);
            }
            return hexString.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return "";
    }

    /// adding data to the table
    public boolean createNewEmail(String name, String email, String password, String phone, String address, String restaurantName, String key) {
        ContentValues row = new ContentValues();
        String salt = md5(email);
        password = md5(password + salt);
        row.put("name", name);
        row.put("email", email);
        row.put("password", password);
        row.put("phone", phone);
        row.put("address", address);
        row.put("res_name", restaurantName);
        row.put("key_d", key);

        database = getWritableDatabase();
        long result = database.insert("emails", null, row);
        if (result == -1) {
            return false;
        } else return true;
    }

    /// to search the data base for the logged in email and password
    public boolean searchEmail(String email) {
        database = getWritableDatabase();
        Cursor cursor = database.rawQuery("select * from emails where email = '" + email + "'", null);
        if (cursor.getCount() > 0) {
            return true;
        }
        return false;
    }

    public boolean searchState(String email) {
        database = getWritableDatabase();
        Cursor cursor = database.rawQuery("select * from emails where email = '" + email + "' and key_d = '0'", null);
        if (cursor.getCount() > 0) {
            return true;
        }
        return false;
    }

    /// to search the data base for the logged in email and password
    public boolean searchPassword(String password) {
        database = getWritableDatabase();
        Cursor cursor = database.rawQuery("select * from emails where password = '" + password + "'", null);
        if (cursor.getCount() > 0) {
            return true;
        }
        return false;
    }

    public boolean searchRestaurant(String restaurant) {
        database = getWritableDatabase();
        Cursor cursor = database.rawQuery("select * from emails where res_name = '" + restaurant + "'", null);
        if (cursor.getCount() > 0) {
            return true;
        }
        return false;
    }

    public String getRestaurantName(String email) {
        database = getWritableDatabase();
        Cursor cursor = database.rawQuery("select * from emails where email = '" + email + "'", null);
        String Restaurant = new String();
        if (cursor.moveToFirst()) {
            Restaurant = cursor.getString(cursor.getColumnIndex("res_name"));
        }
        return Restaurant;
    }
}
