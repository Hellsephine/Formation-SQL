package exorpg.RPG;
import exorpg.utils.Model;

public abstract class BasicItem extends Model {
    protected String nom;
    protected int poids;
    protected String icon;

    public BasicItem(String nom){
        this.nom = nom;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public int getPoids() {
        return poids;
    }

    public void setPoids(int poids) {
        this.poids = poids;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    
}
