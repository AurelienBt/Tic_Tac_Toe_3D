package apo.boissot_laqueuvre_moulon;

import java.util.ArrayList;
import java.util.Scanner;

/***
 * Grille2D extends Grille
 * Représente une grille de morpion en 2 dimensions, de taille variable
 */
public class Grille2D extends Grille {

    /***
     * Tableau de Case de deux dimensions pour contenir la grille de jeu
     */
    private Case grille[][];

    /***
     * Constructeur de la classe Grille2D, initialisant la grille avec des nombres
     * 
     * @param taille
     */
    public Grille2D(int taille) {
        super(taille);

        viderGrille();
    }

    /***
     * Vide la grille en remplaçant toutes les cases par des cases vides (avec leur
     * numéro et pas des "X" "O")
     */
    public void viderGrille() {
        this.grille = new Case[taille][taille];

        Case c;

        for (int i = 0; i < this.taille; i++) {
            for (int j = 0; j < this.taille; j++) {
                c = new Case(i * this.taille + j + 1);
                this.grille[i][j] = c;
            }
        }
    }

    /***
     * Affiche la grille dans la console
     */
    public void afficher() {
        System.out.println();
        for (int i = 0; i < this.taille; i++) {
            System.out.print("|");
            for (int j = 0; j < this.taille; j++) {
                System.out.print(this.grille[i][j].afficher(tailleEntier(this.taille * this.taille)));
            }
            System.out.println("|");
        }
        System.out.println();
    }

    /***
     * Renvoie la case corresondant à une coordonnée passée en paramètre
     * @param coord Coordonnée dont on veut obtenir la case
     * @return Case de la grille correspondant aux coordonnées
     */
    public Case getCase(int[] coord){
        return (this.grille[coord[0]][coord[1]]);
    }

    /***
     * Copie dans la grille actuelle la grille passée en paramètre
     * @param tmp grille dont on va copier les valeurs
     */
    public void copierGrille(Grille tmp){
        int[] coord = new int[2];
        for (int i = 0; i < this.taille; i++) {
            for (int j = 0; j < this.taille; j++) {
                coord[0] = i;
                coord[1] = j;
                Case tmpCase = new Case (tmp.getCase(coord));
                this.grille[i][j] = tmpCase;
            }
        }
    }

    /***
     * Renvoie true puisque la grille est en 2D
     */
    public boolean is2D(){
        return true;
    }

    /***
     * Vérification de la validité du coup joué par le joueur
     * (au niveau de la syntaxe et de la possibilité de le jouer dans la grille)
     * 
     * @param coup l'id de la case
     *             ex : "1" "69"
     * @return true si le coup est valide, false sinon
     */
    public boolean verifierCoup(String coup) {
        if (!verifierInput(coup)) {
            return false;
        } else {
            int numeroCase = Integer.valueOf(coup);
            int x = getXCase(coup);
            int y = getYCase(coup);

            // On regarde si les coordonnées données sont valides (dans le tableau)
            if (x < 0 || x >= this.taille || y < 0 || y >= this.taille) {
                System.out.println("ERREUR ! Le numéro de case " + numeroCase + " est invalide");
                return false;
            }
            // On regarde si la case choisie est libre
            else if (this.grille[y][x].estVide()) {
                return true;
            } else {
                System.out.println("ERREUR ! La case " + numeroCase + " est déjà pleine");
                return false;
            }
        }
    }

    /***
     * Permet de valider un coup avec Entrée ou de choisir un autre coup
     * 
     * @param coup    le coup saisi par le joueur
     *                ex : "16", "8"
     * 
     * @param scanner permet de récupérer les inputs de l'utilisateur
     * 
     * @return String du choix du joueur : une chaine vide pour valider le coup
     *         ou un input pour faire un autre coup
     */
    public String validerCoup(String coup, Scanner scanner) {
        System.out.println();
        System.out.println("Appuyer sur Entrée pour valider votre coup, ou entrez un autre coup");
        int x = getXCase(coup);
        int y = getYCase(coup);
        grille[y][x].setSelectionnee(true);
        afficher();
        String choix = scanner.nextLine();
        grille[y][x].setSelectionnee(false);
        return choix;
    }

    /***
     * Place un symbole de joueur à la case numeroCase si c'est possible
     * 
     * @param joueur     le symbole du joueur
     *                   ex : "O" , "X" ou autre
     * @param numeroCase l'ID de la case
     *                   ex : "3" "19"
     */
    public void placer(String joueur, String numeroCase) {
        if (verifierCoup(numeroCase)) {
            this.grille[getYCase(numeroCase)][getXCase(numeroCase)].setValeur(joueur);
        }
    }

    /***
     * Renvoie la liste des coup possibles
     * 
     * @return tableau contenant la liste des coups valides possible
     *         chaque coup est représenté par un tableau de 2 entiers [y,x]
     */
    public ArrayList<int[]> listerCoupPossible() {
        ArrayList<int[]> coupPossible = new ArrayList<int[]>();


        for (int i = 0; i < this.taille; i++) {
            for (int j = 0; j < this.taille; j++) {
                if (this.grille[i][j].estVide()) {
                    int c[] = { -1, -1 };
                    c[0] = i;
                    c[1] = j;
                    coupPossible.add(c);
                }
            }
        }
        return coupPossible;
    }

