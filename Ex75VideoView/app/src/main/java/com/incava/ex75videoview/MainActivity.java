package com.incava.ex75videoview;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.widget.MediaController;
import android.widget.VideoView;

public class MainActivity extends AppCompatActivity {

    VideoView vv;

    //비디오 파일은 용량이 크기에 앱의 res 폴더에 직접 가지고 있지 않음.
    // 웹서버에 동영상을 업로드하고 이를 불러와서 재생함.
    String videoUrl = "http://commondatastorage.googleapis.com/gtv-videos-bucket/sample/BigBuckBunny.mp4";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.btn).setOnClickListener(v->{
            Intent intent = new Intent(this, SecondActivity.class);
            startActivity(intent);
        });

        vv = findViewById(R.id.vv);
        // 비디오뷰에 재생, 일시정지 등을 할 수 있는 컨트롤바 붙이기.
        vv.setMediaController(new MediaController(this));
        //단점 1. 컨트롤바가 액티비티에 붙는다.
        // 비디오뷰는
        vv.setVideoURI(Uri.parse(videoUrl));

        //동영상은 로딩하는 시간이 걸리기에 바로 start 불가.
        //로딩이 완료되었을때 재생하도록.
        vv.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mediaPlayer) {
                //mediaPlayer.start(); 둘다 됨.
                vv.start();
            }
        });

    }

    @Override
    protected void onPause() {
        super.onPause();
        if(vv!=null&& vv.isPlaying()){
            vv.pause();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        if(vv!=null&&!vv.isPlaying()){
            vv.start();
        }
    }
}