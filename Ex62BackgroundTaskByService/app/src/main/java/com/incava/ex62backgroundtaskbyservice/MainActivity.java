package com.incava.ex62backgroundtaskbyservice;

import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.btn_start).setOnClickListener(v-> {
            //foreground service가 Oreo버전부터 도입.
            // background가 작동되는 것을 알리기 위해, Notification을 강제.
            Intent intent = new Intent(this, MyService.class);
            if(Build.VERSION.SDK_INT>=26){
                startForegroundService(intent);
            }
            startService(intent);
        });
        findViewById(R.id.btn_stop).setOnClickListener(v-> {
            Intent intent = new Intent(this, MyService.class);
            stopService(intent);
        });
        int checkResult = checkSelfPermission(Manifest.permission.POST_NOTIFICATIONS);
        if(checkResult == PackageManager.PERMISSION_DENIED){
            permissionResultLauncher.launch(Manifest.permission.POST_NOTIFICATIONS);
        }
    }
    ActivityResultLauncher<String> permissionResultLauncher = registerForActivityResult(
            new ActivityResultContracts.RequestPermission(), new ActivityResultCallback<Boolean>() {
                @Override
                public void onActivityResult(Boolean result) {
                    if(result) Toast.makeText(MainActivity.this, "알림 허용", Toast.LENGTH_SHORT).show();
                    else Toast.makeText(MainActivity.this, "알림 불가. 서비스도 불가.", Toast.LENGTH_SHORT).show();
                }
            }
    );
    @Override
    public void onBackPressed(){
      finish();
    }


}