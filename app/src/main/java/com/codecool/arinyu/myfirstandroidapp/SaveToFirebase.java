package com.codecool.arinyu.myfirstandroidapp;

import android.net.Uri;
import android.support.annotation.NonNull;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.text.SimpleDateFormat;
import java.util.Date;

public class SaveToFirebase {
    private FirebaseStorage firebaseStorage = FirebaseStorage.getInstance();

    public void savePicture(Uri file){
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmm").format(new Date());
        StorageReference storageRef = firebaseStorage.getReferenceFromUrl("gs://the-bill-splitter-app.appspot.com").child(timeStamp + ".png");
        UploadTask uploadTask = storageRef.putFile(file);
        uploadTask.addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception exception) {
                // Handle unsuccessful uploads

            }
        }).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                // taskSnapshot.getMetadata() contains file metadata such as size, content-type, and download URL.
                Uri downloadUrl = taskSnapshot.getDownloadUrl();
            }
        });
    }

}
