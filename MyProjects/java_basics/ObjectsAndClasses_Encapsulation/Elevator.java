import java.util.Scanner;
public class Elevator {
    private int currentFloor;
    private final int minFloor;
    private final int maxFloor;
    private final String ERROR = "ERROR! SET ANOTHER FLOOR.";
    private final String NOT_ERROR = "ELEVATOR MOVED TO %d FLOOR";
    private final String NOT_MOVED = "ELEVATOR ON %d FLOOR";

    public Elevator(int minFloor, int maxFloor) {
        this.minFloor = minFloor;
        this.maxFloor = maxFloor;
        currentFloor = 1;
        System.out.printf((NOT_MOVED) + "%n", currentFloor);
    }

    public int getCurrentFloor() {
        return currentFloor;
    }

    public void moveUp() {
        currentFloor++;
    }

    public void moveDown() {
        currentFloor--;
    }

    public void move(int floor) {
        if (floor < minFloor || floor > maxFloor) {
            System.out.println(ERROR);
            return;
        }
        while (currentFloor != floor) {
            if (currentFloor > floor) {
                moveDown();
            } else {
                moveUp();
            }
            System.out.printf((NOT_ERROR) + "%n", currentFloor);
        }
        System.out.println(String.format(NOT_MOVED, floor) + "\n");
    }

    public static void main(String[] args) {
        Elevator elevator = new Elevator(-3, 26);
        while (true) {
            System.out.println("Введите номер этажа: ");
            int floor = new Scanner(System.in).nextInt();
            elevator.move(floor);
        }
    }
}
