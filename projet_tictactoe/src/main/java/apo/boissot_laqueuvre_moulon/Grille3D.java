package apo.boissot_laqueuvre_moulon;

import java.util.ArrayList;

public class Grille3D extends Grille {
    private Case grille[][][];

    public Grille3D(int taille) {
        super(taille);

        viderGrille();
    }

    public void viderGrille() {
        this.grille = new Case[taille][taille][taille];

        Case c;

        for (int i = 0; i < this.taille; i++) {
            for (int j = 0; j < this.taille; j++) {
                for (int k = 0; k < this.taille; k++) {
                    c = new Case(i * this.taille + j + 1);
                    this.grille[i][j][k] = c;
                }
            }
        }
    }

    public void afficher() {
        // Affichage en-tête
        int nbChiffresMax = tailleEntier(this.taille * this.taille);
        String enTete = "";
        if (this.taille % 2 == 0) {
            for (int l = 0; l < nbChiffresMax; l++) {
                enTete += " ";
            }
        }
        for (int i = 0; i < this.taille; i++) {
            enTete += (this.taille == 3) ? " " : "  ";
            for (int j = 0; j < (this.taille - 1) / 2; j++) {
                for (int k = 0; k < nbChiffresMax + 2; k++) {
                    enTete += " ";
                }
            }
            enTete += "(" + getLettre(i) + ")";
            for (int j = 0; j < this.taille / 2; j++) {
                for (int k = 0; k < nbChiffresMax + 2; k++) {
                    enTete += " ";
                }
            }
            enTete += " ";
            enTete += (i == this.taille - 1) ? "" : "        ";
        }
        System.out.println(enTete);

        // Affichage grille
        for (int i = 0; i < this.taille; i++) {
            for (int j = 0; j < this.taille; j++) {
                System.out.print("|");
                for (int k = 0; k < this.taille; k++) {
                    System.out.print(this.grille[i][k][j].afficher(nbChiffresMax));
                }
                System.out.print((j == this.taille - 1) ? "|" : "|        "); // gère la fin de ligne
            }
            System.out.println();
        }
    }

    private String getLettre(int nb) {
        if (nb >= 0) {
            if (nb / 26 == 0)
                return String.valueOf((char) (nb + 'a'));
            else
                return String.valueOf((char) (nb / 26 - 1 + 'a')) + String.valueOf((char) (nb % 26 + 'a'));
        } else
            return null;
    }

    public ArrayList<int[]> listerCoupPossible() {
        ArrayList<int[]> coupPossible = new ArrayList<int[]>();

        int c[] = { -1, -1, -1 };

        for (int i = 0; i < this.taille; i++) {
            for (int j = 0; j < this.taille; j++) {
                for (int k = 0; k < this.taille; k++) {
                    if (this.grille[i][j][k].estVide()) {
                        c[0] = i;
                        c[1] = j;
                        c[2] = k;
                        coupPossible.add(c);
                    }
                }
            }
        }

        return coupPossible;
    }

    public void placer(String joueur, String numeroCase) {
        if (verifierCoup(numeroCase)) {
            int coord[] = getCoordonneesCase(numeroCase);
            this.grille[coord[0]][coord[1]][coord[2]].setValeur(joueur);
        }
    }

    // A faire
    public boolean grilleGagnante() {
        return false;
    }

    public boolean verifierCoup(String input) {
        int coord[] = getCoordonneesCase(input);

        // On regarde si les coordonnées données sont valides (dans le tableau)
        if (coord[0] < 0 || coord[0] >= this.taille || coord[1] < 0 || coord[1] >= this.taille || coord[2] < 0
                || coord[2] >= this.taille) {
            System.out.println("EREUR ! Le numéro de case " + input + " est invalide");
            return false;
        }
        // On regarde si la case choisi est libre
        else if (this.grille[coord[0]][coord[1]][coord[2]].estVide()) {
            return true;
        } else {
            System.out.println("EREUR ! La case " + input + " est déjà pleine");
            return false;
        }
    }

    private int[] getCoordonneesCase(String numeroCase) {
        int coord[] = { -1, -1, -1 };

        String lettres = "";
        int numero = 0;
        int z = 0;

        // On récupère toute les lettres dans une nouvelle String
        int i = 0;
        while (!Character.isDigit(numeroCase.charAt(i))) {
            lettres = lettres + numeroCase.charAt(i);
            i++;
        }

        // i = nombre de lettre
        for (int j = 0; j < i; j++) {
            z = z * 26;
            z += lettres.charAt(j) - 97;
        }
        coord[0] = z;

        // On récupère tous les chiffres qu'on transforme en entier
        while (i < numeroCase.length()) {
            numero = numero * 10;
            numero += Integer.valueOf(numeroCase.charAt(i));
            i++;
        }

        coord[1] = (numero - 1) / this.taille;
        coord[2] = (numero - 1) % this.taille;
        return coord;
    }

    public void testRegretion() {
        System.out.println("Test Regression pour une grille 3*3*3");

        // getCoordonneesCase
        System.out.println("z=0, y=0, x=0");
        int c[] = getCoordonneesCase("a1");
        System.out.println("z=" + c[0] + ", y=" + c[1] + ", x=" + c[2]);
        System.out.println("z=2, y=2, x=2");
        c = getCoordonneesCase("c9");
        System.out.println("z=" + c[0] + ", y=" + c[1] + ", x=" + c[2]);
        System.out.println("z=1, y=0, x=1");
        c = getCoordonneesCase("b2");
        System.out.println("z=" + c[0] + ", y=" + c[1] + ", x=" + c[2]);
        System.out.println("");
        System.out.println("");

        // afficher
        System.out.println("Une grille vide :");
        afficher();
        System.out.println("");
        System.out.println("");

        // verifieCoup
        System.out.println("Vrai : " + verifierCoup("1"));
        System.out.println("Vrai : " + verifierCoup("9"));
        System.out.println("Vrai : " + verifierCoup("8"));

        placer("X", "1");
        placer("O", "5");
        placer("X", "9");

        System.out.println("Faux : " + verifierCoup("1"));
        System.out.println("Faux : " + verifierCoup("5"));
        System.out.println("Faux : " + verifierCoup("9"));
        System.out.println("Faux : " + verifierCoup("800"));
        System.out.println("Faux : " + verifierCoup("0"));
        System.out.println("Faux : " + verifierCoup("-1"));
        System.out.println("");
        System.out.println("");

        // afficher et placer
        placer("X", "1");
        placer("O", "5");
        placer("X", "9");
        this.grille[0][2][0].setSelectionnee(true);
        System.out.println("Une grille comme suis :");
        System.out.println("| X  2 >3<|");
        System.out.println("| 4  O  6 |");
        System.out.println("| 7  8  X |");
        System.out.println("");
        afficher();
        System.out.println("");
        System.out.println("");

        // viderGrille
        viderGrille();
        System.out.println("Une grille vide :");
        afficher();
        System.out.println("");
        System.out.println("");

        // tailleEntier
        System.out.println("1 : " + tailleEntier(5));
        System.out.println("2 : " + tailleEntier(50));
        System.out.println("3 : " + tailleEntier(508));
        System.out.println("3 : " + tailleEntier(100));
        System.out.println("4 : " + tailleEntier(5555));
        System.out.println("");
        System.out.println("");
    }

}
