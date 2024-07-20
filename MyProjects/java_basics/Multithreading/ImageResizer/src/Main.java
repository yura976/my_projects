import java.io.File;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        String srcFolder = "/home/yura/workFolder/src";
        String dstFolder = "/home/yura/workFolder/dst";
        int newWidth = 600;
        int newHeight = 450;
        int countPro = 4;
        long start = System.currentTimeMillis();

        List<File[]> filesList = new CreatingArraysImages().getFiles(srcFolder, countPro);
        filesList.forEach(files -> new ImageResizer(files, newWidth, newHeight, dstFolder, start).start());
    }
}