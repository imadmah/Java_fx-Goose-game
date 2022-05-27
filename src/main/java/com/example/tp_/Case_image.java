package com.example.tp_;

import javafx.event.ActionEvent;

import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Cursor;
import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;

import java.io.*;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.Scanner;
import org.controlsfx.control.PopOver;

import java.io.IOException;

public class Case_image extends Case {
    protected final String couleur = "ROSE";
    protected int index; // l'index de la case d'image
    protected Joueur joueur; // le joueur a la case d'image

    // le constructeur de la case de image
    public Case_image(int index, Joueur joueur) {
        this.index = index;
        this.joueur = joueur;
    }

    // le constructeur de la case de image
    public Case_image(int index) {
        this.index = index;
    }

    // retourne l'index de la case destination
    public int getIndex() {
        return this.index;
    }

    public int mouvement(int inter_index) {
        return inter_index;
    }
    public int mouvement(boolean reponse_juste) {
        System.out.print("La case " + this.getIndex() + " est une case image!");
        int new_index = this.getIndex();
        if (reponse_juste) {
            System.out.println("La reponse est juste!");
            new_index = this.getIndex() + 2;
            System.out.println(" Le joueur va atteindre la case :  " + new_index + ".");

        } else {
            System.out.println("La reponse est fausse!");
        }
        return new_index;
    }

    public int score(int score) {
        return score;
    }

    public int score(boolean reponse_juste, int score) {
        if (reponse_juste) {
            return score + 10;
        } else {
            return score;
        }

    }

    public Joueur getJoueur() {
        return this.joueur;
    }

    public void setJoueur(Joueur joueur) {
        this.joueur = joueur;
    }

    // retourne vrai si le joueur est dans cette case //ca sert fl'affichage blk
    public boolean occupe() { return this.joueur != null;}

    public String getCouleur() {
        return this.couleur;
    }
    public void repondre_btn(ActionEvent actionEvent) throws IOException {

    }
}
