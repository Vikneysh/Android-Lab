package com.example.vikslibrary;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class AllBooksDisplayActivity extends AppCompatActivity {
    private ListView bookList;
    private DBHandler dbHandler;
    private ArrayList books;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display);
        Intent intent = getIntent();
        dbHandler = new DBHandler(AllBooksDisplayActivity.this);
        bookList=findViewById(R.id.listView);
        String str = intent.getStringExtra("displayBooks");
        if(str.equals("lended")){
            books=dbHandler.getLendedBooks();
            System.out.println("lended books");
        }
        if(str.equals("all")){
            books=dbHandler.getAllBooks();
        }

        if(str.equals("available")){
            System.out.println("Available books");
            books=dbHandler.getAvailableBooks();
        }

        ArrayAdapter adapter=new ArrayAdapter(this,android.R.layout.simple_list_item_1,books);
        bookList.setAdapter(adapter);

    }
}
