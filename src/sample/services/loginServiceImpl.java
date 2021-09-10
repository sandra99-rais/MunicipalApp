package sample.services;

import sample.DAO.loginDAO;
import sample.Models.login;

public class loginServiceImpl {
    loginDAO logindao=new loginDAO();
    public boolean verif(String cin, String mdp) {
      return  logindao.verif(cin,mdp);
    }
    public login getlogin(String cin){
        return logindao.getlogin(cin);
    }
}
