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
import sample.Models.Materiels;
import sample.Models.login;
import sample.services.materielServiceImpl;

import javax.swing.*;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.ResourceBundle;


public class Controllertablemateriels implements Initializable {
    @FXML
    private TableColumn<Materiels, String> idmateriel;
    @FXML
    private TableView<Materiels> tableview;
    @FXML
    private TableColumn<Materiels, String> type;

    @FXML
    private TableColumn<Materiels, String> quantité;

    @FXML
    private TableColumn<Materiels, String> prix;

    @FXML
    private TableColumn<Materiels, Date> date;

    @FXML
    private TableColumn<Materiels, Integer> ID;

    @FXML
    private Button supp;

    @FXML
    private Button cancel;

    @FXML
    private ImageView supprimer;

    @FXML
    private ImageView retour;

    @FXML
    private ImageView search;

    @FXML
    private TextField recherche;

    @FXML
    private ImageView materiel;
    ObservableList<Materiels> dataList;
    ObservableList<Materiels> listM;
    PreparedStatement pst = null;
    Connection conn =null;
    int index = -1;
    materielServiceImpl materielservic=new materielServiceImpl();
    login log;
    public void login(login log){
        this.log=log;
    }
    @Override
    public void initialize(URL location, ResourceBundle resources) {

        UpdateTable();

    }
    public void UpdateTable(){
        idmateriel.setCellValueFactory(new PropertyValueFactory<Materiels,String>("ID_materiel"));
        type.setCellValueFactory(new PropertyValueFactory<Materiels,String>("Type"));
        quantité.setCellValueFactory(new PropertyValueFactory<Materiels,String>("Quantité"));
        prix.setCellValueFactory(new PropertyValueFactory<Materiels,String>("Prix"));

        date.setCellValueFactory(new PropertyValueFactory<Materiels, Date>("Datee"));

        ID.setCellValueFactory(new PropertyValueFactory<Materiels,Integer>("ID"));
        dataList = materielservic.getDatamateriel();
        tableview.setItems(dataList);


    }
    public void Delete() {
        if(log.getJob().equals("admin")){

        int index = tableview.getSelectionModel().getSelectedIndex();
        if (index <= -1) {
            return;
        }
            int reply=JOptionPane.showConfirmDialog(null,"confirmer votre decision");
            if(reply==JOptionPane.YES_OPTION){

             materielservic.Delete(ID.getCellData(index));

            UpdateTable();
            searchMateriels();}

      } else JOptionPane.showMessageDialog(null, "Vous  n'êtes pas l'admin");

    }



    public void Retour(javafx.event.ActionEvent event) {

        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/sample/views/gestionmateriel.fxml"));
            Parent root1 = (Parent) fxmlLoader.load();
            Controllermateriels controlleur=fxmlLoader.getController();
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
        Stage stage =(Stage) cancel.getScene().getWindow();
        stage.close();
    }

    @FXML
    void searchMateriels() {

        idmateriel.setCellValueFactory(new PropertyValueFactory<Materiels,String>("ID_materiel"));
        type.setCellValueFactory(new PropertyValueFactory<Materiels,String>("Type"));
        quantité.setCellValueFactory(new PropertyValueFactory<Materiels,String>("Quantité"));
        prix.setCellValueFactory(new PropertyValueFactory<Materiels,String>("Prix"));

        date.setCellValueFactory(new PropertyValueFactory<Materiels, Date>("Datee"));

        ID.setCellValueFactory(new PropertyValueFactory<Materiels,Integer>("ID"));
        dataList = materielservic.getDatamateriel();
        tableview.setItems(dataList);



        FilteredList<Materiels> filteredData = new FilteredList<>(dataList, b -> true);
        recherche.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(materiels -> {
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }
                String lowerCaseFilter = newValue.toLowerCase();


                if ( materiels.getID_materiel().toLowerCase().indexOf(lowerCaseFilter) != -1  ) {
                    return true; //
                }else if (materiels.getType().toLowerCase().indexOf(lowerCaseFilter) != -1 ) {
                    return true;}
                else if (materiels.getQuantité().toLowerCase().indexOf(lowerCaseFilter) != -1 ) {
                    return true;}
                else if (materiels.getPrix().toLowerCase().indexOf(lowerCaseFilter) != -1 ) {
                    return true;}
                else if (new String(String.valueOf(materiels.getDatee())).toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true;}



                else if (new String(String.valueOf(materiels.getID())).toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true;}


                else
                    return false; // Does not match.
            });
        });
        SortedList<Materiels> sortedData = new SortedList<>(filteredData);
        sortedData.comparatorProperty().bind(tableview.comparatorProperty());
        tableview.setItems(sortedData);
    }
    @FXML
    public void getselected() throws SQLException {
        int index = tableview.getSelectionModel().getSelectedIndex();
        if (index <= -1) {

            return;}
        ResultSet rs= materielservic.getDatabasemateriel(ID.getCellData(index));
        while (rs.next()){

            JOptionPane.showMessageDialog(null,"DESCRIPTION :\n"+rs.getString("DESCRIPTION"));


        }
}}




