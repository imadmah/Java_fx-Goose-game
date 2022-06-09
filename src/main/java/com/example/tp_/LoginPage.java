package com.example.tp_;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.Scanner;

public class LoginPage implements Initializable {
    @FXML
    TextField jouer_input;
    Scene myscene;
    @FXML
    private ChoiceBox choice_box;
    @FXML
    Label Best_src_ingame;
    static Boolean continue_partie = false;


    // ON VERIFIE SI LE JOUEUR A INTRODUIT UN NOM SINON IL VA CHARGER UNE ANCIENNE PARTIE //
    public void nouvelle_partie(ActionEvent actionEvent) throws IOException {
        if (!jouer_input.getText().isBlank()) {
            Main.jouer_nom = jouer_input.getText();
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("Game_page.fxml"));
            myscene = new Scene(fxmlLoader.load());
            Main.mystage.setScene(myscene);

        } else { //SI TEXT IS BLANK UNE MESSAGE D'ERREUR APPARAIT
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Oublier d'entrer un nom");
            alert.setHeaderText("");
            alert.setContentText("     " +
                    "S'il vous plait Entrer votre nom");
            alert.showAndWait().ifPresent(rs -> {
                if (rs == ButtonType.OK) {
                    System.out.println("Pressed OK.");
                }
            });
        }
    }
    // ON CHARGE LES JOUERS D'APRES LE FICHIER (ICI UNE REMARQUE LA DIFFRENCE ENTRE UN JOUEUR QUI A TERMINE LA DERNIERE PARTIE JOUEE ET              //
    // UN JOUEUR QUI N'A PAS C'EST LE CHAMP DE CASE DANS LE FICHIER C'EST COMME CA ON DISTENQUE ENTRE LES JOUERS ET ON AFFICHE SEULEMENT CES DERNIERS//
    public void load_players() {

        try {
            File myObj = new File("src/main/java/com/example/tp_/Jouers.txt"); // la lecture de fichier des jouers qui ont suspendu la partie
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                String[] jouer_infos = data.split(" ");
                if (jouer_infos.length == 4) {
                    choice_box.getItems().add(jouer_infos[0]);
                }
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        load_players();
        Read_best_score();
    }
    //C'EST L'EVENT DE BUTTON CHARGER_PARTIE
    public void chrager_partie(ActionEvent actionEvent) {
        Main.jouer_nom = (String) choice_box.getSelectionModel().getSelectedItem();
        continue_partie = true; // POUR QUE ON CHARGE LE PLATEAU //
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("Game_page.fxml"));
        try {
            myscene = new Scene(fxmlLoader.load());
        } catch (IOException e) {
            e.printStackTrace();
        }
        Main.mystage.setScene(myscene);


    }
    // LA LECTURE DE MIELLEUR SCORE//
    public void Read_best_score(){
        File myObj = new File("src/main/java/com/example/tp_/Best_score.txt"); // LECTURE DE FICHIER DE JOUEUR//
        try {
            Scanner myReader = new Scanner(myObj);
            String best_score = myReader.nextLine();

            Best_src_ingame.setText(best_score);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
