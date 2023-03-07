package com.incava.ex70photomultiplepickbyintent;

import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.PickVisualMediaRequest;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import android.content.ClipData;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.TextView;

import com.tbuonomo.viewpagerdotsindicator.DotsIndicator;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    TextView tv;
    ArrayList<Uri> arrayList = new ArrayList<>();
    ViewPager2 viewPager;
    MyAdapter myAdapter;

    DotsIndicator dotsIndicator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tv = findViewById(R.id.tv);
        findViewById(R.id.btn).setOnClickListener(v->clickBtn());
        findViewById(R.id.btn2).setOnClickListener(v->clickBtn2());
        findViewById(R.id.btn3).setOnClickListener(v->clickBtn3());
        myAdapter = new MyAdapter(this,arrayList);
        viewPager = findViewById(R.id.pager);
        viewPager.setAdapter(myAdapter);
        viewPager.setOrientation(ViewPager2.ORIENTATION_HORIZONTAL);
        dotsIndicator = findViewById(R.id.dots_indicator);
    }

    //여러사진 선택 결과를 돌려받는 계약체결 대행사 객체 등록.
    ActivityResultLauncher<Intent> resultLauncher =registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(), result -> {
                Intent intent = result.getData();
                ClipData data = intent.getClipData();
                tv.setText("선택갯수 : "+data.getItemCount());
                for(int i = 0; i < data.getItemCount(); i++){
                    arrayList.add(data.getItemAt(0).getUri());
                }
                myAdapter.notifyDataSetChanged();
            });

    void clickBtn(){
        Intent intent = new Intent(Intent.ACTION_OPEN_DOCUMENT).setType("image/*").putExtra(Intent.EXTRA_ALLOW_MULTIPLE,true);
        resultLauncher.launch(intent);
    }

    void clickBtn2(){
        mediaRequestActivityResultLauncher.launch(new PickVisualMediaRequest());

    }

    ActivityResultLauncher<PickVisualMediaRequest> mediaRequestActivityResultLauncher =
            registerForActivityResult(new ActivityResultContracts.PickMultipleVisualMedia(), new ActivityResultCallback<List<Uri>>() {
                @Override
                public void onActivityResult(List<Uri> result) {
                    for(Uri uri : result){
                        arrayList.add(uri);
                    }
                    tv.setText(result.size()+"");
                    myAdapter.notifyDataSetChanged();
                }
            });

    void clickBtn3(){
        Intent intent = new Intent(MediaStore.ACTION_PICK_IMAGES).putExtra(MediaStore.EXTRA_PICK_IMAGES_MAX, 10);
        resultLauncher.launch(intent);
    }


}