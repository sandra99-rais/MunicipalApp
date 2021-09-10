package sample.controlleur;

import javafx.collections.ObservableList;

import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;

import java.io.IOException;
import java.sql.*;
import java.util.Date;
import java.time.LocalDate;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import sample.Models.Recette;
import sample.Models.login;
import sample.services.recetteServiceImpl;

import javax.swing.*;
import java.net.URL;


import java.util.ResourceBundle;


public class ControllerRecette implements  Initializable {




    @FXML
    private Button Cancel;

    @FXML
    private Button supp;

    @FXML
    private Button ajouter;

    @FXML
    private TextField gainTextfield;

    @FXML
    private TextField depenseTextfield;

    @FXML
    private TextField soldeTextfield;

    @FXML
    private Button modifier;

    @FXML
    private ComboBox<String> type_operationComboBox;

    @FXML
    private ComboBox<String> rubriqueComboBox;

    @FXML
    private DatePicker dateDatePicker;

    @FXML
    private ComboBox<String> nomComboBox;

    @FXML
    private TextField idTextfield;

    @FXML
    private TextField search;

    @FXML
    private TableView<Recette> tableview;

    @FXML
    private TableColumn<Recette, Date> date;

    @FXML
    private TableColumn<Recette, String> Type_operation;

    @FXML
    private TableColumn<Recette, String> Rubrique;

    @FXML
    private TableColumn<Recette, String> Nom;

    @FXML
    private TableColumn<Recette, Integer> Id;

    @FXML
    private TableColumn<Recette, Float> Gain;

    @FXML
    private TableColumn<Recette,Float > Depense;

    @FXML
    private TableColumn<Recette, Float> Solde;
    @FXML
    private Label soldelabel;

    @FXML
    private Button viewIcon;

