package com.incava.ex41bottomnavigation;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class Tab3Fragment extends Fragment {

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        items.add(new Tab3RecyclerItem("PARIS",R.drawable.sydney));
        items.add(new Tab3RecyclerItem("PARIS",R.drawable.sydney));
        items.add(new Tab3RecyclerItem("PARIS",R.drawable.sydney));
        items.add(new Tab3RecyclerItem("PARIS",R.drawable.sydney));
        items.add(new Tab3RecyclerItem("PARIS",R.drawable.sydney));
        items.add(new Tab3RecyclerItem("PARIS",R.drawable.sydney));
        items.add(new Tab3RecyclerItem("PARIS",R.drawable.sydney));
        items.add(new Tab3RecyclerItem("PARIS",R.drawable.sydney));
        items.add(new Tab3RecyclerItem("PARIS",R.drawable.sydney));
        items.add(new Tab3RecyclerItem("PARIS",R.drawable.sydney));
        items.add(new Tab3RecyclerItem("PARIS",R.drawable.sydney));

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_tab3, container, false);
        return view;
    }

    ArrayList<Tab3RecyclerItem> items = new ArrayList<>();
    Tab3RecyclerAdapter adapter,adapter2,adapter3;
    RecyclerView recyclerView;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        recyclerView = view.findViewById(R.id.recyclerview1);
        adapter = new Tab3RecyclerAdapter(getActivity(),items);
        recyclerView.setAdapter(adapter);

        recyclerView = view.findViewById(R.id.recyclerview2);
        adapter2 = new Tab3RecyclerAdapter(getActivity(),items);
        recyclerView.setAdapter(adapter2);

        recyclerView = view.findViewById(R.id.recyclerview3);
        adapter3 = new Tab3RecyclerAdapter(getActivity(),items);
        recyclerView.setAdapter(adapter3);



    }
}
