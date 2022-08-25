package exorpg.RPG;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;
import exorpg.utils.Model;
import exorpg.utils.DBManager;

public class Personnage extends Model {

    protected static Arme poings = new Arme("Poings", 1, 0.01f);
    protected static Armure aucune = new Armure("Aucune", 0);

    protected String nom;
    protected boolean type;
    protected int pv = 50;
    protected int pvMax = 50;
    protected int force = 1;

    protected ArrayList<BasicItem> inventaire = new ArrayList<BasicItem>();

    protected Armure armor = aucune;
    protected Arme equipedWeapon = poings;

    public Personnage(String nom){
        this.nom = nom;

    }

    public ArrayList<BasicItem> getInventaire() {
        return inventaire;
    }

    public void setInventaire(ArrayList<BasicItem> inventaire) {
        this.inventaire = inventaire;
    }

    public Personnage(String nom, int pv, int force){
        this.nom = nom;
        this.pv = pv;
        this.force = force;
    }
    //#region GETSET
    public Armure getArmor() {
        return armor;
    }

    public void setArmor(Armure armor) {
        this.armor = armor;
    }

    public Arme getEquipedWeapon() {
        return equipedWeapon;
    }

    public void setEquipedWeapon(Arme equipedWeapon) throws PersonnageException {
        //
        //...
        //
        if(equipedWeapon == null){
            throw new PersonnageException("Hey !");
        }
        else
            this.equipedWeapon = equipedWeapon;
    }

    public String toString(){
        return this.nom+"("+this.pv+")";
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public int getPv() {
        return pv;
    }

    public void setPv(int pv) {
        this.pv = pv;
    }

    public int getForce() {
        return force;
    }

    public void setForce(int force) {
        this.force = force;
    }
    //#endregion
    public float attaquer(Personnage autre){
        int degats = equipedWeapon.getDegats();
        if(Math.random() < (equipedWeapon.getCritique() + this.force / 100)){
            degats *= 2;
        }

        degats *= (1 + 0.1f * this.force);
        System.out.println(this.nom+" utilise "+equipedWeapon.getNom()+ " et tente d'infliger "+degats+" a "+autre.getNom());
        
        autre.prendreCoup(degats);

        return degats;
    }
    public float prendreCoup(float degats){
        degats *= (1-(this.armor.getDefense()/100.0f));
        this.pv -= degats;
        System.out.println(this.nom+ " reçoit "+ degats +" ! Il lui reste "+this.pv+" points de vie !");
        return degats;
    }
    public boolean ajouterItem(BasicItem item){
        return inventaire.add(item);
    }
    public boolean retirerItem(BasicItem item) throws NullPointerException{
        if(item == null)
            throw new NullPointerException("Item vide");
        return inventaire.remove(item);
    }

    public BasicItem retirerItem(int index){
        return inventaire.remove(index);
    }
    /*
     * Version alternative en mettant la méthode equip sur Personnage
     */
    public boolean equip(Arme weapon) throws PersonnageException{
        if(this.getEquipedWeapon() != null && this.getEquipedWeapon() != poings)
            this.ajouterItem(this.getEquipedWeapon());
        if(this.retirerItem(weapon)){
            this.setEquipedWeapon(weapon);
            return true;
        }
        else
            this.setEquipedWeapon(poings);
        return false;
    }
    public boolean equip(Armure armor) throws PersonnageException{
        if(this.getArmor() != null && this.getArmor() != aucune)
            this.ajouterItem(this.getArmor());
        if(this.retirerItem(armor)){
            this.setArmor(armor);
            return true;
        }
        else
            this.setArmor(aucune);
        return false;
    }

    @Override
    public boolean get(int id) {
        String sql = "SELECT * FROM personnages WHERE id_personnage = ?";
        try{
            PreparedStatement stmt = DBManager.conn.prepareStatement(sql);
            stmt.setInt(1, id);

            ResultSet resultat = stmt.executeQuery();
            if(resultat.next()){
                this.id = id;
                this.type = resultat.getBoolean("type");
                this.nom = resultat.getString("nom");
                this.pv = resultat.getInt("pv");
                this.pvMax = resultat.getInt("pvMax");
                this.force = resultat.getInt("force");
                this.armor = new Armure(resultat.getInt("id_armure"));
                this.equipedWeapon = new Arme(resultat.getInt("id_arme"));k
                return true;
            }
        }catch (SQLException ex) {
            // handle any errors
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }
        return false;
    }

    @Override
    public boolean save() {
        // TODO Auto-generated method stub
        return false;
    }
}
