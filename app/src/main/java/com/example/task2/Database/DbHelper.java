package com.example.task2.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.task2.Model.ModelClass;

import java.util.ArrayList;

public class DbHelper extends SQLiteOpenHelper {
     private static final String name= "userData";
     private static final  int version = 1;

    public DbHelper(Context context) {
        super(context, name, null, version);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
         String sql = "CREATE TABLE users( _id INTEGER PRIMARY KEY AUTOINCREMENT , fname TEXT NOT NULL,lname TEXT NOT NULL , number INTEGER NOT NULL, email TEXT NOT NULL, password INTEGER NOT NULL , gender TEXT )";

        sqLiteDatabase.execSQL(sql);




    }

    public Boolean insertData(String fname ,String lname , String number , String email , String password , String gender )
    {
        SQLiteDatabase database = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("fname" ,fname);
        contentValues.put("lname" ,lname);
        contentValues.put("number",number);
        contentValues.put("email",email);
        contentValues.put("password" , password);
        contentValues.put("gender",gender);

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
    public Boolean CheckEmailPassword(String email , String password )
    {
       SQLiteDatabase  database = this.getWritableDatabase();
         Cursor cursor = database.rawQuery("SELECT * FROM users WHERE email = ? AND password = ?",new String[]{email,password});
         if(cursor.getCount() > 0)
         {
             return true;
         }
         else
         {
             return false;
         }

    }
    public ArrayList<ModelClass> getUserData(String gender){
        SQLiteDatabase myDB = this.getReadableDatabase();
        ArrayList<ModelClass> modelClassArrayList = new ArrayList<>();

        Cursor cursor = myDB.rawQuery("select * from users where gender like '" + gender + "'", null);
        if(cursor.getCount() != 0){
            cursor.moveToPosition(-1);

            while (cursor.moveToNext()){
                String user_fName = cursor.getString(0);
                String user_lName = cursor.getString(1);
                String user_gender = cursor.getString(2);
                String user_email = cursor.getString(3);

                modelClassArrayList.add(new ModelClass(user_fName, user_lName, user_email));
            }
            return modelClassArrayList;
        }
        else {
            return null;
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

         sqLiteDatabase.execSQL("DROP TABLE IF EXISTS users");


    }
}
