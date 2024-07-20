package practice.reverseArray;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        String line = "Каждый охотник желает знать, где сидит фазан";
        line = line.replaceAll(",", "");
        String [] text = line.split("[\\s+]");
        String [] result = ReverseArray.reverse(text);
        System.out.println(Arrays.toString(result));
    }
}
