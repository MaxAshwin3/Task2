package com.example.task2.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DbHelper extends SQLiteOpenHelper {
     private static final String name= "userData";
     private static final  int version = 1;

    public DbHelper(Context context) {
        super(context, name, null, version);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
         String sql = "CREATE TABLE users( _id INTEGER PRIMARY KEY AUTOINCREMENT , name TEXT NOT NULL , number INTEGER NOT NULL, email TEXT NOT NULL, password INTEGER NOT NULL )";

        sqLiteDatabase.execSQL(sql);




    }

    public Boolean insertData(String name , String number , String email , String password  )
    {
        SQLiteDatabase database = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("name" ,name);
        contentValues.put("number",number);
        contentValues.put("email",email);
        contentValues.put("password" , password);

       long result = database.insert("users",null,contentValues);

        if(result == -1)
            return false;
        else
            return true;
    }
    public Boolean checkUser(String username  , SQLiteDatabase database)
    {
        database = this.getWritableDatabase();
        Cursor cursor = database.rawQuery("SELECT * FROM users WHERE name =?" ,new String[]{username});

        if(cursor.getCount() > 0)
        {
            return  true;
        }
        else
        {
            return false;
        }

    }
    public Boolean CheckUserNamePassword(String username , String password )
    {
       SQLiteDatabase  database = this.getWritableDatabase();
         Cursor cursor = database.rawQuery("SELECT * FROM users WHERE name = ? AND password = ?",new String[]{username,password});
         if(cursor.getCount() > 0)
         {
             return true;
         }
         else
         {
             return false;
         }

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

         sqLiteDatabase.execSQL("DROP TABLE IF EXISTS users");


    }
}
