package sample.services;

import javafx.collections.ObservableList;
import sample.DAO.demandeDAO;
import sample.Models.demandes;

public class demandeServiceImpl {
    demandeDAO demandedao=new demandeDAO();
    public ObservableList<demandes> getdemandes(){
        return demandedao.getdemandes();
    }
    public void adddemande(demandes demande){
        demandedao.adddemande(demande);
    }
    public void Delete(String id){
        demandedao.Delete(id);
    }
    public void accepterourefuser(String choice,String id){
        demandedao.accepterourefuser(choice,id);
    }
    public void modif(demandes demande){
        demandedao.modif(demande);
    }
}
