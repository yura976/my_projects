package yura.myjava.working_with_files;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import yura.myjava.metro.Line;
import yura.myjava.metro.Station;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class StationsAndLinesData {
    private final Set<Line> lineSet;
    private final Map<Line, List<Station>> stationMap;

    public StationsAndLinesData(Map<Line, List<Station>> stationMap, Set<Line> lineSet) {
        this.stationMap = stationMap;
        this.lineSet = lineSet;
    }
    public JSONArray createLinesData() {
        JSONArray array = new JSONArray();
        lineSet.forEach(line -> {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("number", line.getNumberLine());
            jsonObject.put("name", line.getNameLine());
            array.add(jsonObject);
        });
        return array;
    }
    public List<JSONObject> createStationsData() {
        List<JSONObject> list = new ArrayList<>();
        stationMap.forEach((line, stations) -> {
            JSONArray array = new JSONArray();
            line.getStations().forEach(station -> array.add(station.getNameStation()));
            JSONObject jsonObject = new JSONObject();
            jsonObject.put(line.getNumberLine(), array);
            list.add(jsonObject);
        });
        return list;
    }
}
