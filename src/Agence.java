import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Agence {

    private List<Annonce> listAnnonce;
    private List<RDV> listRDV;
    private List<BienImmobilier> listBienImmobillier;
    private List<Personne> listPersonneVendeur;
    private List<Personne> listAcheteurPotentiel;
    private int lastId;
    private int lastIdVoeux;
    public Agence(ArrayList<Annonce> lA, ArrayList<RDV> lRDV, ArrayList<BienImmobilier> lBI){
        if(lBI.size()>=1&&lRDV.size()>=1) {
    	this.listAnnonce=lA;
        this.listBienImmobillier=lBI;
        for(BienImmobilier b:lBI) {
        	this.listPersonneVendeur.add(b.getVendeur());
        }
        this.listRDV=lRDV;
        this.lastId=0;
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


    void prendreRDVMandat(Date dateRDV, BienImmobilier b, Personne p, Date dateFinMandat){
        this.listRDV.add(new RDV(dateRDV,b, p,"Mandat",dateFinMandat));

    }

    void inscriptionVenteTerrainPersonneMorale(String nom,String adresse,String tel,String mail,String formJuridique, int siren,int prix, String localistation, Date dateDeVenteSouhaitee, Date dateDispo, String orientation,  double surfacesSol, double longueurFacade,Date dateFinMandat,Date dateRDV ){
    	Personne p=new Morale(nom,adresse,tel,mail,formJuridique,siren);
    	listPersonneVendeur.add(p);
    	this.lastId=this.lastId+1;
    	BienImmobilier b=new Terrain(this.lastId,prix,localistation,dateDeVenteSouhaitee,dateDispo,orientation,p,surfacesSol,longueurFacade);
    	p.AjouterBienAVendre(b);
    	this.prendreRDVMandat(dateRDV,b,p,dateFinMandat);
    }
    
    void inscriptionVenteAppartPersonneMorale(String nom,String adresse,String tel,String mail,String formJuridique, int siren, int prix, String localistation, Date dateDeVenteSouhaitee, Date dateDispo, String orientation, Personne vendeur, int nbPieces, int etages, double charges,Date dateFinMandat,Date dateRDV){
    	Personne p=new Morale(nom,adresse,tel,mail,formJuridique,siren);
    	listPersonneVendeur.add(p);
    	this.lastId=this.lastId+1;
    	BienImmobilier b=new Appart(this.lastId,prix, localistation,dateDeVenteSouhaitee,dateDispo,orientation,p,nbPieces,etages,charges);
    	p.AjouterBienAVendre(b);
    	this.prendreRDVMandat(dateRDV,b,p,dateFinMandat);
    }
    
    void inscriptionVenteMaisonPersonneMorale(String nom,String adresse,String tel,String mail,String formJuridique, int siren, int prix, String localistation, Date dateDeVenteSouhaitee, Date dateDispo, String orientation, Personne vendeur, int nbPieces, int nbEtages, double surfaceSol, double longueurFacade, String moyenDeChauffages,Date dateFinMandat,Date dateRDV){
    	Personne p=new Morale(nom,adresse,tel,mail,formJuridique,siren);
    	listPersonneVendeur.add(p);
    	this.lastId=this.lastId+1;
    	BienImmobilier b=new Maison(this.lastId,prix, localistation,dateDeVenteSouhaitee,dateDispo,orientation,p,nbPieces,nbEtages,surfaceSol,longueurFacade,moyenDeChauffages);
    	p.AjouterBienAVendre(b);
    	this.prendreRDVMandat(dateRDV,b,p,dateFinMandat);
    }
    
    void inscriptionVenteTerrainPersonnePhysique(String nom,String adresse,String tel,String mail,int prix, String localistation, Date dateDeVenteSouhaitee, Date dateDispo, String orientation,  double surfacesSol, double longueurFacade,Date dateFinMandat,Date dateRDV){
    	Personne p=new Physique(nom,adresse,tel,mail);
    	listPersonneVendeur.add(p);
    	this.lastId=this.lastId+1;
    	BienImmobilier b=new Terrain(this.lastId,prix,localistation,dateDeVenteSouhaitee,dateDispo,orientation,p,surfacesSol,longueurFacade);
    	p.AjouterBienAVendre(b);
    	this.prendreRDVMandat(dateRDV,b,p,dateFinMandat);
    }
    void inscriptionVenteAppartPersonne(String nom,String adresse,String tel,String mail, int prix, String localistation, Date dateDeVenteSouhaitee, Date dateDispo, String orientation, Personne vendeur, int nbPieces, int etages, double charges,Date dateFinMandat,Date dateRDV){
    	Personne p=new Physique(nom,adresse,tel,mail);
    	listPersonneVendeur.add(p);
    	this.lastId=this.lastId+1;
    	BienImmobilier b=new Appart(this.lastId,prix, localistation,dateDeVenteSouhaitee,dateDispo,orientation,p,nbPieces,etages,charges);
    	p.AjouterBienAVendre(b);
    	this.prendreRDVMandat(dateRDV,b,p,dateFinMandat);
    }
    void inscriptionVenteMaisonPersonne(String nom,String adresse,String tel,String mail, int prix, String localistation, Date dateDeVenteSouhaitee, Date dateDispo, String orientation, Personne vendeur, int nbPieces, int nbEtages, double surfaceSol, double longueurFacade, String moyenDeChauffages,Date dateFinMandat,Date dateRDV){
    	Personne p=new Physique(nom,adresse,tel,mail);
    	listPersonneVendeur.add(p);
    	this.lastId=this.lastId+1;
    	BienImmobilier b=new Maison(this.lastId,prix, localistation,dateDeVenteSouhaitee,dateDispo,orientation,p,nbPieces,nbEtages,surfaceSol,longueurFacade,moyenDeChauffages);
    	p.AjouterBienAVendre(b);
    	this.prendreRDVMandat(dateRDV,b,p,dateFinMandat);
    }
    
    void inscriptionPersonneInterresséTerrainPhysique(String nom,String adresse,String tel,String mail,int prix, String localistation, double surfacesSol) {
    	Personne p=new Physique(nom,adresse,tel,mail);
    	listAcheteurPotentiel.add(p);  
    	p.AjouterVoeuxTerrain("terrain",localistation,prix,surfacesSol);
    }
    void inscriptionPersonneInterresséAppartPhysique(String nom,String adresse,String tel,String mail,int prix, String localistation, int nbPieces) {
    	Personne p=new Physique(nom,adresse,tel,mail);
    	listAcheteurPotentiel.add(p);  
    	p.AjouterVoeuxTerrain("appart",localistation,prix,nbPieces);
    }
    void inscriptionPersonneInterresséMaisonPhysique(String nom,String adresse,String tel,String mail,int prix, String localistation, int nbPieces,double surfacesSol) {
    	Personne p=new Physique(nom,adresse,tel,mail);
    	listAcheteurPotentiel.add(p);  
    	p.AjouterVoeuxTerrain("maison",localistation,prix,nbPieces,surfacesSol);
    }
    
    void inscriptionPersonneInterresséTerrainMorale(String nom,String adresse,String tel,String mail,String formJuridique, int siren,int prix, String localistation, double surfacesSol) {
    	Personne p=new Physique(nom,adresse,tel,mail);
    	listAcheteurPotentiel.add(p);  
    	p.AjouterVoeuxTerrain("terrain",localistation,prix,surfacesSol);
    }
    void inscriptionPersonneInterresséAppartMorale(String nom,String adresse,String tel,String mail,String formJuridique, int siren,int prix, String localistation, int nbPieces) {
    	Personne p=new Physique(nom,adresse,tel,mail);
    	listAcheteurPotentiel.add(p);  
    	p.AjouterVoeuxTerrain("appart",localistation,prix,nbPieces);
    }
    void inscriptionPersonneInterresséMaisonMorale(String nom,String adresse,String tel,String mail,String formJuridique, int siren,int prix, String localistation, int nbPieces,double surfacesSol) {
    	Personne p=new Physique(nom,adresse,tel,mail);
    	listAcheteurPotentiel.add(p);  
    	p.AjouterVoeuxTerrain("maison",localistation,prix,nbPieces,surfacesSol);
    }
    void prendreRDVVente(){

    }
    void prendreRDVVisite(){

    }

}
