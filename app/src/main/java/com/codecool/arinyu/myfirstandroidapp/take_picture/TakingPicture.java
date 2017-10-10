package com.codecool.arinyu.myfirstandroidapp.take_picture;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.codecool.arinyu.myfirstandroidapp.MainActivity;
import com.codecool.arinyu.myfirstandroidapp.R;
import com.orhanobut.logger.AndroidLogAdapter;
import com.orhanobut.logger.Logger;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import static com.codecool.arinyu.myfirstandroidapp.R.id.drawer_layout;

public class TakingPicture extends AppCompatActivity {
    private Uri uriFile;
    private String timeStamp;
    SaveToFirebase saveToFirebase = new SaveToFirebase();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (ContextCompat.checkSelfPermission(this, android.Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{android.Manifest.permission.CAMERA, android.Manifest.permission.WRITE_EXTERNAL_STORAGE}, 0);
        } else {
            ActivityCompat.requestPermissions(this, new String[]{android.Manifest.permission.CAMERA, android.Manifest.permission.WRITE_EXTERNAL_STORAGE}, 0);
        }
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
        uriFile = Uri.fromFile(getOutputMediaFile(getFolderName(), setTimeStampForImageName()));
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
                Uri uriOnPhone = Uri.fromFile(new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES) + File.separator + getFolderName() + File.separator + "IMG_" + timeStamp + ".jpeg"));
                Logger.addLogAdapter(new AndroidLogAdapter());
                saveToFirebase.savePicture(uriOnPhone, timeStamp); //I tried to get the correct orientation but it didn't work
                Logger.i("IMAGE UPLOADED SUCCESSFULLY!");
                finish();
            }
        }

        deleteRecursive(getThePath(getFolderName()));
    }


    public static File getOutputMediaFile(String folder, String timeStamp) {
        File mediaStorageDir = getThePath(folder); // Bills
        if (!mediaStorageDir.exists()) {
            mediaStorageDir.mkdirs();
        }

        return new File(mediaStorageDir.getPath() + File.separator +
                "IMG_" + timeStamp + ".jpeg");

    }

    public static File getThePath(String folder) {
        File path = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);
        return new File(path + File.separator + folder);
    }

    public void deleteRecursive(File fileOrDirectory) {
        if (fileOrDirectory.isDirectory())
            for (File child : fileOrDirectory.listFiles())
                deleteRecursive(child);

        fileOrDirectory.delete();
    }
}