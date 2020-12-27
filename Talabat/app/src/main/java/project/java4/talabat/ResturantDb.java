package project.java4.talabat;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.UUID;


public class ResturantDb extends SQLiteOpenHelper {

    private static final String DB_NAME = "Meal_db";
    private static final int DB_VERSION = 2;

    //  //Resturants table columns
    private static final String KEY_ResturantName = "ResturantName";
    private static final String ResKEY_IMG = "image";
    private static final String TABLE_Resturant_Data = "Resturant";


    //Meals table columns
    private static final String KEY_ID = "id";
    private static final String KEY_MealName = "MealName";
    private static final String KEY_MealDescription = "MealDescription";
    private static final String KEY_MealPrice = "MealPrice";
    private static final String KEY_IMG = "image";
    private static final String TABLE_Meal_Data = "MealsData";
    private static final String Res_name = "Res_name";

    //orders column
    private static final String ordersTable = "orders";
    private static final String orderMealName = "mealName";
    private static final String Resturant_name = "Res_name";
    private static final String orderEmail = "orderEmail" ;
    private static final String orderImg = "orderImg" ;
    private static final String orderMealPrice = "orderPrice" ;


    public ResturantDb(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //intializing resturants
        String Resturant_table = "create table " + TABLE_Resturant_Data + "("
                + KEY_ResturantName + " varchar(255) Primary key,"
                + ResKEY_IMG + " blob)";

        //intializing of meals
        String Meals_table = "create table " + TABLE_Meal_Data + "(" + KEY_ID + " integer primary key autoincrement,"
                + KEY_MealName + " varchar(255) DEFAULT'',"
                + KEY_MealDescription + " varchar(255) DEFAULT'',"
                + KEY_MealPrice + " integer ,"
                + KEY_IMG + " blob,"+Res_name +" varchar(250))";

        //intializing the orders
        String orders_table = "create table " + ordersTable + "(" + orderEmail +" varchar(255) ,"
                +Resturant_name+" varchar(255),"+orderMealName+" varchar(255),"+orderMealPrice+" varchar(255),"+orderImg+" blob)";

        db.execSQL(Resturant_table);
        db.execSQL(Meals_table);
        db.execSQL(orders_table);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        String delete_query = "DROP table if exists " + TABLE_Resturant_Data;
        String delete_query2 = "DROP table if exists " + TABLE_Meal_Data;
        String delete_query3 = "DROP table if exists " + ordersTable;
        db.execSQL(delete_query);
        db.execSQL(delete_query2);
        db.execSQL(delete_query3);
        onCreate(db);
    }

    // get the resturants in the recycler view
    public ArrayList<Resturant> getAllResturants() {
        ArrayList<Resturant> Resturants = new ArrayList<>();

        String select_query = "select * from " + TABLE_Resturant_Data;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(select_query, null);

        if (cursor.moveToFirst()) {
            do {
                String resturantName = cursor.getString(cursor.getColumnIndex(KEY_ResturantName));
                byte[] image = cursor.getBlob(cursor.getColumnIndex(ResKEY_IMG));

                Resturant resturant = new Resturant(image,resturantName);

                Resturants.add(resturant);

            } while (cursor.moveToNext());

        }

        return Resturants;
    }
    // to add meal to the resturant
    public void addMeal(Meal meal) {

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_MealName, meal.getMealName());
        values.put(KEY_MealDescription, meal.getMealDescription());
        values.put(KEY_MealPrice, meal.getMealPrice());
        values.put(KEY_IMG, meal.getImage());
        values.put(Res_name, meal.getRestaurantName());

