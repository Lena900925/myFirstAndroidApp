package com.codecool.arinyu.myfirstandroidapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.codecool.arinyu.myfirstandroidapp.authentication.LoginActivity;
import com.codecool.arinyu.myfirstandroidapp.authentication.RegistrationActivity;
import com.codecool.arinyu.myfirstandroidapp.calculator.CalculatorActivity;
import com.codecool.arinyu.myfirstandroidapp.photo_gallery.GalleryActivity;
import com.codecool.arinyu.myfirstandroidapp.take_picture.TakingPictureActivity;

public class BillSplitterActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button loginButton = (Button) findViewById(R.id.loginButton);
        Button signUpButton = (Button) findViewById(R.id.signupButton);

        loginButton.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View v) {
                Intent loginIntent = new Intent(BillSplitterActivity.this, LoginActivity.class);
                startActivity(loginIntent);
            }
        });

        signUpButton.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View v) {
                Intent singUpIntent = new Intent(BillSplitterActivity.this, RegistrationActivity.class);
                startActivity(singUpIntent);
            }
        });

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        //Handle the calculator action
        if (id == R.id.nav_calculator) {
            Intent calculatorIntent = new Intent(this, CalculatorActivity.class);
            startActivity(calculatorIntent);
        }

        // Handle the camera action
        else if (id == R.id.nav_camera) {
            Intent takingPictureIntent = new Intent(this, TakingPictureActivity.class);
            startActivity(takingPictureIntent);

            //Handle gallery action
        } else if (id == R.id.nav_gallery) {
            Intent showGallery = new Intent(BillSplitterActivity.this, GalleryActivity.class);
            startActivity(showGallery);
        }
//        else if (id == R.id.nav_info) {
//
//        } else if (id == R.id.nav_manage) {
//
//        } else if (id == R.id.nav_send) {
//
//        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
