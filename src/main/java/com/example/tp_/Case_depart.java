package com.example.tp_;

public class Case_depart extends Case {
    private   String couleur = "JAUNE";
    protected int index; // l'index de la case de depart
    protected Joueur joueur; // le joueur a la case de depart //to know wether this cell is occuped or not

    //// le constructeur de la case de depart
    public Case_depart(int index_init, Joueur joueur) {
        index = index_init;
        this.joueur = joueur;
    }

    // le constructeur de la case de depart // utilise dans la creation du plateau
    public Case_depart(int index_init) {
        index = index_init;
    }

    public int getIndex() {
        return this.index;
    }

    // retourne l'index de cette case
    public int mouvement(int inter_index) {
        System.out.print("La case " + this.getIndex() + " est la case de depart!");
        int new_index = inter_index + 0;
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

    // retourne vrai si le joueur est dans cette case //
    public boolean occupe() {
        return this.joueur != null;
    }

    public String getCouleur() {
        return this.couleur;
    }
}
