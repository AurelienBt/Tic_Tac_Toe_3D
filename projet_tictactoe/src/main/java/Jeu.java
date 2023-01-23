public class Jeu {
    private int modeJeu; 
    private int protagonistes;
    private int quiCommence;
    private Grille grille;
    private int tailleGrille;

    public Jeu(int modeJeu, int protagonistes, int quiCommence, int tailleGrille){
        this.modeJeu = modeJeu;
        this.protagonistes = protagonistes;
        this.quiCommence = quiCommence;
        this.tailleGrille = tailleGrille;
    }

    public void partie(){
        switch(modeJeu){
            case 0 :
                this.grille = new Grille2D(this.tailleGrille);
                break;
            case 1 : 
                this.grille = new Grille3D(this.tailleGrille);
                break;
            default : 
                this.grille = new Grille2D(this.tailleGrille);
                break;
        }
        

        demarrerPartie();
    }
    
    private void demarrerPartie(){

    }
}
