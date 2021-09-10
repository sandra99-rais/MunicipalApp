package sample.services;

import javafx.collections.ObservableList;
import sample.Models.travaux;

import java.sql.ResultSet;

public interface travauxService {
    public ResultSet getDatabaseTravaux(int ID);
    public ObservableList<travaux> getDatabaseTravaux( );
    public void Delete(int ID);
    public void AjoutTravaux(travaux trav);
    public void edit(travaux trav);
}
