package sample.services;

import sample.Models.login;

public interface loginService {
    public boolean verif(String cin, String mdp) ;
    public login getlogin(String cin);
}

