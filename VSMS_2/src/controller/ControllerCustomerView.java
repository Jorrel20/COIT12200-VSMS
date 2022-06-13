/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import domain.Customer;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import vsms.VSMS;

/**
 * FXML Controller class
 *
 * @author allen
 */
public class ControllerCustomerView implements Initializable {
    
    private Customer currentCustomer;

    @FXML
    public TextField txtFirstName;
    
    @FXML
    private TextField txtLastName;
    
    @FXML
    private TextField txtPhone;
    
    @FXML
    private TextField txtAddress;
    
    @FXML
    private TextField txtCustomerID;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }
    
    @FXML
    public void addCustomer () {
        if (validateCustomer()){
            Customer c = new Customer(
                0,
                String.format("%s %s", txtFirstName.getText(), txtLastName.getText()),
                txtPhone.getText(),
                txtAddress.getText()
        );
        VSMS.getModel().insertCustomer(c);
        Alert success = new Alert(Alert.AlertType.INFORMATION);
        success.setHeaderText("Success!");
        success.setContentText(c.getName() + " has been added.");
        success.showAndWait();
        clearAll();
        }
    }
    
    @FXML
    public void updateCustomer () {
        
    }
    
    @FXML
    public void clearAll () {
        txtFirstName.setText("");
        txtLastName.setText("");
        txtPhone.setText("");
        txtAddress.setText("");
        txtCustomerID.setText("");
    }
    
    @FXML
    public void searchCustomerByName () {
        Customer c = new Customer(
                0,
                String.format("%s %s", txtFirstName.getText(), txtLastName.getText()),
                txtPhone.getText(),
                txtAddress.getText()
        );
        System.out.println(c);
        VSMS.getModel().insertCustomer(c);
    }
    
    @FXML
    public void backToMain (ActionEvent event) {
        VSMS.loadScene("Main");
    }

    //returns true if textfields contain valid customer data
    private boolean validateCustomer () {
        
        final Integer PHONE_NUMBER_LENGTH = 10;
        boolean valid = true;
        String errorMessage = "";
        
        if (txtFirstName.getText().length() == 0){
            valid = false;
            errorMessage += "First Name cannot be blank!\n";       
        }
        
        if (txtLastName.getText().length() == 0){
            valid = false;
            errorMessage += "Last Name cannot be blank!\n";       
        }
        
        if (!txtPhone.getText().matches("^(?:\\d*\\.)?\\d+$")) {            
            valid = false;                                                          
            errorMessage += "Phone can only contain numbers!\n";       
        }
       
        if ((txtPhone.getText().length() != PHONE_NUMBER_LENGTH)){
            valid = false;
            errorMessage += "Phone must be " + PHONE_NUMBER_LENGTH + "digits!\n";
        }
        
        if (txtAddress.getText().length() == 0){
            valid = false;
            errorMessage += "Address cannot be blank!\n";       
        }
        
        if (!valid){
            Alert invalid = new Alert(Alert.AlertType.ERROR);
            invalid.setContentText(errorMessage);
            invalid.showAndWait();    
        }
        return valid;
    }    
    
}
