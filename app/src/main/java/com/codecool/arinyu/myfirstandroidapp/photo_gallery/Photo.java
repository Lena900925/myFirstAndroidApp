package com.codecool.arinyu.myfirstandroidapp.photo_gallery;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

public class Photo implements Parcelable {
    private static List<String> listOfPicURLs = new ArrayList<>();
    private String mUrl;
    private String mTitle;

    public Photo(String url) {
        mUrl = url;
    }

    public Photo(String url, String title) {
        mUrl = url;
        mTitle = title;
    }

    protected Photo(Parcel in) {
        mUrl = in.readString();
        mTitle = in.readString();
    }

    public static final Creator<Photo> CREATOR = new Creator<Photo>() {
        @Override
        public Photo createFromParcel(Parcel in) {
            return new Photo(in);
        }

        @Override
        public Photo[] newArray(int size) {
            return new Photo[size];
        }
    };

    public String getUrl() {
        return mUrl;
    }

    public void setUrl(String url) {
        mUrl = url;
    }

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String title) {
        mTitle = title;
    }

    public static void addToPhotos(String URL) {
        listOfPicURLs.add(URL);
    }

    public static Photo[] getPhotos() {

        Photo[] photoArray = new Photo[listOfPicURLs.size()];
        for (int i = 0; i < photoArray.length; i++) {
            photoArray[i] = new Photo(listOfPicURLs.get(i));
        }

        return photoArray;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(mUrl);
        parcel.writeString(mTitle);
    }
}