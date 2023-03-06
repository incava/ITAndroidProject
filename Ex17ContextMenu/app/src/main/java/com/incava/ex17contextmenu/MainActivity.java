package com.incava.ex17contextmenu;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.PopupMenu;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Button btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn = findViewById(R.id.btn);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PopupMenu popupMenu = new PopupMenu(MainActivity.this,btn);
                MenuInflater menuInflater = getMenuInflater();
                menuInflater.inflate(R.menu.popup,popupMenu.getMenu());
                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem menuItem) {
                        if (menuItem.getItemId() == R.id.info) Toast.makeText(MainActivity.this,"info",Toast.LENGTH_SHORT).show();
                        else if(menuItem.getItemId() == R.id.delete) Toast.makeText(MainActivity.this, "delete", Toast.LENGTH_SHORT).show();
                        else if(menuItem.getItemId() == R.id.modify) Toast.makeText(MainActivity.this, "modify", Toast.LENGTH_SHORT).show();
                        return false;
                    }
                });
                popupMenu.show();
            Toast.makeText(MainActivity.this,"dd",Toast.LENGTH_SHORT).show();
            }
        });
        registerForContextMenu(btn);
    }
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        //menu폴더안에 context.xml파일을 읽어서 메뉴아이템객체로  생성.
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.context,menu);
        super.onCreateContextMenu(menu, v, menuInfo);
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        if(item.getItemId()==R.id.menu_save) Toast.makeText(MainActivity.this,"save",Toast.LENGTH_SHORT).show();
        else if(item.getItemId()==R.id.menu_delete) Toast.makeText(MainActivity.this,"delete",Toast.LENGTH_SHORT).show();
        return super.onContextItemSelected(item);
    }
}