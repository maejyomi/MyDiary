package com.example.mydiary;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

class NameList{
    String name;
    public NameList(String name){
        this.name = name;
    }
    public String getName(){
        return name;
    }
}

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder>{

    public class MyViewHolder extends RecyclerView.ViewHolder{

        TextView list_text;

        MyViewHolder(View view){
            super(view);
            list_text = view.findViewById(R.id.list_text);
        }
    }

    @Override
    public MyAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_view,parent,false);
        return new MyViewHolder(view);
    }

    public ArrayList<NameList> myNameList;
    MyAdapter(ArrayList<NameList> names){
        this.myNameList = names;
    }

    @Override
    public void onBindViewHolder(MyAdapter.MyViewHolder holder, final int position){
        MyViewHolder myViewHolder = (MyViewHolder) holder;
        myViewHolder.list_text.setText(myNameList.get(position).getName());
        myViewHolder.list_text.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Context context = view.getContext();
                Intent intent = new Intent(context.getApplicationContext(),DiaryReadActivity.class);
                intent.putExtra("values",position);
                context.startActivity(intent);
            }
        });
    }
    @Override
    public int getItemCount(){
        return myNameList.size();
    }
}

