package com.example.fitnessapp.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.fitnessapp.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class bmi_calculator extends AppCompatActivity {
    private EditText heightText, weightText;
    private TextView bmiShow;
    private TextView calculateBMI;
    private ImageView back;

    float height,weight, bmi;;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bmi_calculator);

        initView();
        bottomNavigation();


        calculateBMI.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                height = Float.parseFloat(heightText.getText().toString());
                weight = Float.parseFloat(weightText.getText().toString());
                if(height!=0 && weight!=0){
                     bmi = weight/((height/100)*(height/100));
                    bmiShow.setText("Your BMI is "+bmi+" kg/m^2");
                }
                else {
                    bmiShow.setText("Enter correct values");
                }

            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(bmi_calculator.this,user_profile.class));
            }
        });
    }

    public void initView(){

        heightText = findViewById(R.id.heightEnter);
        weightText = findViewById(R.id.weightEnter);
        calculateBMI = findViewById(R.id.calculateBMI);
        bmiShow  = findViewById(R.id.showBMI);
        back  = findViewById(R.id.back);

    }

    private void bottomNavigation() {
        FloatingActionButton floatingActionButton = findViewById(R.id.bmiWatchBtn);
        LinearLayout homeBtn = findViewById(R.id.homeBMI);
        LinearLayout profileBtn = findViewById(R.id.profileBMI);
        LinearLayout supportBtn = findViewById(R.id.supportBMI);
        LinearLayout bmiBtn = findViewById(R.id.bmiBMI);

        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(bmi_calculator.this, WatchPost.class));
            }
        });

        homeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(bmi_calculator.this, MainActivity.class));
                finish();
            }
        });
        profileBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(bmi_calculator.this, user_profile.class));
            }
        });
        supportBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(bmi_calculator.this,SuportAssistant.class));
            }
        });
        bmiBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(bmi_calculator.this,bmi_calculator.class));
            }
        });


    }
}