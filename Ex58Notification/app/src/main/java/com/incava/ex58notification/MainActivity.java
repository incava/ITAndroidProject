package com.incava.ex58notification;

import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.AudioAttributes;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.btn).setOnClickListener(view -> {
            //Android 13버전(api 33버전)부터 알림에 대한 퍼미션이 추가.
            //이 앱이 알림에 대한 퍼미션을 허용한 상태인지 체크.
            //허가 되어 있으면 0, 거부 되어 있으면 -1
            int checkResult = ContextCompat.checkSelfPermission(this, Manifest.permission.POST_NOTIFICATIONS);
            if(checkResult == PackageManager.PERMISSION_DENIED){
                //퍼미션 요청결과를 받아주는 대행사 객체 -> ResultLauncher
                if(shouldShowRequestPermissionRationale(Manifest.permission.POST_NOTIFICATIONS)){
                    Toast.makeText(this, "지금 한번 거절이미하셨네요.", Toast.LENGTH_SHORT).show();
                }
                permissionResultLauncher.launch(Manifest.permission.POST_NOTIFICATIONS);
                return;
            }
            NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
            NotificationCompat.Builder builder = null;
            //26버전부터는  NotificationChannel을 만들어야함.
            if(Build.VERSION.SDK_INT>= Build.VERSION_CODES.O){
                NotificationChannel channel = new NotificationChannel("ch01","알림ㅇㅇ",NotificationManager.IMPORTANCE_HIGH);
                // 알림 사운드, 진동 channel 또는 builder에서 확인.
                builder = new NotificationCompat.Builder(this,"ch01");
                //기본 uri
                //Uri uri = RingtoneManager.getActualDefaultRingtoneUri(this,RingtoneManager.TYPE_NOTIFICATION);
                //channel.setSound(uri,new AudioAttributes.Builder().build());

                // Custom uri
                Uri uri = Uri.parse("android.resource://"+getPackageName() + "/" + R.raw.s_select);
                channel.setSound(uri,new AudioAttributes.Builder().build());

                //진동은 사용자 "정적" 퍼미션 요구됨.
                channel.setVibrationPattern(new long[]{0,2000,1000,3000});


                notificationManager.createNotificationChannel(channel);
            }else{
                builder = new NotificationCompat.Builder(this,"");
            }
            // 상태 표시줄에 보이는 아이콘.
            builder.setSmallIcon(R.drawable.ic_noti);

            //상태바를 드래그하여 아래로 내리면 보이는 알림창(확장 상태바)의 설정.

            builder.setContentTitle("요기요때");
            builder.setContentText("message....");
            Bitmap bm = BitmapFactory.decodeResource(getResources(),R.drawable.koala);
            builder.setLargeIcon(bm);

            //알림창을 클릭했을 때 새로운 화면이 실행되도록.
            Intent intent = new Intent(this, SecondActivity.class);

            // intent객체에게 바로 실행하지 않고 잠시 보류해 달라고 하기.
            //flag는 immutable로.
            PendingIntent pendingIntent = PendingIntent.getActivity(this,10,intent,PendingIntent.FLAG_IMMUTABLE);
            builder.setContentIntent(pendingIntent);
            //눌렀을때 위의 아이콘을 삭제하도록.
            builder.setAutoCancel(true);

            //요즘들어 보이는 알림창 꾸미기..
            NotificationCompat.BigPictureStyle style = new NotificationCompat.BigPictureStyle();
            style.bigPicture(BitmapFactory.decodeResource(getResources(),R.drawable.newyork));
            builder.setStyle(style);
            // 스타일이 여러개. 확인해보면 좋을듯. keyword: 알림 스타일
            // 알림창의 클릭 액션에 의해 실행될 화면에 여러개 일때
            builder.addAction(R.drawable.ic_noti,"setting",pendingIntent);
            builder.addAction(R.drawable.ic_noti,"dd",pendingIntent);
            builder.addAction(R.drawable.ic_noti,"settingg",pendingIntent);
            Notification notification = builder.build();
            //알림 매니저에게 알림을 보이도록 요청
            notificationManager.notify(100,notification);

        });

    }
    ActivityResultLauncher<String> permissionResultLauncher = registerForActivityResult(
            new ActivityResultContracts.RequestPermission(), new ActivityResultCallback<Boolean>() {
                @Override
                public void onActivityResult(Boolean result) {
                    if(result) Toast.makeText(MainActivity.this, "알림 허용", Toast.LENGTH_SHORT).show();
                    else Toast.makeText(MainActivity.this, "알림 불가", Toast.LENGTH_SHORT).show();
                }
            }
    );
}