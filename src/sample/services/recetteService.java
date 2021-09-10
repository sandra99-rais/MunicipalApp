package sample.services;

import javafx.collections.ObservableList;
import sample.Models.Recette;

import java.sql.ResultSet;

public interface recetteService {
    public ObservableList<Recette> getDataRecette();
    public ResultSet getRecette(int Id);
    public void addrecette(Recette recette);
    public boolean verif(int id);
    public void Delete(String id);
    public void modif(Recette recette);
}
