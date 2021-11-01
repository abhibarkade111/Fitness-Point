package com.example.fitnessapp.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fitnessapp.Activity.Friday;
import com.example.fitnessapp.Activity.Friday_45;
import com.example.fitnessapp.Activity.Monday;
import com.example.fitnessapp.Activity.Monday_45;
import com.example.fitnessapp.Activity.Saturday;
import com.example.fitnessapp.Activity.Saturday_45;
import com.example.fitnessapp.Activity.SundayA;
import com.example.fitnessapp.Activity.Sunday_45;
import com.example.fitnessapp.Activity.Thursday;
import com.example.fitnessapp.Activity.Thursday_45;
import com.example.fitnessapp.Activity.Tuesday;
import com.example.fitnessapp.Activity.Tuesday_45;
import com.example.fitnessapp.Activity.Wednesday;

import com.example.fitnessapp.Activity.Wednesday_45;
import com.example.fitnessapp.Domain.WeekDaysDomain;
import com.example.fitnessapp.R;

import java.util.ArrayList;

public class AboveAgePlanAdapter extends RecyclerView.Adapter<AboveAgePlanAdapter.ViewHolder> {
    ArrayList<WeekDaysDomain> weekdays1;
    Context context;

    public AboveAgePlanAdapter(ArrayList<WeekDaysDomain> weekdays1, Context context) {
        this.weekdays1 = weekdays1;
        this.context = context;
    }

    @NonNull
    @Override
    public AboveAgePlanAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.week_model, parent, false);
        return new ViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull AboveAgePlanAdapter.ViewHolder holder, int position) {
        holder.weekText.setText(weekdays1.get(position).getDayText());

        switch (position) {
            case 0: {
                holder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent =  new Intent(context, Monday_45.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        context.startActivity(intent);
                    }
                });
                break;
            }
            case 1: {
                holder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent =  new Intent(context, Tuesday_45.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        context.startActivity(intent);
                    }
                });
                break;
            }
            case 2: {
                holder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent =  new Intent(context, Wednesday_45.class);
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
                        Intent intent =  new Intent(context, Thursday_45.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        context.startActivity(intent);
                    }
                });
                break;
            }
            case 4: {
                holder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent =  new Intent(context, Friday_45.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        context.startActivity(intent);
                    }
                });
                break;
            }
            case 5: {
                holder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent =  new Intent(context, Saturday_45.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        context.startActivity(intent);
                    }
                });
                break;
            }
            case 6: {
                holder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent =  new Intent(context, Sunday_45.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        context.startActivity(intent);
                    }
                });
                break;
            }

        }
    }
    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView weekText;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            weekText = itemView.findViewById(R.id.weekText);

        }
    }

    @Override
    public int getItemCount() {
        return weekdays1.size();
    }
}
