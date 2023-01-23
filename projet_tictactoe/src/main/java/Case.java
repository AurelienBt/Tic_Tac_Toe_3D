public class Case {

    private String valeur;
    private boolean estSelectionnee;
    private boolean estVide;

    public Case() {
        this.valeur = "";
        this.estSelectionnee = false;
        this.estVide = true;
    }

    public boolean estVide() {
        return this.estVide;
    }

    public void setSelectionnee(boolean select) {
        this.estSelectionnee = select;
    }

    public String getValeur() {
        return this.valeur;
    }

    public void setValeur(String val) {
        this.valeur = val;
    }
    
}
