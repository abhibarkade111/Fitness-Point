package com.example.fitnessapp.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

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

public class view_profile extends AppCompatActivity {
    private ImageView userProfileShow, back;
    private TextView name, email, phone,age,weight,height,plan,selectedPlan;

    private FirebaseUser user;
    private DatabaseReference reference;
    private String userID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_profile);
        initView();
        bottomNavigation();


        user = FirebaseAuth.getInstance().getCurrentUser();
        reference = FirebaseDatabase.getInstance().getReference("Users");
        userID = user.getUid();


        reference.child(userID).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                User userProfile = snapshot.getValue(User.class);
                if (userProfile!=null){
                    String FName = userProfile.getFname();
                    String LName = userProfile.getLname();
                    String profilePicUrl = userProfile.getProfilepic();
                    String email1 = userProfile.getMail();
                    String phone1 = userProfile.getPhone();
                    float age1 = userProfile.getAge();
                    float weight1 = userProfile.getWeight();
                    float height1 = userProfile.getHeight();


                    name.setText(" Name: "+FName+" "+LName);
                    email.setText(" Email: "+email1);
                    phone.setText(" Phone: "+phone1);
                    age.setText(" Age: "+age1);
                    height.setText("Height: "+height1+" cm");
                    weight.setText(" Weight: "+weight1+" kg");

                    if(profilePicUrl!=null && !profilePicUrl.isEmpty()){
                        Picasso.get().load(userProfile.getProfilepic()).placeholder(R.drawable.user).into(userProfileShow);

                    }
                    else {
                        userProfileShow.setImageResource(R.drawable.user);
//                    profilePic.setImageURI(profilePicUrl);
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(view_profile.this, "Something went wrong", Toast.LENGTH_SHORT).show();
            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(view_profile.this,user_profile.class));
            }
        });

    }

    @Override
    protected void onRestart() {
        this.recreate();
        super.onRestart();

    }

    public void initView(){

        userProfileShow = findViewById(R.id.profileUserShow);
        name = findViewById(R.id.userNameShow);
        email = findViewById(R.id.emailShow);
        phone = findViewById(R.id.mobileShow);
        age = findViewById(R.id.viewAge);
        weight  = findViewById(R.id.viewWeight);
        height  = findViewById(R.id.viewHeight);
        plan = findViewById(R.id.palnShow);
        selectedPlan  = findViewById(R.id.selectedPlan);
        back = findViewById(R.id.back);


    }
    private void bottomNavigation() {
        FloatingActionButton floatingActionButton = findViewById(R.id.watchViewProfile);
        LinearLayout homeBtn = findViewById(R.id.homeViewProfile);
        LinearLayout profileBtn = findViewById(R.id.profileViewProfile);
        LinearLayout supportBtn = findViewById(R.id.supportViewProfile);
        LinearLayout bmiBtn = findViewById(R.id.bmiViewProfile);

        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(view_profile.this, WatchPost.class));
            }
        });

        homeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(view_profile.this, MainActivity.class));
                finish();
            }
        });
        profileBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(view_profile.this, user_profile.class));
            }
        });
        supportBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(view_profile.this,SuportAssistant.class));
            }
        });
        bmiBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(view_profile.this,bmi_calculator.class));
            }
        });


    }
}