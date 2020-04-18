package TP.Model;

import java.sql.*;

public class ConnectionMYSQL {
    private static String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    private static String JDBC_URL = "jdbc:mysql://localhost:3306/TPAhorcado";
    private static String JDBC_USER = "root";
    private static String JDBC_PASS = "root";


    public static synchronized Connection getConnection()
            throws SQLException {

            try {
                Class.forName(JDBC_DRIVER);
            } catch (Exception e) {
                System.out.println("falla en cargar el driver");
                e.printStackTrace();
            }

        return DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASS);
    }

    public static void close(ResultSet rs) {
        try {
            if (rs != null) {
                rs.close();
            }
        } catch (SQLException sole) {
            sole.printStackTrace();
        }
    }

    public static void close(PreparedStatement stmt) {
        try {
            if (stmt != null) {
                stmt.close();
            }
        } catch (SQLException sole) {
            sole.printStackTrace();
        }
    }

    public static void close(Connection conn) {
        try {
            if (conn != null){
                conn.close();
            }
        } catch (SQLException sole) {
            sole.printStackTrace();
        }
    }
}
