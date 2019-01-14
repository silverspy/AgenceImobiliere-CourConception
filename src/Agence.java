import java.util.ArrayList;
import java.util.List;

public class Agence {

    private List<Annonce> listAnnonce;
    private List<RDV> listRDV;
    private List<BienImmobilier> listBienImmobillier;
    private List<Personne> listPersonneVendeur;
    private List<Personne> listAcheteurPotentiel;

    public Agence(ArrayList<Annonce> lA, ArrayList<RDV> lRDV, ArrayList<BienImmobilier> lBI){
        if(lBI.size()>=1&&lRDV.size()>=1) {
    	this.listAnnonce=lA;
        this.listBienImmobillier=lBI;
        for(BienImmobilier b:lBI) {
        	this.listPersonneVendeur.add(b.getVendeur());
        }
        this.listRDV=lRDV;
        }else {
        	if(lRDV.size()<1) {
        		throw new IllegalArgumentException("Agence initialisee sans rendez vous");
        	}
        	if(lBI.size()<1) {
        		throw new IllegalArgumentException("Agence initialisee sans Bien Immobillier");
        	}
        }
    }
   
    /*
    public Agence(){
        this.listAnnonce=new ArrayList<>();
        this.listBienImmobillier=new ArrayList<>();
        this.listRDV=new ArrayList<>();
    }
     */
    
    /*Voeux getVoeux() {
    }*/
    List<Annonce>  getAnnonces(){
        return this.listAnnonce;
    }

    List<RDV>  getRDV(){
        return this.listRDV;
    }

    List<BienImmobilier>  getBienImmobillier(){
        return this.listBienImmobillier;
    }

    void setAnnonce(ArrayList<Annonce> lA){
        this.listAnnonce=lA;
    }

    void setRDV(ArrayList<RDV> RDV){
        this.listRDV=RDV;
    }
    void setBienImmobilier(ArrayList<BienImmobilier> lBI){
        this.listBienImmobillier=lBI;
    }


    void prendreRDVMandat(){
        this.listRDV.add(new RDV());

    }

    void inscriptionVentePersonneMorale(String nom,String adresse,String tel,String mail,String formJuridique, int siren,String type, ){
    	listPersonneVendeur.add(new Morale(nom,adresse,tel,mail,formJuridique,siren));
    	this.prendreRDVMandat();
    }
    void prendreRDVVente(){

    }
    void prendreRDVVisite(){

    }

}
