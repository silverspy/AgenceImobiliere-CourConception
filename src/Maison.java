public class Maison extends BienImmobilier {

    int nbPieces, nbEtages;
    double surfaceSol ,longueurFacade;
    String moyenDeChauffages;

    public Maison(int idBien, int prix, String localistation, String dateDeVenteSouhaitee, String dateDispo, String orientation, Personne vendeur, int nbPieces, int nbEtages, double surfaceSol, double longueurFacade, String moyenDeChauffages) {
        super(idBien, prix, localistation, dateDeVenteSouhaitee, dateDispo, orientation, vendeur);
        this.nbPieces = nbPieces;
        this.nbEtages = nbEtages;
        this.surfaceSol = surfaceSol;
        this.longueurFacade = longueurFacade;
        this.moyenDeChauffages = moyenDeChauffages;
    }
}
