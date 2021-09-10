package sample.services;

import javafx.collections.ObservableList;
import sample.DAO.evenementDAO;
import sample.Models.evenement;

import java.sql.ResultSet;

public class evenementServiceImpl {
    evenementDAO evenementdao=new evenementDAO();
    public void accepterourefuser(String choice,int id){
        evenementdao.accepterourefuser(choice,id);
    }
    public ObservableList<evenement> getevenement(){
        return evenementdao.getevenement();
    }
    public ResultSet getevenement(int id){
       return evenementdao.getevenement(id);
    }
    public  void delete(int id){
        evenementdao.delete(id);
    }
    public void Addevenement(evenement event){
        evenementdao.Addevenement(event);
    }
    public void edit(evenement event){
        evenementdao.edit(event);
    }
}
