package test.com.gridnine;

import main.com.gridnine.model.AirTravel;
import main.com.gridnine.model.Segment;
import main.com.gridnine.services.Filter;
import org.junit.jupiter.api.Test;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class FilterTest {
    @Test
    public void shouldReturnEmptyWhenDepartureDateBeforeNowFilter() {
        Segment segment = new Segment(LocalDateTime.now().minusHours(1), LocalDateTime.now());
        List<Segment> segmentList = new ArrayList<>();
        segmentList.add(segment);
        List<AirTravel> flights = new ArrayList<>();
        flights.add(new AirTravel(segmentList));
        List<AirTravel> filteredFlights = Filter.filterNotValidDepartureDates(flights);
        assertTrue(filteredFlights.isEmpty(), "Список перелетов должен быть пустым");
    }

    @Test
    public void shouldReturnNotEmptyWhenDepartureDateNowOrAfterFilter() {
        Segment segment = new Segment(LocalDateTime.now().plusHours(1), LocalDateTime.now().plusHours(3));
        List<Segment> segmentList = new ArrayList<>();
        segmentList.add(segment);
        List<AirTravel> flights = new ArrayList<>();
        flights.add(new AirTravel(segmentList));
        List<AirTravel> filteredFlights = Filter.filterNotValidDatesDepartureAndArrival(flights);
        assertEquals(1, filteredFlights.size(), "В списке перелетов должен быть один элемент");
    }

    @Test
    public void shouldReturnEmptyWhenArrivalDateBeforeDepartureDateFilter() {
        Segment segment = new Segment(LocalDateTime.now(), LocalDateTime.now().minusHours(1));
        List<Segment> segmentList = new ArrayList<>();
        segmentList.add(segment);
        List<AirTravel> flights = new ArrayList<>();
        flights.add(new AirTravel(segmentList));
        List<AirTravel> filteredFlights = Filter.filterNotValidDatesDepartureAndArrival(flights);
        assertTrue(filteredFlights.isEmpty(), "Список перелетов должен быть пустым");
    }

    @Test
    public void shouldReturnNotEmptyWhenArrivalDateAfterDepartureDateFilter() {
        Segment segment = new Segment(LocalDateTime.now(), LocalDateTime.now().plusHours(1));
        List<Segment> segmentList = new ArrayList<>();
        segmentList.add(segment);
        List<AirTravel> flights = new ArrayList<>();
        flights.add(new AirTravel(segmentList));
        List<AirTravel> filteredFlights = Filter.filterNotValidDatesDepartureAndArrival(flights);
        assertEquals(1, filteredFlights.size(), "В списке перелетов должен быть один элемент");
    }

    @Test
    public void shouldReturnEmptyWhenLongWaitingInterval() {
        Integer interval = 30;
        LocalDateTime time = LocalDateTime.now();
        Segment segment1 = new Segment(time.minusHours(1), time);
        Segment segment2 = new Segment(time.plusHours(2).plusSeconds(1), time.plusHours(5));
        List<Segment> segmentList = new ArrayList<>();
        segmentList.add(segment1);
        segmentList.add(segment2);
        List<AirTravel> flights = new ArrayList<>();
        flights.add(new AirTravel(segmentList));
        List<AirTravel> filteredFlights = Filter.filterFlightsWhenIntervalMoreThan(flights, interval);
        assertTrue(filteredFlights.isEmpty(), "Список перелетов должен быть пустым");
    }

    @Test
    public void shouldReturnEmptyWhenNormalWaitingInterval() {
        Integer interval = 250;
        LocalDateTime time = LocalDateTime.now();
        Segment segment1 = new Segment(time.minusHours(1), time);
        Segment segment2 = new Segment(time.plusHours(2).plusSeconds(1), time.plusHours(5));
        List<Segment> segmentList = new ArrayList<>();
        segmentList.add(segment1);
        segmentList.add(segment2);
        List<AirTravel> flights = new ArrayList<>();
        flights.add(new AirTravel(segmentList));
        List<AirTravel> filteredFlights = Filter.filterFlightsWhenIntervalMoreThan(flights, interval);
        assertEquals(1, filteredFlights.size(), "В списке должен быть один перелет");
    }
}
