package sample.DAO;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import sample.Models.Recette;
import sample.Models.travaux;
import sample.connexion.Databasecon;
import sample.services.recetteServiceImpl;

import javax.swing.*;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class travauxDAO {
    recetteServiceImpl recetteservice=new recetteServiceImpl();
    Connection conn = Databasecon.getConnexion();
    PreparedStatement pst,pst1 = null;
    public ObservableList<travaux> getDatabaseTravaux( ) {

        ObservableList<travaux> list = FXCollections.observableArrayList();
        try {
            PreparedStatement ps = conn.prepareStatement("SELECT * from Travaux");
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                list.add(new travaux((rs.getString("Num_Cin")), rs.getString("Nom_chef"),rs.getString("Type") ,rs.getString("Region"),rs.getDate("DateD"),rs.getString("Heure_debut"),rs.getDate("DateF") ,rs.getString("Heure_fin"),rs.getString("Besoin_materiels"),rs.getString("Besoin_vehicules"), rs.getString("Budget"), (Integer) rs.getInt("ID"),rs.getString("description")));
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
        return list;
    }
    public  ResultSet getDatabaseTravaux(int ID){

        ResultSet rs=null;
        ObservableList<travaux> list = FXCollections.observableArrayList();
        try {
            PreparedStatement ps = conn.prepareStatement("select * from Travaux where ID="+ID);
            rs = ps.executeQuery();


        } catch (Exception e) {
        }return rs;}

    public void Delete(int ID) {

        String sql = "delete from Travaux where ID="+ID;
        try {
            pst = conn.prepareStatement(sql);

            pst.execute();

            JOptionPane.showMessageDialog(null, "les travaux ont été supprimés");

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }



    }
    public String aujourdhui() {
        return LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    }

    public void AjoutTravaux(travaux trav){
        String sql = "insert into Travaux(Num_Cin,Nom_chef ,Type,Region,DateD,Heure_debut,DateF,Heure_fin,Besoin_materiels,Besoin_vehicules,Budget,ID,description)values(?,?,?,?,?,?,?,?,?,?,?,trav_auto_incr.nextval,?) ";
        String sql1="insert into GESTIONRECETTE(datee,Type_operation,RUBRIQUE,NOM,ID,GAIN,DEPENSE,SOLDE)values(?,?,?,?,?,?,?,?)";

        try {
            pst = conn.prepareStatement(sql);
            pst.setString(1, trav.getNum_Cin());
            pst.setString(2, trav.getNom_chef());
            pst.setString(3,trav.getType());
            pst.setString(4, trav.getRegion());
            pst.setDate(5, (Date)(trav.getDateD()));
            pst.setString(6, trav.getHeure_debut());
            pst.setDate(7,(Date)(trav.getDateF()));
            pst.setString(8, trav.getHeure_fin());
            pst.setString(9, trav.getBesoin_materiels());
            pst.setString(10, trav.getBesoin_vehicules());
            pst.setString(11, trav.getBudget());
            pst.setString(12,trav.getDescription());
            pst.execute();


            pst1 = conn.prepareStatement(sql1);
            pst1.setDate(1, Date.valueOf(aujourdhui()));
            pst1.setString(2, "Décaissement");
            pst1.setString(3, "frais de mission des projets");
            pst1.setString(4, "citoyen");
            pst1.setString(5, String.valueOf((int) (Math.random() *1000)+2000));
            pst1.setFloat(6, 0);
            pst1.setFloat(7, Float.parseFloat(trav.getBudget()));
            ObservableList<Recette> list= recetteservice.getDataRecette();
            int l=   list.size();
            Recette r=list.get(l-1);
            pst1.setFloat(8,r.getSolde()- Float.parseFloat(trav.getBudget()));
            pst1.execute();

            JOptionPane.showMessageDialog(null, "Les travaux ont été enregistrés");
        } catch (Exception e) {
        e.printStackTrace();
        }
    }

public void edit(travaux trav){
    String sql = "update Travaux set Num_Cin=? ,Nom_chef=?,Type =? ,Region=?,DateD=?,Heure_debut=? ,DateF=? ,Heure_fin=?,Besoin_materiels=?,Besoin_vehicules=?,Budget=?,description=? where ID=" +trav.getID();
   try{
    pst = conn.prepareStatement(sql);
    pst.setString(1, trav.getNum_Cin());
    pst.setString(2, trav.getNom_chef());
    pst.setString(3,trav.getType());
    pst.setString(4, trav.getRegion());
    pst.setDate(5, (Date)(trav.getDateD()));
    pst.setString(6, trav.getHeure_debut());
    pst.setDate(7,(Date)(trav.getDateF()));
    pst.setString(8, trav.getHeure_fin());
    pst.setString(9, trav.getBesoin_materiels());
    pst.setString(10, trav.getBesoin_vehicules());
    pst.setString(11, trav.getBudget());
       pst.setString(12,trav.getDescription());
    pst.execute();
       JOptionPane.showMessageDialog(null, "Les travaux ont été modifiés");
   } catch (Exception e) {
       JOptionPane.showMessageDialog(null, e);}
}
}
