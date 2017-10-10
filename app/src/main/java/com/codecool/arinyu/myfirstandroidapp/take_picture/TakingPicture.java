package com.codecool.arinyu.myfirstandroidapp.take_picture;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.ImageView;

import com.codecool.arinyu.myfirstandroidapp.R;
import com.codecool.arinyu.myfirstandroidapp.take_picture.SaveToFirebase;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TakingPicture extends AppCompatActivity {


    private void takeFilePhoto() {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        Uri uriFile = Uri.fromFile(TakingPicture.getOutputMediaFile(getFolderName(), setTimeStampForImageName()));
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
