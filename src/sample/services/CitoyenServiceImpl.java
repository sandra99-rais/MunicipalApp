package sample.services;

import sample.connexion.CnxDB;
import sample.DAO.CitoyenDAO;
import sample.Models.Citoyen;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;

public class CitoyenServiceImpl implements CitoyenService {
    Statement con = null;
    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;
    CitoyenDAO citoyenDAO = new CitoyenDAO();
    public CitoyenServiceImpl()  {
        try{
            con= CnxDB.getInst();
            System.out.println("l'instance a été créer ");} catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void add(Citoyen citoyen) {
        citoyenDAO.add(citoyen);
    }

    public List<Citoyen> findAll() {

        return (List<Citoyen>) citoyenDAO.findAll();
    }


    public static boolean verify(String cin,String table) {
        return CitoyenDAO.verify(cin,table);
    }



    public void DeleteCityoen(String text) {
        citoyenDAO.DeleteCitoyen(text);
    }

    @Override
    public void edit(Citoyen citoyen) {
        citoyenDAO.edit(citoyen);
    }

}
