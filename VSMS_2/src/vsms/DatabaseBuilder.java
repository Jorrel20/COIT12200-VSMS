/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vsms;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author allen
 */
public class DatabaseBuilder {
    private static final boolean ADD_TEST_DATA = true;//if true, loads db with test data
    private static final String DB_NAME = "VSMS",
                                MYSQL_URL = "jdbc:mysql://localhost:3306",
                                USERNAME = "root",
                                PASSWORD = "Acm3cats",
            
                                TBL_CUSTOMER = "Customer",
                                TBL_VEHICLE = "Vehicle",
                                TBL_SERVICE = "Service";
    private static String   DB_URL,
                            SQL_CREATE_DB,
                            SQL_CREATE_CUSTOMER_TABLE,
                            SQL_CREATE_VEHICLE_TABLE,
                            SQL_CREATE_SERVICE_TABLE,
                            SQL_CUSTOMER_TEST_DATA,
                            SQL_VEHICLE_TEST_DATA,
                            SQL_SERVICE_TEST_DATA;//for testing purposes
    
    private static Connection connection;
    private static Statement statement;
    
    static void buildDB(){
        DB_URL = MYSQL_URL + "/" + DB_NAME;
        
        try {//check if database exists, builds it if not
            connection = DriverManager.getConnection(MYSQL_URL, USERNAME, PASSWORD);
            ResultSet resultSet = connection.getMetaData().getCatalogs();
            while (resultSet.next()) {
              if(resultSet.getString(1).equalsIgnoreCase("vsms")){
                  connection = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
                  return;
              }
            }
            resultSet.close();
        } catch (SQLException e) {
            System.out.println("DatabaseManager: Could not get connection - " + e.getLocalizedMessage());
        }
        
        //create database if it does not extist
        SQL_CREATE_DB = "CREATE DATABASE IF NOT EXISTS " + DB_NAME;
        
        try {    
            connection = DriverManager.getConnection(MYSQL_URL, USERNAME, PASSWORD);
            statement = connection.createStatement();
            statement.executeUpdate(SQL_CREATE_DB);
        } catch (SQLException e) {
            System.out.println("DatabaseManager: Could not create db - " + e.getLocalizedMessage());
        }
        
        //create customer table if it does not exist
        SQL_CREATE_CUSTOMER_TABLE = "CREATE TABLE IF NOT EXISTS " + TBL_CUSTOMER +
                                    "(CustomerID INT not NULL AUTO_INCREMENT," +
                                    "FirstName VARCHAR(20)," +
                                    "LastName VARCHAR(20)," +
                                    "Phone VARCHAR(20)," +
                                    "Address VARCHAR(100)," +
                                    "PRIMARY KEY (CustomerID))";
        
        //create vehicle table if it does not exist
        SQL_CREATE_VEHICLE_TABLE =  "CREATE TABLE IF NOT EXISTS " + TBL_VEHICLE +
                                    "(RegoNumber VARCHAR(20) not NULL," +
                                    "Brand VARCHAR(20)," +
                                    "Model VARCHAR(20)," +
                                    "Year INT," +
                                    "Kilometres INT," +
                                    "CustomerID INT not NULL," +
                                    "PRIMARY KEY (RegoNumber)," +
                                    "FOREIGN KEY (CustomerID) REFERENCES " + TBL_CUSTOMER + " (CUSTOMERID))";
        
        //create product table if it does not exist
        SQL_CREATE_SERVICE_TABLE =  "CREATE TABLE IF NOT EXISTS " + TBL_SERVICE +
                                    "(ServiceID INTEGER not NULL AUTO_INCREMENT," +
                                    "Details VARCHAR(200)," +
                                    "Date DATE," +
                                    "Price DOUBLE(8,2)," +
                                    "RegoNumber VARCHAR(20) not NULL," +
                                    "PRIMARY KEY (ServiceID)," +
                                    "FOREIGN KEY (RegoNumber) REFERENCES " + TBL_VEHICLE + " (RegoNumber))";
        
        //insert statement for test data
        SQL_CUSTOMER_TEST_DATA =    "INSERT INTO " + TBL_CUSTOMER 
                                    + "(FirstName, LastName, Phone, Address)"
                                    + " VALUES ('Rory', 'Allen', '0404040404', '7 Newhave Street, Mount Tarcoola, WA 6530')"
                                    + ", ('Caitlyn', 'Allen', '0404040405', '7 Newhave Street, Mount Tarcoola, WA 6530')"
                                    + ", ('Daffodil', 'Allen', '0404040406', '7 Newhave Street, Mount Tarcoola, WA 6530')"
                                    + ", ('Lily', 'Allen', '0404040407', '7 Newhave Street, Mount Tarcoola, WA 6530')"
                                    + ", ('Moss', 'Allen', '0404040408', '7 Newhave Street, Mount Tarcoola, WA 6530')";
        
        SQL_VEHICLE_TEST_DATA =    "INSERT INTO " + TBL_VEHICLE 
                                    + "(RegoNumber, Brand, Model, Year, Kilometres, CustomerID)"
                                    + " VALUES ('123ABC', 'Daihatsu', 'Charade', '1999', '20000', '1')"
                                    + ", ('234BCD', 'Kia', 'Rio', '1999', '30000', '1')"
                                    + ", ('345CDE', 'Holden', 'Rodeo', '1999', '60000', '1')"
                                    + ", ('456DEF', 'Holden', 'Kingswood', '1999', '40000', '1')"
                                    + ", ('567EFG', 'Ford', 'Captiva', '1999', '80000', '1')";
        
        SQL_SERVICE_TEST_DATA =    "INSERT INTO " + TBL_SERVICE 
                                    + "(Details, Date, Price, RegoNumber)"
                                    + " VALUES ('No Motor', '2022/1/1', '4', '123ABC')"
                                    + ", ('Ripped some stuff out', '2022/1/1', '100', '123ABC')"
                                    + ", ('Dun Work', '2022/1/1', '0', '123ABC')"
                                    + ", ('Added Flux Capacitor', '2022/1/1', '3000', '123ABC')"
                                    + ", ('Its actually a boat', '2022/1/1', '4', '123ABC')";
        
        try {    
            connection = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
            statement = connection.createStatement();
            statement.executeUpdate(SQL_CREATE_CUSTOMER_TABLE);
            statement.executeUpdate(SQL_CREATE_VEHICLE_TABLE);
            statement.executeUpdate(SQL_CREATE_SERVICE_TABLE);
            if (ADD_TEST_DATA){
                statement.executeUpdate(SQL_CUSTOMER_TEST_DATA);
                statement.executeUpdate(SQL_VEHICLE_TEST_DATA);
                statement.executeUpdate(SQL_SERVICE_TEST_DATA);
            }
        } catch (SQLException e) {
            System.out.println("DatabaseBuilder: Could not create customer/vehicle/service table - " + e.getLocalizedMessage());
        }
        
    } // end buildDB
}
