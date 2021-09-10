package sample.controlleur;


import javafx.animation.Interpolator;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.collections.ObservableList;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;

import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;
import sample.Models.login;
import sample.Models.travaux;
import sample.services.travauxServiceImpl;

import javax.swing.*;
import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;


public class ControllerTravauxx implements Initializable {

    @FXML
    private BorderPane anchorpane1;

    @FXML
    private AnchorPane anchorpane2;

    @FXML
    private ImageView travaux;

    @FXML
    private AnchorPane anchor31;

    @FXML
    private TextField cinfield;

    @FXML
    private TextField cheffield;

    @FXML
    private TextField budgetfield;

    @FXML
    private TextField regionfield;

    @FXML
    private Button ajouter;

    @FXML
    private Button modifier;

    @FXML
    private Button retour;

    @FXML
    private Button consult;

    @FXML
    private DatePicker datepicker;

    @FXML
    private TextField debutdatefield;

    @FXML
    private ImageView id1;

    @FXML
    private ImageView chef1;

    @FXML
    private ImageView type1;

    @FXML
    private ImageView localisation1;

    @FXML
    private ImageView date1;

    @FXML
    private ImageView vehicule1;

    @FXML
    private ImageView budget1;

    @FXML
    private ImageView consulter1;

    @FXML
    private ImageView materiels1;

    @FXML
    private ComboBox<String> choiceboxbesoin1;

    @FXML
    private TableView<travaux> table;

    @FXML
    private TableColumn<travaux, Integer> c1id;

    @FXML
    private DatePicker datepicker1;

    @FXML
    private TextField findatefield;

    @FXML
    private ComboBox<String> typebox;

    @FXML
    private ComboBox<String> choiceboxbesoin21;
    @FXML
    private TextArea description;




    int heure,minute;

    Connection conn =null;
    ResultSet rs = null;
    PreparedStatement pst = null;
    PreparedStatement pst1 = null;
    ObservableList<sample.Models.travaux> listM;
    travauxServiceImpl travauxservice=new travauxServiceImpl();

    int index = -1;

    login log;
    public void login(login log){
        this.log=log;
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        choiceboxbesoin1.getItems().addAll(" bétonnière", " pelle mécanique hydraulique","niveleuse","matériel de démolition","matériel de levage","matériel de contrôle","others");
        choiceboxbesoin21.getItems().addAll( "bulldozer","décapeuse","niveleuse"," camion"," tombereau","others");
        typebox.getItems().addAll("Construction ", "Travaux de plomberie "," installations sanitaires","Installation électrique"," Decoration");


        UpdateTable();

    }

    public void RetourButton () {
        Stage stage = (Stage) retour.getScene().getWindow() ;
        stage.close();


    }


    @FXML



