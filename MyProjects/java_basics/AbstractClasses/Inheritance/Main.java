public class Main {
    public static void main(String[] args) {
        Company company = new Company();
        for (int i = 0; i < 180; i++) {company.hire(new Operator());}
        for (int i = 0; i < 80; i++) {company.hire(new Manager(company));}
        for (int i = 0; i < 10; i++) {company.hire(new TopManager(company));}
        System.out.println("15 самых высоких зарплат: ");
        company.getTopSalaryStaff(15).forEach(System.out::println);
        System.out.println("\n30 самых низких зарплат: ");
        company.getLowestSalaryStaff(30).forEach(System.out::println);

        int sizeNewList = company.getEmployeesList().size() / 2;
        for (int i = 0; i < sizeNewList; i++) {
            company.fire(company.getRandomEmployee());
        }
        System.out.println();
        System.out.println("15 самых высоких зарплат: ");
        company.getTopSalaryStaff(15).forEach(System.out::println);
        System.out.println("\n30 самых низких зарплат: ");
        company.getLowestSalaryStaff(30).forEach(System.out::println);
    }
}
