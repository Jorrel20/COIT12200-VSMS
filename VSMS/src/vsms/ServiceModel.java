/*
 * Rory Allen - s#12149026
 * Jorrel Arboleda - s#XXXXXXXX
 */
package vsms;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ServiceModel {
    
    //connection constants
    private static final String DB_URL = "jdbc:mysql://localhost/covidtestdb";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "ChangePassword";
    
    //database connection
    private Connection connection = null;
    
    //stored sql queries
    private PreparedStatement   SQL_ADD_STATEMENTS = null;
    
    //constructor establishes connection to db and defines sql queries
    public ServiceModel (){
        //!!!uncomment below once db is set up!!!
//        try {
//            connection = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
//            
//            //initialise statements
//            
//        } catch (SQLException e) {
//            System.out.println("VirusTestModel.init: cannot create connection to db - " + e.getLocalizedMessage());
//            close();
//        }
    }
    
    //for closing connection
    public final void close(){
        try{
           connection.close();
        }
        catch (SQLException e){
            System.out.println("VirusTestModel.close: problem closing connection - " + e.getLocalizedMessage());
        }
    }
}
