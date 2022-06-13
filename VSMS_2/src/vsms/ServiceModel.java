/*
 * Rory Allen - s#12149026
 * Jorrel Arboleda - s#XXXXXXXX
 */
package vsms;

import domain.Customer;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;

public class ServiceModel {
    
    //connection constants
    private static final String DB_URL = "jdbc:mysql://localhost/vsms";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "Acm3cats";
    
    //database connection
    private Connection connection = null;
    
    //stored sql queries
    private PreparedStatement   SQL_GET_CUSTOMER_BY_ID = null,
                                SQL_GET_CUSTOMER_BY_NAME = null,
                                SQL_GET_CUSTOMER_BY_PHONE = null,
                                SQL_INSERT_CUSTOMER = null,
                                SQL_UPDATE_CUSTOMER = null;
    
    //constructor establishes connection to db and defines sql queries
    public ServiceModel (){
        try {
            connection = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
            
            SQL_GET_CUSTOMER_BY_ID = connection.prepareStatement("SELECT * FROM Customer WHERE CustomerID = ?");
            SQL_GET_CUSTOMER_BY_NAME = connection.prepareStatement("SELECT * FROM Customer WHERE Name LIKE ?");
            SQL_GET_CUSTOMER_BY_PHONE = connection.prepareStatement("SELECT * FROM Customer WHERE Phone = ?");
            SQL_INSERT_CUSTOMER = connection.prepareStatement("INSERT INTO Customer "
                                                        + "(Name, Phone, Address) "
                                                        + "VALUES (?, ?, ?, ?, ?)");
            SQL_UPDATE_CUSTOMER = connection.prepareStatement("UPDATE Customer "
                                                        + "SET Name = ?, Phone = ?, Address = ? "
                                                        + "WHERE CustomerID = ?");
            
        } catch (SQLException e) {
            System.out.println("VirusTestModel.init: cannot create connection to db - " + e.getLocalizedMessage());
            close();
        }
    }
    
    //returns customer for a given id
    public Customer getCustomerByID(Integer id) {
        Customer customer = null;
        try {
            SQL_GET_CUSTOMER_BY_ID.setInt(1, id);
            ResultSet rs = SQL_GET_CUSTOMER_BY_ID.executeQuery();
                customer = new Customer(
                        rs.getInt("CustomerID"), 
                        rs.getString("Name"), 
                        rs.getString("Phone"), 
                        rs.getString("Address"));
                
            
        } catch (SQLException e) {
            System.out.println("ServiceModel.getCustomerByID: problem executing query - " + e.getLocalizedMessage());
            close();
        }
        return customer;
    }
    
    //returns customers for a given name **matches partial**
    public LinkedList<Customer> getCustomersByName(String name) {
        LinkedList customers = new LinkedList();
        try {
            name  = "%" + name + "%";
            SQL_GET_CUSTOMER_BY_NAME.setString(1, name);
            ResultSet rs = SQL_GET_CUSTOMER_BY_NAME.executeQuery();
            while (rs.next()){
                Customer c = new Customer(
                        rs.getInt("CustomerID"), 
                        rs.getString("Name"), 
                        rs.getString("Phone"), 
                        rs.getString("Address"));
                customers.add(c);
            }
        } catch (SQLException e) {
            System.out.println("ServiceModel.getCustomersByName: problem executing query - " + e.getLocalizedMessage());
            close();
        }
        return customers;
    }
    
    //returns customers for a given phone number
    public LinkedList<Customer> getCustomersByPhone(String phone) {
        LinkedList customers = new LinkedList();
        try {
            SQL_GET_CUSTOMER_BY_PHONE.setString(1, phone);
            ResultSet rs = SQL_GET_CUSTOMER_BY_PHONE.executeQuery();
            while (rs.next()){
                Customer c = new Customer(
                        rs.getInt("CustomerID"), 
                        rs.getString("Name"), 
                        rs.getString("Phone"), 
                        rs.getString("Address"));
                customers.add(c);
            }
        } catch (SQLException e) {
            System.out.println("ServiceModel.getCustomersByPhone: problem executing query - " + e.getLocalizedMessage());
            close();
        }
        return customers;
    }
    
    //adds a new customer entry to the db
    public void insertCustomer(Customer customer) {
        try {
            SQL_INSERT_CUSTOMER.setString(1, customer.getName());
            SQL_INSERT_CUSTOMER.setString(2, customer.getPhone());
            SQL_INSERT_CUSTOMER.setString(3, customer.getAddress());
            
            SQL_INSERT_CUSTOMER.executeUpdate();
            System.out.println("customer added: " + customer.toString());
        } catch (SQLException e) {
            System.out.println("ServiceModel.insertCustomer: problem executing query - " + e.getLocalizedMessage());
            close();
        }
    }
    
    //updates a customer entry
    public void updateCustomer(Customer customer) {
        try {
            SQL_UPDATE_CUSTOMER.setString(1, customer.getName());
            SQL_UPDATE_CUSTOMER.setString(2, customer.getPhone());
            SQL_UPDATE_CUSTOMER.setString(3, customer.getAddress());
            SQL_UPDATE_CUSTOMER.setInt(4, customer.getCustomerID());
            
            SQL_UPDATE_CUSTOMER.executeUpdate();
            System.out.println("customer updated: " + customer.toString());
        } catch (SQLException e) {
            System.out.println("ServiceModel.updateCustomer: problem executing query - " + e.getLocalizedMessage());
            close();
        }
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