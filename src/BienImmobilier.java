import java.security.InvalidParameterException;
import java.util.Date;

/**
 * classe abstraite Bien Immobilier .Un bien Immobilier comporte un id,un prix,une localisation,une orientation
 * une Date de vente souhaité une date de disponibilité ainsi qu'un mandat pour autorisé les visites ,une promesse de vente
 * et un propriétaire/vendeur
 *
 */
public abstract class BienImmobilier {

    int idBien;
    private int prix;
    private String localistation, orientation;
    private Date dateDeVenteSouhaitee, dateDispo;
    Mandat mandat;
    PromesseVente promesseVente;
    Personne vendeur;

    /**Constructeur d un bien immobilier avec les attributs suivants
     * @param idBien
     * @param prix
     * @param localistation
     * @param dateDeVenteSouhaitee
     * @param dateDispo
     * @param orientation
     * @param vendeur
     */
    public BienImmobilier(int idBien, int prix, String localistation, Date dateDeVenteSouhaitee, Date dateDispo, String orientation, Personne vendeur) {
        this.idBien = idBien;
        this.prix = prix;
        this.localistation = localistation;
        this.dateDeVenteSouhaitee = dateDeVenteSouhaitee;
        this.dateDispo = dateDispo;
        this.orientation = orientation;
        this.vendeur = vendeur;
        this.vendeur.AjouterBienAVendre(this);
    }

    /**
     * @return prix le prix du bien Immobilier
     */
    public int getPrix() {
        return prix;
    }

    /**
     * @return localisation La localisation du bien Immobilier
     */
    public String getLocalistation() {
        return localistation;
    }

    /**
     * @return idBien l'identifiant du bien
     */
    public int getIdBien() {
        return idBien;
    }

    /**
     * Inscrit le bien immobilier a la vente
     */
    public void inscrire(){

    }

    /**
     * Ajoute le mandat au bien Immobilier necessite la date de fin de mandat
     * @param dateFinMendat
     * @throws InvalidParameterException Mandat already exist if bienImmobilier have already a mandat
     */
    public void signermandat(Date dateFinMendat){
        if (mandat == null) {
            mandat = new Mandat(vendeur, prix, dateDeVenteSouhaitee, dateFinMendat);
        } else {
            throw new InvalidParameterException("Mandat already exist");
        }
    }

    /** retourne le mandat si présent
     * @return mandat si présent
     * @throws InvalidParameterException Mandat already exist if bienImmobilier have already a mandat
     */
    public Mandat getMandat() {
        return mandat;
    }

    /**
     * Ajoute une promesse de fin de vente au bien Immobilier necessite les parametres suivants
     * @param acheteur le futur acheteur du bien
     * @param prix le prix du bien 
     * @param adresseNotaire l'adresse du notaire qui signe la promesse de vente
     * @param dateVente date de vente du bien immobilier
     * @param fraisDeVente frais de vente
     */
    public void signerPromesseDeVente(Personne acheteur, double prix, String adresseNotaire, Date dateVente, Double fraisDeVente){
        acheteur.AjouterBienAAcheter(this);
        promesseVente = new PromesseVente(acheteur, prix, adresseNotaire, dateVente, fraisDeVente);
    }

    /** retourne la promesse de vente
     * @return promesseVente
     */
    public PromesseVente getPromesseVente() {
        return promesseVente;
    }

    /**retourne le vendeur/propriétaire du bien
	 * @return
	 */
	public Personne getVendeur() {
		return this.vendeur;
	}

    //rajout gestion vente
}
