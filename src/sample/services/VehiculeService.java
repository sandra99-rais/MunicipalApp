package sample.services;

import javafx.collections.ObservableList;
import sample.Models.voitures;

import java.sql.ResultSet;

public interface VehiculeService {
    public ObservableList<voitures> getDatavoiture();
    public ResultSet getDatabasevoiture(int ID);
    public void Delete(Integer ID);
    public void Ajoutervehicule(voitures vehicule);
    public void edit(voitures vehicule);
}
