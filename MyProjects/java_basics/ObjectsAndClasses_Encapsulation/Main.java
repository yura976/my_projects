public class Main {
    public static void main(String[] args) {
        Dimensions dimensions = new Dimensions(0.5, 0.6, 0.8);
        Cargo cargo = new Cargo(dimensions, 20.0,
                "Минск", true, "AB203055", true);
        System.out.println(cargo);

        Dimensions copyDimensions = dimensions.setWidth(0.2);
        copyDimensions = copyDimensions.setHeight(0.8);
        copyDimensions = copyDimensions.setLength(1.0);

        Cargo copyCargo = cargo.setDimensions(copyDimensions);
        System.out.println("\n" + cargo);
        System.out.println("\n" + copyCargo);

        Cargo copyCargo1 = cargo.setAddress("Гродно");
        System.out.println("\n" + cargo);
        System.out.println("\n" + copyCargo1);

        Cargo copyCargo2 = cargo.setWeight(31.5);
        System.out.println("\n" + cargo);
        System.out.println("\n" + copyCargo2);
    }
}
