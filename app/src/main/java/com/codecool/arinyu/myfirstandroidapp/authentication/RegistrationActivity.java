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
        mPasswordField = (EditText) findViewById(R.id.txtPassword);
        mConfirmPasswordField = (EditText) findViewById(R.id.txtConfirmPassword);
        mEmailField = (EditText) findViewById(R.id.txtEmailAddress);

        // [START initialize_auth]
        mAuth = FirebaseAuth.getInstance();
        findViewById(R.id.email_create_account_button).setOnClickListener(this);
        progressDialog = new ProgressDialog(this);
    }
    private void registerUser(){
        String email = mEmailField.toString().trim();
        String password = mPasswordField.toString().trim();
        String confirmPassword = mConfirmPasswordField.toString().trim();

        if(TextUtils.isEmpty(email)){
            Toast.makeText(this,"Please enter your email address!",Toast.LENGTH_LONG).show();
            return;
        }
        if (!mPasswordField.equals(mConfirmPasswordField)) {
            Toast.makeText(this, "Passwords don't match! Please try again!", Toast.LENGTH_SHORT).show();
        }
        if(TextUtils.isEmpty(password) || TextUtils.isEmpty(confirmPassword)){
            Toast.makeText(this,"Please enter password!",Toast.LENGTH_LONG).show();
            return;
        }

        progressDialog.setMessage("Registering, Please Wait...");
        progressDialog.show();
        //creating a new user
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        //checking if success
                        if(task.isSuccessful()){
                            //display some message here
                            Toast.makeText(RegistrationActivity.this,"Successfully registered!",Toast.LENGTH_LONG).show();
                        }else{
                            //display some message here
                            Toast.makeText(RegistrationActivity.this,"Registration failed!",Toast.LENGTH_LONG).show();

                        }
                        progressDialog.dismiss();
                    }
                });

    }
    @Override
    public void onClick(View view) {
        //calling register method on click
        registerUser();
    }
}
