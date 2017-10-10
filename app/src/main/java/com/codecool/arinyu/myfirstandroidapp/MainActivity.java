package com.codecool.arinyu.myfirstandroidapp;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.codecool.arinyu.myfirstandroidapp.businesslogic.Calculator;
import com.codecool.arinyu.myfirstandroidapp.take_picture.SaveToFirebase;
import com.codecool.arinyu.myfirstandroidapp.take_picture.TakingPicture;
import com.orhanobut.logger.AndroidLogAdapter;
import com.orhanobut.logger.Logger;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import static com.codecool.arinyu.myfirstandroidapp.R.id.drawer_layout;
import static com.codecool.arinyu.myfirstandroidapp.R.id.login_menu_item;


public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    private EditText mRentUserInput;
    private Uri uriFile;
    private String timeStamp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
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
                    Toast.makeText(MainActivity.this, "Please fill in the required field!", Toast.LENGTH_LONG).show();
                } else {
                    Integer bill = Integer.parseInt(costs);
                    Calculator calculator = new Calculator();
                    String result = calculator.calculateResults(bill);
                    Toast.makeText(MainActivity.this, result, Toast.LENGTH_LONG).show();
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.login_menu_item) {

            Intent loginIntent = new Intent(this, LoginActivity.class);
            startActivity(loginIntent);
            //return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        // Handle the camera action
        if (id == R.id.nav_camera) {

            if (ContextCompat.checkSelfPermission(this, android.Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(this, new String[]{android.Manifest.permission.CAMERA, android.Manifest.permission.WRITE_EXTERNAL_STORAGE}, 0);
            } else {
                ActivityCompat.requestPermissions(this, new String[]{android.Manifest.permission.CAMERA, android.Manifest.permission.WRITE_EXTERNAL_STORAGE}, 0);
            }

        }
        else if (id == R.id.nav_gallery) {

        }
//        else if (id == R.id.nav_info) {
//
//        } else if (id == R.id.nav_manage) {
//
//        } else if (id == R.id.nav_share) {
//
//        } else if (id == R.id.nav_send) {
//
//        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        if (requestCode == 0) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED
                    && grantResults[1] == PackageManager.PERMISSION_GRANTED) {
                takeFilePhoto();
            }
        }
    }

    // Saves correctly to local storage
    private void takeFilePhoto() {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        uriFile = Uri.fromFile(TakingPicture.getOutputMediaFile(getFolderName(), setTimeStampForImageName()));
        intent.putExtra(MediaStore.EXTRA_OUTPUT, uriFile);
        startActivityForResult(intent, 100);
    }

    private String getFolderName() {
        return "Bills";
    }

    private String setTimeStampForImageName() {
        timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        return timeStamp;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent intent) {
        if (requestCode == 100) {
            if (resultCode == RESULT_OK) {
                SaveToFirebase saveToFirebase = new SaveToFirebase();
                Uri uriOnPhone = Uri.fromFile(new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES) + File.separator + getFolderName() + File.separator + "IMG_" + timeStamp + ".jpeg"));
                Logger.addLogAdapter(new AndroidLogAdapter());
                saveToFirebase.savePicture(uriOnPhone, timeStamp);
                Logger.i("IMAGE UPLOADED SUCCESSFULLY!");


                //SNACKBAR
                Snackbar snackbar = Snackbar
                        .make(findViewById(drawer_layout), "Your photo has been uploaded successfully ;)", Snackbar.LENGTH_LONG)
                        .setAction("Action", null);
                snackbar.show();
            }
        }

        TakingPicture takingPicture = new TakingPicture();
        takingPicture.deleteRecursive(TakingPicture.getThePath(getFolderName()));
    }
}