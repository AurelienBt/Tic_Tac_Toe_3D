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
        int c = coordone.charAt(0);
        int x, y;

        switch (c) {
            case 1:
                x = 0;
                y = 0;
                break;
            case 2:
                x = 1;
                y = 0;
                break;
            case 3:
                x = 2;
                y = 0;
                break;
            case 4:
                x = 0;
                y = 1;
                break;
            case 5:
                x = 1;
                y = 1;
                break;
            case 6:
                x = 2;
                y = 1;
                break;
            case 7:
                x = 0;
                y = 2;
                break;
            case 8:
                x = 1;
                y = 2;
                break;
            case 9:
                x = 2;
                y = 2;
                break;
            default:
                System.out.println("EREUR ! Le deuxième caractère des coordoné de la case est invalide");
                break;
        }

        if (this.grille[y][x].estVide()) {
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
}
