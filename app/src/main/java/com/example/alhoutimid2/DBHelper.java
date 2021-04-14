package com.example.alhoutimid2;



import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;


public class DBHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME="People.db";
    public static  final String TABLE_NAME="Person";
    public static final String COLUMN_SSN="SSN";
    public static final String COLUMN_NAME="Name";
    public static final String COLUMN_EMAIL="Email";
    public static final String COLUMN_PHONE="Phone";
    public static final String COLUMN_STUDENT_ID="Student_id";

    SQLiteDatabase db;
    public DBHelper(@Nullable Context context ) {
        super(context, DATABASE_NAME, null, 1);
        db=getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(
                "CREATE TABLE IF NOT EXISTS " + TABLE_NAME
                        + "("+ COLUMN_SSN + " INTEGER PRIMARY KEY ,"
                        + COLUMN_NAME + " TEXT NOT NULL,"
                        + COLUMN_EMAIL +" TEXT NOT NULL,"
                        + COLUMN_PHONE + " TEXT NOT NULL, "
                        + COLUMN_STUDENT_ID + " TEXT NOT NULL)");
    }

    public void insert(String ssn,String name, String surname,String phone,String student_id){
        ContentValues values = new ContentValues();

        values.put(COLUMN_SSN, ssn);
        values.put(COLUMN_NAME, name);
        values.put(COLUMN_EMAIL, surname);
        values.put(COLUMN_PHONE,phone);
        values.put(COLUMN_STUDENT_ID,student_id);
        db.insert(TABLE_NAME, null, values);

    }

    public void delete(String personal_id){
        String [] args={personal_id};
        db.delete(TABLE_NAME, COLUMN_SSN+"= ?", args);
    }



    public Cursor getResult(String name){
        Cursor data = db.rawQuery("SELECT * FROM " + TABLE_NAME+" Where Name="+name,null);
        if (data.moveToNext()){
            return data;
        }else{
            return null;
        }
    }



    /* Every time the dB is updated (or upgraded) */
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public void deleteFirstRow(){
        db.execSQL("Delete from "+TABLE_NAME+" where ID IN (Select ID from "+TABLE_NAME+" limit 1)");

    }
    public Cursor getFirstRow(){
        Cursor c=db.rawQuery("SELECT * FROM "+TABLE_NAME+" LIMIT 1",null);
        if (c.moveToNext()){
            return c;
        }else{
            return null;
        }
    }
}
