package com.max.def.movieapp.Interfaces;

import com.max.def.movieapp.Model.MovieCredits;
import com.max.def.movieapp.Model.MovieDetails;
import com.max.def.movieapp.Model.MovieImages;
import com.max.def.movieapp.Model.MovieResponse;
import com.max.def.movieapp.Model.MovieVideos;
import com.max.def.movieapp.Model.PersonDetails;
import com.max.def.movieapp.Model.PersonImages;
import com.max.def.movieapp.Model.PersonResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface RetrofitService
{
    // https://api.themoviedb.org/3/search/movie?api_key="YOUR_API_KEY"&query="MOVIE_NAME"

    // this is url to get the movie results

    // https://api.themoviedb.org/3/search/person?api_key="YOUR_API_KEY"&query="ACTOR_NAME"

    // this url to get the person results

    //https://api.themoviedb.org/3/ - this is base url . we already created in client

    // now create a service to the get results and convert results into model classes

    @GET("search/movie")
    Call<MovieResponse> getMoviesByQuery(@Query("api_key") String api_key, @Query("query") String query);

    @GET("movie/{movie_id}")
    Call<MovieDetails> getMovieDetailsById(@Path("movie_id") int movie_id, @Query("api_key") String api_key);

    @GET("movie/{movie_id}/credits")
    Call<MovieCredits> getMovieCreditsById(@Path("movie_id") int movie_id, @Query("api_key") String api_key);

    @GET("movie/{movie_id}/images")
    Call<MovieImages> getMovieImagesById(@Path("movie_id") int movie_id, @Query("api_key") String api_key);

    @GET("movie/{movie_id}/videos")
    Call<MovieVideos> getMovieVideosById(@Path("movie_id") int movie_id, @Query("api_key") String api_key);

    // now create a service for person response

    // before that create a model for person results

    @GET("search/person")
    Call<PersonResponse> getPersonsByQuery(@Query("api_key") String api_key, @Query("query") String query);

    @GET("person/{person_id}")
    Call<PersonDetails> getPersonDetailsById(@Path("person_id") int person_id, @Query("api_key") String api_key);

    // create a service to get the images

    @GET("person/{person_id}/images")
    Call<PersonImages> getPersonImagesById(@Path("person_id") int person_id, @Query("api_key") String api_key);
}
