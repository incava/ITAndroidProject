package com.incava.ex60broadcastbooting;

import android.app.Notification;
import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.util.Log;

import androidx.core.app.ActivityCompat;
import androidx.core.app.NotificationChannelCompat;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import java.nio.channels.Channel;

//4대 컴포넌트는 반드시 매니페스트에 등록
public class MyBooting extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        Log.i("EX60", "booting receive");

        //Android N버전부터 부팅완료를 들으려면 앱을 설치한 후 적어도 1회 사용자가 직접 런처화면(앱목록 아이콘)에서
        // 아이콘을 클릭하여 실행한 이력이 있는 앱만 부팅완료를 들을 수 있음.

        //부팅완료되면 MainActivity 화면이 실행되도록...
        String action = intent.getAction();
        if (action.equals(Intent.ACTION_BOOT_COMPLETED)) {
            //Android 10버전부터 리시버에서 직접
            //액티비티 실행하는 것을 금지.
            //대신 알림을 통해 사용자에게 신호를 주고 액티비티 실행 할지 여부를 선택하도록 변경.
            if (Build.VERSION.SDK_INT >= 29) {
                NotificationManagerCompat manager = NotificationManagerCompat.from(context);
                // 알림객체를 만들어주는 건축가 객체 생성
                NotificationChannelCompat channel = new NotificationChannelCompat.Builder("ch01", NotificationManagerCompat.IMPORTANCE_HIGH).build();
                manager.createNotificationChannel(channel);
                NotificationCompat.Builder builder = new NotificationCompat.Builder(context, "ch01");
                builder.setSmallIcon(R.drawable.ic_stat_name);
                builder.setContentTitle("부팅완료");
                builder.setContentText("부팅이 완료되었습니다.");
                if (ActivityCompat.checkSelfPermission(context, android.Manifest.permission.POST_NOTIFICATIONS) != PackageManager.PERMISSION_GRANTED) {
                    return;
                }
                manager.notify(100, builder.build());


            }
            else{
                Intent i = new Intent(context, MainActivity.class);
                i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);//이 앱에서 액티비티가 처음 실행 되는 거라면 필요한 설정.
                context.startActivity(i);
            }
        }
    }
}
