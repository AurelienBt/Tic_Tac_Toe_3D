package apo.boissot_laqueuvre_moulon;

import java.util.ArrayList;
import java.util.Scanner;

/***
 * Cette class abstraite est le modèle des Grille2D et Grille3D
 */
public abstract class Grille {

    /***
     * La hauteur, la largeur (et la profondeur) de la grille
     */
    protected int taille;

    public Grille() {
    }

    /***
     * 
     * @param taille La hauteur, la largeur (et la profondeur) de la grille
     */
    public Grille(int taille) {
        this.taille = taille;
    }

    /***
     * Affiche la grille dans la console
     */
    public abstract void afficher();

    /***
     * Vide la grille en remplaçant toute les cases par des cases vides (avec leur
     * numéro et pas des "X" "O")
     */
    public abstract void viderGrille();

    /***
     * Place un symbole de joueur à la case dont l'id est coordone si c'est possible
     * 
     * @param joueur   le symbole du joueur
     *                 ex : "O" , "X" ou autre
     * @param coordone l'id de la case
     *                 ex : "a1" "9"
     */
    public abstract void placer(String joueur, String coordone);

    /***
     * Place un symbole de joueur à la case dont l'id est coordone si c'est possible
     * 
     * @param joueur   1 ou 2, pour joueur 1 ou joueur 2
     * @param coordone les coordonnées de la case sous forme [z,y,x] ou [y,x]
     */
    public abstract void jouerCoup(int joueur, int[] coordone);

    /***
     * 
     * @param input l'input saisie par le joueur
     * @return true si l'input est valide, false sinon
     */
    public abstract boolean verifierInput(String input);

    /***
     * 
     * @param input l'id de la case
     *              ex : "a1" "9"
     * @return true si le coup est valide, false sinon
     */
    public abstract boolean verifierCoup(String coup);

    public abstract String validerCoup(String coup, Scanner scanner);

    /***
     * 
     * @return tableau contenant la liste des coups valides possible
     *         chaque coup est représenté par un tableau de 2 ou 3 coordonnées
     *         [z,y,x]
     */
    public abstract ArrayList<int[]> listerCoupPossible();

    /***
     * Verifie si la grille est gagnante. Pour alléger les calculs on regarde
     * uniquement en fct du dernier coup.
     * 
     * @param coup l'id de la case du dernier coup
     *             ex : "a1" "9"
     * @return true si la grille est gagnante, false sinon
     */
    public abstract boolean grilleGagnante(String coup);

    /***
     * Return la case d'une coordonée du tableau
     * @param coord coordonée de la case {z,y,x} ou {y,x}
     * @return la case
     */
    public abstract Case getCase(int[] coord);

    /***
     * 
     * @param tmp grille tampon, copie de la première grille
     */
    public abstract void getCopy(Grille tmp);
    public abstract boolean is2D();

    /***
     * 
     * @return true si la grille est pleine, false sinon
     */
    public abstract boolean grilleEstPleine();

    /***
     * 
     * @return taille de la grille (sa hauteur, sa largeur (et sa profondeur))
     */
    public int getTaille() {
        return this.taille;
    }

    /***
     * 
     * @param n
     * @return la taille de n (en nombre de caractère)
     */
    protected int tailleEntier(int n) {
        String nn = n + "";
        return nn.length();
    }
}
