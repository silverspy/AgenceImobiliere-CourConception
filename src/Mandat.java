import java.util.Date;

/**
 *Classe mandat un mandat un est document autorisant l'agence bancaire a faire visiter un bien
 *un mandat dispose de plusieurs informations dont le vendeur,le prix du bien
 *la date de vente souhaité et la date de fin de mandat
 */
public class Mandat {
    Personne vendeur;
    double prix;
    Date dateDeVenteSouhaitee ,dateFinMendat;

    /**
     * Initialise un mandat avec les parametres suivants
     * @param vendeur le propriétaire de la maison
     * @param prix le prix du bien
     * @param dateDeVenteSouhaitee la date de vente souhaité du bien
     * @param dateFinMendat la date de fin du mandat
     */
    public Mandat(Personne vendeur, double prix, Date dateDeVenteSouhaitee, Date dateFinMendat) {
        this.vendeur = vendeur;
        this.prix = prix;
        this.dateDeVenteSouhaitee = dateDeVenteSouhaitee;
        this.dateFinMendat = dateFinMendat;
    }
}