    /***
     * Vérifie que la syntaxe de l'input proposé par le joueur est correcte
     * 
     * @param input l'input saisi par le joueur
     * @return true si l'input est valide, false sinon
     */
    public boolean verifierInput(String input) {
        // On vérifie que l'input ne soit pas vide
        if (input.length() <= 0) {
            System.out.println("ERREUR ! L'input est vide");
            return false;
        }

        // On vérifie que l'input ne contient que des chiffres
        for (int i = 0; i < input.length(); i++) {
            if (!Character.isDigit(input.charAt(i))) {
                System.out.println("ERREUR ! L'input " + input + " n'est pas un chiffre");
                return false;
            }
        }
        // On vérifie que le chiffre correspond à une case
        if (Integer.valueOf(input) > this.taille * this.taille || Integer.valueOf(input) <= 0) {
            System.out.println("ERREUR ! L'input " + input + " n'est pas une case du tableau");
            return false;
        } else {
            return true;
        }
    }

    /***
     * Vérifie si la grille est pleine ou non
     * 
     * @return true si la grille est pleine, false sinon
     */
    public boolean grilleEstPleine() {
        for (int i = 0; i < this.taille; i++) {
            for (int j = 0; j < this.taille; j++) {
                if (grille[i][j].estVide())
                    return false;
            }
        }
        return true;
    }

    /***
     * Vérifie si la grille est gagnante. Pour alléger les calculs on regarde
     * uniquement en fonction du dernier coup joué
     * 
     * @param coup l'id de la case du dernier coup
     *             ex : "3" "89"
     * @return true si la grille est gagnante, false sinon
     */
    public boolean grilleGagnante(String coup) {
        if (verifieDiagonale(coup))
            return true;
        if (verifieX(coup))
            return true;
        if (verifieY(coup))
            return true;

        return false;
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
        this.grille[coordonee[0]][coordonee[1]].setValeur(symbole[joueur - 1]);
    }

