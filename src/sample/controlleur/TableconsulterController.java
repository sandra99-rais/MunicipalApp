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
import sample.services.personnelServiceImpl;
import sample.Models.login;
import sample.Models.personnel;

import javax.swing.JOptionPane;
import java.lang.*;

import java.net.URL;
import java.time.LocalDate;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TableconsulterController implements Initializable {
    @FXML
    private Button addbutton;

    @FXML
    private Button deletebutton;

    @FXML
    private Button modifybutton;


    @FXML
    private TableView<personnel> tableview;

    @FXML
    private TableColumn<personnel,String> tablecin;

    @FXML
    private TableColumn< personnel,String> tablenom;

    @FXML
    private TableColumn< personnel,String> tableprenom;

    @FXML
    private TableColumn< personnel,String> tableemail;

    @FXML
    private TableColumn< personnel,String> tablegrade;

    @FXML
    private TableColumn< personnel, Float> tablesalaire;
    @FXML
    private TextField filteredField;
    @FXML
    private TextField champcin;

    @FXML
    private TextField champnom;

    @FXML
    private TextField champprenom;

    @FXML
    private TextField champemail;

    @FXML
    private TextField champgrade;

    @FXML
    private TextField champsalaire;
    @FXML
    private TextField champmdp;
    @FXML
    private Button RETOUR;
    @FXML
    private DatePicker champdate;
    @FXML
    private TableColumn<personnel, Date> datenaissance;
    @FXML
    private ComboBox<String> combo;


    ObservableList<personnel> listM;
    ObservableList<personnel> dataList;

    personnelServiceImpl personnelservice= new personnelServiceImpl();
    int index=-1;
    login log=null;
    public void login(login log){
        this.log=log;
    }

    @FXML
    void serachpersonnel() {
        tablecin.setCellValueFactory(new PropertyValueFactory<personnel,String>("Cin"));
        tableprenom.setCellValueFactory(new PropertyValueFactory<personnel,String>("Prenom"));
        tablenom.setCellValueFactory(new PropertyValueFactory<personnel,String>("Nom"));
        tablegrade.setCellValueFactory(new PropertyValueFactory<personnel,String>("grade"));
        tableemail.setCellValueFactory(new PropertyValueFactory<personnel,String>("email"));
        tablesalaire.setCellValueFactory(new PropertyValueFactory<personnel,Float>("salaire"));
        datenaissance.setCellValueFactory(new PropertyValueFactory<personnel,Date>("datenaissance"));

        dataList = personnelservice.getDatausers();
        tableview.setItems(dataList);
        FilteredList<personnel> filteredData = new FilteredList<>(dataList, b -> true);
        filteredField.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(person -> {
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }
                String lowerCaseFilter = newValue.toLowerCase();


                if (person.getCin().toLowerCase().indexOf(lowerCaseFilter) != -1 ) {
                    return true; //
                }else if (person.getNom().toLowerCase().indexOf(lowerCaseFilter) != -1 ) {
                    return true;}
                else if (person.getPrenom().toLowerCase().indexOf(lowerCaseFilter) != -1 ) {
                    return true;}
                else if (person.getEmail().toLowerCase().indexOf(lowerCaseFilter) != -1 ) {
                    return true;}
                else if (person.getGrade().toLowerCase().indexOf(lowerCaseFilter) != -1 ) {
                    return true;}
                else if (String.valueOf(person.getSalaire()).toLowerCase().indexOf(lowerCaseFilter) != -1 ) {
                    return true;}
                else if (String.valueOf(person.getDatenaissance()).toLowerCase().indexOf(lowerCaseFilter) != -1 ) {
                    return true;}

                else
                    return false; // Does not match.
            });
        });
        SortedList<personnel> sortedData = new SortedList<>(filteredData);
        sortedData.comparatorProperty().bind(tableview.comparatorProperty());
        tableview.setItems(sortedData);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        tablecin.setCellValueFactory(new PropertyValueFactory<personnel,String>("Cin"));
        tableprenom.setCellValueFactory(new PropertyValueFactory<personnel,String>("Prenom"));
        tablenom.setCellValueFactory(new PropertyValueFactory<personnel,String>("Nom"));
        tableemail.setCellValueFactory(new PropertyValueFactory<personnel,String>("email"));
        tablegrade.setCellValueFactory(new PropertyValueFactory<personnel,String>("grade"));
        tablesalaire.setCellValueFactory(new PropertyValueFactory<personnel,Float>("salaire"));
        datenaissance.setCellValueFactory(new PropertyValueFactory<personnel,Date>("datenaissance"));


        listM = personnelservice.getDatausers();
        tableview.setItems(listM);
        serachpersonnel();
        combo.getItems().addAll("agent","admin");
    }
    public void UpdateTable(){
        tablecin.setCellValueFactory(new PropertyValueFactory<personnel,String>("Cin"));
        tableprenom.setCellValueFactory(new PropertyValueFactory<personnel,String>("Prenom"));
        tablenom.setCellValueFactory(new PropertyValueFactory<personnel,String>("Nom"));
        tableemail.setCellValueFactory(new PropertyValueFactory<personnel,String>("email"));
        tablegrade.setCellValueFactory(new PropertyValueFactory<personnel,String>("grade"));
        tablesalaire.setCellValueFactory(new PropertyValueFactory<personnel,Float>("salaire"));
        datenaissance.setCellValueFactory(new PropertyValueFactory<personnel,Date>("datenaissance"));


        listM = personnelservice.getDatausers();
        tableview.setItems(listM);
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
        else return false;

    }



    @FXML
    public void Addpersonnel (){

        if (!(champcin.getText().matches("[0-9]+"))) JOptionPane.showMessageDialog(null, "Le num Cin est incorrect");
        else if(personnelservice.verify(champcin.getText())) JOptionPane.showMessageDialog(null, "l'utilisateur existe déjà");

           else if(!((champcin.getText().length()==8))) JOptionPane.showMessageDialog(null, "Le num Cin est incorrect");
                else if(! (champnom.getText().matches("[a-zA-Z]+")) ) JOptionPane.showMessageDialog(null, "Le nom n'est pas valide");
                else if(! (champprenom.getText().matches("[a-zA-Z]+")) ) JOptionPane.showMessageDialog(null, "Le prénom n'est pas valide");
                else if(! (champgrade.getText().matches("[a-zA-Z]+")) ) JOptionPane.showMessageDialog(null, "Le grade n'est pas valide");
                else if(! (verifyemail(champemail.getText()))) JOptionPane.showMessageDialog(null, "L'email est incorrecte");
                else if( champmdp.getText().isEmpty()) JOptionPane.showMessageDialog(null, "Le champs mot de pass est obligatoire");
                else if(!(champsalaire.getText().matches("[0-9.]+"))) JOptionPane.showMessageDialog(null, "Le salaire n'est pas valide");
                else if((champdate.getValue()==null)) JOptionPane.showMessageDialog(null, "Le champs date de naissance est vide");
                else if((combo.getValue()==null)) JOptionPane.showMessageDialog(null, "Veuillez séléctioner si le personnel est un agent ou un admin");
        else{


                    if(personnelservice.verifylogin(champcin.getText())==1){
                        JOptionPane.showMessageDialog(null, "L'utilisateur a été archivé");
                    }else{

                personnel pers=new personnel(champcin.getText(),champnom.getText(),champprenom.getText(),champgrade.getText(),java.sql.Date.valueOf(champdate.getValue()),champemail.getText(),Float.valueOf(champsalaire.getText()));
                login login=new login(champcin.getText(),champmdp.getText(),combo.getValue());
                personnelservice.add(pers,login);

            UpdateTable();
            serachpersonnel();
        }
                }


}
    public void Delete(){
        if(log.getJob().equals("admin")){
if(index<=-1){
    return;
} int reply=JOptionPane.showConfirmDialog(null,"confirmer votre decision");
            if(reply==JOptionPane.YES_OPTION){
      personnelservice.delete(champcin.getText());

            UpdateTable();
            serachpersonnel();}

    } else JOptionPane.showMessageDialog(null, "Vous n'êtes pas l'admin");
    }
  
    public void Edit(){
        if(log.getJob().equals("admin")){
            if(index<=-1){
                return;
            }
            if(!(personnelservice.verify(champcin.getText()))) JOptionPane.showMessageDialog(null, "Le personnel n'existe pas ");
        else if(! (champnom.getText().matches("[a-zA-Z]+")) ) JOptionPane.showMessageDialog(null, "Le nom n'est pas valid");
        else if(! (champprenom.getText().matches("[a-zA-Z]+")) ) JOptionPane.showMessageDialog(null, "Le prénom n'est pas valide");
        else if(! (champgrade.getText().matches("[a-zA-Z]+")) ) JOptionPane.showMessageDialog(null, "Le grade n'est pas valide");
        else if(! (verifyemail(champemail.getText()))) JOptionPane.showMessageDialog(null, "L'email est incorrecte");
        else if(!(champsalaire.getText().matches("[0-9.]+"))) JOptionPane.showMessageDialog(null, "Le salaire n'est pas valide");
        else if((champdate.getValue()==null)) JOptionPane.showMessageDialog(null, "Le champs date de naissance est vide");

        else{

                personnel pers=new personnel(champcin.getText(),champnom.getText(),champprenom.getText(),champgrade.getText(),java.sql.Date.valueOf(champdate.getValue()),champemail.getText(),Float.valueOf(champsalaire.getText()));
                personnelservice.edit(pers);

                UpdateTable();
                serachpersonnel();

        }        } else JOptionPane.showMessageDialog(null, "Vous n'êtes pas l'admin");

}


    public void getSelected() {

             index = tableview.getSelectionModel().getSelectedIndex();
            if (index <= -1){

                return;
            }
            champcin.setText(tablecin.getCellData(index).toString());
            champprenom.setText(tableprenom.getCellData(index).toString());
            champnom.setText(tablenom.getCellData(index).toString());
            champemail.setText(tableemail.getCellData(index).toString());
            champgrade.setText(tablegrade.getCellData(index).toString());
            champsalaire.setText(tablesalaire.getCellData(index).toString());
            champdate.setValue(LocalDate.parse(datenaissance.getCellData(index).toString()));




    }
    public void retour() {
        Stage stage = (Stage) RETOUR.getScene().getWindow();
        stage.close();

    }
}
