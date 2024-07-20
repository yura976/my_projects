import core.Station;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class RouteCalculatorTest {

    private static StationIndex stationIndex;
    private static RouteCalculator calculator;

    @BeforeAll
    public static void setUp() {
        MyStationIndex myStationIndex = new MyStationIndex();
        stationIndex = myStationIndex.getMyStationIndex();
        calculator = new RouteCalculator(stationIndex);
    }

    public static Stream<Arguments> expectedShortestRoute() {
        return Stream.of(
                Arguments.of(List.of("Петроградская", "Горьковская", "Невский проспект", "Сенная площадь",
                                "Технологический институт2", "Фрунзенская", "Московские ворота"),
                        "Петроградская", "Московские ворота"),
                Arguments.of(List.of("Петроградская", "Горьковская", "Невский проспект", "Гостиный двор",
                                "Маяковская", "Площадь Александра Невского", "Елизаровская", "Ломоносовская"),
                        "Петроградская", "Ломоносовская"),
                Arguments.of(List.of("Приморская", "Василеостровская", "Гостиный двор",
                        "Невский проспект", "Горьковская"), "Приморская", "Горьковская"),
                Arguments.of(List.of("Площадь Ленина", "Чернышевская", "Площадь Восстания", "Маяковская",
                        "Гостиный двор", "Невский проспект", "Сенная площадь", "Технологический институт2",
                        "Фрунзенская"), "Площадь Ленина", "Фрунзенская"));
    }

    @ParameterizedTest
    @MethodSource("expectedShortestRoute")
    void getShortestRoute(List<String> list, String from, String to) {
        List<String> stationActualList = new ArrayList<>();
        List<Station> shortestRouteList = calculator.getShortestRoute(stationIndex.getStation(from),
                stationIndex.getStation(to));
        shortestRouteList.forEach(s -> stationActualList.add(s.getName()));
        assertEquals(list, stationActualList);
    }

    public static Stream<Arguments> expectedCalculateDuration() {
        return Stream.of(
                Arguments.of("Приморская", "Горьковская", 11.0),
                Arguments.of("Петроградская", "Московские ворота", 15.0),
                Arguments.of("Петроградская", "Ломоносовская", 18.5),
                Arguments.of("Площадь Ленина", "Фрунзенская", 22.0));
    }

    @ParameterizedTest
    @MethodSource("expectedCalculateDuration")
    void calculateDuration(String from, String to, double duration) {
        List<Station> shortestRouteList = calculator.getShortestRoute(stationIndex.getStation(from),
                stationIndex.getStation(to));
        assertEquals(duration, RouteCalculator.calculateDuration(shortestRouteList));
    }

    @ParameterizedTest
    @CsvSource({"'Маяковская', 'Площадь Восстания', true", "'Петроградская', 'Ломоносовская', false",
            "'Невский проспект', 'Гостиный двор', true"})
    public void testIsConnected(String from, String to, boolean expected) {
        try {
            Method method = RouteCalculator.class.getDeclaredMethod("isConnected", Station.class, Station.class);
            method.setAccessible(true);
            assertEquals(expected, method.invoke(calculator, stationIndex.getStation(from),
                    stationIndex.getStation(to)));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}