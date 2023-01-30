package apo.boissot_laqueuvre_moulon;

import java.util.Scanner;

/***
 * La class InterfaceJeu centralise tout les échange homme/machine.
 * Elle est donc responsable de demander les cases à jouer et d'afficher les
 * grilles de jeu
 */
public class InterfaceJeu {

    public InterfaceJeu() {

    }

    /***
     * Rassemble tous les appelles des choix que doit faire l'utilisateur avant de
     * lancer une partie.
     */
    public void choixPartie() {
        Scanner scanner = new Scanner(System.in);
        int modeJeu = choixMode(scanner);
        int joueurs = choixJoueurs(scanner);
        int tailleGrille = choixTaille(scanner);
        Jeu jeu = new Jeu(modeJeu, joueurs, 1, tailleGrille);
        jeu.partie(scanner);
        scanner.close();
    }

    private int choixMode(Scanner scanner) {
        int choix = -1;
        boolean inputCorrect = false;
        do {
            System.out.println();
            System.out.println("Veuillez choisir un mode de jeu : ");
            System.out.println("0 : 2D");
            System.out.println("1 : 3D");
            if (scanner.hasNextInt()) {
                choix = scanner.nextInt();
                if (choix == 0 || choix == 1)
                    inputCorrect = true;
            } else
                scanner.nextLine();
            if (!inputCorrect) {
                System.out.println();
                System.out.println("Saisie incorrecte.");
            }
        } while (!inputCorrect);

        return choix;
    }

    private int choixJoueurs(Scanner scanner) {
        int choix = -1;
        boolean inputCorrect = false;
        do {
            System.out.println();
            System.out.println("Veuillez choisir les joueurs de la partie : ");
            System.out.println("0 : Joueur vs Joueur");
            System.out.println("1 : Joueur vs IA");
            System.out.println("2 : IA vs IA");
            if (scanner.hasNextInt()) {
                choix = scanner.nextInt();
                if (choix == 0 || choix == 1 || choix == 2)
                    inputCorrect = true;
            } else
                scanner.nextLine();
            if (!inputCorrect) {
                System.out.println();
                System.out.println("Saisie incorrecte.");
            }

        } while (!inputCorrect);

        return choix;
    }

    private int choixTaille(Scanner scanner) {
        int choix = -1;
        boolean inputCorrect = false;
        do {
            System.out.println();
            System.out.println("Veuillez entrer la taille de la grille : ");
            if (scanner.hasNextInt()) {
                choix = scanner.nextInt();
                if (choix >= 3)
                    inputCorrect = true;
            }
            scanner.nextLine();
            // else scanner.nextLine();
            if (!inputCorrect) {
                System.out.println();
                System.out.println("Saisie incorrecte.");
            }

        } while (!inputCorrect);

        return choix;
    }
}