    /***
     * Vérifie si une case appartient à une diagonale, le cas échéant il vérifie si
     * la diagonale associée fait gagner la partie
     * 
     * @param coup l'id de la case
     * @return true si la case appartient à une diagonale qui fait gagner la partie,
     *         false sinon
     */
    private boolean verifieDiagonale(String coup) {
        ArrayList<int[]> alignement = new ArrayList<int[]>();

        boolean gagnante = false;
        boolean aligne = true;
        int i = 0;
        int[] coord = convertCoup(coup);
        // Diagonale numéro 1
        if (coord[0] == coord[1]) {
            String val = this.grille[0][0].getValeur();
            do {
                aligne = val == this.grille[i][i].getValeur();

                // ajout du coup pour la soluce gagnante
                int[] coupAlignement = { i, i };
                alignement.add(coupAlignement);

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

        // Diagonale numéro 2
        i = 0;
        if (coord[0] + coord[1] == this.taille - 1) {
            String val = this.grille[0][this.taille - 1].getValeur();

            do {
                aligne = val == this.grille[i][taille - 1 - i].getValeur();

                // ajout du coup pour la soluce gagnante
                int[] coupAlignement = { i, taille - 1 - i };
                alignement.add(coupAlignement);

                i++;
            } while (i < this.taille && aligne);
            gagnante = aligne;
        }
        if (gagnante) {
            mettreEnValeur(alignement);
            return true;
        } else {
            return false;
        }
    }

    /***
     * Sélectionne les cases passé en paramètre
     * 
     * @param alignement un array de int[2] contenant la liste des coordonées des
     *                   cases à mettre en valeur
     */
    protected void mettreEnValeur(ArrayList<int[]> alignement) {
        for (int[] coup : alignement) {
            this.grille[coup[0]][coup[1]].setSelectionnee(true);
        }
    }

    /***
     * Vérifie si la colone de la case fait ganer la partie
     * 
     * @param coup l'id de la case
     * @return true si la colone de la case fait gagner la partie,
     *         false sinon
     */
    private boolean verifieY(String coup) {
        boolean aligne = true;
        int i = 0;
        int coordFixe = getYCase(coup);

        ArrayList<int[]> alignement = new ArrayList<int[]>();

        String val = this.grille[coordFixe][0].getValeur();
        do {
            // ajout du coup pour la soluce gagnante
            int[] coupAlignement = { coordFixe, i };
            alignement.add(coupAlignement);

            aligne = val.equals(this.grille[coordFixe][i].getValeur());
            i++;
        } while (i < this.taille && aligne);

        if (aligne) {
            mettreEnValeur(alignement);
        }
        return aligne;
    }

    /***
     * Vérifie si la ligne dont la case fait partie fait gagner la partie
     * 
     * @param coup l'id de la case
     * @return true si la ligne de la case fait gagner la partie,
     *         false sinon
     */
    private boolean verifieX(String coup) {
        boolean aligne = true;
        int i = 0;
        int coordFixe = getXCase(coup);

        ArrayList<int[]> alignement = new ArrayList<int[]>();

        String val = this.grille[0][coordFixe].getValeur();
        do {
            // ajout du coup pour la soluce gagnante
            int[] coupAlignement = { i, coordFixe };
            alignement.add(coupAlignement);

            aligne = val.equals(this.grille[i][coordFixe].getValeur());
            i++;
        } while (i < this.taille && aligne);

        if (aligne) {
            mettreEnValeur(alignement);
        }
        return aligne;
    }

    /***
     * Transforme l'id d'une case en ses coordonnées x et y
     * 
     * @param coup l'id de la case (ex: "6", "12")
     * @return un tableau de 2 entiers [x, y]
     */
    private int[] convertCoup(String coup) {
        int[] tmp = new int[2];
        int c = Integer.valueOf(coup) - 1;
        int x = c % this.taille;
        int y = c / this.taille;
        tmp[0] = x;
        tmp[1] = y;
        return tmp;
    }

    /***
     * Renvoie la ligne à laquelle appartient la case
     * 
     * @param numeroCase l'id de la case
     * @return l'ordonnée de la case
     */
    private int getYCase(String numeroCase) {
        int numero = Integer.valueOf(numeroCase) - 1;
        int y = numero / this.taille;
        return y;
    }

    /***
     * Renvoie la colonne à laquelle appartient la case
     * 
     * @param numeroCase l'id de la case
     * @return l'abscisse de la case
     */
    private int getXCase(String numeroCase) {
        int numero = Integer.valueOf(numeroCase) - 1;
        int x = numero % this.taille;
        return x;
    }

    /***
     * Un test unitaire de toutes les fonctionnalités de Grille2D
     */
    public void testRegretion() {
        System.out.println("Test Regression pour une grille2D 3*3");

        // afficher
        System.out.println("Une grille vide :");
        afficher();
        System.out.println("");
        System.out.println("");

        // verifierInput
        System.out.println("Faux : " + verifierInput("a"));
        System.out.println("Faux : " + verifierInput("2a"));
        System.out.println("Faux : " + verifierInput(""));
        System.out.println("Faux : " + verifierInput("111"));
        System.out.println("Faux : " + verifierInput("0"));
        System.out.println("Faux : " + verifierInput("-5"));
        System.out.println("Vrai : " + verifierInput("1"));
        System.out.println("Vrai : " + verifierInput("6"));
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
        this.grille[0][2].setSelectionnee(true);
        System.out.println("Une grille comme suit :");
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

        viderGrille();
        // Verifier Diagonale
        placer("X", "1");
        placer("X", "5");
        placer("X", "9");
        afficher();
        System.out.println("Résultat attendu : ");
        System.out.println("Diagonale : True");
        System.out.println("Ligne : false");
        System.out.println("Colonne : false");
        System.out.println("---------------------");
        System.out.println("Diagonale : " + this.verifieDiagonale("9"));
        System.out.println("Ligne : " + this.verifieY("9"));
        System.out.println("Colonne : " + this.verifieX("9"));
        System.out.println("Grille gagnante : " + this.grilleGagnante("9"));
        System.out.println("=======================");
        System.out.println("=======================");

        viderGrille();

        // Verifier Diagonale inverse
        placer("X", "3");
        placer("X", "5");
        placer("X", "7");
        afficher();
        System.out.println("Résultat attendu : ");
        System.out.println("Diagonale : True");
        System.out.println("Ligne : false");
        System.out.println("Colonne : false");
        System.out.println("---------------------");
        System.out.println("Diagonale : " + this.verifieDiagonale("5"));
        System.out.println("Ligne : " + this.verifieY("5"));
        System.out.println("Colonne : " + this.verifieX("5"));
        System.out.println("=======================");
        System.out.println("=======================");

        viderGrille();
        // Verifier ligne
        placer("X", "1");
        placer("X", "2");
        placer("X", "3");
        afficher();
        System.out.println("Résultat attendu : ");
        System.out.println("Diagonale : false");
        System.out.println("Ligne : true");
        System.out.println("Colonne : false");
        System.out.println("---------------------");
        System.out.println("Diagonale : " + this.verifieDiagonale("2"));
        System.out.println("Ligne : " + this.verifieY("2"));
        System.out.println("Colonne : " + this.verifieX("2"));
        System.out.println("=======================");
        System.out.println("=======================");

        viderGrille();
        // Verifier colonne
        placer("X", "1");
        placer("X", "4");
        placer("X", "7");
        afficher();
        System.out.println("Résultat attendu : ");
        System.out.println("Diagonale : false");
        System.out.println("Ligne : true");
        System.out.println("Colonne : false");
        System.out.println("---------------------");
        System.out.println("Diagonale : " + this.verifieDiagonale("1"));
        System.out.println("Ligne : " + this.verifieY("1"));
        System.out.println("Colonne : " + this.verifieX("1"));
        System.out.println("=======================");
        System.out.println("=======================");

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