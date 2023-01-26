package apo.boissot_laqueuvre_moulon;
import java.util.Scanner;

public class JoueurHumain extends Joueur {
    public String choisirCoup(String text, Scanner scanner){
        String choix = "";
        System.out.println();
        System.out.println(text);
        choix = scanner.nextLine();
        return choix;
    }
}   
