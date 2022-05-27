package com.example.tp_;

public abstract class Case {

    // retourne l'index de cette case
    public abstract int getIndex();

    // retourne l'index de la case arrivee
    public abstract int mouvement(int inter_index);// int mouvement(int inter_index)

    // se que se passe quand un joueur est dans cette case
    public abstract int score(int score);

    public abstract Joueur getJoueur();


    public abstract void setJoueur(Joueur joueur);

    public abstract String getCouleur();

}
