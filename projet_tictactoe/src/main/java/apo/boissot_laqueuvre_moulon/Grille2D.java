package apo.boissot_laqueuvre_moulon;
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

    public void placer(String joueur, String numero) {
        int num = Integer.valueOf(numero) - 1;

        int x = num % this.taille;
        int y = num / this.taille;

        if (x < 0 || x >= this.taille || y < 0 || y >= this.taille) {
            System.out.println("EREUR ! Le numéro de case " + num + " est invalide");
        } else if (this.grille[y][x].estVide()) {
            this.grille[y][x].setValeur(joueur);
        } else {
            System.out.println("EREUR ! La case " + num + " est déjà pleine");
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

    public boolean verifieCoup(String input) {
        return true;
    }

}
