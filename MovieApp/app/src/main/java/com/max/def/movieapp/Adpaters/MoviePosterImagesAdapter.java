package com.max.def.movieapp.Adpaters;

import android.app.Activity;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.view.ViewCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.max.def.movieapp.ImageViewerActivity;
import com.max.def.movieapp.Model.MovieImagesBackDropsAndPosters;
import com.max.def.movieapp.Model.PersonImagesProfiles;
import com.max.def.movieapp.R;
import com.max.def.movieapp.ViewHolders.ImagesViewHolder;

import java.util.List;

public class MoviePosterImagesAdapter extends RecyclerView.Adapter<ImagesViewHolder>
{
    private Activity activity;
    private List<MovieImagesBackDropsAndPosters> movieImagesBackDropsAndPostersList;

    public MoviePosterImagesAdapter(Activity activity, List<MovieImagesBackDropsAndPosters> movieImagesBackDropsAndPostersList)
    {
        this.activity = activity;
        this.movieImagesBackDropsAndPostersList = movieImagesBackDropsAndPostersList;
    }

    @NonNull
    @Override
    public ImagesViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i)
    {
        View view = LayoutInflater.from(activity).inflate(R.layout.profile_images_layout,viewGroup,false);
        return new ImagesViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ImagesViewHolder imagesViewHolder, int i)
    {
        final MovieImagesBackDropsAndPosters imagesBackDropsAndPosters = movieImagesBackDropsAndPostersList.get(i);

        imagesViewHolder.setProfileImage(activity,imagesBackDropsAndPosters.getFile_path());

        imagesViewHolder.profileImage.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent imageViewerIntent = new Intent(activity, ImageViewerActivity.class);
                ActivityOptionsCompat compat = ActivityOptionsCompat.makeSceneTransitionAnimation(activity, imagesViewHolder.profileImage, ViewCompat.getTransitionName(imagesViewHolder.profileImage));
                imageViewerIntent.putExtra("image_url",imagesBackDropsAndPosters.getFile_path());
                activity.startActivity(imageViewerIntent,compat.toBundle());
            }
        });
    }

    @Override
    public int getItemCount() {
        return movieImagesBackDropsAndPostersList.size();
    }
}
