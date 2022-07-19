package com.example.fuelexpensecalculator;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity {
    private Button findCostBtn;
    private Spinner vehicleType,vehicleModel;
    private EditText distance;
    private TextView mileage;
    String[] vehicles={"Select Vehicle","Car","Bike"};
    String[] cars={"Select Car","Maruti Swift", "Hyundai Creta","Honda City"};
    String[] bikes={"Select Bike","Honda CB Unicorn", "Yamaha FZ-S","TVS Star City"};
    float mileageValue;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        distance=findViewById(R.id.editTextNumberDecimal);
        vehicleType=findViewById(R.id.spinner);
        vehicleModel=findViewById(R.id.spinner3);
        findCostBtn=findViewById(R.id.button);
        mileage=findViewById(R.id.textView);
        ArrayAdapter aa = new ArrayAdapter(MainActivity.this,android.R.layout.simple_spinner_item,vehicles);
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        vehicleType.setAdapter(aa);
        vehicleType.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String option = parent.getSelectedItem().toString();
                if (option.equals("Select Vehicle")) {

                }
                if (option.equals("Car")) {
                    ArrayAdapter aa1 = new ArrayAdapter(MainActivity.this, android.R.layout.simple_spinner_item,cars);
                    aa1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    vehicleModel.setAdapter(aa1);
                    vehicleModel.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                            String option = parent.getSelectedItem().toString();
                            if (option.equals("Select Car")) {
                                mileageValue=0;
                            }
                            if (option.equals("Maruti Swift")) {
                                mileageValue= (float) 23.2;
                            }
                            if (option.equals("Hyundai Creta")) {
                                mileageValue= (float) 21.0;
                            }
                            if (option.equals("Honda City")) {
                                mileageValue= (float) 18.3;
                            }
                            mileage.setText("Mileage: "+mileageValue);
                        }
                        @Override
                        public void onNothingSelected (AdapterView < ? > adapterView){
                        }
                    });
                }
                if (option.equals("Bike")) {
                    ArrayAdapter aa1 = new ArrayAdapter(MainActivity.this, android.R.layout.simple_spinner_item,bikes);
                    aa1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    vehicleModel.setAdapter(aa1);
                    vehicleModel.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                            String option = parent.getSelectedItem().toString();
                            if (option.equals("Select Bike")) {
                                mileageValue=0;
                            }
                            if (option.equals("Honda CB Unicorn")) {
                                mileageValue= (float) 60.0;
                            }
                            if (option.equals("Yamaha FZ-S")) {
                                mileageValue= (float) 45.0;
                            }
                            if (option.equals("TVS Star City")) {
                                mileageValue= (float) 75.0;
                            }
                            mileage.setText("Mileage: "+mileageValue);
                        }
                        @Override
                        public void onNothingSelected (AdapterView < ? > adapterView){
                        }
                    });

                }
            }

            @Override
            public void onNothingSelected (AdapterView < ? > adapterView){

            }
        });

        findCostBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                float dist = new Float(distance.getText().toString());
                float fuelReqd=dist/mileageValue;
                float cost=fuelReqd*100;
                AlertDialog.Builder builder=new AlertDialog.Builder(MainActivity.this);
                builder.setMessage("Fuel Required: "+fuelReqd+" litres\nCost: Rs. "+cost+"\nTravel distance: "+dist+" km ");
                builder.setTitle("Fuel Cost");
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
            }
        });
    }
}