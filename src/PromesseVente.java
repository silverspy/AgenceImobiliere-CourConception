import java.util.Date;

/**
 *Classe promesse de vente.Une promesse de vente est un document signé lors d'un rendez vous d'achat.
 *Une promesse de vente fait reference a un acheteur,un prix,un prix versé au vendeur
 *l'adresse du notaire ainsi que la date du vente avec la commission de l'agence et le frais de vente
 */
public class PromesseVente {
    private Personne acheteur;
    private double prix, prixVerseVendeur;
    String adresseNotaire;
    Date dateVente;
    Double comissionAgence, fraisDeVente;

    /**initie une promesse de vente avec les attributs suivants
     * @param acheteur l'acheteur du bien concerné par la promesse de vente
     * @param prix le prix du bien concerné par la promesse de vente
     * @param adresseNotaire l'adresse du notaire
     * @param dateVente la date de vente prévue
     * @param fraisDeVente les frais de ventes
     */
    public PromesseVente(Personne acheteur, double prix, String adresseNotaire, Date dateVente, Double fraisDeVente) {
        this.acheteur = acheteur;
        this.prix = prix;
        this.adresseNotaire = adresseNotaire;
        this.dateVente = dateVente;
        this.fraisDeVente = fraisDeVente;
    }
}
