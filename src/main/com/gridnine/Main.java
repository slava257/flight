package main.com.gridnine;

import main.com.gridnine.bilder.FlightBuilder;
import main.com.gridnine.model.AirTravel;
import main.com.gridnine.services.Filter;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        System.out.println("Исходный тестовый набор перелетов:");
        List<AirTravel> flights = FlightBuilder.createFlights();
        printFlights(flights);
        System.out.println("-------------------------------------------------");
        System.out.println("Фильтр прошедших перелетов");
        printFlights(Filter.filterNotValidDepartureDates(flights));
        System.out.println("-------------------------------------------------");
        System.out.println("Фильтр неправильных дат вылета и прилета");
        printFlights(Filter.filterNotValidDatesDepartureAndArrival(flights));
        System.out.println("-------------------------------------------------");
        System.out.println("Фильтр перелетов с длительным интервалом ожидания");
        printFlights(Filter.filterFlightsWhenIntervalMoreThan(flights, 120));
    }
    // вывод в консоль списка перелетов
    static void printFlights(List<AirTravel> flights) {
        flights
                .forEach(System.out::println);
    }
    }