package dao;

import model.User;
import org.hibernate.*;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

import java.util.List;

public class UserDAOImplHibernate implements UserDAO {

    private static final String hibernate_show_sql = "true";
    private static final String hibernate_hbm2ddl_auto = "update";

    public final SessionFactory sessionFactory;

    public UserDAOImplHibernate() {
        Configuration configuration = getMySqlConfiguration();
        sessionFactory = createSessionFactory(configuration);
    }

    @SuppressWarnings("UnusedDeclaration")
    private Configuration getMySqlConfiguration() {
        Configuration configuration = new Configuration();
        configuration.addAnnotatedClass(User.class);
        configuration.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQLDialect");
        configuration.setProperty("hibernate.connection.driver_class", "com.mysql.jdbc.Driver");
        configuration.setProperty("hibernate.connection.url", "jdbc:mysql://localhost:3306/demo");
        configuration.setProperty("hibernate.connection.username", "java");
        configuration.setProperty("hibernate.connection.password", "cookingpies");
        configuration.setProperty("hibernate.show_sql", hibernate_show_sql);
        configuration.setProperty("hibernate.hbm2ddl.auto", hibernate_hbm2ddl_auto);
        return configuration;
    }

    private static SessionFactory createSessionFactory(Configuration configuration) {
        StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder();
        builder.applySettings(configuration.getProperties());
        ServiceRegistry serviceRegistry = builder.build();
        return configuration.buildSessionFactory(serviceRegistry);
    }

    public User getUserbyID(Integer id) throws HibernateException {
        Session session = this.sessionFactory.openSession();
        return session.load(User.class, id);
    }

    public void addNewUser(User user) {
        Session session = this.sessionFactory.openSession();
        Transaction trx = session.beginTransaction();
        session.save(user);
        trx.commit();
        session.close();
    }

    @SuppressWarnings("unchecked")
    public List<User> getAllUsers() {
        Session session = this.sessionFactory.openSession();
        Criteria criteria = session.createCriteria(User.class);
        return criteria.list();
//        return session.createQuery("from User").list();
    }

    public boolean removeUser(Integer id) {
        Session session = this.sessionFactory.openSession();
        User user = session.load(User.class, id);
        Transaction trx = session.beginTransaction();

        if (user != null) {
            session.delete(user);
            trx.commit();
            return true;
        }
        return false;
    }

    public boolean editExistingUser(User user) {
        Session session = this.sessionFactory.openSession();
        Transaction trx = session.beginTransaction();
        session.update(user);
        trx.commit();
        session.close();
        return true;
    }
}
