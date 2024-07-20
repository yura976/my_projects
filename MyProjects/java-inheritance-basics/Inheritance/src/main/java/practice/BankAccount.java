package practice;

public class BankAccount {
  private double amount = 0.0;
  public double getAmount() {
    return amount;
  }

  public void put(double amountToPut) {
    if (amountToPut < 0) {return;}
    amount += amountToPut;
  }

  public void take(double amountToTake) {
    double afterAmountToTake = amount - amountToTake;
    if (afterAmountToTake >= 0) {amount = afterAmountToTake;}
  }
}

