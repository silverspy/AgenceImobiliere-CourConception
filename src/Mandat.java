import java.util.Date;

public class Mandat {
    Personne vendeur;
    double prix;
    Date dateDeVenteSouhaitee ,dateFinMendat;

    public Mandat(Personne vendeur, double prix, Date dateDeVenteSouhaitee, Date dateFinMendat) {
        this.vendeur = vendeur;
        this.prix = prix;
        this.dateDeVenteSouhaitee = dateDeVenteSouhaitee;
        this.dateFinMendat = dateFinMendat;
    }
}
