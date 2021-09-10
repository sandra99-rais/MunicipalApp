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
import sample.connexion.Databasecon;
import sample.Models.login;
import sample.Models.voitures;
import sample.services.VehiculeServiceImpl;

import javax.swing.*;
import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;
public class Controllervehicules implements Initializable {
    @FXML
    private Button ajouter;

    @FXML
    private Button Cancel;

    @FXML
    private TextField marquetexfield;

    @FXML
    private TextField matriculetextfield;

    @FXML
    private TextField couleurtextfield;

    @FXML
    private TextField Nombretextfield;

    @FXML
    private Button modif;

    @FXML
    private DatePicker date;

    @FXML
    private TextField Nombretextfield1;

    @FXML
    private Button consultervehicule;

    @FXML
    private TableView<voitures> tableview;

    @FXML
    private TableColumn<voitures, Integer> ID;


    @FXML
    private ImageView marquevehicule;

    @FXML
    private ImageView matriculevehicule;

    @FXML
    private ImageView couleurvehicule;

    @FXML
    private ImageView nombredevehicule;
    @FXML
    private ImageView prixvehicule;

    @FXML
    private ImageView datee;

    @FXML
    private ImageView consulter;


    @FXML
    private ImageView vehicules;
    @FXML
    private BorderPane anchorpane;
    @FXML
    private ComboBox<String> typebox;
    @FXML
    private ImageView typeimage;


    Connection conn = null;
    ResultSet rs = null;
    PreparedStatement pst,pst1 = null;
    ObservableList<voitures> listM;

    int index = -1;
    login log;
    public void login(login log){
        this.log=log;
    }
    VehiculeServiceImpl vehiculeService=new VehiculeServiceImpl() ;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        typebox.getItems().addAll( "Vehicules de travaux","Vehicules destinés aux personnels","Vehicules d'évenements"," Vehicules à louer","autres");

        UpdateTable();


    }


    public void RetourButton() {
        Stage stage = (Stage) Cancel.getScene().getWindow() ;
        stage.close();

    }
    @FXML
    public void Loadsecondpagee(javafx.event.ActionEvent event) throws IOException {

        Scene scene = consultervehicule.getScene();

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/sample/views/tablevehicule.fxml"));
        Parent root = (Parent)fxmlLoader.load();
        ControllertableVehicule controlleur=fxmlLoader.getController();
        controlleur.login(log);
        root.translateXProperty().set(scene.getWidth());

        anchorpane = (BorderPane) consultervehicule.getScene().getRoot();

        anchorpane.getChildren().add(root);

        Timeline timeline = new Timeline();
        KeyValue kv = new KeyValue(root.translateXProperty(), 0, Interpolator.EASE_IN);
        KeyFrame kf = new KeyFrame(Duration.seconds(1), kv);
        timeline.getKeyFrames().add(kf);

        timeline.play();

    }

    @FXML
    public void AjoutVehicule() {
        if(log.getJob().equals("admin")){

        if (marquetexfield.getText().trim().isEmpty()) JOptionPane.showMessageDialog(null, "Le champs marque est vide");

        else if (matriculetextfield.getText().trim().isEmpty())
            JOptionPane.showMessageDialog(null, " Le champs du matricule est vide");
        else if (!((matriculetextfield.getText().matches("[0-9]+"))))
            JOptionPane.showMessageDialog(null, " Le champs  matricule doit contenir que des lettres");
        else if (!((matriculetextfield.getText().length() == 6)))
            JOptionPane.showMessageDialog(null, " Le nombre des chiffres du champs matricule est  <>6");

        else if (couleurtextfield.getText().trim().isEmpty())
            JOptionPane.showMessageDialog(null, " Le champs de la couleur est vide");
        else if (!((couleurtextfield.getText().matches("[a-zA-Z]+"))))
            JOptionPane.showMessageDialog(null, " Le champs de la couleur doit contenir que des lettres");


        else if (Nombretextfield.getText().trim().isEmpty())
            JOptionPane.showMessageDialog(null, "Le champs du nombre de vehicule  est vide");

        else if (Nombretextfield1.getText().trim().isEmpty())
            JOptionPane.showMessageDialog(null, "Le champs du prix est vide");
        else if (!(Nombretextfield1.getText().matches("[0-9]+")))
            JOptionPane.showMessageDialog(null, " Saisissez un prix correcte");
        else {
            voitures vehicule=new voitures(marquetexfield.getText(),matriculetextfield.getText(),couleurtextfield.getText(),Nombretextfield.getText(),Nombretextfield1.getText(),java.sql.Date.valueOf(date.getValue()),typebox.getValue());

          vehiculeService.Ajoutervehicule(vehicule);

        }
        UpdateTable();
    } else JOptionPane.showMessageDialog(null, "Vous n'êtes pas l'admin");
    }


    public void UpdateTable(){
        ID.setCellValueFactory(new PropertyValueFactory<voitures,Integer>("ID"));


        listM = vehiculeService.getDatavoiture();
        tableview.setItems(listM);
    }
    @FXML
    public void getselected() throws SQLException {
        index = tableview.getSelectionModel().getSelectedIndex();
        if (index <= -1) {

            return;}

        ResultSet rs=vehiculeService.getDatabasevoiture(ID.getCellData(index));

        while (rs.next()){
            marquetexfield.setText(rs.getString("Marque"));
            matriculetextfield.setText(rs.getString("Matricule"));


            couleurtextfield.setText(rs.getString("Couleur"));

            Nombretextfield.setText((rs.getString("Nombre")));

            Nombretextfield1.setText((rs.getString("Prix")));


            date.setValue(rs.getDate("Datee").toLocalDate());
            typebox.setValue(rs.getString("Type"));

        }


    }
    @FXML
    public void Edit (){
        if(log.getJob().equals("admin")){
        conn = Databasecon.getConnexion();

        String sql = "update VOITURE set Marque=? ,Matricule=?,Couleur =? ,Nombre=?,Prix=? ,Datee=? ,Type=?  where ID=" +ID.getCellData(index);
        if (!((matriculetextfield.getText().matches("[0-9]+"))))
            JOptionPane.showMessageDialog(null, " Le champs  matricule doit contenir que des lettres");
        else if (!((matriculetextfield.getText().length() == 6)))
            JOptionPane.showMessageDialog(null, " Le nombre des chiffres du champs matricule est  <>6");


        else if (!((couleurtextfield.getText().matches("[a-zA-Z]+"))))
            JOptionPane.showMessageDialog(null, " Le champs de la couleur doit contenir que des lettres");





        else if (!(Nombretextfield1.getText().matches("[0-9]+")))
            JOptionPane.showMessageDialog(null, " Saisissez un prix correcte");
        else{
            voitures vehicule=new voitures(marquetexfield.getText(),matriculetextfield.getText(),couleurtextfield.getText(),Nombretextfield.getText(),Nombretextfield1.getText(),java.sql.Date.valueOf(date.getValue()),typebox.getValue(),ID.getCellData(index));

            vehiculeService.edit(vehicule);


        }
        UpdateTable();    } else JOptionPane.showMessageDialog(null, "Vous n'êtes pas l'admin");

    }


        }







