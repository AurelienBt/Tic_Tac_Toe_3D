package apo.boissot_laqueuvre_moulon;

import java.util.ArrayList;
import java.util.Scanner;

public class Grille3D extends Grille {
    private Case grille[][][];

    /***
     * Constructeur de la classe Grille2D, initialisant la grille avec des nombres
     * 
     * @param taille
     */
    public Grille3D(int taille) {
        super(taille);

        viderGrille();
    }

    /***
     * Vide la grille en remplaçant toute les cases par des cases vides (avec leur
     * numéro et pas des "X" "O")
     */
    public void viderGrille() {
        this.grille = new Case[taille][taille][taille];

        Case c;
        for (int i = 0; i < this.taille; i++) {
            for (int j = 0; j < this.taille; j++) {
                for (int k = 0; k < this.taille; k++) {
                    c = new Case(j * this.taille + k + 1);
                    this.grille[i][j][k] = c;
                }
            }
        }
    }

    /***
     * Affiche la grille dans la console
     */
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
                    System.out.print(this.grille[j][i][k].afficher(tailleEntier(this.taille * this.taille)));
                    // System.out.print("[" + j + "] [" + i + " ] [" + k + "]");
                }
                System.out.print((j == this.taille - 1) ? "|" : "|        "); // gère la fin de ligne
            }
            System.out.println();
        }
    }

    /***
     * Ranvoie la liste des coups possibles
     * 
     * @return tableau contenant la liste des coups valides possible
     *         chaque coup est représenté par un tableau de 3 entiers [z,y,x]
     */
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

    /***
     * Vérifie si la grille est pleine ou non
     * 
     * @return true si la grille est pleine, false sinon
     */
    public boolean grilleEstPleine() {
        for (int i = 0; i < this.taille; i++) {
            for (int j = 0; j < this.taille; j++) {
                for (int k = 0; k < this.taille; k++) {
                    if (this.grille[i][j][k].estVide())
                        return false;
                }
            }
        }
        return true;
    }

    /***
     * Place un symbole de joueur à la case dont l'id est numeroCase si c'est
     * possible
     * 
     * @param joueur     le symbole du joueur
     *                   ex : "O" , "X" ou autre
     * @param numeroCase le numéro de la case
     *                   ex : "1" "59"
     */
    public void placer(String joueur, String numeroCase) {
        if (verifierCoup(numeroCase)) {
            int coord[] = getCoordonneesCase(numeroCase);
            this.grille[coord[0]][coord[1]][coord[2]].setValeur(joueur);
        }
    }

    /***
     * Verifie si la grille est gagnante. Pour alléger les calculs on regarde
     * uniquement en fct du dernier coup.
     * 
     * @param coup l'id de la case du dernier coup
     *             ex : "a1" "ez69"
     * @return true si la grille est gagnante, false sinon
     */
    public boolean grilleGagnante(String coup) {
        if (verifieDiagonale(coup))
            return true;
        if (verifieDiagPlanX(coup))
            return true;
        if (verifieDiagPlanY(coup))
            return true;
        if (verifieDiagPlanZ(coup))
            return true;
        if (verifieX(coup))
            return true;
        if (verifieY(coup))
            return true;
        if (verifieZ(coup))
            return true;
        return false;
    }

    /***
     * Vérifie si la diagonale de la case fait ganer la partie
     * 
     * @param coup l'id de la case du dernier coup
     *             ex : "a1" "ez69"
     * @return true si la grille est gagnante, false sinon
     */
    private boolean verifieDiagonale(String coup) {
        ArrayList<int[]> alignement = new ArrayList<int[]>();

        boolean gagnante = false;
        boolean aligne = true;
        int i = 0;
        int[] coord = getCoordonneesCase(coup);
        // 4 gde Diagonale
        // Grande diagonale 1
        if (coord[0] == coord[1] && coord[1] == coord[2]) {
            String val = this.grille[0][0][0].getValeur();
            do {
                // ajout du coup pour la soluce gagnante
                int[] coupAlignement = { i, i, i };
                alignement.add(coupAlignement);

                aligne = val == this.grille[i][i][i].getValeur();
                i++;
            } while (i < this.taille && aligne);
            gagnante = aligne;
        }
        if (gagnante) {
            mettreEnValeur(alignement);
            return true;
        } else {
            alignement.clear();
        }

        // diagonale haute droite
        i = 0;
        String val = this.grille[0][0][this.taille - 1].getValeur();
        do {
            // ajout du coup pour la soluce gagnante
            int[] coupAlignement = { i, i, this.taille - 1 - i };
            alignement.add(coupAlignement);

            aligne = val == this.grille[i][i][this.taille - 1 - i].getValeur();
            i++;
        } while (i < this.taille && aligne);
        gagnante = aligne;

        if (gagnante) {
            mettreEnValeur(alignement);
            return true;
        } else {
            alignement.clear();
        }

        // diagonale bas droite
        i = 0;
        val = this.grille[0][this.taille - 1][this.taille - 1].getValeur();
        do {
            aligne = val == this.grille[i][this.taille - 1 - i][this.taille - 1 - i].getValeur();
            i++;
        } while (i < this.taille && aligne);
        gagnante = aligne;

        if (gagnante)
            return true;

        // diagonale bas gauche
        i = 0;
        val = this.grille[0][this.taille - 1][0].getValeur();
        do {
            aligne = val == this.grille[i][this.taille - 1 - i][i].getValeur();
            i++;
        } while (i < this.taille && aligne);
        gagnante = aligne;

        if (gagnante)
            mettreEnValeur(alignement);
        return gagnante;
    }

    /***
     * Sélectionne les cases passé en paramètre
     * 
     * @param alignement un array de int[3] contenant la liste des coordonées des
     *                   cases à mettre en valeur
     */
    protected void mettreEnValeur(ArrayList<int[]> alignement) {
        for (int[] coup : alignement) {
            this.grille[coup[0]][coup[1]][coup[2]].setSelectionnee(true);
        }
    }

    /***
     * Vérifie la diagonal sur le plan X de la case fait ganer la partie
     * 
     * @param coup l'id de la case du dernier coup
     *             ex : "a1" "ez69"
     * @return true si la grille est gagnante, false sinon
     */
    private boolean verifieDiagPlanX(String coup) {
        ArrayList<int[]> alignement = new ArrayList<int[]>();

        boolean aligne = true;
        boolean gagnante = false;
        int i = 0;
        int[] coord = getCoordonneesCase(coup);
        int coordX = coord[2];

        // z==y
        if (coord[0] == coord[1]) {
            String val = this.grille[0][0][coordX].getValeur();
            do {
                // ajout du coup pour la soluce gagnante
                int[] coupAlignement = { i, i, coordX };
                alignement.add(coupAlignement);

                aligne = val == this.grille[i][i][coordX].getValeur();
                i++;
            } while (i < this.taille && aligne);
            gagnante = aligne;
        }
        if (gagnante) {
            mettreEnValeur(alignement);
            return true;
        } else {
            alignement.clear();
        }

        // z+y=t-1
        if (coord[0] + coord[1] == this.taille - 1) {
            i = 0;
            String val = this.grille[0][this.taille - 1][coordX].getValeur();
            do {
                // ajout du coup pour la soluce gagnante
                int[] coupAlignement = { i, taille - 1 - i, coordX };
                alignement.add(coupAlignement);

                aligne = val == this.grille[i][taille - 1 - i][coordX].getValeur();
                i++;
            } while (i < this.taille && aligne);
            gagnante = aligne;
        }

        if (gagnante)
            mettreEnValeur(alignement);
        return gagnante;
    }

    /***
     * Vérifie la diagonal sur le plan Y de la case fait ganer la partie
     * 
     * @param coup l'id de la case du dernier coup
     *             ex : "a1" "ez69"
     * @return true si la grille est gagnante, false sinon
     */
    private boolean verifieDiagPlanY(String coup) {
        ArrayList<int[]> alignement = new ArrayList<int[]>();

        boolean aligne = true;
        boolean gagnante = false;
        int i = 0;
        int[] coord = getCoordonneesCase(coup);
        int coordY = coord[1];

        // x==z
        if (coord[0] == coord[2]) {
            String val = this.grille[0][coordY][0].getValeur();
            do {
                // ajout du coup pour la soluce gagnante
                int[] coupAlignement = { i, coordY, i };
                alignement.add(coupAlignement);

                aligne = val == this.grille[i][coordY][i].getValeur();
                i++;
            } while (i < this.taille && aligne);
            gagnante = aligne;
        }
        if (gagnante) {
            mettreEnValeur(alignement);
            return true;
        } else {
            alignement.clear();
        }

        // x+z=t-1
        if (coord[0] + coord[1] == this.taille - 1) {
            i = 0;
            String val = this.grille[0][coordY][this.taille - 1].getValeur();
            do {
                // ajout du coup pour la soluce gagnante
                int[] coupAlignement = { i, coordY, taille - 1 - i };
                alignement.add(coupAlignement);

                aligne = val == this.grille[i][coordY][taille - 1 - i].getValeur();
                i++;
            } while (i < this.taille && aligne);
            gagnante = aligne;
        }

        if (gagnante)
            mettreEnValeur(alignement);
        return gagnante;
    }

    /***
     * Vérifie la diagonal sur le plan Z de la case fait ganer la partie
     * 
     * @param coup l'id de la case du dernier coup
     *             ex : "a1" "ez69"
     * @return true si la grille est gagnante, false sinon
     */
    private boolean verifieDiagPlanZ(String coup) {
        ArrayList<int[]> alignement = new ArrayList<int[]>();

        boolean aligne = true;
        boolean gagnante = false;
        int i = 0;
        int[] coord = getCoordonneesCase(coup);
        int coordZ = coord[0];

        // y==x
        if (coord[1] == coord[2]) {
            String val = this.grille[coordZ][0][0].getValeur();
            do {
                // ajout du coup pour la soluce gagnante
                int[] coupAlignement = { coordZ, i, i };
                alignement.add(coupAlignement);

                aligne = val == this.grille[coordZ][i][i].getValeur();
                i++;
            } while (i < this.taille && aligne);
            gagnante = aligne;
        }
        if (gagnante) {
            mettreEnValeur(alignement);
            return true;
        } else {
            alignement.clear();
        }

        // z+y=t-1
        if (coord[0] + coord[1] == this.taille - 1) {
            i = 0;
            String val = this.grille[coordZ][0][this.taille - 1].getValeur();
            do {
                // ajout du coup pour la soluce gagnante
                int[] coupAlignement = { coordZ, i, taille - 1 - i };
                alignement.add(coupAlignement);

                aligne = val == this.grille[coordZ][i][taille - 1 - i].getValeur();
                i++;
            } while (i < this.taille && aligne);
            gagnante = aligne;
        }

        if (gagnante)
            mettreEnValeur(alignement);
        return gagnante;
    }

    /***
     * Vérifie si la ligne de la case fait ganer la partie
     * 
     * @param coup l'id de la case du dernier coup
     *             ex : "a1" "ez69"
     * @return true si la grille est gagnante, false sinon
     */
    private boolean verifieX(String coup) {
        ArrayList<int[]> alignement = new ArrayList<int[]>();

        boolean aligne = true;
        int i = 0;
        int YFixe = getYCase(coup);
        int ZFixe = getZCase(coup);
        String val = this.grille[ZFixe][YFixe][0].getValeur();
        do {
            // ajout du coup pour la soluce gagnante
            int[] coupAlignement = { ZFixe, YFixe, i };
            alignement.add(coupAlignement);

            aligne = val.equals(this.grille[ZFixe][YFixe][i].getValeur());
            i++;
        } while (i < this.taille && aligne);

        if (aligne)
            mettreEnValeur(alignement);
        return aligne;
    }

    /***
     * Vérifie si la colone de la case fait ganer la partie
     * 
     * @param coup l'id de la case du dernier coup
     *             ex : "a1" "ez69"
     * @return true si la grille est gagnante, false sinon
     */
    private boolean verifieY(String coup) {
        ArrayList<int[]> alignement = new ArrayList<int[]>();

        boolean aligne = true;
        int i = 0;
        int XFixe = getXCase(coup);
        int ZFixe = getZCase(coup);
        String val = this.grille[ZFixe][0][XFixe].getValeur();

        do {
            // ajout du coup pour la soluce gagnante
            int[] coupAlignement = { ZFixe, i, XFixe };
            alignement.add(coupAlignement);

            aligne = val.equals(this.grille[ZFixe][i][XFixe].getValeur());
            i++;
        } while (i < this.taille && aligne);
        if (aligne)
            mettreEnValeur(alignement);
        return aligne;
    }

    /***
     * Vérifie si la profondeur de la case fait ganer la partie
     * 
     * @param coup l'id de la case du dernier coup
     *             ex : "a1" "ez69"
     * @return true si la grille est gagnante, false sinon
     */
    private boolean verifieZ(String coup) {
        ArrayList<int[]> alignement = new ArrayList<int[]>();

        boolean aligne = true;
        int i = 0;
        int YFixe = getYCase(coup);
        int XFixe = getXCase(coup);
        String val = this.grille[0][YFixe][XFixe].getValeur();

        do {
            // ajout du coup pour la soluce gagnante
            int[] coupAlignement = { i, YFixe, XFixe };
            alignement.add(coupAlignement);

            aligne = val.equals(this.grille[i][YFixe][XFixe].getValeur());
            i++;
        } while (i < this.taille && aligne);
        if (aligne)
            mettreEnValeur(alignement);
        return aligne;
    }

    /***
     * 
     * @param coup l'id de la case du dernier coup
     *             ex : "a1" "ez69"
     * @return l'abscice du coup
     */
    private int getXCase(String coup) {
        return getCoordonneesCase(coup)[2];
    }

    /***
     * 
     * @param coup l'id de la case du dernier coup
     *             ex : "a1" "ez69"
     * @return l'ordonnée du coup
     */
    private int getYCase(String coup) {
        return getCoordonneesCase(coup)[1];
    }

    /***
     * 
     * @param coup l'id de la case du dernier coup
     *             ex : "a1" "ez69"
     * @return la profondeur du coup
     */
    private int getZCase(String coup) {
        return getCoordonneesCase(coup)[0];
    }

    /***
     * Vérification de la validité du coup joué par le joueur
     * (au niveau de la syntaxe et de la possibilité de le jouer dans la grille)
     * 
     * @param coup l'id de la case
     *             ex : "a1" "ez69"
     * @return true si le coup est valide, false sinon
     */
    public boolean verifierCoup(String coup) {
        if (!verifierInput(coup)) {
            return false;
        } else {
            int coord[] = getCoordonneesCase(coup);

            // On regarde si les coordonnées données sont valides (dans le tableau)
            if (coord[0] < 0 || coord[0] >= this.taille || coord[1] < 0 || coord[1] >= this.taille || coord[2] < 0
                    || coord[2] >= this.taille) {
                System.out.println("ERREUR ! Le numéro de case " + coup + " est invalide");
                return false;
            }
            // On regarde si la case choisi est libre
            else if (this.grille[coord[0]][coord[1]][coord[2]].estVide()) {
                return true;
            } else {
                System.out.println("ERREUR ! La case " + coup + " est déjà pleine");
                return false;
            }
        }
    }

    /***
     * Place un symbole de joueur à la case dont l'id est coordonee si c'est
     * possible
     * 
     * @param joueur    1 ou 2, pour joueur 1 ou joueur 2
     * @param coordonee les coordonnées de la case sous forme [y,x]
     */
    public void jouerCoup(int joueur, int[] coordonee) {
        String[] symbole = { "X", "O" };
        this.grille[coordonee[0]][coordonee[1]][coordonee[2]].setValeur(symbole[joueur - 1]);
    }

    /***
     * Permet de valider un coup avec Entrée ou de choisir un autre coup
     * 
     * @param input l'input saisie par le joueur
     * @return true si l'input est valide, false sinon
     */
    public boolean verifierInput(String input) {
        // On vérifie que l'input ne soit pas vide
        if (input.length() <= 0) {
            System.out.println("ERREUR ! L'input est vide");
            return false;
        }

        String lettres = "";
        int numero = 0;
        int z = 0;

        // On récupère toute les lettres dans une nouvelle String
        int i = 0;
        while (i < input.length() && !Character.isDigit(input.charAt(i))) {
            lettres = lettres + input.charAt(i);
            i++;
        }

        // i = nombre de lettre
        for (int j = 0; j < i; j++) {
            z = z * 26;
            z += lettres.charAt(j) - 97;
        }

        // On vérifie que la profondeur indiquée par le groupe de lettre soit dans le
        // tableau
        if (z < 0 || z > taille) {
            System.out.println("ERREUR ! Les lettres " + lettres + " ne sont invalides");
            return false;
        }

        // On récupère tous les chiffres qu'on transforme en entier
        while (i < input.length()) {
            numero = numero * 10;
            // On vérifie que la fin de la chaine ne contient pas de lettre
            if (Character.isDigit(input.charAt(i))) {
                numero += Integer.valueOf(input.charAt(i)) - 48;
            } else {
                System.out.println("ERREUR ! Il y a la lettres " + input.charAt(i) + " au milieu des chiffres");
                return false;
            }
            i++;
        }

        // On vérifie que le chiffre correspond à une case
        if (numero > this.taille * this.taille || numero <= 0) {
            System.out.println("ERREUR ! Le numéro " + numero + " n'est pas une case du tableau");
            return false;
        }

        return true;
    }

    // TODO
    /***
     * Permet de valider un coup avec Entrée ou de choisir un autre coup
     * 
     * @param input   le coup saisi par le joueur
     *                ex : "a16", "b8"
     * 
     * @param scanner permet de récupérer les inputs de l'utilisateur
     * 
     * @return String du choix du joueur : une chaine vide pour valider le coup
     *         ou un input pour faire un autre coup
     */
    public String validerCoup(String input, Scanner scanner) {
        /*
         * System.out.println();
         * System.out.
         * println("Appuyer sur Entrée pour valider votre coup, ou entrez un autre coup"
         * );
         * int x = getXCase(input);
         * int y = getYCase(input);
         * grille[y][x].setSelectionnee(true);
         * afficher();
         * String choix = scanner.nextLine();
         * if (choix != "") grille[y][x].setSelectionnee(false);
         * return choix;
         */
        return "";
    }

    /***
     * Un test unitaire de toutes les fonctionnalités de Grille3D
     */
    public void testRegretion() {
        System.out.println("Test Regression pour une grille3D 3*3*3");

        // verifierInput
        System.out.println("Faux : " + verifierInput("a"));
        System.out.println("Faux : " + verifierInput("1"));
        System.out.println("Faux : " + verifierInput("aa"));
        System.out.println("Faux : " + verifierInput("11"));
        System.out.println("Faux : " + verifierInput(""));
        System.out.println("Faux : " + verifierInput("a100"));
        System.out.println("Faux : " + verifierInput("f1"));
        System.out.println("Faux : " + verifierInput("e0"));
        System.out.println("Faux : " + verifierInput("a0"));
        System.out.println("Faux : " + verifierInput("a1e"));
        System.out.println("Faux : " + verifierInput("1a"));
        System.out.println("Vrai : " + verifierInput("a1"));
        System.out.println("Vrai : " + verifierInput("b3"));
        System.out.println("Vrai : " + verifierInput("c9"));
        System.out.println("");
        System.out.println("");

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
        System.out.println("z=2, y=0, x=2");
        c = getCoordonneesCase("c3");
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

        /*
         * // afficher et placer
         * placer("X", "a1");
         * placer("O", "a5");
         * placer("X", "a9");
         * placer("X", "b9");
         * placer("X", "c2");
         * this.grille[0][2][0].setSelectionnee(true);
         * System.out.println("Une grille comme suis :");
         * System.out.println("    (a)                (b)                (c)");
         * System.out.println("| X  2  3 |        | 1  2  3 |        | 1  X  3 |");
         * System.out.println("| 4  O  6 |        | 4  5  6 |        | 4  5  6 |");
         * System.out.println("|>7< 8  X |        | 7  8  X |        | 7  8  9 |");
         * System.out.println("");
         * afficher();
         * System.out.println("");
         * System.out.println("");
         */

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

        // Test vérification des victoires
        // diag haute gauche
        viderGrille();
        placer("X", "a1");
        placer("X", "b5");
        placer("X", "c9");
        afficher();
        System.out.println("Grille gagnante : " + grilleGagnante("b5"));
        System.out.println("Diagonale : " + verifieDiagonale("b5"));

        System.out.println("Diagonale plan X : " + verifieDiagPlanX("b5"));
        System.out.println("Diagonale plan Y : " + verifieDiagPlanY("b5"));
        System.out.println("Diagonale plan Z : " + verifieDiagPlanZ("b5"));
        System.out.println("X : " + verifieX("b5"));
        System.out.println("Y : " + verifieY("b5"));
        System.out.println("Z : " + verifieZ("b5"));
        System.out.println("=============================");
        System.out.println("=============================");

        // diag haute droite
        viderGrille();
        System.out.println("Diagonale Haute Droite");
        placer("X", "a3");
        placer("X", "b5");
        placer("X", "c7");
        afficher();
        System.out.println("Grille gagnante : " + grilleGagnante("b5"));
        System.out.println("Diagonale : " + verifieDiagonale("b5"));
        System.out.println("=============================");
        System.out.println("=============================");

        // diag basse gauche
        viderGrille();
        System.out.println("Diagonale Basse Gauche");
        placer("X", "a7");
        placer("X", "b5");
        placer("X", "c3");
        afficher();
        System.out.println("Grille gagnante : " + grilleGagnante("b5"));
        System.out.println("Diagonale : " + verifieDiagonale("b5"));
        System.out.println("=============================");
        System.out.println("=============================");

        // diag basse droite
        viderGrille();
        System.out.println("Diagonale Basse Droite");
        placer("X", "a3");
        placer("X", "b5");
        placer("X", "c7");
        afficher();
        System.out.println("Grille gagnante : " + grilleGagnante("b5"));
        System.out.println("Diagonale : " + verifieDiagonale("b5"));

        System.out.println("=============================");
        System.out.println("=============================");

        // DiagPlanX:Y
        viderGrille();
        System.out.println("Diagonale Plan x:y");
        placer("X", "a1");
        placer("X", "a5");
        placer("X", "a9");
        afficher();
        System.out.println("Grille gagnante : " + grilleGagnante("a5"));
        System.out.println("Diagonale : " + verifieDiagonale("a5"));

        System.out.println("Diagonale plan X : " + verifieDiagPlanX("a5"));
        System.out.println("Diagonale plan Y : " + verifieDiagPlanY("a5"));
        System.out.println("Diagonale plan Z : " + verifieDiagPlanZ("a5"));
        System.out.println("X : " + verifieX("a5"));
        System.out.println("Y : " + verifieY("a5"));
        System.out.println("Z : " + verifieZ("a5"));

        System.out.println("=============================");
        System.out.println("=============================");
        // DiagPlanY:Z
        viderGrille();
        System.out.println("Diagonale plan y:z");
        placer("X", "a1");
        placer("X", "b4");
        placer("X", "c7");
        afficher();
        System.out.println("Grille gagnante : " + grilleGagnante("b4"));
        // System.out.println("Diagonale : "+ verifieDiagonale("b4"));

        System.out.println("Diagonale plan X : " + verifieDiagPlanX("b4"));
        System.out.println("Diagonale plan Y : " + verifieDiagPlanY("b4"));
        System.out.println("Diagonale plan Z : " + verifieDiagPlanZ("b4"));

        System.out.println("=============================");
        System.out.println("=============================");

        // DiagPlanX:Z
        viderGrille();
        System.out.println("Diagonale plan x:z");
        placer("X", "a1");
        placer("X", "b2");
        placer("X", "c3");
        afficher();
        System.out.println("Grille gagnante : " + grilleGagnante("b2"));
        // System.out.println("Diagonale : "+ verifieDiagonale("b4"));

        System.out.println("Diagonale plan X : " + verifieDiagPlanX("b2"));
        System.out.println("Diagonale plan Y : " + verifieDiagPlanY("b2"));
        System.out.println("Diagonale plan Z : " + verifieDiagPlanZ("b2"));
        System.out.println("X : " + verifieX("b2"));
        System.out.println("Y : " + verifieY("b2"));
        System.out.println("Z : " + verifieZ("b2"));

        System.out.println("=============================");
        System.out.println("=============================");

        // System.out.println("Diagonale plan X"+ verifieDiagPlanX("b5"));
        // Ligne
        viderGrille();
        System.out.println("Ligne");
        placer("X", "a1");
        placer("X", "a2");
        placer("X", "a3");
        afficher();
        System.out.println("Grille gagnante : " + grilleGagnante("a2"));
        // System.out.println("Diagonale : "+ verifieDiagonale("b4"));

        System.out.println("Diagonale plan X : " + verifieDiagPlanX("a2"));
        System.out.println("Diagonale plan Y : " + verifieDiagPlanY("a2"));
        System.out.println("Diagonale plan Z : " + verifieDiagPlanZ("a2"));
        System.out.println("Ligne : " + verifieX("a2"));
        System.out.println("Colonne : " + verifieY("a2"));
        System.out.println("Pronfodeur : " + verifieZ("a2"));
        System.out.println("=============================");
        System.out.println("=============================");

        // Colonne
        viderGrille();
        System.out.println("Colonne");
        placer("X", "a1");
        placer("X", "a4");
        placer("X", "a7");
        afficher();
        System.out.println("Grille gagnante : " + grilleGagnante("a4"));
        System.out.println("Ligne : " + verifieX("a4"));
        System.out.println("Colonne : " + verifieY("a4"));
        System.out.println("Pronfodeur : " + verifieZ("a4"));
        System.out.println("=============================");
        System.out.println("=============================");

        // Profondeur
        viderGrille();
        System.out.println("Profondeur");
        placer("X", "a2");
        placer("X", "b2");
        placer("X", "c2");
        afficher();
        System.out.println("Grille gagnante : " + grilleGagnante("a2"));
        System.out.println("Ligne : " + verifieX("a2"));
        System.out.println("Colonne : " + verifieY("a2"));
        System.out.println("Pronfodeur : " + verifieZ("a2"));
        System.out.println("=============================");
        System.out.println("=============================");

        System.out.println("=============================");

    }

    /***
     * Renvoie les lettres de l'id d'une case qui a pour profondeur nb
     * ex : 3 donne 'c' et 30 donne 'ad'
     * 
     * @param nb la profondeur de la case
     * @return les lettres de l'id de la case
     */
    private String getLettre(int nb) {
        if (nb >= 0) {
            if (nb / 26 == 0)
                return String.valueOf((char) (nb + 'a'));
            else
                return String.valueOf((char) (nb / 26 - 1 + 'a')) + String.valueOf((char) (nb % 26 + 'a'));
        } else
            return null;
    }

    /***
     * Renvoie les coordonnées correspondant à un numéro de case
     * 
     * 
     * 
     */
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
            numero += Integer.valueOf(numeroCase.charAt(i)) - 48;
            i++;
        }

        coord[1] = (numero - 1) / this.taille;
        coord[2] = (numero - 1) % this.taille;
        return coord;
    }
}