package sample.DAO;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import sample.Models.Recette;
import sample.Models.voitures;
import sample.connexion.Databasecon;
import sample.services.recetteServiceImpl;

import javax.swing.*;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class VehiculeDAO {
    Connection conn = Databasecon.getConnexion();
    PreparedStatement pst,pst1 = null;
    recetteServiceImpl recetteservice=new recetteServiceImpl();


    public  ObservableList<voitures> getDatavoiture() {
        Connection conn = Databasecon.getConnexion();
        ObservableList<voitures> list = FXCollections.observableArrayList();
        try {
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM VOITURE");
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                list.add(new voitures((rs.getString("Marque")), rs.getString("Matricule"),rs.getString("Couleur") ,rs.getString("Nombre"),rs.getString("Prix"),rs.getDate("Datee"),rs.getString("Type"), (Integer) rs.getInt("ID")));
            }
        } catch (Exception e) {
        }
        return list;
    }
    public  ResultSet getDatabasevoiture(int ID){
        Connection conn = Databasecon.getConnexion();
        ResultSet rs=null;
        ObservableList<voitures> list = FXCollections.observableArrayList();
        try {
            PreparedStatement ps = conn.prepareStatement("select * from VOITURE where ID="+ID);
            rs = ps.executeQuery();


        } catch (Exception e) {
        }return rs;
    }

    public void Delete(Integer ID){
        String sql = "delete from VOITURE where ID=" +ID;
        try {
            pst = conn.prepareStatement(sql);
            pst.execute();
            JOptionPane.showMessageDialog(null, "Le Vehicule a été supprimé");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }
    public void Ajoutervehicule(voitures vehicule){

        String sql = "insert into VOITURE(Marque,Matricule ,Couleur,Nombre,Prix,Datee,Type,ID)values(?,?,?,?,?,?,?,veh_auto_incr.nextval) ";
        String sql1="insert into GESTIONRECETTE(datee,Type_operation,RUBRIQUE,NOM,ID,GAIN,DEPENSE,SOLDE)values(?,?,?,?,?,?,?,?)";

        try {
            pst = conn.prepareStatement(sql);
            pst = conn.prepareStatement(sql);
            pst.setString(1, vehicule.getMarque());
            pst.setString(2, vehicule.getMatricule());

            pst.setString(3, vehicule.getCouleur());
            pst.setString(4, vehicule.getNombre());

            pst.setString(5,vehicule.getPrix());
            pst.setDate(6, (Date)(vehicule.getDatee()));
            pst.setString(7, vehicule.getType());


            pst.execute();
            pst1 = conn.prepareStatement(sql1);
            pst1.setDate(1, (Date)(vehicule.getDatee()));
            pst1.setString(2, "Décaissement");
            pst1.setString(3, "achat matriels");
            pst1.setString(4, "fournisseur");
            pst1.setString(5, String.valueOf((int) (Math.random() *1000)+1000));
            pst1.setFloat(6, 0);
            pst1.setFloat(7, Float.parseFloat(vehicule.getPrix()));
            ObservableList<Recette> list= recetteservice.getDataRecette();
            int l=   list.size();
            Recette r=list.get(l-1);
            pst1.setFloat(8,r.getSolde()- Float.parseFloat(vehicule.getPrix()));
            pst1.execute();


            JOptionPane.showMessageDialog(null, "Le vehicule a été ajouté");

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }


    }
    public void edit(voitures vehicule){
        String sql = "update VOITURE set Marque=? ,Matricule=?,Couleur =? ,Nombre=?,Prix=? ,Datee=? ,Type=?  where ID=" +vehicule.getID();
        try {
            pst = conn.prepareStatement(sql);
            pst.setString(1, vehicule.getMarque());
            pst.setString(2,vehicule.getMatricule());

            pst.setString(3, vehicule.getCouleur());
            pst.setString(4, vehicule.getNombre());

            pst.setString(5, vehicule.getPrix());
            pst.setDate(6, (Date) (vehicule.getDatee()));
            pst.setString(7, vehicule.getType());


            pst.execute();


            JOptionPane.showMessageDialog(null, "Le vehicule a été modifié");



        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);}
    }

}
