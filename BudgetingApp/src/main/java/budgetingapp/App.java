/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package budgetingapp;

import java.sql.SQLException;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 *
 * @author mmatila
 */
public class App extends Application {
    
    @Override
    public void start(Stage primaryStage) throws SQLException {
        Button btn = new Button();
        TextField username = new TextField();
        btn.setText("Create an account");
        btn.setOnAction(new EventHandler<ActionEvent>() {
        
            @Override
            public void handle(ActionEvent e) {
                System.out.println("Hello world!");
            }
        });
        
        StackPane root = new StackPane();
        root.getChildren().add(btn);
        
        
        Scene scene = new Scene(root, 600, 480);
        
        primaryStage.setTitle("Hello World!");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
