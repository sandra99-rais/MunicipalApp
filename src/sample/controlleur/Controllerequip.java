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
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import sample.connexion.Databasecon;
import sample.services.equipServiceImpl;
import sample.Models.login;
import sample.Models.personnels;

import javax.swing.*;

import java.io.File;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.ResourceBundle;


public class Controllerequip implements Initializable {
    @FXML
    private TableView<personnels> personneltable;


    @FXML
    private TextField num_cintextfield;

    @FXML
    private Button cancelbutton;
    @FXML
    private TextField numequipe;

    @FXML
    private TableColumn<personnels, String> numeq;

    @FXML
    private TableColumn<personnels, String> numcinp1;

    @FXML
    private TableColumn<personnels, String> numcinp2;

    @FXML
    private TableColumn<personnels, String> numcinp3;

    @FXML
    private TableColumn<personnels, String> numcinp4;

    @FXML
    private TableColumn<personnels, String> tacheeq;

    @FXML
    private TextField filteredField;


    @FXML
    private ImageView teamimage;
    @FXML
    private Label msgLabel;
    @FXML
    private TextField num_cintextfield1;

    @FXML
    private TextField num_cintextfield11;

    @FXML
    private TextField num_cintextfield111;
    @FXML
    private TextField tachetextfield1111;


    @FXML
    ObservableList<personnels> listM;
    ObservableList<personnels> dataList;
    int index = -1;
    Connection conn =null;

    PreparedStatement pst = null;
    equipServiceImpl equipservice=new equipServiceImpl();
    login log=null;
    public void login(login log){
        this.log=log;
    }

