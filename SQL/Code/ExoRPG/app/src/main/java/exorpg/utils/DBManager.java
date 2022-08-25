package exorpg.utils;
import java.sql.*;

public class DBManager {
    public static Connection conn = null;
    private static String user = "M2I";
    private static String password = "H3ll0M2I";
    private static String server = "51.68.227.19";
    private static String database = "simonrpg";

    public static void init(){
        try {
            DBManager.conn =
            DriverManager.getConnection("jdbc:mysql://"+DBManager.server+"/"
                                            +DBManager.database, user, password);
        }
        catch (SQLException ex) {
            // handle any errors
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }
    }

    public static void close(){
        try{
            DBManager.conn.close();
        }
        catch (SQLException ex) {
            // handle any errors
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }
    }

    public static ResultSet execute(String sql){
        ResultSet test = null;
        try {
            Statement stmt = DBManager.conn.createStatement();
            test = stmt.executeQuery(sql);
        }
        catch (SQLException ex) {
            // handle any errors
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }
        return test;
    }
    public static int executeUpdate(String sql){
        int test = -1;
        try {
            Statement stmt = DBManager.conn.createStatement();
            test = stmt.executeUpdate(sql);
        }
        catch (SQLException ex) {
            // handle any errors
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }
        return test;
    }

}
