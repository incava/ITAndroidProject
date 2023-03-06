package com.incava.ex49movie;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.RecyclerView;

import android.app.backup.BackupDataInputStream;
import android.content.res.XmlResourceParser;
import android.os.Bundle;
import android.widget.Toast;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ArrayList<MovieItem> movieItems = new ArrayList<>();
    Myadapter myadapter;
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        movieItems.add(new MovieItem("1","avartar","2023-02-15","150만"));
        movieItems.add(new MovieItem("2","aaaaaaa","2023-02-14","160만"));

        recyclerView = findViewById(R.id.recycler);
        myadapter = new Myadapter(this, movieItems);
        recyclerView.setAdapter(myadapter);
        findViewById(R.id.btn).setOnClickListener(view -> clickBtn());
    }
    void clickBtn() {
        //영화진흥위원회 openApi 정보 가져와서 recyclerview에 보여주기.
        new Thread(){
            @Override
            public void run() {
                String address = "http://www.kobis.or.kr/kobisopenapi/webservice/rest/boxoffice/searchDailyBoxOfficeList.xml";
                try {
                    URL url = new URL(address);
                    InputStream is = url.openStream();
                    InputStreamReader isr = new InputStreamReader(is);
                    XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
                    XmlPullParser xpp = factory.newPullParser();
                    xpp.setInput(isr);
                    MovieItem movieItem = null;
                    int xppType = xpp.getEventType();
                    while (xppType!=XmlPullParser.END_DOCUMENT){
                        switch (xppType){
                            case XmlPullParser.START_DOCUMENT:
                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        Toast.makeText(MainActivity.this, xpp.getName()+"", Toast.LENGTH_SHORT).show();
                                        Toast.makeText(MainActivity.this, "파싱 시작!", Toast.LENGTH_SHORT).show();
                                    }
                                });
                                break;
                            case XmlPullParser.START_TAG:
                                String tagName= xpp.getName();
                                if (tagName.equals("dailyBoxOffice")){
                                    movieItem = new MovieItem();
                                }
                                else if (tagName.equals("rank")){
                                    xpp.next();
                                    movieItem.rank = xpp.getText();
                                }
                                else if (tagName.equals("movieNm")){
                                    xpp.next();
                                    movieItem.movieNm = xpp.getText();
                                }
                                else if (tagName.equals("openDt")){
                                    xpp.next();
                                    movieItem.openDt = xpp.getText();
                                }
                                else if (tagName.equals("audiAcc")){
                                    xpp.next();
                                    movieItem.audiAcc = xpp.getText();
                                }
                                break;
                            case XmlPullParser.END_TAG:
                                String tagName2= xpp.getName();
                                if (tagName2.equals("dailyBoxOffice")){
                                    movieItems.add(movieItem);
                                }
                                break;
                        }
                        xppType = xpp.next();
                    }
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(MainActivity.this, "눌러짐", Toast.LENGTH_SHORT).show();
                            myadapter.notifyDataSetChanged();
                        }
                    });
                } catch (MalformedURLException e) {
                    throw new RuntimeException(e);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                } catch (XmlPullParserException e) {
                    throw new RuntimeException(e);
                }

            }
        }.start();



    }

}