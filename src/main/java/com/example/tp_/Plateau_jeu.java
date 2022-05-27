package com.example.tp_;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Random;
import java.util.Set;

class Plateau_jeu extends Plateau {

    static Case[] cases = new Case[100];
    // Constructeur du plateau du jeu
    public Plateau_jeu() {
        super(100);
    }

    public  void init_plateau() {

        Set<Integer> set = new HashSet<>();    // l'ensemble des cases
        Random rand = new Random();
        cases[0] =  new Case_depart(0);    // la case de depart a pour index 0

        for (int i = 1; i < 99; i++) {
            cases[i] = new Case_parcours(i+1);

        }
        // On genere 25 cases aleatoirement sans la permiere et
        // la derniere case, on change la nature de es cases de facon a avoir
        // 5 cases : bonus, malus, saut, definition et image
        while (set.size() < 25) {
            set.add(1 + rand.nextInt(97));

        }
        Iterator<Integer> it = set.iterator();
        // 5 cases de type bonus
        for (int i = 0; i < 5; i++) {
            int j = it.next();

            cases[j] = new Case_bonus(j+1);


        }
        // 5 cases de type malus
        for (int i = 0; i < 5; i++) {
            int j = it.next();

            cases[j] = new Case_malus(j+1);

        }
        // 5 cases de type saut
        for (int i = 0; i < 5; i++) {
            int j = it.next();
            cases[j] = new Case_saut(j+1, 1 + rand.nextInt(97));



        }
        // 5 cases de type definition
        for (int i = 0; i < 5; i++) {
            int j = it.next();

            cases[j] = new Case_definition(j+1);

        }
        // 5 cases de type image
        for (int i = 0; i < 5; i++) {
            int j = it.next();

            cases[j] = new Case_image(j+1);

        }

        cases[99] = new Case_fin(99);      // la case de fin a pour index 99
        this.setCases(cases);
    }
}
