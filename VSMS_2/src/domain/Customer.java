/*
 * Rory Allen - s#12149026
 * Jorrel Arboleda - s#XXXXXXXX
 */
package domain;

public class Customer implements Comparable<Customer>{

    //instance variables
    private int customerID;
    private String fName;
    private String lName;
    private String phone;
    private String address;
    
    //constructors
    
    public Customer(int customerID, String fName, String lName, String phone, String address) {
        this.customerID = customerID;
        this.fName = fName;
        this.lName = lName;
        this.phone = phone;
        this.address = address;
    } 
    
    //accessors/modifiers
    public int getCustomerID() {
        return customerID;
    } 

    public void setCustomerID(int customerID) {
        this.customerID = customerID;
    }

    public String getFName() {
        return fName;
    }

    public void setFName(String fName) {
        this.fName = fName;
    }
    
    public String getLName() {
        return lName;
    }

    public void setLName(String lName) {
        this.lName = lName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {    
        this.address = address;
    }

    //toString
    @Override
    public String toString() {
        return fName + " " + lName + " (id: " + customerID + ")";
    }
    
    
    //compareTo (for sorting)
    @Override
    public int compareTo(Customer o) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    } 
}
