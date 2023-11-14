package main.com.gridnine.services;

import main.com.gridnine.model.AirTravel;
import main.com.gridnine.model.Segment;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;



import static java.time.temporal.ChronoUnit.SECONDS;
import static java.util.stream.Collectors.toList;

public class Filter {

    public static List<AirTravel> filterNotValidDepartureDates(List<AirTravel> flights) {
        List<AirTravel> filteredFlights = new ArrayList<>();
        for (AirTravel flight : flights) {
            if (flight != null) {
                Optional<Segment> badSegment = flight.getSegments().stream()
                        .filter(segment -> segment.getDateTimeOfDeparture().isBefore(LocalDateTime.now()))
                        .findFirst();
                if (badSegment.isEmpty()) {
                    filteredFlights.add(new AirTravel(flight.getSegments()));
                }
            }
        }
        return filteredFlights;
    }



    public static List<AirTravel> filterNotValidDatesDepartureAndArrival(List<AirTravel> flights) {
        return flights.stream()
                .filter(flight -> !flight.getSegments().isEmpty() && flight.getSegments().stream()
                        .noneMatch(segment -> segment.getArrivalDateTime().isBefore(segment.getDateTimeOfDeparture())))
           .collect(toList());
    }
    public static List<AirTravel> filterFlightsWhenIntervalMoreThan(List<AirTravel> flights, Integer minutesInterval) {
        List<AirTravel> filteredFlights = new ArrayList<>();
        LocalDateTime startTime;
        LocalDateTime endTime;
        boolean isValid = true;
        for (AirTravel flight : flights) {
            if (flight != null) {
                if (flight.getSegments().size() > 1) {
                    for (int i = 0; i < flight.getSegments().size() - 1; i++) {
                        startTime = flight.getSegments().get(i).getArrivalDateTime();
                        endTime = flight.getSegments().get(i + 1).getDateTimeOfDeparture();
                        if (SECONDS.between(startTime, endTime) > minutesInterval * 60) {
                            isValid = false;
                            break;
                        }
                    }
                }
            }
            if (isValid) {
                assert flight != null;
                filteredFlights.add(new AirTravel(flight.getSegments()));
            }
        }
        return filteredFlights;
    }
}