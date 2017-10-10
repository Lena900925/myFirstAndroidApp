package com.codecool.arinyu.myfirstandroidapp.take_picture;

import android.net.Uri;
import android.support.annotation.NonNull;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

public class SaveToFirebase {
    private FirebaseStorage firebaseStorage = FirebaseStorage.getInstance();

    void savePicture(Uri uriFile, String timeStamp) {
        StorageReference storageRef = firebaseStorage.getReferenceFromUrl("gs://the-bill-splitter-app.appspot.com").child(timeStamp + ".jpeg");
        UploadTask uploadTask = storageRef.putFile(uriFile);
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
