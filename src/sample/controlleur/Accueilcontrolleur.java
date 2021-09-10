package sample.controlleur;

import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Rectangle2D;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Screen;
import javafx.util.Duration;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import sample.Models.login;


public class Accueilcontrolleur implements Initializable {

    @FXML
    private Button exit;

    @FXML
    private ImageView Menu;

    @FXML
    private ImageView MenuClose;

    @FXML
    private AnchorPane slider;
    public login log;


    public void login(login log){
        this.log=log;
    }
    public void tableconsulter() {
        try {

            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/sample/views/tableconsulter.fxml"));
            Parent root1 = (Parent)fxmlLoader.load();
            TableconsulterController controlleur=fxmlLoader.getController();
            controlleur.login(log);
            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.initStyle(StageStyle.UNDECORATED);
            stage.setTitle("ABC");
            stage.setMaximized(true);


            stage.setScene(new Scene(root1));
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void materiel() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/sample/views/gestionmateriel.fxml"));
            Parent root1 = (Parent)fxmlLoader.load();
            Controllermateriels controlleur=fxmlLoader.getController();
            controlleur.login(log);
            Stage stage = new Stage();
            stage.setMaximized(true);
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.initStyle(StageStyle.UNDECORATED);
            stage.setTitle("ABC");
            stage.setScene(new Scene(root1));
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void vehicule() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/sample/views/vehicules.fxml"));
            Parent root1 = (Parent)fxmlLoader.load();
            Controllervehicules controlleur=fxmlLoader.getController();
            controlleur.login(log);
            Stage stage = new Stage();
            stage.setMaximized(true);
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.initStyle(StageStyle.UNDECORATED);
            stage.setTitle("ABC");
            stage.setScene(new Scene(root1));
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void teams() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/sample/views/team.fxml"));
            Parent root1 = (Parent)fxmlLoader.load();
            Controllerequip controlleur=fxmlLoader.getController();
            controlleur.login(log);
            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.initStyle(StageStyle.UNDECORATED);
            stage.setTitle("ABC");
            stage.setMaximized(true);
            stage.setScene(new Scene(root1));
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void gestioncomptes() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/sample/views/Gestion_Compte.fxml"));
            Parent root1 = (Parent)fxmlLoader.load();
            Gestion_Compte controlleur=fxmlLoader.getController();
            controlleur.login(log);
            Stage stage = new Stage();
            stage.setMaximized(true);
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.initStyle(StageStyle.UNDECORATED);
            stage.setTitle("ABC");
            stage.setScene(new Scene(root1));
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    public void evenement() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/sample/views/evenement.fxml"));
            Parent root1 = (Parent)fxmlLoader.load();
            evenementControlleur controlleur=fxmlLoader.getController();
            controlleur.login(log);
            Stage stage = new Stage();
            stage.setMaximized(true);
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.initStyle(StageStyle.UNDECORATED);
            stage.setTitle("ABC");
            stage.setScene(new Scene(root1));
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    public void evenementDecision() {
        try {

            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/sample/views/evenementdecision.fxml"));
            Parent root1 = (Parent) fxmlLoader.load();
            evenementdecisioncontrolleur controlleur=fxmlLoader.getController();
            controlleur.login(log);
            Stage stage = new Stage();
            stage.setMaximized(true);
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.initStyle(StageStyle.UNDECORATED);
            stage.setTitle("ABC");
            stage.setScene(new Scene(root1));
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        exit.setOnMouseClicked(event -> {
            System.exit(0);
        });
        slider.setTranslateX(0);
        Menu.setOnMouseClicked(event -> {
            TranslateTransition slide = new TranslateTransition();
            slide.setDuration(Duration.seconds(0.4));
            slide.setNode(slider);

            slide.setToX(0);
            slide.play();

            slider.setTranslateX(-500);

            slide.setOnFinished((ActionEvent e)-> {
                Menu.setVisible(false);
                MenuClose.setVisible(true);
            });
        });

        MenuClose.setOnMouseClicked(event -> {
            TranslateTransition slide = new TranslateTransition();
            slide.setDuration(Duration.seconds(0.4));
            slide.setNode(slider);

            slide.setToX(-500);
            slide.play();

            slider.setTranslateX(0);

            slide.setOnFinished((ActionEvent e)-> {
                Menu.setVisible(true);
                MenuClose.setVisible(false);
            });
        });
    }

    public void cancelButtonOnAction(){
        Stage stage =(Stage) exit.getScene().getWindow();
        stage.close();
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/sample/views/login.fxml"));
            Parent root1 = (Parent) fxmlLoader.load();

            stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.initStyle(StageStyle.UNDECORATED);
            stage.setTitle("ABC");
            stage.setScene(new Scene(root1));
            stage.show();

        }catch(Exception e){
            e.printStackTrace();
        }}
        public void recette() {
            try {
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/sample/views/GestionRecette.fxml"));
                Parent root1 = (Parent)fxmlLoader.load();
                ControllerRecette controlleur=fxmlLoader.getController();
                controlleur.login(log);
                Stage stage = new Stage();
                stage.setMaximized(true);
                stage.initModality(Modality.APPLICATION_MODAL);
                stage.initStyle(StageStyle.UNDECORATED);
                stage.setTitle("ABC");
                stage.setScene(new Scene(root1));
                stage.show();
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    public void demande() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/sample/views/demandes.fxml"));
            Parent root1 = (Parent)fxmlLoader.load();
            Stage stage = new Stage();
            stage.setMaximized(true);
            demandescontrolleur controlleur=fxmlLoader.getController();
            controlleur.login(log);
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.initStyle(StageStyle.UNDECORATED);
            stage.setTitle("ABC");
            stage.setScene(new Scene(root1));
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    public void travaux() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/sample/views/Travaux.fxml"));
            Parent root1 = (Parent)fxmlLoader.load();
            ControllerTravauxx controlleur=fxmlLoader.getController();
            controlleur.login(log);
            Stage stage = new Stage();
            stage.setMaximized(true);
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.initStyle(StageStyle.UNDECORATED);
            stage.setTitle("ABC");
            stage.setScene(new Scene(root1));
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    public void chart() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/sample/views/chartest.fxml"));
            Parent root1 = (Parent)fxmlLoader.load();
            chartest controlleur=fxmlLoader.getController();
            controlleur.login(log);
            Stage stage = new Stage();
            stage.setMaximized(true);
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.initStyle(StageStyle.UNDECORATED);
            stage.setTitle("ABC");
            stage.setScene(new Scene(root1));
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    }
