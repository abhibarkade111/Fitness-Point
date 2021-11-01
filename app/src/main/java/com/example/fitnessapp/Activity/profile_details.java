package com.example.fitnessapp.Activity;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.fitnessapp.R;
import com.google.android.gms.tasks.OnSuccessListener;
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

public class profile_details extends AppCompatActivity {
    private EditText fname,lname,age,height,weight,phoneNo,gender;
    private TextView submit;
    private ImageView profilePic, profilePlus;
    FirebaseStorage storage;
    FirebaseAuth auth;


    private FirebaseUser user;
    private FirebaseDatabase database;
    private DatabaseReference myref;
    private String userID;
    String ffname,llname,pphone,pprofilePic,ggender;
    float aage,wweight,hheight;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_details);
        Spinner spinner = (Spinner) findViewById(R.id.spinner);
// Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.planets_array, android.R.layout.simple_spinner_item);
// Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
// Apply the adapter to the spinner
        spinner.setAdapter(adapter);

        storage = FirebaseStorage.getInstance();
        auth = FirebaseAuth.getInstance();
        database =FirebaseDatabase.getInstance();

        user = FirebaseAuth.getInstance().getCurrentUser();
        userID = user.getUid();

        initView();


        profilePlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_GET_CONTENT);
                intent.setType("image/*");// */*
                startActivityForResult(intent,33);
            }
        });


        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ffname = fname.getText().toString();
                llname = lname.getText().toString();
                pphone = phoneNo.getText().toString();
                aage = Float.parseFloat(age.getText().toString());
                hheight = Float.parseFloat(height.getText().toString());
                wweight = Float.parseFloat(weight.getText().toString());
                ggender = spinner.getSelectedItem().toString();
//                pprofilePic = profilePic.

                updateData();



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
                dataSnapshot.getRef().child("height").setValue(hheight);
                dataSnapshot.getRef().child("age").setValue(aage);
                dataSnapshot.getRef().child("weight").setValue(wweight);
                dataSnapshot.getRef().child("gender").setValue(ggender);
                //dataSnapshot.getRef().child("profilepic").setValue();

                startActivity(new Intent(profile_details.this,MainActivity.class));

            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
                Toast.makeText(profile_details.this, "Something went Wrong", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void initView() {
        fname = findViewById(R.id.fname);
        lname = findViewById(R.id.lname);
        age = findViewById(R.id.age);
        weight = findViewById(R.id.weight);
        height = findViewById(R.id.height);
        submit = findViewById(R.id.calculate);
        profilePic = findViewById(R.id.userProfilepic);
        profilePlus = findViewById(R.id.profileUpload);
        phoneNo = findViewById(R.id.phoneNo);
       
    }
}