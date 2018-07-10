package com.codecool.arinyu.myfirstandroidapp.take_picture;

import android.net.Uri;
import android.support.annotation.NonNull;
import android.widget.Toast;

import com.codecool.arinyu.myfirstandroidapp.photo_gallery.Photo;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class FireBaseConnection {
    private FirebaseStorage firebaseStorage = FirebaseStorage.getInstance();
    String URLtoFirebase = "gs://the-bill-splitter-app.appspot.com";

    void getPicturesFromFirebase() {
        // Try to get all images:
        String expression = "2017";


        StorageReference allRef = firebaseStorage.getReferenceFromUrl(URLtoFirebase).child(expression);
    }

    public void uploadPictureURLsToFirebaseDatabase (String URL) {
        // Write a String to the database
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("image_url");

        myRef.setValue(URL);
    }

    void savePicture(Uri uriFile, String timeStamp) {
        StorageReference storageRef = firebaseStorage.getReferenceFromUrl(URLtoFirebase).child(timeStamp + ".jpeg");
        UploadTask uploadTask = storageRef.putFile(uriFile);

        Toast.makeText(TakingPictureActivity.context, "Uploading photo...", Toast.LENGTH_LONG).show();
        uploadTask.addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception exception) {
        Toast.makeText(TakingPictureActivity.context, "Something went wrong, please try again!", Toast.LENGTH_LONG).show();

            }
        }).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                //save file path with name
                Toast.makeText(TakingPictureActivity.context, "Your photo has been uploaded ;)", Toast.LENGTH_SHORT).show();

                // taskSnapshot.getMetadata() contains file metadata such as size, content-type, and download URL.
                Uri downloadUrl = taskSnapshot.getDownloadUrl();
                assert downloadUrl != null;
                uploadPictureURLsToFirebaseDatabase(downloadUrl.toString());
                //myRef.setValue(downloadUrl.toString());
                Photo.addToPhotos(downloadUrl.toString());
            }
        });
    }

    void saveByteArray (final byte[] bytes, String timeStamp) {
        StorageReference storageRef = firebaseStorage.getReferenceFromUrl(URLtoFirebase).child(timeStamp + ".jpeg");
        UploadTask uploadTask = storageRef.putBytes(bytes);

        Toast.makeText(TakingPictureActivity.context, "Uploading photo...", Toast.LENGTH_LONG).show();
        uploadTask.addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception exception) {
                Toast.makeText(TakingPictureActivity.context, "Something went wrong, please try again!", Toast.LENGTH_LONG).show();

            }
        }).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                //save file path with name
                Toast.makeText(TakingPictureActivity.context, "Your photo has been uploaded ;)", Toast.LENGTH_SHORT).show();

                // taskSnapshot.getMetadata() contains file metadata such as size, content-type, and download URL.
//                Uri downloadUrl = taskSnapshot.getDownloadUrl();
//                assert downloadUrl != null;
                Photo.addBytesToPhotos(bytes);
            }
        });
    }
}
