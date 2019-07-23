package service;

import dao.UserDAOImplJDBC;
import dao.UserDAO;
import model.User;


import java.util.List;

public class UserServiceImpl implements UserService {

    private UserDAO userDAO;

    public UserServiceImpl() {
        this.userDAO =  new UserDAOImplJDBC();
    }

    @Override
    public void addNewUser(User user) {
        userDAO.addNewUser(user);
    }

    @Override
    public User getUserbyID(Integer id) {
        return userDAO.getUserbyID(id);
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
