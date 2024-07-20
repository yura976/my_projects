import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class CreatingArraysImages {
    private List<File[]> filesList = new ArrayList<>();
    private void setFiles(String srcFolder, int countPro) {
        File srcDir = new File(srcFolder);
        File[] files = srcDir.listFiles();
        int lengthOneArray = files.length / countPro;
        int beginArray = 0;
        for (int i = 0; i < countPro; i++) {
            if (i == (countPro - 1)) {
                lengthOneArray = files.length- beginArray;
            }
            File[] filesArray = new File[lengthOneArray];
            System.arraycopy(files, beginArray, filesArray, 0, filesArray.length);
            filesList.add(filesArray);
            beginArray += lengthOneArray;
        }
    }
    public List<File[]> getFiles(String srcFolder, int countPro) {
        setFiles(srcFolder, countPro);
        List<File[]> copyFiles = filesList;
        return copyFiles;
    }
}