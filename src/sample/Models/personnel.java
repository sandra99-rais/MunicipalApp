package sample.Models;

import java.util.Date;

public class personnel {
    String Cin;
    String Nom;
    String Prenom;
    String Grade;
    Date datenaissance;
    String email;
    float salaire;

    public personnel(String cin, String nom, String prenom, String grade, Date datenaissance, String email, float salaire) {
        Cin = cin;
        Nom = nom;
        Prenom = prenom;
        Grade = grade;
        this.datenaissance = datenaissance;
        this.email = email;
        this.salaire = salaire;
    }

    public String getCin() {
        return Cin;
    }

    public void setCin(String cin) {
        Cin = cin;
    }

    public String getNom() {
        return Nom;
    }

    public void setNom(String nom) {
        Nom = nom;
    }

    public String getPrenom() {
        return Prenom;
    }

    public void setPrenom(String prenom) {
        Prenom = prenom;
    }

    public String getGrade() {
        return Grade;
    }

    public void setGrade(String grade) {
        Grade = grade;
    }

    public Date getDatenaissance() {
        return datenaissance;
    }

    public void setDatenaissance(Date datenaissance) {
        this.datenaissance = datenaissance;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public float getSalaire() {
        return salaire;
    }

    public void setSalaire(float salaire) {
        this.salaire = salaire;
    }
}
