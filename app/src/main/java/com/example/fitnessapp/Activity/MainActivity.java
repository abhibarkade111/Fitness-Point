package com.example.fitnessapp.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.fitnessapp.Adapter.CategoryAdapter;
import com.example.fitnessapp.Adapter.PlanAdapter;
import com.example.fitnessapp.Domain.CategoryDomain;
import com.example.fitnessapp.Domain.PlanDomain;
import com.example.fitnessapp.Domain.User;
import com.example.fitnessapp.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private RecyclerView.Adapter adapter, adapter2;
    private RecyclerView recyclerViewCategoryList, recyclerViewPopularList;

    private FirebaseUser user;
    private DatabaseReference reference;
    private String userID;
    private TextView startNow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        startNow = findViewById(R.id.startNow);

        recyclerViewCategory();
        recyclerViewPopular();
        bottomNavigation();
        
        user = FirebaseAuth.getInstance().getCurrentUser();
        if(user==null){
            finish();
            startActivity(new Intent(MainActivity.this,IntroActivity.class));
        }
        reference = FirebaseDatabase.getInstance().getReference("Users");
        userID = user.getUid();

        final TextView userName = (TextView) findViewById(R.id.usernameHome);
        final ImageView profilePic  = (ImageView) findViewById(R.id.userProfilePic);

        profilePic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,user_profile.class));
            }
        });
        
        reference.child(userID).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                User userProfile = snapshot.getValue(User.class);
                if (userProfile!=null){
                    String Name = userProfile.getUserName();
                    String profilePicUrl = userProfile.getProfilepic();
                    
                    userName.setText("Hi "+Name);
                    if(profilePicUrl!=null && !profilePicUrl.isEmpty()){
                        Picasso.get().load(userProfile.getProfilepic()).placeholder(R.drawable.user).into(profilePic);

                    }
                    else {
                        profilePic.setImageResource(R.drawable.user);
//                    profilePic.setImageURI(profilePicUrl);
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(MainActivity.this, "Something went wrong", Toast.LENGTH_SHORT).show();
            }
        });

        startNow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, MiddleAgeHome.class));
            }
        });
    }

    private void bottomNavigation() {
        FloatingActionButton floatingActionButton = findViewById(R.id.card_btn);
        LinearLayout homeBtn = findViewById(R.id.homeBtn);
        LinearLayout profileBtn = findViewById(R.id.profile);
        LinearLayout supportBtn = findViewById(R.id.support);
        LinearLayout bmiBtn = findViewById(R.id.bmiMain);

        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, WatchPost.class));
            }
        });

        homeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, MainActivity.class));
                finish();
            }
        });
        profileBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, user_profile.class));
            }
        });
        supportBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,SuportAssistant.class));
            }
        });
        bmiBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,bmi_calculator.class));
            }
        });
    }

    private void recyclerViewPopular() {

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        recyclerViewPopularList = findViewById(R.id.recyclerView2);
        recyclerViewPopularList.setLayoutManager(linearLayoutManager);

        ArrayList<PlanDomain> foodlist = new ArrayList<>();
        foodlist.add(new PlanDomain("Below 18", "bicycle", "Below img "));
        foodlist.add(new PlanDomain("18 to 45", "middle_img", "Middle img "));
        foodlist.add(new PlanDomain("Above 45", "above_img", "Above img"));
        foodlist.add(new PlanDomain("Diet Plan", "diet_plan", "Above img"));


        adapter2 = new PlanAdapter(foodlist, getBaseContext());
        recyclerViewPopularList.setAdapter(adapter2);

    }

    private void recyclerViewCategory() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        recyclerViewCategoryList = findViewById(R.id.recyclerView);
        recyclerViewCategoryList.setLayoutManager(linearLayoutManager);

        ArrayList<CategoryDomain> categoryList = new ArrayList<>();
        categoryList.add(new CategoryDomain("Yoga", "yoga"));
        categoryList.add(new CategoryDomain("Aerobic", "aerobic"));
        categoryList.add(new CategoryDomain("Cardio", "cardioexercises"));
        categoryList.add(new CategoryDomain("Stretching", "flexibility"));

        adapter = new CategoryAdapter(categoryList,getBaseContext());
        recyclerViewCategoryList.setAdapter(adapter);
    }
}