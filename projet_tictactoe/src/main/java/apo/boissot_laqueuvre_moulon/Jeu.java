package apo.boissot_laqueuvre_moulon;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

/***
 * La clas Jeu centralise toute la logique d'une partie de morpion.
 */
public class Jeu {
    private int modeJeu;
    private int protagonistes;
    private int quiCommence;
    private Grille grille;
    private int tailleGrille;
    private String sauvegardePartie;

    private Joueur j1;
    private Joueur j2;

    public Jeu(int modeJeu, int protagonistes, int quiCommence, int tailleGrille) {
        this.modeJeu = modeJeu;
        this.protagonistes = protagonistes;
        this.quiCommence = quiCommence;
        this.tailleGrille = tailleGrille;

        // Sauvegarde des paramètres de la partie dans le cas où le joueur voudrait sauvegarder
        this.sauvegardePartie = Integer.toString(this.modeJeu) + " "
                              + Integer.toString(this.protagonistes) + " "
                              + Integer.toString(this.quiCommence) + " "
                              + Integer.toString(this.tailleGrille) + "\n";
    }

    public void partie(Scanner scanner, ArrayList<String> sauvegarde) {
        String[] coup = {"X", "1"};
        switch (this.modeJeu) {
            case 0:
                this.grille = new Grille2D(this.tailleGrille);
                break;
            case 1 :
                this.grille = new Grille3D(this.tailleGrille);
                break;
            default:
                this.grille = new Grille2D(this.tailleGrille);
                break;
        }
        boolean tourJ1 = false;
        switch(this.protagonistes) {
            case 0:
                this.j1 = new JoueurHumain();
                this.j2 = new JoueurHumain();
                break;
            case 1:
                if (tourJ1) { // A REMPLACER TODO
                    this.j1 = new JoueurHumain();
                    this.j2 = new JoueurHumain();
                } else {
                    this.j1 = new JoueurHumain();
                    this.j2 = new JoueurHumain();
                }
                break;
            default:
                this.j1 = new JoueurHumain();
                this.j2 = new JoueurHumain();
                break;
        }
        boolean erreurCoup = false;
        for(int i = 1; i<sauvegarde.size(); i++) {
            coup = sauvegarde.get(i).split(" ");
            if(coup.length == 2 && this.grille.verifierCoup(coup[1])) {
                this.grille.placer(coup[0], coup[1]);
                this.sauvegardePartie += sauvegarde.get(i) + "\n";
            }
            else {
                erreurCoup = true;
                break;
            }
        }
        if(erreurCoup) {
            System.out.println("Erreur lors du chargement de la sauvegarde.");
        }
        else {
            if(coup[0].compareTo("X") == 0) jouerPartie(false, scanner);
            else jouerPartie(true, scanner);
        }
    }

    /***
     * 
     * @param scanner
     */
    public void partie(Scanner scanner) {
        // Initialisation des paramètres
        switch (modeJeu) {
            case 0:
                this.grille = new Grille2D(this.tailleGrille);
                break;
            /*
             * case 1 :
             * this.grille = new Grille3D(this.tailleGrille);
             * break;
             */
            default:
                this.grille = new Grille2D(this.tailleGrille);
                break;
        }
        boolean tourJ1;
        switch (quiCommence) {
            case 0:
                Random r = new Random();
                tourJ1 = r.nextBoolean();
                break;
            case 1:
                tourJ1 = true;
                break;
            case 2:
                tourJ1 = false;
                break;
            default:
                tourJ1 = true;
                break;
        }
        switch (protagonistes) {
            case 0:
                this.j1 = new JoueurHumain();
                this.j2 = new JoueurHumain();
                break;
            case 1:
                if (tourJ1) { // A REMPLACER TODO
                    this.j1 = new JoueurHumain();
                    this.j2 = new JoueurHumain();
                } else {
                    this.j1 = new JoueurHumain();
                    this.j2 = new JoueurHumain();
                }
                break;
            default:
                this.j1 = new JoueurHumain();
                this.j2 = new JoueurHumain();
                break;

        }

        jouerPartie(tourJ1, scanner);
    }

    private void jouerPartie(boolean tourJ1, Scanner scanner) {
        // Initialisation
        boolean partieEnCours = true;

        // Boucle d'application
        while (partieEnCours && !this.grille.grilleEstPleine()) {
            String coup = jouerTour(tourJ1, scanner);

            //Vérification de la grille
            partieEnCours = !this.grille.grilleGagnante(coup);
            if(!partieEnCours){
                if(tourJ1){
                    System.out.println("Le gagnant est Joueur 1");
                } else {
                    System.out.println("Le gagnant est joueur 2");
                }
            } else if (this.grille.grilleEstPleine()) {
                System.out.println("Égalité !");
            }
            tourJ1 = !tourJ1;
        }
        // Fin de partie
    }

    
    private String jouerTour(boolean tourJ1, Scanner scanner) {
        return jouerTour(tourJ1, scanner, "");
    }
    
    private String jouerTour(boolean tourJ1, Scanner scanner, String prochainCoup) {
        boolean IA = false; // A REMPLACER TODO
        String text = "Choisissez coordonée";
        String input = "";
        boolean coupValide = false;

        this.grille.afficher();
        if (tourJ1) {
            if (IA) {
                System.out.println("Ordinateur :");
                input = this.j1.choisirCoup("", scanner);
            } else {
                do {
                    System.out.println("Joueur 1 :");
                    if(prochainCoup.compareTo("")==0) input = this.j1.choisirCoup(text, scanner);
                    else input = prochainCoup;
                    if(input.compareTo("save")==0) {
                        sauvegarderPartie();
                        return jouerTour(tourJ1, scanner);
                    }
                    prochainCoup = "";
                    text = "Coup invalide, recommencez svp";
                    coupValide = grille.verifierCoup(input);
                } while (!coupValide);
                prochainCoup = grille.validerCoup(input, scanner);
                if (prochainCoup.compareTo("")!=0) return jouerTour(tourJ1, scanner, prochainCoup);
            }
            this.grille.placer("X", input);
            this.sauvegardePartie += "X "+ input + "\n";
        } else {
            if (IA) {
                System.out.println("Ordinateur :");
                input = this.j2.choisirCoup("", scanner);
            } else {
                do {
                    System.out.println("Joueur 2 :");
                    if(prochainCoup.compareTo("")==0) input = this.j2.choisirCoup(text, scanner);
                    else input = prochainCoup;
                    if(input.compareTo("save")==0) {
                        sauvegarderPartie();
                        return jouerTour(tourJ1, scanner);
                    }
                    prochainCoup = "";
                    text = "Coup invalide, recommencez svp";
                    coupValide = grille.verifierCoup(input);
                } while (!coupValide);
                prochainCoup = grille.validerCoup(input, scanner);
                if (prochainCoup.compareTo("")!=0) return jouerTour(tourJ1, scanner, prochainCoup);
            }
            this.grille.placer("O", input);
            this.sauvegardePartie += "O "+ input + "\n";

        }
        return input;
    }

    private void sauvegarderPartie() { 
        try {
            FileWriter fw = new FileWriter("save.txt");
            this.sauvegardePartie = this.sauvegardePartie.substring(0, this.sauvegardePartie.length()-1); // enlève le dernier saut de ligne
            fw.write(this.sauvegardePartie);
            fw.close();
            System.out.println("La partie a été sauvegardée avec succès");
          } catch (IOException e) {
            System.out.println("Erreur lors de la sauvegarde de la partie :");
            e.printStackTrace();
          }
    }
}
