package com.example.tp_;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.io.*;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Scanner;

import static com.example.tp_.LoginPage.continue_partie;
import static com.example.tp_.Plateau_jeu.*;
import static com.example.tp_.Case_image_controller.*;

public class HelloController implements Initializable {

    @FXML
    GridPane grid; // la grille des butttons
    @FXML
    Label Roll_result; // resutat de tire de des
    @FXML
    AnchorPane Root; // root of scene
    @FXML
    Button Dice_btn; // for setting the hoverProperty for the button
    @FXML
    Label case_destination;
    static Joueur myjouer;
    @FXML
    Label player_name;
    @FXML
    Label Current_score;
    @FXML
    Label Best_src;
    @FXML
    Label case_actuel_txt;
    String[] jouer_infos;
    Button[] button = new Button[100];
    static int rool_resu;
    static int case_actuel = 1;
    private int position_in_file;
    boolean jouer_existe_deja;
    Plateau plateau = new Plateau_jeu();
    boolean case_clicked = true;
    int previous_Case;    // ce int pour connaintre la case avant , elle sera util quand le jouer suspendre la partie sans cliquer sur la case distination


    public void Read_jouer(boolean continue_partie) {
        try {
            File myObj = new File("src/main/java/com/example/tp_/Jouers.txt"); // la lecture de fichier
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                jouer_infos = data.split(" ");
                if (jouer_infos[0].equalsIgnoreCase(HelloApplication.jouer_nom)) {
                    jouer_existe_deja = true;
                    player_name.setText("Player's name : \n " + jouer_infos[0]);
                    if (continue_partie) // ici on fait la difference entre le jouer qui a suspendu la partie ou non//
                    {

                        Best_src.setText(jouer_infos[2]);
                        case_actuel = Integer.parseInt(jouer_infos[3]);
                        Current_score.setText(jouer_infos[1]);
                        case_actuel_txt.setText(String.valueOf(case_actuel));
                        myjouer = new Joueur(jouer_infos[0], Integer.parseInt(jouer_infos[1]), Integer.parseInt(jouer_infos[2]));
                        // ICI ON CHARGE LES INDICES DES CASES SPECIAUX DANS LEURS //
                        //VARIABLE A TRAVERS UN FICHIER QUI EST SOUS LE NOM DE JOUEUR //
                        File plateau_fichier = new File(HelloApplication.jouer_nom + ".txt"); // la lecture de fichier
                        Scanner plateau_fichier_reader = new Scanner(plateau_fichier);
                        String data2 = plateau_fichier_reader.nextLine();
                        String[] plateau_indices = data2.split(" ");
                        for (int i = 0; i < 5; i++) {

                            indices_case_bonus[i] = Integer.parseInt(plateau_indices[i]);
                        }
                        data2 = plateau_fichier_reader.nextLine();
                        plateau_indices = data2.split(" ");
                        for (int i = 0; i < 5; i++) {


                            indices_case_malus[i] = Integer.parseInt(plateau_indices[i]);
                        }
                        data2 = plateau_fichier_reader.nextLine();
                        plateau_indices = data2.split(" ");
                        for (int i = 0; i < 5; i++) {


                            indices_case_definition[i] = Integer.parseInt(plateau_indices[i]);
                        }
                        data2 = plateau_fichier_reader.nextLine();
                        plateau_indices = data2.split(" ");
                        for (int i = 0; i < 5; i++) {
                            indices_case_image[i] = Integer.parseInt(plateau_indices[i]);
                        }
                        data2 = plateau_fichier_reader.nextLine();
                        plateau_indices = data2.split(" ");
                        for (int i = 0; i < 5; i++) {
                            indices_case_saut[i] = Integer.parseInt(plateau_indices[i]);
                        }
                        plateau_fichier_reader.close();
                    } else {

                        Current_score.setText("0");
                        Best_src.setText(jouer_infos[1]);
                        myjouer = new Joueur(jouer_infos[0], 0, Integer.parseInt(jouer_infos[1]));
                    }
                    myReader.close();
                    return; // parceque on veut pas lire la suite de le fichier
                }
                position_in_file++; // l'indice de line de jouer dans le ficher
            }


            myjouer = new Joueur(HelloApplication.jouer_nom, 0, 0);
            player_name.setText("Player's name : \n " + HelloApplication.jouer_nom);
            Best_src.setText("0");
            Current_score.setText("0");


            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    } // lit d'apres un fichier les jouers

