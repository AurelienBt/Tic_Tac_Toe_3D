package apo.boissot_laqueuvre_moulon;
import java.util.Scanner;

public class InterfaceJeu {
    
    public InterfaceJeu() {

    }

    public void choixPartie() {
        int modeJeu = choixMode();
        int joueurs = choixJoueurs();
        int tailleGrille = choixTaille();
        Jeu jeu = new Jeu(modeJeu, joueurs, 0, tailleGrille);
        jeu.partie();
        }
 
    private int choixMode() {
        Scanner scanner = new Scanner(System.in);
        int choix = -1;
        boolean inputCorrect = false;
        do {
            System.out.println();
            System.out.println("Veuillez choisir un mode de jeu : ");
            System.out.println("0 : 2D");
            System.out.println("1 : 3D");
            if(scanner.hasNextInt()) {
                choix = scanner.nextInt();
                if(choix == 0 || choix == 1) inputCorrect = true;
            }
            else scanner.nextLine();
            if(!inputCorrect) System.out.println("Saisie incorrecte.");
            
        } while(!inputCorrect);

        scanner.close();
        return choix;
    }

    private int choixJoueurs() {
        Scanner scanner = new Scanner(System.in);
        int choix = -1;
        boolean inputCorrect = false;
        do {
            System.out.println();
            System.out.println("Veuillez choisir les joueurs de la partie : ");
            System.out.println("0 : Joueur vs Joueur");
            System.out.println("1 : Joueur vs IA");
            System.out.println("2 : IA vs IA");
            if(scanner.hasNextInt()) {
                choix = scanner.nextInt();
                if(choix == 0 || choix == 1 || choix == 2) inputCorrect = true;
            }
            else scanner.nextLine();
            if(!inputCorrect) System.out.println("Saisie incorrecte.");
            
        } while(!inputCorrect);

        scanner.close();
        return choix;
    }

    private int choixTaille() {
        Scanner scanner = new Scanner(System.in);
        int choix = -1;
        boolean inputCorrect = false;
        do {
            System.out.println();
            System.out.println("Veuillez entrer la taille de la grille : ");
            if(scanner.hasNextInt()) {
                choix = scanner.nextInt();
                if(choix >= 3) inputCorrect = true;
            }
            else scanner.nextLine();
            if(!inputCorrect) System.out.println("Saisie incorrecte.");
            
        } while(!inputCorrect);

        scanner.close();
        return choix;
    }
}
