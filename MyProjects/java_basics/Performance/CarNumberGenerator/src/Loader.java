import java.io.FileNotFoundException;
import java.io.PrintWriter;

public class Loader extends Thread {
    private char letters[] = {'У', 'К', 'Е', 'Н', 'Х', 'В', 'А', 'Р', 'О', 'С', 'М', 'Т'};
    private int beginRegionCode;
    private int endRegionCode;
    private String finalFile;
    private long start;
    public Loader(int beginRegionCode, int endRegionCode, String finalFile, long start) {
        this.beginRegionCode = beginRegionCode;
        this.endRegionCode = endRegionCode;
        this.finalFile = finalFile;
        this.start = start;
    }
    @Override
    public void run() {
        try {
            PrintWriter writer = new PrintWriter(finalFile);
            while (beginRegionCode < endRegionCode) {
                StringBuffer stringBuffer = new StringBuffer();
                for (int number = 1; number < 1000; number++) {
                    for (char firstLetter : letters) {
                        for (char secondLetter : letters) {
                            for (char thirdLetter : letters) {
                                stringBuffer.append(firstLetter);
                                stringBuffer.append(padNumber(number, 3));
                                stringBuffer.append(secondLetter);
                                stringBuffer.append(thirdLetter);
                                stringBuffer.append(padNumber(beginRegionCode, 2));
                                stringBuffer.append("\n");
                            }
                        }
                    }
                }
                writer.write(stringBuffer.toString());
                beginRegionCode++;
            }
            writer.flush();
        writer.close();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        System.out.println("Duration: " + (System.currentTimeMillis() - start) + " ms");
    }
    private String padNumber(int number, int numberLength) {
        StringBuffer stringBuffer = new StringBuffer();
        int padSize = numberLength - Integer.toString(number).length();
        for (int i = 0; i < padSize; i++) {
            stringBuffer.append('0');
        }
        stringBuffer.append(number);
        return stringBuffer.toString();
    }
}
