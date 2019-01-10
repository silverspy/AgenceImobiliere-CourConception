import java.util.Date;

public class PromesseVente {
    private Personne acheteur;
    private double prix, prixVerseVendeur;
    String adresseNotaire;
    Date dateVente;
    Double comissionAgence, fraisDeVente;

    public PromesseVente(Personne acheteur, double prix, String adresseNotaire, Date dateVente, Double fraisDeVente) {
        this.acheteur = acheteur;
        this.prix = prix;
        this.adresseNotaire = adresseNotaire;
        this.dateVente = dateVente;
        this.fraisDeVente = fraisDeVente;
    }
}
