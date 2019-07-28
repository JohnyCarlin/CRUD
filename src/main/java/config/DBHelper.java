package config;

import model.User;
import org.hibernate.cfg.Configuration;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBHelper {

    private static DBHelper dbHelper = null;
    private Connection connection;
    private Configuration configuration;

    private DBHelper() {
    }

    private void setConfiguration() {
        configuration = new Configuration();
        configuration.addAnnotatedClass(User.class);
        configuration.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQLDialect");
        configuration.setProperty("hibernate.connection.driver_class", "com.mysql.jdbc.Driver");
        configuration.setProperty("hibernate.connection.url", "jdbc:mysql://localhost:3306/demo");
        configuration.setProperty("hibernate.connection.username", "java");
        configuration.setProperty("hibernate.connection.password", "cookingpies");
        configuration.setProperty("hibernate.show_sql", "true");
        configuration.setProperty("hibernate.hbm2ddl.auto", "validate");
    }

    public void setConnection() {
        connection = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/demo?useSSL=false", "java", "cookingpies");
        } catch (SQLException e) {
            System.out.println(e.getSQLState());
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }

    public static Connection getConnection() {
        if (dbHelper == null) {
            dbHelper = new DBHelper();
        }
        dbHelper.setConnection();
        return dbHelper.connection;
    }

    public static Configuration getConfiguration() {
        if (dbHelper == null) {
            dbHelper = new DBHelper();
        }
        dbHelper.setConfiguration();
        return dbHelper.configuration;
    }
}
