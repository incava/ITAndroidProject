package com.incava.ex63servicebind;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Service;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.IBinder;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    MyMusicService musicService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.btn_play).setOnClickListener(view -> clickPlay());
        findViewById(R.id.btn_pause).setOnClickListener(view -> clickPause());
        findViewById(R.id.btn_stop).setOnClickListener(view -> clickStop());
    }

    @Override
    protected void onResume() {
        super.onResume();
        if(musicService==null){
            Intent intent = new Intent(this, MyMusicService.class);
            startService(intent);
            bindService(intent,serviceConnection,0);
        }
        else{
            Toast.makeText(this, "notNull", Toast.LENGTH_SHORT).show();
        }
    }
    ServiceConnection serviceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            Toast.makeText(MainActivity.this, "binded...", Toast.LENGTH_SHORT).show();
            MyMusicService.MyBinder binder = (MyMusicService.MyBinder)iBinder;
            musicService = binder.getServiceObject();
        }
        @Override
        public void onServiceDisconnected(ComponentName componentName) {

        }
    };

    void clickPlay(){
        if(musicService!=null) musicService.playMusic();
    }

    void clickPause(){
        if(musicService!=null) musicService.pauseMusic();
        //Toast.makeText(this, "pauseing...", Toast.LENGTH_SHORT).show();
    }
    void clickStop() {
        if (musicService != null) {
            musicService.stopMusic();
            unbindService(serviceConnection);
            musicService = null;
            //Toast.makeText(this, "Stopping...", Toast.LENGTH_SHORT).show();
        }
        Intent intent = new Intent(this, MyMusicService.class);
        stopService(intent);
        finish();
    }


}