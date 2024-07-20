public record Keyboard(KeyboardType keyboardType, HasBacklight hasBacklight, double weight) {
    public Keyboard setKeyboardType(KeyboardType keyboardType) {
        return new Keyboard(keyboardType, hasBacklight, weight);
    }
    public Keyboard setHasBacklight(HasBacklight hasBacklight) {
        return new Keyboard(keyboardType, hasBacklight, weight);
    }
    public Keyboard setWeight(double weight) {
        return new Keyboard(keyboardType, hasBacklight, weight);
    }
    @Override
    public String toString() {
        return "\n\tКлавиатура:\n"
                + "\t\t- тип: " + keyboardType + "\n"
                + "\t\t- наличие подсветки: " + hasBacklight + "\n"
                + "\t\t- вес: " + weight + ".";
    }
}
