package com.codecool.arinyu.myfirstandroidapp.firebase_database;

import com.bluelinelabs.logansquare.annotation.JsonField;
import com.bluelinelabs.logansquare.annotation.JsonObject;
import com.google.firebase.database.IgnoreExtraProperties;

import java.util.List;

/**
 * Created by RinyuA on 2018. 04. 16..
 */
@JsonObject
public class UserDatabaseModel {

    @JsonField(name = "users")
    List<User> users;

    List<User> getUsers() {
        return users;
    }

    public void addUsers(List<User> users) {
        this.users = users;
    }

    @JsonObject
    public static class User {

        @JsonField(name = "userName")
        private String userName;

        String getUserName() {
            return userName;
        }

        void setUserName(String userName) {
            this.userName = userName;
        }

        @JsonField(name = "emailAddress")
        private String emailAddress;

        String getEmailAddress() {
            return emailAddress;
        }

        void setEmailAddress(String emailAddress) {
            this.emailAddress = emailAddress;
        }

        @JsonField(name = "imageURLs")
        List<String> imageURLs;

        public List<String> getImageURLsList() {
            return imageURLs;
        }

        public void addImageURLsToList(List<String> imageURLs) {
            this.imageURLs = imageURLs;
        }
    }

}
