package sample.DAO;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import sample.Models.Materiels;
import sample.Models.Recette;
import sample.connexion.Databasecon;
import sample.services.recetteServiceImpl;

import javax.swing.*;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class materielDAO {
    Connection conn = Databasecon.getConnexion();
    PreparedStatement pst,pst1 = null;
    recetteServiceImpl recetteservice=new recetteServiceImpl();

    public ObservableList<Materiels> getDatamateriel() {
        Connection conn = Databasecon.getConnexion();
        ObservableList<Materiels> list = FXCollections.observableArrayList();
        try {
            PreparedStatement ps = conn.prepareStatement("SELECT * from Materiels");
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                list.add(new Materiels((rs.getString("ID_materiel")) ,rs.getString("Type"),rs.getString("Quantité") ,rs.getString("Prix"),rs.getDate("Datee"), (Integer) rs.getInt("ID"),rs.getString("description")));
            }
        } catch (Exception e) {
        }
        return list;
    }
    public ResultSet getDatabasemateriel(int ID){
        Connection conn = Databasecon.getConnexion();
        ResultSet rs=null;
        ObservableList<Materiels> list = FXCollections.observableArrayList();
        try {
            PreparedStatement ps = conn.prepareStatement("select * from Materiels where ID="+ID);
            rs = ps.executeQuery();


        } catch (Exception e) {
        }return rs;
    }
    public void Delete(Integer ID){
        String sql = "delete from Materiels where ID=" +ID;
        try {
            pst = conn.prepareStatement(sql);
            pst.execute();
            JOptionPane.showMessageDialog(null, "Le matériel a été supprimé");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
        }
    public void AjoutMateriel (Materiels materiel){

        String sql = "insert into Materiels(ID_materiel,Type ,Quantité,Prix,Datee,description )values(?,?,?,?,?,? ) ";
        String sql1="insert into GESTIONRECETTE(datee,Type_operation,RUBRIQUE,NOM,ID,GAIN,DEPENSE,SOLDE)values(?,?,?,?,?,?,?,?)";
try{
    pst = conn.prepareStatement(sql);
    pst.setString(1, materiel.getID_materiel());
    pst.setString(2, materiel.getType());
    pst.setString(3, materiel.getQuantité());
    pst.setString(4,materiel.getPrix());
    pst.setDate(5, (Date) (materiel.getDatee()));
    pst.setString(6,materiel.getDescription());
    pst.execute();
    pst1 = conn.prepareStatement(sql1);
    pst1.setDate(1, (Date)(materiel.getDatee()));
    pst1.setString(2, "Décaissement");
    pst1.setString(3, "achat matriels");
    pst1.setString(4, "fournisseur");
    pst1.setString(5, String.valueOf((int) (Math.random() *1000)+1000));
    pst1.setFloat(6, 0);
    pst1.setFloat(7, Float.parseFloat(materiel.getPrix()));
    ObservableList<Recette> list= recetteservice.getDataRecette();
    int l=   list.size();
    Recette r=list.get(l-1);
    pst1.setFloat(8,r.getSolde()- Float.parseFloat(materiel.getPrix()));
    pst1.execute();





    JOptionPane.showMessageDialog(null, "le materiel a été ajouté");
} catch (Exception e) {
    JOptionPane.showMessageDialog(null, e);

}
    }
    public void edit(Materiels materiel){
        String sql = "update Materiels set ID_materiel=? ,Type=?,Quantité =? ,Prix=?,Datee=?,description=?  where ID=" +materiel.getID();

try{
        pst = conn.prepareStatement(sql);
        pst.setString(1, materiel.getID_materiel());
        pst.setString(2,materiel.getType());

        pst.setString(3,materiel.getQuantité());
        pst.setString(4,materiel.getPrix());


        pst.setDate(5, (Date)(materiel.getDatee()));
    pst.setString(6,materiel.getDescription());



    pst.execute();


        JOptionPane.showMessageDialog(null, "Le matériel a été modifié");

    } catch (Exception e) {
        JOptionPane.showMessageDialog(null, e);}
}


}
