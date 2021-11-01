package com.example.fitnessapp.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fitnessapp.Activity.B18;
import com.example.fitnessapp.Activity.Bfri18;
import com.example.fitnessapp.Activity.Bsat18;
import com.example.fitnessapp.Activity.Bsun18;
import com.example.fitnessapp.Activity.Bthrus18;
import com.example.fitnessapp.Activity.Btues18;
import com.example.fitnessapp.Activity.Bwed18;
import com.example.fitnessapp.Activity.Friday;
import com.example.fitnessapp.Activity.Monday;
import com.example.fitnessapp.Activity.Saturday;
import com.example.fitnessapp.Activity.Sunday_45;
import com.example.fitnessapp.Activity.Thursday;
import com.example.fitnessapp.Activity.Tuesday;
import com.example.fitnessapp.Activity.Wednesday;
import com.example.fitnessapp.Domain.WeekDaysDomain;
import com.example.fitnessapp.R;

import java.util.ArrayList;

public class BelowEighteenAdapter extends RecyclerView.Adapter<BelowEighteenAdapter.ViewHolder> {

    ArrayList<WeekDaysDomain> weekdays;
    Context context;
    public BelowEighteenAdapter(ArrayList<WeekDaysDomain> weekdays, Context context) {
        this.weekdays = weekdays;
        this.context = context;
    }

    @NonNull
    @Override
    public BelowEighteenAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.week_model, parent, false);
        return new ViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull BelowEighteenAdapter.ViewHolder holder, int position) {
        holder.weekText.setText(weekdays.get(position).getDayText());

        switch (position) {
            case 0: {
                holder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent =  new Intent(context, B18.class);
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
                        Intent intent =  new Intent(context, Btues18.class);
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
                        Intent intent =  new Intent(context, Bwed18.class);
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
                        Intent intent =  new Intent(context, Bthrus18.class);
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
                        Intent intent =  new Intent(context, Bfri18.class);
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
                        Intent intent =  new Intent(context, Bsat18.class);
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
                        Intent intent =  new Intent(context, Bsun18.class);
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
        return weekdays.size();
    }

}
