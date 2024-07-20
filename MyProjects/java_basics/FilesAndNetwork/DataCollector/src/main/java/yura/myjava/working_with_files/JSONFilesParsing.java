package yura.myjava.working_with_files;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import yura.myjava.metro.Line;
import yura.myjava.metro.Station;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class JSONFilesParsing {
    private Map<Station, String> stationDepthMap;
    private Map<Line, List<Station>> stationMap;      //stationMap without D1 & D2
    public void setStationDepthMap(WebPageParsing webPageParsing, FileSearch fileSearch){
        stationDepthMap = new HashMap<>();
        stationMap = webPageParsing.getSubStationMap();
        fileSearch.getFilesJSONList().forEach(this::fileParsing);
    }
    private void fileParsing(String path) {
        try {
            JSONParser parser = new JSONParser();
            JSONArray linesArray = (JSONArray) parser.parse(getJsonFile(path));
            parseDepth(linesArray);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    private String getJsonFile(String path) {
        StringBuilder builder = new StringBuilder();
        try {
            List<String> lines = Files.readAllLines(Paths.get(path));
            lines.forEach(builder::append);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return builder.toString();
    }
    private void parseDepth(JSONArray jsonArray) {
        jsonArray.forEach(lineObject -> {
            JSONObject lineJsonObject = (JSONObject) lineObject;
            String nameStation = (String) lineJsonObject.get("station_name");
            String depth = (String) lineJsonObject.get("depth");
            depth = depth.replace(",", ".");
            Station station = getCurrentStation(nameStation);
            if (station != null) {
                stationDepthMap.put(station, depth);
            }
        });
    }
    private Station getCurrentStation(String nameStation) {
        Station currentStation = null;
        for (Line line : stationMap.keySet()) {
            for (Station station : line.getStations()) {
                if (station.getNameStation().compareToIgnoreCase(nameStation) == 0) {
                    if (stationDepthMap.containsKey(station)) {
                    } else currentStation = station;
                }
            }
        }
        return currentStation;
    }
    public Map<Station, String> getStationDepthMap() {
        return stationDepthMap;
    }
}
