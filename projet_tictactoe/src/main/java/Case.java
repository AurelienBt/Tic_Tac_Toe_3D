public class Case {

    private String valeur;
    private boolean estSelectionnee;

    public Case() {
        this.valeur = "";
        this.estSelectionnee = false;
    }

    public Case(int val) {
        this.valeur = Integer.toString(val);
        this.estSelectionnee = false;
    }

    public void setSelectionnee(boolean select) {
        this.estSelectionnee = select;
    }

    public String getValeur() {
        if (estSelectionnee) {
            return ">" + this.valeur + "<";
        } else {
            return " " + this.valeur + " ";
        }
    }

    public void setValeur(String val) {
        this.valeur = val;
    }

}
