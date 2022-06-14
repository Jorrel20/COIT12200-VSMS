/*
 * Rory Allen - s#12149026
 * Jorrel Arboleda - s#XXXXXXXX
 */
package vsms;

import domain.Customer;
import domain.Service;
import domain.Vehicle;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import javafx.util.Pair;

public class ServiceModel {
    
    //connection constants
    private static final String DB_URL = "jdbc:mysql://localhost:3306/VSMS";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "Acm3cats";
    
    //database connection
    private Connection connection = null;
    
    //stored sql queries
    private PreparedStatement   SQL_GET_ALL_CUSTOMERS,
                                SQL_GET_CUSTOMER_BY_ID,
                                SQL_GET_CUSTOMER_BY_NAME,
                                SQL_GET_CUSTOMER_BY_PHONE,
                                SQL_INSERT_CUSTOMER,
                                SQL_UPDATE_CUSTOMER,
            
                                SQL_INSERT_VEHICLE,
            
                                SQL_GET_ALL_SERVICES,
                                SQL_GET_SERVICES_BY_REGO,
                                SQL_INSERT_SERVICE,
                                SQL_DELETE_SERVICE,
            
                                SQL_GET_MIN_PRICE,
                                SQL_GET_MAX_PRICE,
                                SQL_GET_AVG_PRICE,
                                SQL_GET_NUM_VEHICLE_BY_BRAND,
                                SQL_GET_NUM_SERVICE_BY_BRAND;
    
    
    //constructor establishes connection to db and defines sql queries
    public ServiceModel (){
        try {
            connection = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
            //customer queries
            SQL_GET_ALL_CUSTOMERS = connection.prepareStatement("SELECT * FROM Customer");
            SQL_GET_CUSTOMER_BY_ID = connection.prepareStatement("SELECT * FROM Customer WHERE CustomerID = ?");
            SQL_GET_CUSTOMER_BY_NAME = connection.prepareStatement("SELECT * FROM Customer WHERE FirstName LIKE ? OR LastName LIKE ?");
            SQL_GET_CUSTOMER_BY_PHONE = connection.prepareStatement("SELECT * FROM Customer WHERE Phone = ?");
            SQL_INSERT_CUSTOMER = connection.prepareStatement("INSERT INTO Customer "
                                                        + "(FirstName, LastName, Phone, Address) "
                                                        + "VALUES (?, ?, ?, ?)");
            SQL_UPDATE_CUSTOMER = connection.prepareStatement("UPDATE Customer "
                                                        + "SET FirstName = ?, LastName = ?, Phone = ?, Address = ? "
                                                        + "WHERE CustomerID = ?");
            //vehicle queries
            SQL_INSERT_VEHICLE = connection.prepareStatement("INSERT INTO Vehicle "
                                                        + "(RegoNumber, Brand, Model, Year, Kilometres, CustomerID) "
                                                        + "VALUES (?, ?, ?, ?, ?, ?)");
            //service queries
            SQL_GET_ALL_SERVICES = connection.prepareStatement("SELECT * FROM Service ORDER BY Price ASC");
            SQL_GET_SERVICES_BY_REGO = connection.prepareStatement("SELECT * FROM Service WHERE RegoNumber = ?");
            SQL_INSERT_SERVICE = connection.prepareStatement("INSERT INTO Service "
                                                        + "(Details, Date, Price, RegoNumber) "
                                                        + "VALUES (?, ?, ?, ?)");
            SQL_DELETE_SERVICE = connection.prepareStatement("DELETE FROM Service WHERE Date = ? AND RegoNumber = ?");
            //statistic queries
            SQL_GET_MIN_PRICE = connection.prepareStatement("SELECT MIN(Price) AS minPrice FROM Service");
            SQL_GET_MAX_PRICE = connection.prepareStatement("SELECT MAX(Price) AS maxPrice FROM Service");
            SQL_GET_AVG_PRICE = connection.prepareStatement("SELECT AVG(Price) AS avgPrice FROM Service");
            SQL_GET_NUM_VEHICLE_BY_BRAND = connection.prepareStatement("SELECT Brand, COUNT(RegoNumber) AS numVehicles "
                                                                        + "FROM Vehicle GROUP BY Brand ORDER BY numVehicles DESC");
            SQL_GET_NUM_SERVICE_BY_BRAND = connection.prepareStatement("SELECT v.Brand, COUNT(s.ServiceID) AS numServices "
                                                                        + "FROM Vehicle AS v INNER JOIN Service AS s "
                                                                        + "ON v.RegoNumber=s.RegoNumber "
                                                                        + "GROUP BY v.Brand "
                                                                        + "ORDER BY numServices DESC");
            
        } catch (SQLException e) {
            System.out.println("ServiceModel.init: cannot create connection to db - " + e.getLocalizedMessage());
            close();
        }
    }
    
