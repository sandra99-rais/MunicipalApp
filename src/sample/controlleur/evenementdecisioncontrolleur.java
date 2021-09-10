package sample.controlleur;

import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import sample.Models.evenement;
import sample.Models.login;
import sample.services.evenementServiceImpl;

import javax.swing.*;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.ResourceBundle;

public class evenementdecisioncontrolleur implements Initializable {
    @FXML
    private TableView<evenement> table;

    @FXML
    private TableColumn<evenement,Integer> id;

    @FXML
    private TableColumn<evenement,String> nom;

    @FXML
    private TableColumn<evenement,String> type;

    @FXML
    private TableColumn<evenement,String> organiseur;

    @FXML
    private TableColumn<evenement,String> lieu;

    @FXML
    private TableColumn<evenement,String> adresse;

    @FXML
    private TableColumn<evenement,String> lieurendezvous;

    @FXML
    private TableColumn<evenement,String> heurerendezvous;

    @FXML
    private TableColumn<evenement,String> nombrespersonne;

    @FXML
    private TableColumn<evenement, Date> datedebut;

    @FXML
    private TableColumn<evenement,String> heuredebut;

    @FXML
    private TableColumn<evenement, Date> datefin;

    @FXML
    private TableColumn<evenement,String> heurefin;

    @FXML
    private TableColumn<evenement,Integer> telephone;

    @FXML
    private TableColumn<evenement,String> mail;
    @FXML
    private Button RETOUR;
    ObservableList<evenement> dataList;
    @FXML
    private TextField FilteredField;

