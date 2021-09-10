package sample.DAO;

import sample.connexion.Databasecon;

import javax.swing.*;
import java.sql.*;

public class verifDAO {
    Connection conn =null;
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
    public  ResultSet getDatausers(String cin) throws SQLException {
        Connection conn = Databasecon.getConnexion();
        ResultSet rs=null;
        try{
            PreparedStatement ps = conn.prepareStatement("select * from PERSONNELINFO where cin='"+cin+"'");
            rs = ps.executeQuery();
        }catch (Exception e){JOptionPane.showMessageDialog(null, e);}


        return rs;
    }
}
