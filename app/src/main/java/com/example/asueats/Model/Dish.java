package com.example.asueats.Model;

import java.io.Serializable;

public class Dish implements Serializable {
    int dishImg;
    String dishName;
    String dishDescription;
    double dishPrice;
    int dishAvailability;

    public Dish(int dishImg, String dishName, String dishDescription, double dishPrice, int dishAvailability) {
        this.dishImg = dishImg;
        this.dishName = dishName;
        this.dishDescription = dishDescription;
        this.dishPrice = dishPrice;
        this.dishAvailability = dishAvailability;
    }

    public int getDishImg() {
        return dishImg;
    }

    public String getDishName() {
        return dishName;
    }

    public String getDishDescription() {
        return dishDescription;
    }

    public double getDishPrice() {
        return dishPrice;
    }

    public int getDishAvailability() {
        return dishAvailability;
    }

    public void setDishAvailability(int dishAvailability) {
        this.dishAvailability = dishAvailability;
    }

    @Override
    public String toString() {
        return "Dish{" +
                "dishImg=" + dishImg +
                ", dishName='" + dishName + '\'' +
                ", dishDescription='" + dishDescription + '\'' +
                ", dishPrice=" + dishPrice +
                ", dishAvailability=" + dishAvailability +
                '}';
    }
}
