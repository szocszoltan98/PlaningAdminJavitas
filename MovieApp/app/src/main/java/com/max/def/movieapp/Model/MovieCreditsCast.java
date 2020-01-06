package com.max.def.movieapp.Model;

public class MovieCreditsCast
{
    private Integer cast_id;

    private String character;

    private String credit_id;

    private int gender;

    private Integer id;

    private String name;

    private Integer order;

    private String profile_path;

    public MovieCreditsCast() {
    }

    public MovieCreditsCast(Integer cast_id, String character, String credit_id, int gender, Integer id, String name, Integer order, String profile_path) {
        this.cast_id = cast_id;
        this.character = character;
        this.credit_id = credit_id;
        this.gender = gender;
        this.id = id;
        this.name = name;
        this.order = order;
        this.profile_path = profile_path;
    }

    public Integer getCast_id() {
        return cast_id;
    }

    public void setCast_id(Integer cast_id) {
        this.cast_id = cast_id;
    }

    public String getCharacter() {
        return character;
    }

    public void setCharacter(String character) {
        this.character = character;
    }

    public String getCredit_id() {
        return credit_id;
    }

    public void setCredit_id(String credit_id) {
        this.credit_id = credit_id;
    }

    public int getGender() {
        return gender;
    }

    public void setGender(int gender) {
        this.gender = gender;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getOrder() {
        return order;
    }

    public void setOrder(Integer order) {
        this.order = order;
    }

    public String getProfile_path()
    {
        String baseImageUrl = "https://image.tmdb.org/t/p/w500";
        return baseImageUrl + profile_path;
    }

    public void setProfile_path(String profile_path) {
        this.profile_path = profile_path;
    }
}
