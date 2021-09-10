package sample.services;

import javafx.collections.ObservableList;
import sample.DAO.travauxDAO;
import sample.Models.travaux;

import java.sql.ResultSet;

public class travauxServiceImpl {
    travauxDAO travauxdao=new travauxDAO();
    public ResultSet getDatabaseTravaux(int ID){
        return travauxdao.getDatabaseTravaux(ID);
    }
    public ObservableList<travaux> getDatabaseTravaux( ){
        return travauxdao.getDatabaseTravaux();
    }
    public void Delete(int ID){
        travauxdao.Delete(ID);
    }
    public void AjoutTravaux(travaux trav){
        travauxdao.AjoutTravaux(trav);
    }
    public void edit(travaux trav){
        travauxdao.edit(trav);
    }
}
