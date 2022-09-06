package com.example.vikslibrary;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class DBHandler extends SQLiteOpenHelper {


    private static final String DB_NAME = "library";
    private static final int DB_VERSION = 1;
    private static final String BOOK_TABLE = "books";
    private static final String ID_COL = "id";
    private static final String NAME_COL = "name";
    private static final String AUTHOR_COL = "author";
    private static final String LOCATION_COL = "location";
    private static final String AVAILABILITY_COL = "availability";

    private static final String USER_TABLE = "users";
    private static final String UID_COL = "uId";
    private static final String UNAME_COL = "uName";
    private static final String UPHONE_COL = "uPhoneNo";

    private static final String LENDED_TABLE = "lendedBooks";
    private static final String USER_COL = "uId";
    private static final String BOOK_COL = "bID";



    public DBHandler(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }
    ArrayList<String> booksList = new ArrayList<>();
    ArrayList<String> usersList = new ArrayList<>();
    ArrayList<String> lendedBooks = new ArrayList<>();
    ArrayList<String> availableBooks = new ArrayList<>();
    ArrayList<String> booksLentToUser = new ArrayList<>();


    @Override
    public void onCreate(SQLiteDatabase db) {

        String query = "CREATE TABLE IF NOT EXISTS " + BOOK_TABLE + " ("
                + ID_COL + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + NAME_COL + " TEXT,"
                + AUTHOR_COL + " TEXT,"
                + LOCATION_COL + " TEXT,"
                + AVAILABILITY_COL + " TEXT)";

        db.execSQL(query);
    }

    public void addNewBook(String bookName, String authorName, String location) {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(NAME_COL, bookName);
        values.put(AUTHOR_COL, authorName);
        values.put(LOCATION_COL, location);
        values.put(AVAILABILITY_COL, "Yes");

        db.insert(BOOK_TABLE, null, values);

        db.close();
    }

    public void addNewUser(String userName, String phnNo) {
        SQLiteDatabase db = this.getWritableDatabase();
        String query1 = "CREATE TABLE IF NOT EXISTS " + USER_TABLE + " ("
                + UID_COL + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + UNAME_COL + " TEXT,"
                + UPHONE_COL + " TEXT)";
        db.execSQL(query1);

        ContentValues values = new ContentValues();

        values.put(UNAME_COL, userName);
        values.put(UPHONE_COL, phnNo);

        db.insert(USER_TABLE, null, values);

        db.close();
    }


    public ArrayList getAllBooks(){
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursorBooks = db.rawQuery("SELECT * FROM " + BOOK_TABLE, null);
        if (cursorBooks.moveToFirst()) {
            do {
                String str=cursorBooks.getString(0)+". " + cursorBooks.getString(1)+" - "+cursorBooks.getString(2);
                booksList.add(str);
            } while (cursorBooks.moveToNext());

        }

        cursorBooks.close();
        return booksList;
    }

    public ArrayList getAvailableBooks(){
        SQLiteDatabase db = this.getReadableDatabase();
        String arg="\""+"Yes"+"\"";

        Cursor cursorBooks = db.rawQuery("SELECT * FROM " + BOOK_TABLE + " WHERE " + AVAILABILITY_COL + "=" + arg,null);
        if (cursorBooks.moveToFirst()) {
            do {
                String str=cursorBooks.getString(0)+". " + cursorBooks.getString(1)+" - "+cursorBooks.getString(2);
                availableBooks.add(str);
            } while (cursorBooks.moveToNext());

        }
        cursorBooks.close();
        return availableBooks;
    }

    public ArrayList getLendedBooks(){

        SQLiteDatabase db = this.getReadableDatabase();
        String arg="\""+"No"+"\"";

        Cursor cursorBooks = db.rawQuery("SELECT * FROM " + BOOK_TABLE + " WHERE " + AVAILABILITY_COL + "=" + arg,null);
        if (cursorBooks.moveToFirst()) {
            do {
                String str=cursorBooks.getString(0)+". " + cursorBooks.getString(1)+" - "+cursorBooks.getString(2);
                lendedBooks.add(str);
            } while (cursorBooks.moveToNext());

        }

        cursorBooks.close();
        return lendedBooks;
    }

    public ArrayList getAllUsers(){
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursorUsers = db.rawQuery("SELECT * FROM " + USER_TABLE, null);
        if (cursorUsers.moveToFirst()) {
            do {
                String str=cursorUsers.getString(0)+". "+cursorUsers.getString(1) + " Ph: "+cursorUsers.getString(2);
                usersList.add(str);
            } while (cursorUsers.moveToNext());

        }

        cursorUsers.close();
        return usersList;
    }

    public ArrayList getBooksLentToUser(String userId){
    SQLiteDatabase db = this.getReadableDatabase();
    System.out.println("VIKRAM");
        Cursor cursorBooksLent = db.rawQuery("SELECT books.id,books.name,users.uName FROM users,books,lendedBooks WHERE lendedBooks.uId=users.uId and lendedBooks.bID=books.id",  null);
        if (cursorBooksLent.moveToFirst()) {
            do {
                String str=cursorBooksLent.getString(0)+". "+cursorBooksLent.getString(1) + " Ph: "+cursorBooksLent.getString(2);
                booksLentToUser.add(str);
                System.out.println(cursorBooksLent.getString(0)+cursorBooksLent.getString(1)+cursorBooksLent.getString(2));
            } while (cursorBooksLent.moveToNext());

        }

        cursorBooksLent.close();
        return booksLentToUser;
    }

    public void deleteUser(String userId){
        SQLiteDatabase db = this.getWritableDatabase();
        String arg="\""+userId+"\"";
        String query = "DELETE FROM " + USER_TABLE + " WHERE " + USER_COL + "=" + arg;
        db.execSQL(query);
    }
    public void lendBook(String userId, String bookId){
        SQLiteDatabase db = this.getWritableDatabase();

        String query2 = "CREATE TABLE IF NOT EXISTS " + LENDED_TABLE + " ("
                + USER_COL + " INTEGER PRIMARY KEY, "
                + BOOK_COL + " TEXT)";

        db.execSQL(query2);

        String query3 = " UPDATE " + BOOK_TABLE + " SET availability='No'" + " WHERE id= " + bookId ;
        db.execSQL(query3);
        ContentValues values=new ContentValues();

        values.put(USER_COL, userId);
        values.put(BOOK_COL, bookId);

        db.insert(LENDED_TABLE,null,values);

    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS " + BOOK_TABLE);
        onCreate(db);
    }
}

