
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
import sample.Models.Materiels;
import sample.Models.login;
import sample.services.materielServiceImpl;

import javax.swing.*;
import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;
public class Controllermateriels implements  Initializable {
    @FXML
    private Button Cancel;

    @FXML
    private Button consulter;

    @FXML
    private Button ajouter;

    @FXML
    private TextField idtextfield;
    @FXML
    private ImageView consult;

    @FXML
    private TextField quantitétextfield;

    @FXML
    private Label idlabel;

    @FXML
    private TextField prixtextfield;

    @FXML
    private Label typelabel;

    @FXML
    private Label quantitélabel;

    @FXML
    private Label prixlabel;

    @FXML
    private ImageView idimage;

    @FXML
    private ImageView typeimage;

    @FXML
    private ImageView quantitéimage;

    @FXML
    private ImageView priximage;

    @FXML
    private DatePicker date;

    @FXML
    private Label prixlabel1;

    @FXML
    private ImageView dateimage;

    @FXML
    private Button supp1;

    @FXML
    private ComboBox<String> typebox;

    @FXML
    private TableView<Materiels> tableview;
    @FXML
    private TextArea description;

    @FXML
    private TableColumn<Materiels, Integer> ID;

    @FXML
    private ImageView materiel;
    @FXML
    private BorderPane anchorpane;

    Connection conn = null;
    ResultSet rs = null;
    PreparedStatement pst = null;
    PreparedStatement pst1 = null;
    ObservableList<Materiels> listM;

    int index = -1;
    login log;
    materielServiceImpl materielservic=new materielServiceImpl();
    public void login(login log){
        this.log=log;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        typebox.getItems().addAll( "Matériaux métalliques","Matériaux organiques"," Mtériaux minéraux"," Matériaux de construction","others");



        UpdateTable();


    }
    public void RetourButton() {
        Stage stage = (Stage) Cancel.getScene().getWindow() ;
        stage.close();
    }

    @FXML
    public void Loadsecondpagee(javafx.event.ActionEvent event) throws IOException {

        Scene scene = consulter.getScene();


        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/sample/views/gestionmaterieltable.fxml"));
        Parent root = (Parent)fxmlLoader.load();

        Controllertablemateriels controlleur=fxmlLoader.getController();
        controlleur.login(log);
        root.translateXProperty().set(scene.getWidth());

        anchorpane = (BorderPane) consulter.getScene().getRoot();

        anchorpane.getChildren().add(root);

        Timeline timeline = new Timeline();
        KeyValue kv = new KeyValue(root.translateXProperty(), 0, Interpolator.EASE_IN);
        KeyFrame kf = new KeyFrame(Duration.seconds(1), kv);
        timeline.getKeyFrames().add(kf);

        timeline.play();

    }


    @FXML
    public void AjoutMateriel () {
        if(log.getJob().equals("admin")){


            conn = Databasecon.getConnexion();

            String sql = "insert into Materiels(ID_materiel,Type ,Quantité,Prix,Datee )values(?,?,?,?,? ) ";
            String sql1="insert into GESTIONRECETTE(datee,Type_operation,RUBRIQUE,NOM,ID,GAIN,DEPENSE,SOLDE)values(?,?,?,?,?,?,?,?)";

            if (idtextfield.getText().trim().isEmpty()) JOptionPane.showMessageDialog(null, "Le champs ID est vide");
            else if (!((idtextfield.getText().length() == 4)))
                JOptionPane.showMessageDialog(null, " Le nombre des chiffres du champs ID materiel est  <>4");
            else if (typebox.getValue().trim().isEmpty()) JOptionPane.showMessageDialog(null, " Le champs Type est vide");
            else if (quantitétextfield.getText().trim().isEmpty())
                JOptionPane.showMessageDialog(null, " Le champs de la quantité est vide");
            else if (!((quantitétextfield.getText().matches("[0-9]+"))))
                JOptionPane.showMessageDialog(null, " Le champs de la quantité doit contenir que des chiffres ");

            else if (prixtextfield.getText().trim().isEmpty())
                JOptionPane.showMessageDialog(null, " Le champs du prix est vide");
            else if (!((prixtextfield.getText().matches("[0-9]+"))))
                JOptionPane.showMessageDialog(null, " Saisissez un prix correcte");
            else if (description.getText().trim().isEmpty())
                JOptionPane.showMessageDialog(null, "  votre description est vide ");

            else{
                Materiels materiel=new Materiels(idtextfield.getText(),typebox.getValue(),quantitétextfield.getText(),prixtextfield.getText(),java.sql.Date.valueOf(date.getValue()),description.getText());
                materielservic.AjoutMateriel(materiel);

              }


            UpdateTable();
        }
        else JOptionPane.showMessageDialog(null, "Vous  n'êtes pas l'admin");

    }
    public void UpdateTable(){
        ID.setCellValueFactory(new PropertyValueFactory<Materiels,Integer>("ID"));


        listM = materielservic.getDatamateriel();
        tableview.setItems(listM);
    }
    @FXML
    public void getselected() throws SQLException {
       index = tableview.getSelectionModel().getSelectedIndex();
        if (index <= -1) {

            return;}

        ResultSet rs= materielservic.getDatabasemateriel(ID.getCellData(index));

        while (rs.next()){
            idtextfield.setText(rs.getString("ID_materiel"));
            typebox.setValue(rs.getString("Type"));
            quantitétextfield.setText((rs.getString("Quantité")));
            prixtextfield.setText(rs.getString("Prix"));
            date.setValue(rs.getDate("Datee").toLocalDate());
            description.setText(rs.getString("description"));








        }



    }
    @FXML
    public void Edit (){
        if(log.getJob().equals("admin")){


            conn = Databasecon.getConnexion();

         if (!((idtextfield.getText().length() == 4)))
                JOptionPane.showMessageDialog(null, " Le nombre des chiffres du champs ID materiel est  <>4");


            else if (!((quantitétextfield.getText().matches("[0-9]+"))))
                JOptionPane.showMessageDialog(null, " Le champs de la quantité doit contenir que des chiffres ");


            else if (!((prixtextfield.getText().matches("[0-9]+"))))
                JOptionPane.showMessageDialog(null, " Saisissez un prix correcte");


         else if (description.getText().trim().isEmpty())
             JOptionPane.showMessageDialog(null, "  votre description est vide ");



         else{
             Materiels materiel=new Materiels(idtextfield.getText(),typebox.getValue(),quantitétextfield.getText(),prixtextfield.getText(),java.sql.Date.valueOf(date.getValue()),ID.getCellData(index),description.getText());

             materielservic.edit(materiel);
            }
            UpdateTable();        } else JOptionPane.showMessageDialog(null, "Vous n'etes pas l'admin");

    }


}
















