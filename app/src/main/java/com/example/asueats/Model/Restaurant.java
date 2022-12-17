package com.example.asueats.Model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Restaurant implements Serializable {
    int restImg;
    String restName;
    String restCousine;
    String restPriceRange;
    List<Dish> dishList;

    public Restaurant(int restImg, String restName, String restCousine, String restPriceRange, List<Dish> dishList) {
        this.restImg = restImg;
        this.restName = restName;
        this.restCousine = restCousine;
        this.restPriceRange = restPriceRange;
        this.dishList = dishList;
    }

    public int getRestImg() {
        return restImg;
    }

    public String getRestName() {
        return restName;
    }

    public String getRestCousine() {
        return restCousine;
    }

    public String getRestPriceRange() {
        return restPriceRange;
    }

    public List<Dish> getDishList() {
        return dishList;
    }
}
