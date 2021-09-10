package sample.controlleur;

import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import sample.connexion.Databasecon;
import sample.Models.demandes;
import sample.Models.login;
import sample.services.demandeServiceImpl;

import javax.swing.*;
import java.net.URL;
import java.sql.*;
import java.time.LocalDate;
import java.util.Date;
import java.util.ResourceBundle;

public class demandescontrolleur implements Initializable {
    @FXML
    private TextField cin;

    @FXML
    private TextField demande;

    @FXML
    private TextField telephone;

    @FXML
    private TextField lieu;

    @FXML
    private DatePicker datedemande;

    @FXML
    private TableView<demandes> table;

    @FXML
    private TableColumn<demandes, String> tableid;

    @FXML
    private TableColumn<demandes, String> tablecin;

    @FXML
    private TableColumn<demandes, String> tabledemande;

    @FXML
    private TableColumn<demandes, String> tabletelephone;

    @FXML
    private TableColumn<demandes, Date> tabledate;

    @FXML
    private TableColumn<demandes, String> tablelieu;

    @FXML
    private TableColumn<demandes, String> tabledecision;
    @FXML
    private TableColumn<demandes, String> typetable;

    @FXML
    private Button RETOUR;
    @FXML
    private TextField filteredField;
    @FXML
    private ComboBox<String> typedemande;



    ObservableList<demandes> listM;
    ObservableList<demandes> dataList;
    Connection conn =null;
    PreparedStatement pst = null;
    int index=-1;
    demandeServiceImpl demandeservice =new demandeServiceImpl();

    login log=null;
    public void login(login log){
        this.log=log;
    }

