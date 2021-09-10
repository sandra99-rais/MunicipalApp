package sample.controlleur;


import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.text.ParseException;
import java.time.LocalDate;
import java.util.LinkedList;
import java.util.Locale;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Callback;
import sample.Models.Citoyen;
import sample.services.CitoyenServiceImpl;
import sample.Models.login;


import javax.swing.*;
import java.util.List;



public class Gestion_Compte implements Initializable {
    @FXML
    private TextField cin;
    @FXML
    private TextField nom;
    @FXML
    private TextField prenom;
    @FXML
    private DatePicker date_naissance;
    @FXML
    private Label lblStatus;
    @FXML
    TableView tblData;
    @FXML
    private TextField filteredField;

    @FXML
    private ComboBox<String> sexe;
    @FXML
    private Button retour;


    Citoyen citoyen;
    CitoyenServiceImpl citoyenService = new CitoyenServiceImpl();
    Connection conn = null;
    PreparedStatement preparedStatement = null;
    private Object DatePicker;
    login log=null;
    public void login(login log){
        this.log=log;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        sexe.getItems().addAll("Homme", "Femme", "Autres");
        sexe.getSelectionModel().select("Homme");
        fetColumnList();
        fetRowList();
        recherche();
    }

    @FXML
    private void HandleEvents(MouseEvent event) {
        //check if not empty
        if (cin.getText().isEmpty() || nom.getText().isEmpty() || prenom.getText().isEmpty() || date_naissance.getValue().equals(null)) {
            lblStatus.setTextFill(Color.TOMATO);
            lblStatus.setText("Enter all details");
        } else {
            citoyen = new Citoyen(cin.getText(), nom.getText(), prenom.getText(), date_naissance.getValue().toString(), sexe.getValue().toString());
            if (!(citoyen.getCIN().matches("[0-9]+"))) JOptionPane.showMessageDialog(null, "Le num Cin n'est pas valide");
            else if (CitoyenServiceImpl.verify(citoyen.getCIN(),"table1"))
                JOptionPane.showMessageDialog(null, "L'utilisateur  existe déjà");
            else if (CitoyenServiceImpl.verify(citoyen.getCIN(),"table2"))
                JOptionPane.showMessageDialog(null, "L'utilisateur est déjà archivé");
            else if (!((citoyen.getCIN().length() == 8))) JOptionPane.showMessageDialog(null, "Le num Cin n'est pas valide");
            else if (!(citoyen.getNom().matches("[a-zA-Z]+"))) JOptionPane.showMessageDialog(null, "le nom n'est pas valide");
            else if (!(citoyen.getPrenom().matches("[a-zA-Z]+")))
                JOptionPane.showMessageDialog(null, "Le prénom n'est pas valide");
            else if (date_naissance.getValue().isAfter(LocalDate.now()))
                JOptionPane.showMessageDialog(null, "La date saisie doit étre entrer le jour de la naissance ");
            else {
                citoyenService.add(citoyen);
                lblStatus.setTextFill(Color.GREEN);
                lblStatus.setText("L'ajout a été effectué");
                fetRowList();

                recherche();
            }
        }
    }

    private void clearFields() {
        cin.clear();
        nom.clear();
        prenom.clear();
    }


    //only fetch columns
    private void fetColumnList() {

        try {

            LinkedList<String> ll
                    = new LinkedList<String>();

            // Adding elements to the linked list

            ll.add("cin");
            ll.add("nom");
            ll.add("prenom");
            ll.add("date_de_naissance");
            ll.add("sexe");

            //SQL FOR SELECTING ALL OF CUSTOMER
            for (int i = 0; i < ll.size(); i++) {
                //We are using non property style for making dynamic table
                final int j = i;
                TableColumn col = new TableColumn(ll.get(i).toUpperCase());
                col.setCellValueFactory(new Callback<CellDataFeatures<ObservableList, String>, ObservableValue<String>>() {
                    public ObservableValue<String> call(CellDataFeatures<ObservableList, String> param) {
                        return new SimpleStringProperty(param.getValue().get(j).toString());
                    }
                });

                tblData.getColumns().removeAll(col);
                tblData.getColumns().addAll(col);

                System.out.println("Column [" + i + "] ");

            }

        } catch (Exception e) {
            System.out.println("Erreur " + e.getMessage());

        }
    }


    private void fetRowList() {
        ObservableList<Object> data = FXCollections.observableArrayList();
        List<Citoyen> listeCitoyen;

        listeCitoyen = citoyenService.findAll();

        for (int i = 0; i < listeCitoyen.size(); i++) {
            //Iterate Row
            ObservableList row = FXCollections.observableArrayList();

            //Iterate Column

            row.add(listeCitoyen.get(i).getCIN());
            row.add(listeCitoyen.get(i).getNom());
            row.add(listeCitoyen.get(i).getPrenom());
            row.add(listeCitoyen.get(i).getDate_de_naissance());
            row.add(listeCitoyen.get(i).getSexe());

            System.out.println("ligne [1] a été ajoutée " + row);
            data.add(row);
        }
        tblData.setItems(data);
        recherche();
    }

