package com.example.fitnessapp.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.fitnessapp.Activity.Aerobic;
import com.example.fitnessapp.Activity.Cardio;
import com.example.fitnessapp.Activity.Streching;
import com.example.fitnessapp.Activity.Yoga;
import com.example.fitnessapp.Domain.CategoryDomain;
import com.example.fitnessapp.R;

import java.util.ArrayList;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.ViewHolder> {
    ArrayList<CategoryDomain> categoryDomains;
    Context context;

    public CategoryAdapter(ArrayList<CategoryDomain> categoryDomains, Context context) {
        this.categoryDomains = categoryDomains;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.viewholder_cat, parent, false);

        return new ViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.categoryName.setText(categoryDomains.get(position).getTitle());
        String picUrl = "";
        switch (position) {
            case 0: {
                picUrl = "yoga";
                holder.mainLayout.setBackground(ContextCompat.getDrawable(holder.itemView.getContext(), R.drawable.category_background1));
                holder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent =  new Intent(context, Yoga.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        context.startActivity(intent);
                    }
                });
                break;
            }
            case 1: {
                picUrl = "aerobic";
                holder.mainLayout.setBackground(ContextCompat.getDrawable(holder.itemView.getContext(), R.drawable.category_background2));
                holder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent =  new Intent(context, Aerobic.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        context.startActivity(intent);
                    }
                });
                break;
            }
            case 2: {
                picUrl = "cardioexercises";
                holder.mainLayout.setBackground(ContextCompat.getDrawable(holder.itemView.getContext(), R.drawable.category_background3));
                holder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent =  new Intent(context, Cardio.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        context.startActivity(intent);
                    }
                });
                break;
            }
            case 3: {
                picUrl = "flexibility";
                holder.mainLayout.setBackground(ContextCompat.getDrawable(holder.itemView.getContext(), R.drawable.category_background4));
                holder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent =  new Intent(context, Streching.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        context.startActivity(intent);
                    }
                });
                break;
            }

        }
        int drawableResourceId = holder.itemView.getContext().getResources().getIdentifier(picUrl, "drawable", holder.itemView.getContext().getPackageName());

        Glide.with(holder.itemView.getContext())
                .load(drawableResourceId)
                .into(holder.categoryPic);

    }


    @Override
    public int getItemCount() {
        return categoryDomains.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView categoryName;
        ImageView categoryPic;
        ConstraintLayout mainLayout;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            categoryName = itemView.findViewById(R.id.categoryName);
            categoryPic = itemView.findViewById(R.id.categoryPic);
            mainLayout = itemView.findViewById(R.id.mainLayout);
        }
    }
}
