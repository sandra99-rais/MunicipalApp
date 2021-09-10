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
import sample.Models.travaux;
import sample.services.travauxServiceImpl;

import javax.swing.*;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.ResourceBundle;

public class ControllerTabletravaux implements Initializable {

    @FXML
    private TableView<travaux> table;

    @FXML
    private TableColumn<travaux,String> cinfield;

    @FXML
    private TableColumn<travaux,String> cheffield;

    @FXML
    private TableColumn<travaux,String> typebox;

    @FXML
    private TableColumn<travaux,String> regionfield;

    @FXML
    private TableColumn<travaux, Date> datepicker;

    @FXML
    private TableColumn<travaux, Date> datepicker1;

    @FXML
    private TableColumn<travaux,String> choiceboxbesoin1;

    @FXML
    private TableColumn<travaux,String> budgetfield;
    @FXML
    private TableColumn<travaux, String> debutdatefield;
    @FXML
    private TableColumn<travaux, String> findatefield;
    @FXML
    private TableColumn<travaux, String> choiceboxbesoin21;




    @FXML
    private ImageView ret;

    @FXML
    private Button supp;
    @FXML
    private ImageView search;

    @FXML
    private ImageView delete;

    @FXML
    private TextField recherche;
    @FXML
    private Button return1;
    ObservableList<travaux> dataList;
    @FXML
    private TableColumn<travaux, Integer> ID;
    ObservableList<travaux> listM;
    PreparedStatement pst = null;
    Connection conn =null;
    int index = -1;
    travauxServiceImpl travauxservice=new travauxServiceImpl();

