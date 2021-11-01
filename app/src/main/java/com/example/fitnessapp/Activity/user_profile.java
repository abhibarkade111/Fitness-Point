package com.example.fitnessapp.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

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
import com.shashank.sony.fancygifdialoglib.FancyGifDialog;
import com.shashank.sony.fancygifdialoglib.FancyGifDialogListener;

public class user_profile extends AppCompatActivity {

    private ImageView profileUser;
    private TextView userName,emailProfile,viewProfile,updateProfile,bmiProfile,viewProgressProfile,logout;
    private FirebaseUser user;
    private DatabaseReference reference;
    private String userID;
    private FirebaseAuth auth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);
        initView();
        bottomNavigation();

        user = FirebaseAuth.getInstance().getCurrentUser();
        reference = FirebaseDatabase.getInstance().getReference("Users");
        userID = user.getUid();
        auth = FirebaseAuth.getInstance();

        reference.child(userID).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                User userProfile = snapshot.getValue(User.class);
                if (userProfile!=null){
                    String FName = userProfile.getFname();
                    String LName = userProfile.getLname();
                    String profilePicUrl = userProfile.getProfilepic();
                    String email = userProfile.getMail();

                    userName.setText(FName+" "+LName);
                    emailProfile.setText(" "+email);
                    if( profilePicUrl!=null && !profilePicUrl.isEmpty()){

                        Picasso.get().load(userProfile.getProfilepic()).placeholder(R.drawable.user).into(profileUser);
                    }
                    else {
                        profileUser.setImageResource(R.drawable.user);
//                    profilePic.setImageURI(profilePicUrl);
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(user_profile.this, "Something went wrong", Toast.LENGTH_SHORT).show();
            }
        });

        viewProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(user_profile.this, view_profile.class));
            }
        });

        updateProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(user_profile.this, update_profile.class));
            }
        });

        bmiProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(user_profile.this,bmi_calculator.class));
            }
        });

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                new FancyGifDialog.Builder(user_profile.this)
                        .setTitle("Are you sure you want to Logout?") // You can also send title like R.string.from_resources
                        .setTitleTextColor(R.color.black)
                        .setDescriptionTextColor(R.color.black)
                        .setNegativeBtnText("Cancel") // or pass it like android.R.string.cancel
                        .setPositiveBtnBackground(R.color.purple_200)
                        .setPositiveBtnText("Ok") // or pass it like android.R.string.ok
                        .setNegativeBtnBackground(R.color.material_on_background_disabled)
                        .setGifResource(R.drawable.gif1)   //Pass your Gif here
                        .isCancellable(true)
                        .OnPositiveClicked(new FancyGifDialogListener() {
                            @Override
                            public void OnClick() {
                                startActivity(new Intent(user_profile.this,signIn.class));
                                auth.signOut();
                            }
                        })
                        .OnNegativeClicked(new FancyGifDialogListener() {
                            @Override
                            public void OnClick() {
                              //  Toast.makeText(user_profile.this, "Cancel", Toast.LENGTH_SHORT).show();
                            }
                        })
                        .build();



            }
        });
    }

    private void bottomNavigation() {
        FloatingActionButton floatingActionButton = findViewById(R.id.card_btnProfile);
        LinearLayout homeBtn = findViewById(R.id.homeBtnProfile);
        LinearLayout profileBtn = findViewById(R.id.profileProfile);
        LinearLayout supportBtn = findViewById(R.id.supportProfile);
        LinearLayout bmiBtn = findViewById(R.id.bmiProfile);


        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(user_profile.this, WatchPost.class));
            }
        });

        homeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(user_profile.this, MainActivity.class));
                finish();
            }
        });
        profileBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(user_profile.this, user_profile.class));
            }
        });
        supportBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(user_profile.this,SuportAssistant.class));
            }
        });
        bmiBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(user_profile.this,bmi_calculator.class));
            }
        });
    }




    public void initView(){
        profileUser = findViewById(R.id.profileUserShow);
        userName = findViewById(R.id.usernameProfile);
        emailProfile = findViewById(R.id.emailProfile);
        viewProfile = findViewById(R.id.userNameShow);
        updateProfile = findViewById(R.id.emailShow);
        bmiProfile =findViewById(R.id.mobileShow);
        logout = findViewById(R.id.palnShow);


    }
}