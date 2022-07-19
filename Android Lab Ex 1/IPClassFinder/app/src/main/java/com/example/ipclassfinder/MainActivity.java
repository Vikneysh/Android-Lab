package com.example.ipclassfinder;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;


import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity {
    private Button findBtn,clearBtn;
    private EditText ip;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findBtn=findViewById(R.id.button);
        clearBtn=findViewById(R.id.button3);
        ip=findViewById(R.id.editTextTextPersonName2);
        findBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String IP = ip.getText().toString();
                String zeroTo255="(\\d{1,2}|(0|1)\\" + "d{2}|2[0-4]\\d|25[0-5])";
                boolean isValidIP=false;
                String regex = zeroTo255 + "\\."  + zeroTo255 + "\\." + zeroTo255 + "\\." + zeroTo255;
                Pattern p = Pattern.compile(regex);
                Matcher m = p.matcher(IP);
                isValidIP=m.matches();
                if(isValidIP==false){
                    Toast.makeText(getApplicationContext(),"Enter a valid ip!", Toast.LENGTH_LONG).show();
                }
                if(isValidIP) {
                    String ipsub = IP.substring(0, IP.indexOf('.'));
                    int ipAddr = Integer.parseInt(ipsub);
                    String IPClass = "";
                    if (ipAddr >= 1 && ipAddr <= 126)
                        IPClass = " Class A";
                    else if (ipAddr >= 128 && ipAddr <= 191)
                        IPClass = " Class B";
                    else if (ipAddr >= 192 && ipAddr < 223)
                        IPClass = " Class C";
                    else if (ipAddr >= 224 && ipAddr <= 239)
                        IPClass = " Class D";
                    else
                        IPClass = " Class E";
                    AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                    builder.setMessage("IP Address: " + IP + "\nClass: " + IPClass);
                    builder.setTitle("IP Address Class");
                    builder.setCancelable(false);
                    builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.cancel();
                        }
                    });
                    AlertDialog alertDialog = builder.create();
                    alertDialog.show();
                }
            }
        });
        clearBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(),"Data reset successfully!", Toast.LENGTH_SHORT).show();
                ip.setText("");
            }
        });
    }
}