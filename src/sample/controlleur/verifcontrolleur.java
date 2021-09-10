package sample.controlleur;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import sample.services.loginServiceImpl;
import sample.services.verifServiceImpl;
import sample.Models.login;

import javax.swing.*;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public class verifcontrolleur {
    @FXML
    private TextField email;

    @FXML
    private TextField cin;

    @FXML
    private TextField prenom;

    @FXML
    private TextField nom;
    @FXML
    private Button RETOUR;

    @FXML
    private DatePicker date;
    Connection conn =null;
    loginServiceImpl loginService=new loginServiceImpl();


  verifServiceImpl verifservice=new verifServiceImpl();
    public void verif() throws SQLException {
        if((!(cin.getText().isEmpty())) && (!(nom.getText().isEmpty())) && (!(prenom.getText().isEmpty())) && (!(email.getText().isEmpty())) && (!(date.getValue()==null)) ){
if(verifservice.verify(cin.getText()) ){
   ResultSet rs=verifservice.getDatausers(cin.getText());
   while(rs.next()) {
       if (nom.getText().equals(rs.getString("nom")) && prenom.getText().equals(rs.getString("prenom")) && email.getText().equals(rs.getString("email")) && java.sql.Date.valueOf(date.getValue()).equals(java.sql.Date.valueOf(String.valueOf(rs.getDate("datenaissance"))))) {
           login log = loginService.getlogin(cin.getText());
           JOptionPane.showMessageDialog(null, "Votre mot de passe est (  " + log.getMdp()+"  )");

       } else JOptionPane.showMessageDialog(null, "Veuillez vérifier vos informations");
   }
} else          JOptionPane.showMessageDialog(null, "Le  personnel n'existe pas");


        }else
            JOptionPane.showMessageDialog(null, "Veuillez remplir tous les champs");
    }
    public void retour() {
        Stage stage = (Stage) RETOUR.getScene().getWindow();
        stage.close();

    }
}
