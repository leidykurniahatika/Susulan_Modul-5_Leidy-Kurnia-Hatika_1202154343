package com.tugas.leidy.leidykurniahatika_1202154343_modul5;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String Table = "ToDoList";
    private static final String kolom1 = "Id";
    private static final String kolom2 = "Name";
    private static final String kolom3 = "Description";
    private static final String kolom4 = "Priority";

    public DatabaseHelper(Context context) {
        super(context, Table, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createSql = "CREATE TABLE " + Table + "(" + kolom1 + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                kolom2 + " TEXT," +
                kolom3 + " TEXT," +
                kolom4 + " TEXT)";
        db.execSQL(createSql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS " + Table);
        onCreate(db);
    }

    public boolean addData(String name, String description, String priority) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues in = new ContentValues();
        in.put(kolom2, name);
        in.put(kolom3, description);
        in.put(kolom4, priority);
        long result = db.insert(Table, null, in);
        if (result == -1) {
            return false;
        } else {
            return true;
        }
    }

    public Cursor getData() {
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT * FROM " + Table;
        Cursor data = db.rawQuery(query, null);
        return data;
    }

    public void deleteData(int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        String q = "DELETE FROM " + Table + " WHERE " + kolom1 + " = '" + id + "'";
        db.execSQL(q);

    }
}