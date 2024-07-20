public class Operator implements Employee {
    private static int countOperator;
    private int numberOperator;
    public Operator() {
        numberOperator = ++countOperator;
    }

    @Override
    public double getMonthSalary() {
        return FIXED_SALARY_OPERATOR;
    }

    @Override
    public String toString() {
        return "Operator №" + numberOperator + ": зарплата - " + getMonthSalary();
    }
}
