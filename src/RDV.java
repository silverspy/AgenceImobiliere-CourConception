import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class RDV {
    private Date dateRDV;
    private BienImmobilier bien;
    private List<Personne> lsPersonne;
    private Personne vendeur;
    private Personne acheteur;
    private String typeRdv;


    public RDV(Date d, BienImmobilier b,Personne achet,Personne vend,String typeRdv) throws IllegalArgumentException{
    	switch(typeRdv) {
    	//to Complete
    	case "Vente":
    		if(vend==null||achet==null) {
    			throw new IllegalArgumentException("un Rendez vous de vente doit comporter un vendeur et un acheteur");
    		}else {
    			this.dateRDV=d;
    			this.bien=b;
    			this.vendeur=vend;
    			this.acheteur=achet;
    			this.typeRdv=typeRdv;
    			//b.signerPromesseDeVente();

    		}
    		break;

    	}
    }
    
    public RDV(Date d, BienImmobilier b,Personne vendeur,String typeRdv,Date dateFinMandat) throws IllegalArgumentException{
    	switch(typeRdv) {
    	
    	case "Mandat":
    		if(vendeur==null) {
    			throw new IllegalArgumentException("Un rendez vous pour signer un mandat ne fait intervenir que le vendeur");
    		}else {
    			if(!vendeur.equals(b.getVendeur())) {
    				throw new IllegalArgumentException("le vendeur doit faire partie des personnes concernné par le rendez vous de vente");
    			}
    			this.dateRDV=d;
    			this.bien=b;
    			this.vendeur=vendeur;
    			this.typeRdv=typeRdv;
    			b.signermandat(dateFinMandat);
    		}
    		break;
    	}
    }
    
    public RDV(Date d, BienImmobilier b,String typeRdv,Personne acheteur) throws IllegalArgumentException{
    	switch(typeRdv) {
    	
    	case "Visite":
    		if(acheteur==null||b==null) {
    			throw new IllegalArgumentException("Une visite necessite un bien immobillier et un acheteur potentiels (non null)");
    		}else {
    			if(acheteur.equals(b.getVendeur())) {
    				throw new IllegalArgumentException("Un vendeur n'as aucun interet a visiter son propre bien");
    			}
    			this.dateRDV=d;
    			this.bien=b;
    			this.acheteur=acheteur;
    			this.typeRdv=typeRdv;
    		}
    		break;
    	}
    }

    Date getDateRDV(){
     return this.dateRDV;
    }
    BienImmobilier getBien(){
        return  this.bien;
    }
    List<Personne> getLsPersonne(){
        return this.lsPersonne;
    }

    void setDateRDV(Date d){
        this.dateRDV=d;
    }

    void setBien(BienImmobilier b){
        this.bien=b;
    }
    void setLsPersonne(ArrayList<Personne> l){
        this.lsPersonne=l;
    }

}
