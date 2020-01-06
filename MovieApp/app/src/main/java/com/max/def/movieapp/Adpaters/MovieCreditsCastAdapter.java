package com.max.def.movieapp.Adpaters;

import android.app.Activity;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.max.def.movieapp.Model.MovieCreditsCast;
import com.max.def.movieapp.R;
import com.max.def.movieapp.ViewHolders.MovieCreditsViewHolder;

import java.util.List;

public class MovieCreditsCastAdapter extends RecyclerView.Adapter<MovieCreditsViewHolder>
{
    private Activity activity;
    private List<MovieCreditsCast> movieCreditsCastList;

    public MovieCreditsCastAdapter(Activity activity, List<MovieCreditsCast> movieCreditsCastList)
    {
        this.activity = activity;
        this.movieCreditsCastList = movieCreditsCastList;
    }

    @NonNull
    @Override
    public MovieCreditsViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i)
    {
        View view = LayoutInflater.from(activity).inflate(R.layout.movie_credits_layout,viewGroup,false);
        return new MovieCreditsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MovieCreditsViewHolder movieCreditsViewHolder, int i)
    {
        final MovieCreditsCast movieCreditsCast = movieCreditsCastList.get(i);

        movieCreditsViewHolder.setMovieCreditsImageView(activity,movieCreditsCast.getProfile_path());

        movieCreditsViewHolder.movieCreditsName.setText(movieCreditsCast.getName());
        movieCreditsViewHolder.movieCreditsCharacter.setText("Character : " + movieCreditsCast.getCharacter());

    }

    @Override
    public int getItemCount()
    {
        return movieCreditsCastList.size();
    }
}
