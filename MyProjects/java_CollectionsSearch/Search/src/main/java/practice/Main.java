package practice;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.TreeSet;

public class Main {
    private static final String NOT_HAS_NUMBER = "Такого номера нет!";
    private static final String HAS_NUMBER = "Такой номер есть!";
    public static void main(String[] args) {
        List<String> list = CoolNumbers.generateCoolNumbers();
        HashSet<String> hashSet = new HashSet<>(list);
        TreeSet<String> treeSet = new TreeSet<>();

        String number = "А555АА055";

        long currentTime1 = System.nanoTime();
        hasNumber(CoolNumbers.bruteForceSearchInList(list, number));
        getTime(currentTime1);

        Collections.sort(list);
        long currentTime2 = System.nanoTime();
        hasNumber(CoolNumbers.binarySearchInList(list, number));
        getTime(currentTime2);

        long currentTime3 = System.nanoTime();
        hasNumber(CoolNumbers.searchInHashSet(hashSet, number));
        getTime(currentTime3);

        long currentTime4 = System.nanoTime();
        hasNumber(CoolNumbers.searchInTreeSet(treeSet, number));
        getTime(currentTime4);
    }

    private static void hasNumber(boolean hasNumber){
        String answer = NOT_HAS_NUMBER;
        if (hasNumber) answer = HAS_NUMBER;
        System.out.println(answer);
    }

    private static void getTime(long time){
        System.out.printf("Время поиска: %d  наносекунд\n", System.nanoTime() - time);
    }
}
