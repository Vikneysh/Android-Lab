package com.example.reversegeocoder;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.List;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {
    private Button locate;
    private EditText lat,lon;
    private TextView addr,viewMap;
    private String address="";
    private String city="";
    private String locationName="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        lat=findViewById(R.id.latitude);
        lon=findViewById(R.id.longitude);
        locate = findViewById(R.id.locateBtn);
        addr= findViewById(R.id.address);
        viewMap=findViewById(R.id.viewMapTextView);

        locate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Double latVal = Double.parseDouble(lat.getText().toString());
                Double lonVal = Double.parseDouble(lon.getText().toString());
                try{
                    Geocoder geocoder =new Geocoder(MainActivity.this, Locale.getDefault());
                    List<Address> ad = geocoder.getFromLocation(latVal,lonVal,2);
                    address = ad.get(0).getAddressLine(0);
//                    city = ad.get(0).getLocality();
                    addr.setText(address );
                    viewMap.setText("View on Map");

                }
                catch (Exception e){
                    e.printStackTrace();  //Latitude: 9.524. Longitude: 77.855 --> Mepco
                }
            }
        });
        viewMap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Uri gmmIntentUri = Uri.parse("geo:0,0?q="+addr.getText().toString());
                Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
                mapIntent.setPackage("com.google.android.apps.maps");
                startActivity(mapIntent);
                }

        });
    }
}