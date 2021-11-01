package com.example.fitnessapp.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.fitnessapp.R;

public class diat extends AppCompatActivity {
    private Button teenager;
    private Button adult;
    private Button senior;
    private ImageView backBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diat);
        teenager = findViewById(R.id.teenagersbtn);
        backBtn = findViewById(R.id.backDietPlan);
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(diat.this,DietMain.class));
            }
        });
        teenager.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(diat.this,teenagers.class));
            }
        });
        adult = findViewById(R.id.adultbtn);
        adult.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(diat.this,adult.class));
            }
        });
        senior = findViewById(R.id.seniorbtn);
        senior.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(diat.this,senior.class));
            }
        });
    }
}