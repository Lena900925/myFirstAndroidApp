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

public class TakingPicture extends AppCompatActivity {
    private SaveToFirebase saveToFirebase = new SaveToFirebase();
    private Button takePictureButton;
    private ImageView imageView;
    private Uri file;
    private Bitmap bitmap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_taking_picture);

        takePictureButton = (Button) findViewById(R.id.button_image);
        imageView = (ImageView) findViewById(R.id.imageview);

        if (ContextCompat.checkSelfPermission(this, android.Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            takePictureButton.setEnabled(false);
            ActivityCompat.requestPermissions(this, new String[]{android.Manifest.permission.CAMERA, android.Manifest.permission.WRITE_EXTERNAL_STORAGE}, 0);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        if (requestCode == 0) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED
                    && grantResults[1] == PackageManager.PERMISSION_GRANTED) {
                takePictureButton.setEnabled(true);
            }
        }
    }

//    public void takePicture(View view) throws IOException {
//        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
////        Uri imageUri = intent.getData();
////        bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), imageUri);
//        file = Uri.fromFile(getOutputMediaFile("Bills", ));
//        intent.putExtra(MediaStore.EXTRA_OUTPUT, file);
//        startActivityForResult(intent, 100);
//    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent intent) {
        if (requestCode == 100) {
            if (resultCode == RESULT_OK) {
                imageView.setImageURI(file);
                //saveToFirebase.savePicture(file);
                //deleteRecursive(getThePath("Bills"));
            }
        }
    }
    public Uri getImageUri(Context inContext, Bitmap inImage) {
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        inImage.compress(Bitmap.CompressFormat.JPEG, 100, bytes);
        String path = MediaStore.Images.Media.insertImage(inContext.getContentResolver(), inImage, "Title", null);
        return Uri.parse(path);
    }

    public static File getOutputMediaFile(String folder, String timeStamp) {
        File mediaStorageDir = getThePath(folder); // Bills
        if (!mediaStorageDir.exists()) {
            mediaStorageDir.mkdirs();
        }

//        if (!mediaStorageDir.exists()){
//            if (!mediaStorageDir.mkdirs()){
//                return null;
//            }
//        }

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