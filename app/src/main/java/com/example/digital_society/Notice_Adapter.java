package com.example.digital_society;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class Notice_Adapter extends RecyclerView.Adapter<Notice_Adapter.MyClass> {

    Context context;
    List<Notice_Model> modelList;

    public Notice_Adapter(Context context, List<Notice_Model> modelList) {
        this.context = context;
        this.modelList = modelList;
    }

    @NonNull
    @Override
    public MyClass onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.notice_row_layout,parent,false);
        return new MyClass(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyClass holder, int position) {

        holder.show_notice.setText(modelList.get(position).getNotice());
        holder.cardv.startAnimation(AnimationUtils.loadAnimation(holder.itemView.getContext(),R.anim.recycler_anim));

    }

    @Override
    public int getItemCount() {
        return modelList.size();
    }

    public class MyClass extends RecyclerView.ViewHolder {

        TextView show_notice;
        CardView cardv;

        public MyClass(@NonNull View itemView) {
            super(itemView);

            show_notice = itemView.findViewById(R.id.show_notice);
            cardv = itemView.findViewById(R.id.cardv);

        }
    }
}
