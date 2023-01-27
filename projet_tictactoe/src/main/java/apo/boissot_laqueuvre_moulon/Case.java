package apo.boissot_laqueuvre_moulon;

/***
 * La classe Case modélise les cases d'une grille de morpion.
 * 
 */
public class Case {

    private String valeur;
    private boolean estSelectionnee;
    private boolean estVide;

    public Case() {
        this.valeur = "";
        this.estSelectionnee = false;
        this.estVide = true;
    }

    /***
     * 
     * @param val contenue de la case à la création, un entier qui représente l'id
     *            de la case pour que le joueur puisse la choisir
     */
    public Case(int val) {
        this.valeur = Integer.toString(val);
        this.estSelectionnee = false;
        this.estVide = true;
    }

    /***
     * 
     * @return true si la case est vide, false sinon
     */
    public boolean estVide() {
        return this.estVide;
    }

    /***
     * Permet de (dé)sélectionner une Case. Une Case sélectionnée sera mis en valeur
     * lors de l'affichage :
     * >X< au lieu de X
     * Utile pour montrer la case choisi par le joueur ou la combinaison gagnante
     * 
     * @param select true pour sélectionner la case, false pour la désélectionner
     */
    public void setSelectionnee(boolean select) {
        this.estSelectionnee = select;
    }

    /***
     * 
     * @return La valeur brut de la case, sans formatage ni mise en valeur
     */
    public String getValeur() {
        return this.valeur;
    }

    /***
     * 
     * @param tailleMax La longueur (en nombre de caractère) du plus grand numéro de
     *                  case
     * @return Une String prête à être affiché avec le bon forma et la mise en
     *         valeur des cases séléctionnées
     */
    public String afficher(double tailleMax) {
        String surlignageGauche = ">";
        String surlignageDroit = "<";
        String espace = " ";

        String espaceAvant = "";
        String espaceApres = "";

        // On calcule combien d'espace au'il faut
        for (int i = 0; i < tailleMax - this.valeur.length(); i++) {
            if (i % 2 == 0) {
                espaceApres = espaceApres + espace;
            } else {
                espaceAvant = espaceAvant + espace;
            }
        }

        // On met en avant la case si elle est séléctionnée
        if (estSelectionnee) {
            return espaceAvant + surlignageGauche + this.valeur + surlignageDroit + espaceApres;
        } else {
            return espaceAvant + espace + this.valeur + espace + espaceApres;
        }
    }

    /***
     * Change la valeur de la Case
     * 
     * @param val
     */
    public void setValeur(String val) {
        this.valeur = val;
        this.estVide = false;
    }
}