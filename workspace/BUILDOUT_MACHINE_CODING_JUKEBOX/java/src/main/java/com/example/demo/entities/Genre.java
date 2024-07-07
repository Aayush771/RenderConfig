package com.example.demo.entities;

public class Genre extends BaseEntity{
    
    String GenreName;

    public Genre(String genreName) {
        GenreName = genreName;
    }

    public String getGenreName() {
        return GenreName;
    }

    public void setGenreName(String genreName) {
        GenreName = genreName;
    }

}
