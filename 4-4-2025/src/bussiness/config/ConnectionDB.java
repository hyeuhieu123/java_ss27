package bussiness.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionDB {
    private static final String URL = "jdbc:mysql://localhost:3306/demo?createDatabaseIfNotExist=true";
    private static final String USER = "root";
    private static final String PASS = "hieu2005";

    public static Connection openConnection(){
        Connection conn = null;
        try{
            conn = DriverManager.getConnection(URL,USER,PASS);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


        return  conn;
    }
    public static void closeConnection(Connection conn){
        if(conn != null ){
            try {
                conn.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }

}
