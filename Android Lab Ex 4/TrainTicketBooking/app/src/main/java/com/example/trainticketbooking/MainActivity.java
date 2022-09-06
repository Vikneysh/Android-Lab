package com.example.trainticketbooking;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.DatePicker;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;


public class MainActivity extends AppCompatActivity {
    private EditText datePicker,tickets;
    private Button checkAvlBtn,buttonDate;
    FloatingActionButton plus,minus;
    private Spinner from,to;
    private int lastSelectedYear;
    private int lastSelectedMonth;
    private int lastSelectedDayOfMonth;
    private int count=0;
    String[] places={"Select","TEN-Tirunelveli","VNR-Virudhunagar","MDU-Madurai","CHN-Chennai"};
    ArrayList<String> fromList= new ArrayList<>(Arrays.asList(places));
    ArrayList<String> toList= new ArrayList<>(Arrays.asList(places));
    ArrayAdapter<String> adapter,adapter1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        checkAvlBtn=findViewById(R.id.button);
        datePicker=findViewById(R.id.editTextDate);
        tickets=findViewById(R.id.editTextNumber);
        plus=findViewById(R.id.floatingActionButton);
        minus=findViewById(R.id.floatingActionButton3);
        from=findViewById(R.id.spinner);
        to=findViewById(R.id.spinner2);

        buttonDate=findViewById(R.id.button2);
        final Calendar c = Calendar.getInstance();
        this.lastSelectedYear = c.get(Calendar.YEAR);
        this.lastSelectedMonth = c.get(Calendar.MONTH);
        this.lastSelectedDayOfMonth = c.get(Calendar.DAY_OF_MONTH);
        adapter=new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,fromList);
        from.setAdapter(adapter);
        from.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String option = adapterView.getSelectedItem().toString();
                if(option!="Select"){
                    toList=new ArrayList<>(Arrays.asList(places));
                    toList.remove(option);
                    adapter1=new ArrayAdapter<String>(MainActivity.this,android.R.layout.simple_spinner_item,toList);
                    to.setAdapter(adapter1);
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        this.buttonDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year,
                                          int monthOfYear, int dayOfMonth) {

                        datePicker.setText(dayOfMonth + "-" + (monthOfYear + 1) + "-" + year);

                        lastSelectedYear = year;
                        lastSelectedMonth = monthOfYear;
                        lastSelectedDayOfMonth = dayOfMonth;
                    }
                };
                DatePickerDialog datePickerDialog = null;
                datePickerDialog = new DatePickerDialog(MainActivity.this,
                        android.R.style.Theme_Holo_Light_Dialog_NoActionBar,
                        dateSetListener,
                        lastSelectedYear,
                        lastSelectedMonth,
                        lastSelectedDayOfMonth);

                datePickerDialog.show();
            }
        });
        plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                count=count+1;
                tickets.setText(String.valueOf(count));
            }
        });
        minus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                count=count-1;
                tickets.setText(String.valueOf(count));
            }
        });
        checkAvlBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(from.getSelectedItem()=="Select" || to.getSelectedItem()=="Select" || to.getSelectedItem()==null || datePicker.getText().toString().length()==0){
                    Toast.makeText(MainActivity.this,"Please fill all fields"  ,Toast.LENGTH_SHORT).show();
                }
                else{
                    Intent i = new Intent(getApplicationContext(), SecondActivity.class);
                    i.putExtra("from",from.getSelectedItem().toString());
                    i.putExtra("to",to.getSelectedItem().toString());
                    i.putExtra("date",datePicker.getText().toString());
                    i.putExtra("tickets",tickets.getText().toString());
                    startActivity(i);

                }
            }
        });
    }
}