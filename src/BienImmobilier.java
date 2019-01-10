import java.util.Date;

public abstract class BienImmobilier {

    int idBien;
    private int prix;
    private String localistation, orientation;
    private Date dateDeVenteSouhaitee, dateDispo;
    Mandat mandat;
    PromesseVente promesseVente;
    Personne vendeur;

    public BienImmobilier(int idBien, int prix, String localistation, Date dateDeVenteSouhaitee, Date dateDispo, String orientation, Personne vendeur) {
        this.idBien = idBien;
        this.prix = prix;
        this.localistation = localistation;
        this.dateDeVenteSouhaitee = dateDeVenteSouhaitee;
        this.dateDispo = dateDispo;
        this.orientation = orientation;
        this.vendeur = vendeur;
        vendeur.AjouterBienAVendre(this);
    }

    public int getPrix() {
        return prix;
    }

    public String getLocalistation() {
        return localistation;
    }

    public int getIdBien() {
        return idBien;
    }

    public void inscrire(){

    }

    public void signermandat(Date dateFinMendat){
        mandat = new Mandat(vendeur, prix, dateDeVenteSouhaitee, dateFinMendat);
    }

    public void signerPromesseDeVente(Personne acheteur, double prix, String adresseNotaire, Date dateVente, Double fraisDeVente){
        acheteur.AjouterBienAAcheter(this);
        promesseVente = new PromesseVente(acheteur, prix, adresseNotaire, dateVente, fraisDeVente);
    }

    //rajout gestion vente
}
