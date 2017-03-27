public class Noeud 
{
    private Noeud gauche;
    private Noeud droit;

    public Noeud(Noeud g, Noeud d) {
        this.gauche = g;
        this.droit = d;
    }
}