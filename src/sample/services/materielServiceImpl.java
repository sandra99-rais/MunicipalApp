package sample.services;

import javafx.collections.ObservableList;
import sample.DAO.materielDAO;
import sample.Models.Materiels;

import java.sql.ResultSet;

public class materielServiceImpl {
    materielDAO materieldao=new materielDAO();
    public ObservableList<Materiels> getDatamateriel(){
        return  materieldao.getDatamateriel();
    }
    public ResultSet getDatabasemateriel(int ID){
        return  materieldao.getDatabasemateriel(ID);
    }
    public void Delete(Integer ID){
        materieldao.Delete(ID);  }
    public void AjoutMateriel (Materiels materiel){
        materieldao.AjoutMateriel(materiel);}
        public void edit(Materiels materiel){
            materieldao.edit(materiel);

    }
}
