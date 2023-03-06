package com.incava.ex47activitysystemintent;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.btn).setOnClickListener(view -> {
            Intent intent = new Intent();
            intent.setAction(Intent.ACTION_DIAL);
            intent.setData(Uri.parse("tel:01041766488"));
            startActivity(intent);
        });
        findViewById(R.id.btn_sms).setOnClickListener(view -> {
            Intent intent = new Intent(Intent.ACTION_SENDTO);
            startActivity(intent);
            intent.setData(Uri.parse("smsto:0101010101"));
            intent.putExtra("sms_body","hello");
            //intent.putExtra(Intent.EXTRA_STREAM, );
        });
        findViewById(R.id.btn_web).setOnClickListener(view -> {
            Intent intent = new Intent(Intent.ACTION_VIEW,Uri.parse("https://www.naver.com"));
            startActivity(intent);
        });

        findViewById(R.id.btn_cam).setOnClickListener(view -> {
            Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            startActivity(intent);
        });



    }
}