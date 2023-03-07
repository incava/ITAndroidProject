package com.incava.ex71camera;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    ImageView iv;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        iv = findViewById(R.id.iv);
        findViewById(R.id.btn).setOnClickListener(v->clickBtn());
    }



    void clickBtn(){
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        activityResultLauncher.launch(intent);
    }

    ActivityResultLauncher<Intent> activityResultLauncher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),result -> {
                if(result.getResultCode()==RESULT_OK){
                    Uri uri = result.getData().getData();
                    if(uri==null) Toast.makeText(this, "null", Toast.LENGTH_SHORT).show();

                    //요즘 핸드폰은 프로그램을 통해 실행한 카메라앱에서 촬영한 사진은 디
                    //촬영한 사진 정보를 Bitmap 객체로 만들어서 Extra 데이터로 전달해줌.
                    //preview 미리 보기 수준.
                    Intent intent = result.getData();
                    Bundle bundle = intent.getExtras();
                    Bitmap bm = (Bitmap) bundle.get("data");
                    iv.setImageBitmap(bm);
                    //개발자가 파일로 저장되길 원한다면,
                    //인텐트로 카메라앱을 실행할 때 추가 데이터를 설정해야 함. 다음 예제로...

                }
            });



}