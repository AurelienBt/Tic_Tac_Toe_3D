package apo.boissot_laqueuvre_moulon;
import java.util.Scanner;

public class JoueurHumain extends Joueur {
    public String choisirCoup(String text){
    
        Scanner scanner = new Scanner(System.in);
        String choix = "";
        boolean inputCorrect = false;
        System.out.println();
        System.out.println(text);
        do {
            choix = scanner.nextLine();
            if(choix.equals("")) inputCorrect = false;

        
            else scanner.nextLine();
            if(!inputCorrect) System.out.println("Saisie incorrecte.");
            
        } while(!inputCorrect);

        scanner.close();
        return choix;
        
    }

}
