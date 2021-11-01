package com.example.fitnessapp.Activity;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
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

public class upload_post extends AppCompatActivity {

    EditText desc;
    ImageView  demoImg;
    Button uploadButton, selectImg;


    private FirebaseUser user;
    private FirebaseDatabase database;
    private DatabaseReference reference,myref;
    private String userID;
    FirebaseStorage storage;
    FirebaseAuth auth;

    String description, userName, profilepic, fname, lname;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload_post);
        initView();
        bottomNavigation();
        database = FirebaseDatabase.getInstance();
        storage = FirebaseStorage.getInstance();
        auth = FirebaseAuth.getInstance();


        user = FirebaseAuth.getInstance().getCurrentUser();
        userID = user.getUid();
        reference = FirebaseDatabase.getInstance().getReference("Users");

        reference.child(userID).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                User userProfile = snapshot.getValue(User.class);
                if (userProfile!=null){
                    userName= userProfile.getUserName();
                    profilepic = userProfile.getProfilepic();
                    fname= userProfile.getFname();
                    lname = userProfile.getLname();


                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(upload_post.this, "Something went wrong", Toast.LENGTH_SHORT).show();
            }
        });


        selectImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_GET_CONTENT);
                intent.setType("image/*");// */*
                startActivityForResult(intent,33);
            }
        });

        uploadButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                 description = desc.getText().toString();
                updateData();
                startActivity(new Intent(upload_post.this,WatchPost.class));

            }
        });







    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);


        if(data.getData()!=null){
            Uri sFile  = data.getData();
            demoImg.setImageURI(sFile);

            final StorageReference reference = storage.getReference().child("upload_post").child(userID);
            reference.putFile(sFile).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                    reference.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                        @Override
                        public void onSuccess(Uri uri) {
                            database.getReference().child("Users_post").child(userID).child("postUrl").setValue(uri.toString());

                        }
                    });
                }
            });
        }
    }

    private void updateData() {
        database = FirebaseDatabase.getInstance();
        myref = database.getReference();
        myref.child("Users_post").child(userID).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                dataSnapshot.getRef().child("userName").setValue(userName);
                dataSnapshot.getRef().child("profilePic").setValue(profilepic);
                dataSnapshot.getRef().child("desc").setValue(description);



//

            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
                Toast.makeText(upload_post.this, "Something went Wrong", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void initView(){

        desc = findViewById(R.id.desc);
        selectImg = findViewById(R.id.buttonSelectImg);
        demoImg = findViewById(R.id.uploadImage);
        uploadButton = findViewById(R.id.buttonUpload);

    }
    private void bottomNavigation() {
        FloatingActionButton floatingActionButton = findViewById(R.id.watchUpload);
        LinearLayout homeBtn = findViewById(R.id.homeUpload);
        LinearLayout profileBtn = findViewById(R.id.profileBottomUpload);
        LinearLayout supportBtn = findViewById(R.id.supportUpload);
        LinearLayout bmiBtn = findViewById(R.id.bmiUpload);

        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(upload_post.this, WatchPost.class));
            }
        });

        homeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(upload_post.this, MainActivity.class));
                finish();
            }
        });
        profileBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(upload_post.this, user_profile.class));
            }
        });
        supportBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(upload_post.this,SuportAssistant.class));
            }
        });
        bmiBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(upload_post.this,bmi_calculator.class));
            }
        });


    }
}