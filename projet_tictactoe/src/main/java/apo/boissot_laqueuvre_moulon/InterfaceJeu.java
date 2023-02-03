package apo.boissot_laqueuvre_moulon;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/***
 * La classe InterfaceJeu est responsable de toutes les demandes à l'utilisateur précédant la création du jeu
 */
public class InterfaceJeu {

    /***
     * Constructeur par défaut de la classe InterfaceJeu
     */
    public InterfaceJeu() {

    }

    /***
     * Gère la création d'une partie après avoir demandé à l'utilisateur les 
     * différents paramètres de celle-ci
     */
    public void choixPartie() {
        Scanner scanner = new Scanner(System.in);
        boolean chargerSave = choixChargerSave(scanner);
        boolean erreur = false;

        // Création de la partie à l'aide des paramètres de la sauvegarde
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

        // Si l'on ne veut pas charger de sauvegarde, où s'il y a eu une erreur de chargement
        if(!chargerSave || (chargerSave && erreur)) {
            int modeJeu = choixMode(scanner);
            int joueurs = choixJoueurs(scanner);
            int tailleGrille = choixTaille(scanner);
            Jeu jeu = new Jeu(modeJeu, joueurs, 1, tailleGrille);
            jeu.partie(scanner);
        }
            scanner.close();
    }

    /***
     * Demande à l'utilisateur s'il veut charger une sauvegarde enregistrée, si la sauvegarde existe
     * @param scanner scanner utilisé pour demander les inputs de l'utilisateur
     * @return true si l'utilisateur veut charger une sauvegarde, false sinon
     */
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

    /**
     * Demande à l'utilisateur s'il souhaite jouer en mode 2D ou 3D
     * @param scanner scanner utilisé pour demander les inputs de l'utilisateur
     * @return Entier correspondant au choix du mode : 0 si 2D, 1 si 3D
     */
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

    /**
     * Demande à l'utilisateur s'il souhaite jouer contre un autre joueur, contre une IA, ou laisser deux IA jouer
     * @param scanner scanner utilisé pour demander les inputs de l'utilisateur
     * @return Entier correspondant au choix des joueurs de la partie : 0 si deux joueurs, 1 si un joueur et une IA, 2 si deux IA
     */
    private int choixJoueurs(Scanner scanner) {
        int choix = -1;
        boolean inputCorrect = false;
        do {
            System.out.println();
            System.out.println("Veuillez choisir les joueurs de la partie : ");
            System.out.println("0 : Joueur vs Joueur");
            System.out.println("1 : Joueur vs IA");
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

    /**
     * Demande à l'utilisateur la taille de la grille dans laquelle il veut jouer
     * @param scanner scanner utilisé pour demander les inputs de l'utilisateur
     * @return Entier correspondant à la taille de la grille
     */
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

    /***
     * Stocke une sauvegarde précédente dans un tableau afin de l'utiliser par la suite
     * @return Tableau de String contenant chaque ligne de la sauvegarde
     */
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
