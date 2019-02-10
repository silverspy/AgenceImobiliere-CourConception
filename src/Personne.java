


import java.util.ArrayList;


/**
 *Classe personne une personne represente a la fois un potentiel acheteur ou un vendeur pour l'agence bancaire
 *Une personne dispose d'un nom,d'une adresse,d'un numero de telephone,un email,une Liste de bien a vendre ainsi que d'une liste de bien a acheter
 */
public abstract class Personne {
    private String nom,adresse,numTel,eMail;
    private ArrayList<BienImmobilier> BienAVendre;
    private ArrayList<BienImmobilier> BienAAcheter;
    private ArrayList<BienImmobilier> BienSoumis;
    private Voeux voeux;

    /** initialise une personne avec les attributs suivants
     * @param nom le nom de la personne
     * @param adresse l'adresse de la personne
     * @param numTel le numero de telephone de la personne
     * @param eMail l'email de la personne
     */
    public Personne(String nom, String adresse, String numTel, String eMail) {
        this.nom = nom;
        this.adresse = adresse;
        this.numTel = numTel;
        this.eMail = eMail;
        this.BienAAcheter=new ArrayList<>();
        this.BienAVendre=new ArrayList<>();
        this.BienSoumis=new ArrayList<>();
    }

    /*
    Ajouter voeux terrain
     */
    
    /**
     * Ajoute un voeux a la liste de voeux avec les parametres suivants
     * @param typeDeBien le type de bien souhaité
     * @param localisation la localisation du bien souhaité
     * @param prixSouhaité le prix du bien souhaité
     * @param surfacesSol la surface au sol du bien souhaité
     */
    public void ajouterVoeux(String typeDeBien, String localisation, int prixSouhaité, double surfacesSol){
        voeux = new Voeux(typeDeBien,localisation,prixSouhaité,surfacesSol);
    }

    
    /**
     * setteur de la liste de bien Immobilier ajoute le bienImmobilier passé en parametre a la liste
     * @param bienImmobilier le bien immobilier a ajouter
     */
    public void AjouterBienAVendre(BienImmobilier bienImmobilier){
        BienAVendre.add(bienImmobilier);
    }

    /**setteur de la liste de bien a acheter .Ajoute le bien immobilier passé en parametre a la liste
     * @param bienImmobilier le bien a ajouter
     */
    public void AjouterBienAAcheter(BienImmobilier bienImmobilier){ // /!\ Verifier que bien pas déja présent
        BienAAcheter.add(bienImmobilier);
    }

    /** setteur du voeux
     * @param voeux le nouveau voeux
     */
    public void AjoutVoeux(Voeux voeux) {
        this.voeux = voeux;
    }

    /**getteur liste Bien a vendre
     * @return BienAVendre la liste des biens a vendre
     */
    public ArrayList<BienImmobilier> getBienAVendre() {
        return BienAVendre;
    }

    /**getteur liste Bien a acheter
     * @return BienAAcheter la liste des biens a acheter
     */
    public ArrayList<BienImmobilier> getBienAAcheter() {
        return BienAAcheter;
    }
    /**getteur nom
     * @return nom le nom de la personne
     */
    public String getNom() {
        return nom;
    }
    /**getteur le voeux de la personne
     * @return voeux le bien souhaité par la personne
     */
    public Voeux getVoeux() {
        return voeux;
    }
    /**getteur l'adresse email de la personne
     * @return eMail l'adresse email de la personne
     */
    public String geteMail() {
        return eMail;
    }
    /**getteur de l'adresse de la personne
     * @return adresse l'adresse de la personne
     */
    public String getAdresse() {
        return adresse;
    }
    /**getteur du numero de telephone de la Personne
     * @return numTel le numero de telephone de la personne
     */
    public String getNumTel() {
        return numTel;
    }
    /**getteur l'adresse email de la personne
     * @return eMail l'adresse email de la personne
     */
    public String getEmail() {
        return eMail;
    }
    /**getteur la liste de bienSoumis
     * @return BienSoumis la liste de bien soumis a la personne
     */
    public ArrayList<BienImmobilier> getBienSoumis(){
    	return this.BienSoumis;
    }
    public String toString() {
    	String s="Personne \n"+this.nom+","+this.adresse+","+this.eMail+"\n"+this.voeux;
    	return s;
    }
}
