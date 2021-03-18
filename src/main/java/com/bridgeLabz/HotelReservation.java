package com.bridgeLabz;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.util.stream.Collectors;

public class HotelReservation {
    Map<String,Integer> allCheapRateHotelsList = new HashMap<>();
    Map<String, Double> allCheapRateHotelsRating = new HashMap<>();
    static public ArrayList<Hotel> hotels = new ArrayList<>();
    Hotel result;

    public ArrayList<Hotel> addHotel(Hotel hotel) {
        hotels.add(hotel);
        return hotels;
    }

//    public void printHotels() {
//        hotels.stream().forEach(hotel -> System.out.println(hotel.hotelName+ " " + hotel.weekDayRate));
//    }

    public long findCheapestHotel(LocalDate d1, LocalDate d2) {
        long noOfDaysBetween = ChronoUnit.DAYS.between(d1, d2);
        Hotel cheapHotel = Collections.min(hotels, Comparator.comparing(hotel -> hotel.weekDayRate));
        long cheapRate = cheapHotel.weekDayRate * (noOfDaysBetween + 1);
        System.out.println("Cheapest Hotel: " + cheapHotel.hotelName +", Total Rates: " + cheapRate);
        return cheapRate;
    }

    public int findCheapestHotelInGivenDateRange(LocalDate d1, LocalDate d2){
        List<LocalDate> dateList =  d1.datesUntil(d2).collect(Collectors.toList());
        dateList.add(d2);
        System.out.println(dateList);
        for (LocalDate localDate : dateList) {
            DayOfWeek dayOfWeek2 = DayOfWeek.from(localDate);
            if (dayOfWeek2.equals(DayOfWeek.SATURDAY) || dayOfWeek2.equals(DayOfWeek.SUNDAY)) {
                for (Hotel hotel : hotels) {
                    hotel.sum = hotel.sum + hotel.weekEndRate;
                }
            } else {
                for (Hotel hotel : hotels) {
                    hotel.sum = hotel.sum + hotel.weekDayRate;
                }
            }
        }
        for (Hotel hotel: hotels) {
                System.out.println(hotel.hotelName+" "+hotel.sum);
        }
         result = hotels.get(0);
        for (Hotel value : hotels) {
            if (result.sum > value.sum) {
                result = value;
                allCheapRateHotelsList.put(value.hotelName, value.sum);
            }
        }
        allCheapRateHotelsList.put(result.hotelName, result.sum);
        for (Hotel hotel: hotels) {
            if (hotel.sum == result.sum) {
                allCheapRateHotelsList.put(hotel.hotelName, hotel.sum);
                allCheapRateHotelsRating.put(hotel.hotelName, hotel.rating);
            }
        }
        allCheapRateHotelsList.forEach((k, v) -> System.out.println("Hotel Name: "+ k + ", Rate: " + v));
        return result.sum;
    }

    public void findByRating() {
        Double maxValueInMap = (Collections.max(allCheapRateHotelsRating.values()));
        for (Map.Entry<String, Double> entry : allCheapRateHotelsRating.entrySet()) {
            if (entry.getValue().equals(maxValueInMap)) {
                System.out.println(entry.getKey() + ", Rating: " + entry.getValue() + " And Total Rates: " + result.sum);     // Print the key with max value
            }
        }
    }
}
