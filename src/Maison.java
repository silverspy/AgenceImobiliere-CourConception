import java.util.Date;

/**
 *Classe Maison une Maison est un type de bienImmobilier.Une maison dispose de tout les attributs d'un bien Immobilier plus 
 *le nombre de pieces ,le nombre d'étages la surface au sol ,la longueure de la facade et le moyen de chauffage
 */
public class Maison extends BienImmobilier {
public abstract class Maison extends BienImmobilier {

    private int nbPieces, nbEtages;
    private double surfaceSol ,longueurFacade;
    private String moyenDeChauffages;

    /** Initialise une Maison avec les attributs suivant
     * @param idBien l'identifiant du bien
     * @param prix le prix de la maison
     * @param localistation la localisation de la maison
     * @param dateDeVenteSouhaitee la date de vente souhaitée de la maison
     * @param dateDispo la date a partir du quelle la maison est disponible
     * @param orientation orientation de la maison
     * @param vendeur le vendeur de la maison
     * @param nbPieces le nombre de pieces de la maison
     * @param nbEtages le nombre d'étages de la maison
     * @param surfaceSol la surface au sol de la maison
     * @param longueurFacade la longueur de la facade de la maison
     * @param moyenDeChauffages le moyen de chauffage de la maison
     */
    public Maison(int idBien, int prix, String localistation, Date dateDeVenteSouhaitee, Date dateDispo, String orientation, Personne vendeur, int nbPieces, int nbEtages, double surfaceSol, double longueurFacade, String moyenDeChauffages) {
        super(idBien, prix, localistation, dateDeVenteSouhaitee, dateDispo, orientation, vendeur);
        this.nbPieces = nbPieces;
        this.nbEtages = nbEtages;
        this.surfaceSol = surfaceSol;
        this.longueurFacade = longueurFacade;
        this.moyenDeChauffages = moyenDeChauffages;
    }
    /** Initialise une Maison avec les attributs suivant
     * @param idBien l'identifiant du bien
     * @param prix le prix de la maison
     * @param localistation la localisation de la maison
     * @param dateDeVenteSouhaitee la date de vente souhaitée de la maison
     * @param dateDispo la date a partir du quelle la maison est disponible
     * @param orientation orientation de la maison
     * @param vendeur le vendeur de la maison
     * @param nbPieces le nombre de pieces de la maison
     * @param nbEtages le nombre d'étages de la maison
     * @param surfaceSol la surface au sol de la maison
     * @param longueurFacade la longueur de la facade de la maison
     */
    public Maison(int idBien, int prix, String localistation, Date dateDeVenteSouhaitee, Date dateDispo, String orientation, Personne vendeur, int nbPieces, int nbEtages, double surfaceSol, double longueurFacade) {
        super(idBien, prix, localistation, dateDeVenteSouhaitee, dateDispo, orientation, vendeur);
        this.nbPieces = nbPieces;
        this.nbEtages = nbEtages;
        this.surfaceSol = surfaceSol;
        this.longueurFacade = longueurFacade;
    }

    /**
     * getteur du nombre de pieces de la maison
     * @return nbPieces
     */
    public int getNbPieces() {
        return nbPieces;
    }

    /**
     * getteur du nombre d'étage de la maison
     * @return nbEtage
     */
    public int getNbEtages() {
        return nbEtages;
    }

    /**
     * getteur de la surface au sol de la maison
     * @return surfaceSol
     */
    public double getSurfaceSol() {
        return surfaceSol;
    }

    /**
     * getteur de la longueur de la facade de la maison
     * @return longueurFacade
     */
    public double getLongueurFacade() {
        return longueurFacade;
    }

    /**
     * getteur du moyen de chauffage de la maison
     * @return
     */
    public String getMoyenDeChauffages() {
        return moyenDeChauffages;
    }
}
