package sample.controlleur;

import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.collections.ObservableList;

import javafx.fxml.FXML;

import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import sample.Models.login;
import sample.Models.voitures;
import sample.services.VehiculeServiceImpl;

import javax.swing.*;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.Date;
import java.util.ResourceBundle;


public class ControllertableVehicule implements Initializable {
    @FXML
    private TableView<voitures> tableview;
    @FXML
    private TableColumn<voitures, String> marque;

    @FXML
    private TableColumn<voitures, String> matricule;

    @FXML
    private TableColumn<voitures, String> couleur;

    @FXML
    private TableColumn<voitures, String> nombre;

    @FXML
    private TableColumn<voitures, String> prix;

    @FXML
    private TableColumn<voitures, Date> date;

    @FXML
    private TableColumn<voitures, Integer> ID;

    @FXML
    private Button retour;

    @FXML
    private Button supp;

    @FXML
    private ImageView listev;

    @FXML
    private ImageView cancel;

    @FXML
    private ImageView supprimer;

    @FXML
    private TextField recherche;
    @FXML
    private TableColumn<voitures, String> type;

    @FXML
    private ImageView search;
    ObservableList<voitures> dataList;
    ObservableList<voitures> listM;
    PreparedStatement pst = null;
    Connection conn =null;
    int index = -1;
    login log;
    public void login(login log){
        this.log=log;
    }
    VehiculeServiceImpl vehiculeService=new VehiculeServiceImpl() ;

    @Override
    public void initialize(URL location, ResourceBundle resources) {


        marque.setCellValueFactory(new PropertyValueFactory<voitures,String>("Marque"));
        matricule.setCellValueFactory(new PropertyValueFactory<voitures,String>("Matricule"));
        couleur.setCellValueFactory(new PropertyValueFactory<voitures,String>("Couleur"));
        nombre.setCellValueFactory(new PropertyValueFactory<voitures,String>("Nombre"));
        prix.setCellValueFactory(new PropertyValueFactory<voitures,String>("Prix"));
        date.setCellValueFactory(new PropertyValueFactory<voitures, Date>("Datee"));
        type.setCellValueFactory(new PropertyValueFactory<voitures,String>("Type"));


        ID.setCellValueFactory(new PropertyValueFactory<voitures,Integer>("ID"));
        dataList = vehiculeService.getDatavoiture();
        tableview.setItems(dataList);
        listM = vehiculeService.getDatavoiture();
        tableview.setItems(listM);
        UpdateTable();
        searchVehicule();


    }
    public void UpdateTable() {
        marque.setCellValueFactory(new PropertyValueFactory<voitures,String>("Marque"));
        matricule.setCellValueFactory(new PropertyValueFactory<voitures,String>("Matricule"));
        couleur.setCellValueFactory(new PropertyValueFactory<voitures,String>("Couleur"));
        nombre.setCellValueFactory(new PropertyValueFactory<voitures,String>("Nombre"));
        prix.setCellValueFactory(new PropertyValueFactory<voitures,String>("Prix"));
        date.setCellValueFactory(new PropertyValueFactory<voitures, Date>("Datee"));
        type.setCellValueFactory(new PropertyValueFactory<voitures,String>("Type"));



        ID.setCellValueFactory(new PropertyValueFactory<voitures,Integer>("ID"));
        dataList = vehiculeService.getDatavoiture();
        tableview.setItems(dataList);
        listM = vehiculeService.getDatavoiture();
        tableview.setItems(listM);

    }
    public void Delete() {
        if(log.getJob().equals("admin")){
        int index = tableview.getSelectionModel().getSelectedIndex();
        if (index <= -1) {

            return;
        }
            int reply=JOptionPane.showConfirmDialog(null,"confirmer votre decision");
            if(reply==JOptionPane.YES_OPTION){
 vehiculeService.Delete(ID.getCellData(index));
            UpdateTable();
            searchVehicule();}

        } else JOptionPane.showMessageDialog(null, "Vous n'êtes pas l'admin");
    }

    public void Retour(javafx.event.ActionEvent event) {
        try {

            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/sample/views/vehicules.fxml"));
            Parent root1 = (Parent) fxmlLoader.load();
            Controllervehicules controlleur=fxmlLoader.getController();
            controlleur.login(log);
           Stage stage = new Stage();
           stage.setMaximized(true);
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.initStyle(StageStyle.UNDECORATED);
            stage.setTitle("Retour");
            stage.setScene(new Scene(root1));
            stage.show();

        }catch(Exception e){
            e.printStackTrace();
        }
        Stage stage =(Stage) retour.getScene().getWindow();
        stage.close();

}
    @FXML
    void searchVehicule() {

        marque.setCellValueFactory(new PropertyValueFactory<voitures,String>("Marque"));
        matricule.setCellValueFactory(new PropertyValueFactory<voitures,String>("Matricule"));
        couleur.setCellValueFactory(new PropertyValueFactory<voitures,String>("Couleur"));
        nombre.setCellValueFactory(new PropertyValueFactory<voitures,String>("Nombre"));
        prix.setCellValueFactory(new PropertyValueFactory<voitures,String>("Prix"));
        date.setCellValueFactory(new PropertyValueFactory<voitures, Date>("Datee"));
        type.setCellValueFactory(new PropertyValueFactory<voitures,String>("Type"));
        ID.setCellValueFactory(new PropertyValueFactory<voitures,Integer>("ID"));
        listM = vehiculeService.getDatavoiture();
        tableview.setItems(listM);
        FilteredList<voitures> filteredData = new FilteredList<>(listM, b -> true);
        recherche.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(voitures -> {
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }
                String lowerCaseFilter = newValue.toLowerCase();


                if ( voitures.getMarque().toLowerCase().indexOf(lowerCaseFilter) != -1  ) {
                    return true; //
                }else if (voitures.getMatricule().toLowerCase().indexOf(lowerCaseFilter) != -1 ) {
                    return true;}
                else if (voitures.getCouleur().toLowerCase().indexOf(lowerCaseFilter) != -1 ) {
                    return true;}
                else if (voitures.getNombre().toLowerCase().indexOf(lowerCaseFilter) != -1 ) {
                    return true;}
                else if (voitures.getPrix().toLowerCase().indexOf(lowerCaseFilter) != -1 ) {
                    return true;}
                else if (new String(String.valueOf(voitures.getDatee())).toLowerCase().indexOf(lowerCaseFilter) != -1 ) {
                    return true;}
                else if (voitures.getType().toLowerCase().indexOf(lowerCaseFilter) != -1 ) {
                    return true;}
                else if (new String(String.valueOf(voitures.getID())).toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true;}
                else
                    return false; // Does not match.
            });
        });
        SortedList<voitures> sortedData = new SortedList<>(filteredData);
        sortedData.comparatorProperty().bind(tableview.comparatorProperty());
        tableview.setItems(sortedData);
    }

}



