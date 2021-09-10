package sample.Models;


import java.util.Date;

public class Materiels {
    String ID_materiel;
    String Type;

    String Quantité;
    String Prix;
    Date Datee;
    Integer ID;
    String description;


    public Materiels(String ID_materiel, String type, String quantité, String prix, Date datee, Integer ID, String description) {
        this.ID_materiel = ID_materiel;
        Type = type;
        Quantité = quantité;
        Prix = prix;
        Datee = datee;
        this.ID = ID;
        this.description = description;
    }
    public Materiels(String ID_materiel, String type, String quantité, String prix, Date datee, String description) {
        this.ID_materiel = ID_materiel;
        Type = type;
        Quantité = quantité;
        Prix = prix;
        Datee = datee;
        this.description = description;
    }

    public String getID_materiel() {
        return ID_materiel;
    }

    public void setID_materiel(String ID_materiel) {
        this.ID_materiel = ID_materiel;
    }

    public String getType() {
        return Type;
    }

    public void setType(String type) {
        Type = type;
    }

    public String getQuantité() {
        return Quantité;
    }

    public void setQuantité(String quantité) {
        Quantité = quantité;
    }

    public String getPrix() {
        return Prix;
    }

    public void setPrix(String prix) {
        Prix = prix;
    }

    public Date getDatee() {
        return Datee;
    }

    public void setDatee(Date datee) {
        Datee = datee;
    }

    public Integer getID() {
        return ID;
    }

    public void setID(Integer ID) {
        this.ID = ID;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}