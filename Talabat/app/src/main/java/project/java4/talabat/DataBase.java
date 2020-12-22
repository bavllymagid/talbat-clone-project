package project.java4.talabat;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DataBase extends SQLiteOpenHelper {
    public static String databaseName = "emails";
    SQLiteDatabase database;

    public DataBase(Context context) {
        super(context, databaseName , null , 1);
    }
    //initializing the table
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table emails(name text not null,"+"email text primary key ,"+"password text not null," + "phone text not null,"+" address tetx not null)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists email");
        onCreate(db);
    }
    /// adding data to the table
    public boolean createNewEmail(String name , String email , String password , String phone , String address){
        ContentValues row = new ContentValues();
        row.put("name", name);
        row.put("email", email);
        row.put("password", password);
        row.put("phone", phone);
        row.put("address", address);

        database = getWritableDatabase();
        long result = database.insert("emails" , null , row);
        if(result == -1){
            return false;
        }else return true;
    }

   /// to search the data base for the logged in email and password
    public boolean searchEmail(String email){
        database = getWritableDatabase();
        Cursor cursor = database.rawQuery("select * from emails where email = '"+email+"'" , null);
        if(cursor.getCount()>0){
            return true;
        }
        return false;
    }

    /// to search the data base for the logged in email and password
    public boolean searchPassword( String password){
        database = getWritableDatabase();
        Cursor cursor = database.rawQuery("select * from emails where password = '"+password+"'" , null);
        if(cursor.getCount()>0){
            return true;
        }
        return false;
    }
}
