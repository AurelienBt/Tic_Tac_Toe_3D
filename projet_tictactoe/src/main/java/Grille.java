public abstract class Grille {
    protected int taille;

    public Grille() {
    }

    public Grille(int taille) {
        this.taille = taille;
    }

    public abstract void afficher();

    public abstract void placer(String joueur, String coordone);

<<<<<<< HEAD
    public abstract boolean verifieCoup(String input);

    public int getTaille() {
        return this.taille;
    }

    public void placer(char joueur, String coordone) {

    }
=======
    public abstract void viderGrille();
>>>>>>> 8da70ceb7689d7bce123999c45b4c0d37110e41e

}
