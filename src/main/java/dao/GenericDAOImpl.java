package dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import org.hibernate.Query;
import java.util.List;

public class GenericDAOImpl<T> implements GenericDAO<T> {
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

    @Override
    public void create(T o) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();

        session.save(o);

        session.getTransaction().commit();
        session.close();
    }

    @Override
    public List read(Class c) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();

        Query query = session.createQuery("from " + c.getName());
        List list = query.list();

        session.getTransaction().commit();
        session.close();
        return list;
    }

    @Override
    public void delete(T instance) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();

        session.delete(instance);

        session.getTransaction().commit();
        session.close();
    }

}
