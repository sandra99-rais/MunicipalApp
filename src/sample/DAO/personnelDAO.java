package sample.DAO;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import sample.connexion.Databasecon;
import sample.Models.login;
import sample.Models.personnel;

import javax.swing.*;
import java.sql.*;

public class personnelDAO {
    Connection conn =null;
    PreparedStatement pst = null;

    int ret;

    public void add(personnel personne, login log) {
        conn = Databasecon.getConnexion();
        String sql = "insert into personnelinfo (cin,prenom,nom,email,grade,salaire,datenaissance)values(?,?,?,?,?,?,?) ";
        String sql2 ="insert into personnel(cin,mdp,job) values(?,?,?)";
try{
        pst = conn.prepareStatement(sql);
        pst.setString(1, personne.getCin());
        pst.setString(2, personne.getPrenom());
        pst.setString(3, personne.getNom());
        pst.setString(4, personne.getEmail());
        pst.setString(5, personne.getGrade());
        pst.setFloat(6, personne.getSalaire());
        pst.setDate(7, (Date) personne.getDatenaissance());
        PreparedStatement pst2 = conn.prepareStatement(sql2);
        pst2.setString(1, log.getCin());
        pst2.setString(2, log.getMdp());
        pst2.setString(3,log.getJob());
        pst2.execute();
        pst.execute();
    JOptionPane.showMessageDialog(null, "L'utilisateur a été ajouté");
} catch (Exception e) {
    JOptionPane.showMessageDialog(null, e);}
    }
    public int verifylogin(String cin){
        conn = Databasecon.getConnexion();
        String verifylogin="SELECT count(1) from PERSONNELarchiver WHERE CIN = '" + cin + "'";
        try{
            Statement statement = conn.createStatement();
            ResultSet queryResult =statement.executeQuery(verifylogin);
            while (queryResult.next()){
                if(queryResult.getInt(1)==1) {
                    ret=1;

                }
            else ret= -1;
            }
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, e);
        }
return ret;

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

    public void Delete(String cin){

            conn = Databasecon.getConnexion();
            String sql3 = "delete from personnel where cin = ?";
            String sql2="insert into personnelarchiver (cin,prenom,nom,email,grade,salaire,datenaissance)values(?,?,?,?,?,?,?)";
            String sql = "delete from personnelinfo where cin = ?";

            try {
                PreparedStatement ps = conn.prepareStatement("select * from PERSONNELINFO where cin ="+cin);
                ResultSet rs = ps.executeQuery();
                PreparedStatement pst2 = conn.prepareStatement(sql2);
                while(rs.next()){
                    pst2.setString(1, rs.getString("cin"));
                    pst2.setString(2, rs.getString("prenom"));
                    pst2.setString(3, rs.getString("nom"));
                    pst2.setString(4, rs.getString("email"));
                    pst2.setString(5, rs.getString("grade"));
                    pst2.setFloat(6, Float.valueOf(rs.getString("salaire")));
                    pst2.setDate(7, (rs.getDate("datenaissance")));
                }
                pst2.execute();
                pst = conn.prepareStatement(sql);
                pst.setString(1, cin);
                pst.execute();
                PreparedStatement pst3 = conn.prepareStatement(sql3);
                pst3.setString(1, cin);
                pst3.execute();
                JOptionPane.showMessageDialog(null, "L'utilisateur a été supprimé");
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e);
            }

    }

    public void Edit(personnel personnel){
  conn = Databasecon.getConnexion();
            String sql = "update personnelinfo set nom=?,prenom=?,grade=?,datenaissance=?,email=?,salaire=? WHERE cin='"+personnel.getCin()+"'";


                try{
                    pst = conn.prepareStatement(sql);
                    pst.setString(2, personnel.getPrenom());
                    pst.setString(1, personnel.getNom());
                    pst.setString(5, personnel.getEmail());
                    pst.setString(3, personnel.getGrade());
                    pst.setFloat(6, Float.valueOf(personnel.getSalaire()));
                    pst.setDate(4, (Date) personnel.getDatenaissance());
                    pst.execute();
                    JOptionPane.showMessageDialog(null, "La mise a jours a été effectuée");
                }catch(Exception e){
                    e.printStackTrace();
                }

            }

    public  ObservableList<personnel> getDatausers(){
        Connection conn = Databasecon.getConnexion();
        ObservableList<personnel> list = FXCollections.observableArrayList();
        try {
            PreparedStatement ps = conn.prepareStatement("select * from PERSONNELINFO");
            ResultSet rs = ps.executeQuery();

            while (rs.next()){
                list.add(new personnel((rs.getString("CIN")), rs.getString("nom"), rs.getString("prenom"),rs.getString("grade"),rs.getDate("datenaissance"),rs.getString("email") ,rs.getFloat("salaire")));
            }
        } catch (Exception e) {
        }
        return list;
    }
}
