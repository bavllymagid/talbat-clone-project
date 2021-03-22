package project.java4.talabat.DataBases;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import project.java4.talabat.Classes.Customer;
import project.java4.talabat.Classes.RestaurantOwner;


import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

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

    /// adding data to the table related to customer
    public boolean createNewEmail(Customer customer, String key) {
        ContentValues row = new ContentValues();
        String salt = md5(customer.getEmail());
        customer.setPassword(md5(customer.getPassword() + salt));
        row.put("name", customer.getName());
        row.put("email", customer.getEmail());
        row.put("password", customer.getPassword());
        row.put("phone", customer.getPhoneNumber());
        row.put("address", customer.getAddress());
        row.put("res_name", "null");
        row.put("key_d", key);

        database = getWritableDatabase();
        long result = database.insert("emails", null, row);
        if (result == -1) {
            return false;
        } else return true;
    }

    /// adding data to the table related to owner
    public boolean createNewEmail(RestaurantOwner restaurantOwner, String key) {
        ContentValues row = new ContentValues();
        String salt = md5(restaurantOwner.getEmail());
        restaurantOwner.setPassword(md5(restaurantOwner.getPassword() + salt));
        row.put("name", restaurantOwner.getName());
        row.put("email", restaurantOwner.getEmail());
        row.put("password", restaurantOwner.getPassword());
        row.put("phone", "null");
        row.put("address", "null");
        row.put("res_name", restaurantOwner.getRestaurantName());
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

    /// to search the data base for the logged in email and password
    public String getName(String email) {
        database = getWritableDatabase();
        String name = new String();
        Cursor cursor = database.rawQuery("select * from emails where email = '" + email + "'", null);
        if(cursor.moveToFirst()){
            name = cursor.getString(cursor.getColumnIndex("name"));
        }
        return name;
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