    login log;
    public void login(login log){
        this.log=log;
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {


        cinfield.setCellValueFactory(new PropertyValueFactory<travaux,String>("Num_Cin"));
        cheffield.setCellValueFactory(new PropertyValueFactory<travaux,String>("Nom_chef"));
        typebox.setCellValueFactory(new PropertyValueFactory<travaux,String>("Type"));
        regionfield.setCellValueFactory(new PropertyValueFactory<travaux,String>("Region"));

        datepicker.setCellValueFactory(new PropertyValueFactory<travaux, Date>("DateD"));
        debutdatefield.setCellValueFactory(new PropertyValueFactory<travaux,String>("Heure_debut"));
        datepicker1.setCellValueFactory(new PropertyValueFactory<travaux, Date>("DateF"));
        findatefield.setCellValueFactory(new PropertyValueFactory<travaux,String>("Heure_fin"));
        choiceboxbesoin1.setCellValueFactory(new PropertyValueFactory<travaux,String>("Besoin_materiels"));
        choiceboxbesoin21.setCellValueFactory(new PropertyValueFactory<travaux,String>("Besoin_vehicules"));

        budgetfield.setCellValueFactory(new PropertyValueFactory<travaux,String>("Budget"));
        ID.setCellValueFactory(new PropertyValueFactory<travaux,Integer>("ID"));
        dataList = travauxservice.getDatabaseTravaux();
        table.setItems(dataList);
        listM = travauxservice.getDatabaseTravaux();
        table.setItems(listM);
        searchTravaux();

    }
    public void UpdateTable(){
        cinfield.setCellValueFactory(new PropertyValueFactory<travaux,String>("Num_Cin"));
        cheffield.setCellValueFactory(new PropertyValueFactory<travaux,String>("Nom_chef"));
        typebox.setCellValueFactory(new PropertyValueFactory<travaux,String>("Type"));
        regionfield.setCellValueFactory(new PropertyValueFactory<travaux,String>("Region"));

        datepicker.setCellValueFactory(new PropertyValueFactory<travaux, Date>("DateD"));
        debutdatefield.setCellValueFactory(new PropertyValueFactory<travaux,String>("Heure_debut"));
        datepicker1.setCellValueFactory(new PropertyValueFactory<travaux, Date>("DateF"));
        findatefield.setCellValueFactory(new PropertyValueFactory<travaux,String>("Heure_fin"));
        choiceboxbesoin1.setCellValueFactory(new PropertyValueFactory<travaux,String>("Besoin_materiels"));
        choiceboxbesoin21.setCellValueFactory(new PropertyValueFactory<travaux,String>("Besoin_vehicules"));

        budgetfield.setCellValueFactory(new PropertyValueFactory<travaux,String>("Budget"));
        ID.setCellValueFactory(new PropertyValueFactory<travaux,Integer>("ID"));
        dataList = travauxservice.getDatabaseTravaux();
        table.setItems(dataList);


    }
    public void Delete() {
       index = table.getSelectionModel().getSelectedIndex();
        if (index <= -1) {

            return;
        }
        int reply=JOptionPane.showConfirmDialog(null,"confirmer votre decision");
        if(reply==JOptionPane.YES_OPTION){
        travauxservice.Delete(ID.getCellData(index));
        UpdateTable();
            searchTravaux();}

UpdateTable();

    }



    public void Retour(javafx.event.ActionEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/sample/views/Travaux.fxml"));
            Parent root1 = (Parent) fxmlLoader.load();
            ControllerTravauxx controlleur=fxmlLoader.getController();
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
        Stage stage =(Stage) return1.getScene().getWindow();
        stage.close();
    }

    @FXML
    void searchTravaux() {
        cinfield.setCellValueFactory(new PropertyValueFactory<travaux,String>("Num_Cin"));
        cheffield.setCellValueFactory(new PropertyValueFactory<travaux,String>("Nom_chef"));
        typebox.setCellValueFactory(new PropertyValueFactory<travaux,String>("Type"));
        regionfield.setCellValueFactory(new PropertyValueFactory<travaux,String>("Region"));

        datepicker.setCellValueFactory(new PropertyValueFactory<travaux, Date>("DateD"));
        debutdatefield.setCellValueFactory(new PropertyValueFactory<travaux,String>("Heure_debut"));
        datepicker1.setCellValueFactory(new PropertyValueFactory<travaux, Date>("DateF"));
        findatefield.setCellValueFactory(new PropertyValueFactory<travaux,String>("Heure_fin"));
        choiceboxbesoin1.setCellValueFactory(new PropertyValueFactory<travaux,String>("Besoin_materiels"));
        choiceboxbesoin21.setCellValueFactory(new PropertyValueFactory<travaux,String>("Besoin_vehicules"));

        budgetfield.setCellValueFactory(new PropertyValueFactory<travaux,String>("Budget"));
        ID.setCellValueFactory(new PropertyValueFactory<travaux,Integer>("ID"));
        dataList = travauxservice.getDatabaseTravaux();
        table.setItems(dataList);
        dataList = travauxservice.getDatabaseTravaux();
        table.setItems(dataList);



        FilteredList<travaux> filteredData = new FilteredList<>(listM, b -> true);
        recherche.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(travaux -> {
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }
                String lowerCaseFilter = newValue.toLowerCase();


                if ( travaux.getNum_Cin().toLowerCase().indexOf(lowerCaseFilter) != -1  ) {
                    return true; //
                }else if (travaux.getNom_chef().toLowerCase().indexOf(lowerCaseFilter) != -1 ) {
                    return true;}
                else if (travaux.getType().toLowerCase().indexOf(lowerCaseFilter) != -1 ) {
                    return true;}
                else if (travaux.getRegion().toLowerCase().indexOf(lowerCaseFilter) != -1 ) {
                    return true;}
                else if (new String(String.valueOf(travaux.getDateD())).toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true;}
                else if (travaux.getHeure_debut().toLowerCase().indexOf(lowerCaseFilter) != -1 ) {
                    return true;}


                else if (new String(String.valueOf(travaux.getDateF())).toLowerCase().indexOf(lowerCaseFilter) != -1 ) {
                    return true;}

                else if (travaux.getHeure_fin().toLowerCase().indexOf(lowerCaseFilter) != -1 ) {
                    return true;}

                else if (travaux.getBesoin_materiels().toLowerCase().indexOf(lowerCaseFilter) != -1 ) {
                    return true;}
                else if (travaux.getBesoin_vehicules().toLowerCase().indexOf(lowerCaseFilter) != -1 ) {
                    return true;}


                else if (travaux.getBudget().toLowerCase().indexOf(lowerCaseFilter) != -1 ) {
                    return true;}
                else if (new String(String.valueOf(travaux.getID())).toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true;}


                else
                    return false; // Does not match.
            });
        });
        SortedList<travaux> sortedData = new SortedList<>(filteredData);
        sortedData.comparatorProperty().bind(table.comparatorProperty());
        table.setItems(sortedData);
    }
    @FXML
    public void getselected() throws SQLException {
        index = table.getSelectionModel().getSelectedIndex();
        if (index <= -1) {

            return;}
        ResultSet rs= travauxservice.getDatabaseTravaux(ID.getCellData(index));
        while (rs.next()){

            JOptionPane.showMessageDialog(null,"DESCRIPTION :\n"+rs.getString("DESCRIPTION"));


        }}
}


