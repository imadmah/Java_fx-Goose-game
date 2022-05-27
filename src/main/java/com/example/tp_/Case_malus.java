package com.example.tp_;

public class Case_malus extends Case {
    protected final String couleur = "ROUGE";
    protected int index; // l'index de la case de malus
    protected Joueur joueur; // le joueur a la case de malus

    // le constructeur de la case de malus
    public Case_malus(int index, Joueur joueur) {
        this.index = index;
        this.joueur = joueur;
    }

    // le constructeur de la case de malus
    public Case_malus(int index) {
        this.index = index;
    }

    public int getIndex() {
        return this.index;
    }

    // retourne l'index de la case destination
    public int mouvement(int inter_index) {
        System.out.print("La case " + this.getIndex() + " est une case malus!");
        int new_index = inter_index - 2; // recule de deux cases.
        System.out.println(" Le joueur va atteindre la case :  " + new_index + ".");
        return new_index;
    }

    public int score(int score) {
        return score - 10;
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