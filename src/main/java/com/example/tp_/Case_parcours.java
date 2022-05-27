package com.example.tp_;

public class Case_parcours extends Case {
    protected  String couleur = "BLANCHE";
    protected int index; // l'index de la case de parcours
    protected Joueur joueur; // le joueur a la case de parcours

    // le constructeur de la case de parcours
    public Case_parcours(int index, Joueur joueur) {
        this.index = index;
        this.joueur = joueur;
    }

    // le constructeur de la case de parcours
    public Case_parcours(int index) {
        this.index = index;
    }

    public int getIndex() {
        return this.index;
    }

    // retourne l'index de la case destination
    public int mouvement(int inter_index) {
        System.out.print("La case " + this.getIndex() + " est une case parcours!");
        int new_index = inter_index; // elle va rien faire.
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