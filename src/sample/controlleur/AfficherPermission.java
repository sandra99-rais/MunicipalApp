package sample.controlleur;


import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;


import java.net.URL;
import java.util.Date;
import java.util.ResourceBundle;



public class AfficherPermission implements Initializable {
    @FXML
    private Label idl;

    @FXML
    private Label noml;

    @FXML
    private Label rubriquel;

    @FXML
    private Label gainl;

    @FXML
    private Label depensel;

    @FXML
    private Label soldel;

    @FXML
    private Label typeop;

    @FXML
    private Label datel;
    @FXML
    private Button cancel;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }


    public void setTextField(int id, String type_operation, String rubrique, Date date, String nom, float gain, float depense, float solde) {
        idl.setText(String.valueOf(id));
        datel.setText(String.valueOf(date));
        typeop.setText(String.valueOf(type_operation));
        rubriquel.setText(String.valueOf(rubrique));
        gainl.setText(String.valueOf(gain));
        depensel.setText(String.valueOf(depense));
        soldel.setText(String.valueOf(solde));
        noml.setText(String.valueOf(nom));

    }
    public void Retour(javafx.event.ActionEvent event) {
        Stage stage =(Stage) cancel.getScene().getWindow();
        stage.close();

    }}
