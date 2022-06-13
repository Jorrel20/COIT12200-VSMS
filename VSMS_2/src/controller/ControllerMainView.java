/*
 * Rory Allen - s#12149026
 * Jorrel Arboleda - s#XXXXXXXX
 */
package controller;

import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import vsms.ServiceModel;
import vsms.VSMS;

//main view controller class - just like it says on the box
public class ControllerMainView implements Initializable {
    
    //input lists
    
    //controls
    @FXML
    ImageView imvLogo;
    
    //other class params
    ServiceModel model;
    
    //init controller object
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Image logoImage = new Image(getClass().getResourceAsStream("/image/Logo1.png"));
        imvLogo.setImage(logoImage);
        
        model = new ServiceModel();
    }

    //creates new patient entry
    public void goToCustomers (ActionEvent event) {
        VSMS.loadScene("Customer");
    }
    
    //updates patient from current input feild values
    public void goToVehicles (ActionEvent event) {
        VSMS.loadScene("Vehicle");
    }
    
    //loads next patient data into input feilds 
    public void goToServices (ActionEvent event) {
        VSMS.loadScene("Service");
    }
    
    //loads previous patient data into input feilds 
    public void goToStats (ActionEvent event) {
        VSMS.loadScene("Statistic");
    }
    
    //closes connection and exits application
    public void exit () {
        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setTitle("Exit");
        alert.setHeaderText("Close the application.");
        String content = String.format("Are you certain that you wish to exit?");
        alert.setContentText(content);
        Optional<ButtonType> result = alert.showAndWait();
	if (result.get() == ButtonType.OK)
        {
            model.close();
            Platform.exit();
        }
    }
    
    //helper method to display alerts
    private void showInfoAlert(String title, String header, String content){
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(content);
        alert.show();
    }
    
//    //helper method to convert string date to sql compatible
//    private java.sql.Date formatDate (String date){
//        java.sql.Date sqlDate = null;
//        try {
//            java.util.Date utilDate = new SimpleDateFormat("yyyy-mm-dd").parse(date);
//            sqlDate = new java.sql.Date(utilDate.getTime());
//        } catch (ParseException ex) {
//            Logger.getLogger(ControllerMainView.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        return sqlDate;
//    }
}
