public class Appart extends BienImmobilier {

    int nbPieces, etages;
    double charges;

    public Appart(int idBien, int prix, String localistation, String dateDeVenteSouhaitee, String dateDispo, String orientation, Personne vendeur, int nbPieces, int etages, double charges) {
        super(idBien, prix, localistation, dateDeVenteSouhaitee, dateDispo, orientation, vendeur);
        this.nbPieces = nbPieces;
        this.etages = etages;
        this.charges = charges;
    }
}
