package com.max.def.movieapp.Model;

import java.util.List;

public class MovieImages
{
    private Integer id;

    private List<MovieImagesBackDropsAndPosters> backdrops;

    private List<MovieImagesBackDropsAndPosters> posters;

    public MovieImages() {
    }

    public MovieImages(Integer id, List<MovieImagesBackDropsAndPosters> backdrops, List<MovieImagesBackDropsAndPosters> posters) {
        this.id = id;
        this.backdrops = backdrops;
        this.posters = posters;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public List<MovieImagesBackDropsAndPosters> getBackdrops() {
        return backdrops;
    }

    public void setBackdrops(List<MovieImagesBackDropsAndPosters> backdrops) {
        this.backdrops = backdrops;
    }

    public List<MovieImagesBackDropsAndPosters> getPosters() {
        return posters;
    }

    public void setPosters(List<MovieImagesBackDropsAndPosters> posters) {
        this.posters = posters;
    }
}
