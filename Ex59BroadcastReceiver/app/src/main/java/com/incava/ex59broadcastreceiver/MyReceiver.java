package com.incava.ex59broadcastreceiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

// 안드로이드 4대 컴포넌트 클래스들은 반드시 AndroidManifest.xml에 등록해야만 함.
public class MyReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        Toast.makeText(context, "리시버", Toast.LENGTH_SHORT).show();
    }
}
