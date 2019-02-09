/**
 *Une personne morale est un type de personne avec les memes attributs qu'une personne plus un numero siren et une forme juridique
 */
public class Morale extends Personne {

    private String formJuridique;
    private int siren;

    /**Initialise une personne morale avec les attributs suivants
     * @param nom le nom de la personne morale
     * @param adresse l'adresse de la personne morale
     * @param numTel le numero de telephone de la personne morale
     * @param eMail l'adresse email de la personne morale
     * @param formJuridique la forme juridique de la personne morale
     * @param siren le numero siren de la personne morale
     */
    public Morale(String nom, String adresse, String numTel, String eMail, String formJuridique, int siren) {
        super(nom, adresse, numTel, eMail);
        this.formJuridique = formJuridique;
        this.siren = siren;
    }

    /**
     * getteur de la forme juridique d'une personne morale
     * @return formJuridique
     */
    public String getFormJuridique() {
        return formJuridique;
    }

    /**
     * getteur du numero siren d'une personne morale
     * @return siren
     */
    public int getSiren() {
        return siren;
    }

}
