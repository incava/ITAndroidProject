package com.incava.ex90firebasechatting;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;

import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;
import com.incava.ex90firebasechatting.databinding.ActivityChattingBinding;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

public class ChattingActivity extends AppCompatActivity {

    ActivityChattingBinding binding;
    FirebaseFirestore firestore;
    CollectionReference chatRef;
    String chatName = "Chatting";

    ArrayList<MessageItem> messageItems = new ArrayList<>();

    MessageAdapter messageAdapter;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityChattingBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        getSupportActionBar().setTitle(chatName);
        getSupportActionBar().setSubtitle("상대방 이름");
        // firebase Firestore 관리객체 및 참조객체 소환
        firestore = FirebaseFirestore.getInstance();
        chatRef = firestore.collection(chatName);

        chatRef.addSnapshotListener((value, error) -> {
            //변경된 Document만 찾아달라고 요청.
            List<DocumentChange> documentChanges = value.getDocumentChanges();
            for(DocumentChange documentChange : documentChanges){
                //변경된 문서내역중에서 데이터를 촬영한 SnapShot을 얻어오기
                DocumentSnapshot snapshot = documentChange.getDocument();
                Map<String, Object> msg = snapshot.getData();
                String name =  msg.get("name").toString();
                String message = msg.get("message").toString();
                String profileUrl = msg.get("profileUrl").toString();
                String time = msg.get("time").toString();
                messageItems.add(new MessageItem(name,message,profileUrl,time));
                messageAdapter.notifyItemChanged(messageItems.size()-1);
                binding.recycler.scrollToPosition(messageItems.size()-1);
            }

            Toast.makeText(this, ""+messageItems.size(), Toast.LENGTH_SHORT).show();

        });
        binding.btn.setOnClickListener(v -> clickSend());
        messageAdapter = new MessageAdapter(this,messageItems);
        binding.recycler.setAdapter(messageAdapter);
    }

    void clickSend(){
        String nickName = G.nickName;
        String message = binding.et.getText().toString();
        String profileUrl = G.profileUrl;
        //메세지 작성 시간을 문자열 [시 : 분]
        Calendar calendar = Calendar.getInstance();
        String time = calendar.get(Calendar.HOUR_OF_DAY) + ":" + calendar.get(Calendar.MINUTE);
        MessageItem item = new MessageItem(nickName, message, profileUrl, time);
        chatRef.document("MSG_" + System.currentTimeMillis()).set(item);
        binding.et.setText("");
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(),0); // getCurrentFocus-> 포커스 view가져오기.
        // getWindowToken -> 토큰가져오기.
    }

}