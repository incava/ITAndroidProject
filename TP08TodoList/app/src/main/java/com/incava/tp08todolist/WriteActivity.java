
package com.incava.tp08todolist;

import androidx.activity.OnBackPressedCallback;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

public class WriteActivity extends AppCompatActivity {

    Button btnCancel,btnSubmit;
    TextInputEditText etName,etNick, etTheme, etBody;
    TextView tvWatcher;
    TextInputLayout tilBody;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_write);
        etName = findViewById(R.id.et_name);
        etNick = findViewById(R.id.et_nick);
        etTheme = findViewById(R.id.et_theme);
        etBody = findViewById(R.id.et_body);
        btnSubmit =findViewById(R.id.btn_submit);
        btnCancel = findViewById(R.id.btn_cancel);
        tilBody = findViewById(R.id.til_body);
        tvWatcher = findViewById(R.id.tv_watcher);

        etBody.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                tvWatcher.setText("글자수 : "+ i1 + "/"+tilBody.getCounterMaxLength());
            }

            @Override
            public void afterTextChanged(Editable et) {

            }
        });



        btnSubmit.setOnClickListener(view -> {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setMessage("작성을 완료하시겠습니까?")
                    .setPositiveButton("네", (dialogInterface, i) -> {
                        Intent intent = getIntent();
                        intent.putExtra("name",etName.getText().toString());
                        intent.putExtra("nick",etNick.getText().toString());
                        intent.putExtra("theme",etTheme.getText().toString());
                        intent.putExtra("body",etBody.getText().toString());
                        setResult(RESULT_OK,intent);
                        finish();
                    })
                    .setNegativeButton("아니요", (dialogInterface, i) -> {
                    })
                    .show();

        });
        btnCancel.setOnClickListener(view -> {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setMessage("취소할건가요?")
                    .setPositiveButton("네", (dialogInterface, i) -> {
                        finish();
                    })
                    .setNegativeButton("아니요", (dialogInterface, i) -> {
                    })
                    .show();
        });

    }
}