package com.example.vaccineslotbooking;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity {
    String[] places={"Select","TEN-Tirunelveli","VNR-Virudhunagar","MDU-Madurai","CHN-Chennai"};
    ArrayList<String> areas= new ArrayList<>(Arrays.asList(places));
    private Spinner areaSpinner;
    private EditText name,phone,datePicker;
    private Button book,buttonDate;
    private int lastSelectedYear,lastSelectedMonth,lastSelectedDayOfMonth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        areaSpinner=findViewById(R.id.spinner);
        name=findViewById(R.id.editTextTextPersonName);
        phone=findViewById(R.id.editTextTextPersonName2);
        book=findViewById(R.id.button2);
        datePicker=findViewById(R.id.editTextDate);
        buttonDate=findViewById(R.id.button);
        ArrayAdapter aa = new ArrayAdapter(this,android.R.layout.simple_spinner_item,areas);
        areaSpinner.setAdapter(aa);
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
        book.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String number=phone.getText().toString();
                String msg="Dear "+name.getText().toString()+",\n\tYour vaccine slot has been booked for "+datePicker.getText().toString();
                try {
                    SmsManager smsManager=SmsManager.getDefault();
                    smsManager.sendTextMessage(phone.getText().toString(),null,msg,null,null);
                    Toast.makeText(getApplicationContext(),"Message Sent",Toast.LENGTH_LONG).show();
                }catch (Exception e)
                {
                    e.printStackTrace();
                }
            }
        });
    }
}