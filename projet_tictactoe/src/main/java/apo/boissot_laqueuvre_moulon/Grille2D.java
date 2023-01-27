package apo.boissot_laqueuvre_moulon;

import java.util.ArrayList;
import java.util.Scanner;

public class Grille2D extends Grille {
    private Case grille[][];

    public Grille2D(int taille) {
        super(taille);

        viderGrille();
    }

    public void viderGrille() {
        this.grille = new Case[taille][taille];

        Case c;

        for (int i = 0; i < this.taille; i++) {
            for (int j = 0; j < this.taille; j++) {
                c = new Case(i * this.taille + j + 1);
                this.grille[i][j] = c;
            }
        }
    }

    public void afficher() {
        for (int i = 0; i < this.taille; i++) {
            System.out.print("|");
            for (int j = 0; j < this.taille; j++) {
                System.out.print(this.grille[i][j].afficher(tailleEntier(this.taille * this.taille)));
            }
            System.out.println("|");
        }
    }

    // A faire
    public boolean grilleGagnante() {
        return false;
    }


    public boolean verifierCoup(String input) {
        if (!verifierInput(input)) {
            return false;
        } else {
            int numeroCase = Integer.valueOf(input) - 1;
            int x = getXCase(input);
            int y = getYCase(input);

            // On regarde si les coordonnées données sont valides (dans le tableau)
            if (x < 0 || x >= this.taille || y < 0 || y >= this.taille) {
                System.out.println("ERREUR ! Le numéro de case " + numeroCase + " est invalide");
                return false;
            }
            // On regarde si la case choisi est libre
            else if (this.grille[y][x].estVide()) {
                return true;
            } else {
                System.out.println("ERREUR ! La case " + numeroCase + " est déjà pleine");
                return false;
            }
        }
    }

    public String validerCoup(String input, Scanner scanner) {
        System.out.println();
        System.out.println("Appuyer sur Entrée pour valider votre coup, ou entrez un autre coup");
        int x = getXCase(input);
        int y = getYCase(input);
        grille[y][x].setSelectionnee(true);
        afficher();
        String choix = scanner.nextLine();
        grille[y][x].setSelectionnee(false);
        return choix;
    }

    public void placer(String joueur, String numeroCase) {
        if (verifierCoup(numeroCase)) {
            this.grille[getYCase(numeroCase)][getXCase(numeroCase)].setValeur(joueur);
        }
    }

    public ArrayList<int[]> listerCoupPossible() {
        ArrayList<int[]> coupPossible = new ArrayList<int[]>();

        int c[] = { -1, -1 };

        for (int i = 0; i < this.taille; i++) {
            for (int j = 0; j < this.taille; j++) {
                if (this.grille[i][j].estVide()) {
                    c[0] = i;
                    c[1] = j;
                    coupPossible.add(c);
                }
            }
        }

        return coupPossible;
    }

    public boolean verifierInput(String input) {
        // On vérifie que l'input ne soit pas vide
        if (input.length() <= 0) {
            System.out.println("ERREUR ! L'input est vide");
            return false;
        }

        // On vérifie que l'input ne contient que des chiffres
        for (int i = 0; i < input.length(); i++) {
            if (!Character.isDigit(input.charAt(i))) {
                System.out.println("ERREUR ! L'input " + input + " n'est pas un chiffre");
                return false;
            }
        }
        // On vérifie que le chiffre correspond à une case
        if (Integer.valueOf(input) > this.taille * this.taille || Integer.valueOf(input) <= 0) {
            System.out.println("ERREUR ! L'input " + input + " n'est pas une case du tableau");
            return false;
        } else {
            return true;
        }
    }

