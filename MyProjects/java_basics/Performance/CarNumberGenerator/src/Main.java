import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        ArrayList<Loader> loaders = new ArrayList<>();
        loaders.add(new Loader(1, 26, "res/region_1_25.txt", start));
        loaders.add(new Loader(26, 51, "res/region_26_50.txt", start));
        loaders.add(new Loader(51, 76, "res/region_51_75.txt", start));
        loaders.add(new Loader(76, 100, "res/region_76_99.txt", start));
        loaders.forEach(loader -> loader.start());
    }
}
