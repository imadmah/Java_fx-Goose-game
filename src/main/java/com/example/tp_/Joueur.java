package com.example.tp_;

import java.util.concurrent.ThreadLocalRandom;

public class Joueur {
    private int id;
    private String nom;
    private Case case_actuelle;
    private int score;
    private int best_score;

    public Case getCase_actuelle() {
        return case_actuelle;
    }

    public int getBest_score() {
        return best_score;
    }

    public Joueur(int id, String nom) {
        this.id = id;
        this.nom = nom;
    }

    public Joueur(String nom, int score, int best_score) {
        this.nom = nom;
        this.score = score;
        this.best_score = best_score;
    }

    // le constructeur de la classe Joueur
    public Joueur(String nom, Case case_actuelle, int score) {
        this.nom = nom;
        this.case_actuelle = case_actuelle;
        this.score = score;
    }

    // le constructeur de la classe Joueur
    public Joueur(String nom, Case case_actuelle) {
        this.nom = nom;
        this.case_actuelle = case_actuelle;
    }

    // le constructeur de la classe Joueur
    public Joueur(String nom) {
        this.nom = nom;
        this.case_actuelle = null;
    }

    // retourne le nom du joueur
    public String toString() {
        return nom;
    }

    public int getId() {
        return id;
    }

    public String getNom() {
        return nom;
    }

    public int getScore() {
        return score;
    }

    // retourne la case actuelle du joueur
    public Case getCase() {
        return this.case_actuelle;
    }

    // change la case actuelle du joueur
    public void setCase(Case case_actuelle) {
        this.case_actuelle = case_actuelle;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setScore(int score) {
        this.score = score;
    }

    private int lance_de() {
        return ThreadLocalRandom.current().nextInt(1, 6 + 1);
    }

    public int lance_des() {
        return lance_de() + lance_de();
    }
}
