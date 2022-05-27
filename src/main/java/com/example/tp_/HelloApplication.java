package com.example.tp_;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {
    static String jouer_nom;
    static Stage mystage;
    @Override
    public void start(Stage stage) throws IOException {
        mystage=stage;
        FXMLLoader fxmlLoader1 = new FXMLLoader(HelloApplication.class.getResource("Login_page.fxml"));
        Group root = new Group();
        Scene scene = new Scene( fxmlLoader1.load() , 850, 550, Color.CORAL);
        stage.setResizable(false);
        stage.setTitle("Goose Game");
        stage.setScene(scene);
        stage.show();
    }


    public static void main(String[] args) {
        launch();
    }
}