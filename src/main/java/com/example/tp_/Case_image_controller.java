package com.example.tp_;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.concurrent.ThreadLocalRandom;

import static com.example.tp_.Game_page.case_actuel;
import static com.example.tp_.Game_page.*;

public class Case_image_controller implements Initializable {
    public TextField Answer;
    public Label Word;
    int image_chosen;
    // @FXML Label Word ;
  //  @FXML TextField Answer ;
    @FXML ImageView img1;
    @FXML ImageView img2;
    @FXML ImageView img3;
    @FXML ImageView img4;
   static Stage stage;


    //ON CHARGE DES IMAGES ALEATOIREMENT D'APRES LEUR FICHIER
    public void Charger_images(){
         image_chosen=  ThreadLocalRandom.current().nextInt(5);

        if(image_chosen==0){
            Image Image = new Image("C:\\Users\\MY-PC\\IdeaProjects\\Tp_\\src\\main\\resources\\com\\example\\tp_\\images\\animals\\1.jpg");
            img1.setImage(Image);

            Image = new Image("C:\\Users\\MY-PC\\IdeaProjects\\Tp_\\src\\main\\resources\\com\\example\\tp_\\images\\animals\\fennec_fox.jpg");
            img2.setImage(Image);

            Image=new Image("C:\\Users\\MY-PC\\IdeaProjects\\Tp_\\src\\main\\resources\\com\\example\\tp_\\images\\animals\\2.jpg");
            img3.setImage(Image);

            Image=new Image("C:\\Users\\MY-PC\\IdeaProjects\\Tp_\\src\\main\\resources\\com\\example\\tp_\\images\\animals\\4.jpg");
            img4.setImage(Image);
            Word.setText("Fennec-Fox");


        }
        if(image_chosen==1){
            Image Image = new Image("C:\\Users\\MY-PC\\IdeaProjects\\Tp_\\src\\main\\resources\\com\\example\\tp_\\fruits\\animals\\1.jpg");
            img1.setImage(Image);

            Image = new Image("C:\\Users\\MY-PC\\IdeaProjects\\Tp_\\src\\main\\resources\\com\\example\\tp_\\images\\fruits\\pomegranate.jpg");
            img2.setImage(Image);

            Image=new Image("C:\\Users\\MY-PC\\IdeaProjects\\Tp_\\src\\main\\resources\\com\\example\\tp_\\images\\fruits\\2.jpg");
            img3.setImage(Image);

            Image=new Image("C:\\Users\\MY-PC\\IdeaProjects\\Tp_\\src\\main\\resources\\com\\example\\tp_\\images\\fruits\\4.jpg");
            img4.setImage(Image);
            img4.setFitWidth(150);
            Word.setText("Pomegranate");

        }
         if(image_chosen==2){
            Image Image = new Image("C:\\Users\\MY-PC\\IdeaProjects\\Tp_\\src\\main\\resources\\com\\example\\tp_\\images\\computer science\\1.png");
            img1.setImage(Image);

            Image = new Image("C:\\Users\\MY-PC\\IdeaProjects\\Tp_\\src\\main\\resources\\com\\example\\tp_\\images\\computer science\\2.jpg");
            img2.setImage(Image);

            Image=new Image("C:\\Users\\MY-PC\\IdeaProjects\\Tp_\\src\\main\\resources\\com\\example\\tp_\\images\\computer science\\4.jpg");
            img3.setImage(Image);

            Image=new Image("C:\\Users\\MY-PC\\IdeaProjects\\Tp_\\src\\main\\resources\\com\\example\\tp_\\images\\computer science\\Hard-Drive.png");
            img4.setImage(Image);

             Word.setText("Hard-Drive");
        }
         if(image_chosen==3){
             Image Image = new Image("C:\\Users\\MY-PC\\IdeaProjects\\Tp_\\src\\main\\resources\\com\\example\\tp_\\images\\jobs\\firefighters.jpg");
             img1.setImage(Image);

             Image = new Image("C:\\Users\\MY-PC\\IdeaProjects\\Tp_\\src\\main\\resources\\com\\example\\tp_\\images\\jobs\\1.jpg");
             img2.setImage(Image);

             Image=new Image("C:\\Users\\MY-PC\\IdeaProjects\\Tp_\\src\\main\\resources\\com\\example\\tp_\\images\\jobs\\2.jpg");
             img3.setImage(Image);

             Image=new Image("C:\\Users\\MY-PC\\IdeaProjects\\Tp_\\src\\main\\resources\\com\\example\\tp_\\images\\jobs\\4.jpg");
             img4.setImage(Image);
             Word.setText("Firefighters");

             }
        if(image_chosen==4){
            Image Image = new Image("C:\\Users\\MY-PC\\IdeaProjects\\Tp_\\src\\main\\resources\\com\\example\\tp_\\images\\sports\\1.jpg");
            img1.setImage(Image);

            Image = new Image("C:\\Users\\MY-PC\\IdeaProjects\\Tp_\\src\\main\\resources\\com\\example\\tp_\\images\\sports\\3.jpg");
            img2.setImage(Image);

            Image=new Image("C:\\Users\\MY-PC\\IdeaProjects\\Tp_\\src\\main\\resources\\com\\example\\tp_\\images\\sports\\LeBron James.jpg");
            img3.setImage(Image);

            Image=new Image("C:\\Users\\MY-PC\\IdeaProjects\\Tp_\\src\\main\\resources\\com\\example\\tp_\\images\\sports\\4.jpg");
            img4.setImage(Image);
            Word.setText("BasketBall");

        }




    }
    // CHARGER STAGE DE CASE IMAGE
    static void launch_stage(){

        try {
            System.out.println("clicked");
             stage = new Stage();
            FXMLLoader fxmlLoader2 = new FXMLLoader(Main.class.getResource("Case_image.fxml"));
            Scene scene = new Scene(fxmlLoader2.load());
            stage.setScene(scene);
            stage.setResizable(false);
            stage.setTitle("Goose Game");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    //LA GESTION DES REPONSES
    public void Repondre(ActionEvent actionEvent) {
        if(image_chosen==0){
            if(Answer.getText().equalsIgnoreCase("2")){ myjouer.setScore(myjouer.getScore()+10);
                case_actuel=case_actuel+2;
                if (case_actuel > 100) case_actuel = 100 - (case_actuel - 100); //
            }


        }
        if(image_chosen==1){
            if(Answer.getText().equalsIgnoreCase("2")) {
                myjouer.setScore(myjouer.getScore()+10);
                case_actuel=case_actuel+2;
                if (case_actuel > 100) case_actuel = 100 - (case_actuel - 100);
            }
        }

        if(image_chosen==2){
            if(Answer.getText().equalsIgnoreCase("4")) {
                myjouer.setScore(myjouer.getScore()+10);
                case_actuel=case_actuel+2;
                if (case_actuel > 100) case_actuel = 100 - (case_actuel - 100); }



        }
        if(image_chosen==3){
            if(Answer.getText().equalsIgnoreCase("1")) {
                myjouer.setScore(myjouer.getScore()+20);
                case_actuel=case_actuel+2;
                if (case_actuel > 100) case_actuel = 100 - (case_actuel - 100); }


        }
        if(image_chosen==4){
            if(Answer.getText().equalsIgnoreCase("3")) {
                myjouer.setScore(myjouer.getScore()+10);
                case_actuel=case_actuel+2;
                if (case_actuel > 100) case_actuel = 100 - (case_actuel - 100); }


        }

        stage.close();




    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Charger_images();
    }
}
