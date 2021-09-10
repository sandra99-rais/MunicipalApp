package sample.DAO;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import sample.Models.Recette;
import sample.connexion.Databasecon;

import javax.swing.*;
import java.sql.*;

public class recetteDAO {
    Connection conn = Databasecon.getConnexion();
    PreparedStatement pst = null;
    boolean msg;
    public  ObservableList<Recette> getDataRecette() {

        ObservableList<Recette> list = FXCollections.observableArrayList();
        try {
            PreparedStatement ps = conn.prepareStatement("SELECT * from GESTIONRECETTE");
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                list.add(new Recette((rs.getDate("datee")) ,rs.getString("Type_operation"),rs.getString("Rubrique") ,rs.getString("Nom"),rs.getInt("Id"),rs.getFloat("Gain"),rs.getFloat("Depense"),rs.getFloat("Solde")));
            }
        } catch (Exception e) {
        }
        return list;
    }
    public  ResultSet getRecette(int Id){

        ResultSet rs=null;
        ObservableList<Recette> list = FXCollections.observableArrayList();
        try {
            PreparedStatement ps = conn.prepareStatement("select * from GESTIONRECETTE where Id="+Id);
            rs = ps.executeQuery();


        } catch (Exception e) {
        }return rs;}

        public void addrecette(Recette recette){
            String sql = "insert into GESTIONRECETTE(datee,Type_operation,RUBRIQUE,NOM,ID,GAIN,DEPENSE,SOLDE)values(?,?,?,?,?,?,?,?) ";

            try {
                pst = conn.prepareStatement(sql);
                pst.setDate(1, (Date) ((recette.getDate())));
                pst.setString(2, recette.getType_operation());
                pst.setString(3, recette.getRubrique());
                pst.setString(4,recette.getNom());
                pst.setString(5, String.valueOf(recette.getId()));
                pst.setFloat(6,recette.getGain());
                pst.setFloat(7, recette.getDepense());
                pst.setFloat(8,recette.getSolde());



                pst.execute();


                JOptionPane.showMessageDialog(null, "La Recette a été ajoutée");
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e);
            }
        }
        public boolean verif(int id) {
            String verifyRec = "Select count(1)  from GESTIONRECETTE where  ID  ='" + id + "' ";
            try {
                Statement statement = conn.createStatement();
                ResultSet queryResult = statement.executeQuery(verifyRec);
                while (queryResult.next()) {
                    if (queryResult.getInt(1) == 1) {
                        msg=true;

                    }else msg=false;
                }
            } catch (Exception e) {
                e.printStackTrace();
            } return  msg;
        }
    public void Delete(String id) {
        conn = Databasecon.getConnexion();
        String sql = "delete from GESTIONRECETTE where ID  = "+id;


        try {
            pst = conn.prepareStatement(sql);
            pst.execute();
            JOptionPane.showMessageDialog(null, "La Recette a été supprimée");

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }

    }

    public void modif(Recette recette){

        String sql = "update GESTIONRECETTE set DATEE=?,TYPE_OPERATION=?,RUBRIQUE=?,NOM=?,GAIN=?,DEPENSE=?,SOLDE=? WHERE ID='"+recette.getId()+"'";



                        try{
                            pst = conn.prepareStatement(sql);

                            pst.setDate(1, (Date) (((recette.getDate()))));
                            pst.setString(2, recette.getType_operation());
                            pst.setString(3, recette.getRubrique());
                            pst.setString(4,recette.getNom());
                            pst.setFloat(5,recette.getGain());
                            pst.setFloat(6, recette.getDepense());
                            pst.setFloat(7,recette.getSolde());



                            pst.execute();
                            JOptionPane.showMessageDialog(null, "Update");
                        }catch(Exception e){   JOptionPane.showMessageDialog(null, e);

                        }}


        }



