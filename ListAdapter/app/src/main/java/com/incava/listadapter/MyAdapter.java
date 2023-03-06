package com.incava.listadapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class MyAdapter extends BaseAdapter {

    Context context;
    ArrayList<Item> items;

    public MyAdapter(Context context, ArrayList<Item>items){
        this.context = context;
        this.items = items;

    }


    @Override
    public int getCount() {
        return items.size();
    }

    @Override
    public Object getItem(int i) {
        return items.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        //재활용할 view가 없는가?
        if(view == null) {
            //1. create view : xml 모양으로 view 객체를 생성
            LayoutInflater layoutInflater = LayoutInflater.from(context);
            //2. bind view : 생성된 View객체 안에 정보 값들을 설정(연결)
            view = layoutInflater.inflate(R.layout.listview_item,null);
            // root는 어디에 붙일것인지를 물어보는 곳, 하지만 자동으로 해주므로 null처리.
        }

        //2. bind view : 생성된 view 객체안에 정보 값들을 설정
        // 이 메서드의 첫번째 파라미터 i  - 현재 만들어야 할 번째 인덱스 번호
        // 현재번째 데이터 얻어오기
        Item item = items.get(i);

        //아이템뷰 안에 있는 자식뷰들을 참조하기.

        ImageView iv = view.findViewById(R.id.iv);
        TextView tvName = view.findViewById(R.id.tv_name);
        TextView tvNation = view.findViewById(R.id.tv_nation);

        tvName.setText(item.name);
        tvNation.setText(item.nation);
        iv.setImageResource(item.imgId);


        return view;
    }
}
