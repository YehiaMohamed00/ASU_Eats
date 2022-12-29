package com.example.asueats.Model;

import java.io.Serializable;
import java.util.List;

public class Order implements Serializable {
    public String orderID;
    public String userID;
    public String orderDate;
    public String orderStatus;
    public String orderGate;
    public String orderTimePeriod;
    public Double orderPrice;
    public List<Dish> orderedDishes;

    public Order(String orderID, String userID, String orderDate, String orderStatus, String orderGate, String orderTimePeriod, Double orderPrice, List<Dish> orderedDishes) {
        this.orderID = orderID;
        this.userID = userID;
        this.orderDate = orderDate;
        this.orderStatus = orderStatus;
        this.orderGate = orderGate;
        this.orderTimePeriod = orderTimePeriod;
        this.orderPrice = orderPrice;
        this.orderedDishes = orderedDishes;
    }


}
