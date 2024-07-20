package yura.myjava.metro;

import java.util.ArrayList;
import java.util.List;

public class Line implements Comparable<Line> {
    private final List<Station> stations;
    private final String numberLine;
    private final String nameLine;

    public Line(String numberLine, String nameLine) {
        this.numberLine = numberLine;
        this.nameLine = nameLine;
        stations = new ArrayList<>();
    }
    public String getNumberLine() {
        return numberLine;
    }

    public String getNameLine() {
        return nameLine;
    }
    public void addStation(Station station) {
        stations.add(station);
    }
    public List<Station> getStations() {
        return stations;
    }

    @Override
    public String toString() {
        return numberLine;
    }

    @Override
    public int compareTo(Line o) {
        if (numberLine.length() > o.getNumberLine().length()) {
            return Integer.compare(numberLine.length(), o.getNumberLine().length());
        }
        return numberLine.compareToIgnoreCase(o.getNumberLine());
    }

    @Override
    public boolean equals(Object obj) {
        return compareTo((Line) obj) == 0;
    }
}
