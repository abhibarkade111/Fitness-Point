package com.example.fitnessapp.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.fitnessapp.Adapter.BelowEighteenAdapter;
import com.example.fitnessapp.Adapter.CategoryAdapter;
import com.example.fitnessapp.Domain.CategoryDomain;
import com.example.fitnessapp.Domain.WeekDaysDomain;
import com.example.fitnessapp.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class BelowEighteenHome extends AppCompatActivity {
    private RecyclerView recyclerViewWeek;
    private RecyclerView.Adapter adapter;
    private ImageView backBtnBelow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_below_eighteen_home);
        backBtnBelow = findViewById(R.id.backBelow);
        bottomNavigation();

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerViewWeek = findViewById(R.id.recyclerViewWeek);
        recyclerViewWeek.setLayoutManager(linearLayoutManager);

        ArrayList<WeekDaysDomain> weekDays = new ArrayList<>();
        weekDays.add(new WeekDaysDomain("Monday"));
        weekDays.add(new WeekDaysDomain("Tuesday"));
        weekDays.add(new WeekDaysDomain("Wednesday"));
        weekDays.add(new WeekDaysDomain("Thursday"));
        weekDays.add(new WeekDaysDomain("Friday"));
        weekDays.add(new WeekDaysDomain("Saturday"));
        weekDays.add(new WeekDaysDomain("Sunday"));



        adapter = new BelowEighteenAdapter(weekDays,getBaseContext());
        recyclerViewWeek.setAdapter(adapter);
        backBtnBelow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(BelowEighteenHome.this,MainActivity.class));
            }
        });
    }

    private void bottomNavigation() {
        FloatingActionButton floatingActionButton = findViewById(R.id.card_btnBelow);
        LinearLayout homeBtn = findViewById(R.id.homeBtnBelow);
        LinearLayout profileBtn = findViewById(R.id.profileBtnBelow);
        LinearLayout supportBtn = findViewById(R.id.supportBtnBelow);
        LinearLayout bmiBtn = findViewById(R.id.bmiBtnBelow);

        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(BelowEighteenHome.this, WatchPost.class));
            }
        });

        homeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(BelowEighteenHome.this, MainActivity.class));
                finish();
            }
        });
        profileBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(BelowEighteenHome.this, user_profile.class));
            }
        });
        supportBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(BelowEighteenHome.this,SuportAssistant.class));
            }
        });
        bmiBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(BelowEighteenHome.this,bmi_calculator.class));
            }
        });
    }
}