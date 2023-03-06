package com.incava.tpservice;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.AlarmManager;
import android.app.DatePickerDialog;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;
import android.widget.DatePicker;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {
    DatePickerDialog datePickerDialog = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.btn).setOnClickListener(v-> clickBtn());
        findViewById(R.id.btn2).setOnClickListener(v-> clickBtn2());
        findViewById(R.id.btn3).setOnClickListener(v-> clickBtn3());
    }
    public void clickBtn(){
        //알람시계 앱 실행

    }

    @SuppressLint("ScheduleExactAlarm")
    public void clickBtn2(){
        //알람 매니저를 이용한 5초 뒤에 알람
        // 알람 매니저로 알람서비스를 불러온다.
        AlarmManager manager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        Intent intent = new Intent(this, AlarmService.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(this,10,intent,PendingIntent.FLAG_IMMUTABLE);

        // 5초뒤에 인텐트가 깨어나면서 발동.
        manager.setExactAndAllowWhileIdle(AlarmManager.RTC_WAKEUP,System.currentTimeMillis()-5000,pendingIntent);
    }
    @SuppressLint("ScheduleExactAlarm")
    public void clickBtn3(){
        //알람 매니저를 이용한 특정시간 알람
        if (datePickerDialog != null){
            Log.i("피커 널아님","피커널 x");
            return ;
        }
        int result = checkSelfPermission(Manifest.permission.POST_NOTIFICATIONS);
        if (result == PackageManager.PERMISSION_DENIED){
            launcher.launch(Manifest.permission.POST_NOTIFICATIONS);
            return;
        }
        times();
        datePickerDialog = null;
    }

    ActivityResultLauncher<String> launcher = registerForActivityResult(
            new ActivityResultContracts.RequestPermission(), new ActivityResultCallback<Boolean>() {
                @Override
                public void onActivityResult(Boolean result) {
                    if(result) Toast.makeText(MainActivity.this, "알림 허용", Toast.LENGTH_SHORT).show();
                    else {
                        Toast.makeText(MainActivity.this, "알림 불가", Toast.LENGTH_SHORT).show();
                    }

                }
            });

    int year, month, day, hour, minute;
    @SuppressLint("ScheduleExactAlarm")
    public void times() {
        datePickerDialog = new DatePickerDialog(this);
        datePickerDialog.setOnDateSetListener(new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int y, int m, int d) {
                year = y;
                month = m;
                day = d;
                Calendar calendar = Calendar.getInstance();
                hour = calendar.get(Calendar.HOUR_OF_DAY);
                minute = calendar.get(Calendar.MINUTE);
                TimePickerDialog.OnTimeSetListener timeListener = new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int i, int i1) {
                        hour = i;
                        minute = i1;
                        Calendar calendar = Calendar.getInstance();
                        calendar.set(year,month,day,hour,minute);
                        AlarmManager manager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
                        Intent intent = new Intent(MainActivity.this, AlarmService.class);
                        PendingIntent pendingIntent = PendingIntent.getBroadcast(MainActivity.this,100, intent,PendingIntent.FLAG_IMMUTABLE);
                        manager.setExactAndAllowWhileIdle(AlarmManager.RTC_WAKEUP,calendar.getTimeInMillis(),pendingIntent);
                    }
                };
                new TimePickerDialog(MainActivity.this,timeListener,hour,minute,true).show();
            }
        });
        datePickerDialog.show();

    }

}