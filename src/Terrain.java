import java.util.Date;

public class Terrain extends BienImmobilier {

    private double surfacesSol;
    private double longueurFacade;

    public Terrain(int idBien, int prix, String localistation, Date dateDeVenteSouhaitee, Date dateDispo, String orientation, Personne vendeur, double surfacesSol, double longueurFacade) {
        super(idBien, prix, localistation, dateDeVenteSouhaitee, dateDispo, orientation, vendeur);
        this.surfacesSol = surfacesSol;
        this.longueurFacade = longueurFacade;
    }

    public double getSurfacesSol() {
        return surfacesSol;
    }

    public double getLongueurFacade() {
        return longueurFacade;
    }
}
