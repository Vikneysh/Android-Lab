package com.example.vikslibrary;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class BookLendingActivity extends AppCompatActivity {
    private Spinner book,user;
    private Button lendBtn;
    private DBHandler dbHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booklending);
        dbHandler = new DBHandler(BookLendingActivity.this);
        book=findViewById(R.id.bookToLend);
        user=findViewById(R.id.userToLendTo);
        lendBtn=findViewById(R.id.lendButton);
        ArrayList books=dbHandler.getAvailableBooks();
        ArrayList users=dbHandler.getAllUsers();
        ArrayAdapter bookadapter=new ArrayAdapter(this,android.R.layout.simple_list_item_1,books);
        ArrayAdapter useradapter=new ArrayAdapter(this,android.R.layout.simple_list_item_1,users);
        book.setAdapter(bookadapter);
        user.setAdapter(useradapter);

        lendBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String bookID=book.getSelectedItem().toString().substring(0,1);
                String userID=user.getSelectedItem().toString().substring(0,1);
                dbHandler.lendBook(userID,bookID);
                Toast.makeText(BookLendingActivity.this,"Book lended to User",Toast.LENGTH_SHORT).show();
            }
        });


    }
}
