package practice;

import java.util.*;

public class CoolNumbers {
    public static final String LETTERS = "АВЕКМНОРСТУХ";
    public static final int MAX_REGION = 199;
    public static final int MAX_DIGIT = 9;
    public static final int MAX_COUNT_CAR_NUMBER = 2_500_000;
    public static List<String> generateCoolNumbers() {
        List<String> numbers = new ArrayList<>();
        for (int i = 0; i < MAX_COUNT_CAR_NUMBER; i++) {
            String firstLetter = getRandomLetter();
            String secondLetter = getRandomLetter();
            String thirdLetter = getRandomLetter();
            String digits = getRandomNumber(MAX_DIGIT);
            String region = getRandomNumber(MAX_REGION);
            String carNumber = String.format("%s%s%s%s%s%s%s",
                    firstLetter, digits,digits,digits, secondLetter, thirdLetter, region);
            numbers.add(carNumber);
        }
        return numbers;
    }

    public static boolean bruteForceSearchInList(List<String> list, String number) {
        for (String carNumber : list) {
            if (carNumber.equals(number)) {
                return true;
            }
        }
        return false;
    }

    public static boolean binarySearchInList(List<String> sortedList, String number) {
        int position = Collections.binarySearch(sortedList, number);
        return position >= 0;
    }

    public static boolean searchInHashSet(HashSet<String> hashSet, String number) {
        return hashSet.contains(number);
    }

    public static boolean searchInTreeSet(TreeSet<String> treeSet, String number) {
        return treeSet.contains(number);
    }
    private static String getRandomNumber(int to) {
        int numberPosition = (int) (Math.random() * to + 1);
        String number = "";
        String countZero = "";
        if (numberPosition < 10 && to > 9) {
            countZero = "00";
        }
        else if (numberPosition < 100 && to > 9) {
            countZero = "0";
        }
        return number.concat(countZero).concat(String.valueOf(numberPosition));
    }
    private static String getRandomLetter() {
        int letterPosition = (int) (Math.random() * LETTERS.length());
        return String.valueOf(LETTERS.charAt(letterPosition));
    }

}
