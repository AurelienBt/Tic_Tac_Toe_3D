package apo.boissot_laqueuvre_moulon;
import java.util.Scanner;

public class JoueurHumain extends Joueur {

    /***
     * Permet à l'utilisateur de choisir son prochain coup
     * @param text Texte à afficher dans la console 
     * (en fonction de si le dernier input du joueur n'était pas valide par exemple)
     * @param scanner Scanner utilisé pour demander les inputs de l'utilisateur
     * @return String correspondant au coup choisi par le joueur
     */
    public String choisirCoup(String text, Scanner scanner){
        String choix = "";
        System.out.println();
        System.out.println(text);
        choix = scanner.nextLine();
        return choix;
    }
   

    public boolean estIA(){
        return false;
    }

    public void setGrille(Grille grille){}
}