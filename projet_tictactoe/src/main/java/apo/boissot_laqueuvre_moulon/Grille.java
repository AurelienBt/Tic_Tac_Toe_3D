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

    /***
     * Constructeur de la classe Grille
     */
    public Grille() {
    }

    /***
     * Constructeur de la classe grille initialisant sa taille
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
     * Place un symbole de joueur à la case dont l'id est coordonee si c'est possible
     * 
     * @param joueur le symbole du joueur
     *               ex : "O" , "X" ou autre
     * @param coordonee l'id de la case
     *                  ex : "a1" "9"
     */
    public abstract void placer(String joueur, String coordonee);

    /***
     * Place un symbole de joueur à la case dont l'id est coordonee si c'est possible
     * 
     * @param joueur 1 ou 2, pour joueur 1 ou joueur 2
     * @param coordonee les coordonnées de la case sous la forme [z,y,x] ou [y,x]
     */
    public abstract void jouerCoup(int joueur, int[] coordonee);

    /***
     * 
     * @param input l'input saisi par le joueur
     * @return true si l'input est valide, false sinon
     */
    public abstract boolean verifierInput(String input);

    /***
     * Vérification de la validité du coup joué par le joueur 
     * (au niveau de la syntaxe et de la possibilité de le jouer dans la grille)
     * @param input l'id de la case
     *              ex : "a1" "9"
     * @return true si le coup est valide, false sinon
     */
    public abstract boolean verifierCoup(String coup);

    /***
     * Permet de valider un coup avec Entrée ou de choisir un autre coup
     * @param coup le coup saisi par le joueur
     *             ex : "a6", "8"
     * @param scanner Scanner utilisé pour demander les inputs de l'utilisateur
     * @return String du choix du joueur : une chaine vide pour valider le coup
     *         ou un input pour faire un autre coup
     */
    public abstract String validerCoup(String coup, Scanner scanner);

    /***
     * Renvoie la liste des coup possibles dans la grille
     * @return tableau contenant la liste des coups valides possible
     *         chaque coup est représenté par un tableau de 2 ou 3 coordonnées
     *         [y,x] ou [z,y,x]
     */
    public abstract ArrayList<int[]> listerCoupPossible();

    /***
     * Verifie si la grille est gagnante ou non. Pour alléger les calculs on regarde
     * uniquement en fonction du dernier coup
     * 
     * @param coup l'id de la case du dernier coup
     *             ex : "a1" "9"
     * @return true si la grille est gagnante, false sinon
     */
    public abstract boolean grilleGagnante(String coup);

    /***
     * Vérifie si la grille est pleine ou non
     * @return true si la grille est pleine, false sinon
     */
    public abstract boolean grilleEstPleine();

    /***
     * Renvoie la taille de la grille
     * @return taille de la grille (sa hauteur, sa largeur (et sa profondeur))
     */
    public int getTaille() {
        return this.taille;
    }

    /***
     * Renvoie la taille de l'entier n en nombre de caractères
     * @param n
     * @return la taille de n (en nombre de caractère)
     */
    protected int tailleEntier(int n) {
        String nn = n + "";
        return nn.length();
    }

    /***
     * Sélectionne les cases passé en paramètre
     * 
     * @param alignement un array de int[] contenant la liste des coordonées des
     *                   cases à mettre en valeur
     */
    protected abstract void mettreEnValeur(ArrayList<int[]> alignement);
}
