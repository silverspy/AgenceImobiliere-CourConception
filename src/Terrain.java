import java.util.Date;

/**
 *Classe terrain.Un terrain est un certain type de bien immobilier
 *un terrain dispose de tout les attributs d'un bien immobilier plus la surface au sol et
 *et la longueur de la facade
 */
public class Terrain extends BienImmobilier {

    private double surfacesSol;
    private double longueurFacade;

    /**
     * initialise un terrain avec les attributs suivants
     * @param idBien l'id du bien
     * @param prix le prix du terrain
     * @param localistation la localisation du terrain
     * @param dateDeVenteSouhaitee la date de vente souhaité
     * @param dateDispo la date de disponibilité du terrain
     * @param orientation l'orientation du terrain
     * @param vendeur le propriétaire du terrain
     * @param surfacesSol la surface au sol du terrain
     * @param longueurFacade la longueur de la facade du terrain
     */
    public Terrain(int idBien, int prix, String localistation, Date dateDeVenteSouhaitee, Date dateDispo, String orientation, Personne vendeur, double surfacesSol, double longueurFacade) {
        super(idBien, prix, localistation, dateDeVenteSouhaitee, dateDispo, orientation, vendeur);
        this.surfacesSol = surfacesSol;
        this.longueurFacade = longueurFacade;
    }

    /**getteur de la surface au sol du terrain
     * @return surfacesSol
     */
    public double getSurfacesSol() {
        return surfacesSol;
    }

    /**getteur de la longueur de la facade
     * @return longueur de la facade
     */
    public double getLongueurFacade() {
        return longueurFacade;
    }
}