    public void testRegretion() {
        System.out.println("Test Regression pour une grille2D 3*3");

        // afficher
        System.out.println("Une grille vide :");
        afficher();
        System.out.println("");
        System.out.println("");

        // verifierInput
        System.out.println("Faux : " + verifierInput("a"));
        System.out.println("Faux : " + verifierInput("2a"));
        System.out.println("Faux : " + verifierInput(""));
        System.out.println("Faux : " + verifierInput("111"));
        System.out.println("Faux : " + verifierInput("0"));
        System.out.println("Faux : " + verifierInput("-5"));
        System.out.println("Vrai : " + verifierInput("1"));
        System.out.println("Vrai : " + verifierInput("6"));
        System.out.println("");
        System.out.println("");

        // verifieCoup
        System.out.println("Vrai : " + verifierCoup("1"));
        System.out.println("Vrai : " + verifierCoup("9"));
        System.out.println("Vrai : " + verifierCoup("8"));

        placer("X", "1");
        placer("O", "5");
        placer("X", "9");

        System.out.println("Faux : " + verifierCoup("1"));
        System.out.println("Faux : " + verifierCoup("5"));
        System.out.println("Faux : " + verifierCoup("9"));
        System.out.println("Faux : " + verifierCoup("800"));
        System.out.println("Faux : " + verifierCoup("0"));
        System.out.println("Faux : " + verifierCoup("-1"));
        System.out.println("");
        System.out.println("");

        // afficher et placer
        placer("X", "1");
        placer("O", "5");
        placer("X", "9");
        this.grille[0][2].setSelectionnee(true);
        System.out.println("Une grille comme suit :");
        System.out.println("| X  2 >3<|");
        System.out.println("| 4  O  6 |");
        System.out.println("| 7  8  X |");
        System.out.println("");
        afficher();
        System.out.println("");
        System.out.println("");

        // viderGrille
        viderGrille();
        System.out.println("Une grille vide :");
        afficher();
        System.out.println("");
        System.out.println("");

        viderGrille();
        //Verifier Diagonale
        placer("X", "1");
        placer("X", "5");
        placer("X", "9");
        afficher();
        System.out.println("Résultat attendu : ");
        System.out.println("Diagonale : True");
        System.out.println("Ligne : false");
        System.out.println("Colonne : false");
        System.out.println("---------------------");
        System.out.println("Diagonale : "+this.verifieDiagonale("9"));
        System.out.println("Ligne : "+this.verifieY("9"));
        System.out.println("Colonne : "+this.verifieX("9"));
        System.out.println("Grille gagnante : "+this.grilleGagnante("9"));
        System.out.println("=======================");
        System.out.println("=======================");

        viderGrille();

        //Verifier Diagonale inverse
        placer("X", "3");
        placer("X", "5");
        placer("X", "7");
        afficher();
        System.out.println("Résultat attendu : ");
        System.out.println("Diagonale : True");
        System.out.println("Ligne : false");
        System.out.println("Colonne : false");
        System.out.println("---------------------");
        System.out.println("Diagonale : "+this.verifieDiagonale("5"));
        System.out.println("Ligne : "+this.verifieY("5"));
        System.out.println("Colonne : "+this.verifieX("5"));
        System.out.println("=======================");
        System.out.println("=======================");

        viderGrille();
        //Verifier ligne
        placer("X", "1");
        placer("X", "2");
        placer("X", "3");
        afficher();
        System.out.println("Résultat attendu : ");
        System.out.println("Diagonale : false");
        System.out.println("Ligne : true");
        System.out.println("Colonne : false");
        System.out.println("---------------------");
        System.out.println("Diagonale : "+this.verifieDiagonale("1"));
        System.out.println("Ligne : "+this.verifieY("1"));
        System.out.println("Colonne : "+this.verifieX("1"));
        System.out.println("=======================");
        System.out.println("=======================");

        viderGrille();
        //Verifier colonne
        placer("X", "1");
        placer("X", "4");
        placer("X", "7");
        afficher();
        System.out.println("Résultat attendu : ");
        System.out.println("Diagonale : false");
        System.out.println("Ligne : true");
        System.out.println("Colonne : false");
        System.out.println("---------------------");
        System.out.println("Diagonale : "+this.verifieDiagonale("1"));
        System.out.println("Ligne : "+this.verifieY("1"));
        System.out.println("Colonne : "+this.verifieX("1"));
        System.out.println("=======================");
        System.out.println("=======================");



        // tailleEntier
        System.out.println("1 : " + tailleEntier(5));
        System.out.println("2 : " + tailleEntier(50));
        System.out.println("3 : " + tailleEntier(508));
        System.out.println("3 : " + tailleEntier(100));
        System.out.println("4 : " + tailleEntier(5555));
        System.out.println("");
        System.out.println("");

    }


    public boolean grilleEstPleine(){
        for (int i = 0; i<this.taille; i++){
            for (int j=0; j<this.taille; j++){
                if(grille[i][j].estVide()) return false;
            }
        }
        return true;
    }
    
    //Fonction pour la vérification d'une grille 
    public boolean grilleGagnante(String coup){
        if(verifieDiagonale(coup)) return true;
        if(verifieX(coup))return true;
        if(verifieY(coup)) return true;

        return false;
    }

    //Vérifie si une case appartient à une diagonale, 
    //Le cas échéant il vérifie la diagonale associée
    private boolean verifieDiagonale(String coup){
        boolean gagnante = false;
        boolean aligne = true;
        int i=0;
        System.out.println(coup);
        int[] coord = convertCoup(coup);
        System.out.println(coord[0]+"     "+coord[1]);
        //Diagonale numéro 1
        if(coord[0]==coord[1]) { 
            String val = this.grille[0][0].getValeur();
            do{
                aligne = val == this.grille[i][i].getValeur();
                i++;
            }while(i<this.taille && aligne);
            gagnante = aligne;            
        }
        if(gagnante) return true;

        //Diagonale numéro 2
        i = 0;
        if(coord[0] + coord[1] == this.taille - 1) { 
            String val = this.grille[0][this.taille-1].getValeur();
            do{
                aligne = val == this.grille[i][taille-1-i].getValeur();
                i++;
            }while(i<this.taille && aligne);
            gagnante = aligne;            
        }
        if(gagnante) return true;
        return gagnante;
    }

    private boolean verifieY(String coup){
        boolean aligne = true;
        int i = 0;
        int coordFixe = getYCase(coup);
        String val = this.grille[coordFixe][0].getValeur();
        do{
            aligne = val.equals(this.grille[coordFixe][i].getValeur());
            i++;
        }while(i<this.taille && aligne);
        return aligne; 
    }

    private boolean verifieX(String coup){
        boolean aligne = true;
        int i = 0;
        int coordFixe = getXCase(coup);
        String val = this.grille[0][coordFixe].getValeur();
        
        do{
            aligne = val.equals(this.grille[i][coordFixe].getValeur());
            i++;
        }while(i<this.taille && aligne);
        return aligne;
    }

    private int[] convertCoup(String coup){
        int[] tmp = new int[2];
        int c = Integer.valueOf(coup)-1;
        int x = c % this.taille;
        int y = c / this.taille;
        tmp[0] = x;
        tmp[1] = y;
        return tmp;
    }

    

    


    private int getYCase(String numeroCase) {
        int numero = Integer.valueOf(numeroCase) - 1;
        int y = numero / this.taille;
        return y;
    }

    private int getXCase(String numeroCase) {
        int numero = Integer.valueOf(numeroCase) - 1;
        int x = numero % this.taille;
        return x;
    }

    private int getYCase(int numeroCase) {
        int y = (numeroCase - 1) / this.taille;
        return y;
    }

    private int getXCase(int numeroCase) {
        int x = (numeroCase - 1) % this.taille;
        return x;
    }

}