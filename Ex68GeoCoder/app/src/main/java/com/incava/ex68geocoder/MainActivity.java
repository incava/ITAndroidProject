package com.incava.ex68geocoder;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.net.Uri;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.Toast;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    EditText et;
    EditText etLat, etLng;
    double latitude;
    double longitude;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        et = findViewById(R.id.et);
        etLat = findViewById(R.id.et_lat);
        etLng = findViewById(R.id.et_lng);

        findViewById(R.id.btn).setOnClickListener(v->clickBtn());
        findViewById(R.id.btn2).setOnClickListener(v->clickBtn2());
        findViewById(R.id.btn3).setOnClickListener(v->clickBtnMap());
    }

    void clickBtn(){
        String addr =et.getText().toString();
        Geocoder geocoder = new Geocoder(this, Locale.KOREA);
        try {
            List<Address> addresses = geocoder.getFromLocationName(addr,3);
            StringBuffer buffer = new StringBuffer();
            for( Address address : addresses){
                buffer.append(address.getLatitude()+" , " + address.getLongitude() + "\n");
            }
            new AlertDialog.Builder(this)
                    .setMessage(buffer.toString())
                    .create()
                    .show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    void clickBtn2() {
        latitude = Double.parseDouble(etLat.getText().toString());
        longitude = Double.parseDouble(etLng.getText().toString());
        StringBuffer buffer = new StringBuffer();
        Geocoder geocoder = new Geocoder(this, Locale.KOREA);
        try {
            List<Address> addresses = geocoder.getFromLocation(latitude,longitude,3);
            for(Address address : addresses){
                buffer.append(address.getCountryName() +"\n");
                buffer.append(address.getCountryCode() + "\n");
                buffer.append(address.getPostalCode() + "\n");
                buffer.append(address.getAddressLine(0) + "\n");
                buffer.append(address.getAddressLine(1) + "\n");
                buffer.append(address.getAddressLine(2) + "\n");
                buffer.append("=================================\n");
            }
            new AlertDialog.Builder(this)
                    .setMessage(buffer.toString())
                    .create()
                    .show();
        } catch (IOException e) {
            Toast.makeText(this, "에러", Toast.LENGTH_SHORT).show();
            throw new RuntimeException(e);
        }
        Toast.makeText(this, "끝", Toast.LENGTH_SHORT).show();
    }

    void clickBtnMap(){
        Intent intent = new Intent(Intent.ACTION_VIEW);

        Uri uri = Uri.parse("geo:"+latitude + ","  + longitude+"?q="+latitude+","+longitude+"&z=10");
        intent.setData(uri);
        startActivity(intent);
    }


}