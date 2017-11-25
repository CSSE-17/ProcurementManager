package dao;

import models.RequisitionEntity;
import org.hibernate.Session;

public class RequisitionDAO extends GenericDAOImpl {
    public void update(RequisitionEntity req) {
        Session session = sessionFactory.openSession();

        session.beginTransaction();
        session.update(req);
        session.getTransaction().commit();

        session.close();
    }
}
