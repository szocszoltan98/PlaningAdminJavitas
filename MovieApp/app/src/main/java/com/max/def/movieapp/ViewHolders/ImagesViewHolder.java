package com.max.def.movieapp.ViewHolders;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.max.def.movieapp.R;
import com.squareup.picasso.Picasso;

public class ImagesViewHolder extends RecyclerView.ViewHolder
{
    public AppCompatImageView profileImage;

    public ImagesViewHolder(@NonNull View itemView)
    {
        super(itemView);

        profileImage = itemView.findViewById(R.id.profile_image);
    }

    public void setProfileImage(Activity activity, String profilePath)
    {
        Picasso.with(activity).load(profilePath).into(profileImage);
    }

}
