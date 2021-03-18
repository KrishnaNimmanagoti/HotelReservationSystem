package com.bridgeLabz;

public class Hotel {
    String hotelName;
    int weekDayRate;
    int weekEndRate;
    int sum;
    double rating;

    public Hotel(String hotelName, int weekDayRate, int weekEndRate, double rating) {
        this.hotelName = hotelName;
        this.weekDayRate = weekDayRate;
        this.weekEndRate = weekEndRate;
        this.rating = rating;
    }
}
