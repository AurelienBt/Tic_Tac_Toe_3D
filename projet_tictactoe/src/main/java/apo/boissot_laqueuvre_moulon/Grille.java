package apo.boissot_laqueuvre_moulon;
public abstract class Grille {
    protected int taille;

    public Grille() {
    }

    public Grille(int taille) {
        this.taille = taille;
    }

    public abstract void afficher();

    public abstract void placer(String joueur, String coordone);

    public abstract boolean verifieCoup(String input);
    public abstract boolean grilleGagnante();
    public abstract boolean grilleEstPleine();

    public int getTaille() {
        return this.taille;
    }

    public void placer(char joueur, String coordone) {

    }
    public abstract void viderGrille();

}
