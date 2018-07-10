package com.codecool.arinyu.myfirstandroidapp.firebase_database;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.IgnoreExtraProperties;

import java.util.List;

/**
 * Created by RinyuA on 2018. 04. 16..
 */

@IgnoreExtraProperties
public class User {


    public String username;
    public String emailAddress;
    private List<String> imageURLs;
    private DatabaseReference databaseReference;

    public User() {

    }

    public User(String username, String emailAddress) {
        this.username = username;
        this.emailAddress = emailAddress;
    }

    public List<String> getImageURLs() {
        return imageURLs;
    }

    public void setImageURLs(List<String> imageURLs) {
        this.imageURLs = imageURLs;
    }

}
