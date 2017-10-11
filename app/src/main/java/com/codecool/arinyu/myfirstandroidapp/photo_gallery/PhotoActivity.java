package com.codecool.arinyu.myfirstandroidapp.photo_gallery;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.codecool.arinyu.myfirstandroidapp.R;

public class PhotoActivity extends AppCompatActivity {

    public static final String EXTRA_PHOTO = "SpacePhotoActivity.SPACE_PHOTO";
    private ImageView mImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recycler_detail);

        mImageView = (ImageView) findViewById(R.id.image);
        Photo spacePhoto = getIntent().getParcelableExtra(EXTRA_PHOTO);

        Glide.with(this)
                .load(spacePhoto.getUrl())
                .asBitmap()
                .error(R.drawable.ic_photo_placeholder)
                .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                .into(mImageView);
    }
}
