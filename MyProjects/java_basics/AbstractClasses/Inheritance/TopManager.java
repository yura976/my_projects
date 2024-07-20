public class TopManager implements Employee {
    private final double BONUS = 1.5;
    private double income;
    private static int countTopManager;
    private int numberTopManager;

    public TopManager(Company company) {
        income = company.getIncome();
        numberTopManager = ++countTopManager;
    }

    @Override
    public double getMonthSalary() {
        return income > 10_000_000 ? FIXED_SALARY_TOP_MANAGER + FIXED_SALARY_TOP_MANAGER * BONUS :
                FIXED_SALARY_TOP_MANAGER;
    }

    @Override
    public String toString() {
        return "TopManager №" + numberTopManager + ": зарплата - " + getMonthSalary();
    }
}
