package com.incava.ex63servicebind;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Binder;
import android.os.IBinder;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class MyMusicService extends Service {


    //bindService()를 실행 하면 자동으로 발동하는 콜백메서드.
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return new MyBinder();
    }
    //터널을 통해 MainActivity로 넘어갈 Binder 객체
    class MyBinder extends Binder {
        public MyMusicService getServiceObject(){
            return MyMusicService.this;
        }

    }



    @Override
    public void onCreate() {
        super.onCreate();
        Toast.makeText(this, "onCreate", Toast.LENGTH_SHORT).show();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Toast.makeText(this, "OnstartCommand", Toast.LENGTH_SHORT).show();
        return START_STICKY;
    }

    MediaPlayer mp;

    public void playMusic(){
        if(mp==null){
            mp = MediaPlayer.create(this,R.raw.my_friend_dragon);
            mp.setVolume(0.7f,0.7f);
            mp.setLooping(true);
            Toast.makeText(this, "playing...", Toast.LENGTH_SHORT).show();
        }

    }

    public void pauseMusic(){
        if(mp!= null && mp.isPlaying()) {
            mp.pause();
            Toast.makeText(this, "Stopping...", Toast.LENGTH_SHORT).show();
        }
    }

    public void stopMusic(){
        if(mp!=null){
            mp.stop();
            mp.release();
            mp = null;
        }
    }

}
