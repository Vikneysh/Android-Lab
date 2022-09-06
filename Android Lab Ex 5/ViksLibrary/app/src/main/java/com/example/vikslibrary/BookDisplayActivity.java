package com.example.vikslibrary;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class BookDisplayActivity extends AppCompatActivity {
    private Button allBooks,lendedBooks,availableBooks;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_books);
        allBooks=findViewById(R.id.button9);
        lendedBooks=findViewById(R.id.button8);
        availableBooks=findViewById(R.id.button7);
        allBooks.setText("All");
        lendedBooks.setText("Lended");
        availableBooks.setText("In Lib");

        allBooks.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), AllBooksDisplayActivity.class);
                i.putExtra("displayBooks","all");
                startActivity(i);
            }
        });

        lendedBooks.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), AllBooksDisplayActivity.class);
                i.putExtra("displayBooks","lended");
                startActivity(i);
            }
        });

        availableBooks.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), AllBooksDisplayActivity.class);
                i.putExtra("displayBooks","available");
                startActivity(i);
            }
        });
    }
}