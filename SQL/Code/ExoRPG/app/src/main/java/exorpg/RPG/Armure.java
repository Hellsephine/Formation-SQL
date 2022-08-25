package exorpg.RPG;

public class Armure extends BasicItem implements Equipable{
    protected int defense = 1;

    public Armure(int id){
        super("");
    }

    public Armure(String nom){
        super(nom);
    }

    public Armure(String nom, int defense){
        super(nom);
        this.defense = defense;
    }

    public int getDefense() {
        return defense;
    }

    public void setDefense(int defense) {
        this.defense = defense;
    }

    @Override
    public boolean equip(Personnage target) {
        if(target.getArmor() != null)
            target.ajouterItem(target.getArmor());
        if(target.retirerItem(this)){
            target.setArmor(this);
            return true;
        }
        return false;
    }
    @Override
    public boolean unequip(Personnage target) {
        if(target.getArmor() == this){
            target.ajouterItem(this);
            target.setArmor(null);
            return true;
        }
        return false;
    }
}
