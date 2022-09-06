package com.example.vikslibrary;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class BooksActivity extends AppCompatActivity {
    private Button addBooks,lendBook,displayBooks;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_books);
        addBooks=findViewById(R.id.button7);
        lendBook=findViewById(R.id.button8);
        displayBooks=findViewById(R.id.button9);

        addBooks.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), BookAdditionActivity.class);
                startActivity(i);
            }
        });

        lendBook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), BookLendingActivity.class);
                startActivity(i);
            }
        });

        displayBooks.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), BookDisplayActivity.class);
                startActivity(i);
            }
        });
    }
}
