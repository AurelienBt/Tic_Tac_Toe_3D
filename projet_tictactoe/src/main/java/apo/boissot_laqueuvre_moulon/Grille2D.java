package apo.boissot_laqueuvre_moulon;
<<<<<<< HEAD
import java.util.*;

=======
>>>>>>> 73375b3a87fe694b1d63e5e68a5e47895387f801
public class Grille2D extends Grille {
    private Case grille[][];

    public Grille2D(int taille) {
        super(taille);

        viderGrille();
    }

    public void viderGrille() {
        this.grille = new Case[taille][];

        Case[] ligne;
        Case c;

        for (int i = 0; i < this.taille; i++) {
            ligne = new Case[taille];
            for (int j = 0; j < this.taille; j++) {
                c = new Case(i * this.taille + j + 1);
                ligne[j] = c;
            }
            this.grille[i] = ligne;
        }
    }

    private int tailleEntier(int n) {
        String nn = n + "";
        return nn.length();
    }

    public void afficher() {
        for (int i = 0; i < this.taille; i++) {
            System.out.print("|");
            for (int j = 0; j < this.taille; j++) {
                System.out.print(this.grille[i][j].getValeur(tailleEntier(this.taille * this.taille)));
            }
            System.out.println("|");
        }
    }

    public boolean grilleGagnante() {
        return false;
    }

    private int getXCase(String numeroCase) {
        int numero = Integer.valueOf(numeroCase) - 1;
        int x = numero % this.taille;
        return x;
    }

    private int getYCase(String numeroCase) {
        int numero = Integer.valueOf(numeroCase) - 1;
        int y = numero / this.taille;
        return y;
    }

    public boolean verifieCoup(String input) {
        int numeroCase = Integer.valueOf(input) - 1;
        int x = getXCase(input);
        int y = getYCase(input);

        if (x < 0 || x >= this.taille || y < 0 || y >= this.taille) {
            System.out.println("EREUR ! Le numéro de case " + numeroCase + " est invalide");
            return false;
        } else if (this.grille[y][x].estVide()) {
            return true;
        } else {
            System.out.println("EREUR ! La case " + numeroCase + " est déjà pleine");
            return false;
        }
    }

    public void placer(String joueur, String numeroCase) {
        if (verifieCoup(numeroCase)) {
            this.grille[getYCase(numeroCase)][getXCase(numeroCase)].setValeur(joueur);
        }
    }

    public void testRegretion() {
        System.out.println("Test Regression pour une grille 3*3");

        // afficher
        System.out.println("Une grille vide :");
        afficher();
        System.out.println("");
        System.out.println("");

        // verifieCoup
        System.out.println("Vrai : " + verifieCoup("1"));
        System.out.println("Vrai : " + verifieCoup("9"));
        System.out.println("Vrai : " + verifieCoup("8"));

        placer("X", "1");
        placer("O", "5");
        placer("X", "9");

        System.out.println("Faux : " + verifieCoup("1"));
        System.out.println("Faux : " + verifieCoup("5"));
        System.out.println("Faux : " + verifieCoup("9"));
        System.out.println("Faux : " + verifieCoup("800"));
        System.out.println("Faux : " + verifieCoup("0"));
        System.out.println("Faux : " + verifieCoup("-1"));
        System.out.println("");
        System.out.println("");

        // afficher et placer
        placer("X", "1");
        placer("O", "5");
        placer("X", "9");
        this.grille[0][2].setSelectionnee(true);
        System.out.println("Une grille comme suis :");
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
    
    public boolean grilleGagnante(String coup){
        boolean gagnante = false;
        int[] coord = convertCoup(coup);
        if (estDansDiagonale(coord)){
            gagnante = verifieDiagonale(coord);
        }
        /*if (coordEstCentre(coup)){
            gagnant = verifieAdjacentCentre();
        }*/
        return true;
    }

    //Vérifie si une case appartient à une diagonale, 
    //Le cas échéant il vérifie la diagonale associée
    private boolean verifieDiagonale(int[] coord){
        boolean gagnante = false;
        boolean aligne = true;
        int i=0;

        //Diagonale numéro 1
        if(coord[0]==coord[1]) { 
            String val = this.grille[0][0].getValeur();
            //String valEnCours = val;
            //ajouter if taille =1;
            do{
                i++;
                aligne = val == this.grille[i][i].getValeur();
            }while(i<this.taille && aligne);
            gagnante = aligne;            
        }
        if(gagnante) return true;

        //Diagonale numéro 1
        if(coord[0] + coord[1] == this.taille - 1) { 
            String val = this.grille[0][this.taille-1].getValeur();
            //String valEnCours = val;
            //ajouter if taille =1;
            do{
                i++;
                aligne = val == this.grille[i][taille-1-i].getValeur();
            }while(i<this.taille && aligne);
            gagnante = aligne;            
        }
        if(gagnante) return true;
        return gagnante;
    }

    private int[] convertCoup(String coup){
        int[] tmp = new int[2];
        int c = Integer.valueOf(coup);
        int x = c % this.taille;
        int y = c / this.taille;
        tmp[0] = x;
        tmp[1] = y;
        return tmp;
    }

    

    private boolean estDansDiagonale(int[] coord){
        return(coord[0]==coord[1] || coord[0]+coord[1]==(this.taille - 1));
    }

    /*
    private boolean verifieAdjacentCentre(){
        int[][] listeCoordonne = new int[9][];
        for(int i = 0; i<9; i++){
            
        }

        verifieAlignement(null, null, null);
    }*/

    private boolean verifieAlignement(int[] coord1, int[] coord2, int[] coord3){
        
        return true;
    }
    /*
    private boolean coordEstCentre(String coup){
        int coord = Integer.parseInt(coup);
        if (coord == 5) return true;
        return false;
    }*/

    
}