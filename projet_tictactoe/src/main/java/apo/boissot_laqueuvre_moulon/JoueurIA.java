package apo.boissot_laqueuvre_moulon;

import java.util.Scanner;
import java.util.Collections;
import java.util.ArrayList;

public class JoueurIA extends Joueur {

    int maxDepth;
    Grille grille;

    /***
     * unique onstructeur de JoueurIA
     * 
     * @param maxDepth la profondeur maximale de minmax
     * @param grille   la grille surlaquelle effectuer minmax
     */
    JoueurIA(int maxDepth, Grille grille) {
        this.maxDepth = maxDepth;
        this.grille = grille;
    }

    /***
     * Mettre à jour la grille à utiliser pour le minmax
     * 
     * @param grille nouvelle grille
     */
    public void setGrille(Grille grille) {
        this.grille.getCopy(grille);
    }

    /***
     * Renvoie le prochain coup joué par l'IA
     * @param text Texte à afficher dans la console 
     * @param scanner Scanner utilisé pour demander les inputs de l'utilisateur
     * @return String correspondant au coup choisi par l'IA
     */
    public String choisirCoup(String text, Scanner scanner) {
        int[] coupAJouer = score(this.grille, this.maxDepth, true);
        System.out.println(coupAJouer);
        return coordEnCoup(coupAJouer);
    }

    public boolean estIA(){
        return true;
    }

    /***
     * Liste et associe à chaque coup possible un score déterminé par l'algorithme
     * minmax
     * 
     * @param grille    Grille de jeu (carré de taille n)
     * @param max_depth profondeur maximum pour l'algorithme minmax
     * @param minimizer indique si minmax doit maximiser ou minimiser le score
     * 
     * @return coup avec le score le plus élevé ("meilleur coup")
     */

    private int[] score(Grille grille, int max_depth, boolean minimizer) {
        Grille grille_tampon;
        if (grille.is2D()){
            grille_tampon = new Grille2D(grille.getTaille());
        } else{
            grille_tampon = new Grille3D(grille.getTaille());
        }
        grille_tampon.getCopy(grille);
        int scoreTampon = 0;
        int scoreJouer = 0;
        ArrayList<int[]> listeCoup = grille.listerCoupPossible();

        ArrayList<int[]> liste_coup_jouable = new ArrayList<int[]>();
        for (int i = 0; i < listeCoup.size(); i++) {
            int[] coup = listeCoup.get(i);

            if (minimizer) {
                grille_tampon.jouerCoup(2, coup);
                scoreTampon = minmax(grille_tampon, max_depth, !minimizer, coordEnCoup(coup));
                if (i == 0) {
                    scoreJouer = scoreTampon;
                }
                if (scoreTampon == scoreJouer) {
                    liste_coup_jouable.add(coup);
                } else if (scoreTampon > scoreJouer) {
                    scoreJouer = scoreTampon;
                    liste_coup_jouable.clear();
                    liste_coup_jouable.add(coup);
                }
            } else {
                grille_tampon.jouerCoup(1, coup);
                scoreTampon = minmax(grille_tampon, max_depth, !minimizer, coordEnCoup(coup));
                if (i == 0) {
                    scoreJouer = scoreTampon;
                }
                if (scoreTampon == scoreJouer) {
                    liste_coup_jouable.add(coup);
                } else if (scoreTampon > scoreJouer) {
                    scoreJouer = scoreTampon;
                    liste_coup_jouable.clear();
                    liste_coup_jouable.add(coup);
                }
            }
            grille_tampon.getCopy(grille);
        }

        // On mélange aléatoirement la liste
        Collections.shuffle(liste_coup_jouable);
        return liste_coup_jouable.get(0);
    }

    /***
     * Algorthme récursif minmax
     * 
     * @param grille_virt grille de jeu
     * @param max_depth   profondeur max, se décrémente entre les appels récusrsifs
     * @param minimizer   indique si le minmax doit renvoyer le max ou le min,
     *                    s'inverse entre chaque appel
     * @return
     */
    public int minmax(Grille grille_virt, int max_depth, boolean minimizer, String coup) {
        ArrayList<Integer> liste = new ArrayList<Integer>();

        // Conditions d'arrets
        //System.out.println(coup);
        //grille_virt.afficher();
        if (grille_virt.grilleGagnante(coup)) {
            //grille_virt.afficher();
            if (minimizer) {
                return -100 * max_depth;
            } else {
                return 100 * max_depth;
            }
        } else if (grille_virt.grilleEstPleine() || max_depth == 0) {
            
            return 0;
        }

        // Boucle d'application
        else {
            ArrayList<int[]> listeCoup = grille_virt.listerCoupPossible();
            
            


            for (int i = 0; i < listeCoup.size(); i++) {
                Grille grille_tmp;
                if (grille.is2D()){
                    grille_tmp = new Grille2D(grille.getTaille());
                } else{
                    grille_tmp = new Grille3D(grille.getTaille());
                }
                grille_tmp.getCopy(grille_virt);
                if (minimizer) {
                    grille_tmp.jouerCoup(2, listeCoup.get(i));
                } else {
                    grille_tmp.jouerCoup(1, listeCoup.get(i));
                }
                liste.add(minmax(grille_tmp, max_depth - 1, !minimizer, coordEnCoup(listeCoup.get(i))));
            }
            //System.out.println(liste);
            if(liste.isEmpty()) return 0;
            if (minimizer) {
                int max = Collections.max(liste);
                return max;
            } else {
                int min = Collections.min(liste);
                return min;
            }
        }
    }

    /***
     * Un test unitaire de toute les fonctionnalité de Grille2D
     */
    public void testRegretion() {
        System.out.println("Test Regression pour un JoueurIA");

        // getLettre
        System.out.println("a " + getLettre(0));
        System.out.println("d " + getLettre(3));
        System.out.println("ae " + getLettre(30));
        System.out.println("z " + getLettre(25));
        System.out.println("");
        System.out.println("");

        // coordEnCoup
        int[] tab = { 0, 1, 1};
        System.out.println("a1 " + coordEnCoup(tab));
        tab[0] = 0;
        tab[1] = 1;
        tab[2] = 2;
        System.out.println("a9 " + coordEnCoup(tab));
        System.out.println("");
        System.out.println("");
    }

    /***
     * Transforme des coordonnées en coup
     * 
     * @param coord les coordonnées sous la forme [z,y,x] ou [y,x]
     * @return le coup qui correspond a des coordonnées ("a1" "12")
     */
    private String coordEnCoup(int[] coord) {
        String coup;

        if (coord.length == 2) {
            int numero = coord[0] * this.grille.getTaille() + coord[1] + 1;
            coup = "" + numero;
        } else {
            int numero = coord[1] * this.grille.getTaille() + coord[2] + 1;
            String lettre = getLettre(coord[0]);
            coup = lettre + numero;
        }

        return coup;
    }

    /***
     * Renvoie les lettres de l'id d'une case qui à pour profondeur nb
     * ex : 3 donne 'd' et 30 donne 'ae'
     * 
     * @param nb la profondeur de la case
     * @return les lettres de l'id de la case
     */
    private String getLettre(int nb) {
        if (nb >= 0) {
            if (nb / 26 == 0)
                return String.valueOf((char) (nb + 'a'));
            else
                return String.valueOf((char) (nb / 26 - 1 + 'a')) + String.valueOf((char) (nb % 26 + 'a'));
        } else
            return null;
    }
}
