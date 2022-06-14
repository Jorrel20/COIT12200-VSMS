//@author rory allen
package controller;

import domain.Service;
import java.net.URL;
import java.sql.Date;
import java.time.LocalDate;
import java.util.LinkedList;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import vsms.VSMS;

public class ControllerServiceView implements Initializable {

    @FXML
    private TextField txtRegoAdd;
    
    @FXML
    private DatePicker pkrDateAdd;
    
    @FXML
    private TextArea tarDetails;
    
    @FXML
    private TextField txtPrice;
    
    @FXML
    private TextArea tarResults;
    
    @FXML
    private TextField txtRegoDelete;
    
    @FXML
    private DatePicker pkrDateDelete;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }
    
    @FXML
    public void addService () {
        if (validateAddService()){
            Date date = Date.valueOf(pkrDateAdd.getValue());
            String rego = txtRegoAdd.getText();
            String details = tarDetails.getText();
            double price = Double.parseDouble(txtPrice.getText());
            Service service = new Service (0, details, date, price, rego);
            VSMS.getModel().insertService(service);
            Alert success = new Alert(Alert.AlertType.INFORMATION);
            success.setHeaderText("Success!");
            success.setContentText("Service has been booked for vehicle (rego: " + service.getRegoNumber() + ")");
            success.showAndWait();
            clearAll();
        }
    }
    
    @FXML
    public void viewAllServices () {
        String results = "Services\n------------------------------------------------\n";
        LinkedList<Service>  services = VSMS.getModel().getAllServices();
        for(Service s: services){
            results += s.listString();
        }
        tarResults.setText(results);
    }
    
    @FXML
    public void deleteService() {
        String rego = txtRegoDelete.getText();
        Date date = Date.valueOf(pkrDateDelete.getValue());
        Alert confirm = new Alert(Alert.AlertType.CONFIRMATION);
        confirm.setHeaderText("Warning!");
        confirm.setContentText("Are you sure you want to cancel the service?\n(rego: " + rego + ", date: " + date +")");
        confirm.showAndWait();
        Optional<ButtonType> result = confirm.showAndWait();
	if(!result.isPresent() || result.get() != ButtonType.OK) {
            System.out.println("what?");
	} else {
            VSMS.getModel().deleteService(date, rego);
            Alert success = new Alert(Alert.AlertType.INFORMATION);
            success.setHeaderText("Success!");
            success.setContentText("Service has been cancelled");
            success.showAndWait();
            clearAll();
	}
    }
    
    public void backToMain (ActionEvent event) {
        VSMS.loadScene("Main");
    }    
    
    //returns true if fields contain valid service data
    private boolean validateAddService () {
        boolean valid = true;
        String errorMessage = "";
        if (txtRegoAdd.getText().length() == 0){
            valid = false;
            errorMessage += "Please enter a valid registration number!\n";       
        }
        if (tarDetails.getText().length() == 0){
            valid = false;
            errorMessage += "Details cannot be blank!\n";       
        }
        if (!txtPrice.getText().matches("^(?:\\d*\\.)?\\d+$")) {            
            valid = false;                                                          
            errorMessage += "Year must only be numbers!\n";       
        }
        if (!valid){
            Alert invalid = new Alert(Alert.AlertType.ERROR);
            invalid.setContentText(errorMessage);
            invalid.showAndWait();    
        }
        return valid;
    }
    
    public void clearAll (){
        txtRegoAdd.setText("");
        txtRegoDelete.setText("");
        txtPrice.setText("");
        tarDetails.setText("");
        tarResults.setText("");
    }
}
