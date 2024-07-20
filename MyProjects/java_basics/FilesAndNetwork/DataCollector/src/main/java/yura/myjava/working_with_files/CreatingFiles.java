package yura.myjava.working_with_files;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.json.simple.JSONObject;

import java.io.File;
import java.io.FileWriter;

public class CreatingFiles {
    private final WebPageParsing web;
    private final FileSearch search;
    private final CSVFilesParsing csvParsing;
    private final JSONFilesParsing jsonParsing;
    public CreatingFiles() {
        web = new WebPageParsing();
        search = new FileSearch();
        csvParsing = new CSVFilesParsing();
        jsonParsing = new JSONFilesParsing();
    }
    public void dataCollection(String url, String path){
        web.startParsing(url);
        search.iterateOverFolders(path);
        csvParsing.setStationDateMap(web, search);
        jsonParsing.setStationDepthMap(web, search);
    }
    public void stationList(String pathFile){
        JSONObject jObj = new JSONObject();
        StationsAndLinesData data = new StationsAndLinesData(web.getStationMap(), web.getLineSet());
        jObj.put("stations", data.createStationsData());
        jObj.put("lines", data.createLinesData());
        writeJson(jObj, pathFile);
    }
    public void propertiesList(String pathFile){
        JSONObject jObj = new JSONObject();
        StationsData data = new StationsData(web.getSubStationMap(),
                web.getHasConnection(), jsonParsing.getStationDepthMap(), csvParsing.getStationDateMap());
        jObj.put("stations", data.createStationData());
        writeJson(jObj, pathFile);
    }
    private void writeJson(JSONObject jObj, String pathFile) {
        File jsonFile = new File(pathFile);
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String json = gson.toJson(jObj);
        try (FileWriter fileWriter = new FileWriter(jsonFile)) {
            fileWriter.write(json);
            fileWriter.flush();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
