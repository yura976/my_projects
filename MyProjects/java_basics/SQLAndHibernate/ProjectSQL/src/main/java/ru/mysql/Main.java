package ru.mysql;

public class Main {
    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/skillbox";
        String user = "root";
        String pass = "rootROOT";
        String myQuery = "SELECT course_name, count(*)/(month(max(subscription_date)) - " +
                "(month(min(subscription_date)) - 1)) average_count_purchases " +
                "FROM skillbox.PurchaseList " +
                "group by course_name";
        SQLQuery sqlQuery = new SQLQuery(url, user, pass);
        sqlQuery.queryExecution(myQuery);
    }
}
