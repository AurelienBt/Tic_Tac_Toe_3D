package apo.boissot_laqueuvre_moulon;

public class Case {

    private String valeur;
    private boolean estSelectionnee;
    private boolean estVide;

    public Case() {
        this.valeur = "";
        this.estSelectionnee = false;
        this.estVide = true;
    }

    public Case(int val) {
        this.valeur = Integer.toString(val);
        this.estSelectionnee = false;
        this.estVide = true;
    }

    public boolean estVide() {
        return this.estVide;
    }

    public void setSelectionnee(boolean select) {
        this.estSelectionnee = select;
    }

    public String getValeur(double tailleMax) {
        String surlignageGauche = ">";
        String surlignageDroit = "<";
        String espace = " ";

        String espaceAvant = "";
        String espaceApres = "";

        for (int i = 0; i < tailleMax - this.valeur.length(); i++) {
            if (i % 2 == 0) {
                espaceApres = espaceApres + espace;
            } else {
                espaceAvant = espaceAvant + espace;
            }
        }

        if (estSelectionnee) {
            return espaceAvant + surlignageGauche + this.valeur + surlignageDroit + espaceApres;
        } else {
            return espaceAvant + espace + this.valeur + espace + espaceApres;
        }
    }

    public void setValeur(String val) {
        this.valeur = val;
        this.estVide = false;
    }

}
