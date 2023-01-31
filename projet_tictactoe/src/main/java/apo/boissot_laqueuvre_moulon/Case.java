package apo.boissot_laqueuvre_moulon;

/***
 * La classe Case modélise les cases d'une grille de morpion.
 */
public class Case {

    private String valeur;
    private boolean estSelectionnee;
    private boolean estVide;
    
    /*** 
     * Constructeur de la classe Case initialisant les données membres
    */
    public Case() {
        this.valeur = "";
        this.estSelectionnee = false;
        this.estVide = true;
    }

    /***
     * Constructeur de la classe Case initialisant la case avec une  valeur donnée
     * @param valeur contenu de la case à la création, un entier qui représente
     *               l'id de la case pour que le joueur puisse la choisir
     */
    public Case(int valeur) {
        this.valeur = Integer.toString(valeur);
        this.estSelectionnee = false;
        this.estVide = true;
    }

    /***
     * Vérifie si une grille est vide ou non
     * @return true si la case est vide, false sinon
     */
    public boolean estVide() {
        return this.estVide;
    }

    /***
     * Permet de sélectionner ou déselectionner une Case. 
     * Une Case sélectionnée sera mise en valeur lors de l'affichage : >X< au lieu de X
     * Utile pour montrer la case choisi par le joueur ou la combinaison gagnante
     * 
     * @param select true pour sélectionner la case, false pour la désélectionner
     */
    public void setSelectionnee(boolean select) {
        this.estSelectionnee = select;
    }

    /***
     * Renvoie la valeur de la Case
     * @return La valeur brute de la case, sans formatage ni mise en valeur
     */
    public String getValeur() {
        return this.valeur;
    }

    /***
     * Renvoie un String permettant d'afficehr la case
     * @param tailleMax La longueur (en nombre de caractère) du plus grand numéro de
     *                  case (pour centrer la case)
     * @return Un String prêt à être affiché avec le bon format et la mise en
     *         valeur des cases séléctionnées
     */
    public String afficher(double tailleMax) {
        String surlignageGauche = ">";
        String surlignageDroit = "<";
        String espace = " ";

        String espaceAvant = "";
        String espaceApres = "";

        // On calcule combien d'espaces nécessaire 
        for (int i = 0; i < tailleMax - this.valeur.length(); i++) {
            if (i % 2 == 0) {
                espaceApres = espaceApres + espace;
            } else {
                espaceAvant = espaceAvant + espace;
            }
        }

        // On met en avant la case si elle est sélectionnée
        if (estSelectionnee) {
            return espaceAvant + surlignageGauche + this.valeur + surlignageDroit + espaceApres;
        } else {
            return espaceAvant + espace + this.valeur + espace + espaceApres;
        }
    }

    /***
     * Change la valeur de la Case
     * 
     * @param val Valeur à mettre dans la case
     */
    public void setValeur(String val) {
        this.valeur = val;
        this.estVide = false;
    }
}