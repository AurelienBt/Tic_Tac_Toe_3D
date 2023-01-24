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
}