package com.example.digital_society;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class Mem_Dash_Adapter extends RecyclerView.Adapter<Mem_Dash_Adapter.Mem_Class> {

    Context context;
    ArrayList<Mem_Dash_Model> modelList;

    public Mem_Dash_Adapter(Context context, ArrayList<Mem_Dash_Model> modelList) {
        this.context = context;
        this.modelList = modelList;
    }

    @NonNull
    @Override
    public Mem_Class onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.mem_dash_row_layout,parent,false);
        return new Mem_Class(v);
    }

    @Override
    public void onBindViewHolder(@NonNull Mem_Class holder, @SuppressLint("RecyclerView") int position) {

        Mem_Dash_Model model = modelList.get(position);

        holder.img_notification.setImageResource(model.getImg());
        holder.tv_notification.setText(model.getTitle());

        holder.cardview.startAnimation(AnimationUtils.loadAnimation(holder.itemView.getContext(),R.anim.recycler_anim));

        holder.itemView.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {

               if (model.getTitle().equals("E-Notice"))
               {
                   AppCompatActivity appCompatActivity = (AppCompatActivity) view.getContext();

                   mem_Notice memNotice = new mem_Notice();
                   appCompatActivity.getSupportFragmentManager().beginTransaction().replace(R.id.drawer_layout, memNotice)
                           .addToBackStack("E-Notice").commit();
               }
               else if (model.getTitle().equals("View Members"))
               {
                   AppCompatActivity appCompatActivity = (AppCompatActivity) view.getContext();

                   view_mem viewMem = new view_mem();
                   appCompatActivity.getSupportFragmentManager().beginTransaction().replace(R.id.drawer_layout, viewMem)
                           .addToBackStack("View Members").commit();
               }
               else if (model.getTitle().equals("Complaint"))
               {
                   AppCompatActivity appCompatActivity = (AppCompatActivity) view.getContext();

                   mem_complaint memComplaint = new mem_complaint();
                   appCompatActivity.getSupportFragmentManager().beginTransaction().replace(R.id.drawer_layout, memComplaint)
                           .addToBackStack("Complaint").commit();
               }
               else if (model.getTitle().equals("Show Events"))
               {
                   AppCompatActivity appCompatActivity = (AppCompatActivity) view.getContext();

                   mem_events memEvents = new mem_events();
                   appCompatActivity.getSupportFragmentManager().beginTransaction().replace(R.id.drawer_layout, memEvents)
                           .addToBackStack("Show Events").commit();
               }
               else if (model.getTitle().equals("Payments"))
               {
                   AppCompatActivity appCompatActivity = (AppCompatActivity) view.getContext();

                   mem_payments memPayments = new mem_payments();
                   appCompatActivity.getSupportFragmentManager().beginTransaction().replace(R.id.drawer_layout, memPayments)
                           .addToBackStack("Payments").commit();
               }
               else if (model.getTitle().equals("Vote"))
               {
                   AppCompatActivity appCompatActivity = (AppCompatActivity) view.getContext();

                   mem_vote memVote = new mem_vote();
                   appCompatActivity.getSupportFragmentManager().beginTransaction().replace(R.id.drawer_layout, memVote)
                           .addToBackStack("Vote").commit();
               }
           }
       });
    }

    @Override
    public int getItemCount() {
        return modelList.size();
    }

    class Mem_Class extends RecyclerView.ViewHolder
    {
        ImageView img_notification;
        TextView tv_notification;
        ConstraintLayout cardview;

        public Mem_Class(@NonNull View itemView)
        {
            super(itemView);

            img_notification = itemView.findViewById(R.id.img_notification);
            tv_notification = itemView.findViewById(R.id.tv_notification);
            cardview = itemView.findViewById(R.id.cardview);
        }
    }
}
