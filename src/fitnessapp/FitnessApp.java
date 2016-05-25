/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fitnessapp;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author Usuario
 */
public class FitnessApp extends Application {
    
    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader myLoader = new FXMLLoader(getClass().getResource("mainwindowv2.fxml"));
        Parent root = myLoader.load();
        Mainwindowv2Controller window1 = myLoader.<Mainwindowv2Controller>getController();        
        Scene scene = new Scene(root);        
        stage.setScene(scene);
        stage.setTitle("Empty Route");
        window1.initData(stage);
        stage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
