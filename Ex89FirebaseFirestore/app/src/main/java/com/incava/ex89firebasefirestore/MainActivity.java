package com.incava.ex89firebasefirestore;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.incava.ex89firebasefirestore.databinding.ActivityMainBinding;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.btnSave.setOnClickListener(v-> clickSave());
        binding.btnLoad.setOnClickListener(v-> clickLoad());

    }

    void clickSave(){
        String name = binding.etName.getText().toString();
        int age = Integer.parseInt(binding.etAge.getText().toString());
        String address = binding.etAddress.getText().toString();
        FirebaseFirestore firestore = FirebaseFirestore.getInstance();
        CollectionReference personRef = firestore.collection("person");

        Map<String,Object> person = new HashMap<>();
        person.put("name", name);
        person.put("age", age);
        person.put("address",address);
//        personRef.document().set(person).addOnSuccessListener(result -> {
//            Toast.makeText(this, "save", Toast.LENGTH_SHORT).show();
//        });

        personRef.add(person).addOnSuccessListener(result -> {
            Toast.makeText(this, "save", Toast.LENGTH_SHORT).show();
        });
    }

    void clickLoad(){
        FirebaseFirestore firestore = FirebaseFirestore.getInstance();
        CollectionReference personRef = firestore.collection("person");
        personRef.get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
            @Override
            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                //Collection 안에 여러개의 Document가 있기에
                StringBuffer buffer = new StringBuffer();
                for(QueryDocumentSnapshot snapshot :queryDocumentSnapshots){
                    Map<String,Object> person = snapshot.getData();
                    String name = person.get("name").toString();
                    int age = Integer.parseInt(person.get("age").toString());
                    String address = person.get("address").toString();
                    buffer.append(name+" : " + age + " : " + address + "\n");
                }
                binding.tv.setText(buffer.toString());
            }
        });

//        personRef.whereEqualTo("name","sam").addSnapshotListener((value, error) -> {
//
//        });
    }



}