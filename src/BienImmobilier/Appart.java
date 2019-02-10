package BienImmobilier;

import Personne.Personne;

import java.util.Date;


/**
 * Classe BienImmobilier.Appart un BienImmobilier.Appart est un type de bienImmobilier possedant un nombre de piece,un etage et des charges
 */
public class Appart extends BienImmobilier {


    private int nbPieces, etages;
    private double charges;

    /**
     * Creer un Appartement avec les attributs suivants
     *
     * @param idBien               l'identifiant du bien
     * @param prix                 le prix de l'appart
     * @param localistation        la localisation de l'appart
     * @param dateDeVenteSouhaitee la date de vente souhaité du bien
     * @param dateDispo            la date a partir de laquelle le bien est disponible
     * @param orientation          l'orientation du bien
     * @param vendeur              le vendeur/propriétaire du bien
     * @param nbPieces             le nombre de piece du bien
     * @param etages               l'étage de l'appartement
     * @param charges              les charges
     */
    public Appart(int idBien, int prix, String localistation, Date dateDeVenteSouhaitee, Date dateDispo, String orientation, Personne vendeur, int nbPieces, int etages, double charges) {
        super(idBien, prix, localistation, dateDeVenteSouhaitee, dateDispo, orientation, vendeur);
        this.setNbPieces(nbPieces);
        this.etages = etages;
        this.charges = charges;
    }

    /**
     * Creer un Appartement avec les attribut suivants
     *
     * @param idBien               l'identifiant du bien
     * @param prix
     * @param localistation
     * @param dateDeVenteSouhaitee
     * @param dateDispo
     * @param orientation
     * @param vendeur
     * @param nbPieces
     * @param etages
     */

    public Appart(int idBien, int prix, String localistation, Date dateDeVenteSouhaitee, Date dateDispo, String orientation, Personne vendeur, int nbPieces, int etages) {
        super(idBien, prix, localistation, dateDeVenteSouhaitee, dateDispo, orientation, vendeur);
        this.setNbPieces(nbPieces);
        this.etages = etages;
    }

    /**
     * get le nombre de pieces
     *
     * @return nbPieces
     */
    public int getNbPieces() {
        return nbPieces;
    }

    /**
     * set le nombre de piece
     *
     * @param nbPieces
     */
    public void setNbPieces(int nbPieces) {
        this.nbPieces = nbPieces;
    }

    /**
     * getEtages
     *
     * @return etages
     **/
    public int getNbEtages() {
        return etages;
    }

    /**
     * get les charges
     *
     * @return Charges
     */
    public double getCharges() {
        return charges;
    }

    /**
     * set etages
     *
     * @param etages
     */
    public void setEtages(int etages) {
        this.etages = etages;
    }

    /**
     * set les charges
     *
     * @param charges
     */
    public void setCharges(double charges) {
        this.charges = charges;
    }
}
