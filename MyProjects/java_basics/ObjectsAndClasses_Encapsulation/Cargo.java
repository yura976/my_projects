public class Cargo {
    private final Dimensions dimensions;
    private final double weight;
    private final String address;
    private final boolean canTurnOver;
    private final String registrationNumber;
    private final boolean isFragile;

    public Cargo(Dimensions dimensions, double weight, String address,
                 boolean canTurnOver, String registrationNumber, boolean isFragile) {
        this.dimensions = dimensions;
        this.weight = weight;
        this.address = address;
        this.canTurnOver = canTurnOver;
        this.registrationNumber = registrationNumber;
        this.isFragile = isFragile;
    }

    public Dimensions getDimensions() {
        return dimensions;
    }

    public double getWeight() {
        return weight;
    }

    public String getAddress() {
        return address;
    }

    public boolean isCanTurnOver() {
        return canTurnOver;
    }

    public String getRegistrationNumber() {
        return registrationNumber;
    }

    public boolean isFragile() {
        return isFragile;
    }

    public Cargo setDimensions(Dimensions dimensions) {
        return new Cargo(dimensions, weight, address, canTurnOver, registrationNumber, isFragile);
    }
    public Cargo setWeight(double weight) {
        return new Cargo(dimensions, weight, address, canTurnOver, registrationNumber, isFragile);
    }
    public Cargo setAddress(String address) {
        return new Cargo(dimensions, weight, address, canTurnOver, registrationNumber, isFragile);
    }
    public Cargo setCanTurnOver(boolean canTurnOver) {
        return new Cargo(dimensions, weight, address, canTurnOver, registrationNumber, isFragile);
    }
    public Cargo setRegistrationNumber(String registrationNumber) {
        return new Cargo(dimensions, weight, address, canTurnOver, registrationNumber, isFragile);
    }
    public Cargo setIsFragile(boolean isFragile) {
        return new Cargo(dimensions, weight, address, canTurnOver, registrationNumber, isFragile);
    }

    @Override
    public String toString() {
        return "Груз: " +
                "\nГабариты - " + dimensions +
                "\nМасса - " + weight + " кг." +
                "\nАдрес доставки - " + address + '\'' +
                "\nМожно ли переворачивать - " + canTurnOver +
                "\nРегистрационный номер - " + registrationNumber +
                "\nЯвляется ли груз хрупким " + isFragile + ".";
    }
}
