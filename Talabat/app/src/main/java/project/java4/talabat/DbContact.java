package project.java4.talabat;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;


public class DbContact extends SQLiteOpenHelper {

    private static final String DB_NAME = "Meal_db";
    private static final int DB_VESION = 2;

    private static final String KEY_ID = "id";
    private static final String KEY_MealName = "MealName";
    private static final String KEY_MealDescription = "MealDescription";
    private static final String KEY_MealPrice = "MealPrice";
    private static final String KEY_IMG = "image";

    private static final String TABLE_Meal_Data = "contacts";


    public DbContact(Context context) {
        super(context, DB_NAME, null, DB_VESION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String create_table = "create table " + TABLE_Meal_Data + "(" + KEY_ID + " integer primary key autoincrement,"
                + KEY_MealName + " varchar(255) DEFAULT'',"
                + KEY_MealDescription + " varchar(255) DEFAULT'',"
                + KEY_MealPrice + " integer ,"
                + KEY_IMG + " blob)";

        Log.d("create", create_table);
        db.execSQL(create_table);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        String delete_query = "DROP table if exists " + TABLE_Meal_Data;
        db.execSQL(delete_query);

        onCreate(db);

    }


    public void addContact(Meal meal) {

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_MealName, meal.getMealName());
        values.put(KEY_MealDescription, meal.getMealDescription());
        values.put(KEY_MealPrice, meal.getMealPrice());
        values.put(KEY_IMG, meal.getImage());

        db.insert(TABLE_Meal_Data, null, values);

    }

    public ArrayList<Meal> getAllContacts() {
        ArrayList<Meal> meals = new ArrayList<>();

        String select_query = "select * from " + TABLE_Meal_Data;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(select_query, null);

        if (cursor.moveToFirst()) {

            do {

                int id = cursor.getInt(cursor.getColumnIndex(KEY_ID));
                String MealName = cursor.getString(cursor.getColumnIndex(KEY_MealName));
                String MealDescription = cursor.getString(cursor.getColumnIndex(KEY_MealDescription));
                int MealPrice = cursor.getInt(cursor.getColumnIndex(KEY_MealPrice));
                byte[] image = cursor.getBlob(cursor.getColumnIndex(KEY_IMG));

                Meal meal = new Meal(id, MealName, MealDescription , MealPrice, image);

                meals.add(meal);

            } while (cursor.moveToNext());

        }

        return meals;
    }

    // الطريقه الاولى لجلب المعلومات لقاعده البيانات
    public Meal getContactById(int id) {

        SQLiteDatabase db = this.getReadableDatabase();

        String select_query = "select * from " + TABLE_Meal_Data + "where id=" + id;

        Cursor cursor = db.rawQuery(select_query, null);

        Meal meal = null;

        if (cursor.moveToFirst()) {

            int id_contact = cursor.getInt(cursor.getColumnIndex(KEY_ID));
            String MealName = cursor.getString(cursor.getColumnIndex(KEY_MealName));
            String MealDescription = cursor.getString(cursor.getColumnIndex(KEY_MealDescription));
            int MealPrice = cursor.getInt(cursor.getColumnIndex(KEY_MealPrice));
            byte[] image = cursor.getBlob(cursor.getColumnIndex(KEY_IMG));


            meal = new Meal(id, MealName,MealDescription, MealPrice, image);

        }
        return meal;
    }


    public Meal getContactById2(int id) {

        Meal meal = null;

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_Meal_Data, new String[]{"id", "MealName","MealDescription", "MealPrice", "image"}, "id=?",
                new String[]{String.valueOf(id)}, null, null, null, null);

        if (cursor.moveToFirst()) {

            int id_contact = cursor.getInt(cursor.getColumnIndex(KEY_ID));
            String MealName = cursor.getString(cursor.getColumnIndex(KEY_MealName));
            String MealDescription = cursor.getString(cursor.getColumnIndex(KEY_MealDescription));
            int MealPrice = cursor.getInt(cursor.getColumnIndex(KEY_MealPrice));
            byte[] image = cursor.getBlob(cursor.getColumnIndex(KEY_IMG));

            meal = new Meal(id, MealName,MealDescription, MealPrice, image);

        }

        return meal;

    }

    public void updateContact(Meal meal) {

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_MealName, meal.getMealName());
        values.put(KEY_MealDescription, meal.getMealDescription());
        values.put(KEY_MealPrice, meal.getMealPrice());
        values.put(KEY_IMG, meal.getImage());

        db.update(TABLE_Meal_Data, values, "id=?", new String[]{String.valueOf(meal.getId())});

    }


    public void deletContact(int id) {
        SQLiteDatabase db = this.getWritableDatabase();

        db.delete(TABLE_Meal_Data, "id=?", new String[]{String.valueOf(id)});

    }


}
