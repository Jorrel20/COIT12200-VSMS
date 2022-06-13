/*
 * COIT13229 - Applied Distributed Systems
 * Assignment 2 - Maleny Dairies Home Delivery System - MDHS
 * Group Assessment
 * Students: Amanda Pearce s0292756, Rory Allen s12149026
 * Connor James s0291202, Ethan Stevens-Smith 12108585
 */

package MDHS_Client;


import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MDHS extends Application{
    
    //static variables
    private static Scene scene;

    @Override
    public void start(Stage primaryStage) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("FXMLLogIn.fxml"));
        scene = new Scene(root, 600, 370);
        primaryStage.setTitle("Hello");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    
    //main to launch
    public static void main(String[] args) {
        
        launch(args);
    }

    
    static void loadScene(String fxml){
        Parent root;
        try {
            root = FXMLLoader.load(MDHS.class.getResource(fxml + ".fxml"));
            scene.setRoot(root);
        } catch (IOException e) {
            System.out.println("cannot load scene: IOException - " + e.getLocalizedMessage());
        }
    }
    
    
}
