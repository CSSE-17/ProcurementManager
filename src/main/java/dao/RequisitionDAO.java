package dao;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import models.RequisitionEntity;
import models.UserAccountEntity;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import javax.management.Query;
import java.util.List;
/**
 * Created by HPkavin on 11/22/2017.
 */
public class RequisitionDAO {
    protected SessionFactory sessionFactory;

    public void setup() {
        final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure() // configures settings from hibernate.cfg.xml
                .build();
        try {
            sessionFactory = new MetadataSources(registry).buildMetadata().buildSessionFactory();
        } catch (Exception ex) {
            StandardServiceRegistryBuilder.destroy(registry);
        }
    }

    public void exit() {
        sessionFactory.close();
    }

    public void create(RequisitionEntity NewReq) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();

        session.save(NewReq);

        session.getTransaction().commit();
        session.close();

    }

    public RequisitionEntity read(String id) {
        Session session = sessionFactory.openSession();
        RequisitionEntity RequisitionEnty = session.get(RequisitionEntity.class, id);
        session.close();
        return RequisitionEnty;
    }

    public void update(RequisitionEntity NewReq) {
        Session session = sessionFactory.openSession();

        session.beginTransaction();
        session.update(NewReq);
        session.getTransaction().commit();
        session.close();
    }
    public ObservableList<RequisitionEntity> getAll() {

            Session session = sessionFactory.openSession();
            ObservableList<RequisitionEntity> reqList = FXCollections.observableArrayList();
            List<RequisitionEntity> rList = session.
                    createCriteria(RequisitionEntity.class).list();
            for (RequisitionEntity ent : rList) {
                reqList.add(ent);
            }
            return reqList;
        }

    }




