package dao;

import models.DeliveryNoteEntity;
import models.RequisitionItemEntity;
import models.UserAccountEntity;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import sun.net.www.content.text.Generic;

import java.util.List;

/**
 * Created by HPkavin on 11/25/2017.
 */
public class DiliveryNoteDAO extends GenericDAOImpl {


    public void update(DeliveryNoteEntity note) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.update(note);
        session.getTransaction().commit();

        session.close();
    }
//    public void updateItem(RequisitionItemEntity item) {
//        Session session = sessionFactory.openSession();
//        session.beginTransaction();
//        session.update(item);
//        session.getTransaction().commit();
//
//        session.close();
//    }

    public List<DeliveryNoteEntity> readItems(String reqId) throws Exception {

        List results;
        try (Session session = sessionFactory.openSession()) {

            Criteria cr = session.createCriteria(DeliveryNoteEntity.class);
            cr.add(Restrictions.eq("requisitionID", reqId));
            results = cr.list();
            session.beginTransaction().commit();
            session.beginTransaction();
        }
        return results;


    }

//    public DeliveryNoteEntity readList(String reqId) {
//        Session session = sessionFactory.openSession();
//        UserAccountEntity user = session.get(UserAccountEntity.class, reqId);
//        session.close();
//        return user;
//    }


}
