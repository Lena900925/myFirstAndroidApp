package com.codecool.arinyu.myfirstandroidapp;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.Toast;

import com.codecool.arinyu.myfirstandroidapp.calculator.CalculatorActivity;
import com.codecool.arinyu.myfirstandroidapp.photo_gallery.GalleryActivity;
import com.codecool.arinyu.myfirstandroidapp.take_picture.TakingPictureActivity;
import com.google.firebase.auth.FirebaseAuth;

public class BillSplitterActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    long back_pressed;
    boolean doubleBackToExitPressedOnce = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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
//        else if (id == R.id.nav_settings) {
//
         else if (id == R.id.nav_send_email) {
//
//        else if (id == R.id.nav_logout) {
            FirebaseAuth.getInstance().signOut();
            Intent mainIntent = new Intent(this, MainActivity.class);
            startActivity(mainIntent);
            Toast.makeText(getBaseContext(),
                    "You logged out successfully :)", Toast.LENGTH_SHORT)
                    .show();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onBackPressed() {
        if (doubleBackToExitPressedOnce) {
            super.onBackPressed();
            return;
        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            Toast.makeText(this, "Press back again to exit", Toast.LENGTH_SHORT).show();
        }

        this.doubleBackToExitPressedOnce = true;

        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                doubleBackToExitPressedOnce = false;
            }
        }, 2000);
    }

}
