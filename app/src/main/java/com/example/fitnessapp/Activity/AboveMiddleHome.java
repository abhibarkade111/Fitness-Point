package com.example.fitnessapp.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.fitnessapp.Adapter.AboveAgePlanAdapter;
import com.example.fitnessapp.Domain.WeekDaysDomain;
import com.example.fitnessapp.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class AboveMiddleHome extends AppCompatActivity {
    private RecyclerView recyclerViewWeek;
    private RecyclerView.Adapter adapter;
    private ImageView backBtnAbove;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_above_middle_home);
        backBtnAbove = findViewById(R.id.backAbove);
        bottomNavigation();

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerViewWeek = findViewById(R.id.recyclerViewWeek2);
        recyclerViewWeek.setLayoutManager(linearLayoutManager);

        ArrayList<WeekDaysDomain> weekDays = new ArrayList<>();
        weekDays.add(new WeekDaysDomain("Monday"));
        weekDays.add(new WeekDaysDomain("Tuesday"));
        weekDays.add(new WeekDaysDomain("Wednesday"));
        weekDays.add(new WeekDaysDomain("Thursday"));
        weekDays.add(new WeekDaysDomain("Friday"));
        weekDays.add(new WeekDaysDomain("Saturday"));
        weekDays.add(new WeekDaysDomain("Sunday"));



        adapter = new AboveAgePlanAdapter(weekDays,getBaseContext());
        recyclerViewWeek.setAdapter(adapter);
        backBtnAbove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AboveMiddleHome.this,MainActivity.class));
            }
        });
    }

    private void bottomNavigation() {
        FloatingActionButton floatingActionButton = findViewById(R.id.card_btnAbove);
        LinearLayout homeBtn = findViewById(R.id.homeBtnAbove);
        LinearLayout profileBtn = findViewById(R.id.profileBtnAbove);
        LinearLayout supportBtn = findViewById(R.id.supportBtnAbove);
        LinearLayout bmiBtn = findViewById(R.id.bmiBtnAbove);

        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AboveMiddleHome.this, WatchPost.class));
            }
        });

        homeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AboveMiddleHome.this, MainActivity.class));
                finish();
            }
        });
        profileBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AboveMiddleHome.this, user_profile.class));
            }
        });
        supportBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AboveMiddleHome.this,SuportAssistant.class));
            }
        });
        bmiBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AboveMiddleHome.this,bmi_calculator.class));
            }
        });
    }
}