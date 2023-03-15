package com.incava.ex86imageupload;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.loader.content.CursorLoader;

import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.incava.ex86imageupload.databinding.ActivityMainBinding;

import java.io.File;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;

    //업로드 할 파일의 주소를 저장하는 문자열 변수하나.
    String imgPath = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.btnSelect.setOnClickListener(v->clickSelect());
        binding.btnUpload.setOnClickListener(v->clickUpload());
    }

    void clickSelect(){
        Intent intent = new Intent(MediaStore.ACTION_PICK_IMAGES);
        resultLauncher.launch(intent);
    }

    ActivityResultLauncher<Intent> resultLauncher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(), result -> {
                if(result.getResultCode() == RESULT_CANCELED) return;
                 Uri uri = result.getData().getData();
                Glide.with(this)
                        .load(uri)
                        .into(binding.iv);
                //new AlertDialog.Builder(this).setMessage(uri.toString()).create().show();

                //uri --> 파일 주소로 변환
                imgPath = getFilePathFromUri(uri);
                new AlertDialog.Builder(this).setMessage(imgPath).create().show();






            });

    //uri --> 파일 경로로 바꿔서 리턴해주는 메서드.

    String getFilePathFromUri(Uri uri){
        String[] proj = new String[]{MediaStore.Images.Media.DATA};
        CursorLoader loader = new CursorLoader(this,uri,proj,null,null,null);
        Cursor cursor = loader.loadInBackground(); // loader를 background로 빼서 작업
        int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
        cursor.moveToFirst(); // cursor를 처음으로 옮기기.
        String result = cursor.getString(column_index);
        cursor.close();
        return result;
    }



    void clickUpload(){
        Retrofit.Builder builder = new Retrofit.Builder();
        builder.baseUrl("http://incava.dothome.co.kr")
                .addConverterFactory(GsonConverterFactory.create());
        Retrofit retrofit = builder.build();
        RetrofitService retrofitService = retrofit.create(RetrofitService.class);
        File file = new File(imgPath);
        RequestBody body  = RequestBody.create(MediaType.parse("image/*"),file);
        MultipartBody.Part part = MultipartBody.Part.createFormData("img",file.getName(),body);
        Call<String> call = retrofitService.uploadImage(part);
        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                String s = response.body();
                new AlertDialog.Builder(MainActivity.this).setMessage(s).create().show();
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                Toast.makeText(MainActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

}