package com.incava.ex72cameraapp2;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.FileProvider;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MainActivity extends AppCompatActivity {

    ImageView iv;
    TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        iv = findViewById(R.id.iv);
        tv = findViewById(R.id.tv);
        findViewById(R.id.btn).setOnClickListener(view -> clickBtn());
    }
    Uri imgUri= null;
    void clickBtn(){
        Intent intent= new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

        // 개발자가 저장되길 원하는 위치의 파일 경로 URI를 만들어주는 기능호출
        createImageUri(); // 저 아래에 직접 만들 메소드

        // 촬영한 이미지를 파일로 저장하도록..추가 데이터로 [저장될 이미지의 Uri] 설정
        if(imgUri!=null) intent.putExtra(MediaStore.EXTRA_OUTPUT, imgUri);
        resultLauncher.launch(intent);
    }
    ActivityResultLauncher<Intent> resultLauncher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),result -> {
               if(result.getResultCode()==RESULT_OK){
                    //촬영한 이미지를 EXTRA_OUTPUT으로 지정한
                   //imgUri 에 저장 했을 것이다.
                   Glide.with(this)
                           .load(imgUri)
                           .into(iv);
               }
            });

    void createImageUri(){
        File path = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);
        tv.setText(path.toString());
        String filename = "EX72_IMG"+ new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date()) + ".jpg";
        File file = new File(path,filename);
        tv.setText(file.toString());
        imgUri = FileProvider.getUriForFile(this,"Ex72",file);

        // 카메라 앱은 저장될 이미지의 실제 경로가 아니라 DB주소에 해당하는 콘텐츠 경로가 필요함.
        // 이 콘텐츠 경로를 Uri라고 부름.
        // 실제경로(File 클래스 객체)를 콘텐츠 경로 Uri객체로 변환.


    }


}