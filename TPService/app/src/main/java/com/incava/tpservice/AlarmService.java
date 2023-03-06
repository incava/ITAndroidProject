package com.incava.tpservice;

import android.Manifest;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.widget.Toast;

import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.core.app.ActivityCompat;
import androidx.core.app.NotificationChannelCompat;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

public class AlarmService extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        // 노티피케이션 만들기.
        Toast.makeText(context, "알람 발동!", Toast.LENGTH_SHORT).show();
        Log.i("알람발동!!", "알람발동");
        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(context);
        NotificationChannel channel;
        NotificationCompat.Builder builder = null;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            channel = new NotificationChannel("ch01", "알림ㅇㅇ", NotificationManager.IMPORTANCE_HIGH);
            builder = new NotificationCompat.Builder(context, "ch01");
            notificationManager.createNotificationChannel(channel);

        } else {
            builder = new NotificationCompat.Builder(context, "");
        }
        Intent intents = new Intent(context, MainActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(context, 10, intents, PendingIntent.FLAG_IMMUTABLE);
        builder.setContentTitle("알람") //제목
                .setContentText("알람 테스트입니다") //내용
                .setDefaults(Notification.DEFAULT_ALL) //알림 설정(사운드, 진동)
                .setAutoCancel(true) //터치 시 자동으로 삭제할 지 여부
                .setPriority(NotificationCompat.PRIORITY_HIGH) // 알림의 중요도
                .setSmallIcon(R.drawable.newyork)
                .setLargeIcon(BitmapFactory.decodeResource(context.getResources(), R.drawable.newyork))
                .setContentIntent(pendingIntent);
        Notification notification = builder.build();
        //알림 매니저에게 알림을 보이도록 요청
        if (ActivityCompat.checkSelfPermission(context, android.Manifest.permission.POST_NOTIFICATIONS) != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        notificationManager.notify(100, notification);
    }

}
