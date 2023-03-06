package com.incava.ex48openapi;


import androidx.appcompat.app.AppCompatActivity;
import android.content.res.Resources;
import android.content.res.XmlResourceParser;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {
    TextView tv;
    Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tv = findViewById(R.id.tv);
        btn = findViewById(R.id.btn);
        btn.setOnClickListener(view->clickBtn());
    }


    void clickBtn(){
        Resources res = getResources();

        XmlResourceParser xrp = res.getXml(R.xml.movies);
        try {
            xrp.next();
            int eventType = xrp.getEventType();
            StringBuilder buffer = new StringBuilder();
            while(eventType!=XmlResourceParser.END_DOCUMENT){
                switch (eventType){
                    case XmlResourceParser.START_DOCUMENT:
                        buffer.append("-- 파싱 작업을 시작합니다. -- \n\n");
                        break;
                    case XmlResourceParser.START_TAG:
                        break;
                    case XmlResourceParser.END_TAG:
                        buffer.append("\n");
                        break;
                    case XmlResourceParser.TEXT:
                        buffer.append(xrp.getText());
                        break;
                }
                eventType = xrp.next();
            }
            buffer.append("-- 파싱 작업을  완료했습니다. --");
            tv.setText(buffer.toString());



        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (XmlPullParserException e) {
            throw new RuntimeException(e);
        }
    }


}