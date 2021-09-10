package sample.Models;

import java.util.Date;

public class evenement {
    int id;
    String nom;
	String type;
	String organiseur;
	String lieu;
    String adresse;
    String LIEURENDEZVOUS;
    String HEURERENDEZVOUS;
	int NMBRPERSONNES;
	Date DUREEDU;
    String HEUREDU;
	Date DUREEAU;
    String HEUREAU;
	int TELEPHONE;
    String EMAIL;
    String Descision;
    String description;

    public evenement(String nom, String type, String organiseur, String lieu, String adresse, String LIEURENDEZVOUS, String HEURERENDEZVOUS, int NMBRPERSONNES, Date DUREEDU, String HEUREDU, Date DUREEAU, String HEUREAU, int TELEPHONE, String EMAIL,String Descision, String description) {
        this.nom = nom;
        this.type = type;
        this.organiseur = organiseur;
        this.lieu = lieu;
        this.adresse = adresse;
        this.LIEURENDEZVOUS = LIEURENDEZVOUS;
        this.HEURERENDEZVOUS = HEURERENDEZVOUS;
        this.NMBRPERSONNES = NMBRPERSONNES;
        this.DUREEDU = DUREEDU;
        this.HEUREDU = HEUREDU;
        this.DUREEAU = DUREEAU;
        this.HEUREAU = HEUREAU;
        this.TELEPHONE = TELEPHONE;
        this.EMAIL = EMAIL;
        this.Descision=Descision;
        this.description= description;

    }

    public evenement(int id, String nom, String type, String organiseur, String lieu, String adresse, String LIEURENDEZVOUS, String HEURERENDEZVOUS, int NMBRPERSONNES, Date DUREEDU, String HEUREDU, Date DUREEAU, String HEUREAU, int TELEPHONE, String EMAIL,String Descision, String description) {
        this.id = id;
        this.nom = nom;
        this.type = type;
        this.organiseur = organiseur;
        this.lieu = lieu;
        this.adresse = adresse;
        this.LIEURENDEZVOUS = LIEURENDEZVOUS;
        this.HEURERENDEZVOUS = HEURERENDEZVOUS;
        this.NMBRPERSONNES = NMBRPERSONNES;
        this.DUREEDU = DUREEDU;
        this.HEUREDU = HEUREDU;
        this.DUREEAU = DUREEAU;
        this.HEUREAU = HEUREAU;
        this.TELEPHONE = TELEPHONE;
        this.EMAIL = EMAIL;
        this.Descision=Descision;
        this.description= description;
    } public evenement(int id, String nom, String type, String organiseur, String lieu, String adresse, String LIEURENDEZVOUS, String HEURERENDEZVOUS, int NMBRPERSONNES, Date DUREEDU, String HEUREDU, Date DUREEAU, String HEUREAU, int TELEPHONE, String EMAIL , String description) {
        this.id = id;
        this.nom = nom;
        this.type = type;
        this.organiseur = organiseur;
        this.lieu = lieu;
        this.adresse = adresse;
        this.LIEURENDEZVOUS = LIEURENDEZVOUS;
        this.HEURERENDEZVOUS = HEURERENDEZVOUS;
        this.NMBRPERSONNES = NMBRPERSONNES;
        this.DUREEDU = DUREEDU;
        this.HEUREDU = HEUREDU;
        this.DUREEAU = DUREEAU;
        this.HEUREAU = HEUREAU;
        this.TELEPHONE = TELEPHONE;
        this.EMAIL = EMAIL;
        this.description= description;

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getOrganiseur() {
        return organiseur;
    }

    public void setOrganiseur(String organiseur) {
        this.organiseur = organiseur;
    }

    public String getLieu() {
        return lieu;
    }

    public void setLieu(String lieu) {
        this.lieu = lieu;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getLIEURENDEZVOUS() {
        return LIEURENDEZVOUS;
    }

    public void setLIEURENDEZVOUS(String LIEURENDEZVOUS) {
        this.LIEURENDEZVOUS = LIEURENDEZVOUS;
    }

    public String getHEURERENDEZVOUS() {
        return HEURERENDEZVOUS;
    }

    public void setHEURERENDEZVOUS(String HEURERENDEZVOUS) {
        this.HEURERENDEZVOUS = HEURERENDEZVOUS;
    }

    public int getNMBRPERSONNES() {
        return NMBRPERSONNES;
    }

    public void setNMBRPERSONNES(int NMBRPERSONNES) {
        this.NMBRPERSONNES = NMBRPERSONNES;
    }

    public Date getDUREEDU() {
        return DUREEDU;
    }

    public void setDUREEDU(Date DUREEDU) {
        this.DUREEDU = DUREEDU;
    }

    public String getHEUREDU() {
        return HEUREDU;
    }

    public void setHEUREDU(String HEUREDU) {
        this.HEUREDU = HEUREDU;
    }

    public Date getDUREEAU() {
        return DUREEAU;
    }

    public void setDUREEAU(Date DUREEAU) {
        this.DUREEAU = DUREEAU;
    }

    public String getHEUREAU() {
        return HEUREAU;
    }

    public void setHEUREAU(String HEUREAU) {
        this.HEUREAU = HEUREAU;
    }

    public int getTELEPHONE() {
        return TELEPHONE;
    }

    public void setTELEPHONE(int TELEPHONE) {
        this.TELEPHONE = TELEPHONE;
    }

    public String getEMAIL() {
        return EMAIL;
    }

    public void setEMAIL(String EMAIL) {
        this.EMAIL = EMAIL;
    }

    public String getDescision() {
        return Descision;
    }

    public void setDescision(String descision) {
        Descision = descision;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
