package sample.services;

import javafx.collections.ObservableList;
import sample.DAO.equipDAO;
import sample.Models.personnels;

public class equipServiceImpl {
    equipDAO equipdao=new equipDAO();
    public ObservableList<personnels> getDatausers(){
        return equipdao.getDatausers();
    }
    public boolean verify(String cin){
        return equipdao.verify(cin);
    }


    public boolean verifycin(String cin1, String cin2, String cin3, String cin4) {
        return equipdao.verifycin(cin1,cin2,cin3,cin4);
    }

    public boolean verifynumequip(String numequip) {
        return equipdao.verifynumequip(numequip);
    }
    public void Addpersonnel (personnels equip){
        equipdao.Addpersonnel(equip);
    }
    public void Delete(String numequipe){

        equipdao.Delete(numequipe);
    }
}
