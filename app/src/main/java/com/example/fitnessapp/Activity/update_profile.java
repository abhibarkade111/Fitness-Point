package com.example.fitnessapp.Activity;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.fitnessapp.Domain.User;
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

public class update_profile extends AppCompatActivity {
    private EditText fname,lname,age,height,weight,phoneNo,gender,email;
    private TextView update;
    private ImageView profilePic, profilePlus,back;
    FirebaseStorage storage;
    FirebaseAuth auth;

    private FirebaseUser user;
    private FirebaseDatabase database;
    private DatabaseReference myref, reference;
    private String userID;


    String ffname,llname,pphone,pprofilePic,ggender,eemail;
    float aage,wweight,hheight;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_profile);

        Spinner spinner = (Spinner) findViewById(R.id.spinnerUpdate);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.planets_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        storage = FirebaseStorage.getInstance();
        auth = FirebaseAuth.getInstance();
        database =FirebaseDatabase.getInstance();
        user = FirebaseAuth.getInstance().getCurrentUser();

        userID = user.getUid();

        initView();
        bottomNavigation();

        reference = FirebaseDatabase.getInstance().getReference("Users");
        reference.child(userID).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                User userProfile = snapshot.getValue(User.class);
                if (userProfile!=null){

                    String profilePicUrl = userProfile.getProfilepic();

                    if(profilePicUrl!=null){
                        profilePic.setImageResource(R.drawable.user);
                    }
                    else {
                        Picasso.get().load(userProfile.getProfilepic()).placeholder(R.drawable.user).into(profilePic);
//                    profilePic.setImageURI(profilePicUrl);
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(update_profile.this, "Something went wrong", Toast.LENGTH_SHORT).show();
            }
        });


        profilePlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_GET_CONTENT);
                intent.setType("image/*");// */*
                startActivityForResult(intent,33);
            }
        });

        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ffname = fname.getText().toString();
                llname = lname.getText().toString();
                eemail = email.getText().toString();
                pphone = phoneNo.getText().toString();
                aage = Float.parseFloat(age.getText().toString());
                hheight = Float.parseFloat(height.getText().toString());
                wweight = Float.parseFloat(weight.getText().toString());
                ggender = spinner.getSelectedItem().toString();
//                pprofilePic = profilePic.

                updateData();
                Toast.makeText(update_profile.this, "fname"+ffname+"lname"+llname+"phone "+pphone+"age "+aage +" height "+hheight + "w "+wweight +" gender "+ggender, Toast.LENGTH_SHORT).show();



            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(update_profile.this, user_profile.class));
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(data.getData()!=null){
            Uri sFile  = data.getData();
            profilePic.setImageURI(sFile);

            final StorageReference reference = storage.getReference().child("profile_picture").child(FirebaseAuth.getInstance().getUid());
            reference.putFile(sFile).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                    reference.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                        @Override
                        public void onSuccess(Uri uri) {
                            database.getReference().child("Users").child(userID).child("profilepic").setValue(uri.toString());
                        }
                    });
                }
            });
        }
    }

    private void updateData() {
        database = FirebaseDatabase.getInstance();
        myref = database.getReference();
        myref.child("Users").child(userID).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                dataSnapshot.getRef().child("fname").setValue(ffname);
                dataSnapshot.getRef().child("lname").setValue(llname);
                dataSnapshot.getRef().child("phone").setValue(pphone);
                dataSnapshot.getRef().child("mail").setValue(eemail);
                dataSnapshot.getRef().child("height").setValue(hheight);
                dataSnapshot.getRef().child("age").setValue(aage);
                dataSnapshot.getRef().child("weight").setValue(wweight);
                dataSnapshot.getRef().child("gender").setValue(ggender);
                //dataSnapshot.getRef().child("profilepic").setValue();

//

            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
                Toast.makeText(update_profile.this, "Something went Wrong", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void initView() {
        fname = findViewById(R.id.fnameUpdate);
        lname = findViewById(R.id.lnameUpdate);
        age = findViewById(R.id.ageUpdate);
        weight = findViewById(R.id.weightUpdate);
        height = findViewById(R.id.heightUpdate);
        update = findViewById(R.id.update);
        profilePic = findViewById(R.id.userProfilepicUpdate);
        profilePlus = findViewById(R.id.profileUploadUpdate);
        phoneNo = findViewById(R.id.phoneUpdate);
        email = findViewById(R.id.emailUpdate);
        back = findViewById(R.id.back);

    }
    private void bottomNavigation() {
        FloatingActionButton floatingActionButton = findViewById(R.id.watchUpdateProfile);
        LinearLayout homeBtn = findViewById(R.id.homeUpdateProfile);
        LinearLayout profileBtn = findViewById(R.id.profileUpdateProfile);
        LinearLayout supportBtn = findViewById(R.id.supportUpdateProfile);
        LinearLayout bmiBtn = findViewById(R.id.bmiUpdateProfile);

        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(update_profile.this, WatchPost.class));
            }
        });

        homeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(update_profile.this, MainActivity.class));
                finish();
            }
        });
        profileBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(update_profile.this, user_profile.class));
            }
        });
        supportBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(update_profile.this,SuportAssistant.class));
            }
        });
        bmiBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(update_profile.this,bmi_calculator.class));
            }
        });


    }
}