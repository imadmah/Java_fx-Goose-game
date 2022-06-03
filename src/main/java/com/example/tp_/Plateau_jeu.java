package com.example.tp_;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Random;
import java.util.Set;

class Plateau_jeu extends Plateau {

    static Case[] cases = new Case[100];
    static int[] indices_case_bonus = new int[5];
    static int[] indices_case_malus = new int[5];
    static int[] indices_case_saut = new int[5];
    static int[] indices_case_definition = new int[5];
    static int[] indices_case_image = new int[5];

    // Constructeur du plateau du jeu
    public Plateau_jeu() {
        super(100);
    }

    public void init_plateau() {

        Set<Integer> set = new HashSet<>();    // l'ensemble des cases
        Random rand = new Random();
        cases[0] = new Case_depart(0);    // la case de depart a pour index 0

        for (int i = 1; i < 99; i++) {
            cases[i] = new Case_parcours(i + 1);

        }
        // On genere 25 cases aleatoirement sans la permiere et
        // la derniere case, on change la nature de es cases de facon a avoir
        // 5 cases : bonus, malus, saut, definition et image
        if (LoginPage.continue_partie) {
            int j;// on remplir les cases d'apres le fichier
            for (int i = 0; i < 5; i++) {

                j = indices_case_bonus[i];
                cases[j] = new Case_bonus(j + 1);

            }
            // 5 cases de type malus
            for (int i = 0; i < 5; i++) {

                j = indices_case_malus[i];

                cases[j] = new Case_malus(j + 1);

            }
            // 5 cases de type saut
            for (int i = 0; i < 5; i++) {

                j = indices_case_saut[i];
                cases[j] = new Case_saut(j + 1, 1 + rand.nextInt(97));


            }
            // 5 cases de type definition
            for (int i = 0; i < 5; i++) {

                j = indices_case_definition[i];
                cases[j] = new Case_definition(j + 1);

            }
            // 5 cases de type image
            for (int i = 0; i < 5; i++) {

                j = indices_case_image[i];
                cases[j] = new Case_image(j + 1);

            }


        } else {
            while (set.size() < 25) {
                set.add(1 + rand.nextInt(97));

            }
            Iterator<Integer> it = set.iterator();
            // 5 cases de type bonus
            for (int i = 0; i < 5; i++) {
                int j = it.next();
                indices_case_bonus[i] = j;

                cases[j] = new Case_bonus(j + 1);


            }
            // 5 cases de type malus
            for (int i = 0; i < 5; i++) {
                int j = it.next();
                indices_case_malus[i] = j;

                cases[j] = new Case_malus(j + 1);

            }
            // 5 cases de type saut
            for (int i = 0; i < 5; i++) {
                int j = it.next();
                indices_case_saut[i] = j;
                cases[j] = new Case_saut(j + 1, 1 + rand.nextInt(97));


            }
            // 5 cases de type definition
            for (int i = 0; i < 5; i++) {
                int j = it.next();
                indices_case_definition[i] = j;
                cases[j] = new Case_definition(j + 1);

            }
            // 5 cases de type image
            for (int i = 0; i < 5; i++) {
                int j = it.next();
                indices_case_image[i] = j;
                cases[j] = new Case_image(j + 1);

            }


        }
        cases[99] = new Case_fin(99);      // la case de fin a pour index 99
        this.setCases(cases);

    }
}
