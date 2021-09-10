package sample.DAO;

import sample.connexion.Databasecon;
import sample.Models.login;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class loginDAO {
    boolean msg;
public boolean verif(String cin,String mdp){

    Databasecon connectNow= new Databasecon();
    Connection connectdb =connectNow.getConnexion();
    String verifylogin="SELECT count(1) from PERSONNEL WHERE CIN = '" + cin+ "' AND MDP = '" + mdp+"'";
    try{
        Statement statement = connectdb.createStatement();
        ResultSet queryResult =statement.executeQuery(verifylogin);
        while (queryResult.next()){
            if(queryResult.getInt(1)==1){
                msg=true;
            }else msg=false;
}
      }catch(Exception e){
        e.printStackTrace();
    }
return msg;
}
    public login getlogin(String cin){
        Connection conn = Databasecon.getConnexion();
        ResultSet rs=null;
        login log=null;
        try {
            PreparedStatement ps = conn.prepareStatement("select * from PERSONNEL where cin="+cin);
            rs = ps.executeQuery();
            while (rs.next()) {
                log=new login((rs.getString("cin")), rs.getString("job"), rs.getString("mdp"));
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }return log;}

}
