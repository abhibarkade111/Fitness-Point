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
import com.example.fitnessapp.Adapter.MiddleAgePlanAdapter;
import com.example.fitnessapp.Domain.WeekDaysDomain;
import com.example.fitnessapp.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class MiddleAgeHome extends AppCompatActivity {
    private RecyclerView recyclerViewWeek;
    private RecyclerView.Adapter adapter;
    private ImageView backBtnMiddle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_middle_age_home);
        bottomNavigation();
        backBtnMiddle = findViewById(R.id.backMiddle);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerViewWeek = findViewById(R.id.recyclerViewWeek3);
        recyclerViewWeek.setLayoutManager(linearLayoutManager);

        ArrayList<WeekDaysDomain> weekDays = new ArrayList<>();
        weekDays.add(new WeekDaysDomain("Monday"));
        weekDays.add(new WeekDaysDomain("Tuesday"));
        weekDays.add(new WeekDaysDomain("Wednesday"));
        weekDays.add(new WeekDaysDomain("Thursday"));
        weekDays.add(new WeekDaysDomain("Friday"));
        weekDays.add(new WeekDaysDomain("Saturday"));
        weekDays.add(new WeekDaysDomain("Sunday"));



        adapter = new MiddleAgePlanAdapter(weekDays,getBaseContext());
        recyclerViewWeek.setAdapter(adapter);

        backBtnMiddle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MiddleAgeHome.this,MainActivity.class));
            }
        });
    }

    private void bottomNavigation() {
        FloatingActionButton floatingActionButton = findViewById(R.id.card_btnMiddle);
        LinearLayout homeBtn = findViewById(R.id.homeBtnMiddle);
        LinearLayout profileBtn = findViewById(R.id.profileBtnMiddle);
        LinearLayout supportBtn = findViewById(R.id.supportBtnMiddle);
        LinearLayout bmiBtn = findViewById(R.id.bmiBtnMiddle);

        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MiddleAgeHome.this, WatchPost.class));
            }
        });

        homeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MiddleAgeHome.this, MainActivity.class));
                finish();
            }
        });
        profileBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MiddleAgeHome.this, user_profile.class));
            }
        });
        supportBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MiddleAgeHome.this,SuportAssistant.class));
            }
        });
        bmiBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MiddleAgeHome.this,bmi_calculator.class));
            }
        });
    }
}