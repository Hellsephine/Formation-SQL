package exorpg.RPG;

public interface Equipable {
    public boolean equip(Personnage target) throws PersonnageException;

    public boolean unequip(Personnage target) throws PersonnageException;
}
