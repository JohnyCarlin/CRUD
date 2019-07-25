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

    private static final String jdbcURL = "jdbc:mysql://localhost:3306/demo?useSSL=false";
    private static final String jdbcUsername = "java";
    private static final String jdbcPassword = "cookingpies";

    private static final String hibernate_show_sql = "true";
    private static final String hibernate_hbm2ddl_auto = "validate";


    // JDBC Constructor
    private DBHelper(String jdbcURL, String jdbcUsername, String jdbcPassword) {
        connection = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
        } catch (SQLException e) {
            System.out.println(e.getSQLState());
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }

    // Hibernate Constructor
    private DBHelper(String hibernate_show_sql, String hibernate_hbm2ddl_auto) {
        configuration = new Configuration();
        configuration.addAnnotatedClass(User.class);

        configuration.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQLDialect");
        configuration.setProperty("hibernate.connection.driver_class", "com.mysql.jdbc.Driver");
        configuration.setProperty("hibernate.connection.url", "jdbc:mysql://localhost:3306/demo");
        configuration.setProperty("hibernate.connection.username", "java");
        configuration.setProperty("hibernate.connection.password", "cookingpies");
        configuration.setProperty("hibernate.show_sql", hibernate_show_sql);
        configuration.setProperty("hibernate.hbm2ddl.auto", hibernate_hbm2ddl_auto);
    }

    public static Connection getConnection() {
        if (dbHelper == null) {
            dbHelper = new DBHelper(jdbcURL, jdbcUsername, jdbcPassword);
        }
        return dbHelper.connection;
    }

    public static Configuration getConfiguration() {
        if (dbHelper == null) {
            dbHelper = new DBHelper(hibernate_show_sql, hibernate_hbm2ddl_auto);
        }
        return dbHelper.configuration;
    }

}
