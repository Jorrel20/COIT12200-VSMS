/*
 * Rory Allen - s#12149026
 * Jorrel Arboleda - s#XXXXXXXX
 */
package vsms;

import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

//main class - loads fxml on start
public class VSMS extends Application {
    
    private static Scene scene;
    private static ServiceModel model;
    
    @Override
    public void start(Stage stage) throws Exception {
        
        DatabaseBuilder.buildDB();
        model = new ServiceModel();
        
        Parent root = FXMLLoader.load(getClass().getResource("/view/FXMLMainView.fxml"));
        //root.getStylesheets().add(getClass().getResource("your_stylesheet.css").toString());
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    
    public static void loadScene(String fxml){
        try {
            Parent root = FXMLLoader.load(VSMS.class.getResource("/view/FXML" + fxml + "View.fxml"));
            scene.setRoot(root);
        } catch (IOException e) {
            System.out.println("cannot load scene: IOException - " + e.getLocalizedMessage());
        }
    }
    
    public static ServiceModel getModel (){
        return model;
    }

    public static void main(String[] args) {launch(args);}
}
