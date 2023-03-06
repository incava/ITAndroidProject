package com.incava.ex51daemonthread;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import java.util.Date;

public class MainActivity extends AppCompatActivity {

    Mythread mythread;
    Long is;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mythread = new Mythread();
        mythread.start();
    }

    class Mythread extends Thread {
        boolean isRun = true;
        @Override
        public void run() {
            //5초마다 현재시간을 Toast로 보이도록.. 코드
            while(isRun){
                Date now = new Date();
                String s = now.toString();
                try {
                    Thread.sleep(5000);
                    runOnUiThread(()->Toast.makeText(MainActivity.this, s, Toast.LENGTH_SHORT).show());
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
            Log.i("Ex51","Mythread is dead");
        }
    }

    // 액티비티가 메모리에서 없어질 때 자동으로 실행디는 콜백 메소드


    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i("EX51","onDestroy");
       // mythread.isRun = false;

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
//        if (is!=null && System.currentTimeMillis()-is<3000) finish();
//        is = System.currentTimeMillis();
        //Toast.makeText(this, "한번더 누르면 꺼짐.", Toast.LENGTH_SHORT).show();
        Log.i("EX51","backPressed");
    }


}