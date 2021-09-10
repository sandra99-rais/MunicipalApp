package sample.services;

import javafx.collections.ObservableList;
import sample.Models.login;
import sample.Models.personnel;

public interface personnelService {
    public void add(personnel personne, login log);
    public void edit(personnel personne);
    public void delete(String cin);
    public int veryfylogin(String cin);
    public boolean veryfy(String cin);
    public ObservableList<personnel> getDatausers();
}
