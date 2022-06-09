package com.example.tp_;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.IOException;

// La classe du jeu
public class Jeu {
private Stage stage ;

    public Jeu(Stage stage) {
        try {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("Login_page.fxml"));
        Scene scene = null;
        scene = new Scene(fxmlLoader.load(), 850, 550, Color.CORAL);

        stage.setResizable(false);
        stage.setTitle("Goose Game");
        stage.setScene(scene);
        stage.show();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
}