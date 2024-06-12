package foly.anhld.assmfull.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

public class FoodDataSource {
    private SQLiteDatabase database;
    private FoodDatabaseHelper dbHelper;

    public FoodDataSource(Context context) {
        dbHelper = new FoodDatabaseHelper(context);
    }

    public void open() {
        database = dbHelper.getWritableDatabase();
    }

    public void close() {
        dbHelper.close();
    }

    public void addFood(String name) {
        ContentValues values = new ContentValues();
        values.put("name", name);
        database.insert("foods", null, values);
    }

    public List<String> getAllFoods() {
        List<String> foods = new ArrayList<>();
        Cursor cursor = database.query("foods", new String[]{"name"}, null, null, null, null, null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            foods.add(cursor.getString(0));
            cursor.moveToNext();
        }
        cursor.close();
        return foods;
    }

    public void clearAllFoods() {
        database.delete("foods", null, null);
    }
}

