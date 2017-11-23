package dao;

import models.UserAccountEntity;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

public class UserDAO {
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

    public void create() {
        UserAccountEntity user = new UserAccountEntity();
        user.setUserName("admin");
        user.setPassword("1qaz2wsx");
        user.setLastName("Tennakoon");
        user.setFirstName("Mahendra");
        user.setAccType("admin");
        user.setAccessPrivileges("101010");
        user.setEmail("mahendrathennakoon@gmail.com");

        Session session = sessionFactory.openSession();
        session.beginTransaction();

        session.save(user);

        session.getTransaction().commit();
        session.close();
    }

    public UserAccountEntity read(String username) {
        Session session = sessionFactory.openSession();
        UserAccountEntity user = session.get(UserAccountEntity.class, username);
        session.close();
        return user;
    }

    public void update(UserAccountEntity user) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.update(user);
        session.getTransaction().commit();
        session.close();
    }

    public void delete() {
        // code to remove a user
    }
}
