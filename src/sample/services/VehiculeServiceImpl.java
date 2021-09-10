package sample.services;

import javafx.collections.ObservableList;
import sample.DAO.VehiculeDAO;
import sample.Models.voitures;

import java.sql.ResultSet;

public class VehiculeServiceImpl {
    VehiculeDAO vehiculedao = new VehiculeDAO();
    public ObservableList<voitures> getDatavoiture(){
        return  vehiculedao.getDatavoiture();
    }
    public ResultSet getDatabasevoiture(int ID){
        return vehiculedao.getDatabasevoiture(ID);
    }
    public void Delete(Integer ID){
        vehiculedao.Delete(ID);  }
    public void Ajoutervehicule(voitures vehicule){
        vehiculedao.Ajoutervehicule(vehicule);
    }
    public void edit(voitures vehicule){
        vehiculedao.edit(vehicule);
    }
}
