package com.bridgeLabz;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAccessor;
import java.util.*;
import java.util.stream.Collectors;

public class HotelReservation {
    Map<String,Integer> allmin = new HashMap<>();
    static public ArrayList<Hotel> hotels = new ArrayList<>();

    public ArrayList<Hotel> addHotel(Hotel hotel) {
        hotels.add(hotel);
        return hotels;
    }

    public void printHotels() {
        hotels.stream().forEach(hotel -> System.out.println(hotel.hotelName+ " " + hotel.weekDayRate));
    }

    public long findCheapestHotel(LocalDate d1, LocalDate d2) {
        long noOfDaysBetween = ChronoUnit.DAYS.between(d1, d2);
        Hotel cheapHotel = hotels.stream().min((hotel1, hotel2) -> hotel1.weekDayRate > hotel2.weekDayRate ? 1: -1).get();
        long cheapRate = cheapHotel.weekDayRate * (noOfDaysBetween + 1);
        System.out.println("Cheapest Hotel: " + cheapHotel.hotelName +", Total Rates: " + cheapRate);
        return cheapRate;
    }

    public int findCheapestHotelInGivenDateRange(LocalDate d1, LocalDate d2){

        List<LocalDate> dateList =  d1.datesUntil(d2).collect(Collectors.toList());
        dateList.add(d2);
        System.out.println(dateList);

        Iterator itr=dateList.iterator();
        while(itr.hasNext()){

            DayOfWeek dayOfWeek2 = DayOfWeek.from((TemporalAccessor) itr.next());
            if(dayOfWeek2.equals(DayOfWeek.SATURDAY) || dayOfWeek2.equals(DayOfWeek.SUNDAY)){
                for (Hotel hotel: hotels) {
                    hotel.sum = hotel.sum+hotel.weekEndRate;
                }
            }
            else {
                for (Hotel hotel: hotels) {
                    hotel.sum = hotel.sum+hotel.weekDayRate;
                }
            }
        }
        for (Hotel hotel: hotels) {
                System.out.println(hotel.hotelName+" "+hotel.sum);
        }
        Hotel result = hotels.get(0);
        for(int i=0; i<hotels.size(); i++){
                if (result.sum > hotels.get(i).sum) {
                    result = hotels.get(i);
                    allmin.put(hotels.get(i).hotelName, hotels.get(i).sum);
                }
        }
        allmin.put(result.hotelName, result.sum);
        for (Hotel hotel: hotels) {
            if (hotel.sum == result.sum) {
                allmin.put(hotel.hotelName, hotel.sum);
            }
        }
        allmin.forEach((k,v) -> System.out.println("Hotel Name: "+ k + ", Rate: " + v));
        return result.sum;
    }
}
