package com.example.tp_;

public abstract class Plateau {
    // Le plateau contient deux des et 100 cases disposees en spirale
    protected int nombre_cases; // le nombre de cases du plateau
    protected Case[] cases; // le cases du plateau

    // Constructeur du plateau avec le nombre de cases donne
    public Plateau(int nombre_cases) {
        this.nombre_cases = nombre_cases;
    }

    // Construit le plateau selon le plateau voulou
    public abstract void init_plateau();

    // Retourne les cases du plateau
    public Case[] getCases() {
        return this.cases;
    }

    // Modifie les cases du plateau
    public void setCases(Case[] cases) {
        this.cases = cases;
    }

    // Retourne la case specifiee par l'index
    public Case getCase(int index) {
        return this.getCases()[index];
    }

    // Retourne le nombre de cases du plateau
    public int getNombre_cases() {
        return this.nombre_cases;
    }
}