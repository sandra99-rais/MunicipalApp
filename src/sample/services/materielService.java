package sample.services;

import javafx.collections.ObservableList;
import sample.Models.Materiels;

import java.sql.ResultSet;

public interface materielService {
    public ResultSet getDatabasemateriel(int ID);
    public ObservableList<Materiels> getDatamateriel();
    public void Delete(Integer ID);
    public void AjoutMateriel (Materiels materiel);
    public void edit(Materiels materiel);
}