    public void Creat_cases() { // cette methode faire tout
        int i = 0;
        int maxline = 14;
        int maxcolon = 14;
        int minline = 0;
        int mincolon = 0;
        int cpt2 = 0;
        int cpt1 = 0;

        grid.setHgap(3);
        grid.setVgap(3);
        for (int j = 0; j < 100; j++) {
            button[j] = new Button("case" + j);
            button[j].setText(Integer.toString(j + 1));
            button[j].setPrefHeight(70);
            button[j].setPrefWidth(70);
            // GridPane.setMargin(button[j], new Insets(0,0 , 0, 0));
            //  else GridPane.setMargin(button[j], new Insets(0,0 , 5, 10));
        }


        try {


            while (i < 100) {

                while (cpt1 < maxcolon & i < 100) { //----->
                    grid.add(button[i], cpt1, cpt2);
                    i++;
                    cpt1++;
                }
                if (i == 100) break;
                cpt2++;
                cpt1--; // t3 la boucle
                while (cpt2 < maxline & i < 100) { //----- down
                    grid.add(button[i], cpt1, cpt2);
                    cpt2++;
                    i++;
                }

                cpt1--;

                cpt2--; // ki zad bach khrj mla boucle
                while (cpt1 > mincolon & i < 100) { //<----------
                    grid.add(button[i], cpt1, cpt2);
                    i++;
                    cpt1 = cpt1 - 1;
                }
                minline++;
                cpt1++;// t3 la boucle
                while (cpt2 > minline + 1 & i < 100) { //------^ up
                    grid.add(button[i], cpt1, cpt2 - 1);
                    cpt2 = cpt2 - 1;
                    i++;
                }


                minline++;
                mincolon=mincolon+2;
                maxcolon=maxcolon-2;
                maxline=maxline-2;
                cpt1++; //pour le decalage
            }


        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void Dice_action() {
        case_actuel_txt.setText(String.valueOf(case_actuel));
        Current_score.setText(String.valueOf(myjouer.getScore()));
        previous_Case = case_actuel;
        System.out.println(case_clicked);
        if (case_clicked) { // on verifie que la case est clique

            rool_resu = myjouer.lance_des();
            case_actuel = rool_resu + case_actuel;
            if (case_actuel == 100) return;
            if (case_actuel > 100) case_actuel = 100 - (case_actuel - 100);
            case_destination.setText(String.valueOf(case_actuel));
            case_clicked = false;
            Roll_result.setText("Dice's result : \n" + rool_resu);
        }

    }

    public void Set_ButtonsEvents() {


        button[0].setStyle("-fx-background-radius: 15; -fx-border-color:black ;" +
                "-fx-border-radius:15;-fx-background-color: #fce303");
        button[0].hoverProperty().addListener((observableValue, aBoolean, t1) -> {
            if (t1)
                button[0].setStyle("-fx-background-radius: 15; -fx-border-color:black ;" + "-fx-border-radius:15;-fx-background-color: #e5dcd6");
            else
                button[0].setStyle("-fx-background-radius: 15; -fx-border-color:black ;-fx-border-radius:15;-fx-background-color: #fce303");
        });

        for (int i = 1; i < 99; i++) {
            int j = i;
            button[i].setCursor(Cursor.HAND);

            if (cases[i].getCouleur().equalsIgnoreCase("BLANCHE")) {
                button[i].hoverProperty().addListener((observableValue, aBoolean, t1) -> {
                    if (t1) button[j].setStyle("-fx-background-radius: 15; -fx-border-color:black ;" +
                            "-fx-border-radius:15;-fx-background-color: #e5dcd6");
                    else button[j].setStyle("-fx-background-radius: 15; -fx-border-color:black ;" +
                            "-fx-border-radius:15;-fx-background-color: #00baba");
                });
                button[i].setStyle("-fx-background-radius: 15; -fx-border-color:black ;" +
                        "-fx-border-radius:15;-fx-background-color: #00baba");

                button[i].setOnAction(actionEvent -> {
                    if (j + 1 == case_actuel) {
                        case_clicked = true;
                        case_actuel = cases[j].mouvement(case_actuel);
                        if (!cases[case_actuel - 1].getCouleur().equalsIgnoreCase("BLANCHE"))
                            case_actuel = cases[case_actuel - 1].mouvement(case_actuel);
                        case_actuel_txt.setText(String.valueOf(case_actuel));


                    }
                });
            }
            if (cases[i].getCouleur().equalsIgnoreCase("Rouge")) {

                button[i].hoverProperty().addListener((observableValue, aBoolean, t1) -> {
                    if (t1) button[j].setStyle("-fx-background-radius: 15; -fx-border-color:black ;" +
                            "-fx-border-radius:15;-fx-background-color: #e5dcd6");
                    else button[j].setStyle("-fx-background-radius: 15; -fx-border-color:black ;" +
                            "-fx-border-radius:15;-fx-background-color: #c53a48");
                });
                button[i].setStyle("-fx-background-radius: 15; -fx-border-color:black ;" +
                        "-fx-border-radius:15;-fx-background-color: #c53a48");
                button[i].setOnAction(actionEvent -> {
                    if (j + 1 == case_actuel) {
                        System.out.println("clicked");
                        myjouer.setScore(myjouer.getScore() - 10);
                        Current_score.setText(String.valueOf(myjouer.getScore()));
                        case_clicked = true;
                        case_actuel = cases[j].mouvement(case_actuel);
                        if (!cases[case_actuel - 1].getCouleur().equalsIgnoreCase("BLANCHE"))
                            case_actuel = cases[case_actuel - 1].mouvement(case_actuel);
                        case_actuel_txt.setText(String.valueOf(case_actuel));
                    }
                });
            }// LA CASE MALUS
            if (cases[i].getCouleur().equalsIgnoreCase("Rose")) {
                button[i].hoverProperty().addListener((observableValue, aBoolean, t1) -> {
                    if (t1) button[j].setStyle("-fx-background-radius: 15; -fx-border-color:black ;" +
                            "-fx-border-radius:15;-fx-background-color: #e5dcd6");
                    else button[j].setStyle("-fx-background-radius: 15; -fx-border-color:black ;" +
                            "-fx-border-radius:15;-fx-background-color: #e489a6");
                });
                button[i].setStyle("-fx-background-radius: 15; -fx-border-color:black ;" +
                        "-fx-border-radius:15;-fx-background-color: #e489a6");
                button[i].setOnAction(actionEvent -> {
                    if (j + 1 == case_actuel) {
                        case_actuel = cases[j].mouvement(case_actuel);
                        case_actuel_txt.setText(String.valueOf(case_actuel));
                        Current_score.setText(String.valueOf(myjouer.getScore()));
                        case_clicked = true;
                        if (!cases[case_actuel - 1].getCouleur().equalsIgnoreCase("BLANCHE"))
                            case_actuel = cases[case_actuel - 1].mouvement(case_actuel);
                        case_actuel_txt.setText(String.valueOf(case_actuel));

                    }
                });
            } // LA CASE IMAGE
            if (cases[i].getCouleur().equalsIgnoreCase("VERTE")) {
                button[i].hoverProperty().addListener((observableValue, aBoolean, t1) -> {
                    if (t1) button[j].setStyle("-fx-background-radius: 15; -fx-border-color:black ;" +
                            "-fx-border-radius:15;-fx-background-color: #e5dcd6");
                    else button[j].setStyle("-fx-background-radius: 15; -fx-border-color:black ;" +
                            "-fx-border-radius:15;-fx-background-color: #6ccf41");
                });
                button[i].setStyle("-fx-background-radius: 15; -fx-border-color:black ;" +
                        "-fx-border-radius:15;-fx-background-color: #6ccf41");
                button[i].setOnAction(actionEvent -> {

                    if (j + 1 == case_actuel) {
                        System.out.println("clicked");
                        case_clicked = true;
                        myjouer.setScore(myjouer.getScore() + 10);
                        Current_score.setText(String.valueOf(myjouer.getScore()));
                        case_actuel = cases[j].mouvement(case_actuel);
                        if (!cases[case_actuel - 1].getCouleur().equalsIgnoreCase("BLANCHE"))
                            case_actuel = cases[case_actuel - 1].mouvement(case_actuel);
                        case_actuel_txt.setText(String.valueOf(case_actuel));

                    }
                });
            } // LA CASE BONUS
            if (cases[i].getCouleur().equalsIgnoreCase("bleu")) {
                button[i].hoverProperty().addListener((observableValue, aBoolean, t1) -> {
                    if (t1) button[j].setStyle("-fx-background-radius: 15; -fx-border-color:black ;" +
                            "-fx-border-radius:15;-fx-background-color: #e5dcd6");
                    else button[j].setStyle("-fx-background-radius: 15; -fx-border-color:black ;" +
                            "-fx-border-radius:15;-fx-background-color: #1773c5");
                });
                button[i].setStyle("-fx-background-radius: 15; -fx-border-color:black ;" +
                        "-fx-border-radius:15;-fx-background-color: #1773c5");
                button[i].setOnAction(actionEvent -> {
                    if (j + 1 == case_actuel) {
                        try {
                            System.out.println("clicked");

                            Stage stage = new Stage();
                            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource(""));
                            Scene scene = new Scene(fxmlLoader.load(), 400, 200);
                            stage.setScene(scene);
                            stage.show();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        System.out.println("clicked");

                        case_actuel = cases[j].mouvement(case_actuel);
                        case_clicked = true;
                        if (!cases[case_actuel - 1].getCouleur().equalsIgnoreCase("BLANCHE"))
                            case_actuel = cases[case_actuel - 1].mouvement(case_actuel);
                        case_actuel_txt.setText(String.valueOf(case_actuel));

                    }
                });
            } // LA CASE DEFINITION
            if (cases[i].getCouleur().equalsIgnoreCase("orange")) {
                button[i].hoverProperty().addListener((observableValue, aBoolean, t1) -> {
                    if (t1) button[j].setStyle("-fx-background-radius: 15; -fx-border-color:black ;" +
                            "-fx-border-radius:15;-fx-background-color: #e5dcd6");
                    else button[j].setStyle("-fx-background-radius: 15; -fx-border-color:black ;" +
                            "-fx-border-radius:15;-fx-background-color: #e99d4e");
                });
                button[i].setStyle("-fx-background-radius: 15; -fx-border-color:black ;" +
                        "-fx-border-radius:15;-fx-background-color: #e99d4e");
                button[i].setOnAction(actionEvent -> {

                    if (j + 1 == case_actuel) {
                        System.out.println("clicked");
                        case_clicked = true;
                        case_actuel = cases[j].mouvement(case_actuel);


                        if (!cases[case_actuel - 1].getCouleur().equalsIgnoreCase("BLANCHE"))
                            case_actuel = cases[case_actuel - 1].mouvement(case_actuel);
                        case_actuel_txt.setText(String.valueOf(case_actuel));

                    }
                });
            } // LA CASE SAUT
        }

        button[99].setStyle("-fx-base: coral;-fx-background-radius: 15; -fx-background-color: #000000");
        button[99].hoverProperty().addListener((observableValue, aBoolean, t1) -> {
            if (t1) button[99].setStyle("-fx-background-radius: 15; -fx-border-color:black ;" +
                    "-fx-border-radius:15;-fx-background-color: #e5dcd6");
            else button[99].setStyle("-fx-background-radius: 15; -fx-border-color:black ;" +
                    "-fx-border-radius:15;-fx-background-color: #000000");
        });
        Dice_btn.hoverProperty().addListener((observableValue, aBoolean, t1) -> {
            if (t1) Dice_btn.setStyle("-fx-background-color: Transparent; -fx-background-radius: 30; " +
                    "-fx-border-color: green; -fx-border-radius: 30;");
            else Dice_btn.setStyle("-fx-background-radius: 30; -fx-border-color:black ;" +
                    "-fx-border-radius:30;-fx-background-color: transparent");
        });


    } // TO DO : regler le score

    public void write_jouer() {

        List<String> lines;

        try {
            lines = Files.readAllLines(Path.of("src/main/java/com/example/tp_/Jouers.txt"));

            System.out.println(jouer_existe_deja);
            if (case_actuel == 100) {
                if (jouer_existe_deja) {
                    if (myjouer.getBest_score() >= myjouer.getScore())
                        lines.set(position_in_file, HelloApplication.jouer_nom + " " + myjouer.getBest_score());
                    else
                        lines.set(position_in_file, HelloApplication.jouer_nom + " " + myjouer.getScore());
                    Files.write(Path.of("src/main/java/com/example/tp_/Jouers.txt"), lines);

                } else {
                    PrintWriter printWriter = new PrintWriter(new FileWriter("src/main/java/com/example/tp_/Jouers.txt", true));
                    printWriter.append("\n" + HelloApplication.jouer_nom + " " + myjouer.getScore());
                    printWriter.close();
                }
            } // si on a arrive a la fin de la partie
            else {
                if (case_clicked) {
                    if (jouer_existe_deja) {
                        if (myjouer.getBest_score() >= myjouer.getScore())
                            lines.set(position_in_file, HelloApplication.jouer_nom + " " + myjouer.getScore() + " " + myjouer.getBest_score() + " " + case_actuel);
                        else
                            lines.set(position_in_file, HelloApplication.jouer_nom + " " + myjouer.getScore() + " " + myjouer.getScore() + " " + case_actuel);
                        Files.write(Path.of("src/main/java/com/example/tp_/Jouers.txt"), lines);
                    } else {
                        PrintWriter printWriter = new PrintWriter(new FileWriter("src/main/java/com/example/tp_/Jouers.txt", true));
                        printWriter.append("\n" + HelloApplication.jouer_nom + " " + myjouer.getScore() + " " + myjouer.getBest_score() + " " + case_actuel);
                        printWriter.close();
                    }
                } // esq le jouer a suspendu la partie et a clique sur la case distination
                else {
                    if (jouer_existe_deja) {
                        if (myjouer.getBest_score() >= myjouer.getScore())
                            lines.set(position_in_file, HelloApplication.jouer_nom + " " + myjouer.getScore() + " " + myjouer.getBest_score() + " " + previous_Case);
                        else
                            lines.set(position_in_file, HelloApplication.jouer_nom + " " + myjouer.getScore() + " " + myjouer.getScore() + " " + previous_Case);
                        Files.write(Path.of("src/main/java/com/example/tp_/Jouers.txt"), lines);
                    } else {
                        PrintWriter printWriter = new PrintWriter(new FileWriter("src/main/java/com/example/tp_/Jouers.txt", true));
                        printWriter.append("\n" + HelloApplication.jouer_nom + " " + myjouer.getScore() + " " + myjouer.getBest_score() + " " + previous_Case);
                        printWriter.close();
                    }
                }
                if (!continue_partie) {

                    File file = new File(HelloApplication.jouer_nom + ".txt");
                    System.out.println(file.getPath());
                    try {

                        // create a new file with name specified
                        // by the file object
                        boolean value = file.createNewFile();
                        if (value) {
                            PrintWriter printWriter = new PrintWriter(new FileWriter(file));

                            for (int i = 0; i < 5; i++)
                                printWriter.append(String.valueOf(Plateau_jeu.indices_case_bonus[i]) + " ");
                            printWriter.append("\n");
                            for (int i = 0; i < 5; i++) printWriter.append(indices_case_malus[i] + " ");
                            printWriter.append("\n");
                            for (int i = 0; i < 5; i++)
                                printWriter.append(String.valueOf(Plateau_jeu.indices_case_definition[i]) + " ");
                            printWriter.append("\n");
                            for (int i = 0; i < 5; i++)
                                printWriter.append(String.valueOf(Plateau_jeu.indices_case_image[i]) + " ");
                            printWriter.append("\n");
                            for (int i = 0; i < 5; i++)
                                printWriter.append(String.valueOf(Plateau_jeu.indices_case_saut[i]) + " ");
                            printWriter.close();


                        } else {
                            System.out.println("The file already exists.");
                        }
                    } catch (Exception e) {
                        e.getStackTrace();
                    }

                }
            }


        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
    } // TO DO : enregistrer l'etat de plateau

    public void save_plateau_state() {


    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Read_jouer(continue_partie);
        Creat_cases();
        plateau.init_plateau();
        Set_ButtonsEvents();
        HelloApplication.mystage.setOnCloseRequest(we -> {
            System.out.println("jouer_info_written");
            write_jouer();
        });

    }


}