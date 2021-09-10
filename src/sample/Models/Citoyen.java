package sample.Models;

public class Citoyen {
    private String CIN;
    private String nom;
    private String prenom;
    private String date_de_naissance;
    private String sexe;

    public Citoyen() {
    }

    public Citoyen(String CIN, String nom, String prenom, String date_de_naissance,String sexe) {
        this.CIN = CIN;
        this.nom = nom;
        this.prenom = prenom;
        this.date_de_naissance = date_de_naissance;
        this.sexe=sexe;
    }

    public String getCIN() {
        return CIN;
    }

    public void setCIN(String CIN) {
        this.CIN = CIN;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getDate_de_naissance() {
        return date_de_naissance;
    }

    public void setDate_de_naissance(String date_de_naissance) {
        this.date_de_naissance = date_de_naissance;
    }

    public String getSexe() { return sexe; }

    public void setSexe(String sexe) { this.sexe = sexe; }

    @Override
    public String toString() {
        return "Citoyen{" +
                "CIN='" + CIN + '\'' +
                ", nom='" + nom + '\'' +
                ", prenom='" + prenom + '\'' +
                ", date_de_naissance='" + date_de_naissance + '\'' +
                ", sexe='" + sexe + '\'' +
                '}';
    }
}
