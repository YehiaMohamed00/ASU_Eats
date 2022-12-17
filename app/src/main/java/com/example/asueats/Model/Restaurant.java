package com.example.asueats.Model;

public class Restaurant {
    int restImg;
    String restName;
    String restCousine;
    String restPriceRange;

    public Restaurant(int restImg, String restName, String restCousine, String restPriceRange) {
        this.restImg = restImg;
        this.restName = restName;
        this.restCousine = restCousine;
        this.restPriceRange = restPriceRange;
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
}
