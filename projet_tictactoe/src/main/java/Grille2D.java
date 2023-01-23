public class Grille2D extends Grille {
    private Case grille[][];

    public Grille2D(int taille) {
        super(taille);

        this.grille = new Case[taille][];

        Case[] ligne;
        Case c;

        for (int i = 0; i < this.taille; i++) {
            ligne = new Case[taille];
            for (int j = 0; j < this.taille; j++) {
                c = new Case();
                ligne[j] = c;
            }
            this.grille[i] = ligne;
        }
    }

    public void afficher() {
        for (int i = 0; i < this.taille; i++) {
            System.out.print("| ");
            for (int j = 0; j < this.taille; j++) {
                System.out.print(this.grille[i][j]);
                System.out.print(" ");
            }
            System.out.println("|");
        }
    }

    public void placer(String joueur, int x, int y) {
        if (this.grille[y][x].estVide()) {
            this.grille[y][x].setValeur(joueur);
        }
    }
}
