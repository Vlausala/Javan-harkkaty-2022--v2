package com.example.java_harkkatyo;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import org.w3c.dom.Comment;

import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class DBHelper extends SQLiteOpenHelper {

    public DBHelper( Context context) {
        super(context, "Login.db",null,1); //TODO Valtteri:  tutki voiko muuttaa singleton periaatteella toimvaksi
    }


    @Override
    public void onCreate(SQLiteDatabase myDB) {
        myDB.execSQL("CREATE TABLE users(INTEGER PRIMARY KEY, username Text, password Text)");
        myDB.execSQL("CREATE TABLE comments(INTEGER PRIMARY KEY,username Text, lakename Text, comment Text)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase myDB, int oldVersion, int newVersion) {
        myDB.execSQL("DROP TABLE IF EXISTS users");
        myDB.execSQL("DROP TABLE IF EXISTS comments");
    }


    public Boolean insertUser(String username, String password){
        SQLiteDatabase myDB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("username", username);
        contentValues.put("password",password);
        long result = myDB.insert("users",null,contentValues);

        if (result == -1 ){
            return false;
        }
        else {
            return true;
        }

    }

    public Boolean checkUsername(String username){
        SQLiteDatabase myDB = this.getWritableDatabase();
        Cursor cursor = myDB.rawQuery(
                "select * from users where username = ?", new String[] {username});
        if(cursor.getCount() > 0 ){
            cursor.close();
            return true;
        } else{
            cursor.close();
            return false;
        }
    }

    public Boolean checkUsernameAndPassword(String username, String password){
        SQLiteDatabase myDB = this.getWritableDatabase();
        Cursor cursor = myDB.rawQuery(
                "select * from users where username = ? and password = ?",
                new String[] {username,password}
        );
        if(cursor.getCount() > 0 ){
            cursor.close();
            return true;
        } else{
            cursor.close();
            return false;
        }
    }



    public Boolean InsertComment(String lakename, String comment, String username){
        SQLiteDatabase myDB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("username", username);
        contentValues.put("lakename",lakename);
        contentValues.put("comment", comment);
        long result = myDB.insert("comments", null, contentValues);
        if (result == -1 ){
            return false;
        }
        else {
            return true;
        }

    }

    public ArrayList<UserComment> QueryComments(String username){
        ArrayList<UserComment> comments = new ArrayList<>();
        SQLiteDatabase myDB = this.getWritableDatabase();
        Cursor cursor = myDB.rawQuery(
                "select * from comments where username = ?",
                new String[] {username}
        );
        if (cursor.moveToFirst()){
            while(!cursor.isAfterLast()){

                String name = cursor.getString( 1);
                String lakename = cursor.getString(2);
                String comment = cursor.getString(3);

                UserComment result = new UserComment(name, lakename, comment);
                comments.add(result);

                cursor.moveToNext();
            }
        }
        cursor.close();

        return comments;
    }


}
