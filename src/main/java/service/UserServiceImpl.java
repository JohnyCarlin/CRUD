package service;

import dao.UserDAO;
import dao.UserDAOFactory;
import model.User;

import java.util.List;

public class UserServiceImpl implements UserService {

    private UserDAO userDAO;
    private static UserServiceImpl userService = null;

    private UserServiceImpl() {
        this.userDAO =  UserDAOFactory.getUserDAOFactory().getUserDAO();
    }

    public static UserServiceImpl getUserService() {
        if (userService == null) {
            userService = new UserServiceImpl();
        }
        return userService;
    }

    @Override
    public void addNewUser(User user) {
        userDAO.addNewUser(user);
    }

    @Override
    public User getUserByID(Integer id) {
        return userDAO.getUserByID(id);
    }

    @Override
    public List<User> getAllUsers() {
        return userDAO.getAllUsers();
    }

    @Override
    public boolean removeUser(Integer id) {
        return userDAO.removeUser(id);
    }

    @Override
    public boolean editExistingUser(User user) {
        return userDAO.editExistingUser(user);
    }
}
