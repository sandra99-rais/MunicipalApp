package sample.DAO;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import sample.connexion.Databasecon;
import sample.Models.personnels;

import javax.swing.*;
import java.sql.*;

public class equipDAO {
    Connection conn = Databasecon.getConnexion();
    boolean msg;
    PreparedStatement pst = null;
    public  ObservableList<personnels> getDatausers() {
        Connection conn = Databasecon.getConnexion();
        ObservableList<personnels> list = FXCollections.observableArrayList();
        try {
            PreparedStatement ps = conn.prepareStatement("SELECT * from TeamPersonnel");
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                list.add(new personnels((rs.getString("NumEquipe")) ,rs.getString("P1"),rs.getString("P2") ,rs.getString("P3"),rs.getString("TacheEquipe"),rs.getString("P4") ));
            }
        } catch (Exception e) {
        }
        return list;
    }
    public boolean verify(String cin){
        boolean msg=false;
        String verifycin="SELECT count(1) from personnelinfo WHERE CIN = '" + cin + "'";
        try{
            conn = Databasecon.getConnexion();
            Statement statement = conn.createStatement();
            ResultSet queryResult =statement.executeQuery(verifycin);
            while (queryResult.next()){
                if(queryResult.getInt(1)==1){

                    msg= true;}
            }}catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);}
        return msg;
    }
    public boolean verifycin(String cin1,String cin2,String cin3,String cin4){

        String verifycin="Select count(*)  from TeamPersonnel where  P1  ='"+cin1+"' or P2  ='"+cin1+"' or P3  ='"+cin1+"' or P4  ='"+cin1+"' or P1  ='"+cin2+"' or P2  ='"+cin2+"' or P3  ='"+cin2+"' or P4  ='"+cin2+"' or P1  ='"+cin3+"' or P2  ='"+cin3+"' or P3  ='"+cin3+"' or P4  ='"+cin3+"' or P1  ='"+cin4+"' or P2  ='"+cin4+"' or P3  ='"+cin4+"' or P4  ='"+cin4+"'";
        try{Statement statement = conn.createStatement();
            ResultSet queryResult=statement.executeQuery(verifycin);
            while(queryResult.next()){
                if(queryResult.getInt(1)==1){
                    msg=true;
                }else msg=false;

            }

} catch (SQLException throwables) {
            throwables.printStackTrace();
        }return msg;}

    public boolean verifynumequip(String num){
        boolean msg=false;
        String verifycin="SELECT count(1) from teampersonnel WHERE numequipe = '" + num + "'";
        try{
            conn = Databasecon.getConnexion();
            Statement statement = conn.createStatement();
            ResultSet queryResult =statement.executeQuery(verifycin);
            while (queryResult.next()){
                if(queryResult.getInt(1)==1){

                    msg= true;}
            }}catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);}
        return msg;
    }

    public void Addpersonnel (personnels equip) {

        conn = Databasecon.getConnexion();
        String sql = "insert into TeamPersonnel(NumEquipe,P1,P2,P3,TacheEquipe,P4)values(?,?,?,?,?,? ) ";



                try {
                    pst = conn.prepareStatement(sql);
                    pst.setString(1,equip.getNumequip());
                    pst.setString(2, equip.getNum_CIN1());
                    pst.setString(3, equip.getNum_CIN2());
                    pst.setString(4, equip.getNum_CIN3());

                    pst.setString(5, equip.getTache());
                    pst.setString(6,equip.getNum_CIN4());


                    pst.execute();

                    JOptionPane.showMessageDialog(null, "L'equip' a été ajouté avec succés");


                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null, e);
                }
    }

    public void Delete(String numequipe){
        conn = Databasecon.getConnexion();
        String sql = "delete from TEAMPERSONNEL where NUMEQUIPE  = ?";
        try {
            pst = conn.prepareStatement(sql);
            pst.setString(1,numequipe);
            pst.execute();
            JOptionPane.showMessageDialog(null,"l'equip a été supprimer");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }




    }

}
