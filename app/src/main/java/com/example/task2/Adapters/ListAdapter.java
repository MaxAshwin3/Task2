package com.example.task2.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.task2.Model.ModelClass;
import com.example.task2.R;

import java.util.ArrayList;

public class ListAdapter extends RecyclerView.Adapter<ListAdapter.ViewHolder> {

    ArrayList<ModelClass> List;
     Context context;

    public ListAdapter(ArrayList<ModelClass> arrayList, Context context) {
        List = arrayList;
        this.context = context;
    }

    @NonNull
    @Override
    public ListAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.modified_list,parent,false);
        ViewHolder viewholder = new ViewHolder(view);
        return viewholder;
    }


    @Override
    public void onBindViewHolder(@NonNull ListAdapter.ViewHolder holder, int position) {

        ModelClass modelClass = List.get(position);
        holder.cardFName.setText(modelClass.getFname());
        holder.cardLName.setText(modelClass.getLname());
        holder.cardEmail.setText(modelClass.getEmail());

    }


    @Override
    public int getItemCount() {
        return List.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

          TextView cardFName , cardLName , cardEmail;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            cardFName = itemView.findViewById(R.id.cardFName);
            cardLName = itemView.findViewById(R.id.cardLName);
            cardEmail = itemView.findViewById(R.id.cardEmail);


        }
    }
}
