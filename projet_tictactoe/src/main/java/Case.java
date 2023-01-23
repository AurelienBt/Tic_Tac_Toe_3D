public class Case {

    private String valeur;
    private boolean estSelectionnee;

    public Case() {
        this.valeur = "";
        this.estSelectionnee = false;
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
