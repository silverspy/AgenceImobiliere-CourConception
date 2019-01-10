import java.util.Collection;

public class Personne {

    String nom,adresse,numTel,eMail;

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
