package sample.connexion;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import sample.Models.*;

import javax.swing.*;
import java.sql.*;

public class Databasecon  {
    public static Connection databaseLink;
    public static Connection getConnexion(){
        String databaseUser ="System";
        String databasePassword="Sandra";
        String url ="jdbc:oracle:thin:@localhost:1521:XE";
        try{
            Class.forName("oracle.jdbc.OracleDriver");

            databaseLink=DriverManager.getConnection(url,databaseUser,databasePassword);

        }catch(Exception e){
            e.printStackTrace();
            e.getCause();
        }
        return databaseLink;
    }

    }





