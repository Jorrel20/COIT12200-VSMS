/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import domain.Customer;
import domain.Vehicle;
import java.net.URL;
import java.util.LinkedList;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import vsms.VSMS;

/**
 * FXML Controller class
 *
 * @author allen
 */
public class ControllerVehicleView implements Initializable {

    private LinkedList<Customer> customers;
    private Customer selectedCustomer;
    
    @FXML
    private TextField txtRego;
    
    @FXML
    private TextField txtBrand;
    
    @FXML
    private TextField txtModel;
    
    @FXML
    private TextField txtYear;
    
    @FXML
    private TextField txtKilometres;
    
    @FXML
    private ComboBox<Customer> cmbCustomer;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //set up customers for selection
        customers = VSMS.getModel().getAllCustomers();
        cmbCustomer.setItems(FXCollections.observableArrayList(customers));
        selectedCustomer = customers.getFirst();
        cmbCustomer.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Customer>(){
            @Override
            public void changed(ObservableValue<? extends Customer> ov, Customer t, Customer t1) {
                selectedCustomer = (Customer) cmbCustomer.getSelectionModel().getSelectedItem();
            }
        });
    }
    
    public void addVehicle (ActionEvent event) {
        if (validateVehicle()){//todo
            Vehicle v = new Vehicle(
                txtRego.getText(),
                txtBrand.getText(),
                txtModel.getText(),
                Integer.parseInt(txtYear.getText()),
                Integer.parseInt(txtKilometres.getText()),
                selectedCustomer.getCustomerID()
        );
        VSMS.getModel().insertVehicle(v);
        Alert success = new Alert(Alert.AlertType.INFORMATION);
        success.setHeaderText("Success!");
        success.setContentText(v.getBrand() + " " + v.getModel() + " has been added to customer (id: " + v.getCustomerID() + ")");
        success.showAndWait();
        clearAll();
        }
    }    
    
    public void backToMain (ActionEvent event) {
        VSMS.loadScene("Main");
    }
    
    private void clearAll() {
        txtRego.setText("");
        txtBrand.setText("");
        txtModel.setText("");
        txtYear.setText("");
        txtKilometres.setText("");
    }
    
    //returns true if fields contain valid vehicle data
    private boolean validateVehicle () {
        final int YEAR_LENGTH = 4;
        boolean valid = true;
        String errorMessage = "";
        if (txtRego.getText().length() == 0){
            valid = false;
            errorMessage += "Rego cannot be blank!\n";       
        }
        if (txtBrand.getText().length() == 0){
            valid = false;
            errorMessage += "Brand cannot be blank!\n";       
        }
        if (txtModel.getText().length() == 0){
            valid = false;
            errorMessage += "Model cannot be blank!\n";       
        }
        if (!txtYear.getText().matches("^(?:\\d*\\.)?\\d+$") || txtYear.getText().length() != YEAR_LENGTH) {            
            valid = false;                                                          
            errorMessage += "Year must be 4 numerical digits!\n";       
        }
        if ((txtKilometres.getText().length() == 0)){
            valid = false;
            errorMessage += "Kilometres cannot be blank!\n";
        }
        if (!txtKilometres.getText().matches("^(?:\\d*\\.)?\\d+$")) {            
            valid = false;                                                          
            errorMessage += "Kilometres field must only contain numbers!\n";       
        }
        if (selectedCustomer == null){
            valid = false;
            errorMessage += "Please select a customer!\n";       
        }
        if (!valid){
            Alert invalid = new Alert(Alert.AlertType.ERROR);
            invalid.setContentText(errorMessage);
            invalid.showAndWait();    
        }
        return valid;
    }
}
