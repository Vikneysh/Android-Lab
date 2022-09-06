package com.example.vikslibrary;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class BookAdditionActivity extends AppCompatActivity {
    private EditText bookName,authorName;
    private Spinner location;
    private Button add,clear;
    private String[] locations={"Select location","A01","A02","A03","B01","B02"};
    private DBHandler dbHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bookaddition);
        bookName=findViewById(R.id.editTextTextPersonName2);
        authorName=findViewById(R.id.editTextTextPersonName3);
        location=findViewById(R.id.spinner);
        add=findViewById(R.id.button2);
        clear=findViewById(R.id.button3);
        ArrayAdapter aa = new ArrayAdapter(this,android.R.layout.simple_spinner_item,locations);
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        location.setAdapter(aa);
        dbHandler = new DBHandler(BookAdditionActivity.this);

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String book=bookName.getText().toString();
                String author=authorName.getText().toString();
                String loc=location.getSelectedItem().toString();
                if(book!="" && author!="" && loc!="Select location"){
                    dbHandler.addNewBook(book,author,loc);
                    bookName.setText("");
                    authorName.setText("");
                    location.setSelection(0);
                    Toast.makeText(BookAdditionActivity.this,"New Book Added Successfully!",Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(BookAdditionActivity.this,"Please fill all the fields!",Toast.LENGTH_SHORT).show();
                }
            }
        });
        

    }



}


