package com.example.vikslibrary;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class UserAdditionActivity extends AppCompatActivity {
    private EditText userName,userPhn;

    private Button add,clear;
    private DBHandler dbHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_useraddition);
        userName=findViewById(R.id.userName);
        userPhn=findViewById(R.id.userPhoneNo);
        add=findViewById(R.id.addUserBtn);

        dbHandler = new DBHandler(UserAdditionActivity.this);

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name=userName.getText().toString();
                String phn=userPhn.getText().toString();

                if(name!="" && phn!=""){
                    dbHandler.addNewUser(name,phn);
                    userName.setText("");
                    userPhn.setText("");
                    Toast.makeText(UserAdditionActivity.this,"New User Added Successfully!",Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(UserAdditionActivity.this,"Please fill all the fields!",Toast.LENGTH_SHORT).show();
                }
            }
        });


    }



}


