import java.util.ArrayList;

public abstract class Personne {

    private String nom,adresse,numTel,eMail;
    private ArrayList<BienImmobilier> BienAVendre;
    private ArrayList<BienImmobilier> BienAAcheter;

    public Personne(String nom, String adresse, String numTel, String eMail) {
        this.nom = nom;
        this.adresse = adresse;
        this.numTel = numTel;
        this.eMail = eMail;
    }

    public void AjouterBienAVendre(BienImmobilier bienImmobilier){ // /!\ Verifier que bien pas déja présent
        BienAVendre.add(bienImmobilier);
    }

    public void AjouterBienAAcheter(BienImmobilier bienImmobilier){ // /!\ Verifier que bien pas déja présent
        BienAAcheter.add(bienImmobilier);
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
}