        db.insert(TABLE_Meal_Data, null, values);
    }

    // to add a new resturant
    public void addResturant(Resturant resturant) {

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_ResturantName, resturant.getName());
        values.put(ResKEY_IMG , resturant.getImageResource());

        db.insert( TABLE_Resturant_Data , null, values);
    }

    public ArrayList<Meal> getAllMeals(String resturantName) {
        ArrayList<Meal> meals = new ArrayList<>();

        String select_query = "select * from " + TABLE_Meal_Data;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(select_query, null);

        if (cursor.moveToFirst()) {
            do {
                if(resturantName.equals(cursor.getString(cursor.getColumnIndex(Res_name)))) {
                    int id = cursor.getInt(cursor.getColumnIndex(KEY_ID));
                    String MealName = cursor.getString(cursor.getColumnIndex(KEY_MealName));
                    String MealDescription = cursor.getString(cursor.getColumnIndex(KEY_MealDescription));
                    int MealPrice = cursor.getInt(cursor.getColumnIndex(KEY_MealPrice));
                    byte[] image = cursor.getBlob(cursor.getColumnIndex(KEY_IMG));

                    Meal meal = new Meal(id, MealName, MealDescription, MealPrice, image);

                    meals.add(meal);
                }

            } while (cursor.moveToNext());

        }

        return meals;
    }

    public Meal getMealById2(int id) {

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

    public void updateMeal(Meal meal) {

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_MealName, meal.getMealName());
        values.put(KEY_MealDescription, meal.getMealDescription());
        values.put(KEY_MealPrice, meal.getMealPrice());
        values.put(KEY_IMG, meal.getImage());

        db.update(TABLE_Meal_Data, values, "id=?", new String[]{String.valueOf(meal.getId())});

    }


    public void deletMeal(int id) {
        SQLiteDatabase db = this.getWritableDatabase();

        db.delete(TABLE_Meal_Data, "id=?", new String[]{String.valueOf(id)});

    }



    /*
    Orders section
     */

    // to add meal to the resturant
    public void addOrder(Order order) {

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(orderMealName , order.getMealName());
        values.put(orderEmail , order.getEmail());
        values.put(orderImg , order.getImage());
        values.put(Resturant_name , order.getRestaurantName());
        values.put(orderMealPrice , order.getMealPrice());
        db.insert(ordersTable, null, values);
    }

    // to get all the orders from the data base
    public ArrayList<Order> getAllCustomerOrders(String Email) {
        ArrayList<Order> orders = new ArrayList<>();

        String select_query = "select * from " + ordersTable;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(select_query, null);

        if (cursor.moveToFirst()) {
            do {
                if(Email.equals(cursor.getString(cursor.getColumnIndex(orderEmail)))) {
                    String email = cursor.getString(cursor.getColumnIndex(orderEmail));
                    String orderName = cursor.getString(cursor.getColumnIndex(orderMealName));
                    int orderPrice = cursor.getInt(cursor.getColumnIndex(orderMealPrice));
                    byte[] image = cursor.getBlob(cursor.getColumnIndex(orderImg));
                    String resturantName = cursor.getString(cursor.getColumnIndex(Resturant_name));

                    Order order = new Order(email , orderName , orderPrice , image , resturantName);

                    orders.add(order);
                }
            } while (cursor.moveToNext());

        }
        return orders;
    }

    //to get all the orders of the resturant
    public ArrayList<Order> getAllRestaurantOrders(String restaurantName) {
        ArrayList<Order> orders = new ArrayList<>();

        String select_query = "select * from " + ordersTable;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(select_query, null);

        if (cursor.moveToFirst()) {
            do {
                if(restaurantName.equals(cursor.getString(cursor.getColumnIndex(Resturant_name)))) {
                    String email = cursor.getString(cursor.getColumnIndex(orderEmail));
                    String orderName = cursor.getString(cursor.getColumnIndex(orderMealName));
                    int orderPrice = cursor.getInt(cursor.getColumnIndex(orderMealPrice));
                    byte[] image = cursor.getBlob(cursor.getColumnIndex(orderImg));
                    String resturantName = cursor.getString(cursor.getColumnIndex(Resturant_name));

                    Order order = new Order(email , orderName , orderPrice , image , resturantName);

                    orders.add(order);
                }
            } while (cursor.moveToNext());

        }
        return orders;
    }


}