    @FXML
    private TableColumn<evenement,String> decision;
    evenementServiceImpl evenementservice=new evenementServiceImpl();
    ObservableList<evenement> listM;
    PreparedStatement pst = null;
    Connection conn =null;
    int index = -1;
    login log;
    public void login(login log){
        this.log=log;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        id.setCellValueFactory(new PropertyValueFactory<evenement,Integer>("id"));
        nom.setCellValueFactory(new PropertyValueFactory<evenement,String>("nom"));
        type.setCellValueFactory(new PropertyValueFactory<evenement,String>("type"));
        organiseur.setCellValueFactory(new PropertyValueFactory<evenement,String>("organiseur"));
        lieu.setCellValueFactory(new PropertyValueFactory<evenement,String>("lieu"));
        adresse.setCellValueFactory(new PropertyValueFactory<evenement,String>("adresse"));
        lieurendezvous.setCellValueFactory(new PropertyValueFactory<evenement,String>("LIEURENDEZVOUS"));
        heurerendezvous.setCellValueFactory(new PropertyValueFactory<evenement,String>("HEURERENDEZVOUS"));
        nombrespersonne.setCellValueFactory(new PropertyValueFactory<evenement,String>("NMBRPERSONNES"));
        datedebut.setCellValueFactory(new PropertyValueFactory<evenement,Date>("DUREEDU"));
        heuredebut.setCellValueFactory(new PropertyValueFactory<evenement,String>("HEUREDU"));
        datefin.setCellValueFactory(new PropertyValueFactory<evenement,Date>("DUREEAU"));
        heurefin.setCellValueFactory(new PropertyValueFactory<evenement,String>("HEUREAU"));
        telephone.setCellValueFactory(new PropertyValueFactory<evenement,Integer>("TELEPHONE"));
        mail.setCellValueFactory(new PropertyValueFactory<evenement,String>("EMAIL"));
        decision.setCellValueFactory(new PropertyValueFactory<evenement,String>("Descision"));
        listM = evenementservice.getevenement();
        table.setItems(listM);
        serachpersonnel();
    }
    public void UpdateTable(){
        id.setCellValueFactory(new PropertyValueFactory<evenement,Integer>("id"));
        nom.setCellValueFactory(new PropertyValueFactory<evenement,String>("nom"));
        type.setCellValueFactory(new PropertyValueFactory<evenement,String>("type"));
        organiseur.setCellValueFactory(new PropertyValueFactory<evenement,String>("organiseur"));
        lieu.setCellValueFactory(new PropertyValueFactory<evenement,String>("lieu"));
        adresse.setCellValueFactory(new PropertyValueFactory<evenement,String>("adresse"));
        lieurendezvous.setCellValueFactory(new PropertyValueFactory<evenement,String>("LIEURENDEZVOUS"));
        heurerendezvous.setCellValueFactory(new PropertyValueFactory<evenement,String>("HEURERENDEZVOUS"));
        nombrespersonne.setCellValueFactory(new PropertyValueFactory<evenement,String>("NMBRPERSONNES"));
        datedebut.setCellValueFactory(new PropertyValueFactory<evenement,Date>("DUREEDU"));
        heuredebut.setCellValueFactory(new PropertyValueFactory<evenement,String>("HEUREDU"));
        datefin.setCellValueFactory(new PropertyValueFactory<evenement,Date>("DUREEAU"));
        heurefin.setCellValueFactory(new PropertyValueFactory<evenement,String>("HEUREAU"));
        telephone.setCellValueFactory(new PropertyValueFactory<evenement,Integer>("TELEPHONE"));
        mail.setCellValueFactory(new PropertyValueFactory<evenement,String>("EMAIL"));
        decision.setCellValueFactory(new PropertyValueFactory<evenement,String>("Descision"));

        listM = evenementservice.getevenement();
        table.setItems(listM);
    }
    public void Accepter() throws SQLException {
        if(log.getJob().equals("admin")){
            if (index <= -1) {

                return;
            }

        int d=id.getCellData(index);
        evenementservice.accepterourefuser("Accepted",d);

        UpdateTable();
        serachpersonnel();
    } else JOptionPane.showMessageDialog(null, "Vous n'êtes pas l'admin");


    }
    public void Refuser() throws SQLException {
        if(log.getJob().equals("admin")){
            if (index <= -1) {

                return;
            }

            int d=id.getCellData(index);
        evenementservice.accepterourefuser("Refused",d);
        UpdateTable();
    } else JOptionPane.showMessageDialog(null, "Vous n'êtes pas l'admin");


    }
    public void retour() {
        Stage stage = (Stage) RETOUR.getScene().getWindow();
        stage.close();

    }
    @FXML
    void serachpersonnel() {
        id.setCellValueFactory(new PropertyValueFactory<evenement,Integer>("id"));
        nom.setCellValueFactory(new PropertyValueFactory<evenement,String>("nom"));
        type.setCellValueFactory(new PropertyValueFactory<evenement,String>("type"));
        organiseur.setCellValueFactory(new PropertyValueFactory<evenement,String>("organiseur"));
        lieu.setCellValueFactory(new PropertyValueFactory<evenement,String>("lieu"));
        adresse.setCellValueFactory(new PropertyValueFactory<evenement,String>("adresse"));
        lieurendezvous.setCellValueFactory(new PropertyValueFactory<evenement,String>("LIEURENDEZVOUS"));
        heurerendezvous.setCellValueFactory(new PropertyValueFactory<evenement,String>("HEURERENDEZVOUS"));
        nombrespersonne.setCellValueFactory(new PropertyValueFactory<evenement,String>("NMBRPERSONNES"));
        datedebut.setCellValueFactory(new PropertyValueFactory<evenement,Date>("DUREEDU"));
        heuredebut.setCellValueFactory(new PropertyValueFactory<evenement,String>("HEUREDU"));
        datefin.setCellValueFactory(new PropertyValueFactory<evenement,Date>("DUREEAU"));
        heurefin.setCellValueFactory(new PropertyValueFactory<evenement,String>("HEUREAU"));
        telephone.setCellValueFactory(new PropertyValueFactory<evenement,Integer>("TELEPHONE"));
        mail.setCellValueFactory(new PropertyValueFactory<evenement,String>("EMAIL"));
        decision.setCellValueFactory(new PropertyValueFactory<evenement,String>("Descision"));

        dataList = evenementservice.getevenement();
        table.setItems(dataList);
        FilteredList<evenement> filteredData = new FilteredList<>(dataList, b -> true);
        FilteredField.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(evenement -> {
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }
                String lowerCaseFilter = newValue.toLowerCase();


                if ( new String(String.valueOf(evenement.getId())).toLowerCase().indexOf(lowerCaseFilter) != -1 ) {
                    return true; //
                }else if (evenement.getNom().toLowerCase().indexOf(lowerCaseFilter) != -1 ) {
                    return true;}
                else if (evenement.getType().toLowerCase().indexOf(lowerCaseFilter) != -1 ) {
                    return true;}
                else if (evenement.getOrganiseur().toLowerCase().indexOf(lowerCaseFilter) != -1 ) {
                    return true;}
                else if (evenement.getLieu().toLowerCase().indexOf(lowerCaseFilter) != -1 ) {
                    return true;}
                else if (evenement.getAdresse().toLowerCase().indexOf(lowerCaseFilter) != -1 ) {
                    return true;}
                else if (evenement.getLIEURENDEZVOUS().toLowerCase().indexOf(lowerCaseFilter) != -1 ) {
                    return true;}
                else if (evenement.getHEURERENDEZVOUS().toLowerCase().indexOf(lowerCaseFilter) != -1 ) {
                    return true;}
                else if (new String(String.valueOf(evenement.getNMBRPERSONNES())).toLowerCase().indexOf(lowerCaseFilter) != -1 ) {
                    return true;}
                else if (evenement.getNom().toLowerCase().indexOf(lowerCaseFilter) != -1 ) {
                    return true;}
                else if (new String(String.valueOf(evenement.getDUREEDU())).toLowerCase().indexOf(lowerCaseFilter) != -1 ) {
                    return true;}
                else if (evenement.getHEUREDU().toLowerCase().indexOf(lowerCaseFilter) != -1 ) {
                    return true;}
                else if (new String(String.valueOf(evenement.getDUREEAU())).toLowerCase().indexOf(lowerCaseFilter) != -1 ) {
                    return true;}
                else if (evenement.getHEUREAU().toLowerCase().indexOf(lowerCaseFilter) != -1 ) {
                    return true;}
                else if (new String(String.valueOf(evenement.getTELEPHONE())).toLowerCase().indexOf(lowerCaseFilter) != -1 ) {
                    return true;}
                else if (evenement.getEMAIL().toLowerCase().indexOf(lowerCaseFilter) != -1 ) {
                    return true;}
                else if (evenement.getDescision().toLowerCase().indexOf(lowerCaseFilter) != -1 ) {
                    return true;}

                else
                    return false; // Does not match.
            });
        });
        SortedList<evenement> sortedData = new SortedList<>(filteredData);
        sortedData.comparatorProperty().bind(table.comparatorProperty());
        table.setItems(sortedData);
    }
    public void Delete() {
        if(log.getJob().equals("admin")){


        int index = table.getSelectionModel().getSelectedIndex();
        if (index <= -1) {

            return;
        }
            int reply=JOptionPane.showConfirmDialog(null,"confirmer votre decision");
            if(reply==JOptionPane.YES_OPTION){
            evenementservice.delete(id.getCellData(index));
            UpdateTable();
            serachpersonnel();}

    } else JOptionPane.showMessageDialog(null, "Vous n'êtes pas l'admin");

    }
    public void getselected() {
        index = table.getSelectionModel().getSelectedIndex();
        if (index <= -1) {

            return;}



        ResultSet rs=evenementservice.getevenement(id.getCellData(index));
        try{
        while (rs.next()) {
        JOptionPane.showMessageDialog(null, "DESCRIPTION :\n" +rs.getString("DESCRIPTION"));
        }
        }catch(Exception e){JOptionPane.showMessageDialog(null, e);}



    }


}

