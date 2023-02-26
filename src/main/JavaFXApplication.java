/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMain.java to edit this template
 */
package main;

import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

/**
 *\
 * @author Admin
 */
public class JavaFXApplication extends Application {

    @Override
    public void start(Stage primaryStage) throws IOException {
        Parent login = FXMLLoader.load(getClass().getResource("/view/DangNhap.fxml"));
        Scene scene_login;
        scene_login = new Scene(login);
        scene_login.getStylesheets().add("/CSS/globalStyle.css");

        primaryStage.setTitle("Hello World!");
        primaryStage.setScene(scene_login);
        primaryStage.setResizable(false);
        primaryStage.getScene().setFill(Color.TRANSPARENT);

        primaryStage.show();
    }

    /**
     * @param args the command line argumentsjadm
     */
    public static void main(String[] args) {
        launch(args);
    }

}
