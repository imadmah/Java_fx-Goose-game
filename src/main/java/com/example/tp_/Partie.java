package com.example.tp_;

public class Partie {
    private Joueur joueur;
    private Plateau_jeu plateau;
    private boolean partie_finie;
    private int score = 0;

    public Partie(Plateau_jeu plateau, Joueur joueur) {
        this.plateau = plateau;
        this.joueur = joueur;
        this.partie_finie = false;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public Joueur getJoueur() {
        return joueur;
    }

    public void setJoueur(Joueur joueur) {
        this.joueur = joueur;
    }

    public boolean isPartie_finie() {
        return partie_finie;
    }

    public void setPartie_finie(boolean partie_finie) {
        this.partie_finie = partie_finie;
    }

    public Plateau_jeu getPlateau() {
        return plateau;
    }

    public void setPlateau(Plateau_jeu plateau) {
        this.plateau = plateau;
    }

    public void jouer() {
        int taille_plateau = this.getPlateau().getNombre_cases();
        while (!partie_finie) {
            int resultat = this.joueur.lance_des();
            // calculer le nouveau index du joueur
            int index_actuel = joueur.getCase().getIndex();
            int inter_index = index_actuel + resultat;
            // int index_intermediare;
            int index_destination;
            System.out.println(joueur.toString() + " est dans la case : " + index_actuel + ".");
            System.out.println("Le resultat de la lance des dés est : " + resultat);
            if (inter_index < taille_plateau - 1) { // 99
                // System.out.println("Le joueur doit se deplacer premierement en cliquant sur la case :" + inter_index);
                // System.out.println(" la case :" + inter_index + " est une case intermediaire de couleur "
                //+ this.getPlateau().getCase(inter_index).getCouleur());
                index_destination = this.getPlateau().getCase(inter_index).mouvement(inter_index);
                this.score = this.getPlateau().getCase(inter_index).score(this.score);
                System.out.println("le score de joueur apres le parcours d'une case "
                        + this.getPlateau().getCase(inter_index).getCouleur() + "est : " + this.score);
                if (index_destination > taille_plateau - 1) { // case bonus a 99
                    index_destination = taille_plateau - 1;
                } else if (index_destination < 0) {
                    index_destination = 0;
                }
            } else {
                int index_fin = taille_plateau - 1;
                int supplementaire = (resultat - ((taille_plateau - 1) - index_actuel));
                index_destination = index_fin - supplementaire;
                System.out.println("le joueur a depasser la case Fin avec " + supplementaire + " Cases");
                System.out.println("donc il va se trouver maintenant a la case : " + index_destination);
            }

            Case case_destination = this.getPlateau().getCase(index_destination);
            Case case_actuelle = this.getPlateau().getCase(index_actuel);
            case_actuelle.setJoueur(null); // to change the occupied state.
            case_destination.setJoueur(joueur);
            joueur.setCase(case_destination);

            if (index_destination == taille_plateau - 1) {
                System.out.println(
                        joueur.toString() + " est dans la case " + index_destination + " qui est la case fin!");
                System.out.println("La partie est finie!");
                System.out.println("Le Meilleur score du joueur " + joueur.toString() + " est : " + joueur.getScore());
                partie_finie = true;
            }
            System.out.println();
        }

        System.out.println("\n Votre score final de cette partie \n :" + this.score);
        if (this.score > joueur.getScore()) {
            System.out.println("\n Votre meilleur score avant cette partie : " + joueur.getScore());
            joueur.setScore(this.score);
            System.out.println(" \n BRAVO! vous avez battu votre meilleur score !");
            System.out.println("\n Votre nouveau meilleur score est : " + joueur.getScore());
        } else {
            System.out.println("Dommage! Vous n'avez pas battu votre propre meilleur score!");
        }
    }

}