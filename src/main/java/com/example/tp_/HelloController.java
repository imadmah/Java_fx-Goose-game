package com.example.tp_;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Cursor;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.effect.Light;
import javafx.scene.input.InputEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.robot.Robot;
import javafx.stage.Stage;
import javafx.util.Duration;
import org.controlsfx.control.PopOver;

import java.io.*;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.Scanner;

public class HelloController implements Initializable  {

    @FXML GridPane grid; // la grille des butttons
    @FXML Label Roll_result; // resutat de tire de des
    @FXML AnchorPane Root; // root of scene
    @FXML Button Dice_btn; // for setting the hoverProperty for the button
    @FXML Label case_destination;
    Joueur myjouer;
    @FXML Label player_name;
    @FXML Label Current_score ;
    @FXML Label Best_src;
    @FXML Label Best_src_ingame;
    @FXML Label case_actuel_txt;

     String[] jouer_infos;
       Button[] button= new Button[100];
    static int rool_resu;
     static int case_actuel=1;
    boolean jouer_existe_deja ;
    Plateau plateau= new Plateau_jeu();
     boolean case_clicked=true;
     PopOver popOver=new PopOver();


   public void Read_jouer (){

            try {
                File myObj = new File("src/main/java/com/example/tp_/Jouers.txt"); // la lecture de fichier
                Scanner myReader = new Scanner(myObj);
                while (myReader.hasNextLine()) {
                    String data = myReader.nextLine();
                    jouer_infos=data.split(" ");
                    if(jouer_infos[0].equalsIgnoreCase(HelloApplication.jouer_nom)){
                        jouer_existe_deja=true;
                        player_name.setText("Player's name : \n "+jouer_infos[0]);
                        Current_score.setText(jouer_infos[1]);
                        Best_src.setText(jouer_infos[2]);
                        myjouer=new Joueur(jouer_infos[0],Integer.parseInt(jouer_infos[1]),Integer.parseInt(jouer_infos[2]));
                        myReader.close();
                        return; // parceque on veut pas lire la suite de le fichier
                        }
                }
                myjouer=new Joueur(HelloApplication.jouer_nom,0,0);
                    player_name.setText("Player's name : \n "+HelloApplication.jouer_nom);
                    Best_src.setText(jouer_infos[2]);
                    Current_score.setText(jouer_infos[1]);


                myReader.close();
            } catch (FileNotFoundException e) {
                System.out.println("An error occurred.");
                e.printStackTrace();
            }
        } // lit d'apres un fichier les jouers
   public void Creat_cases(){ // cette methode faire tout
        int i=0;
        int maxline = 11;
        int maxcolon = 11;
        int minline =0;
        int mincolon=0;
        int cpt2 = 0;
        int cpt1 = 0;

       grid.setHgap(3);
       grid.setVgap(3);
       for(int j=0;j<100;j++) {
           button[j] = new Button("case"+j);
           button[j].setText(Integer.toString(j+1));
           button[j].setPrefHeight(70);
           button[j].setPrefWidth(70);


           // GridPane.setMargin(button[j], new Insets(0,0 , 0, 0));
           //  else GridPane.setMargin(button[j], new Insets(0,0 , 5, 10));

       }



       try {


           while(i<100) {

               while (cpt1 < maxcolon & i<100) { //----->
                   grid.add(button[i], cpt1, cpt2);
                   i++;
                   cpt1++;
               }
               if(i==100) break;
               cpt2++;
               cpt1--; // t3 la boucle
               while (cpt2 < maxline & i<100) { //----- down
                   grid.add(button[i], cpt1, cpt2);
                   cpt2++;
                   i++;
               }

               cpt1--;

               cpt2--; // ki zad bach khrj mla boucle
               while (cpt1 > mincolon& i<100) { //<----------
                   grid.add(button[i], cpt1, cpt2);
                   i++;
                   cpt1 = cpt1 - 1;
               }

               cpt1++;// t3 la boucle
               while (cpt2 > minline + 1 & i<100 ) { //------^ up
                   grid.add(button[i], cpt1, cpt2 - 1);
                   cpt2 = cpt2 - 1;
                   i++;
               }


               minline++;
               mincolon++;
               maxcolon--;
               maxline--;
               cpt1++; //pour le decalage
           }




       } catch (Exception e) {
           e.printStackTrace();
       }

   }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Read_jouer();
        Creat_cases();
        plateau.init_plateau();
        Set_ButtonsEvents();

