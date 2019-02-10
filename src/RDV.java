import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Classe RDV.Un rendez vous peut etre un mandat,une visite ou un rendez vous de vente.
 * un rendez vous dispose d une date concerne un bien immobilier ,une liste de personne ainsi qu un acheteur et un vendeur
 *
 */
public class RDV {
    private Date dateRDV;
    private BienImmobilier bien;
    private List<Personne> lsPersonne;
    private Personne vendeur;
    private Personne acheteur;
    private String typeRdv;


    /** Initialise un RDV  de vente avec les parametres suivants 
     * @param d la date du rendez vous
     * @param b le bien immobilier concerne
     * @param achet l acheteur du bien immobilier
     * @param vend le vendeur
     * @param typeRdv le type de rendez vous
     * @throws IllegalArgumentException
     */
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
    			System.out.println("on organise un RDV pour signer la vente");
    		}
    		break;

    	}
    }
    
    /** Initialise un rendez vous pour signer un mandat avec les attributs suivants
     * @param d la date du rendez vous
     * @param b le bien immobilier concerné par le mandat
     * @param vendeur le vendeur du bien
     * @param typeRdv le type de rendez vous ici mandat
     * @param dateFinMandat la date de la fin du mandat
     * @throws IllegalArgumentException
     */
    public RDV(Date d, BienImmobilier b,Personne vendeur,String typeRdv,Date dateFinMandat) throws IllegalArgumentException{
    	switch(typeRdv) {
    	
    	case "Mandat":
    		if(vendeur==null) {
    			throw new IllegalArgumentException("Un rendez vous pour signer un mandat ne fait intervenir que le vendeur");
    		}else {
    			if(!vendeur.equals(b.getVendeur())) {
    				throw new IllegalArgumentException("le vendeur doit faire partie des personnes concernné par le rendez vous de vente");
    			}
    			System.out.println("on organise un RDV pour signer le mandat");
    			this.dateRDV=d;
    			this.bien=b;
    			this.vendeur=vendeur;
    			this.typeRdv=typeRdv;
    			b.signermandat(dateFinMandat);
    		}
    		break;
    	}
    }
    
    /** Initialise un rendez vous de type visite avec les parametre suivants
     * @param d la date de la visite
     * @param b le bien concerné par la visite
     * @param typeRdv le type de rendez vous ici visite
     * @param acheteur le potentiel acheteur qui visite le bien
     * @throws IllegalArgumentException
     */
    public RDV(Date d, BienImmobilier b,String typeRdv,Personne acheteur) throws IllegalArgumentException{
    	switch(typeRdv) {
    	
    	case "Visite":
    		if(acheteur==null||b==null) {
    			throw new IllegalArgumentException("Une visite necessite un bien immobillier et un acheteur potentiels (non null)");
    		}else {
    			if(acheteur.equals(b.getVendeur())) {
    				throw new IllegalArgumentException("Un vendeur n'as aucun interet a visiter son propre bien");
    			}
    			System.out.println("on organise un RDV pour visiter");
    			this.dateRDV=d;
    			this.bien=b;
    			this.acheteur=acheteur;
    			this.typeRdv=typeRdv;
    		}
    		break;
    	}
    }

    /**getteur de la date du RDV
     * @return dateRDV
     */
    Date getDateRDV(){
     return this.dateRDV;
    }
    /**getteur du bien concerné par le RDV
     * @return bien
     */
    BienImmobilier getBien(){
        return  this.bien;
    }
    
    /** getteur de la liste de personne concerné par le RDV
     * @return lsPersonne 
     */
    List<Personne> getLsPersonne(){
        return this.lsPersonne;
    }

    /** setteur de la date du RDV
     * @param d la nouvelle date
     */
    void setDateRDV(Date d){
        this.dateRDV=d;
    }

    /**setteur du bien concerné par le RDV
     * @param b le nouveau bien
     */
    void setBien(BienImmobilier b){
        this.bien=b;
    }
    
    /** setteur de la liste de personne 
     * @param l la nouvelle liste de personne
     */
    void setLsPersonne(ArrayList<Personne> l){
        this.lsPersonne=l;
    }
    public String toString() {
    	return "RDV"+this.typeRdv;
    }
}
