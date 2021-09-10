package sample.Models;



import java.util.Date;

public class travaux {
    String Num_Cin;
    String Nom_chef;
    String Type;
    String Region;
    Date DateD;
    String Heure_debut;
    Date DateF;
    String Heure_fin;

    String Besoin_materiels;
    String Besoin_vehicules;
    String Budget;
    int ID;
    String description;

    public travaux(String num_Cin, String nom_chef, String type, String region, Date dateD, String heure_debut, Date dateF, String heure_fin, String besoin_materiels, String besoin_vehicules, String budget, int ID,String description) {
        Num_Cin = num_Cin;
        Nom_chef = nom_chef;
        Type = type;
        Region = region;
        DateD = dateD;
        Heure_debut = heure_debut;
        DateF = dateF;
        Heure_fin = heure_fin;
        Besoin_materiels = besoin_materiels;
        Besoin_vehicules = besoin_vehicules;
        Budget = budget;
        this.ID = ID;
        this.description=description;
    }
    public travaux(String num_Cin, String nom_chef, String type, String region, Date dateD, String heure_debut, Date dateF, String heure_fin, String besoin_materiels, String besoin_vehicules, String budget,String description) {
        Num_Cin = num_Cin;
        Nom_chef = nom_chef;
        Type = type;
        Region = region;
        DateD = dateD;
        Heure_debut = heure_debut;
        DateF = dateF;
        Heure_fin = heure_fin;
        Besoin_materiels = besoin_materiels;
        Besoin_vehicules = besoin_vehicules;
        Budget = budget;
        this.description=description;

    }

    public String getNum_Cin() {
        return Num_Cin;
    }

    public void setNum_Cin(String num_Cin) {
        Num_Cin = num_Cin;
    }

    public String getNom_chef() {
        return Nom_chef;
    }

    public void setNom_chef(String nom_chef) {
        Nom_chef = nom_chef;
    }

    public String getType() {
        return Type;
    }

    public void setType(String type) {
        Type = type;
    }

    public String getRegion() {
        return Region;
    }

    public void setRegion(String region) {
        Region = region;
    }

    public Date getDateD() {
        return DateD;
    }

    public void setDateD(Date dateD) {
        DateD = dateD;
    }

    public String getHeure_debut() {
        return Heure_debut;
    }

    public void setHeure_debut(String heure_debut) {
        Heure_debut = heure_debut;
    }

    public Date getDateF() {
        return DateF;
    }

    public void setDateF(Date dateF) {
        DateF = dateF;
    }

    public String getHeure_fin() {
        return Heure_fin;
    }

    public void setHeure_fin(String heure_fin) {
        Heure_fin = heure_fin;
    }

    public String getBesoin_materiels() {
        return Besoin_materiels;
    }

    public void setBesoin_materiels(String besoin_materiels) {
        Besoin_materiels = besoin_materiels;
    }

    public String getBesoin_vehicules() {
        return Besoin_vehicules;
    }

    public void setBesoin_vehicules(String besoin_vehicules) {
        Besoin_vehicules = besoin_vehicules;
    }

    public String getBudget() {
        return Budget;
    }

    public void setBudget(String budget) {
        Budget = budget;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}