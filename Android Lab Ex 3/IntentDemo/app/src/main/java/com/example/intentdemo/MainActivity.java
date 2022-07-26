package com.example.intentdemo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    private EditText phNo;
    private Button call, openGallery;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        phNo=findViewById(R.id.editTextPhone);
        call=findViewById(R.id.button);
        openGallery=findViewById(R.id.button2);
        call.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Uri u = Uri.parse("tel:" + phNo.getText().toString());
                Intent i = new Intent(Intent.ACTION_DIAL, u);
                try
                {
                    startActivity(i);
                }
                catch (Exception e)
                {
                }
            }
        });
        openGallery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                try
                {
                    startActivity(i);
                }
                catch (Exception e)
                {
                }
            }
        });

    }
}