package apo.boissot_laqueuvre_moulon;
public class Grille3D extends Grille {
    private Case grille[][][];

    public Grille3D(int taille) {
        super(taille);

        viderGrille();
    }

    public void viderGrille() {
        this.grille = new Case[taille][taille][taille];

        Case c;

        for (int i = 0; i < this.taille; i++) {
            for (int j = 0; j < this.taille; j++) {
                for(int k = 0; k< this.taille; k++) {
                    c = new Case(i * this.taille + j + 1);
                    this.grille[i][j][k] = c;
                }
            }
        }
    }

    public void afficher() {
        // Affichage en-tête
        int nbChiffresMax = tailleEntier(this.taille*this.taille);
        String enTete = "";
        if(this.taille%2==0) {
            for(int l = 0; l<nbChiffresMax; l++) {
                enTete += " ";
            }
        }
        for(int i = 0; i<this.taille; i++) {
            enTete += (this.taille==3)? " " : "  ";
            for(int j = 0; j<(this.taille-1)/2; j++) {
                for(int k = 0; k<nbChiffresMax+2; k++) {
                    enTete += " ";
                }
            }
            enTete += "("+getLettre(i)+")";
            for(int j = 0; j<this.taille/2; j++) {
                for(int k = 0; k<nbChiffresMax+2; k++) {
                    enTete += " ";
                }
            }
            enTete += " ";
            enTete += (i==this.taille-1)? "" : "        ";
        }
        System.out.println(enTete);

        // Affichage grille
        for (int i = 0; i < this.taille; i++) {
            for(int j = 0; j < this.taille; j++) { 
                System.out.print("|");
                for (int k = 0; k < this.taille; k++) {
                    System.out.print(this.grille[i][k][j].getValeur(nbChiffresMax));
                }
                System.out.print((j==this.taille-1) ? "|" : "|        "); // gère la fin de ligne
            }
        System.out.println();
        }
    }

    private String getLettre(int nb) {
        if(nb >= 0) {
            if(nb/26==0) return String.valueOf((char)(nb + 'a'));
            else return String.valueOf((char)(nb/26-1 + 'a')) + String.valueOf((char)(nb%26 + 'a'));
        } else return null;
    }

    private int tailleEntier(int n) {
        String nn = n + "";
        return nn.length();
    }

    public void placer(String joueur, String coordonee) {
        /*
        int c = Integer.valueOf(coordonee);

        int x = c % this.taille;
        int y = c / this.taille;
        int z=0;

        if (x < 0 || x >= this.taille || y < 0 || y >= this.taille) {
            System.out.println("EREUR ! Le deuxième caractère des coordoné de la case est invalide");
        } else if (this.grille[y][x][z].estVide()) {
            this.grille[y][x][z].setValeur(joueur);
        }*/
    }

    /*public void testRegretion() {

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

    }*/
    public boolean grilleGagnante(){return false;} // a modif

    public boolean verifieCoup(String input){return false;}
}
