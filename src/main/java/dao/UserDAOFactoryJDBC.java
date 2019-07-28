package dao;

public class UserDAOFactoryJDBC implements UserDAOFactory {

    private UserDAO userDAO;

    public UserDAOFactoryJDBC() {
        this.userDAO = new UserDAOImplJDBC();
    }

    @Override
    public UserDAO getUserDAO() {
        return userDAO;
    }
}
