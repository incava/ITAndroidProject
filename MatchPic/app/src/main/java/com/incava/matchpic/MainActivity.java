package com.incava.matchpic;

import static java.lang.Integer.getInteger;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    ImageView ivHowTo, ivStart,ivAnimal1,ivAnimal2,ivAnimal3,ivAnimal4,ivAnimal5,ivBoard;
    Map<Integer, Integer> map = new HashMap<>();
    ArrayList<Integer> list = new ArrayList<>();
    int[] animalArray = new int[]{R.drawable.a_ele,R.drawable.a_frog,R.drawable.a_lion,R.drawable.a_pig,R.drawable.a_monkey};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ivHowTo = findViewById(R.id.iv_how_to_play);
        ivStart = findViewById(R.id.iv_start);
        ivAnimal1 = findViewById(R.id.iv_animal1);
        ivAnimal2 = findViewById(R.id.iv_animal2);
        ivAnimal3 = findViewById(R.id.iv_animal3);
        ivAnimal4 = findViewById(R.id.iv_animal4);
        ivAnimal5 = findViewById(R.id.iv_animal5);
        ivBoard = findViewById(R.id.iv_board);
        map.put(R.drawable.a_ele,R.drawable.b_ele);
        map.put(R.drawable.a_frog,R.drawable.b_frog);
        map.put(R.drawable.a_lion,R.drawable.b_lion);
        map.put(R.drawable.a_pig,R.drawable.b_pig);
        map.put(R.drawable.a_monkey,R.drawable.b_monkey);
        ivAnimal1.setOnClickListener(listener);
        ivAnimal2.setOnClickListener(listener);
        ivAnimal3.setOnClickListener(listener);
        ivAnimal4.setOnClickListener(listener);
        ivAnimal5.setOnClickListener(listener);
        list.add(R.drawable.b_ele);
        list.add(R.drawable.b_frog);
        list.add(R.drawable.b_lion);
        list.add(R.drawable.b_pig);
        list.add(R.drawable.b_monkey);

        setT


    }
    View.OnClickListener listener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            ImageView imageView = findViewById(view.getId()); //누른 이미지 id값 가져오기.
            Toast.makeText(MainActivity.this, "good", Toast.LENGTH_SHORT).show();
            //Toast.makeText(MainActivity.this, imageView.getDrawable()+"", Toast.LENGTH_SHORT).show();

            Toast.makeText(MainActivity.this, "good", Toast.LENGTH_SHORT).show();
                Collections.shuffle(list);
        }
    };
}
