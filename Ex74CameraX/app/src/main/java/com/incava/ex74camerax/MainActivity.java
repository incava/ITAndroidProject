package com.incava.ex74camerax;

import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.camera.core.CameraSelector;
import androidx.camera.core.ImageCapture;
import androidx.camera.core.ImageCaptureException;
import androidx.camera.core.Preview;
import androidx.camera.lifecycle.ProcessCameraProvider;
import androidx.camera.view.PreviewView;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.ContentValues;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.common.util.concurrent.ListenableFuture;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ExecutionException;

import de.hdodenhof.circleimageview.CircleImageView;

public class MainActivity extends AppCompatActivity {

    PreviewView previewView;

    TextView tv;
    CircleImageView civ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        previewView = findViewById(R.id.preview_view);
        tv = findViewById(R.id.tv);
        civ = findViewById(R.id.civ);
        ArrayList<String> permissions = new ArrayList<>();
        permissions.add(Manifest.permission.CAMERA);
        permissions.add(Manifest.permission.RECORD_AUDIO);

        findViewById(R.id.fab).setOnClickListener(v->clickFab());
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);

        if(Build.VERSION.SDK_INT<=28) permissions.add(Manifest.permission.WRITE_EXTERNAL_STORAGE);
        int checkResult = checkSelfPermission(permissions.get(0));
        if (checkResult == PackageManager.PERMISSION_DENIED){
                String[] arr = new String[permissions.size()];
                permissions.toArray(arr);
                resultLauncher.launch(arr);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        startCamera();
    }
    ImageCapture imageCapture= null;
    void clickFab(){
        if(imageCapture==null) return;

        //시간이용으로 중복회피.
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss", Locale.KOREA);
        String fileName = sdf.format(System.currentTimeMillis());
        //CameraX의 미디어 DB에 저장할 한줄(record : 하나의 파일정보)
        ContentValues values = new ContentValues();
        values.put(MediaStore.MediaColumns.DISPLAY_NAME,fileName);
        values.put(MediaStore.MediaColumns.MIME_TYPE,"image/jpeg");
        if(Build.VERSION.SDK_INT > Build.VERSION_CODES.P){
            values.put(MediaStore.Images.Media.RELATIVE_PATH, "Pictures/CameraX-Image");
        }
        ImageCapture.OutputFileOptions outputFileOptions = new ImageCapture.OutputFileOptions
                .Builder(getContentResolver(),
                        MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                        values)
                .build();

        imageCapture.takePicture(outputFileOptions, ContextCompat.getMainExecutor(this),
                new ImageCapture.OnImageSavedCallback() {
            @Override
            public void onImageSaved(@NonNull ImageCapture.OutputFileResults outputFileResults) {
                Toast.makeText(MainActivity.this,"저장 성공", Toast.LENGTH_SHORT).show();
                tv.setText(outputFileResults.getSavedUri().toString());
                Glide.with(MainActivity.this)
                        .load(outputFileResults.getSavedUri())
                        .into(civ);
            }

            @Override
            public void onError(@NonNull ImageCaptureException exception) {
                Toast.makeText(MainActivity.this,"error : " + exception.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    void startCamera(){

        ListenableFuture<ProcessCameraProvider> listenableFuture= ProcessCameraProvider.getInstance(this);

        listenableFuture.addListener(new Runnable() {
            @Override
            public void run() {
                //카메라 기능 제공자..
                try {
                    ProcessCameraProvider cameraProvider= listenableFuture.get();

                    // Preview 작업을 하는 객체
                    Preview.Builder builder= new Preview.Builder();
                    Preview preview= builder.build();
                    preview.setSurfaceProvider(previewView.getSurfaceProvider());

                    imageCapture= new ImageCapture.Builder().build();
                    CameraSelector cameraSelector= CameraSelector.DEFAULT_BACK_CAMERA;

                    cameraProvider.bindToLifecycle(MainActivity.this, cameraSelector, preview, imageCapture);

                } catch (ExecutionException e) {
                    throw new RuntimeException(e);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }, ContextCompat.getMainExecutor(this));
    }


    ActivityResultLauncher<String[]> resultLauncher = registerForActivityResult(
            new ActivityResultContracts.RequestMultiplePermissions(), new ActivityResultCallback<Map<String, Boolean>>() {
                @Override
                public void onActivityResult(Map<String, Boolean> result) {
                    Set<String> keys = result.keySet();
                    for(String key : keys){
                        boolean value = result.get(key);
                        if (value) Toast.makeText(MainActivity.this, key+"를 허용 하셨습니다.", Toast.LENGTH_SHORT).show();
                        else Toast.makeText(MainActivity.this, key+"를 허용하지 않았습니다.", Toast.LENGTH_SHORT).show();
                    }
                }
            });





}