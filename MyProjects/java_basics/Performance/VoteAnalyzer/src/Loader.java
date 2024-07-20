import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.File;

public class Loader {
    private static final String FILE_NAME = "/home/yura/Рабочий стол/data-1572M.xml";
    private static final long START = System.currentTimeMillis();
    public static void main(String[] args) throws Exception {
        SAXParserFactory factory = SAXParserFactory.newInstance();
        SAXParser parser = factory.newSAXParser();
        XMLHandler handler = new XMLHandler();
        parser.parse(new File(FILE_NAME), handler);
        DBConnection.executeMultiInsert();
        System.out.println("Table creation duration: "
                + (System.currentTimeMillis() - START) + " ms");
    }
}