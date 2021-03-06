package com.codecool.arinyu.myfirstandroidapp.photo_gallery;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.codecool.arinyu.myfirstandroidapp.R;

public class GalleryActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recycler_layout);

        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(this, 2);
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.rv_images);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(layoutManager);

        ImageGalleryAdapter adapter = new ImageGalleryAdapter(this, Photo.getPhotos());
        recyclerView.setAdapter(adapter);
    }

    private class ImageGalleryAdapter extends RecyclerView.Adapter<ImageGalleryAdapter.MyViewHolder> {

        @Override
        public ImageGalleryAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

            Context context = parent.getContext();
            LayoutInflater inflater = LayoutInflater.from(context);
            //Inflate the layout
            View photoView = inflater.inflate(R.layout.recycler_imageview, parent, false);
            ImageGalleryAdapter.MyViewHolder viewHolder = new ImageGalleryAdapter.MyViewHolder(photoView);
            return viewHolder;
        }

        @Override
        public void onBindViewHolder(ImageGalleryAdapter.MyViewHolder holder, int position) {

            Photo photo = mPhotos[position];
            ImageView imageView = holder.mPhotoImageView;
            Glide.with(mContext)
                    .load(photo.getUrl())
                    .placeholder(R.drawable.ic_photo_placeholder)
                    .into(imageView);

        }

        @Override
        public int getItemCount() {
            return (mPhotos.length);
        }

        public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

            public ImageView mPhotoImageView;

            public MyViewHolder(View itemView) {

                super(itemView);
                mPhotoImageView = (ImageView) itemView.findViewById(R.id.iv_photo);
                itemView.setOnClickListener(this);
            }

            @Override
            public void onClick(View view) {
                int position = getAdapterPosition();
                if (position != RecyclerView.NO_POSITION) {
                    Photo photo = mPhotos[position];

                    Intent intent = new Intent(mContext, PhotoActivity.class);
                    intent.putExtra(PhotoActivity.EXTRA_SPACE_PHOTO, photo);
                    startActivity(intent);
                }
            }
        }

        private Photo[] mPhotos;
        private Context mContext;

        public ImageGalleryAdapter(Context context, Photo[] Photos) {
            mContext = context;
            mPhotos = Photos;
        }
    }

}
