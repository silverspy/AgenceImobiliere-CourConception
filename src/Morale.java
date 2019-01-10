public class Morale extends Personne {

    String formJuridique;
    int siren;

    public Morale(String nom, String adresse, String numTel, String eMail, String formJuridique, int siren) {
        super(nom, adresse, numTel, eMail);
        this.formJuridique = formJuridique;
        this.siren = siren;
    }

    public String getFormJuridique() {
        return formJuridique;
    }

    public int getSiren() {
        return siren;
    }

}
