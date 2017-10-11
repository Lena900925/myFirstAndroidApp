package com.codecool.arinyu.myfirstandroidapp.take_picture;

import android.net.Uri;
import android.support.annotation.NonNull;
import android.widget.Toast;

import com.codecool.arinyu.myfirstandroidapp.MainActivity;
import com.codecool.arinyu.myfirstandroidapp.photo_gallery.Photo;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import static java.security.AccessController.getContext;

public class FireBaseConnection {
    private FirebaseStorage firebaseStorage = FirebaseStorage.getInstance();
    String URLtoFirebase = "gs://the-bill-splitter-app.appspot.com";

    void getPicturesFromFirebase() {
        // Try to get all images:
        String expression = "2017";
        //need recursion
        //OR
        //WHILE TRUE ;)

        StorageReference allRef = firebaseStorage.getReferenceFromUrl(URLtoFirebase).child(expression);
        //
    }

    void savePicture(Uri uriFile, String timeStamp) {
        StorageReference storageRef = firebaseStorage.getReferenceFromUrl(URLtoFirebase).child(timeStamp + ".jpeg");
        UploadTask uploadTask = storageRef.putFile(uriFile);

        //ITT LEGYEN Uploading...
        uploadTask.addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception exception) {
                // Handle unsuccessful uploads

            }
        }).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                Toast.makeText(TakingPicture.context, "Your photo has been uploaded ;)", Toast.LENGTH_LONG).show();

                // taskSnapshot.getMetadata() contains file metadata such as size, content-type, and download URL.
                Uri downloadUrl = taskSnapshot.getDownloadUrl();
                assert downloadUrl != null;
                Photo.addToPhotos(downloadUrl.toString());
            }
        });
    }
}
