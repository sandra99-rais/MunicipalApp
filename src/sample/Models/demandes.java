package sample.Models;

import java.util.Date;

public class demandes {
    String ID_DEMANDE,CIN,DEMANDE,TELEPHONE;
    Date datedemande;
    String lieu,decision,type;

    public demandes(String ID_DEMANDE, String CIN, String DEMANDE, String TELEPHONE, Date datedemande, String lieu, String decision, String type) {
        this.ID_DEMANDE = ID_DEMANDE;
        this.CIN = CIN;
        this.DEMANDE = DEMANDE;
        this.TELEPHONE = TELEPHONE;
        this.datedemande = datedemande;
        this.lieu = lieu;
        this.decision = decision;
        this.type = type;
    } public demandes(String ID_DEMANDE, String CIN, String DEMANDE, String TELEPHONE, Date datedemande, String lieu,  String type) {
        this.ID_DEMANDE = ID_DEMANDE;
        this.CIN = CIN;
        this.DEMANDE = DEMANDE;
        this.TELEPHONE = TELEPHONE;
        this.datedemande = datedemande;
        this.lieu = lieu;

        this.type = type;
    }
    public demandes( String CIN, String DEMANDE, String TELEPHONE, Date datedemande, String lieu, String decision, String type) {
        this.CIN = CIN;
        this.DEMANDE = DEMANDE;
        this.TELEPHONE = TELEPHONE;
        this.datedemande = datedemande;
        this.lieu = lieu;
        this.decision = decision;
        this.type = type;
    }

    public String getID_DEMANDE() {
        return ID_DEMANDE;
    }

    public void setID_DEMANDE(String ID_DEMANDE) {
        this.ID_DEMANDE = ID_DEMANDE;
    }

    public String getCIN() {
        return CIN;
    }

    public void setCIN(String CIN) {
        this.CIN = CIN;
    }

    public String getDEMANDE() {
        return DEMANDE;
    }

    public void setDEMANDE(String DEMANDE) {
        this.DEMANDE = DEMANDE;
    }

    public String getTELEPHONE() {
        return TELEPHONE;
    }

    public void setTELEPHONE(String TELEPHONE) {
        this.TELEPHONE = TELEPHONE;
    }

    public Date getDatedemande() {
        return datedemande;
    }

    public void setDatedemande(Date datedemande) {
        this.datedemande = datedemande;
    }

    public String getLieu() {
        return lieu;
    }

    public void setLieu(String lieu) {
        this.lieu = lieu;
    }

    public String getDecision() {
        return decision;
    }

    public void setDecision(String decision) {
        this.decision = decision;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