    ObservableList<Recette> listM;
    ObservableList<Recette> dataList;
    int index = -1;
    Connection conn =null;
    ResultSet rs = null;
    PreparedStatement pst = null;PreparedStatement pst1 = null;PreparedStatement pst2 = null;
    recetteServiceImpl recetteservice=new recetteServiceImpl();
    login log=null;
    public void login(login log){
        this.log=log;
    }
    public void UpdateTable() {

        date.setCellValueFactory(new PropertyValueFactory<Recette,Date>("Date"));
        Type_operation.setCellValueFactory(new PropertyValueFactory<Recette, String>("Type_operation"));
        Rubrique.setCellValueFactory(new PropertyValueFactory<Recette, String>("Rubrique"));
        Nom.setCellValueFactory(new PropertyValueFactory<Recette, String>("Nom"));
        Id.setCellValueFactory(new PropertyValueFactory<Recette, Integer>("Id"));
        Gain.setCellValueFactory(new PropertyValueFactory<Recette, Float>("Gain"));
        Depense.setCellValueFactory(new PropertyValueFactory<Recette, Float>("Depense"));
        Solde.setCellValueFactory(new PropertyValueFactory<Recette,Float>("Solde"));

        listM = recetteservice.getDataRecette();
        tableview.setItems(listM);
    }
    @FXML
    private void searchRecette() {
        date.setCellValueFactory(new PropertyValueFactory<Recette,Date>("Date"));
        Type_operation.setCellValueFactory(new PropertyValueFactory<Recette, String>("Type_operation"));
        Rubrique.setCellValueFactory(new PropertyValueFactory<Recette, String>("Rubrique"));
        Nom.setCellValueFactory(new PropertyValueFactory<Recette, String>("Nom"));
        Id.setCellValueFactory(new PropertyValueFactory<Recette, Integer>("Id"));
        Gain.setCellValueFactory(new PropertyValueFactory<Recette, Float>("Gain"));
        Depense.setCellValueFactory(new PropertyValueFactory<Recette, Float>("Depense"));
        Solde.setCellValueFactory(new PropertyValueFactory<Recette,Float>("Solde"));

        dataList = recetteservice.getDataRecette();
        tableview.setItems(listM);


        FilteredList<Recette> filteredData = new FilteredList<>(dataList, b -> true);
        search.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(recette -> {
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }
                String lowerCaseFilter = newValue.toLowerCase();


                if (new String(String.valueOf(recette.getDate())).toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true; //
                }else if (recette.getType_operation().toLowerCase().contains(lowerCaseFilter)) {
                    return true;}
                else if (recette.getRubrique().toLowerCase().contains(lowerCaseFilter)) {
                    return true;}
                else if (recette.getNom().toLowerCase().contains(lowerCaseFilter) ) {
                    return true;}
                else if (String.valueOf(recette.getGain()).indexOf(lowerCaseFilter) != -1 ) {
                    return true;}
                else if (String.valueOf(recette.getDepense()).indexOf(lowerCaseFilter) != -1 ) {
                    return true;}
                else if (String.valueOf(recette.getSolde()).indexOf(lowerCaseFilter) != -1 ) {
                    return true;}




                else
                    return false; // Does not match.
            });
        });
        SortedList<Recette> sortedData = new SortedList<>(filteredData);
        sortedData.comparatorProperty().bind(tableview.comparatorProperty());
        tableview.setItems(sortedData);
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
type_operationComboBox.getItems().addAll("Décaissement", "Encaissement");
UpdateTable();
searchRecette();
combo();
        soldeupdate();
    }



    public void CancelButtonOnAction () {
        Stage stage = (Stage) Cancel.getScene().getWindow() ;
        stage.close();

    }



    @FXML
    public void addRecette() {

        if (!(gainTextfield.getText().matches("[0-9.]+"))) JOptionPane.showMessageDialog(null, "Le gain est incorrect ");
        else if (!(depenseTextfield.getText().matches("[0-9.]+")))  JOptionPane.showMessageDialog(null, " La dépense est incorrecte ");
        else if (!(soldeTextfield.getText().matches("[0-9.]+")) ) JOptionPane.showMessageDialog(null, "Le solde est incorect ");
        else if (!(idTextfield.getText().matches("[0-9]+"))) JOptionPane.showMessageDialog(null, "l'ID est incorrect");
        else if (type_operationComboBox.getSelectionModel().isEmpty()) JOptionPane.showMessageDialog(null, "  Veuillez selectionner un Models ");
        else if (rubriqueComboBox.getSelectionModel().isEmpty()) JOptionPane.showMessageDialog(null, " Veuillez séléctionner une rubrique ");
        else if (nomComboBox.getSelectionModel().isEmpty()) JOptionPane.showMessageDialog(null, "  Veuillez séléctionner un nom ");
        else if(dateDatePicker.getValue().isBefore(LocalDate.now()))JOptionPane.showMessageDialog(null, "  La date devrait être avant la date d'aujourd'hui ");

        else {

                    if (recetteservice.verif(Integer.parseInt(idTextfield.getText()))) {
                        JOptionPane.showMessageDialog(null, "La Recette existe déjà ");
                    } else {

                        Recette recette=new Recette(java.sql.Date.valueOf((dateDatePicker.getValue())), type_operationComboBox.getValue(),
                                    rubriqueComboBox.getValue(),nomComboBox.getValue(),Integer.parseInt(idTextfield.getText()), Float.parseFloat(gainTextfield.getText()),
                                    Float.parseFloat(depenseTextfield.getText()),Float.parseFloat(soldeTextfield.getText()) );
                            recetteservice.addrecette(recette);
                            searchRecette();
                            UpdateTable();
                    }

        }
        soldeupdate();
    }






    public void Delete() {
        if(idTextfield.getText().trim().isEmpty())
            JOptionPane.showMessageDialog(null, "ecrire l'id de la recette");
        else    if (!(recetteservice.verif(Integer.parseInt(idTextfield.getText())))) {
            JOptionPane.showMessageDialog(null, "La Recette n'existe pas ");
        } else{
           int reply=JOptionPane.showConfirmDialog(null,"confirmer votre decision");
           if(reply==JOptionPane.YES_OPTION){
     recetteservice.Delete(idTextfield.getText());
            UpdateTable();
            searchRecette();

        soldeupdate();}}
    }



    @FXML
    public void getselected() throws SQLException {
        index = tableview.getSelectionModel().getSelectedIndex();
        if (index <= -1){

            return;
        }

        ResultSet rs= recetteservice.getRecette(Id.getCellData(index));
        while (rs.next()){
            dateDatePicker.setValue(rs.getDate("Datee").toLocalDate());
            type_operationComboBox.setValue(rs.getString("Type_operation"));
            rubriqueComboBox.setValue(rs.getString("Rubrique"));
            nomComboBox.setValue(rs.getString("Nom"));
            idTextfield.setText(rs.getString("Id"));
            gainTextfield.setText(String.valueOf(rs.getFloat("Gain")));
            depenseTextfield.setText(String.valueOf(rs.getFloat("Depense")));
            soldeTextfield.setText(String.valueOf((rs.getFloat("Solde"))));



        }


    }
    public void modif(){
        if (!(gainTextfield.getText().matches("[0-9.]+"))) JOptionPane.showMessageDialog(null, "Le gain est incorrect ");
        else if (!(depenseTextfield.getText().matches("[0-9.]+")))  JOptionPane.showMessageDialog(null, "La dépense est incorrecte ");
        else if (!(soldeTextfield.getText().matches("[0-9.]+")) ) JOptionPane.showMessageDialog(null, "Le solde est incorect ");
        else if (!(idTextfield.getText().matches("[0-9]+"))) JOptionPane.showMessageDialog(null, "L'ID est incorrect");
        else{

                    if (!(recetteservice.verif(Integer.parseInt(idTextfield.getText())))) {
                        JOptionPane.showMessageDialog(null, "La Recette n'existe pas ");
                    } else{
                        Recette recette=new Recette(java.sql.Date.valueOf((dateDatePicker.getValue())), type_operationComboBox.getValue(),
                                rubriqueComboBox.getValue(),nomComboBox.getValue(),Integer.parseInt(idTextfield.getText()), Float.parseFloat(gainTextfield.getText()),
                                Float.parseFloat(depenseTextfield.getText()),Float.parseFloat(soldeTextfield.getText()) );
                        recetteservice.modif(recette);
                      UpdateTable();
                    searchRecette();}
        }
        soldeupdate();
    }

     private void combo(){
       type_operationComboBox.valueProperty().addListener((obs, oldValue, newValue) -> {
            if (newValue == null) {

                rubriqueComboBox.getItems().clear();
                rubriqueComboBox.setDisable(true);
                rubriqueComboBox.getEditor().setEditable(false);
                nomComboBox.getItems().clear();
                nomComboBox.setDisable(true);
            } else
            if(newValue == "Encaissement"){
                rubriqueComboBox.getItems().clear();;
                // sample code, adapt as needed:
                rubriqueComboBox.getItems().addAll("Frais de services d'extrait","Frais d'organisation des événements","Location des matériels","Frais de mission des projets");
                rubriqueComboBox.setDisable(false);
                nomComboBox.getItems().clear();
            nomComboBox.getItems().addAll("citoyen");
            }
                else{
                rubriqueComboBox.getItems().clear();
                rubriqueComboBox.getItems().addAll("Achat matériel","Achat vehicule");
                rubriqueComboBox.setDisable(false);
                nomComboBox.getItems().clear();
                nomComboBox.getItems().addAll("fournisseur");
                }


        }); }
        public void soldeupdate(){
            ObservableList<Recette> list= recetteservice.getDataRecette();
            int l=   list.size();
            Recette r=list.get(l-1);
            soldelabel.setText("solde="+ r.getSolde());
        }
        @FXML
        private void imprimer() throws IOException {

            if(index!=-1){
            Recette permission = tableview.getSelectionModel().getSelectedItem();


            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/sample/views/PermissionAffichers.fxml"));
            Parent root1 = (Parent) fxmlLoader.load();
            AfficherPermission addPermissionController = fxmlLoader.getController();
            addPermissionController.setTextField(permission.getId(),permission.getType_operation(),permission.getRubrique(), permission.getDate(),permission.getNom(),permission.getGain(),permission.getDepense(),permission.getSolde());

        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setScene(new Scene(root1));
        stage.setMaximized(true);
        stage.initStyle(StageStyle.UNDECORATED);
        stage.showAndWait();}
            else
                JOptionPane.showMessageDialog(null, "choisir un recette");


    }
}
