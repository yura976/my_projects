import java.util.*;

public class Company {
    private List<Employee> employeesList = new ArrayList<>();
    private final int START_SUB_EMPLOYEES_LIST = 0;
    private double income;
    public List<Employee> getTopSalaryStaff(int count) {
        return sortEmployee(Collections.reverseOrder(new EmployeeComparator()), count);
    }
    public List<Employee> getLowestSalaryStaff(int count) {
        return sortEmployee(new EmployeeComparator(), count);
    }
    private List<Employee> sortEmployee(Comparator comparator, int count) {
        List<Employee> copyEmployeesList = new ArrayList<>(employeesList);
        copyEmployeesList.sort(comparator);
        return copyEmployeesList.subList(START_SUB_EMPLOYEES_LIST, count);
    }
    public void hire(Employee employee) {
        employeesList.add(employee);
    }
    public void hireAll(Collection<Employee> employeeList) {
        employeesList.addAll(employeeList);
    }
    public void fire(Employee employee) {
        employeesList.remove(employee);
    }
    public double getIncome() {
        return income;
    }
    public void setIncome(double earnedMoney) {
        income += earnedMoney;
    }
    public List<Employee> getEmployeesList() {
        return new ArrayList<>(employeesList);
    }
    public Employee getRandomEmployee() {
        List<Employee> copyEmployeesList = new ArrayList<>(employeesList);
        Collections.shuffle(copyEmployeesList);
        return copyEmployeesList.get(0);
    }
}