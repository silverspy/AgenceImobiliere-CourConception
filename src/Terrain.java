public class Terrain extends BienImmobilier {

    double surfacesSol;
    double longueurFacade;

    public Terrain(int idBien, int prix, String localistation, String dateDeVenteSouhaitee, String dateDispo, String orientation, Personne vendeur, double surfacesSol, double longueurFacade) {
        super(idBien, prix, localistation, dateDeVenteSouhaitee, dateDispo, orientation, vendeur);
        this.surfacesSol = surfacesSol;
        this.longueurFacade = longueurFacade;
    }
}
