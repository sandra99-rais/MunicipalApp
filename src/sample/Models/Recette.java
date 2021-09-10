package sample.Models;


import java.util.Date;

public class Recette{
    private Date Date;
    private String Type_operation;
    private String Rubrique;
    private String Nom;
    private int Id;
    private float Gain;
    private float Depense;
    private float Solde;

    public Recette(java.util.Date date, String type_operation, String rubrique, String nom, int id, float gain, float depense, float solde) {
        Date = date;
        Type_operation = type_operation;
        Rubrique = rubrique;
        Nom = nom;
        Id = id;
        Gain = gain;
        Depense = depense;
        Solde = solde;
    }

    public Date getDate() {
        return Date;
    }

    public void setDate(java.util.Date date) {
        Date = date;
    }

    public String getType_operation() {
        return Type_operation;
    }

    public void setType_operation(String type_operation) {
        Type_operation = type_operation;
    }

    public String getRubrique() {
        return Rubrique;
    }

    public void setRubrique(String rubrique) {
        Rubrique = rubrique;
    }

    public String getNom() {
        return Nom;
    }

    public void setNom(String nom) {
        Nom = nom;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public float getGain() {
        return Gain;
    }

    public void setGain(float gain) {
        Gain = gain;
    }

    public float getDepense() {
        return Depense;
    }

    public void setDepense(float depense) {
        Depense = depense;
    }

    public float getSolde() {
        return Solde;
    }

    public void setSolde(float solde) {
        Solde = solde;
    }


}
