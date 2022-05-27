package com.example.tp_;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;

import java.io.IOException;

public class LoginPage    {
    @FXML TextField jouer_input;
    Scene myscene ;



    public void login_btn(ActionEvent actionEvent) throws IOException {
        if(!jouer_input.getText().isBlank()){
        HelloApplication.jouer_nom=jouer_input.getText();
            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("Game_page.fxml"));
            myscene=new Scene(fxmlLoader.load(),850,550);
            HelloApplication.mystage.setScene(myscene);

    }
    else{ Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Oublier d'entrer un nom");
        alert.setHeaderText("");
        alert.setContentText("     " +
                "S'il vous plait Entrer votre nom");
        alert.showAndWait().ifPresent(rs -> {
            if (rs == ButtonType.OK) {
                System.out.println("Pressed OK.");
            }
        });}
    }



}