        HelloApplication.mystage.setOnCloseRequest(we -> {
            System.out.println("jouer_info_written");
            write_jouer();
        });

    }

    public void Dice_action()   {

            System.out.println(case_clicked);
            if(case_clicked){ // on verifie que la case est clique
                rool_resu= myjouer.lance_des();
                 case_actuel=rool_resu+case_actuel;
                 if(case_actuel==100) return;
                if(case_actuel>100){
                    case_actuel=100-(case_actuel-100);
                }
                case_destination.setText(String.valueOf(case_actuel));
             case_clicked=false;
             Roll_result.setText("Dice's result : \n"+rool_resu);
         }
            System.out.println(case_clicked);
    }

    public void Set_ButtonsEvents(){

            button[0].setStyle("-fx-background-radius: 15; -fx-border-color:black ;" +
                "-fx-border-radius:15;-fx-background-color: #fce303");
            button[0].hoverProperty().addListener((observableValue, aBoolean, t1) ->{
                if(t1) button[0].setStyle("-fx-background-radius: 15; -fx-border-color:black ;" + "-fx-border-radius:15;-fx-background-color: #e5dcd6");
                else button[0].setStyle("-fx-background-radius: 15; -fx-border-color:black ;-fx-border-radius:15;-fx-background-color: #fce303");
                    } );

            for(int i =1;i<99;i++){
                       int j=i;
                       button[i].setCursor(Cursor.HAND);
                       if(Plateau_jeu.cases[i].getCouleur().equalsIgnoreCase("BLANCHE")){
                           button[i].hoverProperty().addListener((observableValue, aBoolean, t1) ->{
                               if(t1)  button[j].setStyle("-fx-background-radius: 15; -fx-border-color:black ;" +
                                       "-fx-border-radius:15;-fx-background-color: #e5dcd6");
                               else button[j].setStyle("-fx-background-radius: 15; -fx-border-color:black ;" +
                                       "-fx-border-radius:15;-fx-background-color: #00baba");
                           } );
                           button[i].setStyle("-fx-background-radius: 15; -fx-border-color:black ;" +
                                   "-fx-border-radius:15;-fx-background-color: #00baba");
                           button[i].setOnAction(actionEvent -> {
                               if(j+1==case_actuel) {
                                   popOver.setTitle("Case parcours");
                                   Label l = new Label("Case parcours");
                                   HBox hBox = new HBox();
                                   hBox.getChildren().add(l);
                                   popOver.setContentNode(hBox);
                                   popOver.show(button[j]);
                                   popOver.setFadeInDuration(Duration.millis(300));
                                   case_clicked = true;
                                   case_actuel=Plateau_jeu.cases[j].mouvement(case_actuel);
                                   if(!Plateau_jeu.cases[case_actuel-1].getCouleur().equalsIgnoreCase("BLANCHE"))
                                       case_actuel=Plateau_jeu.cases[case_actuel-1].mouvement(case_actuel);
                                       case_actuel_txt.setText(String.valueOf(case_actuel));


                               }});
                       }

                       if(Plateau_jeu.cases[i].getCouleur().equalsIgnoreCase( "Rouge")){

                           button[i].hoverProperty().addListener((observableValue, aBoolean, t1) ->{
                               if(t1)  button[j].setStyle("-fx-background-radius: 15; -fx-border-color:black ;" +
                                       "-fx-border-radius:15;-fx-background-color: #e5dcd6");
                               else button[j].setStyle("-fx-background-radius: 15; -fx-border-color:black ;" +
                                       "-fx-border-radius:15;-fx-background-color: #c53a48");
                           } );
                           button[i].setStyle("-fx-background-radius: 15; -fx-border-color:black ;" +
                                   "-fx-border-radius:15;-fx-background-color: #c53a48");
                           button[i].setOnAction(actionEvent -> {
                               if(j+1==case_actuel) {
                                   System.out.println("clicked");
                                   popOver.setTitle("Case Malus");
                                   popOver.setHeight(50);
                                   popOver.setWidth(100);
                                   Label l = new Label("Case Malus :\n Vous etes recule de 2 cases");
                                   HBox hBox = new HBox();
                                   hBox.getChildren().add(l);
                                   popOver.setContentNode(hBox);
                                   popOver.show(button[j]);
                                   popOver.setFadeInDuration(Duration.millis(300));
                                   case_clicked = true;
                                   case_actuel=Plateau_jeu.cases[j].mouvement(case_actuel);
                                   if(!Plateau_jeu.cases[case_actuel-1].getCouleur().equalsIgnoreCase("BLANCHE"))
                                       case_actuel=Plateau_jeu.cases[case_actuel-1].mouvement(case_actuel);
                                       case_actuel_txt.setText(String.valueOf(case_actuel));
                               }});
                       }

                       if(Plateau_jeu.cases[i].getCouleur().equalsIgnoreCase( "Rose")){
                           button[i].hoverProperty().addListener((observableValue, aBoolean, t1) ->{
                               if(t1)  button[j].setStyle("-fx-background-radius: 15; -fx-border-color:black ;" +
                                       "-fx-border-radius:15;-fx-background-color: #e5dcd6");
                               else button[j].setStyle("-fx-background-radius: 15; -fx-border-color:black ;" +
                                       "-fx-border-radius:15;-fx-background-color: #e489a6");
                           } );
                           button[i].setStyle("-fx-background-radius: 15; -fx-border-color:black ;" +
                                   "-fx-border-radius:15;-fx-background-color: #e489a6");
                           button[i].setOnAction(actionEvent -> {
                               if(j+1==case_actuel) {
                                   System.out.println("clicked");
                                   case_clicked = true;
                                   try {

                                       System.out.println("clicked");
                                       FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("case_question.fxml"));
                                       Scene scene=new Scene(fxmlLoader.load(),600,400);
                                       Button node = (Button) actionEvent.getSource();
                                       Stage thisStage = (Stage) node.getScene().getWindow();
                                       thisStage.setScene(scene);
                                   } catch (IOException e) {
                                       e.printStackTrace();
                                   }
                                   case_actuel=Plateau_jeu.cases[j].mouvement(case_actuel);
                                   if(!Plateau_jeu.cases[case_actuel-1].getCouleur().equalsIgnoreCase("BLANCHE"))
                                       case_actuel=Plateau_jeu.cases[case_actuel-1].mouvement(case_actuel);
                                   case_actuel_txt.setText(String.valueOf(case_actuel));

                               }});
                       }
                       if(Plateau_jeu.cases[i].getCouleur().equalsIgnoreCase("VERTE")){
                           button[i].hoverProperty().addListener((observableValue, aBoolean, t1) ->{
                               if(t1)  button[j].setStyle("-fx-background-radius: 15; -fx-border-color:black ;" +
                                       "-fx-border-radius:15;-fx-background-color: #e5dcd6");
                               else button[j].setStyle("-fx-background-radius: 15; -fx-border-color:black ;" +
                                       "-fx-border-radius:15;-fx-background-color: #6ccf41");
                           } );
                           button[i].setStyle("-fx-background-radius: 15; -fx-border-color:black ;" +
                                   "-fx-border-radius:15;-fx-background-color: #6ccf41");
                           button[i].setOnAction(actionEvent -> {

                               if(j+1==case_actuel) {
                                   System.out.println("clicked");
                                   case_clicked = true;
                                   popOver.setTitle("Case Bonus");
                                   popOver.setHeight(50);
                                   popOver.setWidth(100);
                                   Label l = new Label("Case Bonus :\n Vous etes avance de 2 cases");
                                   HBox hBox = new HBox();
                                   hBox.getChildren().add(l);
                                   popOver.setContentNode(hBox);
                                   popOver.show(button[j]);
                                   popOver.setFadeInDuration(Duration.millis(300));
                                   case_actuel=Plateau_jeu.cases[j].mouvement(case_actuel);
                                   if(!Plateau_jeu.cases[case_actuel-1].getCouleur().equalsIgnoreCase("BLANCHE"))
                                       case_actuel=Plateau_jeu.cases[case_actuel-1].mouvement(case_actuel);
                                   case_actuel_txt.setText(String.valueOf(case_actuel));

                               }});
                       }
                       if(Plateau_jeu.cases[i].getCouleur().equalsIgnoreCase( "bleu")){
                           button[i].hoverProperty().addListener((observableValue, aBoolean, t1) ->{
                               if(t1)  button[j].setStyle("-fx-background-radius: 15; -fx-border-color:black ;" +
                                       "-fx-border-radius:15;-fx-background-color: #e5dcd6");
                               else button[j].setStyle("-fx-background-radius: 15; -fx-border-color:black ;" +
                                       "-fx-border-radius:15;-fx-background-color: #1773c5");
                           } );
                           button[i].setStyle("-fx-background-radius: 15; -fx-border-color:black ;" +
                                   "-fx-border-radius:15;-fx-background-color: #1773c5");
                           button[i].setOnAction(actionEvent -> {

                               if(j+1==case_actuel) {
                                   System.out.println("clicked");
                                   case_clicked = true;
                                   case_actuel=Plateau_jeu.cases[j].mouvement(case_actuel);
                                   if(!Plateau_jeu.cases[case_actuel-1].getCouleur().equalsIgnoreCase("BLANCHE"))
                                       case_actuel=Plateau_jeu.cases[case_actuel-1].mouvement(case_actuel);
                                   case_actuel_txt.setText(String.valueOf(case_actuel));

                               }});
                       }
                       if(Plateau_jeu.cases[i].getCouleur().equalsIgnoreCase("orange")){
                           button[i].hoverProperty().addListener((observableValue, aBoolean, t1) ->{
                               if(t1)  button[j].setStyle("-fx-background-radius: 15; -fx-border-color:black ;" +
                                       "-fx-border-radius:15;-fx-background-color: #e5dcd6");
                               else button[j].setStyle("-fx-background-radius: 15; -fx-border-color:black ;" +
                                       "-fx-border-radius:15;-fx-background-color: #e99d4e");
                           } );
                           button[i].setStyle("-fx-background-radius: 15; -fx-border-color:black ;" +
                                   "-fx-border-radius:15;-fx-background-color: #e99d4e");
                           button[i].setOnAction(actionEvent -> {

                               if(j+1==case_actuel) {
                                   System.out.println("clicked");
                                   case_clicked = true;
                                   case_actuel=Plateau_jeu.cases[j].mouvement(case_actuel);
                                   Label l = new Label("Case Saut :\n Vous etes avance a la case"+case_actuel);
                                   HBox hBox = new HBox();
                                   hBox.getChildren().add(l);
                                   popOver.setContentNode(hBox);
                                   popOver.show(button[j]);
                                   popOver.setFadeInDuration(Duration.millis(300));
                                   if(!Plateau_jeu.cases[case_actuel-1].getCouleur().equalsIgnoreCase("BLANCHE"))
                                       case_actuel=Plateau_jeu.cases[case_actuel-1].mouvement(case_actuel);
                                   case_actuel_txt.setText(String.valueOf(case_actuel));

                               }});
                       }
                       }

                    button[99].setStyle("-fx-base: coral;-fx-background-radius: 15; -fx-background-color: #000000");
                    button[99].hoverProperty().addListener((observableValue, aBoolean, t1) ->{
                        if(t1)  button[99].setStyle("-fx-background-radius: 15; -fx-border-color:black ;" +
                                "-fx-border-radius:15;-fx-background-color: #e5dcd6");
                        else button[99].setStyle("-fx-background-radius: 15; -fx-border-color:black ;" +
                                "-fx-border-radius:15;-fx-background-color: #000000");
                           } );
                    Dice_btn.hoverProperty().addListener((observableValue, aBoolean, t1) ->{
                        if(t1)  Dice_btn.setStyle("-fx-background-color: Transparent; -fx-background-radius: 30; " +
                                "-fx-border-color: green; -fx-border-radius: 30;");
                        else Dice_btn.setStyle("-fx-background-radius: 30; -fx-border-color:black ;" +
                                "-fx-border-radius:30;-fx-background-color: transparent");
                    } );



            } // TO DO :
    public void write_jouer(){
        FileWriter fileWriter;
        try {
            fileWriter = new FileWriter("src/main/java/com/example/tp_/Jouers.txt",true);
            PrintWriter printWriter = new PrintWriter(fileWriter);
            if(!jouer_existe_deja) {
                printWriter.append("\n" + HelloApplication.jouer_nom + " " + myjouer.getScore()+" "+myjouer.getBest_score());
            }
            else{
            if(myjouer.getBest_score()>=myjouer.getScore())  printWriter.append("\n" + HelloApplication.jouer_nom + " " + myjouer.getScore()+" "+myjouer.getBest_score());
            else  printWriter.append("\n" + HelloApplication.jouer_nom + " " + myjouer.getScore()+" "+myjouer.getScore());

            }
            printWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }



    }// TO DO : check the line in which the jouer will be written


}