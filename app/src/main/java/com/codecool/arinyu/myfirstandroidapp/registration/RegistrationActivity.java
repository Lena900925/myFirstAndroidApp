package com.codecool.arinyu.myfirstandroidapp.registration;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.codecool.arinyu.myfirstandroidapp.MainActivity;
import com.codecool.arinyu.myfirstandroidapp.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class RegistrationActivity extends AppCompatActivity implements View.OnClickListener {

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

        mEmailField.setOnEditorActionListener(new EditText.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_DONE) {
                    registerUser();
                    return true;
                }
                return false;
            }
        });

        // [START initialize_auth]
        mAuth = FirebaseAuth.getInstance();
        findViewById(R.id.email_create_account_button).setOnClickListener(this);
        progressDialog = new ProgressDialog(this);
    }

    private void registerUser() {
        String userName = mUsername.getText().toString();
        String email = mEmailField.getText().toString();
        String password = mPasswordField.getText().toString();
        String confirmPassword = mConfirmPasswordField.getText().toString();

        if (TextUtils.isEmpty(userName) || userName.length() < 3) {
            Toast.makeText(this, "Username must be at least 3 characters!", Toast.LENGTH_LONG).show();
            return;
        }

        if (!password.equals(confirmPassword)) {
            Toast.makeText(this, "Passwords don't match! Please try again!", Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(password) || TextUtils.isEmpty(confirmPassword)) {
            Toast.makeText(this, "Please enter password!", Toast.LENGTH_LONG).show();
            return;
        }

        if (password.length() < 6) {
            Toast.makeText(this, "Password must be at least 6 characters!", Toast.LENGTH_LONG).show();
            return;
        }

        if (TextUtils.isEmpty(email)) {
            Toast.makeText(this, "Please enter your email address!", Toast.LENGTH_LONG).show();
            return;
        }

        if (!email.contains("@")) {
            Toast.makeText(this, "Please enter a valid email address!", Toast.LENGTH_LONG).show();
            return;
        }

        progressDialog.setMessage("Registering, please Wait...");
        progressDialog.show();
        //creating a new user
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        //checking if success
                        if (task.isSuccessful()) {
                            //display some message here
                            Toast.makeText(RegistrationActivity.this, "Successfully registered!", Toast.LENGTH_LONG).show();
                            Intent mainIntent = new Intent(RegistrationActivity.this, MainActivity.class);
                            startActivity(mainIntent);
                            finish();

                        } else {
                            //display some message here
                            Toast.makeText(RegistrationActivity.this, "Registration failed!", Toast.LENGTH_SHORT).show();
                            Toast.makeText(RegistrationActivity.this, task.getException().getMessage(), Toast.LENGTH_LONG).show();
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
