package practice.string;

public class SequentialWordsNumbers {

    public static String sequentialWordsNumbers(String text){
        String resultString = "";
        int indexWord;
        int indexSpace = 0;
        int step = 0;
        int count = 1;
        while (indexSpace >= 0) {
            indexSpace = text.indexOf(' ', step);
            if (text.length() == 0) {continue;}
            if (indexSpace == step) {
                resultString = resultString.concat(String.valueOf(text.charAt(indexSpace)));
                step++;
            } else {
                indexWord = step;
                step = text.indexOf(' ', step);
                if (step < 0) {
                    indexSpace = -1;
                    step = text.length();
                }
                resultString = resultString.concat("(" + count++ + ") ");
                resultString = resultString.concat(text.substring(indexWord, step));
            }
        }
        return resultString;
    }

    public static void main(String[] args) {
        System.out.println(sequentialWordsNumbers("  ssss   sssss ssssss sssss"));
    }
}
