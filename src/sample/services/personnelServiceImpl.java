package sample.services;

import javafx.collections.ObservableList;
import sample.DAO.personnelDAO;
import sample.Models.login;
import sample.Models.personnel;

public class personnelServiceImpl {
    personnelDAO personneldao = new personnelDAO();
    public void add(personnel personne, login log) {
        personneldao.add(personne,log);
    }
    public void edit(personnel personne) {
        personneldao.Edit(personne);
    }

    public int verifylogin(String cin) {
        return personneldao.verifylogin(cin);
    }
    public boolean verify(String cin) {
        return personneldao.verify(cin);
    }

    public void delete(String cin) {
        personneldao.Delete(cin);
    }
    public ObservableList<personnel> getDatausers(){return personneldao.getDatausers();}
}
