package service;

import dao.UserDAO;
import dao.UserDAOInterface;
import model.User;


import java.util.List;

public class UserService implements UserServiceInterface {

    private UserDAOInterface userDAO;

    public UserService() {
        this.userDAO =  new UserDAO();
    }

    @Override
    public void insertUser(User user) {
        userDAO.insertUser(user);
    }

    @Override
    public User selectUser(int id) {
        return userDAO.selectUser(id);
    }

    @Override
    public List<User> selectAllUsers() {
        return userDAO.selectAllUsers();
    }

    @Override
    public boolean deleteUser(int id) {
        return userDAO.deleteUser(id);
    }

    @Override
    public boolean updateUser(User user) {
        return userDAO.updateUser(user);
    }
}
