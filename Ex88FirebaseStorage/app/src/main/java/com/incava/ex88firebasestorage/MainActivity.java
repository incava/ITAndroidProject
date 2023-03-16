package com.incava.ex88firebasestorage;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.incava.ex88firebasestorage.databinding.ActivityMainBinding;

import java.text.SimpleDateFormat;
import java.util.Date;

public class MainActivity extends AppCompatActivity {

    //Firebase : 안드로이드 개발할 때 서버단 작업을 코딩 한 줄없이 사용 하는 플랫폼
    // 이 프로젝트와 Firebase 플랫폼을 연동하기!
    ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        binding.btnLoad.setOnClickListener(v-> clickLoad());
        binding.btnSelect.setOnClickListener(v-> clickSelect());
        binding.btnUpLoad.setOnClickListener(v-> clickUpload());
    }

    void clickSelect(){
        Intent intent = new Intent(MediaStore.ACTION_PICK_IMAGES);
        resultLauncher.launch(intent);
    }

    void clickUpload(){
        if (imgUri==null) return;

        FirebaseStorage firebaseStorage = FirebaseStorage.getInstance();
        // 저장할 파일명이 중복되지 않도록 날짜로 변수명 정하기.
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
        String fileName = "IMG_" + sdf.format(new Date()) +".png";

        StorageReference imgRef = firebaseStorage.getReference("photo/"+fileName);// photo 폴더가 없으면 만들고 있으면 참조.

        //위 저장경로 참조객체에게 실제 파일업로드 시키기.
        imgRef.putFile(imgUri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                Toast.makeText(MainActivity.this, taskSnapshot.toString(), Toast.LENGTH_SHORT).show();
            }
        });
        //Firebase Storage에 파일 업로드 하기.
        //파이어베이스 저장소 관리 객체 소환.
    }

    Uri imgUri = null;
    ActivityResultLauncher<Intent>  resultLauncher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(), result -> {
                if(result.getResultCode() == RESULT_CANCELED) return;
                imgUri = result.getData().getData();
                Glide.with(this).load(imgUri).into(binding.iv);
            });


    void clickLoad(){
        FirebaseStorage firebaseStorage = FirebaseStorage.getInstance();
        StorageReference rootRef = firebaseStorage.getReference();
        StorageReference imgRef = rootRef.child("paris.jpg");
        if(imgRef!=null){
            //파일 참조객체로부터 이미지의 [다운로드 URL]를 얻어오기.
            imgRef.getDownloadUrl().addOnSuccessListener(uri -> {
                Glide.with(this).load(uri).into(binding.iv);
            });
        }
    }

}