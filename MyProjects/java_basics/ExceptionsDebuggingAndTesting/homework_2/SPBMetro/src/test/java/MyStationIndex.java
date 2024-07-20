import core.Line;
import core.Station;

import java.util.*;

public class MyStationIndex {
    private StationIndex stationIndex;
    private HashMap<Integer, Line> number2line;
    private TreeSet<Station> stations;
    private TreeMap<Station, TreeSet<Station>> connections;

    public MyStationIndex() {
        stationIndex = new StationIndex();
        number2line = new HashMap<>();
        stations = new TreeSet<>();
        connections = new TreeMap<>();
    }

    public StationIndex getMyStationIndex() {
        addLineAndStation();
        addConnection();
        return stationIndex;
    }

    private void addLineAndStation() {
        Line line1 = new Line(1, "Кировско-Выборгская");
        Line line2 = new Line(2, "Московско-Петроградская");
        Line line3 = new Line(3, "Невско-Василеостровская");

        stationIndex.addLine(line1);
        stationIndex.addLine(line2);
        stationIndex.addLine(line3);

        stationIndex.addStation(new Station("Площадь Ленина", line1));
        line1.addStation(stationIndex.getStation("Площадь Ленина"));
        stationIndex.addStation(new Station("Чернышевская", line1));
        line1.addStation(stationIndex.getStation("Чернышевская"));
        stationIndex.addStation(new Station("Площадь Восстания", line1));
        line1.addStation(stationIndex.getStation("Площадь Восстания"));
        stationIndex.addStation(new Station("Владимирская", line1));
        line1.addStation(stationIndex.getStation("Владимирская"));
        stationIndex.addStation(new Station("Пушкинская", line1));
        line1.addStation(stationIndex.getStation("Пушкинская"));
        stationIndex.addStation(new Station("Технологический институт1", line1));
        line1.addStation(stationIndex.getStation("Технологический институт1"));
        stationIndex.addStation(new Station("Балтийская", line1));
        line1.addStation(stationIndex.getStation("Балтийская"));

        stationIndex.addStation(new Station("Петроградская", line2));
        line2.addStation(stationIndex.getStation("Петроградская"));
        stationIndex.addStation(new Station("Горьковская", line2));
        line2.addStation(stationIndex.getStation("Горьковская"));
        stationIndex.addStation(new Station("Невский проспект", line2));
        line2.addStation(stationIndex.getStation("Невский проспект"));
        stationIndex.addStation(new Station("Сенная площадь", line2));
        line2.addStation(stationIndex.getStation("Сенная площадь"));
        stationIndex.addStation(new Station("Технологический институт2", line2));
        line2.addStation(stationIndex.getStation("Технологический институт2"));
        stationIndex.addStation(new Station("Фрунзенская", line2));
        line2.addStation(stationIndex.getStation("Фрунзенская"));
        stationIndex.addStation(new Station("Московские ворота", line2));
        line2.addStation(stationIndex.getStation("Московские ворота"));

        stationIndex.addStation(new Station("Приморская", line3));
        line3.addStation(stationIndex.getStation("Приморская"));
        stationIndex.addStation(new Station("Василеостровская", line3));
        line3.addStation(stationIndex.getStation("Василеостровская"));
        stationIndex.addStation(new Station("Гостиный двор", line3));
        line3.addStation(stationIndex.getStation("Гостиный двор"));
        stationIndex.addStation(new Station("Маяковская", line3));
        line3.addStation(stationIndex.getStation("Маяковская"));
        stationIndex.addStation(new Station("Площадь Александра Невского", line3));
        line3.addStation(stationIndex.getStation("Площадь Александра Невского"));
        stationIndex.addStation(new Station("Елизаровская", line3));
        line3.addStation(stationIndex.getStation("Елизаровская"));
        stationIndex.addStation(new Station("Ломоносовская", line3));
        line3.addStation(stationIndex.getStation("Ломоносовская"));

    }

    private void addConnection() {
        List<Station> connectionStations1 = new ArrayList<>();
        connectionStations1.add(stationIndex.getStation("Невский проспект", 2));
        connectionStations1.add(stationIndex.getStation("Гостиный двор", 3));

        List<Station> connectionStations2 = new ArrayList<>();
        connectionStations2.add(stationIndex.getStation("Площадь Восстания", 1));
        connectionStations2.add(stationIndex.getStation("Маяковская", 3));

        stationIndex.addConnection(connectionStations1);
        stationIndex.addConnection(connectionStations2);
    }
}
