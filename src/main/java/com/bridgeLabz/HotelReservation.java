package com.bridgeLabz;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;

public class HotelReservation {

    static public ArrayList<Hotel> hotels = new ArrayList<>();

    public ArrayList<Hotel> addHotel(Hotel hotel) {
        hotels.add(hotel);
        return hotels;
    }

    public void printHotels() {
        hotels.stream().forEach(hotel -> System.out.println(hotel.hotelName+ " " + hotel.rate));
    }

    public long findCheapestHotel1 (LocalDate d1, LocalDate d2) {
        long noOfDaysBetween = ChronoUnit.DAYS.between(d1, d2);
        Hotel cheapHotel = hotels.stream().min((hotel1, hotel2) -> hotel1.rate > hotel2.rate ? 1: -1).get();
        long cheapRate = cheapHotel.rate * (noOfDaysBetween + 1);
        System.out.println("Cheapest Hotel: " + cheapHotel.hotelName +", Total Rates: " + cheapRate);
        return cheapRate;
    }
}
