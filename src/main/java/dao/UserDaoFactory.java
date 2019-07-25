package dao;

import java.io.File;
import java.io.FileInputStream;

public class UserDaoFactory {
    private UserDAO userDAO;

    public UserDaoFactory() {
        if (readPropertyFile().equals("Hibernate")) {
            userDAO = new UserDAOImplHibernate();
        } else {
            userDAO = new UserDAOImplJDBC();
        }
    }

    private String readPropertyFile() {
        String property = null;
        try {
            File file = new File("/home/yekaterina/Documents/IdeaProjects/mycrude/src/main/java/config/property");
            FileInputStream fileInput = new FileInputStream(file);
            property = fileInput.toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return property;
    }

    public UserDAO getUserDAO() {
        return userDAO;
    }
}



