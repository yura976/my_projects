import java.util.Random;

public class Main {
    private static final int COUNT_USERS = 20;
    private static final int SLEEP_CYCLE = 1000;

    public static void main(String[] args) throws InterruptedException {
        RedisStorage rs = new RedisStorage();
        rs.init();
        while (true) {
            int userName = 1;
            int paymentItem1 = randomNumber(0, COUNT_USERS / 2 - 1);
            int paymentItem2 = randomNumber(COUNT_USERS / 2, COUNT_USERS - 1);
            int userPaidService1 = randomNumber(1, COUNT_USERS);
            int userPaidService2 = randomNumber(1, COUNT_USERS);

            for (int i = 0; !rs.containsAllUsers(); i++) {
                if (i == paymentItem1) {
                    rs.addUserWithPayment(paymentItem1, userPaidService1);
                } else if (i == paymentItem2){
                    rs.addUserWithPayment(paymentItem2, userPaidService2);
                } else i = rs.addUserWithoutPayment(i, userName++) ? i : i - 1;
            }
            Thread.sleep(SLEEP_CYCLE);
            rs.deleteAllUsers();
        }
    }
    private static int randomNumber(int min, int max) {
        return new Random().nextInt((max - min) + 1) + min;
    }
}