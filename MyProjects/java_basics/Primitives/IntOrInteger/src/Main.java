public class Main {
    public static void main(String[] args) {
        Container container = new Container ();
        container.addCount(5672);
        System.out.println(container.getCount());

        for (int i = 1040; i <= 1103; i++) {
            if (i == 1046 || i == 1078) {
                int j = (i == 1046) ? 1025 : 1105;
                char anotherRusSymbol = (char) j;
                System.out.println(j + " - " + anotherRusSymbol);
            }
            char rusSymbol = (char) i;
            System.out.println(i + " - " + rusSymbol);
        }
    }
}
