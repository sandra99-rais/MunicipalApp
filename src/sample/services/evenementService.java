package sample.services;

import javafx.collections.ObservableList;
import sample.Models.evenement;

import java.sql.ResultSet;

public interface evenementService {
    public void accepterourefuser(String choice,int id);
    public ObservableList<evenement> getevenement();
    public ResultSet getevenement(int id);
    public  void delete(int id);
    public void Addevenement(evenement event);
    public void edit(evenement event);
}
