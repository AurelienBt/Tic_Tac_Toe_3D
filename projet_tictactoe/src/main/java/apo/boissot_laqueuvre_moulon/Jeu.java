package apo.boissot_laqueuvre_moulon;

import java.util.Random;

public class Jeu {
    private int modeJeu;
    private int protagonistes;
    private int quiCommence;
    private Grille grille;
    private int tailleGrille;

    private Joueur j1;
    private Joueur j2;

    public Jeu(int modeJeu, int protagonistes, int quiCommence, int tailleGrille) {
        this.modeJeu = modeJeu;
        this.protagonistes = protagonistes;
        this.quiCommence = quiCommence;
        this.tailleGrille = tailleGrille;
    }

    public void partie() {
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
                if (tourJ1) { // A REMPLACER
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

        jouerPartie(tourJ1);
    }

    private void jouerPartie(boolean tourJ1) {
        // Initialisation
        boolean partieEnCours = true;
        
        //Boucle d'application
        while (partieEnCours){
            jouerTour(tourJ1);

            //Vérification de la grille
            if(this.grille.grilleGagnante()){
                if(tourJ1){
                    System.out.println("Le gagnant est Joueur 1");
                }else{
                    System.out.println("Le gagnant est joueur 2");
                }
            }
            else if (this.grille.grilleEstPleine()){
                System.out.println("Égalité !");
            }
            tourJ1 = !tourJ1;
        }

        //Fin de partie 
        
    }


    private void jouerTour(boolean tourJ1) {
        boolean IA = false; // A REMPLACER
        String text = "Choisissez coordonée";
        String input = "";
        boolean coupValide = false;

        this.grille.afficher();
        if (tourJ1) {
            if (IA) {
                System.out.println("Ordinateur :");
                input = this.j1.choisirCoup("");
            } else {
                do {
                    System.out.println("Joueur 1 :");
                    input = this.j1.choisirCoup(text);
                    text = "Coup invalide, recommencez svp";
                    coupValide = grille.verifierCoup(input);
                } while (!coupValide);
            }
            this.grille.placer("X", input);
        } else {
            if (IA) {
                System.out.println("Ordinateur :");
                input = this.j2.choisirCoup("");
            } else {
                do {
                    System.out.println("Joueur 2 :");
                    input = this.j1.choisirCoup(text);
                    text = "Coup invalide, recommencez svp";
                    coupValide = grille.verifierCoup(input);
                } while (!coupValide);
            }
            //this.grille.verifieCoup(input);
            this.grille.placer("O", input);
        }
    }
}
