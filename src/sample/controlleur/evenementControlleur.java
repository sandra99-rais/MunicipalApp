package sample.controlleur;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;

import java.net.URL;
import java.sql.*;
import java.time.LocalDate;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import sample.Models.evenement;
import sample.Models.login;
import sample.services.evenementServiceImpl;

import javax.swing.JOptionPane;
import java.lang.*;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class evenementControlleur implements Initializable{
    @FXML
    private DatePicker datedebut;

    @FXML
    private DatePicker datefin;

    @FXML
    private TextField organiser;

    @FXML
    private TextField adresse;

    @FXML
    private TextField telephone;

    @FXML
    private TextField email;

    @FXML
    private TextField heuredebut;

    @FXML
    private TextField heurefin;

    @FXML
    private ComboBox<String> type;

    @FXML
    private TextField heurerendez;

    @FXML
    private TextField nombre;

    @FXML
    private TextField lieu;

    @FXML
    private TextField lieurendez;

    @FXML
    private Button enregisterer;
    @FXML
    private TableView<evenement> table;

    @FXML
    private TableColumn<evenement, Integer> idcolumn;
    @FXML
    private TextArea description;

    @FXML
    private Button cancel;
    int heure,minute;
    @FXML
    private TextField nom;
    evenementServiceImpl evenementService=new evenementServiceImpl();
    Connection conn =null;
    ResultSet rs = null;
    PreparedStatement pst = null;
    ObservableList<evenement> listM;
    login log=null;
    int index=-1;

    public void login(login log){
        this.log=log;
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        type.getItems().addAll("La journée d'étude", "Le séminaire","La conférence et congrès","Le lancement de produit","Le team building","Les festivals","Les salons ou expositions","Les événements sportifs", "Autres");
UpdateTable();
    }
    public void UpdateTable(){
        idcolumn.setCellValueFactory(new PropertyValueFactory<evenement,Integer>("id"));


        listM = evenementService.getevenement();
        table.setItems(listM);
    }

    public boolean verifyemail(String email) {
        Pattern pattern= Pattern.compile("@");
        Matcher matcher=pattern.matcher(email);
        int count =0;
        while(matcher.find()) count++;
        Pattern patt= Pattern.compile("[.]");
        Matcher match=patt.matcher(email);
        int count2 =0;
        while(match.find()) count2++;
        int diff=email.indexOf(".")-email.indexOf("@");
        int diff2=(email.length()-1)-email.indexOf(".");
        int diff3=email.indexOf("@");
        if((count==1) && (count2==1) &&(diff>1) &&(diff2>2) && (diff3>0 ))return true;
        else return false;}

    public boolean verifyheure(String heur) {

       try{ heure=Integer.parseInt(heur.substring(0,heur.indexOf(':')));
         minute=Integer.parseInt(heur.substring(heur.indexOf(':')+1,5));}
       catch(Exception e){}

        int diff=heur.indexOf(":")-0;
        int diff2=(heur.length()-1)-heur.indexOf(":");
        int diff3=heur.indexOf(":");
        if((diff==2) &&(diff2==2) && (diff3==2 ) && (heur.matches("[0-9:]+"))&& (heure<=23) && (minute<=59))return true;
        else return false;}
    @FXML
    public void Addevenement (){

        if (!(nombre.getText().matches("[0-9]+"))) JOptionPane.showMessageDialog(null, " Veuillez saisir un nombre");
        else if(!(verifyheure(heurerendez.getText()))) JOptionPane.showMessageDialog(null, "L'heure  du rendez-vous est incorrecte");
        else if(!(verifyheure(heuredebut.getText()))) JOptionPane.showMessageDialog(null, "L'heure du début de l'événement est incorrecte");
        else if(!(verifyheure(heurefin.getText()))) JOptionPane.showMessageDialog(null, "L'heure  de la fin de l'événement est incorrecte");
        else if(! (verifyemail(email.getText()))) JOptionPane.showMessageDialog(null, "L'email n'est pas valide");
        else if (!(telephone.getText().matches("[0-9]+"))) JOptionPane.showMessageDialog(null, " Veuillez saisir un nombre");
        else if (datedebut.getValue().isAfter(datefin.getValue())) JOptionPane.showMessageDialog(null, "la date du début  doit être  avant la date de  la fin de l'événement ");
        else if (telephone.getText().toString().length()!=8) JOptionPane.showMessageDialog(null, "Le numéro du téléphone n'est pas valide ");
        else if (datedebut.getValue().isBefore(LocalDate.now())) JOptionPane.showMessageDialog(null, "  la date de début  l'événement  n'est pas prévue aujourd'hui ");
        else if (type.getSelectionModel().isEmpty()) JOptionPane.showMessageDialog(null, "  Veuillez séléctionner un Models ");
        else if (description.getText().trim().isEmpty()) JOptionPane.showMessageDialog(null, "  votre description est vide ");

        else{


                            evenement event=new evenement(nom.getText(), type.getValue(),organiser.getText(),
                             lieu.getText(), adresse.getText(),lieurendez.getText(), heurerendez.getText(),
                            Integer.parseInt(nombre.getText()),java.sql.Date.valueOf( datedebut.getValue()),heuredebut.getText(),
                                    java.sql.Date.valueOf( datefin.getValue())
                                    , heurefin.getText(),  Integer.parseInt(telephone.getText())
                                    ,email.getText(),"undecided",description.getText());

                          evenementService.Addevenement(event);


                    }
        UpdateTable();
                }

    public void cancelButtonOnAction(ActionEvent event){
        Stage stage =(Stage) cancel.getScene().getWindow();
        stage.close();
    }
    @FXML
    public void getselected() throws SQLException {
        index = table.getSelectionModel().getSelectedIndex();
        if (index <= -1) {

            return;}
            ResultSet rs=evenementService.getevenement(idcolumn.getCellData(index));
        while (rs.next()){
            nom.setText(rs.getString("NOM"));
            type.setValue(rs.getString("TYPE"));
            organiser.setText(rs.getString("ORGANISEUR"));
            lieu.setText(rs.getString("LIEU"));
            adresse.setText(rs.getString("ADRESSE"));
            lieurendez.setText(rs.getString("LIEURENDEZVOUS"));
            heurerendez.setText(rs.getString("HEURERENDEZVOUS"));
            nombre.setText((rs.getString("NMBRPERSONNES")).toString());
            datedebut.setValue(rs.getDate("DUREEDU").toLocalDate());
            heuredebut.setText((rs.getString("HEUREDU")));
            datefin.setValue(rs.getDate("DUREEAU").toLocalDate());
            heurefin.setText((rs.getString("HEUREAU")));
            telephone.setText(rs.getString("TELEPHONE"));
            email.setText(rs.getString("EMAIL"));
            description.setText(rs.getString("DESCRIPTION"));




        }


    }
    @FXML
    public void Edit (){
        if (index <= -1) {

            return;
        }

        if (!(nombre.getText().matches("[0-9]+"))) JOptionPane.showMessageDialog(null, " Veuillez saisir un nombre");
        else if(!(verifyheure(heurerendez.getText()))) JOptionPane.showMessageDialog(null, "L'heure  du rendez-vous est incorrecte");
        else if(!(verifyheure(heuredebut.getText()))) JOptionPane.showMessageDialog(null, "L'heure du début de l'événement est incorrecte");
        else if(!(verifyheure(heurefin.getText()))) JOptionPane.showMessageDialog(null, "L'heure  de la fin de l'événement est incorrecte");
        else if(! (verifyemail(email.getText()))) JOptionPane.showMessageDialog(null, "L'email n'est pas valide");
        else if (!(telephone.getText().matches("[0-9]+"))) JOptionPane.showMessageDialog(null, " Veuillez saisir un nombre");
        else if (datedebut.getValue().isAfter(datefin.getValue())) JOptionPane.showMessageDialog(null, "la date du début  doit être  avant la date de  la fin de l'événement ");
        else if (telephone.getText().toString().length()!=8) JOptionPane.showMessageDialog(null, "Le numéro du téléphone n'est pas valide ");
        else if (type.getSelectionModel().isEmpty()) JOptionPane.showMessageDialog(null, "  Veuillez séléctionner un Models ");
        else if (description.getText().trim().isEmpty()) JOptionPane.showMessageDialog(null, "  votre description est vide ");

        else{



            evenement event=new evenement(idcolumn.getCellData(index),nom.getText(), type.getValue(),organiser.getText(),
                    lieu.getText(), adresse.getText(),lieurendez.getText(), heurerendez.getText(),
                    Integer.parseInt(nombre.getText()),java.sql.Date.valueOf( datedebut.getValue()),heuredebut.getText(),
                    java.sql.Date.valueOf( datefin.getValue())
                    , heurefin.getText(),  Integer.parseInt(telephone.getText())
                    ,email.getText(),description.getText());

            evenementService.edit(event);


        }
        UpdateTable();
    }

    }

