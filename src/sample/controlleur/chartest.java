package sample.controlleur;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import sample.Models.Recette;
import sample.Models.login;
import sample.services.recetteServiceImpl;

import javax.swing.*;
import java.net.URL;
import java.time.LocalDate;
import java.time.Month;
import java.time.YearMonth;
import java.util.*;

public class chartest implements Initializable {
    @FXML
    private BarChart<?, ?> chart;

    @FXML
    private CategoryAxis dateaxis;

    @FXML
    private NumberAxis numberaxis;

    @FXML
    private BarChart<?, ?> chartmonth;

    @FXML
    private CategoryAxis dateaxis1;

    @FXML
    private NumberAxis numberaxis1;

    @FXML
    private TextField year;

    @FXML
    private TextField monthc;
    @FXML
    private TextField year1;

    @FXML
    private Button cancelbutton;

    float gaintotale=0;
    float depensetotale=0;
    LocalDate date;
    public login log;
    recetteServiceImpl recetteservice=new recetteServiceImpl();


    public void login(login log){
        this.log=log;
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
public void yearchart(){


        if (year.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "write a year");

          } else{
            chart.getData().clear();
            int yr=0;
            try{
         yr=Integer.parseInt(year.getText());}catch (Exception e){}
    XYChart.Series set1=new XYChart.Series<>();
    XYChart.Series set2=new XYChart.Series<>();


    ObservableList<Recette> list= recetteservice.getDataRecette();
    int l=   list.size();

    Month month=Month.JANUARY;
    for(int y=1;y<=12;y++){
        int n= YearMonth.of(yr,month).lengthOfMonth();
        LocalDate date1=LocalDate.of(yr, month,01);
        LocalDate date2=LocalDate.of(yr,month,n );
        for (date=date1; date.isBefore(date2.plusDays(1));date=date.plusDays(1)){
            for(int m=0;m<l;m++){
                Recette recette=list.get(m);
                if(date.isEqual(LocalDate.parse(recette.getDate().toString())))
                {
                    gaintotale=gaintotale+(recette.getGain());
                    depensetotale=depensetotale+(recette.getDepense());
                }
            }
        }
        set1.getData().add(new XYChart.Data(String.valueOf(month),gaintotale));
        set2.getData().add(new XYChart.Data(String.valueOf(month),depensetotale));
        gaintotale=0;
        depensetotale=0;
        month= month.plus(1);}
    chart.getData().addAll(set1);
    chart.getData().addAll(set2); }
}

    public void monthchart(){


        if (year1.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "veuillez saisir une année");

        } else
        if (monthc.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "veuillez saisir un mois");

        }else
        {
            chartmonth.getData().clear();
            int yr=0;
            Month month=null;
            try{
                yr=Integer.parseInt(year1.getText());
            month=Month.of(Integer.parseInt(monthc.getText()));
            }catch (Exception e){            JOptionPane.showMessageDialog(null, e);
            }
            XYChart.Series set1=new XYChart.Series<>();
            XYChart.Series set2=new XYChart.Series<>();


            ObservableList<Recette> list= recetteservice.getDataRecette();
            int l=   list.size();


                int n= YearMonth.of(yr,month).lengthOfMonth();
                LocalDate date1=LocalDate.of(yr, month,01);
                LocalDate date2=LocalDate.of(yr,month,n );
                for (date=date1; date.isBefore(date2.plusDays(1));date=date.plusDays(1)){
                    for(int m=0;m<l;m++){
                        Recette recette=list.get(m);
                        if(date.isEqual(LocalDate.parse(recette.getDate().toString())))
                        {
                            gaintotale=gaintotale+(recette.getGain());
                            depensetotale=depensetotale+(recette.getDepense());
                        }

                    }
                    set1.getData().add(new XYChart.Data(String.valueOf(date),gaintotale));
                    set2.getData().add(new XYChart.Data(String.valueOf(date),depensetotale));
                    gaintotale=0;
                    depensetotale=0;
                }


            chartmonth.getData().addAll(set1);
            chartmonth.getData().addAll(set2);
                 }


    }
    public void CancelButtonOnAction () {
        Stage stage = (Stage) cancelbutton.getScene().getWindow() ;
        stage.close();


    }
}
