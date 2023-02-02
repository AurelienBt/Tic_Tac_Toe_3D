package apo.boissot_laqueuvre_moulon;

import java.util.Scanner;

public abstract class Joueur {

    /***
     * Permet à l'utilisateur de choisir son prochain coup
     * 
     * @param text    Texte à afficher dans la console
     *                (en fonction de si le dernier input du joueur n'était pas
     *                valide par exemple)
     * @param scanner Scanner utilisé pour demander les inputs de l'utilisateur
     * @return String correspondant au coup choisi par le joueur
     */
    public abstract String choisirCoup(String text, Scanner scanner);
    public abstract void setGrille(Grille grille);
    public abstract boolean estIA();
}
