package com.bridgeLabz;

public class Hotel {
    String hotelName;
    int weekDayRate;
    int weekEndRate;
    int sum;
    double rating;
    int rewardCustomerWeedDayRate;
    int rewardCustomerWeedEndRate;

    public Hotel(String hotelName, int weekDayRate, int weekEndRate, double rating, int rewardCustomerWeedDayRate, int rewardCustomerWeedEndRate) {
        this.hotelName = hotelName;
        this.weekDayRate = weekDayRate;
        this.weekEndRate = weekEndRate;
        this.rating = rating;
    }
}
