package com.bridgeLabz;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.util.stream.Collectors;

public class HotelReservation {
    Map<String,Integer> regularCustomerRateHotelsList = new HashMap<>();
    Map<String, Double> regularRateHotelsRating = new HashMap<>();
    Map<String,Integer> rewardedCustomerRateHotelsList = new HashMap<>();
    Map<String, Double> rewardedRateHotelsRating = new HashMap<>();
    static public ArrayList<Hotel> hotels = new ArrayList<>();
    Hotel result1;
    Hotel result2;
    LocalDate startDate;
    LocalDate endDate;

    public ArrayList<Hotel> addHotel(Hotel hotel) {
        hotels.add(hotel);
        return hotels;
    }

    public void printHotels() {
        hotels.stream().forEach(hotel -> System.out.println(hotel.hotelName+ " " + hotel.regularWeekDayRate));
    }

    public long findCheapestHotel(LocalDate d1, LocalDate d2) {
        long noOfDaysBetween = ChronoUnit.DAYS.between(d1, d2);
        Hotel cheapHotel = Collections.min(hotels, Comparator.comparing(hotel -> hotel.regularWeekDayRate));
        long cheapRate = cheapHotel.regularWeekDayRate * (noOfDaysBetween + 1);
        System.out.println("Cheapest Hotel is: " + cheapHotel.hotelName +", Total Rates: " + cheapRate);
        return cheapRate;
    }

    public void calculateTotalRateByDate(LocalDate startDate, LocalDate endDate){
        List<LocalDate> dateList =  startDate.datesUntil(endDate).collect(Collectors.toList());
        dateList.add(endDate);
        for (LocalDate localDate : dateList) {
            DayOfWeek dayOfWeek2 = DayOfWeek.from(localDate);
            if (dayOfWeek2.equals(DayOfWeek.SATURDAY) || dayOfWeek2.equals(DayOfWeek.SUNDAY)) {
                for (Hotel hotel : hotels) {
                    hotel.totalRegularRate = hotel.totalRegularRate + hotel.regularWeekEndRate;
                    hotel.totalRewardedRate = hotel.totalRewardedRate + hotel.rewardCustomerWeedEndRate;
                }
            } else {
                for (Hotel hotel : hotels) {
                    hotel.totalRegularRate = hotel.totalRegularRate + hotel.regularWeekDayRate;
                    hotel.totalRewardedRate = hotel.totalRewardedRate + hotel.rewardCustomerWeedDayRate;
                }
            }
        }
//        for (Hotel hotel: hotels) {
//                System.out.println(hotel.hotelName+" "+hotel.sum);
//        }
         result1 = hotels.get(0);
        for (Hotel value : hotels) {
            if (result1.totalRegularRate > value.totalRegularRate) {
                result1 = value;
                regularCustomerRateHotelsList.put(value.hotelName, value.totalRegularRate);
            }
        }
        regularCustomerRateHotelsList.put(result1.hotelName, result1.totalRegularRate);
        for (Hotel hotel: hotels) {
            if (hotel.totalRegularRate == result1.totalRegularRate) {
                regularCustomerRateHotelsList.put(hotel.hotelName, hotel.totalRegularRate);
                regularRateHotelsRating.put(hotel.hotelName, hotel.rating);
            }
        }

        result2 = hotels.get(0);
        for (Hotel value : hotels) {
            if (result2.totalRewardedRate > value.totalRewardedRate) {
                result2 = value;
                rewardedCustomerRateHotelsList.put(value.hotelName, value.totalRewardedRate);
            }
        }
        rewardedCustomerRateHotelsList.put(result2.hotelName, result2.totalRewardedRate);
        for (Hotel hotel: hotels) {
            if (hotel.totalRewardedRate == result2.totalRewardedRate) {
                rewardedCustomerRateHotelsList.put(hotel.hotelName, hotel.totalRewardedRate);
                rewardedRateHotelsRating.put(hotel.hotelName, hotel.rating);
            }
        }
    }

    public int findCheapestHotelInGivenDateRange(LocalDate startDate, LocalDate endDate) {
        calculateTotalRateByDate(startDate, endDate);
        System.out.println("\nThe Cheapest Hotel Name in Given Date Range: ");
        regularCustomerRateHotelsList.forEach((k, v) -> System.out.println("Hotel Name: "+ k + ", Rate: " + v));
        System.out.println();
        return result1.totalRegularRate;
    }

    //UC-10 Method
    public void findCheapestBestRatedHotelForRegular(LocalDate startDate, LocalDate endDate) {
        calculateTotalRateByDate(startDate, endDate);
        Double maxValueInMap = (Collections.max(regularRateHotelsRating.values()));
        System.out.println("The Cheapest Best Rated Hotel is ::");
        for (Map.Entry<String, Double> entry : regularRateHotelsRating.entrySet()) {
            if (entry.getValue().equals(maxValueInMap)) {
                System.out.println(entry.getKey() + ", Rating: " + entry.getValue() + " And Total Rates: " + result1.totalRegularRate +"\n");     // Print the key with max value
            }
        }
    }

    public void findCheapestBestRatedHotelForReward(String startDate, String endDate) {
        this.startDate = LocalDate.parse(startDate);
        this.endDate = LocalDate.parse(endDate);
        calculateTotalRateByDate(this.startDate, this.endDate);
        Double maxValueInMap = (Collections.max(rewardedRateHotelsRating.values()));
        System.out.println("\nThe Cheapest Best Rated Hotel is ::");
        for (Map.Entry<String, Double> entry : rewardedRateHotelsRating.entrySet()) {
            if (entry.getValue().equals(maxValueInMap)) {
                System.out.println(entry.getKey() + ", Rating: " + entry.getValue() + " And Total Rates: " + result2.totalRewardedRate +"\n");     // Print the key with max value
            }
        }
    }

    public Hotel findBestRatedHotelForRegularCustomer(LocalDate startDate, LocalDate endDate) {
        calculateTotalRateByDate(startDate, endDate);
        Hotel bestRated = Collections.max(hotels, Comparator.comparing(hotel -> hotel.rating));
        System.out.print("\nBest Rated For regular");
        System.out.println("\nHotel Name: " + bestRated.hotelName + ", Total Rate: " + bestRated.totalRegularRate + "\n");
        return bestRated;
    }
}