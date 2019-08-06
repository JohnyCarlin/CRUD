package dao;

public class UserDAOFactoryHibernate implements UserDAOFactory {

    private UserDAO userDAO;

    public UserDAOFactoryHibernate() {
        this.userDAO = new UserDAOImplHibernate();
    }

    @Override
    public UserDAO getUserDAO() {
        return userDAO;
    }
}
