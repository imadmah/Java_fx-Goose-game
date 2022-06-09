package com.example.tp_;

import java.util.concurrent.ThreadLocalRandom;

public class Case_saut extends Case {
    private final String couleur = "ORANGE";
    private int index; // l'index de la case de saut
    private int contenu; // le contenu de la case saut
    private Joueur joueur; // le joueur a la case de saut

    // le constructeur de la case de saut
    public Case_saut(int index, int contenu, Joueur joueur) {
        this.index = index;
        this.contenu = contenu;
        this.joueur = joueur;
    }

    // le constructeur de la case de saut
    public Case_saut(int index, int contenu) {
        this.index = index;
        this.contenu = contenu;
    }

    public int getIndex() {
        return this.index;
    }

    // retourne l'index de la case destination
    public int mouvement(int inter_index) {
        int new_index=0;
        System.out.print("La case " + this.getIndex() + " est une case saut!");
        new_index =  ThreadLocalRandom.current().nextInt( 99);
        System.out.println(" Le joueur va atteindre la case :  " + new_index + ".");
        return new_index;
    }

    public int score(int score) {
        return score;
    }

    public Joueur getJoueur() {
        return this.joueur;
    }

    public void setJoueur(Joueur joueur) {
        this.joueur = joueur;
    }

    // retourne vrai si le joueur est dans cette case //ca sert fl'affichage blk
    public boolean occupe() {
        return this.joueur != null;
    }

    public String getCouleur() {
        return this.couleur;
    }
}
