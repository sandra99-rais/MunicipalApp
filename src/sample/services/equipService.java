package sample.services;

import javafx.collections.ObservableList;
import sample.Models.personnels;

public interface equipService {
    public ObservableList<personnels> getDatausers();
    public boolean verify(String cin);
    public boolean verifycin(String cin1, String cin2, String cin3, String cin4);
    public boolean verifynumequip(String numequip);
    public void Addpersonnel (personnels equip);
    public void Delete(String numequipe);

}
