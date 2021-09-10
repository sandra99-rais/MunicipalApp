
package sample.Models;

import java.util.Date;

public class voitures {
    String Marque;
    String Matricule;
    String Couleur;
    String Nombre;
    String Prix;
    Date Datee;
    String Type;
    int ID;

    public voitures(String marque, String matricule, String couleur, String nombre, String prix, Date datee, String type, int ID) {
        Marque = marque;
        Matricule = matricule;
        Couleur = couleur;
        Nombre = nombre;
        Prix = prix;
        Datee = datee;
        Type = type;
        this.ID = ID;
    }
    public voitures(String marque, String matricule, String couleur, String nombre, String prix, Date datee, String type) {
        Marque = marque;
        Matricule = matricule;
        Couleur = couleur;
        Nombre = nombre;
        Prix = prix;
        Datee = datee;
        Type = type;

    }

    public String getMarque() {
        return Marque;
    }

    public void setMarque(String marque) {
        Marque = marque;
    }

    public String getMatricule() {
        return Matricule;
    }

    public void setMatricule(String matricule) {
        Matricule = matricule;
    }

    public String getCouleur() {
        return Couleur;
    }

    public void setCouleur(String couleur) {
        Couleur = couleur;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String nombre) {
        Nombre = nombre;
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

    public String getType() {
        return Type;
    }

    public void setType(String type) {
        Type = type;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }
}