package com.incava.ex62backgroundtaskbyservice;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.IBinder;

import androidx.annotation.Nullable;
import androidx.core.app.NotificationChannelCompat;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

// Service는 안드로이드의 4대 주요 구성요소.

public class MyService extends Service {

    MediaPlayer mp;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        //foreground service가 Oreo버전부터 도입.
        // background가 작동되는 것을 알리기 위해, Notification을 강제.
        // startForeground();
        NotificationManagerCompat manager= NotificationManagerCompat.from(this);
        NotificationCompat.Builder builder = null;
        if(Build.VERSION.SDK_INT>=26){
            NotificationChannelCompat channel = new NotificationChannelCompat.Builder(
                    "ch01", NotificationManagerCompat.IMPORTANCE_HIGH).setName("Ex62 알림채널").build();
            manager.createNotificationChannel(channel);
            builder = new NotificationCompat.Builder(this,"ch01");
        }
        else{
            builder = new NotificationCompat.Builder(this,"");
        }
        builder.setSmallIcon(R.drawable.ic_stat_name);
        builder.setContentTitle("Ex62 Music Service");
        builder.setContentText("뮤직서비스가 실행중입니다.");


        Intent i = new Intent(this, MainActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(this,11,i,PendingIntent.FLAG_IMMUTABLE);
        builder.setContentIntent(pendingIntent);
        Notification notification = builder.build();
        startForeground(1,notification);

        if (mp==null){
            mp = MediaPlayer.create(this,R.raw.kalimba);
            mp.setVolume(0.7f,0.7f);
            mp.setLooping(true);
        }
        return START_STICKY;
    }

    @Override
    public void onDestroy() {
        if (mp!=null){
            mp.stop();
            mp.release();
            mp = null;
        }
        super.onDestroy();
    }
}
