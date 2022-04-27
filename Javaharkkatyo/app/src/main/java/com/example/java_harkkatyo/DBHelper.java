/*
 *Course: CT60A2411 Olio-ohjelmointi
 *Date: 29.4.2022
 *Group: Matti Lankinen, Valtteri Lausala, Jan-Peter Kauppinen
 */

package com.example.java_harkkatyo;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import java.util.ArrayList;

/**
 * Class for helping with database-related operations
 */
public class DBHelper extends SQLiteOpenHelper {

    /**
     * Constructor for the class
     * Login.db is the underlying database for the application
     * @param context The class in which the DBHelper-object is created
     */
    public DBHelper( Context context) {
        super(context, "Login.db",null,1);
    }

    /**
     * Creation-method which is run automatically the first time the database is created.
     *
     * onCreate adds users & comments tables to the database
     * @param myDB passes the DBHelper-instance for SQL-execution
     */
    @Override
    public void onCreate(SQLiteDatabase myDB) {
        //Creates users and comments tables
        myDB.execSQL("CREATE TABLE users(INTEGER PRIMARY KEY, username Text, password Text)");
        myDB.execSQL("CREATE TABLE comments(INTEGER PRIMARY KEY,username Text, lakename Text, comment Text)");
    }


    @Override
    public void onUpgrade(SQLiteDatabase myDB, int oldVersion, int newVersion) {
        myDB.execSQL("DROP TABLE IF EXISTS users");
        myDB.execSQL("DROP TABLE IF EXISTS comments");
    }

    /**
     * Method for creating a new user entry into the database
     * @param username
     * @param password
     * @return boolean based on the success of the operation
     */
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

    /**
     * Method for checking if user exists in the database
     * @param username
     * @return boolean based on the outcome of the operations
     */
    public Boolean checkUsername(String username){
        SQLiteDatabase myDB = this.getWritableDatabase();
        //SQL-query is done for the username
        Cursor cursor = myDB.rawQuery(
                "select * from users where username = ?", new String[] {username});
        //If query returned more than 0 results, username is contained in the database.
        if(cursor.getCount() > 0 ){
            cursor.close();
            return true;
        } else{
            cursor.close();
            return false;
        }
    }

    /**
     * Method for checking username and password
     * @param username
     * @param password
     * @return boolean based on if the username and password match existing ones
     */
    public Boolean checkUsernameAndPassword(String username, String password){
        SQLiteDatabase myDB = this.getWritableDatabase();
        //SQL-query for searching password&username pair
        Cursor cursor = myDB.rawQuery(
                "select * from users where username = ? and password = ?",
                new String[] {username,password}
        );
        //If more than 0- results are returned, password and username are found
        if(cursor.getCount() > 0 ){
            cursor.close();
            return true;
        } else{
            cursor.close();
            return false;
        }
    }


    /**
     * Method for inserting comments about lakes to the database
     * @param lakename
     * @param comment
     * @param username username is passed to make retrieving comments by user possible
     * @return boolean based on success of the operation
     */

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

    /**
     * Method querying comments inserted by the user from the database
     * @param username
     * @return Arraylist<UserComment> Arraylist containing comments
     */
    public ArrayList<UserComment> QueryComments(String username){
        ArrayList<UserComment> comments = new ArrayList<>();
        SQLiteDatabase myDB = this.getWritableDatabase();
        //SQL-query to get user specific comments
        Cursor cursor = myDB.rawQuery(
                "select * from comments where username = ?",
                new String[] {username}
        );
        //Checks if query contains anything
        if (cursor.moveToFirst()){
            while(!cursor.isAfterLast()){

                //Saves the columns to string values
                String name = cursor.getString( 1);
                String lakename = cursor.getString(2);
                String comment = cursor.getString(3);

                //Creates comment object
                UserComment result = new UserComment(name, lakename, comment);
                //Adds the object to arraylist
                comments.add(result);

                cursor.moveToNext();
            }
        }
        cursor.close();

        return comments;
    }


}
