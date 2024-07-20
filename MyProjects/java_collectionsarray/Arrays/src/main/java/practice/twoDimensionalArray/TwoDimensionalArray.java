package practice.twoDimensionalArray;

public class TwoDimensionalArray {
    public static final char SYMBOL = 'X';
    public static char[][] getTwoDimensionalArray(int size) {

        char [][] twoDimensionalArray = new char[size][size];
        for (int i = 0; i < twoDimensionalArray.length; i++) {
            for (int j = 0; j < twoDimensionalArray[i].length; j++) {
                if (j == i || j == (twoDimensionalArray[j].length - 1) - i) {
                    twoDimensionalArray[i][j] = SYMBOL;
                }
                else {
                    twoDimensionalArray[i][j] = ' ';
                }
            }
        }
        return twoDimensionalArray;
    }
}

// массив должен содержать символ SYMBOL по диагоналям, пример для size = 3
// [X,  , X]
// [ , X,  ]
// [X,  , X]
