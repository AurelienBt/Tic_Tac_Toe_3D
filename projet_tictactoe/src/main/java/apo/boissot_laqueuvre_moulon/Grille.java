package apo.boissot_laqueuvre_moulon;

import java.util.ArrayList;

public abstract class Grille {
    protected int taille;

    public Grille() {
    }

    public Grille(int taille) {
        this.taille = taille;
    }

    public abstract void afficher();

    public abstract void placer(String joueur, String coordone);

    public abstract boolean verifierCoup(String input);

    public abstract ArrayList<int[]> listerCoupPossible();

    public abstract boolean grilleGagnante();

    public int getTaille() {
        return this.taille;
    }

    public void placer(char joueur, String coordone) {

    }

    public abstract void viderGrille();

    protected int tailleEntier(int n) {
        String nn = n + "";
        return nn.length();
    }
}
