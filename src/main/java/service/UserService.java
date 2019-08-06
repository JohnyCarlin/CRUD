package service;

import model.User;

import java.util.List;

public interface UserService {

    void addNewUser(User user);

    User getUserByID(Integer id);

    List<User> getAllUsers();

    boolean removeUser(Integer id);

    boolean editExistingUser(User user);
}
