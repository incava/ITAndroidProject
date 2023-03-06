package com.incava.ex48thread;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    Button btn, btn2;

    TextView tv;

    int num = 0;

    MyThread thread;

//    Handler handler = new Handler(Looper.getMainLooper()){
//        @Override
//        public void handleMessage(@NonNull Message msg) {
//            super.handleMessage(msg);
//            tv.setText(num+"");
//        }
//    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn = findViewById(R.id.btn);
        tv = findViewById(R.id.tv);
        btn2 = findViewById(R.id.btn2);
        btn.setOnClickListener(view -> {
            for(int i = 0; i<20; i++){
                tv.setText(++num+"");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }

        });
        thread = new MyThread();
        btn2.setOnClickListener(view -> {
            thread.start();
        });
    }


    class MyThread extends Thread{

        @Override
        public void run() {
            for(int i = 0; i<10; i++){
                num++;
                //handler.sendEmptyMessage(0);
                //runOnUiThread(() -> tv.setText(num+""));
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        tv.setText(num+"");
                    }
                });
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }


}