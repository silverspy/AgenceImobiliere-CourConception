package Personne;

/**
 * Une personne morale est un type de personne avec les memes attributs qu'une personne
 */
public class Physique extends Personne {

    /**
     * Initialise une personne physique avec les attributs suivants
     *
     * @param nom     le nom de la personne
     * @param adresse l'adresse de la personne
     * @param numTel  le numero de telephone de la personne
     * @param eMail   l'adresse email de la personne
     */
    public Physique(String nom, String adresse, String numTel, String eMail) {
        super(nom, adresse, numTel, eMail);
    }
}
