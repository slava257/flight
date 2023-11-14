package main.com.gridnine.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class AirTravel {
    private final List<Segment> segments;

    public AirTravel(final List<Segment> segmentList) {
        segments = segmentList;
    }

    public List<Segment> getSegments() {
        return segments;
    }




    @Override
    public String toString() {
        return segments.stream().map(Object::toString)
                .collect(Collectors.joining(" "));
    }
}

