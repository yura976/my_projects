public class Arithmetic {
    private int num1;
    private int num2;

    public Arithmetic (int value1, int value2) {
        num1 = value1;
        num2 = value2;
    }

    public int sum () {
        return num1 + num2;
    }

    public int product () {
        return num1 * num2;
    }

    public int max () {
        if (num1 >= num2) {
            return num1;
        } else {
            return num2;
        }
    }

    public int min () {
        if (num1 < num2) {
            return num1;
        } else {
            return num2;
        }
    }

    public static void main(String[] args) {
        Arithmetic arithmetic = new Arithmetic(10, 5);
        System.out.println("Сумма чисел равна: " + arithmetic.sum());
        System.out.println("Произведение чисел равно: " + arithmetic.product());
        System.out.println("Максимальное число: " + arithmetic.max());
        System.out.println("Минимальное число: " + arithmetic.min());
    }
}

