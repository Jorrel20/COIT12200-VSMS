/*
 * Rory Allen - s#12149026
 * Jorrel Arboleda - s#XXXXXXXX
 */
package domain;

import java.sql.Date;

public class Service implements Comparable<Service>{
    
    //instance variables
    private int serviceID;
    private String details;
    private Date date;
    private double price;
    private String regoNumber;
    
    //constructor
    public Service(int serviceID, String details, Date date, double price, String regoNumber) {
        this.serviceID = serviceID;
        this.details = details;
        this.date = date;
        this.price = price;
        this.regoNumber = regoNumber;
    }
    
    //accessors/modifiers

    public int getServiceID() {
        return serviceID;
    }

    public void setServiceID(int serviceID) {
        this.serviceID = serviceID;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getRegoNumber() {
        return regoNumber;
    }

    public void setRegoNumber(String regoNumber) {
        this.regoNumber = regoNumber;
    }
    
    //tostring
    @Override
    public String toString() {
        return "Service{" + "serviceID=" + serviceID + ", details=" + details + ", date=" + date + ", price=" + price + ", regoNumber=" + regoNumber + '}';
    }
    
    //compareTo (for sorting)
    @Override
    public int compareTo(Service other) {
        if (this.price != other.getPrice()) {
            return Double.compare(this.price, other.getPrice());
        }
        return this.date.compareTo(other.getDate());
    }
}
