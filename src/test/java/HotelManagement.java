import com.bridgeLabz.Hotel;
import com.bridgeLabz.HotelReservation;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;

public class HotelManagement {

    HotelReservation hotelReservation = new HotelReservation();

    Hotel lakewood = new Hotel("Lakewood", 110, 90, 3);
    Hotel bridgewood = new Hotel("Bridgewood", 150, 50, 4);
    Hotel ridgewood = new Hotel("Ridgewood", 220, 150, 5);

    @BeforeEach
    public void addHotelTest() {
        ArrayList<Hotel> hotel1 = hotelReservation.addHotel(lakewood);
        ArrayList<Hotel> hotel2 = hotelReservation.addHotel(bridgewood);
        ArrayList<Hotel> hotel3 = hotelReservation.addHotel(ridgewood);

        Assertions.assertTrue(hotel1.contains(lakewood));
        Assertions.assertTrue(hotel2.contains(bridgewood));
        Assertions.assertTrue(hotel3.contains(ridgewood));
    }

    @Test
    public void findCheapestHotel() {
        long rate = hotelReservation.findCheapestHotel(LocalDate.parse("2020-10-10"), LocalDate.parse("2020-10-11"));
        Assertions.assertEquals(220, rate);

    }

    @Test
    public void CheapestHotelInGivenDates() {
        LocalDate startDate = LocalDate.parse("2020-09-11");
        LocalDate endDate = LocalDate.parse("2020-09-12");
        int result = hotelReservation.findCheapestHotelInGivenDateRange(startDate, endDate);
        Assertions.assertEquals(200, result);
    }

    @Test
    public void findByRatingTest() {
        LocalDate startDate = LocalDate.parse("2020-09-11");
        LocalDate endDate = LocalDate.parse("2020-09-12");
        hotelReservation.findCheapestBestRatedHotel(startDate, endDate);
    }

    @Test
    public void TestFindBestRatedHotel() {
        LocalDate startDate = LocalDate.parse("2020-09-11");
        LocalDate endDate = LocalDate.parse("2020-09-12");
        hotelReservation.findBestRatedHotel(startDate, endDate);
    }

}
