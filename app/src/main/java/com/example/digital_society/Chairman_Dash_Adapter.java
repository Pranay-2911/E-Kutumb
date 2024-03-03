package com.example.digital_society;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import java.lang.invoke.ConstantCallSite;
import java.util.List;

public class Chairman_Dash_Adapter extends RecyclerView.Adapter<Chairman_Dash_Adapter.MyClass> {

    Context context;
    List<Chairman_Dash_Model> itemList1;

    public Chairman_Dash_Adapter(Context context, List<Chairman_Dash_Model> itemList1) {
        this.context = context;
        this.itemList1 = itemList1;
    }

    @NonNull
    @Override
    public MyClass onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.chairman_dash_row_layout,parent,false);
        return new MyClass(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyClass holder, int position) {

        Chairman_Dash_Model my = itemList1.get(position);

        holder.chairman_img_notification.setImageResource(my.getChairman_img());
        holder.chairman_tv_notification.setText(my.getChairman_title());

        holder.chairman_cardview.startAnimation(AnimationUtils.loadAnimation(holder.itemView.getContext(),R.anim.recycler_anim));

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (my.getChairman_title().equals("Add Notice"))
                {
                    AppCompatActivity appCompatActivity = (AppCompatActivity) view.getContext();

                    Add_Notice add_Notice = new Add_Notice();
                    appCompatActivity.getSupportFragmentManager().beginTransaction().replace(R.id.draw_layout, add_Notice)
                            .addToBackStack("Add Notice").commit();

                }
                else if(my.getChairman_title().equals("Add Members"))
                {
                    AppCompatActivity appCompatActivity = (AppCompatActivity) view.getContext();

                    Add_Members_frag add_members_frag = new Add_Members_frag();
                    appCompatActivity.getSupportFragmentManager().beginTransaction().replace(R.id.draw_layout, add_members_frag)
                            .addToBackStack("Add Members").commit();
                }
                else if(my.getChairman_title().equals("Show Members"))
                {
                    AppCompatActivity appCompatActivity = (AppCompatActivity) view.getContext();

                    Show_Members_frag show_members_frag = new Show_Members_frag();
                    appCompatActivity.getSupportFragmentManager().beginTransaction().replace(R.id.draw_layout, show_members_frag)
                            .addToBackStack("Show Members").commit();
                }
                else if(my.getChairman_title().equals("Show Notice"))
                {
                    AppCompatActivity appCompatActivity = (AppCompatActivity) view.getContext();

                    Chairman_Show_Notice chairman_show_notice = new Chairman_Show_Notice();
                    appCompatActivity.getSupportFragmentManager().beginTransaction().replace(R.id.draw_layout, chairman_show_notice)
                            .addToBackStack("Show Notice").commit();
                }
                else if(my.getChairman_title().equals("Compalint"))
                {
                    Toast.makeText(context, "Coming Soon!", Toast.LENGTH_SHORT).show();
                }
                else if(my.getChairman_title().equals("Add Events"))
                {
                    Toast.makeText(context, "Coming Soon!", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }

    @Override
    public int getItemCount() {
        return itemList1.size();
    }

    public class MyClass extends RecyclerView.ViewHolder {

        ConstraintLayout chairman_cardview;
        ImageView chairman_img_notification;
        TextView chairman_tv_notification;

        public MyClass(@NonNull View itemView) {
            super(itemView);

            chairman_img_notification = itemView.findViewById(R.id.chairman_img_notification);
            chairman_tv_notification = itemView.findViewById(R.id.chairman_tv_notification);
            chairman_cardview = itemView.findViewById(R.id.chairman_cardview);

        }
    }
}
