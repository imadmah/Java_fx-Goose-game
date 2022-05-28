package com.example.tp_;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.Scanner;

public class LoginPage implements Initializable {
    @FXML TextField jouer_input;
    Scene myscene ;
    @FXML ChoiceBox choice_box;
    static Boolean continue_partie=false;



    public void nouvelle_partie(ActionEvent actionEvent) throws IOException {

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
    public void load_players(){

        try {
            File myObj = new File("src/main/java/com/example/tp_/Jouers.txt"); // la lecture de fichier des jouers qui ont suspendu la partie
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
               String[] jouer_infos=data.split(" ");
               if(jouer_infos.length==4){
                choice_box.getItems().add(jouer_infos[0]);
                }}
        myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        load_players();
    }

    public void chrager_partie(ActionEvent actionEvent) {
            HelloApplication.jouer_nom= (String) choice_box.getSelectionModel().getSelectedItem();
            continue_partie=true;
            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("Game_page.fxml"));
            try {
                myscene=new Scene(fxmlLoader.load(),850,550);
            } catch (IOException e) {
                e.printStackTrace();
            }
            HelloApplication.mystage.setScene(myscene);




    }
}
