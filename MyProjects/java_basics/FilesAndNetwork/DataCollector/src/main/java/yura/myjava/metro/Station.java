package yura.myjava.metro;

public class Station implements Comparable<Station> {
    private final Line line;
    private final String nameStation;

    public Station(Line line, String nameStation) {
        this.line = line;
        this.nameStation = nameStation;
    }
    public Line getLine() {
        return line;
    }
    public String getNameStation() {
        return nameStation;
    }

    @Override
    public String toString() {
        return nameStation;
    }

    @Override
    public int compareTo(Station station) {
        int simile = line.compareTo(station.getLine());
        if (simile != 0) {
            return simile;
        }
        return nameStation.compareToIgnoreCase(station.getNameStation());
    }
    @Override
    public boolean equals(Object obj) {
        return compareTo((Station) obj) == 0;
    }
}

