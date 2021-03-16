package com.bridgeLabz;

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
}