    @FXML
    void searchpersonnel() {
        numcinp1.setCellValueFactory(new PropertyValueFactory<personnels,String>("Num_CIN1"));
        numcinp2.setCellValueFactory(new PropertyValueFactory<personnels,String>("Num_CIN2"));
        numcinp3.setCellValueFactory(new PropertyValueFactory<personnels,String>("Num_CIN3"));
        numcinp4.setCellValueFactory(new PropertyValueFactory<personnels,String>("Num_CIN4"));
        numeq.setCellValueFactory(new PropertyValueFactory<personnels,String>("Numequip"));
        tacheeq .setCellValueFactory(new PropertyValueFactory<personnels,String>("Tache"));



        dataList = equipservice.getDatausers();
        personneltable.setItems(dataList);
        FilteredList<personnels> filteredData = new FilteredList<>(dataList, b -> true);
        filteredField.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(person -> {
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }
                String lowerCaseFilter = newValue.toLowerCase();


                if (person.getNum_CIN1().toLowerCase().indexOf(lowerCaseFilter) != -1 ) {
                    return true; //
                }else if (person.getNum_CIN2().toLowerCase().indexOf(lowerCaseFilter) != -1 ) {
                    return true;}
                else if (person.getNum_CIN3().toLowerCase().indexOf(lowerCaseFilter) != -1 ) {
                    return true;}
                else if (person.getNum_CIN4().toLowerCase().indexOf(lowerCaseFilter) != -1 ) {
                    return true;}
                else if (person.getNumequip().toLowerCase().indexOf(lowerCaseFilter) != -1 ) {
                    return true;}
                else if (person.getTache().toLowerCase().indexOf(lowerCaseFilter) != -1 ) {
                    return true;}

                else
                    return false; // Does not match.
            });
        });
        SortedList<personnels> sortedData = new SortedList<>(filteredData);
        sortedData.comparatorProperty().bind(personneltable.comparatorProperty());
        personneltable.setItems(sortedData);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        UpdateTable();
        searchpersonnel();
    }
    @FXML
    public void UpdateTable(){

        numcinp1.setCellValueFactory(new PropertyValueFactory<personnels,String>("Num_CIN1"));
        numcinp2.setCellValueFactory(new PropertyValueFactory<personnels,String>("Num_CIN2"));
        numcinp3.setCellValueFactory(new PropertyValueFactory<personnels,String>("Num_CIN3"));
        numcinp4.setCellValueFactory(new PropertyValueFactory<personnels,String>("Num_CIN4"));
        numeq.setCellValueFactory(new PropertyValueFactory<personnels,String>("Numequip"));
        tacheeq .setCellValueFactory(new PropertyValueFactory<personnels,String>("Tache"));

        listM = equipservice.getDatausers();
        personneltable.setItems(listM);
    }
    @FXML
    public void Addpersonnel () {

        conn = Databasecon.getConnexion();
        if((num_cintextfield.getText().matches("[0-9]+")) &&(num_cintextfield1.getText().matches("[0-9]+")) && (num_cintextfield11.getText().matches("[0-9]+")) && (num_cintextfield111.getText().matches("[0-9]+")) && (numequipe.getText().matches("[0-9]+")) && (tachetextfield1111.getText().matches("[a-zA-Z]+")) && (num_cintextfield.getText().length()==8) &&(num_cintextfield1.getText().length()==8) && (num_cintextfield11.getText().length()==8) && (num_cintextfield111.getText().length()==8))
        {
            if(!equipservice.verify(num_cintextfield.getText())) JOptionPane.showMessageDialog(null, "personnel "+num_cintextfield.getText()+" n'existe pas");
            else if(!equipservice.verify(num_cintextfield1.getText())) JOptionPane.showMessageDialog(null, " personnel "+num_cintextfield1.getText()+" n'existe pas");
            else if(!equipservice.verify(num_cintextfield11.getText())) JOptionPane.showMessageDialog(null, " personnel "+num_cintextfield11.getText()+" n'existe pas");
            else if   (!equipservice.verify(num_cintextfield111.getText())) JOptionPane.showMessageDialog(null, " personnel "+num_cintextfield111.getText()+" n'existe pas");
            else if((num_cintextfield.getText().equals(num_cintextfield1.getText())) || (num_cintextfield.getText().equals(num_cintextfield11.getText())) || (num_cintextfield.getText().equals(num_cintextfield111.getText())) || (num_cintextfield1.getText().equals(num_cintextfield11.getText())) || (num_cintextfield1.getText().equals(num_cintextfield111.getText()))|| (num_cintextfield11.getText().equals(num_cintextfield111.getText())))
                JOptionPane.showMessageDialog(null, " Le num Cin a deja été ajouté !");
       else
                if(equipservice.verifycin(num_cintextfield.getText(),num_cintextfield1.getText(),num_cintextfield11.getText(),num_cintextfield111.getText())){
                    JOptionPane.showMessageDialog(null, " Le num Cin  existe deja  dans une autre equipe");}

               else if(equipservice.verifynumequip(numequipe.getText()))   JOptionPane.showMessageDialog(null, "numero d'equip deja utilisé");
               else{
            try {
                personnels equip=new personnels(numequipe.getText(),num_cintextfield.getText(),num_cintextfield1.getText(),num_cintextfield11.getText(),tachetextfield1111.getText(),num_cintextfield111.getText());
           equipservice.Addpersonnel(equip);


            searchpersonnel();
            UpdateTable();

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }}


                }else{ JOptionPane.showMessageDialog(null, "verifier les champs");}
    }


    public void Delete(){
if(!equipservice.verifynumequip(numequipe.getText()))
    JOptionPane.showMessageDialog(null, "cette equipe n'existe pas");
        else{
    int reply=JOptionPane.showConfirmDialog(null,"confirmer votre decision");
    if(reply==JOptionPane.YES_OPTION){
            try {
          equipservice.Delete(numequipe.getText());
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }}}
        UpdateTable();
        searchpersonnel();
    }

            public void CancelButtonOnAction () {
                Stage stage = (Stage) cancelbutton.getScene().getWindow() ;
                stage.close();


            }


    public void getSelected(javafx.scene.input.MouseEvent mouseEvent) {
        index = personneltable.getSelectionModel().getSelectedIndex();
        if (index <= -1){

            return;
        }
        numequipe.setText(numeq.getCellData(index).toString());
        num_cintextfield.setText(numcinp1.getCellData(index).toString());
        num_cintextfield1.setText(numcinp2.getCellData(index).toString());
        num_cintextfield11.setText(numcinp3.getCellData(index).toString());
        num_cintextfield111.setText(numcinp4.getCellData(index).toString());
        tachetextfield1111.setText(tacheeq.getCellData(index).toString());



    }



}

