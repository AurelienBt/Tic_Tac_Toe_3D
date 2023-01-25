package apo.boissot_laqueuvre_moulon;
import java.util.*;

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

    public void afficher() {
        for (int i = 0; i < this.taille; i++) {
            System.out.print("|");
            for (int j = 0; j < this.taille; j++) {
                System.out.print(this.grille[i][j].getValeur());
            }
            System.out.println("|");
        }
    }

    public void placer(String joueur, String coordone) {
        int c = Integer.valueOf(coordone);

        int x = c % this.taille;
        int y = c / this.taille;

        if (x < 0 || x >= this.taille || y < 0 || y >= this.taille) {
            System.out.println("EREUR ! Le deuxième caractère des coordoné de la case est invalide");
        } else if (this.grille[y][x].estVide()) {
            this.grille[y][x].setValeur(joueur);
        }
    }

    public void testRegretion() {

        // Affichage
        System.out.println("Une grille vide :");
        afficher();
        System.out.println("");
        System.out.println("");

        // Affichage et placer
        placer("X", "1");
        placer("O", "5");
        placer("X", "9");
        System.out.println("Une grille comme suis :");
        System.out.println("| X  2  3 |");
        System.out.println("| 4  O  6 |");
        System.out.println("| 7  8  X |");
        System.out.println("");
        afficher();
        System.out.println("");
        System.out.println("");

        // ViderGrille
        viderGrille();
        System.out.println("Une grille vide :");
        afficher();
        System.out.println("");
        System.out.println("");

    }

    public boolean verifieCoup(String input){
        return true;
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
