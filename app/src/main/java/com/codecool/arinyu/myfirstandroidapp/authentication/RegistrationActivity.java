package com.codecool.arinyu.myfirstandroidapp.authentication;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.codecool.arinyu.myfirstandroidapp.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.orhanobut.logger.AndroidLogAdapter;
import com.orhanobut.logger.Logger;

public class RegistrationActivity extends AppCompatActivity implements View.OnClickListener{

    private static final String TAG = "EmailPassword";

    private EditText mUsername;
    private EditText mPasswordField;
    private EditText mConfirmPasswordField;
    private EditText mEmailField;
    private ProgressDialog progressDialog;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        mUsername = (EditText) findViewById(R.id.txtUserName);
        mConfirmPasswordField = (EditText) findViewById(R.id.txtConfirmPassword);
        mPasswordField = (EditText) findViewById(R.id.txtPassword);
        mEmailField = (EditText) findViewById(R.id.txtEmailAddress);

        // [START initialize_auth]
        mAuth = FirebaseAuth.getInstance();
        findViewById(R.id.email_create_account_button).setOnClickListener(this);
        progressDialog = new ProgressDialog(this);
    }
    private void registerUser(){

        //creating a new user
        mAuth.createUserWithEmailAndPassword("user email here", "user password here")
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        //checking if success
                        if(task.isSuccessful()){
                            //display some message here
                        }else{
                            //display some message here
                        }

                    }
                });

    }

}
