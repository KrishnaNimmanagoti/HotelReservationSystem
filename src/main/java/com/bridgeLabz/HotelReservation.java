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
        System.out.println("Cheapest Hotel is: " + cheapHotel.hotelName +", Total Rates: " + cheapRate);
        return cheapRate;
    }

    public int findCheapestHotelInGivenDateRange(LocalDate startDate, LocalDate endDate){
        List<LocalDate> dateList =  startDate.datesUntil(endDate).collect(Collectors.toList());
        dateList.add(endDate);
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
//        for (Hotel hotel: hotels) {
//                System.out.println(hotel.hotelName+" "+hotel.sum);
//        }
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
        System.out.println("\nThe Cheapest Hotel Name in Given Date Range: ");
        allCheapRateHotelsList.forEach((k, v) -> System.out.println("Hotel Name: "+ k + ", Rate: " + v));
        System.out.println();
        return result.sum;
    }

    public void findCheapestBestRatedHotel(LocalDate startDate, LocalDate endDate) {
        findCheapestHotelInGivenDateRange(startDate, endDate);
        Double maxValueInMap = (Collections.max(allCheapRateHotelsRating.values()));
        System.out.println("The Cheapest Best Rated Hotel is ::");
        for (Map.Entry<String, Double> entry : allCheapRateHotelsRating.entrySet()) {
            if (entry.getValue().equals(maxValueInMap)) {
                System.out.println(entry.getKey() + ", Rating: " + entry.getValue() + " And Total Rates: " + result.sum+"\n");     // Print the key with max value
            }
        }
    }

    public Hotel findBestRatedHotel(LocalDate startDate, LocalDate endDate) {
        findCheapestHotelInGivenDateRange(startDate, endDate);
        Hotel bestRated = Collections.max(hotels, Comparator.comparing(hotel -> hotel.rating));
        System.out.print("Best Rated ");
        System.out.println("Hotel Name: " + bestRated.hotelName + ", Total Rate: " + bestRated.sum + "\n");
        return bestRated;
    }
}
