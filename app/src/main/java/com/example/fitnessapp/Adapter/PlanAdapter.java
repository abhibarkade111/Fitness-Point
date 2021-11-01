package com.example.fitnessapp.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.fitnessapp.Activity.AboveMiddleHome;
import com.example.fitnessapp.Activity.Aerobic;
import com.example.fitnessapp.Activity.BelowEighteenHome;
import com.example.fitnessapp.Activity.Cardio;
import com.example.fitnessapp.Activity.DietMain;
import com.example.fitnessapp.Activity.MiddleAgeHome;
import com.example.fitnessapp.Activity.ShowDetailActivity;
import com.example.fitnessapp.Activity.Streching;
import com.example.fitnessapp.Activity.Yoga;
import com.example.fitnessapp.Domain.PlanDomain;
import com.example.fitnessapp.R;

import java.util.ArrayList;

public class PlanAdapter extends RecyclerView.Adapter<PlanAdapter.ViewHolder> {
    ArrayList<PlanDomain> planDomains;
    Context context;

    public PlanAdapter(ArrayList<PlanDomain> planDomains, Context context) {
        this.planDomains = planDomains;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.viewholder_popular, parent, false);

        return new ViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.title.setText(planDomains.get(position).getTitle());


        int drawableResourceId = holder.itemView.getContext().getResources().getIdentifier(planDomains.get(position).getPic(), "drawable", holder.itemView.getContext().getPackageName());

        Glide.with(holder.itemView.getContext())
                .load(drawableResourceId)
                .into(holder.pic);


        switch (position) {
            case 0: {
                holder.addBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent =  new Intent(context, BelowEighteenHome.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        context.startActivity(intent);
                    }
                });
                break;
            }
            case 1: {
                holder.addBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent =  new Intent(context, MiddleAgeHome.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        context.startActivity(intent);
                    }
                });
                break;
            }
            case 2: {
                holder.addBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent =  new Intent(context, AboveMiddleHome.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        context.startActivity(intent);
                    }
                });
                break;
            }
            case 3: {
                holder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent =  new Intent(context, DietMain.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        context.startActivity(intent);
                    }
                });
                break;
            }

        }

    }


    @Override
    public int getItemCount() {
        return planDomains.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView title;
        ImageView pic;
        TextView addBtn;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.title);
            pic = itemView.findViewById(R.id.pic);
            addBtn = itemView.findViewById(R.id.addBtn);
        }
    }
}
