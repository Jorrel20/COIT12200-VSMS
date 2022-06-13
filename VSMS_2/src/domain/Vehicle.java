/*
 * Rory Allen - s#12149026
 */
package domain;

public class Vehicle {
    
    //instance variables
    private String regoNumber;
    private String brand;
    private String model;
    private int year;
    private int kilometres;
    private int customerID;

    //constructors
    public Vehicle(String regoNumber, String brand, String model, int year, int kilometres, int customerID) {
        this.regoNumber = regoNumber;
        this.brand = brand;
        this.model = model;
        this.year = year;
        this.kilometres = kilometres;
        this.customerID = customerID;
    }

    //accessors and modifiers
    public String getRegoNumber() {
        return regoNumber;
    }

    public void setRegoNumber(String regoNumber) {
        this.regoNumber = regoNumber;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getKilometres() {
        return kilometres;
    }

    public void setKilometres(int kilometres) {
        this.kilometres = kilometres;
    }

    public int getCustomerID() {
        return customerID;
    }

    public void setCustomerID(int customerID) {
        this.customerID = customerID;
    }

    //to string
    @Override
    public String toString() {
        return "Vehicle{" + "regoNumber=" + regoNumber + ", brand=" + brand + ", model=" + model + ", year=" + year + ", kilometres=" + kilometres + ", customerID=" + customerID + '}';
    }
}
