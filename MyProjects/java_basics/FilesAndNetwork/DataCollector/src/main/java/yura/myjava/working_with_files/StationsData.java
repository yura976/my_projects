package yura.myjava.working_with_files;

import yura.myjava.metro.Line;
import yura.myjava.metro.Station;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class StationsData {
    private final Map<Line, List<Station>> subStationMap;
    private final Map<Station, Boolean> hasConnection;
    private final Map<Station, String> stationDepthMap;
    private final Map<Station, LocalDate> stationDateMap;

    public StationsData(Map<Line, List<Station>> subStationMap, Map<Station, Boolean> hasConnection,
                        Map<Station, String> stationDepthMap, Map<Station, LocalDate> stationDateMap) {
        this.subStationMap = subStationMap;
        this.hasConnection = hasConnection;
        this.stationDepthMap = stationDepthMap;
        this.stationDateMap = stationDateMap;
    }
    public List createStationData() {
        List list = new ArrayList<>();
        subStationMap.forEach((line, stations) -> stations.forEach(station -> {
            Map map = new LinkedHashMap();
            map.put("name", station.getNameStation());
            map.put("line", line.getNameLine());
            if (getDate(station) != null) {
                map.put("date", getDate(station));
            }
            if (getDepth(station) != null && !getDepth(station).equals("?")) {
                map.put("depth", Double.parseDouble(getDepth(station)));
            }
            if (getHasConnection(station)!= null) {
                map.put("hasConnection", getHasConnection(station));
            }
            list.add(map);
        }));
        return list;
    }
    private Boolean getHasConnection(Station station) {
        return hasConnection.get(station);
    }
    private String getDepth(Station station) {
        return stationDepthMap.get(station);
    }
    private String getDate(Station station) {
        String date = null;
        if (stationDateMap.get(station) != null) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
            date = stationDateMap.get(station).format(formatter);
        }
        return date;
    }
}

