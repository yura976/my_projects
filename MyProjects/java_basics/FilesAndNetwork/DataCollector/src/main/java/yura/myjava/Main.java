package yura.myjava;

import yura.myjava.working_with_files.CreatingFiles;

public class Main {
    public static void main(String[] args) {
        String url = "https://skillbox-java.github.io/";
        String path = "data/stations-data/";
        String outFile1 = "myJSON/result1.json";
        String outFile2 = "myJSON/result2.json";
        CreatingFiles creatingFiles = new CreatingFiles();
        creatingFiles.dataCollection(url, path);
        creatingFiles.stationList(outFile1);
        creatingFiles.propertiesList(outFile2);
    }
}