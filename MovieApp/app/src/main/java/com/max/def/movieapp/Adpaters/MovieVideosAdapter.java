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

import com.max.def.movieapp.Model.MovieVideosResults;
import com.max.def.movieapp.R;
import com.max.def.movieapp.VideoPlayActivity;
import com.max.def.movieapp.ViewHolders.MovieVideosViewHolder;

import java.util.ArrayList;
import java.util.List;

public class MovieVideosAdapter extends RecyclerView.Adapter<MovieVideosViewHolder>
{
    private Activity activity;
    private List<MovieVideosResults> movieVideosResultsList;

    public MovieVideosAdapter(Activity activity, List<MovieVideosResults> movieVideosResultsList) {
        this.activity = activity;
        this.movieVideosResultsList = movieVideosResultsList;
    }

    @NonNull
    @Override
    public MovieVideosViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i)
    {
        View view = LayoutInflater.from(activity).inflate(R.layout.video_layout,viewGroup,false);
        return new MovieVideosViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final MovieVideosViewHolder movieVideosViewHolder, int i)
    {
        MovieVideosResults movieVideosResults = movieVideosResultsList.get(i);

        String baseUrl = "https://www.youtube.com/watch?v=";

        String videoUrl = baseUrl + movieVideosResults.getKey();

        movieVideosViewHolder.setThumbnailView(activity,videoUrl);
        movieVideosViewHolder.videoTitle.setText(movieVideosResults.getName());

        movieVideosViewHolder.itemView.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                ArrayList<MovieVideosResults> movieVideosResultsArrayList = new ArrayList<>(movieVideosResultsList);

                Intent intent = new Intent(activity, VideoPlayActivity.class);

                //set animation to open the video

                ActivityOptionsCompat compat = ActivityOptionsCompat.makeSceneTransitionAnimation(activity,movieVideosViewHolder.thumbnailView, ViewCompat.getTransitionName(movieVideosViewHolder.thumbnailView));

                // put the current video positioin and video list

                intent.putExtra("position",String.valueOf(movieVideosViewHolder.getAdapterPosition()));
                intent.putExtra("video",movieVideosResultsArrayList);

                activity.startActivity(intent,compat.toBundle());
            }
        });
    }

    @Override
    public int getItemCount() {
        return movieVideosResultsList.size();
    }
}
