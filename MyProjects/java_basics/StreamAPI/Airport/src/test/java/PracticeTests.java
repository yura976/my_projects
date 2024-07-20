import airport.Main;
import com.skillbox.airport.Aircraft;
import com.skillbox.airport.Airport;
import com.skillbox.airport.Flight;
import com.skillbox.airport.Flight.Type;
import com.skillbox.airport.Terminal;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("Поиск рейсов")
class PracticeTests {

    Airport testingAirport;

    static List<Flight> departureInTwoHours = new ArrayList<>();
    static List<Flight> departureInOneHours = new ArrayList<>();

    static Flight t1FirstArrivalExpected;

    @BeforeEach
    void init() {
        departureInTwoHours = new ArrayList<>();
        departureInOneHours = new ArrayList<>();
        testingAirport = Airport.getInstance();
        var terminals = testingAirport.getTerminals();
        terminals.clear();
        terminals.addAll(testingTerminals());
    }

    private static List<Terminal> testingTerminals() {
        List<Terminal> terminals = new ArrayList<>();

        Instant now = Instant.now().plusSeconds(50);
        Instant nowPlusThreeHourAndFiveMinutes = now.plus(3, ChronoUnit.HOURS)
                .plus(5, ChronoUnit.MINUTES);
        Instant nowPlusOneHourAndFiftyMin = now.plus(1, ChronoUnit.HOURS)
                .plus(50, ChronoUnit.MINUTES);

        // 1 Terminal
        var terminal1 = new Terminal("terminal1");
        terminal1.addFlight(new Flight("t1f0", Type.DEPARTURE, now.minus(1, ChronoUnit.HOURS), new Aircraft("T1-100")));
        var t1twoHours0 = new Flight("t1f1", Type.DEPARTURE, now, new Aircraft("T1-100"));
        terminal1.addFlight(t1twoHours0);
        var t1FirstArrival = new Flight("t1f2", Type.ARRIVAL, nowPlusThreeHourAndFiveMinutes, new Aircraft("T2-101"));
        t1FirstArrivalExpected = t1FirstArrival;
        terminal1.addFlight(t1FirstArrival);
        var t1twoHours1 = new Flight("t1f3", Type.DEPARTURE, nowPlusOneHourAndFiftyMin, new Aircraft("T3-102"));
        terminal1.addFlight(t1twoHours1);
        terminal1.addFlight(new Flight("t1f4", Type.ARRIVAL, nowPlusThreeHourAndFiveMinutes, new Aircraft("T4-103")));

        terminal1.addParkingAircraft(new Aircraft("T1-parked1"));
        terminal1.addParkingAircraft(new Aircraft("T1-parked2"));
        terminal1.addParkingAircraft(new Aircraft("DD-parked3"));
        terminals.add(terminal1);

        // 2 Terminal
        var terminal2 = new Terminal("terminal2");
        var t2twoHours1 = new Flight("t2f1", Type.DEPARTURE, nowPlusOneHourAndFiftyMin, new Aircraft("T22-100"));
        terminal2.addFlight(t2twoHours1);
        terminal2.addFlight(new Flight("t2f2", Type.ARRIVAL, nowPlusOneHourAndFiftyMin, new Aircraft("T1-101")));
        var t2twoHours2 = new Flight("t2f3", Type.DEPARTURE, nowPlusOneHourAndFiftyMin, new Aircraft("T22-102"));
        terminal2.addFlight(t2twoHours2);
        terminal2.addFlight(new Flight("t2f4", Type.ARRIVAL, nowPlusThreeHourAndFiveMinutes, new Aircraft("DD-103")));

        terminal2.addParkingAircraft(new Aircraft("T2-parked1"));
        terminal2.addParkingAircraft(new Aircraft("DD-parked2"));
        terminals.add(terminal2);

        departureInTwoHours.addAll(List.of(t1twoHours1, t2twoHours1, t2twoHours2, t1twoHours0));
        departureInOneHours.add(t1twoHours0);
        return terminals;
    }

    @ParameterizedTest
    @DisplayName("Поиск количества самолетов модели Airbus")
    @CsvSource({ "DD,3", "T22,2", "T1,5", "NO,0" })
    void findCountAircraftWithModelAirbus(String model, long expected) {
        long actual = Main.findCountAircraftWithModelAirbus(testingAirport, model);
        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("Поиск количества припаркованных самолетов во всех терминалам")
    void findMapCountParkedAircraftByTerminalName() {
        Map<String, Integer> actualMapAircraftByModel = Main.findMapCountParkedAircraftByTerminalName(testingAirport);
        Map<String, Integer> expected = Map.of("terminal1", 3, "terminal2", 2);
        assertEquals(expected, actualMapAircraftByModel);
    }

    @Test
    @DisplayName("Поиск рейсов вылетающих в ближайшие два часа")
    void findFlightsLeavingInTheNextTwoHours() {
        List<Flight> actual = Main.findFlightsLeavingInTheNextHours(testingAirport, 2);
        assertThat(actual).containsOnlyElementsOf(departureInTwoHours);
    }

    @Test
    @DisplayName("Поиск рейсов вылетающих в ближайший час")
    void findFlightsLeavingInTheNextThreeHours() {
        List<Flight> actual = Main.findFlightsLeavingInTheNextHours(testingAirport, 1);
        assertThat(actual).containsOnlyElementsOf(departureInOneHours);
    }

    @Test
    @DisplayName("Поиск первого ближайшего рейса прилетающего в terminal1")
    void findFirstFlightArrivalFromTerminal_terminal1() {
        Optional<Flight> actual = Main.findFirstFlightArriveToTerminal(testingAirport, "terminal1");
        assertThat(actual).isPresent()
                .contains(t1FirstArrivalExpected);
    }

    @Test
    @DisplayName("Поиск первого ближайшего рейса прилетающего в несуществующий терминал")
    void findFirstFlightArrivalFromTerminal_terminalNotExists() {
        Optional<Flight> actual = Main.findFirstFlightArriveToTerminal(testingAirport, "no_terminal");
        assertThat(actual).isEmpty();
    }
}
