package main.com.gridnine.model;
import java.util.List;
import java.util.stream.Collectors;


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

