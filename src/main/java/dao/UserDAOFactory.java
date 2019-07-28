package dao;

import java.io.FileReader;
import java.util.Properties;

public interface UserDAOFactory {

    static UserDAOFactory getUserDAOFactory() {
        UserDAOFactory userDAOFactory = null;
        if (readProperties().equals("hibernate")) {
            userDAOFactory = new UserDAOFactoryHibernate();
        } else {
            userDAOFactory = new UserDAOFactoryJDBC();
        }
        return userDAOFactory;
    }

    static String readProperties() {
        try {
            FileReader reader = new FileReader("db.properties");
            Properties properties = new Properties();
            properties.load(reader);
            return properties.getProperty("db_access");
        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
            return null;
        }
    }

    UserDAO getUserDAO();
}
