package sample.services;

import javafx.collections.ObservableList;
import sample.Models.demandes;

public interface demandeService {
    public ObservableList<demandes> getdemandes();
    public void adddemande(demandes demande);
    public void Delete(String id);
    public void accepterourefuser(String choice,String id);
    public void modif(demandes demande);
}
