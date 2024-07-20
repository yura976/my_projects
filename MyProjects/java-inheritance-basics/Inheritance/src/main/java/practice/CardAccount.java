package practice;

public class CardAccount extends BankAccount {
    private final double COMMISSION = 0.01;
    @Override
    public void take(double amountToTake) {
        amountToTake += amountToTake * COMMISSION;
        super.take(amountToTake);
    }
}
