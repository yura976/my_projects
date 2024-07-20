package ru.hibernate;

import org.hibernate.Session;

public class Main {
    public static void main(String[] args) {

        Session session = HibernateStart.getSessionFactory().openSession();
        LinkedPurchaseListExecution listExecution = new LinkedPurchaseListExecution(session);
        listExecution.createLinkedPurchaseList();
        listExecution.getStudentCourses(11);
        listExecution.getCourseStudents(11);
        session.close();
        HibernateStart.close();
    }
}