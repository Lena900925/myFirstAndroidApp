package com.codecool.arinyu.myfirstandroidapp.calculator;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.codecool.arinyu.myfirstandroidapp.R;
import com.codecool.arinyu.myfirstandroidapp.photo_gallery.GalleryActivity;
import com.codecool.arinyu.myfirstandroidapp.take_picture.TakingPictureActivity;

public class CalculatorActivity extends AppCompatActivity {
    private EditText mRentUserInput;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.calculator);
        mRentUserInput = (EditText) findViewById(R.id.editInput);
        final Button btnCalculate = (Button) findViewById(R.id.btnCalculate);

        mRentUserInput.setOnEditorActionListener(new EditText.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_DONE) {
                    btnCalculate.performClick();
                    return true;
                }
                return false;
            }
        });

        btnCalculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String costs = mRentUserInput.getText().toString();
                if (costs.isEmpty()) {
                    Toast.makeText(CalculatorActivity.this, "Please fill in the required field!", Toast.LENGTH_LONG).show();
                } else {
                    Integer bill = Integer.parseInt(costs);
                    Calculator calculator = new Calculator();
                    String result = calculator.calculateResults(bill);
                    Toast.makeText(CalculatorActivity.this, result, Toast.LENGTH_LONG).show();
                }
            }
        });

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_SENDTO); // it's not ACTION_SEND
                intent.setType("text/plain");
                intent.putExtra(Intent.EXTRA_SUBJECT, "Havi lakbér");
                intent.putExtra(Intent.EXTRA_TEXT, "Szia Matyi! \n\n Kérlek utald át nekem a havi lakbért + rezsit erre a számlaszámra: \n\n 11773449-01196337 \n OTP \n Rinyu Annemarie \n\n Köszi szépen előre is! \n\n Üdv, \n Lena");
                intent.setData(Uri.parse("mailto:rinyu.annemarie.hd@gmail.com")); // or just "mailto:" for blank
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK); // this will make such that when user returns to your app, your app is displayed, instead of the email app.
                startActivity(intent);
                Snackbar.make(view, "Email has been sent to the roommates ;)", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }
}