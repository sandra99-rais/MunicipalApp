package sample.DAO;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import sample.Models.evenement;
import sample.connexion.Databasecon;

import javax.swing.*;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class evenementDAO {
    Connection conn = Databasecon.getConnexion();
    PreparedStatement pst,pst1 = null;
    public void accepterourefuser(String choice,int id){
        try{
        String sql = "update EVENEMENT set DECISION= '"+choice+"' where ID=" + id ;
        pst= conn.prepareStatement(sql);
        pst.execute();}catch (Exception e){
            JOptionPane.showMessageDialog(null, e);
        }
    }
    public ObservableList<evenement> getevenement(){
        Connection conn = Databasecon.getConnexion();
        ObservableList<evenement> list = FXCollections.observableArrayList();
        try {
            PreparedStatement ps = conn.prepareStatement("select * from EVENEMENT");
            ResultSet rs = ps.executeQuery();

            while (rs.next()){
                list.add(new evenement((rs.getInt("ID")), rs.getString("NOM"), rs.getString("TYPE"), rs.getString("ORGANISEUR"),rs.getString("LIEU") ,rs.getString("ADRESSE"),rs.getString("LIEURENDEZVOUS"),rs.getString("HEURERENDEZVOUS"),rs.getInt("NMBRPERSONNES"),rs.getDate("DUREEDU"),rs.getString("HEUREDU"),rs.getDate("DUREEAU"),rs.getString("HEUREAU"),rs.getInt("TELEPHONE"),rs.getString("EMAIL"),rs.getString("DECISION"),rs.getString("description")));
            }
        } catch (Exception e) {
        }
        return list;
    }
    public  ResultSet getevenement(int id){
        Connection conn = Databasecon.getConnexion();
        ResultSet rs=null;
        ObservableList<evenement> list = FXCollections.observableArrayList();
        try {
            PreparedStatement ps = conn.prepareStatement("select * from evenement where id="+id);
            rs = ps.executeQuery();


        } catch (Exception e) {
        }return rs;}

        public  void delete(int id){
            String sql = "delete from evenement where id="+id;
            try{
                pst = conn.prepareStatement(sql);
                pst.execute();

                JOptionPane.showMessageDialog(null, "L'événement a été supprimé");
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e);
            }

        }
        public void Addevenement(evenement event){
            String sql = "insert into EVENEMENT (ID,nom,type,organiseur,lieu,adresse,LIEURENDEZVOUS,HEURERENDEZVOUS,NMBRPERSONNES,DUREEDU,HEUREDU,DUREEAU,HEUREAU,TELEPHONE,EMAIL,DECISION,description )values(Event_auto_incr.nextval,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,? ) ";
            try {
                pst = conn.prepareStatement(sql);
                pst.setString(1, event.getNom());
                pst.setString(2, event.getType());
                pst.setString(3, event.getOrganiseur());
                pst.setString(4,event.getLieu());
                pst.setString(5, event.getAdresse());
                pst.setString(6, event.getLIEURENDEZVOUS());
                pst.setString(7, event.getHEURERENDEZVOUS());
                pst.setInt(8, event.getNMBRPERSONNES());
                pst.setDate(9, (Date) (event.getDUREEDU()));
                pst.setString(10, event.getHEUREDU());
                pst.setDate(11, (Date) (event.getDUREEAU()));
                pst.setString(12, event.getHEUREAU());
                pst.setInt(13,event.getTELEPHONE());
                pst.setString(14, event.getEMAIL());
                pst.setString(15, "undecided");
                pst.setString(16,event.getDescription());



                pst.execute();

                JOptionPane.showMessageDialog(null, "l'événement a été enregistré");

            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e);}

        }
        public void edit(evenement event){
            String sql = "update EVENEMENT set nom=? ,type=?,organiseur=?,lieu=?,adresse=?,LIEURENDEZVOUS=?,HEURERENDEZVOUS=?,NMBRPERSONNES=?,DUREEDU=?,HEUREDU=?,DUREEAU=?,HEUREAU=?,TELEPHONE=?,EMAIL=?,description=? where id="+event.getId();
            try {
                pst = conn.prepareStatement(sql);

                pst.setString(1, event.getNom());
               pst.setString(2, event.getType());
               pst.setString(3, event.getOrganiseur());
               pst.setString(4, event.getLieu());
               pst.setString(5, event.getAdresse());
               pst.setString(6, event.getLIEURENDEZVOUS());
               pst.setString(7, event.getHEURERENDEZVOUS());
               pst.setInt(8, event.getNMBRPERSONNES());
               pst.setDate(9, (Date) (event.getDUREEDU()));
               pst.setString(10, event.getHEUREDU());
               pst.setDate(11, (Date) (event.getDUREEAU()));
               pst.setString(12, event.getHEUREAU());
               pst.setInt(13, event.getTELEPHONE());
               pst.setString(14, event.getEMAIL());
                pst.setString(15,event.getDescription());


                pst.execute();

                JOptionPane.showMessageDialog(null, "l'événement a été modifié");

            }catch (Exception e){
                JOptionPane.showMessageDialog(null, e);}
            }
        }


