/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import domain.Customer;
import java.net.URL;
import java.util.LinkedList;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import vsms.VSMS;

/**
 * FXML Controller class
 *
 * @author allen
 */
public class ControllerCustomerView implements Initializable {
    
    private Customer currentCustomer;
    private Integer currentCustomerIndex;
    private LinkedList<Customer> searchResultCustomers;

    @FXML
    private TextField txtFirstName;
    
    @FXML
    private TextField txtLastName;
    
    @FXML
    private TextField txtPhone;
    
    @FXML
    private TextField txtAddress;
    
    @FXML
    private TextField txtCustomerID;
    
    @FXML
    private TextField txtSearch;
    
    @FXML
    private Label lblResults;
    
    @FXML
    private Button btnPrevious;
    
    @FXML
    private Button btnNext;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        searchResultCustomers = new LinkedList();
    }
    
    @FXML
    public void addCustomer () {
        if (validateCustomer()){
            Customer c = new Customer(
                0,
                txtFirstName.getText(),
                txtLastName.getText(),
                txtPhone.getText(),
                txtAddress.getText()
        );
        VSMS.getModel().insertCustomer(c);
        Alert success = new Alert(Alert.AlertType.INFORMATION);
        success.setHeaderText("Success!");
        success.setContentText(c.getFName() + " has been added.");
        success.showAndWait();
        clearAll();
        }
    }
    
    @FXML
    public void updateCustomer () {
        if (validateCustomer() && txtCustomerID.getText() != null && !txtCustomerID.getText().trim().isEmpty()){
            Customer c = new Customer(
                currentCustomer.getCustomerID(),
                txtFirstName.getText(),
                txtLastName.getText(),
                txtPhone.getText(),
                txtAddress.getText()
        );
        VSMS.getModel().updateCustomer(c);
        Alert success = new Alert(Alert.AlertType.INFORMATION);
        success.setHeaderText("Success!");
        success.setContentText("Customer (id: " + c.getCustomerID() + ") has been updated.");
        success.showAndWait();
        clearAll();
        }
    }
    
    @FXML
    public void clearAll () {
        txtFirstName.setText("");
        txtLastName.setText("");
        txtPhone.setText("");
        txtAddress.setText("");
        txtCustomerID.setText("");
        btnPrevious.setDisable(true);
        btnNext.setDisable(true);
        lblResults.setText("");
    }
    
    @FXML
    public void searchCustomerByName () {
        if (validateSearch()){
            searchResultCustomers = VSMS.getModel().getCustomersByName(txtSearch.getText());
            showResults();
        }
    }
    
    @FXML
    public void searchCustomerByPhone () {
        if (validateSearch()){
            searchResultCustomers = VSMS.getModel().getCustomersByPhone(txtSearch.getText());
            showResults();
        }
    }
    
    @FXML
    public void previousResult () {
        currentCustomerIndex = currentCustomerIndex == 0 ?
                searchResultCustomers.size() - 1 :
                currentCustomerIndex - 1;
        currentCustomer = searchResultCustomers.get(currentCustomerIndex);
        loadCustomer(currentCustomer);
        setResultLabel();
    }
    
    @FXML
    public void nextResult () {
        currentCustomerIndex = currentCustomerIndex == searchResultCustomers.size() - 1 ?
                0 :
                currentCustomerIndex + 1;
        currentCustomer = searchResultCustomers.get(currentCustomerIndex);
        loadCustomer(currentCustomer);
        setResultLabel();
    }
    
    @FXML
    public void backToMain (ActionEvent event) {
        VSMS.loadScene("Main");
    }
    
    @FXML
    public void goToVehicles (ActionEvent event) {
        VSMS.loadScene("Vehicle");
    }
    
    @FXML
    public void goToServices (ActionEvent event) {
        VSMS.loadScene("Service");
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
    
    private boolean validateSearch () {
        boolean valid = true;
        String errorMessage = "";
        if (txtSearch.getText().length() == 0){
            valid = false;
            errorMessage += "Search field cannot be blank!\n";
            Alert invalid = new Alert(Alert.AlertType.ERROR);
            invalid.setContentText(errorMessage);
            invalid.showAndWait();    
        }
        return valid;
    }

    private void showResults () {
        if (searchResultCustomers.size() > 0){
            currentCustomerIndex = 0;
            currentCustomer = searchResultCustomers.get(currentCustomerIndex);
            loadCustomer(currentCustomer);
            setResultLabel();
            if (searchResultCustomers.size() > 1){
                btnPrevious.setDisable(false);
                btnNext.setDisable(false);
            }
        } else {
            clearAll();
            lblResults.setText("Search yeilded no results");
            btnPrevious.setDisable(true);
            btnNext.setDisable(true);
        }
    }

    private void loadCustomer (Customer c){
        txtFirstName.setText(c.getFName());
            txtLastName.setText(c.getLName());
            txtPhone.setText(c.getPhone());
            txtAddress.setText(c.getAddress());
            txtCustomerID.setText("" + c.getCustomerID());
    }
    
    private void setResultLabel() {
        lblResults.setText("Showing result " + (currentCustomerIndex + 1) + " of " + searchResultCustomers.size());
    }
}