    public LinkedList<Customer> getAllCustomers() {
        LinkedList<Customer> customers = new LinkedList();
        try {
            ResultSet rs = SQL_GET_ALL_CUSTOMERS.executeQuery();
            while (rs.next()){
                Customer c = new Customer(
                        rs.getInt("CustomerID"),  
                        rs.getString("FirstName"), 
                        rs.getString("LastName"), 
                        rs.getString("Phone"), 
                        rs.getString("Address"));
                customers.add(c);
            }
        } catch (SQLException e) {
            System.out.println("ServiceModel.getAllCustomers: problem executing query - " + e.getLocalizedMessage());
            close();
        }    
        return customers;
    }
    
    //returns customer for a given id
    public Customer getCustomerByID(Integer id) {
        Customer customer = null;
        try {
            SQL_GET_CUSTOMER_BY_ID.setInt(1, id);
            ResultSet rs = SQL_GET_CUSTOMER_BY_ID.executeQuery();
                customer = new Customer(
                        rs.getInt("CustomerID"), 
                        rs.getString("FirstName"), 
                        rs.getString("LastName"), 
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
            SQL_GET_CUSTOMER_BY_NAME.setString(2, name);
            ResultSet rs = SQL_GET_CUSTOMER_BY_NAME.executeQuery();
            while (rs.next()){
                Customer c = new Customer(
                        rs.getInt("CustomerID"),  
                        rs.getString("FirstName"), 
                        rs.getString("LastName"), 
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
                        rs.getString("FirstName"), 
                        rs.getString("LastName"), 
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
            SQL_INSERT_CUSTOMER.setString(1, customer.getFName());
            SQL_INSERT_CUSTOMER.setString(2, customer.getLName());
            SQL_INSERT_CUSTOMER.setString(3, customer.getPhone());
            SQL_INSERT_CUSTOMER.setString(4, customer.getAddress());
            
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
            SQL_UPDATE_CUSTOMER.setString(1, customer.getFName());
            SQL_UPDATE_CUSTOMER.setString(2, customer.getLName());
            SQL_UPDATE_CUSTOMER.setString(3, customer.getPhone());
            SQL_UPDATE_CUSTOMER.setString(4, customer.getAddress());
            SQL_UPDATE_CUSTOMER.setInt(5, customer.getCustomerID());
            
            SQL_UPDATE_CUSTOMER.executeUpdate();
            System.out.println("customer updated: " + customer.toString());
        } catch (SQLException e) {
            System.out.println("ServiceModel.updateCustomer: problem executing query - " + e.getLocalizedMessage());
            close();
        }
    }
    
    //adds a new vehicle entry to the db
    public void insertVehicle(Vehicle vehicle) {
        try {
            SQL_INSERT_VEHICLE.setString(1, vehicle.getRegoNumber());
            SQL_INSERT_VEHICLE.setString(2, vehicle.getBrand());
            SQL_INSERT_VEHICLE.setString(3, vehicle.getModel());
            SQL_INSERT_VEHICLE.setInt(4, vehicle.getYear());
            SQL_INSERT_VEHICLE.setInt(5, vehicle.getKilometres());
            SQL_INSERT_VEHICLE.setInt(6, vehicle.getCustomerID());
            
            SQL_INSERT_VEHICLE.executeUpdate();
            System.out.println("vehicle added: " + vehicle.toString());
        } catch (SQLException e) {
            System.out.println("ServiceModel.insertVehicle: problem executing query - " + e.getLocalizedMessage());
            close();
        }
    }
    
    //returns collection of all services from db
    public LinkedList<Service> getAllServices () {
        LinkedList<Service> services = new LinkedList();
        try {
            ResultSet rs = SQL_GET_ALL_SERVICES.executeQuery();
            while (rs.next()){
                Service s = new Service(
                        rs.getInt("ServiceID"),  
                        rs.getString("Details"), 
                        rs.getDate("Date"), 
                        rs.getDouble("Price"), 
                        rs.getString("RegoNumber"));
                services.add(s);
            }
        } catch (SQLException e) {
            System.out.println("ServiceModel.getAllServices: problem executing query - " + e.getLocalizedMessage());
            close();
        }    
        return services;
    }
    
    //returns services matching a given rego
    public LinkedList<Service> getServicesByRego(String rego) {
        LinkedList servives = new LinkedList();
        try {
            SQL_GET_CUSTOMER_BY_NAME.setString(1, rego);
            ResultSet rs = SQL_GET_SERVICES_BY_REGO.executeQuery();
            while (rs.next()){
                Service s = new Service(
                        rs.getInt("ServiceID"),  
                        rs.getString("Details"), 
                        rs.getDate("Date"), 
                        rs.getDouble("Price"), 
                        rs.getString("RegoNumber"));
                servives.add(s);
            }
        } catch (SQLException e) {
            System.out.println("ServiceModel.getServiceByRego: problem executing query - " + e.getLocalizedMessage());
            close();
        }
        return servives;
    }
    
    //adds a new service entry to the db
    public void insertService(Service service) {
        try {
            SQL_INSERT_SERVICE.setString(1, service.getDetails());
            SQL_INSERT_SERVICE.setDate(2, service.getDate());
            SQL_INSERT_SERVICE.setDouble(3, service.getPrice());
            SQL_INSERT_SERVICE.setString(4, service.getRegoNumber());
            
            SQL_INSERT_SERVICE.executeUpdate();
        } catch (SQLException e) {
            System.out.println("ServiceModel.insertService: problem executing query - " + e.getLocalizedMessage());
            close();
        }
    }
    
    //removes a service for a given date and rego
    //**NB  -ALL services (usually 1) for a given date and rego will be deleted.
    //**    -Noted in case (for some reason) one vehicle gets serviced multiple times in one day.
    public void deleteService(Date date, String rego) {
        try {
            SQL_DELETE_SERVICE.setDate(1, date);
            SQL_DELETE_SERVICE.setString(2, rego);
            SQL_DELETE_SERVICE.execute();
        } catch (SQLException e) {
            System.out.println("ServiceModel.deleteService: problem executing query - " + e.getLocalizedMessage());
            close();
        }
    }
    
    //returns minimum price of service
    public double minimumPrice() {
        double min = 0;
        try {
            min = SQL_GET_MIN_PRICE.executeQuery().getDouble("minPrice");
        } catch (SQLException e) {
            System.out.println("ServiceModel.minimumPrice: problem executing query - " + e.getLocalizedMessage());
            close();
        }
        return min;
    }
    
    //returns maximum price of service
    public double maximumPrice() {
        double max = 0;
        try {
            max = SQL_GET_MAX_PRICE.executeQuery().getDouble("maxPrice");
        } catch (SQLException e) {
            System.out.println("ServiceModel.maximumPrice: problem executing query - " + e.getLocalizedMessage());
            close();
        }
        return max;
    }
    
    //returns average price of service
    public double averagePrice() {
        double avg = 0;
        try {
            avg = SQL_GET_AVG_PRICE.executeQuery().getDouble("avgPrice");
        } catch (SQLException e) {
            System.out.println("ServiceModel.averagePrice: problem executing query - " + e.getLocalizedMessage());
            close();
        }
        return avg;
    }
    
    //returns list of number of vehicles per vehicle brand
    public LinkedList<Pair> getNumVehiclesByBrand() {
        LinkedList vehiclesByBrand = new LinkedList();
        try {
            ResultSet rs = SQL_GET_NUM_VEHICLE_BY_BRAND.executeQuery();
            while (rs.next()){
                Pair p = new Pair(rs.getString("Brand"), rs.getInt("NumVehicles"));
                vehiclesByBrand.add(p);
            }
        } catch (SQLException e) {
            System.out.println("ServiceModel.getNumVehiclesByBrand: problem executing query - " + e.getLocalizedMessage());
            close();
        }
        return vehiclesByBrand;
    }
    
    //returns list of top 3 number of services per vehicle brand
    public LinkedList<Pair> getNumServicesByBrand() {
        LinkedList servicesByBrand = new LinkedList();
        try {
            ResultSet rs = SQL_GET_NUM_SERVICE_BY_BRAND.executeQuery();
            int i = 1;
            while (rs.next() && i <= 3){
                Pair p = new Pair(rs.getString("Brand"), rs.getInt("NumServices"));
                servicesByBrand.add(p);
                i++;
            }
        } catch (SQLException e) {
            System.out.println("ServiceModel.getNumServicesByBrand: problem executing query - " + e.getLocalizedMessage());
            close();
        }
        return servicesByBrand;
    }
    
    //for closing connection
    public final void close(){
        try{
           connection.close();
        }
        catch (SQLException e){
            System.out.println("ServiceModel.close: problem closing connection - " + e.getLocalizedMessage());
        }
    }

    
}
