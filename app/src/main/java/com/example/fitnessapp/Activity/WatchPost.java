package com.example.fitnessapp.Activity;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.fitnessapp.Adapter.PostAdapter;
import com.example.fitnessapp.Domain.User;
import com.example.fitnessapp.Domain.UserPost;
import com.example.fitnessapp.R;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class WatchPost extends AppCompatActivity {
    private RecyclerView.Adapter adapter;
    private RecyclerView recyclerViewList;
    private TextView totalFeeTxt, taxTxt, deliveryTxt, totalTxt, emptyTxt, checkout,uploadText;
    private double tax;
    private ScrollView scrollView;
    private ImageView uploadImageIcon;

    private FirebaseUser user;
    private FirebaseDatabase database;
    private DatabaseReference reference;
    private String userID;
    FirebaseStorage storage;
    FirebaseAuth auth;

    ArrayList<UserPost> list = new ArrayList<>();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_watch_post);
        initView();

        PostAdapter adapter = new PostAdapter(list, getBaseContext());
        recyclerViewList.setAdapter(adapter);

        storage = FirebaseStorage.getInstance();
        auth = FirebaseAuth.getInstance();
        database =FirebaseDatabase.getInstance();

        user = FirebaseAuth.getInstance().getCurrentUser();
        userID = user.getUid();





        LinearLayoutManager layoutManager = new LinearLayoutManager(getBaseContext());
        recyclerViewList.setLayoutManager(layoutManager);
//        recyclerViewList.getLayoutManager().setMeasurementCacheEnabled(false);


        database.getReference().child("Users_post").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                list.clear();
                for(DataSnapshot dataSnapshot : snapshot.getChildren()){
                    UserPost usersPost = dataSnapshot.getValue(UserPost.class);
                    usersPost.setUserId(dataSnapshot.getKey());
                    list.add(usersPost);

                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });





        bottomNavigation();
    }





    private void bottomNavigation() {
        FloatingActionButton floatingActionButton = findViewById(R.id.card_btn);
        LinearLayout homeBtn = findViewById(R.id.homeWatch);
        LinearLayout profileBtn = findViewById(R.id.profileWatch);
        LinearLayout supportBtn = findViewById(R.id.supportWatch);
        LinearLayout bmiBtn = findViewById(R.id.bmiWatch);

        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(WatchPost.this, WatchPost.class));
            }
        });

        homeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(WatchPost.this, MainActivity.class));
                finish();
            }
        });
        profileBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(WatchPost.this, user_profile.class));
            }
        });
        supportBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(WatchPost.this,SuportAssistant.class));
            }
        });
        bmiBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(WatchPost.this,bmi_calculator.class));
            }
        });


        uploadImageIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(WatchPost.this,upload_post.class));
            }
        });

       uploadText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(WatchPost.this,upload_post.class));
            }
        });
    }




    private void initView() {
        recyclerViewList = findViewById(R.id.recyclerview);
        uploadImageIcon = findViewById(R.id.uploadImageIcon);
        uploadText = findViewById(R.id.uploadText);


    }
}