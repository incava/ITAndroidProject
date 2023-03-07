package com.incava.ex69camera;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.PickVisualMediaRequest;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.widget.ImageView;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import com.github.chrisbanes.photoview.PhotoView;

public class MainActivity extends AppCompatActivity {
    TextView tv;
    PhotoView iv;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tv = findViewById(R.id.tv_uri);
        iv = findViewById(R.id.iv);
        findViewById(R.id.btn1).setOnClickListener(v->clickBtn());
        findViewById(R.id.btn2).setOnClickListener(v->clickBtn2());
        findViewById(R.id.btn3).setOnClickListener(v->clickBtn3());
        findViewById(R.id.btn4).setOnClickListener(v->clickBtn4());
        findViewById(R.id.btn5).setOnClickListener(v->clickBtn5());

    }

    //사진 선택결과를 받아오는 계약을 체결하는 대행사 객체 등록.

    ActivityResultLauncher<Intent> imagePickResultLauncher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    if(result.getResultCode() == RESULT_OK){
                        Intent intent = result.getData();
                        Uri uri = intent.getData();
                        tv.setText(uri.toString());
                        Glide.with(MainActivity.this)
                                .load(uri)
                                .circleCrop()
                                .into(iv);
                    } else if (result.getResultCode() == RESULT_CANCELED) {

                    }
                }
            });


    void clickBtn(){
        Intent intent = new Intent(Intent.ACTION_PICK).setType("image/*");
        imagePickResultLauncher.launch(intent);

    }

    void clickBtn2(){
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT).setType("image/*");
        imagePickResultLauncher.launch(intent);

    }

    void clickBtn3(){
        Intent intent = new Intent(Intent.ACTION_OPEN_DOCUMENT).setType("image/*");
        imagePickResultLauncher.launch(intent);

    }

    void clickBtn4(){
        //명시적인 INTENT 생성없이 사진선택 화면이 BottomSheet 형식으로 보여짐.
        // PickVisualMedia
        pickMediaLauncher.launch(new PickVisualMediaRequest());
    }
    ActivityResultLauncher<PickVisualMediaRequest> pickMediaLauncher = registerForActivityResult(
            new ActivityResultContracts.PickVisualMedia(), new ActivityResultCallback<Uri>() {
                @Override
                public void onActivityResult(Uri result) {
                    tv.setText(result.toString());
                    Glide.with(MainActivity.this)
                            .load(result)
                            .into(iv);
                }
            });



    void clickBtn5(){
        //MediaStore ACTION_PICK -> PickVisualMedia와 같은 역할 액션
        Intent intent = new Intent(MediaStore.ACTION_PICK_IMAGES);
        imagePickResultLauncher.launch(intent);
    }






}