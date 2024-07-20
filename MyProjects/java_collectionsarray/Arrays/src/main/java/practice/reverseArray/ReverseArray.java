package practice.reverseArray;

public class ReverseArray {

    public static String[] reverse(String[] strings) {

        String [] resultPhone = new String[strings.length];
        for (int i = strings.length -1, j = 0; i >= 0; i--, j++) {
            resultPhone[j] = strings[i];
        }
        for (int i = 0; i < strings.length; i++) {
            String temp = resultPhone[i];
            strings[i] = temp;
        }
        return strings;
    }

}