package sample.services;

import sample.DAO.verifDAO;

import java.sql.ResultSet;
import java.sql.SQLException;

public class verifServiceImpl {
    verifDAO verifdao=new verifDAO();
    public boolean verify(String cin) {
       return  verifdao.verify(cin) ;
    }
    public ResultSet getDatausers(String cin) throws SQLException {
        return verifdao.getDatausers(cin);
    }
}
