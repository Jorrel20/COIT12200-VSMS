/*
 * COIT13229 - Applied Distributed Systems
 * Assignment 2 - Maleny Dairies Home Delivery System - MDHS
 * Group Assessment
 * Students: Amanda Pearce s0292756, Rory Allen s12149026
 * Connor James s0291202, Ethan Stevens-Smith 12108585
 */

package MDHS_Client;


import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

/**
 * Log in screen for registered users.
 */
public class ControllerLogIn implements Initializable {

    @FXML
    private Label IncorrectPassword;// is there a popup message we can use?

    @FXML
    private PasswordField txtFldPassword;
    @FXML
    private TextField txtFldUserName;


    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    @FXML
    private void signIn() throws IOException {
            System.out.println("signIn");
    }
    
    @FXML
    private void goToRegister   (){MDHS.loadScene("FXMLRegister");}
    
}
