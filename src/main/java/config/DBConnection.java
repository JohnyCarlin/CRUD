//package config;
//
//import java.sql.Connection;
//import java.sql.DriverManager;
//import java.sql.SQLException;
//
//public class DBConnection {
//    private static DBConnection dbConnection = null;
//    private String jdbcURL = "jdbc:mysql://localhost:3306/demo?useSSL=false";
//    private String jdbcUsername = "java";
//    private String jdbcPassword = "cookingpies";
//    private Connection connection;
//
//    private DBConnection() {
//        connection = null;
//        try {
//            Class.forName("com.mysql.cj.jdbc.Driver");
//            connection = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
//        } catch (SQLException e) {
//            System.out.println(e.getSQLState());
//            e.printStackTrace();
//        } catch (ClassNotFoundException e) {
//            System.out.println(e.getMessage());
//            e.printStackTrace();
//        }
//    }
//
//    public static Connection getConnection() {
//        if (dbConnection == null) {
//            dbConnection = new DBConnection();
//        }
//        return dbConnection.connection;
//    }
//}
