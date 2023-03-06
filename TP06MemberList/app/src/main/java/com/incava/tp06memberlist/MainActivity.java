package com.incava.tp06memberlist;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Button dialog_btn_add, dialog_btn_cancel;
    EditText dialog_et_name;
    String dialog_string_nation, dialog_string_gender, dialog_string_name;

    RadioGroup dialog_rg;


    Spinner dialog_spinner;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.member_option,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        //todo 눌릴때 실행.
        if (item.getItemId() == R.id.menu_search) {
            //todo search
        }
        else if(item.getItemId() == R.id.menu_add){
            //todo add
            AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
            builder.setView(R.layout.add_dialog);
            AlertDialog dialog = builder.create();
            dialog.show();

            dialog_btn_add = dialog.findViewById(R.id.btn_add);
            dialog_btn_cancel = dialog.findViewById(R.id.btn_cancel);

            dialog_btn_add.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    dialog_rg = dialog.findViewById(R.id.rg_gender);
                    switch (dialog_rg.getCheckedRadioButtonId()){
                        case R.id.rb_male:
                            dialog_string_gender = "MALE";
                    }
                    dialog_string_gender = dialog_rb.getText().toString();

                    dialog_et_name = dialog.findViewById(R.id.et_name);
                    dialog_string_name = dialog_et_name.getText().toString();

                    dialog_spinner = dialog.findViewById(R.id.spinner_nation);
                    dialog_string_nation = dialog_spinner.getSelectedItem().toString();
                    Toast.makeText(MainActivity.this, dialog_string_name+dialog_string_nation, Toast.LENGTH_SHORT).show();
                }
            });

            dialog_btn_cancel.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    dialog.cancel();
                }
            });
        }
        return super.onOptionsItemSelected(item);
    }


}
