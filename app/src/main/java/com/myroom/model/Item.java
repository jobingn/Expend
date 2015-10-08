package com.myroom.model;

import java.util.ArrayList;

/**
 * Created by Home on Jun 16.
 */
public class Item {
     String title;
     String rating;
    public Item(){

    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String name) {
        this.title = name;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

}