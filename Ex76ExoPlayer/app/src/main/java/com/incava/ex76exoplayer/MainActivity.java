package com.incava.ex76exoplayer;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.res.Configuration;
import android.net.Uri;
import android.os.Bundle;

import com.google.android.exoplayer2.ExoPlayer;
import com.google.android.exoplayer2.MediaItem;
import com.google.android.exoplayer2.ui.PlayerView;
import com.google.android.exoplayer2.ui.StyledPlayerView;

public class MainActivity extends AppCompatActivity {

    PlayerView playerView;
    StyledPlayerView styledPlayerView;
    ExoPlayer exoPlayer,exoPlayer2;

    Uri videoUri = Uri.parse("http://commondatastorage.googleapis.com/gtv-videos-bucket/sample/ElephantsDream.mp4");



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        playerView = findViewById(R.id.player_view);
        exoPlayer = new ExoPlayer.Builder(this).build();
        playerView.setPlayer(exoPlayer);

        MediaItem mediaItem = MediaItem.fromUri(videoUri);
        exoPlayer.setMediaItem(mediaItem);
        exoPlayer.prepare();
        exoPlayer.play(); // 로딩완ㄹ까지 기다렸다가 재생함. join이 되나봄?
        findViewById(R.id.btn).setOnClickListener(view -> clickBtn());
//        Uri firstUri = Uri.parse("http://commondatastorage.googleapis.com/gtv-videos-bucket/sample/ForBiggerBlazes.mp4");
//        Uri secondUri = Uri.parse("http://commondatastorage.googleapis.com/gtv-videos-bucket/sample/ForBiggerEscapes.mp4");
//        MediaItem item1 = MediaItem.fromUri(firstUri);
//        MediaItem item2 = MediaItem.fromUri(secondUri);
//        exoPlayer.addMediaItem(item1);
//        exoPlayer.addMediaItem(item2);
        exoPlayer.setRepeatMode(ExoPlayer.REPEAT_MODE_ALL);

        styledPlayerView = findViewById(R.id.pv);
        exoPlayer2 = new ExoPlayer.Builder(this).build();
        styledPlayerView.setPlayer(exoPlayer);
        Uri firstUri = Uri.parse("http://commondatastorage.googleapis.com/gtv-videos-bucket/sample/ForBiggerBlazes.mp4");
        MediaItem mediaItem2 = MediaItem.fromUri(firstUri);
        exoPlayer2.setMediaItem(mediaItem2);
        exoPlayer2.prepare();
        exoPlayer2.play();

        //컨트롤 박스 모양 별도 레이아웃으로 지정.
        //layout폴더 안에  [exo_player_control_view.xml]로 레이아웃 만들면 바로 적용됨.
    }
    void clickBtn(){
        //전체화면 모드로 변경 - 별도의 전체화면용 액티비티 실행
        Intent intent = new Intent(this, FullScreenActivity.class);
        intent.setData(videoUri);
        intent.putExtra("currPos",exoPlayer.getCurrentPosition());
        startActivity(intent);
        //현재까지 재생된 위치정보도 추가로 전달.

    }

    @Override
    protected void onPause() {
        super.onPause();
        exoPlayer.pause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        exoPlayer.stop();
        exoPlayer.release();
        exoPlayer = null;
    }
}