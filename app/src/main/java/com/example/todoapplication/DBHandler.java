package com.example.todoapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class DBHandler extends SQLiteOpenHelper {


    private static final String DB_NAME = "tododb";
    private static final int DB_VERSION = 1;
    private static final String TABLE_NAME = "mytododb";
    private static final String ID_COL = "id";
    private static final String DETAIL_COL = "detail";
    private static final String DATE_COL = "date";
    public DBHandler(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }












    @Override
    public void onCreate(SQLiteDatabase db) {

        String query = "CREATE TABLE " + TABLE_NAME + " ("
                + ID_COL + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + DETAIL_COL + " TEXT,"
                + DATE_COL + " INTEGER)";

        db.execSQL(query);

    }

    public void addNewTodo(String todoDetail, String todoDate) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(DETAIL_COL, todoDetail);
        values.put(DATE_COL, todoDate);


        db.insert(TABLE_NAME, null, values);
        db.close();
    }

    public ArrayList<TodoModal> readTodos() {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursorTodos = db.rawQuery("SELECT * FROM " + TABLE_NAME, null);

        ArrayList<TodoModal> todoModalArrayList = new ArrayList<>();

        if (cursorTodos.moveToFirst()) {
            do {
                todoModalArrayList.add(new TodoModal(cursorTodos.getString(1),
                        cursorTodos.getString(2)));
            } while (cursorTodos.moveToNext());
        }
        cursorTodos.close();
        return todoModalArrayList;
    }

    public void updateTodo(String originalDetail, String detail, String todoDate) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(DETAIL_COL, detail);
        values.put(DATE_COL, todoDate);


        db.update(TABLE_NAME, values, "detail=?", new String[]{originalDetail});
        db.close();
    }


    public void deleteTodo(String detail) {

        SQLiteDatabase db = this.getWritableDatabase();

        db.delete(TABLE_NAME, "detail=?", new String[]{detail});
        db.close();
    }





    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }




}

