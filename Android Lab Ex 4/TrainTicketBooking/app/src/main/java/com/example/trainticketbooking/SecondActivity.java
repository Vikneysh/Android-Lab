package com.example.trainticketbooking;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class SecondActivity extends AppCompatActivity {
    private String from,to,date,tickets;
    private TextView fromView,toView,dateView,ticketView;
    private Button backBtn;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        fromView=findViewById(R.id.textView12);
        toView=findViewById(R.id.textView13);
        dateView=findViewById(R.id.textView14);
        ticketView=findViewById(R.id.textView16);
        backBtn=findViewById(R.id.back);
        Bundle extras=getIntent().getExtras();
        from=extras.getString("from");
        to=extras.getString("to");
        date=extras.getString("date");
        tickets=extras.getString("tickets");
        fromView.setText(from);
        toView.setText(to);
        dateView.setText(date);
        ticketView.setText(tickets);

        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(i);
            }
        });
    }
}
