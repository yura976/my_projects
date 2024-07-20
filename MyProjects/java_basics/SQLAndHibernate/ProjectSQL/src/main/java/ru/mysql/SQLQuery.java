package ru.mysql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class SQLQuery {

    private String url;
    private String user;
    private String pass;

    public SQLQuery(String url, String user, String pass) {
        this.url = url;
        this.user = user;
        this.pass = pass;
    }
    public void queryExecution(String myQuery) {
        try {
            Connection connection = DriverManager.getConnection(url, user, pass);
            Statement statement = connection.createStatement();
            ResultSet resultSet =
                    statement.executeQuery(myQuery);
            while (resultSet.next()){
                String courseName = resultSet.getString("course_name");
                String averageCountPurchases = resultSet.getString("average_count_purchases");
                System.out.println("Название курса: \""
                        + courseName
                        + "\". Среднее количество покупок за месяц в 2018 году - "
                        + averageCountPurchases
                        + ".");
            }
            resultSet.close();
            statement.close();
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
