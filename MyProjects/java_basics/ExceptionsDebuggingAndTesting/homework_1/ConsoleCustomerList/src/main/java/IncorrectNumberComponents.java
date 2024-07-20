public class IncorrectNumberComponents extends RuntimeException{
    private final String TEXT_ERROR = "Please re-enter!\n" +
            "You entered %d component, after the \"%s\" command you must enter %d component.";

    public IncorrectNumberComponents(int countComponents, String instruction, int requiredQuantity) {
        System.out.printf((TEXT_ERROR) + "%n", countComponents, instruction, requiredQuantity);
    }
}
