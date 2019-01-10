


import java.util.ArrayList;


public abstract class Personne {
    private String nom,adresse,numTel,eMail;
    private ArrayList<BienImmobilier> BienAVendre;
    private ArrayList<BienImmobilier> BienAAcheter;
    private Voeux voeux;

    public Personne(String nom, String adresse, String numTel, String eMail) {
        this.nom = nom;
        this.adresse = adresse;
        this.numTel = numTel;
        this.eMail = eMail;
    }

    /*
    Ajouter voeux terrain
     */
    public void ajouterVoeux(String typeDeBien, String localisation, int prixSouhaité, double surfacesSol){

    }

    public void AjouterBienAVendre(BienImmobilier bienImmobilier){ // /!\ Verifier que bien pas déja présent
        BienAVendre.add(bienImmobilier);
    }

    public void AjouterBienAAcheter(BienImmobilier bienImmobilier){ // /!\ Verifier que bien pas déja présent
        BienAAcheter.add(bienImmobilier);
    }

    public ArrayList<BienImmobilier> getBienAVendre() {
        return BienAVendre;
    }

    public ArrayList<BienImmobilier> getBienAAcheter() {
        return BienAAcheter;
    }

    public String getNom() {
        return nom;
    }

    public Voeux getVoeux() {
        return voeux;
    }

    public String geteMail() {
        return eMail;
    }

    public String getAdresse() {
        return adresse;
    }

    public String getNumTel() {
        return numTel;
    }

    public String getEmail() {
        return eMail;
    }

}
