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
import javafx.scene.control.TextField;
import vsms.VSMS;

/**
 * FXML Controller class
 *
 * @author allen
 */
public class ControllerCustomerView implements Initializable {

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
    public void addCustomer (ActionEvent event) {
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
    
}