    public void Loadsecondpagee(javafx.event.ActionEvent event) throws  IOException{


        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/sample/views/TableTravaux.fxml"));
        Parent root = (Parent)fxmlLoader.load();
        ControllerTabletravaux controlleur=fxmlLoader.getController();
        controlleur.login(log);
        Scene scene = consult.getScene();
        root.translateXProperty().set(scene.getWidth());

        anchorpane1 = (BorderPane) consult.getScene().getRoot();

        anchorpane1.getChildren().add(root);

        Timeline timeline = new Timeline();
        KeyValue kv = new KeyValue(root.translateXProperty(), 0, Interpolator.EASE_IN);
        KeyFrame kf = new KeyFrame(Duration.seconds(1), kv);
        timeline.getKeyFrames().add(kf);

        timeline.play();

    }
    public boolean verifytime(String hour) {

        try{ heure=Integer.parseInt(hour.substring(0,hour.indexOf(':')));
            minute=Integer.parseInt(hour.substring(hour.indexOf(':')+1,5));}
        catch(Exception e){}

        int diff=hour.indexOf(":")-0;
        int diff2=(hour.length()-1)-hour.indexOf(":");
        int diff3=hour.indexOf(":");
        if((diff==2) &&(diff2==2) && (diff3==2 ) && (hour.matches("[0-9:]+"))&& (heure<=23) && (minute<=59))return true;
        else return false;}
    public void Verifchamps(){
        if (cinfield.getText().trim().isEmpty() && cheffield.getText().trim().isEmpty() && typebox.getValue().trim().isEmpty() && regionfield.getText().trim().isEmpty() && debutdatefield.getText().trim().isEmpty()&& findatefield.getText().trim().isEmpty()&& budgetfield.getText().trim().isEmpty()){

            Alert fail= new Alert(Alert.AlertType.INFORMATION);
            fail.setHeaderText("Attention!");
            fail.setContentText("Vous devez remplir les champs .");
            fail.showAndWait();
        }}
    @FXML
    public void AjoutTravaux (){
        if (cinfield.getText().trim().isEmpty()) JOptionPane.showMessageDialog(null, "Le champs num Cin est vide");
        else if(!((cinfield.getText().length()==8))) JOptionPane.showMessageDialog(null, " Le nombre des chiffres du champs num Cin est  <>8");
        else if (cheffield.getText().trim().isEmpty()) JOptionPane.showMessageDialog(null, " Le champs du Nom du chef est vide");
        else if (!((cheffield.getText().matches("[a-zA-Z]+")))) JOptionPane.showMessageDialog(null, " Le champs du Nom du chef doit contenir que des lettres");
        else if  (typebox.getSelectionModel().isEmpty()) JOptionPane.showMessageDialog(null, " Le champs Type est vide");
        else if  (choiceboxbesoin1.getSelectionModel().isEmpty()) JOptionPane.showMessageDialog(null, " Le champs besoins materiels est vide");
        else if  (choiceboxbesoin21.getSelectionModel().isEmpty()) JOptionPane.showMessageDialog(null, " Le champs des besoins vehicules est vide");
        else if (regionfield.getText().trim().isEmpty()) JOptionPane.showMessageDialog(null, " Le champs de la région est vide");
        else if (!((regionfield.getText().matches("[a-zA-Z]+")))) JOptionPane.showMessageDialog(null, " Le champs de la région doit contenir que des lettres");

        else if (debutdatefield.getText().trim().isEmpty())JOptionPane.showMessageDialog(null,"Le champs de l'heure du début des travaux est vide");
        else if(!(verifytime(debutdatefield.getText()))) JOptionPane.showMessageDialog(null, "l'heure de début  des travaux est incorrecte");
        else if (findatefield.getText().trim().isEmpty())JOptionPane.showMessageDialog(null,"Le champs de l'heure de fin des travaux est vide");
        else if(!(verifytime(findatefield.getText()))) JOptionPane.showMessageDialog(null, "L'heure de la fin des travaux est incorrecte");
        else if (budgetfield.getText().trim().isEmpty()) JOptionPane.showMessageDialog(null,"Le champs budget est vide");
        else if (!(budgetfield.getText().matches("[0-9]+"))) JOptionPane.showMessageDialog(null, " Veuillez saisir une somme exacte");
        else if (datepicker.getValue().isAfter(datepicker1.getValue())) JOptionPane.showMessageDialog(null, "la date de début doit étre avant la date de la fin de l'événement ");
        else if (description.getText().trim().isEmpty()) JOptionPane.showMessageDialog(null, "  votre description est vide ");

        else  {


                Verifchamps();
                travaux trav=new travaux( cinfield.getText(), cheffield.getText(), typebox.getValue(), regionfield.getText() ,java.sql.Date.valueOf(datepicker.getValue()), debutdatefield.getText(),java.sql.Date.valueOf(datepicker1.getValue()),findatefield.getText(), choiceboxbesoin1.getValue(), choiceboxbesoin21.getValue(),budgetfield.getText(),description.getText());
                travauxservice.AjoutTravaux(trav);
           }
  UpdateTable();
}


