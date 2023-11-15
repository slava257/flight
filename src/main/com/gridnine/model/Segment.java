package main.com.gridnine.model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;




public class Segment {
    private final LocalDateTime dateTimeOfDeparture;
    private final LocalDateTime arrivalDateTime;

    public Segment(LocalDateTime dateTimeOfDeparture, LocalDateTime arrivalDateTime) {
        this.dateTimeOfDeparture = dateTimeOfDeparture;
        this.arrivalDateTime = arrivalDateTime;
    }

    public LocalDateTime getDateTimeOfDeparture() {
        return dateTimeOfDeparture;
    }


    public LocalDateTime getArrivalDateTime() {
        return arrivalDateTime;
    }



    @Override
    public String toString() {
        DateTimeFormatter fmt =
                DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm");
        return '[' + dateTimeOfDeparture.format(fmt) + '|' + arrivalDateTime.format(fmt)
                + ']';
    }

}
