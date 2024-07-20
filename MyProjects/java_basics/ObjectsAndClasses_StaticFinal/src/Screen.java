public record Screen(double diagonal, ScreenType screenType, double weight) {
    public Screen setDiagonal(double diagonal) {
        return new Screen(diagonal, screenType, weight);
    }
    public Screen setScreenType(ScreenType screenType) {
        return new Screen(diagonal, screenType, weight);
    }
    public Screen setWeight(double weight) {
        return new Screen(diagonal, screenType, weight);
    }
    @Override
    public String toString() {
        return "\n\tЭкран:\n"
                + "\t\t- диагональ: " + diagonal + "\n"
                + "\t\t- тип: " + screenType + "\n"
                + "\t\t- вес: " + weight + ".";
    }
}
