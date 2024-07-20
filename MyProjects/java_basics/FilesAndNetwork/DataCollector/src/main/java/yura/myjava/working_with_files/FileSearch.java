package yura.myjava.working_with_files;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class FileSearch {
    private final List<String> filesCSVList = new ArrayList<>();
    private final List<String> filesJSONList = new ArrayList<>();
    public void iterateOverFolders(String path) {
        File folder = new File(path);
        if (folder.isFile()) {
            if (folder.getPath().endsWith(".csv")) {
                filesCSVList.add(folder.getPath());
            }
            if (folder.getPath().endsWith(".json")) {
                filesJSONList.add(folder.getPath());
            }
            return;
        }
        File[] files = folder.listFiles();
        assert files != null;
        for (File file : files) {
            iterateOverFolders(file.getPath());
        }
    }
    public List<String> getFilesCSVList() {
        return filesCSVList;
    }
    public List<String> getFilesJSONList() {
        return filesJSONList;
    }
}
