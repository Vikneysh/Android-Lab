package com.example.vikslibrary;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {
    private Button button;
    private Switch mode;
    private EditText email,passwd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button=findViewById(R.id.button);
        mode=findViewById(R.id.switch1);
        email=findViewById(R.id.editTextTextPersonName);
        passwd=findViewById(R.id.editTextTextPassword);
        mode.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(b) {
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                }
                else{
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                }
            }
        });
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(email.getText().toString().equals("admin") && passwd.getText().toString().equals("viks")){
                    Intent i = new Intent(getApplicationContext(), ChoiceActivity.class);
                    startActivity(i);
                }
                else{
                    Toast.makeText(MainActivity.this,"Invalid Credentials!",Toast.LENGTH_LONG).show();
                }
            }
        });

    }

}