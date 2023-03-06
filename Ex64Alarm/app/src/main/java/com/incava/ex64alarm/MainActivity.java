package com.incava.ex64alarm;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlarmManager;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.provider.AlarmClock;
import android.widget.DatePicker;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.btn).setOnClickListener(v-> clickBtn());
        findViewById(R.id.btn2).setOnClickListener(v-> clickBtn2());
        findViewById(R.id.btn3).setOnClickListener(v-> clickBtn3());
    }

    void clickBtn(){
        Intent intent = new Intent(AlarmClock.ACTION_SET_ALARM)
                .putExtra(AlarmClock.EXTRA_HOUR,14)
                .putExtra(AlarmClock.EXTRA_MINUTES,31)
                .putExtra(AlarmClock.EXTRA_SKIP_UI,false)
                .putExtra(AlarmClock.EXTRA_MESSAGE,"TEST ALARM")
                .putExtra(AlarmClock.EXTRA_DAYS,new int[]{Calendar.MONDAY, Calendar.WEDNESDAY});
        startActivity(intent);

    }
    void clickBtn2(){
        AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        Intent intent = new Intent(this,AlarmReceiver.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(this,10,intent,PendingIntent.FLAG_IMMUTABLE);
        alarmManager.setAndAllowWhileIdle(AlarmManager.RTC_WAKEUP,System.currentTimeMillis()-10000,pendingIntent);

    }
    void clickBtn3(){
        DatePickerDialog dialog = new DatePickerDialog(this);
        dialog.setOnDateSetListener((datePicker, y, m, d) -> {
            Toast.makeText(this, year+"년"+month+"월"+day+"일", Toast.LENGTH_SHORT).show();
            year = y;
            month = m;
            day = d;
            Calendar calendar = Calendar.getInstance();
            hour = calendar.get(Calendar.HOUR_OF_DAY);
            minute = calendar.get(Calendar.MINUTE);
            new TimePickerDialog(this,timeSetListener,hour,minute,true).show();
        });
        dialog.show();
    }
    int year, month, day;
    int hour, minute;
    TimePickerDialog.OnTimeSetListener timeSetListener = new TimePickerDialog.OnTimeSetListener() {
        @Override
        public void onTimeSet(TimePicker timePicker, int h, int m) {
            hour = h;
            minute = m;

            //정해진 날짜로 Calendar 객체를 생성
            Calendar calendar = Calendar.getInstance();
            calendar.set(year,month,day,hour,minute);

            AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
            Intent intent = new Intent(MainActivity.this, AlarmReceiver.class);
            PendingIntent pendingIntent = PendingIntent.getBroadcast(MainActivity.this,200,intent,PendingIntent.FLAG_IMMUTABLE);
            alarmManager.setAndAllowWhileIdle(AlarmManager.RTC_WAKEUP,calendar.getTimeInMillis(),pendingIntent);
        }
    };

}