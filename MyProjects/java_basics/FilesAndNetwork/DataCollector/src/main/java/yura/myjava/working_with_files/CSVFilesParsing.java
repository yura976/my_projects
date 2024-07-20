package yura.myjava.working_with_files;

import yura.myjava.metro.Line;
import yura.myjava.metro.Station;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CSVFilesParsing {
    private Map<Station, LocalDate> stationDateMap;
    private Map<Line, List<Station>> stationMap;      //stationMap without D1 & D2
    private List<String> dataList;

    public void setStationDateMap(WebPageParsing webPageParsing, FileSearch fileSearch) {
        stationDateMap = new HashMap<>();
        dataList = new ArrayList<>();
        stationMap = webPageParsing.getSubStationMap();
        fileSearch.getFilesCSVList().forEach(this::fileParsing);
    }
    private void fileParsing(String path) {
        List<String> lines = new ArrayList<>();
        try {
            lines = Files.readAllLines(Paths.get(path));
        } catch (IOException e) {
            e.printStackTrace();
        }
        String firstLine = null;
        for (String line : lines) {
            if (firstLine == null) {
                firstLine = line;
                continue;
            }
            dataList.add(line);
        }
        for (String line : dataList) {
            String[] tokens = line.split(",");
            String nameStation = tokens[0];
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
            LocalDate localDate = LocalDate.parse(tokens[1], formatter);
            setDataList(nameStation, localDate);
        }
    }
    private void setDataList(String nameStation, LocalDate localDate) {
        for (Line line : stationMap.keySet()) {
            for (Station station : line.getStations()) {
                if (station.getNameStation().compareToIgnoreCase(nameStation) == 0) {
                    if (stationDateMap.containsKey(station)) {
                    } else stationDateMap.put(station, localDate);
                }
            }
        }
    }
    public Map<Station, LocalDate> getStationDateMap() {
        return stationDateMap;
    }
}

