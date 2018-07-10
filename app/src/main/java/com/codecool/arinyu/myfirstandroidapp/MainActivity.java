package com.codecool.arinyu.myfirstandroidapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.codecool.arinyu.myfirstandroidapp.registration.RegistrationActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.codecool.arinyu.myfirstandroidapp.photo_gallery.Photo.addToPhotos;

// Here you can login

public class MainActivity extends AppCompatActivity {

//    @BindView(R.id.cheat_button)
//    Button cheat_login;
//    @BindView((R.id.loginButton))
//    Button loginButton;
//    @BindView(R.id.signupButton)
//    Button signupButton;

    @BindView(R.id.login_progress)
    ProgressBar loginProgress;
    @BindView(R.id.txtPassword)
    EditText mPassword;
    @BindView(R.id.txtEmailAddress)
    EditText mEmailAddress;

    private FirebaseAuth mAuth;
    String email;
    String password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);
        ButterKnife.bind(this);
        setPictures(); //Todo load them from database
        mAuth = FirebaseAuth.getInstance();

    }

    @OnClick(R.id.cheat_button)
    public void onClick(View view) { //maybe need the View v
        Intent billSplitterIntent = new Intent(MainActivity.this, BillSplitterActivity.class);
        startActivity(billSplitterIntent);
    }

    @OnClick(R.id.signupButton)
    public void register(View view) {
        Intent registerIntent = new Intent(MainActivity.this, RegistrationActivity.class);
        startActivity(registerIntent);
    }

    @OnClick(R.id.loginButton)
    public void loginPressed(View view) {
        email = mEmailAddress.getText().toString();
        password = mPassword.getText().toString();
        checkInput();
    }

    private void checkInput() {

        if (TextUtils.isEmpty(email)) {
            Toast.makeText(getApplicationContext(), "Please enter email address!", Toast.LENGTH_SHORT).show();
        }

        if (TextUtils.isEmpty(password)) {
            Toast.makeText(getApplicationContext(), "Please enter password!", Toast.LENGTH_SHORT).show();
        }

        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(MainActivity.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        loginProgress.setVisibility(View.GONE);
                        if (task.isSuccessful()) {
                            // Sign in success
                            login();
                            Toast.makeText(MainActivity.this, "You logged in successfully :)",
                                    Toast.LENGTH_SHORT).show();
                            finish();

                        } else {
                            // If sign in fails, display a message to the user.
                            Toast.makeText(MainActivity.this, "Authentication failed!",
                                    Toast.LENGTH_SHORT).show();

                            Toast.makeText(MainActivity.this, task.getException().getMessage(),
                                    Toast.LENGTH_LONG).show();
                            FirebaseUser user = mAuth.getCurrentUser();
                            user = null;
                        }
                    }
                });
    }


    public void login() {
        Intent billSplitterIntent = new Intent(MainActivity.this, BillSplitterActivity.class);
        startActivity(billSplitterIntent);
    }


    public static void setPictures() {
        addToPhotos("https://firebasestorage.googleapis.com/v0/b/the-bill-splitter-app.appspot.com/o/bill3.jpg?alt=media&token=1e147c2d-e751-4595-86d8-9cb9f17e5e21");
        addToPhotos("https://firebasestorage.googleapis.com/v0/b/the-bill-splitter-app.appspot.com/o/bill1.jpg?alt=media&token=3ee40d45-9c87-43c3-abd9-7f72d201e51b");
        addToPhotos("https://firebasestorage.googleapis.com/v0/b/the-bill-splitter-app.appspot.com/o/bill7.jpeg?alt=media&token=47e0992e-e460-4d2a-aa88-f77aa73bf935");
        addToPhotos("https://firebasestorage.googleapis.com/v0/b/the-bill-splitter-app.appspot.com/o/bill2.jpg?alt=media&token=29a9363e-62ff-4573-8a84-81741904a900");
    }

    @Override
    public void onBackPressed() {
        finish();
    }
}