    @FXML
    public void DeleteCitoyen(MouseEvent event) {
        if (cin.getText().isEmpty() ) {
            lblStatus.setTextFill(Color.TOMATO);
            lblStatus.setText("Veuillez saisir un num Cin");
        }else{
            int reply=JOptionPane.showConfirmDialog(null,"confirmer votre decision");
            if(reply==JOptionPane.YES_OPTION){
            citoyenService.DeleteCityoen(cin.getText());
            lblStatus.setTextFill(Color.GREEN);
            lblStatus.setText("Le citoyen a été supprimé ");
            fetRowList();
            recherche();}
        }
    }
    public void getSelected() throws ParseException {
        String ro;
        String[] table;
        int index = tblData.getSelectionModel().getSelectedIndex();
        if (index <= -1) {


            return;
        }
        Object row = tblData.getItems().get(index);
        ro = row.toString();

        StringBuilder s = new StringBuilder(ro);
        s = s.deleteCharAt(0);
        s = s.deleteCharAt(s.length() - 1);
        ro = s.toString();


        table = ro.split(",");

        s = new StringBuilder(table[1]);
        s = s.deleteCharAt(0);
        table[1] = s.toString();

        s = new StringBuilder(table[2]);
        s = s.deleteCharAt(0);
        table[2] = s.toString();

        s = new StringBuilder(table[3]);
        s = s.deleteCharAt(0);
        table[3] = s.toString();

        s = new StringBuilder(table[4]);
        s = s.deleteCharAt(0);
        table[4] = s.toString();




        cin.setText(table[0]);
        nom.setText(table[1]);
        prenom.setText(table[2]);

date_naissance.setValue(LocalDate.parse(table[3]));
sexe.setValue(table[4]);


    }
@FXML
    void recherche() {
        ObservableList<Object> data = FXCollections.observableArrayList();
        List<Citoyen> listeCitoyen;
        String n = filteredField.getText();
        listeCitoyen = citoyenService.findAll();
try{
        for (int i = 0; i < listeCitoyen.size(); i++) {
            //Iterate Row
            ObservableList row = FXCollections.observableArrayList();

            //Iterate Column

            row.add(listeCitoyen.get(i).getCIN());
            row.add(listeCitoyen.get(i).getNom());
            row.add(listeCitoyen.get(i).getPrenom());
            row.add(listeCitoyen.get(i).getDate_de_naissance());
            row.add(listeCitoyen.get(i).getSexe());
            String s = row.toString();
            if (filteredField.getText().isEmpty()) {
                System.out.println("Ligne [1] a été ajoutée " + row);
                data.add(row);
            } else {


                if (s.toLowerCase(Locale.ROOT).indexOf(n.toLowerCase(Locale.ROOT)) != -1) {
                    System.out.println("Ligne [1] a été ajoutée " + row);
                    data.add(row);

                }
            }
            tblData.setItems(data);
        }}catch (Exception e){

        }
    }
    @FXML
    private void modifier(MouseEvent event) {
        //check if not empty
        if (cin.getText().isEmpty() || nom.getText().isEmpty() || prenom.getText().isEmpty() || date_naissance.getValue().equals(null)) {
            lblStatus.setTextFill(Color.TOMATO);
            lblStatus.setText("Remplissez tous les champs !");
        } else {
            citoyen = new Citoyen(cin.getText(), nom.getText(), prenom.getText(), date_naissance.getValue().toString(), sexe.getValue().toString());
            if (!(citoyen.getCIN().matches("[0-9]+"))) JOptionPane.showMessageDialog(null, " Le num Cin n'est pas valide");
            else if (!(CitoyenServiceImpl.verify(citoyen.getCIN(),"table1")))
                JOptionPane.showMessageDialog(null, "l'utilisateur  n'existe pas");
            else if (CitoyenServiceImpl.verify(citoyen.getCIN(),"table2"))
                JOptionPane.showMessageDialog(null, "l'utilisateur  est déjà archivé");
            else if (!((citoyen.getCIN().length() == 8))) JOptionPane.showMessageDialog(null, "Le num Cin n'est pas  valide");
            else if (!(citoyen.getNom().matches("[a-zA-Z]+"))) JOptionPane.showMessageDialog(null, "Le nom n'est pas ");
            else if (!(citoyen.getPrenom().matches("[a-zA-Z]+")))
                JOptionPane.showMessageDialog(null, "le prénom n'est pas valide");
            else {
                citoyenService.edit(citoyen);
                lblStatus.setTextFill(Color.GREEN);
                lblStatus.setText("La modification a été faite");
                fetRowList();
                //clear fields
                recherche();
            }
        }
    }
    public void retour() {
        Stage stage = (Stage) retour.getScene().getWindow();
        stage.close();
      
    }

    }