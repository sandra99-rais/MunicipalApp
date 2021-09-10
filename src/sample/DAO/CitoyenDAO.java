package sample.DAO;


import sample.connexion.CnxDB;
import sample.Models.Citoyen;

import javax.swing.*;
import java.sql.*;
import java.util.LinkedList;
import java.util.List;

public class CitoyenDAO {


    static Statement con = null;
    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;
    public CitoyenDAO() {
        try {
            con = CnxDB.getInst();
            System.out.println("L'instance a été  créer");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static boolean verify(String cin,String table){
        boolean msg=false;
        String verifycin="SELECT count(1) from "+table+" WHERE CIN = '" + cin + "'";
        try{
            con = CnxDB.getInst();

            ResultSet queryResult =con.executeQuery(verifycin);
            while (queryResult.next()){
                if(queryResult.getInt(1)==1){

                    msg= true;}
            }}catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);}
        return msg;
    }

    public void add(Citoyen citoyen){

            try {

                String st = "INSERT INTO table1 (cin,nom,prenom,date_naissance,sexe) VALUES  ('" + citoyen.getCIN()+ "'," + "'" + citoyen.getNom() + "','" + citoyen.getPrenom() + "','" + citoyen.getDate_de_naissance()  + "','" + citoyen.getSexe() + "') ";
                int i = con.executeUpdate(st);

            } catch (SQLException ex) {
                System.out.println("Erreur!");
                System.out.println(ex.getMessage());
            }
}
    public List<Citoyen> findAll() {
        LinkedList listeCitoyen = new LinkedList();

        String SQL = "SELECT * from table1";
        try {
            resultSet = con.executeQuery(SQL);


            while (resultSet.next()) {

                String CIN = resultSet.getString(1);
                String Nom = resultSet.getString(2);
                String Prenom = resultSet.getString(3);
                String date_naissance = resultSet.getString(4);
                String sexe = resultSet.getString(5);


                Citoyen newCitoyen = new Citoyen(CIN,Nom,Prenom,date_naissance,sexe);
                listeCitoyen.add(newCitoyen);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return listeCitoyen;
    }

    public void DeleteCitoyen(String cin){



        try {
            String sql = "delete from table1 where cin = '"+cin+"'";
            String sql3=("select * from table1 where cin ='"+cin+"'");
            ResultSet rs = con.executeQuery(sql3);
String CIN = null;
String Nom=null;
String Prenom=null;
String date_naissance=null;
String sexe=null;
            while(rs.next()){
                CIN = rs.getString(1);
                  Nom = rs.getString(2);
               Prenom = rs.getString(3);
                date_naissance = rs.getString(4);
                 sexe = rs.getString(5);}
                String sql2="insert into table2 (cin,nom,prenom,date_naissance,sexe)values('" +CIN+ "'," + "'" + Nom + "','" + Prenom + "','" + date_naissance   + "','" + sexe + "') ";
                int i=con.executeUpdate(sql2);



            int j=con.executeUpdate(sql);

            JOptionPane.showMessageDialog(null, "Le citoyen a été supprimé");

        } catch (Exception e) {
            System.out.println(e);
            JOptionPane.showMessageDialog(null, e);
        }
}
    public void edit(Citoyen citoyen){

        try {

            String ste = "update table1 set NOM= '" + citoyen.getNom()+ "', PRENOM='" + citoyen.getPrenom() + "', DATE_NAISSANCE='" + citoyen.getDate_de_naissance() + "',sexe='" + citoyen.getSexe()  + "' where cin='" + citoyen.getCIN() + "'";
            int i = con.executeUpdate(ste);


        } catch (SQLException ex) {
            System.out.println("probleme içi");
            System.out.println(ex.getMessage());
        }
    }
}

















