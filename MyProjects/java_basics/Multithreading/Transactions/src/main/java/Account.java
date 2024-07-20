public class Account implements Comparable<Account>{
    private long money;
    private String accNumber;
    private boolean isBloked;

    public Account() {
        this.isBloked = false;
    }
    public long getMoney() {
        return money;
    }
    public void setMoney(long money) {
        this.money = money;
    }
    public String getAccNumber() {
        return accNumber;
    }
    public void setAccNumber(String accNumber) {
        this.accNumber = accNumber;
    }
    public boolean isBloked() {return isBloked;}
    public void setBloked(boolean bloked) {isBloked = bloked;}

    @Override
    public String toString() {
        return "Account number - " + accNumber + ", money - " + money + " " + isBloked;
    }
    @Override
    public int compareTo(Account o) {
        return this.getAccNumber().compareTo(o.accNumber);
    }
}
