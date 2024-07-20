import java.util.concurrent.ForkJoinPool;

public class Main {
    public static String url = "https://skillbox.ru/";
    public static String filePath = "/home/yura/workFolder/siteMap.txt";

    public static void main(String[] args) {
        NodeSite nodeSite = new NodeSite(url);
        SiteMapRecursiveTask task = new SiteMapRecursiveTask(nodeSite);
        new ForkJoinPool().invoke(task);
        new WriteToFile(nodeSite, filePath).writeFile();
    }
}