    @Override
    public void initialize(URL file, ResourceBundle resourceBundle) {
typedemande.getItems().addAll("Demande","Réclamation");



        tableid.setCellValueFactory(new PropertyValueFactory<demandes, String>("ID_DEMANDE"));
        tablecin.setCellValueFactory(new PropertyValueFactory<demandes, String>("CIN"));
        tabledemande.setCellValueFactory(new PropertyValueFactory<demandes, String>("DEMANDE"));
        tabletelephone.setCellValueFactory(new PropertyValueFactory<demandes, String>("TELEPHONE"));
        tabledate.setCellValueFactory(new PropertyValueFactory<demandes, Date>("datedemande"));
        tablelieu.setCellValueFactory(new PropertyValueFactory<demandes, String>("lieu"));
        tabledecision.setCellValueFactory(new PropertyValueFactory<demandes, String>("decision"));
        typetable.setCellValueFactory(new PropertyValueFactory<demandes, String>("type"));



        listM = demandeservice.getdemandes();
        table.setItems(listM);

        serachpersonnel();



    }
    public void UpdateTable(){
        tableid.setCellValueFactory(new PropertyValueFactory<demandes, String>("ID_DEMANDE"));
        tablecin.setCellValueFactory(new PropertyValueFactory<demandes, String>("CIN"));
        tabledemande.setCellValueFactory(new PropertyValueFactory<demandes, String>("DEMANDE"));
        tabletelephone.setCellValueFactory(new PropertyValueFactory<demandes, String>("TELEPHONE"));
        tabledate.setCellValueFactory(new PropertyValueFactory<demandes, Date>("datedemande"));
        tablelieu.setCellValueFactory(new PropertyValueFactory<demandes, String>("lieu"));
        tabledecision.setCellValueFactory(new PropertyValueFactory<demandes, String>("decision"));
        typetable.setCellValueFactory(new PropertyValueFactory<demandes, String>("type"));


        listM = demandeservice.getdemandes();
        table.setItems(listM);
    }
    @FXML
    void serachpersonnel() {
        tableid.setCellValueFactory(new PropertyValueFactory<demandes, String>("ID_DEMANDE"));
        tablecin.setCellValueFactory(new PropertyValueFactory<demandes, String>("CIN"));
        tabledemande.setCellValueFactory(new PropertyValueFactory<demandes, String>("DEMANDE"));
        tabletelephone.setCellValueFactory(new PropertyValueFactory<demandes, String>("TELEPHONE"));
        tabledate.setCellValueFactory(new PropertyValueFactory<demandes, Date>("datedemande"));
        tablelieu.setCellValueFactory(new PropertyValueFactory<demandes, String>("lieu"));
        tabledecision.setCellValueFactory(new PropertyValueFactory<demandes, String>("decision"));
        typetable.setCellValueFactory(new PropertyValueFactory<demandes, String>("type"));



        dataList = demandeservice.getdemandes();
        table.setItems(dataList);
        FilteredList<demandes> filteredData = new FilteredList<>(dataList, b -> true);
        filteredField.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(person -> {
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }
                String lowerCaseFilter = newValue.toLowerCase();


                if (person.getCIN().toLowerCase().indexOf(lowerCaseFilter) != -1 ) {
                    return true; //
                }else if (person.getID_DEMANDE().toLowerCase().indexOf(lowerCaseFilter) != -1 ) {
                    return true;}
                else if (person.getTELEPHONE().toLowerCase().indexOf(lowerCaseFilter) != -1 ) {
                    return true;}
                else if (person.getLieu().toLowerCase().indexOf(lowerCaseFilter) != -1 ) {
                    return true;}
                else if (person.getDecision().toLowerCase().indexOf(lowerCaseFilter) != -1 ) {
                    return true;}
                else if (person.getDEMANDE().toLowerCase().indexOf(lowerCaseFilter) != -1 ) {
                    return true;}
                else if (person.getType().toLowerCase().indexOf(lowerCaseFilter) != -1 ) {
                    return true;}
                else if (person.getDatedemande().toString().toLowerCase().indexOf(lowerCaseFilter) != -1 ) {
                    return true;}


                else
                    return false; // Does not match.
            });
        });
        SortedList<demandes> sortedData = new SortedList<>(filteredData);
        sortedData.comparatorProperty().bind(table.comparatorProperty());
        table.setItems(sortedData);
    }


    @FXML
    public void adddemande (){
        if (!(cin.getText().matches("[0-9]+"))) JOptionPane.showMessageDialog(null, "Num Cin n'est pas valide");
        else if(!((cin.getText().length()==8))) JOptionPane.showMessageDialog(null, "Num Cin n'est pas valide");
        else if(! (telephone.getText().matches("[0-9]+")) ) JOptionPane.showMessageDialog(null, "le Numero de téléphone n'est pas valide");
        else if(!((telephone.getText().length()==8))) JOptionPane.showMessageDialog(null, "Le numero de téléphone n'est pas valide");
        else if(datedemande.getValue().isBefore(LocalDate.now())) JOptionPane.showMessageDialog(null, "Veuillez saisir  une date à partir de la date d'aujourd'hui");
        else{
                            demandes demandee=new demandes(cin.getText(),demande.getText(),telephone.getText(),java.sql.Date.valueOf(datedemande.getValue()),lieu.getText(),"undecided",typedemande.getValue());
                            demandeservice.adddemande(demandee);
            }
           UpdateTable();
           serachpersonnel();
                       }


    public void Delete(){
        int index = table.getSelectionModel().getSelectedIndex();
        if (index <= -1){

            return;
        }
        int reply=JOptionPane.showConfirmDialog(null,"confirmer votre decision");
        if(reply==JOptionPane.YES_OPTION){
        demandeservice.Delete(tableid.getCellData(index));
        UpdateTable();
        serachpersonnel();}

    }
    public void getSelected() {

     index = table.getSelectionModel().getSelectedIndex();
        if (index <= -1){

            return;
        }

        cin.setText(tablecin.getCellData(index).toString());
        demande.setText(tabledemande.getCellData(index).toString());
        telephone.setText(tabletelephone.getCellData(index).toString());
        lieu.setText(tablelieu.getCellData(index).toString());
        datedemande.setValue(LocalDate.parse(tabledate.getCellData(index).toString()));
        typedemande.setValue(typetable.getCellData(index));






    }
    public void modif(){
        if (index <= -1){

            return;
        }
        conn = Databasecon.getConnexion();
        String sql = "update DEMANDES set cin=?,TELEPHONE=?,DEMANDE=?,LIEU=?,DATEDEMANDE=?,TYPE=? WHERE ID_DEMANDE='"+tableid.getCellData(index).toString()+"'";
            if (!(cin.getText().matches("[0-9]+"))) JOptionPane.showMessageDialog(null, "Num Cin n'est pas valide");


            else if(!((cin.getText().length()==8))) JOptionPane.showMessageDialog(null, "Num Cin n'est pas valide");
            else if(! (telephone.getText().matches("[0-9]+")) ) JOptionPane.showMessageDialog(null, "Le numéro de téléphone n'est pas valide");
            else if(!((telephone.getText().length()==8))) JOptionPane.showMessageDialog(null, "Num Cin n'est pas valide");
            else if(datedemande.getValue().isBefore(LocalDate.now())) JOptionPane.showMessageDialog(null, "Veuillez saisir  une date à partir de la date d'aujourd'hui");

        else{
                demandes demandee=new demandes(tableid.getCellData(index),cin.getText(),demande.getText(),telephone.getText(),java.sql.Date.valueOf(datedemande.getValue()),lieu.getText(),"undecided",typedemande.getValue());
                demandeservice.modif(demandee);
            UpdateTable();
            serachpersonnel();

        }
    }
    public void retour() {
        Stage stage = (Stage) RETOUR.getScene().getWindow();
        stage.close();

    }
    public void Accepter() throws SQLException {

        if(log.getJob().equals("admin")){
            if (index <= -1) {

                return;
            }

            String d=tableid.getCellData(index);
       demandeservice.accepterourefuser("Accepted",d);
        UpdateTable();}
        else
            JOptionPane.showMessageDialog(null, "Vous n'êtes pas l'admin");


    }
    public void Refuser() throws SQLException {

        if(log.getJob().equals("admin")){
            if (index <= -1) {

                return;
            }

            String d=tableid.getCellData(index);
        demandeservice.accepterourefuser("Refused",d);
        UpdateTable();}
        else
            JOptionPane.showMessageDialog(null, "Vous n'êtes pas l'admin");


    }
}




