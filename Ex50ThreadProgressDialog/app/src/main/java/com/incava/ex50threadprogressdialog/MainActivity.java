package com.incava.ex50threadprogressdialog;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.btn).setOnClickListener(view->clickBtn());
        findViewById(R.id.btn2).setOnClickListener(view->clickBtn2());

    }

    ProgressDialog dialog;
    int gauge = 0;

    void clickBtn(){
        if( dialog!=null ) return;

        dialog = new ProgressDialog(this);
        dialog.setTitle("wheel dialog");
        dialog.setMessage("down loading");
        dialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        dialog.setCanceledOnTouchOutside(false);
        dialog.show();
        handler.sendEmptyMessageDelayed(0,3000);
        //wheel type progress dialog..
    }
    Handler handler = new Handler(Looper.getMainLooper()){
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            dialog.dismiss();
            dialog = null;
        }
    };

    void clickBtn2(){
        dialog = new ProgressDialog(this);
        dialog.setTitle("wheel dialog");
        dialog.setMessage("down loading");
        dialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        dialog.setCanceledOnTouchOutside(false);
        dialog.setMax(100);
        dialog.show();
        new Thread(){
            @Override
            public void run() {
                while (gauge<100){
                    gauge+=1;
                    dialog.setProgress(gauge);
                    try {
                        Thread.sleep(50);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
                dialog.dismiss();
                dialog = null;
                gauge = 0;
            }
        }.start();

        //handler.sendEmptyMessageDelayed(0,3000);
        //
    }


}