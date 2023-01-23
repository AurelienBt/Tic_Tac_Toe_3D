public abstract class Grille {
    protected int taille;

    public Grille() {
    }

    public Grille(int taille) {
        this.taille = taille;
    }

    public abstract void afficher();

    public abstract void placer(String joueur, String coordone);

    public abstract void viderGrille();

    public int getTaille() {
        return this.taille;
    }
}
