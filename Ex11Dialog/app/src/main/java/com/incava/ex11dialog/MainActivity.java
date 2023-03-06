package com.incava.ex11dialog;

import androidx.appcompat.app.AppCompatActivity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    Button btn1,btn2;
    String[] items = new String[]{"Apple","Banana","Orange"};
    boolean[] checkArray = new boolean[]{true,false,true};
    int selectedPosition = 1;
    String a = "paris";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn1 = findViewById(R.id.btn_1);
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this,"띵동!",Toast.LENGTH_SHORT).show();
            }
        });

        btn2 = findViewById(R.id.btn_2);
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //AlertDialog를 만들어주는 건축가 객체를 생성
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                //builder.setMessage("dd")
//                builder.setMultiChoiceItems(items,checkArray, new DialogInterface.OnMultiChoiceClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialogInterface, int i, boolean b) {
//                        //두번째 파라미터 i 선택할때마다.
//                            checkArray[i] = b;
//                    }
//                })
//                        .setPositiveButton("ㄱㄱ", new DialogInterface.OnClickListener() {
//                            @Override
//                            public void onClick(DialogInterface dialogInterface, int i) {
//                                Toast.makeText(MainActivity.this,items[selectedPosition],Toast.LENGTH_SHORT).show();
//                                String s = "";
//                                for(int k = 0; k < checkArray.length; k++){
//                                    if(checkArray[k]) s += items[k];
//                                }
//                            }
//                        })
//                        .setNegativeButton("취소", new DialogInterface.OnClickListener() {
//                            @Override
//                            public void onClick(DialogInterface dialogInterface, int i) {
//                                Toast.makeText(MainActivity.this,"취소",Toast.LENGTH_SHORT).show();
//                            }
//                        });
                builder.setView(R.layout.dialog);
                AlertDialog alertDialog = builder.create();
                alertDialog.show();
                alertDialog.setCanceledOnTouchOutside(false);
                alertDialog.setCancelable(false);
            }
        });

    }
}