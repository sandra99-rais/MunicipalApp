package sample.services;

import javafx.collections.ObservableList;
import sample.DAO.recetteDAO;
import sample.Models.Recette;

import java.sql.ResultSet;

public class recetteServiceImpl {
    recetteDAO recettedao=new recetteDAO();
    public ObservableList<Recette> getDataRecette(){
        return  recettedao.getDataRecette();
    }
    public ResultSet getRecette(int Id){
        return recettedao.getRecette(Id);
    }
    public void addrecette(Recette recette){
        recettedao.addrecette(recette);
    }
    public boolean verif(int id){
        return recettedao.verif(id) ;
    }
    public void Delete(String id){
        recettedao.Delete(id);
    }
    public void modif(Recette recette){
        recettedao.modif(recette);
    }
}
