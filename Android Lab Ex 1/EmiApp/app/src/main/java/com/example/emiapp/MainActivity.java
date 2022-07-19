package com.example.emiapp;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;


import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private Button calcBtn,resetBtn;
    private EditText p,n;
    private TextView msg;
    private SeekBar r;
    private Spinner loanType;
    String[] loans={"Select","Housing Loan","Car Loan","Personal Loan"};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        calcBtn=findViewById(R.id.button);
        resetBtn=findViewById(R.id.button2);
        loanType=findViewById(R.id.spinner2);
        p=findViewById(R.id.editTextNumber2);
        n=findViewById(R.id.editTextNumber);
        r=findViewById(R.id.seekBar2);
        msg=findViewById(R.id.textView5);

        ArrayAdapter aa = new ArrayAdapter(this,android.R.layout.simple_spinner_item,loans);
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        loanType.setAdapter(aa);
        loanType.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String option = parent.getSelectedItem().toString();
                if(option.equals("Select")){
                    r.setProgress(0);
                }
                if(option.equals("Housing Loan")){
                    r.setProgress(3);
                }
                if(option.equals("Car Loan")){
                    r.setProgress(4);
                }
                if(option.equals("Personal Loan")){
                    r.setProgress(4);
                }
                Toast.makeText(getApplicationContext(),loans[position] , Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
        r.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener(){
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser){
                float f=((float)progress+10)/2;
                Toast.makeText(getApplicationContext(),"Rate of Interest: " + f ,Toast.LENGTH_SHORT).show();
            }
            @Override
            public void onStartTrackingTouch(SeekBar seekBar){
            }
            @Override
            public void onStopTrackingTouch(SeekBar seekBar){
            }
        }
        );
        calcBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
               float principal = new Float(p.getText().toString());
               float months = new Float(n.getText().toString());
               float rateOfInterest=new Float(r.getProgress());
               rateOfInterest=(rateOfInterest/12)/100;
               float EMI= (float) ((principal*rateOfInterest)*Math.pow(1+rateOfInterest,months)/(Math.pow(1+rateOfInterest,months)-1));
               float amtPayable=EMI*months;
               float interestPayable=amtPayable-principal;
               AlertDialog.Builder builder=new AlertDialog.Builder(MainActivity.this);
               builder.setMessage("EMI: "+EMI+"\nAmount Payable: "+amtPayable+"\nInterest Payable: "+interestPayable);
               builder.setTitle("EMI Details");
               builder.setCancelable(false);
               builder.setPositiveButton("OK",new DialogInterface.OnClickListener() {
                   @Override
                   public void onClick(DialogInterface dialog,int which)
                   {
                       dialog.cancel();
                   }
               });
               AlertDialog alertDialog = builder.create();
               alertDialog.show();
               msg.setText("EMI: "+EMI);
            }
        });
        resetBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(),"Data reset successfully!", Toast.LENGTH_SHORT).show();
                p.setText("");
                n.setText("");
                r.setProgress(0);
                loanType.setSelection(0);
            }
        });
    }
}
