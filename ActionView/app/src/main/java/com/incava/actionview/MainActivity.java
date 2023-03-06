package com.incava.actionview;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText actionViewET;

    Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.option,menu);
        MenuItem item = menu.findItem(R.id.menu_action);
        LinearLayout layout = (LinearLayout)item.getActionView();
        actionViewET = layout.findViewById(R.id.actionview_et);
        actionViewET.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                //키보드에서 어떤 키를 눌렀는지 가지고 이쓴ㄴ 변수 : 두번째 파라미터 i
                if(i == EditorInfo.IME_ACTION_SEARCH){
                    String message = actionViewET.getText().toString();
                    Toast.makeText(MainActivity.this, "검색어 : " + message, Toast.LENGTH_SHORT).show();
                }
                return false;
            }
        });

        return super.onCreateOptionsMenu(menu);
    }
}