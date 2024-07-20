public class Manager implements Employee {
    private final int MAX_EARNED_MONEY = 140_000;
    private final int MIN_EARNED_MONEY = 115_000;
    private final double BONUS = 0.05;
    private double earnedMoney;
    private static int countManager = 0;
    private int numberManager;

    public Manager(Company company) {
        earnedMoney = (Math.random() * (MAX_EARNED_MONEY - MIN_EARNED_MONEY) ) + MIN_EARNED_MONEY;
        company.setIncome(earnedMoney);
        numberManager = ++countManager;
    }

    @Override
    public double getMonthSalary() {
        return Math.round((FIXED_SALARY_MANAGER + earnedMoney * BONUS) * 100.0) / 100.0;
    }


    @Override
    public String toString() {
        return "Manager №" + numberManager + ": зарплата - " + getMonthSalary();
    }
}

