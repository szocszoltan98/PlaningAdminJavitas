package com.max.def.movieapp;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatEditText;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.Toast;

import com.google.gson.Gson;
import com.max.def.movieapp.Adpaters.MovieSearchAdapter;
import com.max.def.movieapp.Client.RetrofitClient;
import com.max.def.movieapp.Interfaces.RetrofitService;
import com.max.def.movieapp.Model.MovieResponse;
import com.max.def.movieapp.Model.MovieResponseResults;

import org.angmarch.views.NiceSpinner;

import java.util.ArrayList;
import java.util.List;

import io.paperdb.Paper;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MoviesActivity extends AppCompatActivity
{
    private NiceSpinner sourceSpinner;

    private AppCompatEditText queryEditText;

    private AppCompatButton querySearchButton;

    private RecyclerView resultsRecyclerView;

    private String movie = "By Movie Title";



    private RetrofitService retrofitService;

    private MovieSearchAdapter movieSearchAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movies);



        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);

        sourceSpinner = findViewById(R.id.source_spinner);

        queryEditText = findViewById(R.id.query_edit_text);

        querySearchButton = findViewById(R.id.query_search_button);

        resultsRecyclerView = findViewById(R.id.results_recycler_view);
        resultsRecyclerView.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false));

        Paper.init(this);

        retrofitService = RetrofitClient.getClient().create(RetrofitService.class);

        final ArrayList<String> category = new ArrayList<>();


        category.add(movie);

        sourceSpinner.attachDataSource(category);


        if (Paper.book().read("position") != null)
        {
            int position = Paper.book().read("position");

            sourceSpinner.setSelectedIndex(position);
        }



        int position = sourceSpinner.getSelectedIndex();

        if (position == 0)
        {
            queryEditText.setHint("Enter any movie title...");
        }

        sourceSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
        {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
            {


                if (position == 0)
                {
                    queryEditText.setHint("Enter any movie title...");
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent)
            {

            }
        });



        if(Paper.book().read("cache") != null)
        {
            String results = Paper.book().read("cache");

            if (Paper.book().read("source") != null)
            {
                String source = Paper.book().read("source");

                if (source.equals("movie"))
                {
                    // convert the string cache to model movie response class using gson

                    MovieResponse movieResponse = new Gson().fromJson(results, MovieResponse.class);

                    if (movieResponse != null)
                    {
                        List<MovieResponseResults> movieResponseResults = movieResponse.getResults();

                        movieSearchAdapter = new MovieSearchAdapter(MoviesActivity.this,movieResponseResults);

                        resultsRecyclerView.setAdapter(movieSearchAdapter);

                        Paper.book().write("cache",new Gson().toJson(movieResponse));



                        Paper.book().write("source","movie");

                    }
                }

            }
        }



        querySearchButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                if (queryEditText.getText() != null)
                {
                    String query = queryEditText.getText().toString();

                    if (query.equals("") || query.equals(" "))
                    {
                        Toast.makeText(MoviesActivity.this, "Please enter any text...", Toast.LENGTH_SHORT).show();
                    }
                    else
                    {
                        queryEditText.setText("");


                        String finalQuery = query.replaceAll(" ","+");

                        if (category.size() > 0)
                        {

                                Call<MovieResponse> movieResponseCall = retrofitService.getMoviesByQuery(BuildConfig.THE_MOVIE_DB_API_KEY,finalQuery);

                                movieResponseCall.enqueue(new Callback<MovieResponse>()
                                {
                                    @Override
                                    public void onResponse(@NonNull Call<MovieResponse> call, @NonNull Response<MovieResponse> response)
                                    {
                                        MovieResponse movieResponse = response.body();

                                        if (movieResponse != null)
                                        {
                                            List<MovieResponseResults> movieResponseResults = movieResponse.getResults();

                                            movieSearchAdapter = new MovieSearchAdapter(MoviesActivity.this,movieResponseResults);

                                            resultsRecyclerView.setAdapter(movieSearchAdapter);

                                            Paper.book().write("cache",new Gson().toJson(movieResponse));

                                            Paper.book().write("source","movie");

                                        }
                                    }

                                    @Override
                                    public void onFailure(@NonNull Call<MovieResponse> call,@NonNull Throwable t)
                                    {
                                        Toast.makeText(MoviesActivity.this, t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                                    }
                                });


                        }
                    }
                }


            }
        });
    }

    @Override
    protected void onStop()
    {
        super.onStop();

        Paper.book().write("position",sourceSpinner.getSelectedIndex());
    }
}

