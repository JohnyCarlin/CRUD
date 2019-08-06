package dao;

import config.DBHelper;
import model.User;
import org.hibernate.*;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

import java.util.List;

public class UserDAOImplHibernate implements UserDAO {

    private Configuration configuration;

    public final SessionFactory sessionFactory;

    public UserDAOImplHibernate() {
        this.configuration = DBHelper.getConfiguration();
        sessionFactory = createSessionFactory(configuration);
    }

    private static SessionFactory createSessionFactory(Configuration configuration) {
        StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder();
        builder.applySettings(configuration.getProperties());
        ServiceRegistry serviceRegistry = builder.build();
        return configuration.buildSessionFactory(serviceRegistry);
    }

    public User getUserByID(Integer id) throws HibernateException {
        Session session = this.sessionFactory.openSession();
        return session.load(User.class, id);
    }

    @SuppressWarnings("unchecked")
    public List<User> getAllUsers() {
        Session session = this.sessionFactory.openSession();
        return session.createQuery("from User", User.class).list();
    }

    public void addNewUser(User user) {
        Session session = this.sessionFactory.openSession();
        Transaction trx = null;
        try {
            trx = session.beginTransaction();
            session.save(user);
            trx.commit();
        } catch (Exception e) {
            if (trx != null) trx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    public boolean removeUser(Integer id) {
        Session session = this.sessionFactory.openSession();
        User user = session.load(User.class, id);
        Transaction trx = null;
        try {
            trx = session.beginTransaction();
            if (user != null) {
                session.delete(user);
                trx.commit();
            }
            return true;
        } catch (Exception e) {
            if (trx != null) trx.rollback();
            e.printStackTrace();
            return false;
        } finally {
            session.close();
        }
    }

    public boolean editExistingUser(User user) {
        Session session = this.sessionFactory.openSession();
        Transaction trx = null;
        try {
            trx = session.beginTransaction();
            session.update(user);
            trx.commit();
            return true;
        } catch (Exception e) {
            if (trx != null) trx.rollback();
            e.printStackTrace();
            return false;
        } finally {
            session.close();
        }
    }
}
