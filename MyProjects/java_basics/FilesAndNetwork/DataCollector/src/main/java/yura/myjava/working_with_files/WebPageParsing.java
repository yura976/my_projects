package yura.myjava.working_with_files;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import yura.myjava.metro.Line;
import yura.myjava.metro.Station;

import java.io.IOException;
import java.util.*;

public class WebPageParsing {
    private Map<Line, List<Station>> stationMap;
    private Map<Line, List<Station>> subStationMap;    //stationMap without D1 & D2
    private Set<Line> lineSet;
    private Map<Station, Boolean> hasConnection;
    public void startParsing(String url) {
        Document document = getCodePage(url);
        setLines(document);
        setStations(document);
    }
    private Document getCodePage(String url) {
        Document doc = null;
        try {
            doc = Jsoup.connect(url).get();
        } catch (IOException e) {
            System.out.println("Couldn't download page.");
        }
        return doc;
    }

    private void setLines(Document document) {
        lineSet = new TreeSet<>();
        Elements elements = document.select("span[data-line]");
        for (Element line : elements) {
            lineSet.add(new Line(line.attr("data-line"), line.text()));
        }
    }
    private void setStations(Document document) {
        Elements allLines = document.select("span[data-line]");
        stationMap = new TreeMap<>();
        subStationMap = new TreeMap<>();
        hasConnection = new TreeMap<>();
        for (Element oneLine : allLines) {
            String lineName = oneLine.attr("data-line");
            Line line = new Line(lineName, oneLine.text());
            Elements stations = document.select("div[data-line=" + lineName + "] > p > span.name");
            Elements connections = document.select("div[data-line=" + lineName + "] > p > span.name, " +
                    "div[data-line=" + lineName + "] > p > span[title]");
            List<Element> connectionsList = connections.stream().toList();
            setConnections(connectionsList, line);
            stations.forEach(station -> line.addStation(new Station(line, station.text())));
            stationMap.put(line, line.getStations());
            if (!(line.getNumberLine().equals("D1") || line.getNumberLine().equals("D2"))) {
                subStationMap.put(line, line.getStations());
            }
        }
    }

    private void setConnections(List<Element> list, Line line) {
        String name;
        String nextName = null;
        boolean isConnection = false;
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).hasText()) {
                name = nextName;
                nextName = list.get(i).text();
                if (name == null) {continue;}
                hasConnection.put(new Station(line, name), isConnection);
                isConnection = false;
            }
            if (list.get(i).hasAttr("title")) {
                isConnection = true;
            }
            if (i == (list.size() - 1)) {
                hasConnection.put(new Station(line, nextName), isConnection);
            }
        }
    }
    public Map<Line, List<Station>> getStationMap() {
        return stationMap;
    }
    public Map<Line, List<Station>> getSubStationMap() {
        return subStationMap;
    }

    public Set<Line> getLineSet() {
        return lineSet;
    }

    public Map<Station, Boolean> getHasConnection() {
        return hasConnection;
    }
}
