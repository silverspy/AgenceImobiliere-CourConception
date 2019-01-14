import java.util.Date;

public class Maison extends BienImmobilier {

    private int nbPieces, nbEtages;
    private double surfaceSol ,longueurFacade;
    private String moyenDeChauffages;

    public Maison(int idBien, int prix, String localistation, Date dateDeVenteSouhaitee, Date dateDispo, String orientation, Personne vendeur, int nbPieces, int nbEtages, double surfaceSol, double longueurFacade, String moyenDeChauffages) {
        super(idBien, prix, localistation, dateDeVenteSouhaitee, dateDispo, orientation, vendeur);
        this.nbPieces = nbPieces;
        this.nbEtages = nbEtages;
        this.surfaceSol = surfaceSol;
        this.longueurFacade = longueurFacade;
        this.moyenDeChauffages = moyenDeChauffages;
    }

    public Maison(int idBien, int prix, String localistation, Date dateDeVenteSouhaitee, Date dateDispo, String orientation, Personne vendeur, int nbPieces, int nbEtages, double surfaceSol, double longueurFacade) {
        super(idBien, prix, localistation, dateDeVenteSouhaitee, dateDispo, orientation, vendeur);
        this.nbPieces = nbPieces;
        this.nbEtages = nbEtages;
        this.surfaceSol = surfaceSol;
        this.longueurFacade = longueurFacade;
    }

    public int getNbPieces() {
        return nbPieces;
    }

    public int getNbEtages() {
        return nbEtages;
    }

    public double getSurfaceSol() {
        return surfaceSol;
    }

    public double getLongueurFacade() {
        return longueurFacade;
    }

    public String getMoyenDeChauffages() {
        return moyenDeChauffages;
    }
}
