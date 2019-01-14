import java.util.Date;

public class Appart extends BienImmobilier {

    private int nbPieces, etages;
    private double charges;

    public Appart(int idBien, int prix, String localistation, Date dateDeVenteSouhaitee, Date dateDispo, String orientation, Personne vendeur, int nbPieces, int etages, double charges) {
        super(idBien, prix, localistation, dateDeVenteSouhaitee, dateDispo, orientation, vendeur);
        this.nbPieces = nbPieces;
        this.etages = etages;
        this.charges = charges;
    }

    public Appart(int idBien, int prix, String localistation, Date dateDeVenteSouhaitee, Date dateDispo, String orientation, Personne vendeur, int nbPieces, int etages) {
        super(idBien, prix, localistation, dateDeVenteSouhaitee, dateDispo, orientation, vendeur);
        this.nbPieces = nbPieces;
        this.etages = etages;
    }
}
