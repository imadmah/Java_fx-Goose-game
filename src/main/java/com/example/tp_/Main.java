package com.example.tp_;

public class Main {
    public static void main(String[] args) {
        // on cree le pleteau du jeu
        Plateau_jeu plateau = new Plateau_jeu();
        plateau.init_plateau();
        // on definie le joueur de la partie
        Joueur joueur = new Joueur("RAMY", plateau.getCase(0));

        // on initialise une partie de jeu
        Partie partie = new Partie(plateau, joueur);

        // on joue la partie
        partie.jouer();
    }
}
