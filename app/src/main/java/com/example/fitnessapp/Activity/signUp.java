package com.example.fitnessapp.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.fitnessapp.Domain.User;
import com.example.fitnessapp.R;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;
import com.google.firebase.database.FirebaseDatabase;

public class signUp extends AppCompatActivity {

    private EditText email, password, username;
    private TextView alreadyAcc;
    Button sign_up, btn_google_signup;

    private FirebaseAuth auth;
    FirebaseDatabase database;
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_sign_up);
        auth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance();
        initView();
        progressDialog = new ProgressDialog(signUp.this);
        progressDialog.setTitle("Creating Account");
        progressDialog.setMessage("We are creating your account");
        sign_up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressDialog.show();
                auth.createUserWithEmailAndPassword(email.getText().toString(), password.getText().toString()).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        progressDialog.dismiss();
                        if (task.isSuccessful()) {

                            User user = new User(username.getText().toString(), email.getText().toString(), password.getText().toString(),"","","","",0.0f,0.0f,0.0f,"");

                            String id = task.getResult().getUser().getUid();
                            database.getReference().child("Users").child(id).setValue(user);
                            Toast.makeText(signUp.this, "User Created Successfully", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(signUp.this, profile_details.class));
                        } else {
                            Toast.makeText(signUp.this, task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                        }

                    }
                });
            }
        });

        alreadyAcc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(signUp.this,signIn.class));
            }
        });
    }

        public void initView() {
            email = findViewById(R.id.seemail);
            password = findViewById(R.id.sepassword);
            username = findViewById(R.id.eusername);
            sign_up = findViewById(R.id.btn_signup);
            btn_google_signup = findViewById(R.id.btn_google_signup);
            alreadyAcc =findViewById(R.id.already_accountSignUp);

        }
    }
