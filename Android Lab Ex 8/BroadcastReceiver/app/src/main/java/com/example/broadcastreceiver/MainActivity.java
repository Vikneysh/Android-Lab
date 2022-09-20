package com.example.broadcastreceiver;

import androidx.appcompat.app.AppCompatActivity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    ConnectivityChangeReceiver c=new ConnectivityChangeReceiver();
    private Button b;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        broadcastIntent();
        b=findViewById(R.id.button);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                registerReceiver(mMessageReceiver,new IntentFilter("com.example.broadcastreceiver.CUSTOM_INTENT"));
                Intent intent=new Intent("com.example.broadcastreceiver.CUSTOM_INTENT");
                sendBroadcast(intent);
            }
        });
    }

    private BroadcastReceiver mMessageReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            // Extract data included in the Intent
            String message = intent.getAction();
            Toast.makeText(MainActivity.this,message,Toast.LENGTH_LONG).show();

        }
    };
    public void broadcastIntent() {
        registerReceiver(c, new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION));

    }
    @Override
    protected void onPause() {
        super.onPause();
        unregisterReceiver(c);
    }
}