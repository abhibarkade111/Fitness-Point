package com.example.fitnessapp.Adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.fitnessapp.Domain.UserPost;
import com.example.fitnessapp.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class PostAdapter extends RecyclerView.Adapter<PostAdapter.ViewHolder> {

    ArrayList<UserPost> list;
    Context context;

    public PostAdapter(ArrayList<UserPost> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       View view = LayoutInflater.from(context).inflate(R.layout.watch_post, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        UserPost usersPost = list.get(position);

//        if(usersPost.getUserName()!=null && !usersPost.getUserName().isEmpty()){
//            holder.userName.setText(usersPost.getUserName());
//        }
//        else {
//            holder.userName.setText("Anonymous");
//        }
        Log.d("TAG","userDetails"+ list.size());
        holder.userName.setText(usersPost.getUserName());
        if(usersPost.getProfilePic()!=null && !usersPost.getProfilePic().isEmpty()){
            Picasso.get().load(usersPost.getProfilePic()).placeholder(R.drawable.user).into(holder.userImage);
        }
        else {
            holder.userImage.setImageResource(R.drawable.user);
        }
        Picasso.get().load(usersPost.getPostUrl()).placeholder(R.drawable.watch_post_default).into(holder.postImage);
        holder.userDesc.setText(usersPost.getDesc());

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView userImage, postImage;
        TextView userName,userDesc;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            userImage = itemView.findViewById(R.id.userProfilePostWatch);
            postImage = itemView.findViewById(R.id.postImage);
            userName = itemView.findViewById(R.id.userNamePost);
            userDesc  = itemView.findViewById(R.id.userDesc);


        }
    }
}
