package com.example.digital_society;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.Myclass> {

    Context context;
    List<Register_Member_Model> modelList;


    public MyAdapter(Context context, List<Register_Member_Model> modelList) {
        this.context = context;
        this.modelList = modelList;
    }

    @NonNull
    @Override
    public Myclass onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_layout,parent,false);
        return new Myclass(v);
    }

    @Override
    public void onBindViewHolder(@NonNull Myclass holder, int position) {

        String memname = modelList.get(position).getFirstname()+" "+ modelList.get(position).getLastname();
        holder.show_mem_name.setText(memname);
        holder.show_mem_flatno.setText("Apartment No: " +modelList.get(position).getFlat_no());
        holder.show_housemem.setText("No. of House Members: " +modelList.get(position).getNo_of_members());

        holder.relative.startAnimation(AnimationUtils.loadAnimation(holder.itemView.getContext(),R.anim.recycler_anim));
    }

    @Override
    public int getItemCount() {
        return modelList.size();
    }

    class Myclass extends RecyclerView.ViewHolder
    {
        TextView show_mem_name,show_mem_flatno,show_housemem;
        RelativeLayout relative;

        public Myclass(@NonNull View itemView)
        {
            super(itemView);

            show_mem_name = itemView.findViewById(R.id.show_mem_name);
            show_mem_flatno = itemView.findViewById(R.id.show_mem_flatno);
            show_housemem = itemView.findViewById(R.id.show_housemem);

            relative = itemView.findViewById(R.id.relative);
        }
    }
}
