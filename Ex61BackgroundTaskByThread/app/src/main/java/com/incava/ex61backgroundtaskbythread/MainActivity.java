package com.incava.ex61backgroundtaskbythread;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    MyThread myThread;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.btn_start).setOnClickListener(view -> {
            if(myThread !=null)return;
            myThread = new MyThread();
            myThread.start();
        });

        findViewById(R.id.btn_stop).setOnClickListener(view -> {
            if(myThread !=null){
             myThread.isRun = false;
             myThread = null;
            }else{
                Toast.makeText(this, "Thread객체를 참조하고 있지 않습니다.", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onBackPressed() {
        //super.onBackPressed();
        finish();
    }

    class MyThread extends Thread{
        boolean isRun = true;
        @Override
        public void run() {
            while(isRun){
                runOnUiThread(() -> Toast.makeText(MainActivity.this, "백그라운드 작업...", Toast.LENGTH_SHORT).show());
                Log.i("EX61", "백그라운드 작업");
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }

}