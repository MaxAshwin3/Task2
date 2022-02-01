package com.example.task2.Fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.task2.Adapters.ListAdapter;
import com.example.task2.Database.DbHelper;
import com.example.task2.R;


public class FirstFragment extends Fragment {



    RecyclerView maleRecyclerView;
    ListAdapter adapter;


    public FirstFragment() {
        // Required empty public constructor
    }





    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_first, container, false);
        maleRecyclerView = view.findViewById(R.id.maleRecyclerView);

        DbHelper dbHelper = new DbHelper(getContext());
        adapter = new ListAdapter(dbHelper.getUserData("Male"),getContext());

        maleRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        maleRecyclerView.setAdapter(adapter);


        return view;
    }
}