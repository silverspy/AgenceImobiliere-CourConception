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
    		if(vend!=null&&achet!=null) {
    			throw new IllegalArgumentException("un Rendez vous de vente doit comporter un vendeur et un acheteur");
    		}else {
    			this.dateRDV=d;
    			this.bien=b;
    			this.vendeur=vend;
    			this.acheteur=achet;
    			this.typeRdv=typeRdv;
    			b.signerPromesseVente();
    			//this.prix=prix;

    		}
    		break;

    	}
    }
    
    public RDV(Date d, BienImmobilier b,Personne vendeur,String typeRdv,Date dVS,Date dateFinMandat) throws IllegalArgumentException{
    	switch(typeRdv) {
    	
    	case "Mandat":
    		if(lsP.size()>1) {
    			throw new IllegalArgumentException("Un rendez vous pour signer un mandat ne fait intervenir que le vendeur");
    		}else {
    			if(lsP.indexOf(b.getVendeur())==-1) {
    				throw new IllegalArgumentException("le vendeur doit faire partie des personnes concernné par le rendez vous de vente");
    			}
    			this.dateRDV=d;
    			this.bien=b;
    			this.vendeur=vendeur;
    			this.typeRdv=typeRdv;
    			b.signerMandat(dateFinMandat);
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
