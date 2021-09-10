package sample.DAO;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import sample.Models.demandes;
import sample.connexion.Databasecon;

import javax.swing.*;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class demandeDAO {
    Connection conn = Databasecon.getConnexion();
    PreparedStatement pst,pst1 = null;
    public  ObservableList<demandes> getdemandes(){

        ObservableList<demandes> list = FXCollections.observableArrayList();
        try {
            PreparedStatement ps = conn.prepareStatement("select * from demandes");
            ResultSet rs = ps.executeQuery();

            while (rs.next()){
                list.add(new demandes((rs.getString("ID_DEMANDE")), rs.getString("CIN"), rs.getString("demande"), rs.getString("telephone"), rs.getDate("datedemande"), rs.getString("lieu"), rs.getString("decision"), rs.getString("type")));
            }
        } catch (Exception e) {
        }
        return list;}
        public void adddemande(demandes demande){
            String sql = "insert into DEMANDES(ID_DEMANDE, CIN,DEMANDE,Type,TELEPHONE,DATEDEMANDE,LIEU,DECISION,TYPE)values(Demande_auto_incr.nextval,?,?,?,?,?,?,? ) ";
            try {
                pst = conn.prepareStatement(sql);
                pst.setString(1, demande.getCIN());
                pst.setString(2, demande.getDEMANDE());
                pst.setString(3, demande.getTELEPHONE());
                pst.setDate(4, (Date) (demande.getDatedemande()));
                pst.setString(5, demande.getLieu());
                pst.setString(6, demande.getDecision());
                pst.setString(7, demande.getType());

                pst.execute();

                JOptionPane.showMessageDialog(null, "La demande a été bien ajoutée");

            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e);}
        }
    public void Delete(String id){

        conn = Databasecon.getConnexion();
        String sql = "delete from demandes where ID_DEMANDE = '"+id+"'";


        try {


            pst = conn.prepareStatement(sql);
            pst.execute();

            JOptionPane.showMessageDialog(null, "La demande a été supprimée");

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }

    }

    public void accepterourefuser(String choice,String id){
        try{
            String sql ="update DEMANDES set DECISION= '"+choice+"' where ID_DEMANDE="+id;
            pst= conn.prepareStatement(sql);
            pst.execute();}catch (Exception e){
            JOptionPane.showMessageDialog(null, e);
        }
    }

    public void modif(demandes demande){
        String sql = "update DEMANDES set cin=?,TELEPHONE=?,DEMANDE=?,LIEU=?,DATEDEMANDE=?,TYPE=? WHERE ID_DEMANDE='"+demande.getID_DEMANDE()+"'";
try{
    pst = conn.prepareStatement(sql);
    pst.setString(1, demande.getCIN());
    pst.setString(2, demande.getTELEPHONE());
    pst.setString(3, demande.getDEMANDE());
    pst.setString(4, demande.getLieu());
    pst.setDate(5, (Date) (demande.getDatedemande()));
    pst.setString(6, demande.getType());

    pst.execute();
    JOptionPane.showMessageDialog(null, "La mise à jour a été effectuée avec succés ");

}catch(Exception e){
    JOptionPane.showMessageDialog(null, e);

}
    }
}