    public void UpdateTable(){
        c1id.setCellValueFactory(new PropertyValueFactory<travaux,Integer>("ID"));


        listM = travauxservice.getDatabaseTravaux();
        table.setItems(listM);
    }
    @FXML
    public void getselected() throws SQLException {
       index = table.getSelectionModel().getSelectedIndex();
        if (index <= -1) {

            return;}
        ResultSet rs=travauxservice.getDatabaseTravaux( c1id.getCellData(index));
        while (rs.next()){
            cinfield.setText(rs.getString("Num_Cin"));
            cheffield.setText(rs.getString("Nom_chef"));
            typebox.setValue(rs.getString("Type"));

            regionfield.setText(rs.getString("Region"));
            datepicker.setValue(rs.getDate("DateD").toLocalDate());
            debutdatefield.setText((rs.getString("Heure_debut")));
            datepicker1.setValue(rs.getDate("DateF").toLocalDate());
            findatefield.setText((rs.getString("Heure_fin")));
            choiceboxbesoin1.setValue(rs.getString("Besoin_materiels"));
            choiceboxbesoin21.setValue (rs.getString("Besoin_vehicules"));
            budgetfield.setText((rs.getString("Budget")));
            description.setText(rs.getString("description"));

        }


    }
    @FXML
    public void Edit (){
        if (index <= -1) {

            return;}
        if (cinfield.getText().trim().isEmpty()) JOptionPane.showMessageDialog(null, "Le champs num Cin est vide");
        else if(!((cinfield.getText().length()==8))) JOptionPane.showMessageDialog(null, " Le nombre des chiffres du champs num Cin est  <>8");
        else if (cheffield.getText().trim().isEmpty()) JOptionPane.showMessageDialog(null, " Le champs du Nom du chef est vide");
        else if (!((cheffield.getText().matches("[a-zA-Z]+")))) JOptionPane.showMessageDialog(null, " Le champs du Nom du chef doit contenir que des lettres");
        else if (regionfield.getText().trim().isEmpty()) JOptionPane.showMessageDialog(null, " Le champs de la région est vide");
        else if (!((regionfield.getText().matches("[a-zA-Z]+")))) JOptionPane.showMessageDialog(null, " Le champs de la région doit contenir que des lettres");

        else if (debutdatefield.getText().trim().isEmpty())JOptionPane.showMessageDialog(null,"Le champs de l'heure du commencement des travaux est vide");
        else if(!(verifytime(debutdatefield.getText()))) JOptionPane.showMessageDialog(null, "l'heure du commencement des travaux  est incorrecte");
        else if (findatefield.getText().trim().isEmpty())JOptionPane.showMessageDialog(null,"Le champs de l'heure de fin des travaux est vide");
        else if(!(verifytime(findatefield.getText()))) JOptionPane.showMessageDialog(null, "l'heure  de fin des travaux est incorrecte");
        else if (budgetfield.getText().trim().isEmpty()) JOptionPane.showMessageDialog(null,"Le champs budget est vide");
        else if (!(budgetfield.getText().matches("[0-9]+"))) JOptionPane.showMessageDialog(null, " saisir une somme exacte");
        else if (datepicker.getValue().isAfter(datepicker1.getValue())) JOptionPane.showMessageDialog(null, "la date de début doit étre avant la date de la fin de l'événement  ");
        else if (description.getText().trim().isEmpty()) JOptionPane.showMessageDialog(null, "  votre description est vide ");

        else{

            travaux trav=new travaux( cinfield.getText(), cheffield.getText(), typebox.getValue(), regionfield.getText() ,java.sql.Date.valueOf(datepicker.getValue()), debutdatefield.getText(),java.sql.Date.valueOf(datepicker1.getValue()),findatefield.getText(), choiceboxbesoin1.getValue(), choiceboxbesoin21.getValue(),budgetfield.getText(), c1id.getCellData(index),description.getText());
            travauxservice.edit(trav);
        }
        UpdateTable();
    }

}





