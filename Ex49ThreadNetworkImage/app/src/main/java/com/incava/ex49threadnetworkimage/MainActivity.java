package com.incava.ex49threadnetworkimage;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

public class MainActivity extends AppCompatActivity {

    Button btn;
    ImageView iv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn = findViewById(R.id.btn_image);
        iv = findViewById(R.id.iv);
        btn.setOnClickListener(view -> {
//            new Thread(){
//                public void run(){
//
//                    //                        URL url = new URL(imgUrl);
////                        InputStream is = url.openStream();
////                        //스트림을 통해 이미지를 읽어와서 Bitmap 객체로 생성하기.
////                        Bitmap bm = BitmapFactory.decodeStream(is);
////                        runOnUiThread(()->iv.setImageBitmap(bm));
//                        runOnUiThread(()->);
//                }
//            }.start();
        });

    }


}