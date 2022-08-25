package exorpg.RPG;

public class PotionSoin extends BasicItem implements Consommable {
    protected int pvRendu = 0;

    public PotionSoin(String nom) {
        super(nom);
    }

    public int getPvRendu() {
        return pvRendu;
    }

    public void setPvRendu(int pvRendu) {
        this.pvRendu = pvRendu;
    }

    @Override
    public void consommer(Personnage cible) {
        cible.setPv(cible.getPv() + pvRendu);
    }
    
}
