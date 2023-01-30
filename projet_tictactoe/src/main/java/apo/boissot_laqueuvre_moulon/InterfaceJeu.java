package apo.boissot_laqueuvre_moulon;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
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
        boolean chargerSave = choixChargerSave(scanner);
        boolean erreur = false;
        if(chargerSave) {
            ArrayList<String> sauvegarde = getSauvegarde();
            if(sauvegarde.get(0).compareTo("erreur") == 0) {
                erreur = true;
            }
            else {
                String[] parametres = sauvegarde.get(0).split(" ");
                int modeJeu = -1;
                int protagonistes = -1;
                int quiCommence = -1;
                int tailleGrille = 3;
                try {
                    modeJeu = Integer.valueOf(parametres[0]);
                    protagonistes = Integer.valueOf(parametres[1]);
                    quiCommence = Integer.valueOf(parametres[2]);
                    tailleGrille = Integer.valueOf(parametres[3]);
                }
                catch(Exception e) {
                    erreur = true;
                }
                if(!erreur) {
                    Jeu jeu = new Jeu(modeJeu, protagonistes, quiCommence, tailleGrille);
                    jeu.partie(scanner, sauvegarde);
                }
            }
            
        }
        if(!chargerSave || (chargerSave && erreur)) {
            int modeJeu = choixMode(scanner);
            int joueurs = choixJoueurs(scanner);
            int tailleGrille = choixTaille(scanner);
            Jeu jeu = new Jeu(modeJeu, joueurs, 1, tailleGrille);
            jeu.partie(scanner);
        }
            scanner.close();
    }

    private boolean choixChargerSave(Scanner scanner) {
        File f = new File("save.txt");
        if(f.exists() && !f.isDirectory() && f.length() > 0) { 
            int choix = -1;
            boolean inputCorrect = false;
            do {
                System.out.println();
                System.out.println("Voulez vous charger la partie enregistrée ?");
                System.out.println("0 : oui");
                System.out.println("1 : non");
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

            if(choix == 0) return true;
            else return false;
        }
        else return false;
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

    private ArrayList<String> getSauvegarde() {
        ArrayList<String> sauvegarde = new ArrayList<>();
        try {
            // Le fichier d'entrée
            FileInputStream file = new FileInputStream("save.txt");
            Scanner reader = new Scanner(file);

            // renvoie true s'il y a une autre ligne à lire
            while (reader.hasNextLine()) {
                sauvegarde.add(reader.nextLine());
            }
            reader.close();

        } catch (IOException e) {
            sauvegarde.add("erreur");
        }
        
        if(sauvegarde.isEmpty()) sauvegarde.add("erreur");

        return sauvegarde;
    }
}
