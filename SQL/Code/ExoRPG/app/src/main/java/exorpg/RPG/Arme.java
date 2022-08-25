package exorpg.RPG;

import exorpg.utils.DBManager;
import java.sql.*;

public class Arme extends BasicItem implements Equipable{
    int degats = 0;
    float critique = 0;
    int id = 0;

    public Arme(String nom){
        super(nom);
    }
    public Arme(String nom, int degats, float critique){
        super(nom);
        this.degats = degats;
        this.critique = critique;
    }

    public Arme(int id){
        super("");
        try{
            ResultSet resultat = DBManager.execute("SELECT * FROM armes WHERE id_arme = "+id);
            if(resultat.next()){
                this.nom = resultat.getString("nom");
                this.degats = resultat.getInt("degats");
                this.critique = resultat.getFloat("critique");
                this.poids = resultat.getInt("poids");
                this.icon = resultat.getString("icon");
                this.id = id;
            }
        }
        catch (SQLException ex) {
            // handle any errors
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }
    }

    public boolean get(int id){
        try{
            ResultSet resultat = DBManager.execute("SELECT * FROM armes WHERE id_arme = "+id);
            if(resultat.next()){
                this.nom = resultat.getString("nom");
                this.degats = resultat.getInt("degats");
                this.critique = resultat.getFloat("critique");
                this.poids = resultat.getInt("poids");
                this.icon = resultat.getString("icon");
                this.id = id;
                return true;
            }
        }
        catch (SQLException ex) {
            // handle any errors
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }
        return false;
    }

    public boolean save(){
        String sql;
        if(this.id != 0)
            sql = "UPDATE armes "+
                    "SET nom = ?, degats = ?, critique = ?, poids = ?, icon = ?"+
                    "WHERE id_arme = ?";
        else
            sql = "INSERT INTO armes (nom, degats, critique, poids, icon)"+
                        "VALUES(?, ?, ?, ?, ?)";
        try{
            PreparedStatement stmt = DBManager.conn.prepareStatement(sql);
            stmt.setString(1, this.nom);
            stmt.setInt(2, this.degats);
            stmt.setFloat(3, this.critique);
            stmt.setInt(4, this.poids);
            stmt.setString(5, this.icon);
            if(id != 0)
                stmt.setInt(6, this.id);

            return stmt.execute();
        }catch (SQLException ex) {
            // handle any errors
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
            return false;
        }
        /* Old version
        if(this.id != 0){
            sql = "UPDATE armes SET "+
                            "nom = '"+this.nom+"', "+
                            "degats = "+this.degats+", "+
                            "critique = "+this.critique+", "+
                            "poids = "+this.poids+", "+
                            "icon = '"+this.icon+"' "+
                            "WHERE id_arme = "+id;
        }
        else{
            sql = "INSERT INTO armes (nom, degats, critique, poids, icon) "+
                    "VALUES("+
                    "'"+this.nom+"',"+
                    ""+this.degats+","+
                    ""+this.critique+","+
                    ""+this.poids+","+
                    "'"+this.icon+"')";
        }

        return (DBManager.executeUpdate(sql) > 0);
        */
    }

    public int getDegats() {
        return degats;
    }
    public void setDegats(int degats) {
        this.degats = degats;
    }
    public float getCritique() {
        return critique;
    }
    public void setCritique(float critique) {
        this.critique = critique;
    }
    @Override
    public boolean equip(Personnage target) throws PersonnageException{
        if(target.getEquipedWeapon() != null)
            target.ajouterItem(target.getEquipedWeapon());
        if(target.retirerItem(this)){
            target.setEquipedWeapon(this);
            return true;
        }
        return false;
    }
    @Override
    public boolean unequip(Personnage target) throws PersonnageException{
        if(target.getEquipedWeapon() == this){
            target.ajouterItem(this);
            target.setEquipedWeapon(null);
            return true;
        }
        return false;
    }
    
}
