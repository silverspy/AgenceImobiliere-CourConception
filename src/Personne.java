public abstract class Personne {

    String nom,adresse,numTel,eMail;

    public Personne(String nom, String adresse, String numTel, String eMail) {
        this.nom = nom;
        this.adresse = adresse;
        this.numTel = numTel;
        this.eMail = eMail;
    }



    public String getNom() {
        return nom;
    }

    public String getAdresse() {
        return adresse;
    }

    public String getNumTel() {
        return numTel;
    }

    public String getEmail() {
        return eMail;
    }

	public Collection<? extends Voeux> getListVoeux() {
		// TODO Auto-generated method stub
		return null;
	}
}
