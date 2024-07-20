package ru.hibernate;

import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class LinkedPurchaseListExecution {
    private Session session;

    public LinkedPurchaseListExecution(Session session) {
        this.session = session;
    }
    public void createLinkedPurchaseList() {
        CreateLinkedPurchaseList purchaseList = new CreateLinkedPurchaseList(session);
        purchaseList.setLinkedPurchaseList();
    }
    public void getStudentCourses(int number) {
        Transaction transaction = session.beginTransaction();
        Course course = session.get(Course.class, number);
        List<Student> studentList = course.getCourseStudents();
        System.out.println("Студенты курса " + course.getName()
                + "  - " + course.getType() + ":");
        for (Student student : studentList) {
            System.out.println(student.getName());
        }
        transaction.commit();
    }
    public void getCourseStudents(int number) {
        Transaction transaction = session.beginTransaction();
        Student student = session.get(Student.class, number);
        List<Course> courseList = student.getStudentCourses();
        System.out.println("\nКурсы студента - " + student.getName() + ":");
        for (Course course : courseList) {
            System.out.println(course.getName());
        }
        transaction.commit();
    }
}
