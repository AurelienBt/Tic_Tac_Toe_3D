package apo.boissot_laqueuvre_moulon;
import java.util.Scanner;

public class JoueurHumain extends Joueur {
    public String choisirCoup(String text){
        String choix = "";
        
        Scanner scanner = new Scanner(System.in);
        System.out.println(text);
        System.out.println(scanner.hasNext());
        /*while(!scanner.hasNextLine()){
            scanner.nextInt();
            System.out.println("Clearing...");
        } */
        if (scanner.hasNextLine()) choix = scanner.nextLine();
        
        scanner.close();
        return choix;
    }

}
