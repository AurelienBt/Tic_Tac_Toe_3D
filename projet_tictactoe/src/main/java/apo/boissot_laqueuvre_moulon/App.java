package apo.boissot_laqueuvre_moulon;

public class App {
    public static void main(String[] args) {

        // InterfaceJeu iJeu = new InterfaceJeu();
        // iJeu.choixPartie();
        // Grille2D g = new Grille2D(3);
        // Jeu j = new Jeu(0, 0, 0, 3);
        // j.partie();
        // g.testRegretion();
        // InterfaceJeu iJeu = new InterfaceJeu();
        // iJeu.choixPartie();

        Grille2D g = new Grille2D(3);
        // gg.testRegretion();

        JoueurIA j = new JoueurIA(3, g);

        j.testRegretion();
    }
}