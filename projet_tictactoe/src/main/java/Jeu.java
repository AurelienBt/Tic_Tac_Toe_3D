import java.util.Random;

public class Jeu {
    private int modeJeu;
    private int protagonistes;
    private int quiCommence;
    private Grille grille;
    private int tailleGrille;

    public Jeu(int modeJeu, int protagonistes, int quiCommence, int tailleGrille) {
        this.modeJeu = modeJeu;
        this.protagonistes = protagonistes;
        this.quiCommence = quiCommence;
        this.tailleGrille = tailleGrille;
    }

<<<<<<< HEAD
    public void partie(){
        //Initialisation des paramètres
        switch(modeJeu){
            case 0 :
                this.grille = new Grille2D(this.tailleGrille);
                break;
            /*case 1 : 
                this.grille = new Grille3D(this.tailleGrille);
                break;*/
            default : 
                this.grille = new Grille2D(this.tailleGrille);
                break;
        }
        boolean tourJ1;
        switch(quiCommence){
            case 0 :
                Random r = new Random();
                tourJ1 = r.nextBoolean();
                break;
            case 1 : 
                tourJ1 = true;
                break;
            case 2 : 
                tourJ1 = false;
                break;
            default : 
                tourJ1 = true;
                break;
        }
=======
    public void partie() {
        switch (modeJeu) {
            case 0:
                this.grille = new Grille2D(this.tailleGrille);
                break;
            case 1:
                this.grille = new Grille3D(this.tailleGrille);
                break;
            default:
                this.grille = new Grille2D(this.tailleGrille);
                break;
        }
>>>>>>> 8da70ceb7689d7bce123999c45b4c0d37110e41e

        
        jouerPartie(tourJ1);
    }
<<<<<<< HEAD
    
=======

    private void demarrerPartie() {
>>>>>>> 8da70ceb7689d7bce123999c45b4c0d37110e41e

    private void jouerPartie(boolean tourJ1){
        //Initialisation
        String j1;
        String j2;
        boolean partieEnCours = true;
        if (tourJ1){
            j1 = "X";
            j2 = "O";
        } else {
            j1 = "O";
            j2 = "X";
        }
        int IA = this.protagonistes;

        //Boucle d'application
        while (partieEnCours){
            if(tourJ1){
                String text = "Choisissez coordonée";
                String input ="";
                boolean coupValide = false;
                do{
                    input = j1.choisirCoup(text);
                    text = "Coup invalide, recommencez svp";
                    coupValide = grille.verifieCoup(input);
                }while(!coupValide);
                grille.placer('X',input);
            }
        }
    }
}
