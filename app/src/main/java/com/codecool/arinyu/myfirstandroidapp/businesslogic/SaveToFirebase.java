package com.codecool.arinyu.myfirstandroidapp.businesslogic;

import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

public class SaveToFirebase {
    private FirebaseStorage firebaseStorage = FirebaseStorage.getInstance();

    public void savePicture(Intent intent){
        Uri file = intent.getData();
        StorageReference storageRef = firebaseStorage.getReference();
        StorageReference imagesRef = storageRef.child("images");
        StorageReference childRef = imagesRef.child("images/" + file.getLastPathSegment());
        UploadTask uploadTask = childRef.putFile(file);
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
