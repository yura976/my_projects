package ru.hibernate;

import jakarta.persistence.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import java.util.List;

public class CreateLinkedPurchaseList {
    private Session session;
    public CreateLinkedPurchaseList(Session session) {
        this.session = session;
    }

    public void setLinkedPurchaseList() {
        List<Object[]> list = setIdList();
        for (Object[] obj : list) {
            Transaction transaction = session.beginTransaction();
            LinkedPurchaseList lpl = new LinkedPurchaseList();
            LinkedPurchaseKey lpk = new LinkedPurchaseKey();
            lpk.setStudentId((Long) obj[0]);
            lpk.setCourseId((Long) obj[1]);
            lpl.setId(lpk);
            session.save(lpl);
            transaction.commit();
        }
    }
    private List<Object[]> setIdList() {
        Query query = session.createQuery(
                "SELECT s.id, c.id FROM PurchaseList p " +
                "JOIN Student s ON s.name = p.studentName " +
                "JOIN Course c ON c.name = p.courseName");
        List<Object[]> list = query.getResultList();
        return list;
    }
}
