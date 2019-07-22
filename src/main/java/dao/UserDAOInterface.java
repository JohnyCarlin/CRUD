package dao;

import model.User;

import java.sql.Connection;
import java.util.List;

public interface UserDAOInterface {

    public void insertUser(User user);

    public User selectUser(int id);

    public List<User> selectAllUsers();

    public boolean deleteUser(int id);

    public boolean updateUser(User user);
}
