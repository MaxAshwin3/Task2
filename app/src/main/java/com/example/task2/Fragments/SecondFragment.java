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


public class SecondFragment extends Fragment {

    RecyclerView femaleRecyclerView;
    ListAdapter adapter;


    public SecondFragment() {
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

        View v = inflater.inflate(R.layout.fragment_second, container, false);
        femaleRecyclerView = v.findViewById(R.id.femaleRecyclerView);

        DbHelper dbHelper = new DbHelper(getContext());
        adapter = new ListAdapter(dbHelper.getUserData("Female"), getContext());

        femaleRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        femaleRecyclerView.setAdapter(adapter);

        return v;
    }
}