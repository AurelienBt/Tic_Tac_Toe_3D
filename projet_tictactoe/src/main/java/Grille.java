public abstract class Grille {
    protected int taille;

    public Grille() {
    }

    public Grille(int taille) {
        this.taille = taille;
    }

    public abstract void afficher();

    public abstract void placer(char joueur);

    public abstract void viderGrille();

    public int getTaille() {
        return this.taille;
    }

    public void placer(char joueur, String coordone) {

    }
}
