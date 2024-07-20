package airport;

import com.skillbox.airport.Airport;
import com.skillbox.airport.Flight;
import com.skillbox.airport.Terminal;

import java.time.Instant;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

public class Main {

    public static long findCountAircraftWithModelAirbus(Airport airport, String model) {
        return airport.getAllAircrafts()
                .stream()
                .filter(aircraft -> aircraft.getModel().contains(model))
                .count();
    }

    public static Map<String, Integer> findMapCountParkedAircraftByTerminalName(Airport airport) {
        return airport
                .getTerminals()
                .stream()
                .collect(Collectors.toMap(Terminal::getName, terminal -> terminal.getParkedAircrafts().size()));
    }

    public static List<Flight> findFlightsLeavingInTheNextHours(Airport airport, int hours) {
        return airport.getTerminals()
                .stream()
                .map(Terminal::getFlights)
                .flatMap(Collection::stream)
                .filter(flight -> flight.getType().equals(Flight.Type.DEPARTURE))
                .filter(flight -> {
                    long period = flight.getDate().getEpochSecond() - Instant.now().getEpochSecond();
                    return period < hours * 3600L && period > 0;
                })
                .collect(Collectors.toList());
    }

    public static Optional<Flight> findFirstFlightArriveToTerminal(Airport airport, String terminalName) {
        return airport.getTerminals()
                .stream()
                .filter(terminal -> terminal.getName().equals(terminalName))
                .map(Terminal::getFlights)
                .flatMap(Collection::stream)
                .filter(flight -> flight.getType().equals(Flight.Type.ARRIVAL))
                .findFirst();
    }

    public static void main(String[] args) {
        Airport airport = Airport.getInstance();
        List<Flight> list = findFlightsLeavingInTheNextHours(airport, 1);
        list.forEach(System.out::println);
    }
}