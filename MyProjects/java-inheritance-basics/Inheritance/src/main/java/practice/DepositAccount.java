package practice;

import java.time.LocalDate;

public class DepositAccount extends BankAccount {
    private LocalDate lastIncome;
    private final long PERIOD = 1;

    @Override
    public void put(double amountToPut) {
        super.put(amountToPut);
        lastIncome = LocalDate.now();
    }

    @Override
    public void take(double amountToTake) {
        if (LocalDate.now().minusMonths(PERIOD).compareTo(lastIncome) >= 0) {
            super.take(amountToTake);
        }
    }
}
