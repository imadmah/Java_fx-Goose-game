package com.example.tp_;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {
    static String jouer_nom;
    static Stage mystage;

    @Override
    public void start(Stage stage) throws IOException {
        mystage = stage;
         Jeu Jouers = new Jeu(stage); // ON LANCE UN JEU
    }


    public static void main(String[] args) {
            launch();
    }
}