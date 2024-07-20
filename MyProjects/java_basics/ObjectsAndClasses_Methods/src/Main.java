public class Main {

    public static void main(String[] args) {
        Basket basket = new Basket();
        basket.add("Milk", 40);
        basket.add("Apple", 6);
        basket.print("Первая корзина.");
        System.out.println("Общая стоимость товаров - " + basket.getTotalPrice() + " руб.\n");

        Basket secondBasket = new Basket();
        secondBasket.add("Bread", 40, 1, 0.4);
        secondBasket.add("Eggs", 10, 10, 0.5);
        secondBasket.add("Eggs", 10, 10, 0.5);
        secondBasket.add("Matches", 4, 1);
        secondBasket.add("Matches", 4);
        secondBasket.print("\nВторая корзина.");
        System.out.println("Общая стоимость товаров - " + secondBasket.getTotalPrice() + " руб.");
        System.out.println("Общий вес товаров - " + secondBasket.getTotalWeight() + " кг.");
    }
}
