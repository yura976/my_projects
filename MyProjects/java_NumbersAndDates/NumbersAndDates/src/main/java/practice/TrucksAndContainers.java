package practice;

import java.util.Scanner;

public class TrucksAndContainers {

    public static final int BOXES_IN_CONTAINER = 27;
    public static final int CONTAINERS_IN_TRUCK = 12;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int boxes = scanner.nextInt();

        int containers = boxes / BOXES_IN_CONTAINER;
        containers = (boxes % BOXES_IN_CONTAINER) == 0 ? containers : ++containers;
        int trucks = containers / CONTAINERS_IN_TRUCK;
        trucks = (containers % CONTAINERS_IN_TRUCK) == 0 ? trucks : ++trucks;
        printСargo(boxes);
        System.out.printf("Необходимо:\nгрузовиков - %d шт.\nконтейнеров - %d шт.%n",
                trucks, containers);

    }
    public static void printСargo(int boxes) {
        int boxCount = 1;
        int containerCount = 1;
        int truckCount = 0;
        while (boxes >= boxCount) {
            truckCount++;
            System.out.println("Грузовик: " + truckCount);
            int containerCounter = 0;
            while (containerCounter < CONTAINERS_IN_TRUCK && boxes >= boxCount) {
                containerCounter++;
                System.out.println("\tКонтейнер: " + containerCount++);
                int counter = 0;
                while (counter < BOXES_IN_CONTAINER && boxes >= boxCount) {
                    counter++;
                    System.out.println("\t\tЯщик: " + boxCount++);
                }
            }
        }
    }
}