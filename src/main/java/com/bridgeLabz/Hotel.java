package com.bridgeLabz;

public class Hotel {
    public String hotelName;
    public int regularWeekDayRate;
    public int regularWeekEndRate;
    public int rewardCustomerWeedDayRate;
    public int rewardCustomerWeedEndRate;
    public int totalRegularRate;
    public int totalRewardedRate;
    public double rating;

    public Hotel(String hotelName, int regularWeekDayRate, int regularWeekEndRate, double rating, int rewardCustomerWeedDayRate, int rewardCustomerWeedEndRate) {
        this.hotelName = hotelName;
        this.regularWeekDayRate = regularWeekDayRate;
        this.regularWeekEndRate = regularWeekEndRate;
        this.rewardCustomerWeedDayRate = rewardCustomerWeedDayRate;
        this.rewardCustomerWeedEndRate = rewardCustomerWeedEndRate;
        this.rating = rating;
    }
}
