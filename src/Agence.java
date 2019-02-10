import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

import com.sun.org.apache.xerces.internal.impl.xpath.regex.ParseException;

/**
 * Classe Agence immobiliere
 * une agence est constitue d une liste de RDV,d annonce,de bien a vendre ainsi que d'une liste
 * de ses acheteurs potentiel et des vendeurs
 */
public class Agence {

    private List<Annonce> listAnnonce;
    private List<RDV> listRDV;
    private List<BienImmobilier> listBienImmobillier;
    private List<BienImmobilier> listBienVendu;
    private List<Personne> listPersonneVendeur;
    private List<Personne> listAcheteurPotentiel;
    private int lastId;
    private int lastIdVoeux;

    /**
     * Constructeur d une agence bancaire
     *
     * @param lA   liste d'annonce de l'agence
     * @param lRDV liste des rdv de l'agence
     * @param lBI  liste des bien immobilier a vendre de l'agence
     */
    public Agence(ArrayList<Annonce> lA, ArrayList<RDV> lRDV, ArrayList<BienImmobilier> lBI){
        if(lBI.size()>=1&&lRDV.size()>=1) {
        	this.listAnnonce=lA;
        	this.listBienImmobillier=lBI;
        	this.listBienVendu=new ArrayList<>();
        	this.listPersonneVendeur=new ArrayList<>();
        	this.listAcheteurPotentiel=new ArrayList<>();
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

    /**
     * getteur de la liste d'annonce
     *
     * @return listAnnonce la liste d'annonce de l'agence
     */
    List<Annonce> getAnnonces() {
        return this.listAnnonce;
    }

    /**
     * getteur de la liste de rdv
     *
     * @return listAnnonce la liste de rdv de l'agence
     */
    List<RDV> getRDV() {
        return this.listRDV;
    }

    /**
     * getteur de la liste de bien immobillier
     *
     * @return listAnnonce la liste de bien immobillier de l'agence
     */
    List<BienImmobilier> getBienImmobillier() {
        return this.listBienImmobillier;
    }

    /**
     * setteur de la liste d'annonce
     *
     * @param lA la nouvelle liste d'annonce
     */
    void setAnnonce(ArrayList<Annonce> lA) {
        this.listAnnonce = lA;
    }

    /**
     * setteur de la liste de rdv
     *
     * @param RDV la nouvelle liste d'annonce
     */
    void setRDV(ArrayList<RDV> RDV) {
        this.listRDV = RDV;
    }

    /**
     * setteur de la liste de bien immobillier
     *
     * @param lBI la nouvelle liste d'annonce
     */
    void setBienImmobilier(ArrayList<BienImmobilier> lBI) {
        this.listBienImmobillier = lBI;
    }

    /**
     * ajoute un RDV pour signer un mandat a la liste des RDV
     *
     * @param dateRDV       la date du RDV
     * @param b             le bien immobillier concerné par le mandat
     * @param p             le vendeur du bien immobillier
     * @param dateFinMandat la date de fin de mandat
     */
    void prendreRDVMandat(Date dateRDV, BienImmobilier b, Personne p, Date dateFinMandat) {
        this.listRDV.add(new RDV(dateRDV, b, p, "Mandat", dateFinMandat));

    }


    /**
     * inscrit une personne Morale pour la vente d un terrain.
     * Ajoute la personne a la liste des vendeurs le terrain dans la liste des bien et creer un rdv pour signer un mandat et l ajoute a la liste des RDV
     *
     * @param nom                  nom de la personne morale
     * @param adresse              adresse de la personne morale
     * @param tel                  telephone de la personne moral
     * @param mail                 adresse mail de la personne morale
     * @param formJuridique        forme juridique de la personne morale
     * @param siren                numero siret de la personne morale
     * @param localistation        localisation du bien a vendre
     * @param prix                 prix du bien a vendre
     * @param dateDeVenteSouhaitee date de vente du bien souhaité
     * @param dateDispo            date a partir de laquelle le bien eest disponibe
     * @param orientation          orientation du terrain
     * @param surfacesSol          surface du terrain a vendre
     * @param longueurFacade       longueure de la facade du terrain a vendre
     * @param dateFinMandat        date de fin mandat souhaité pour le terrain
     * @param dateRDV              date du rdv pour signer le mandat
     * @see Agence#prendreRDVMandat(Date, BienImmobilier, Personne, Date)
     * @see Terrain
     * @see Agence#checkVoeux(BienImmobilier)
     */
    void inscriptionVenteTerrainPersonneMorale(String nom, String adresse, String tel, String mail, String formJuridique, int siren, int prix, String localistation, Date dateDeVenteSouhaitee, Date dateDispo, String orientation, double surfacesSol, double longueurFacade, Date dateFinMandat, Date dateRDV) {
        Personne p = new Morale(nom, adresse, tel, mail, formJuridique, siren);
        listPersonneVendeur.add(p);
        this.lastId = this.lastId + 1;
        BienImmobilier b = new Terrain(this.lastId, prix, localistation, dateDeVenteSouhaitee, dateDispo, orientation, p, surfacesSol, longueurFacade);
        p.AjouterBienAVendre(b);
        this.prendreRDVMandat(dateRDV, b, p, dateFinMandat);
        this.listBienImmobillier.add(b);
        this.checkVoeux(b);
    }


    /**
     * relance la methode permettant de verifier les voeux pour un bien immobillier
     * si il s'agit d une maison on lance check voeux maison si c'est un appartement on lance CheckVoeuxAppart
     * ...
     *
     * @param b le bien Immobillier
     * @see Agence#checkVoeuxAppart(BienImmobilier)
     * @see Agence#checkVoeuxMaison(BienImmobilier)
     * @see Agence#checkVoeuxTerrain(BienImmobilier)
     */
    private void checkVoeux(BienImmobilier b) {
    	System.out.println("dans check voeux");
    	if(b instanceof Maison) {
    		System.out.println("lance check voeux maison");
    		checkVoeuxMaison(b);
    	}else if (b instanceof Appart) {
    		System.out.println("lance checkVoeux Appart");
    		checkVoeuxAppart(b);
    	}else {
    		System.out.println("lance check voeux terrain");
    		checkVoeuxTerrain(b);
    	}
		
	}
    /**
     * Menu de l'application en ligne de commande 
     */
    public void Menu() {
    	System.out.println("Bienvenue sur le menu de l'application");
    	String menu="**********************\n";
    	menu+="I- Inscription d une personne pour une vente\n";
    	menu+="A- Inscription d'une personne interresé par un achat\n";
    	menu+="P- Afficher l'etat de l'agence\n";
    	menu+="G- Modifier le voeux d'un acheteur\n";
    	menu+="Q- Quitter\n";
    	boolean fin=false;
    	while(fin==false) {
    		System.out.println(menu);
    		Scanner sc=new Scanner(System.in);
    		String str=sc.nextLine();
    		switch(str) {
    		case "I":
    			this.menuInscriptionVente();
    			break;
    		case "A":
    			this.menuAchat();
    			break;
    		case "G":
    			this.gestionAcheteur();
    			break;
    		case "P":
    			System.out.println(this.toString());
    			break;
    		case "Q":
    			fin=true;
    			System.out.print("fin de l'application");
    			break;
    		default:
    			System.out.println("error rentrez une des lettres du menu");
    		}

    	}
    }
    
    /**
     *Methode permettant de modifier le voeux d'un acheteur potentiel 
     */
    private void gestionAcheteur() {
		// TODO Auto-generated method stub
    	System.out.println("Module gestion Acheteur");
    	String st=this.enterString("nom de l'acheteur souhaitant modifier son voeux");
    	ArrayList<Personne> lsP=new ArrayList<>();
    	for(Personne p:this.listAcheteurPotentiel) {
    		if(p.getNom().equals(st)) {
    			lsP.add(p);
    		}
    	}
    	if(lsP.size()>1) {
    		String affich="Plusieurs acheteurs correspondent a votre recherche veuillez en selectionner un\n";
    		int i=1;
    		for(Personne p:lsP) {
    			affich+=i+"- "+p.toString();
    			i++;
    		}
    		Personne choosen=lsP.get(i-1);
    		boolean ok=false;
    		while(ok==false) {
    			int rep=this.enterInt("un nombre entre 1 et "+i);
    			if(rep<=i&&rep>=1) {
    				ok=true;
    			}
    		}
    		ok=false;
    		while(ok==false) {
    			System.out.println("selectionner le type de bien immobilier que vous souhaitez \n appart/maison/terrain");
    			Scanner sc=new Scanner(System.in);
    			String typeVoeux=sc.nextLine();
    			switch (typeVoeux) {
				case "appart":
					String loca=this.enterString("localisation");
					int prix=this.enterInt("prix");
					int nbPieces=this.enterInt("nombre de pieces ");
					choosen.AjoutVoeux(new Voeux("appart", loca, prix, nbPieces));
					System.out.println("le voeux de "+choosen.getNom()+"a ete mis a jour");
					ok=true;
					if(this.listBienImmobillier.size()>0) {
			    		for(BienImmobilier b:this.listBienImmobillier) {
			    			if(this.listBienVendu.indexOf(b)==-1)
			    				this.checkVoeux(b);
			    		}
			    	}
					
					break;
				case "maison":
					String local=this.enterString("localisation");
					int prix1=this.enterInt("prix");
					int nbPieces1=this.enterInt("nombre de pieces ");
					double Surface=this.entrerFraisDeVente("Surface au sol souhaité :");
					choosen.AjoutVoeux(new Voeux("maison", local, prix1, nbPieces1,Surface));
					System.out.println("le voeux de "+choosen.getNom()+"a ete mis a jour");
					ok=true;
					if(this.listBienImmobillier.size()>0) {
			    		for(BienImmobilier b:this.listBienImmobillier) {
			    			if(this.listBienVendu.indexOf(b)==-1)
			    				this.checkVoeux(b);
			    		}
			    	}
					break;
				case "terrain":
					String local1=this.enterString("localisation du bien souhaité:");
					int prix12=this.enterInt("prix souhaité :");
					double Surface2=this.entrerFraisDeVente("Surface au sol souhaité :");
					choosen.AjoutVoeux(new Voeux("terrain", local1, prix12,Surface2));
					System.out.println("le voeux de "+choosen.getNom()+"a ete mis a jour");
					ok=true;
					if(this.listBienImmobillier.size()>0) {
			    		for(BienImmobilier b:this.listBienImmobillier) {
			    			if(this.listBienVendu.indexOf(b)==-1)
			    				this.checkVoeux(b);
			    		}
			    	}
					break;
				default:
					System.out.println("selectionner le type de bien immobilier que vous souhaitez \n appart/maison/terrain");
					break;
				}
    		}
    	}else {
    		if(lsP.size()==1) {
    			Personne chosen=lsP.get(0);
    			boolean ok=false;
        		while(ok==false) {
        			System.out.println("selectionner le type de bien immobilier que vous souhaitez \n appart/maison/terrain");
        			Scanner sc=new Scanner(System.in);
        			String typeVoeux=sc.nextLine();
        			switch (typeVoeux) {
    				case "appart":
    					String loca=this.enterString("localisation");
    					int prix=this.enterInt("prix");
    					int nbPieces=this.enterInt("nombre de pieces ");
    					chosen.AjoutVoeux(new Voeux("appart", loca, prix, nbPieces));
    					System.out.println("le voeux de "+chosen.getNom()+"a ete mis a jour");
    					ok=true;
    					if(this.listBienImmobillier.size()>0) {
    			    		for(BienImmobilier b:this.listBienImmobillier) {
    			    			if(this.listBienVendu.indexOf(b)==-1)
    			    				this.checkVoeux(b);
    			    		}
    			    	}
    					
    					break;
    				case "maison":
    					String local=this.enterString("localisation");
    					int prix1=this.enterInt("prix");
    					int nbPieces1=this.enterInt("nombre de pieces ");
    					double Surface=this.entrerFraisDeVente("Surface au sol souhaité :");
    					chosen.AjoutVoeux(new Voeux("maison", local, prix1, nbPieces1,Surface));
    					System.out.println("le voeux de "+chosen.getNom()+"a ete mis a jour");
    					ok=true;
    					if(this.listBienImmobillier.size()>0) {
    			    		for(BienImmobilier b:this.listBienImmobillier) {
    			    			if(this.listBienVendu.indexOf(b)==-1)
    			    				this.checkVoeux(b);
    			    		}
    			    	}
    					break;
    				case "terrain":
    					String local1=this.enterString("localisation du bien souhaité:");
    					int prix12=this.enterInt("prix souhaité :");
    					double Surface2=this.entrerFraisDeVente("Surface au sol souhaité :");
    					chosen.AjoutVoeux(new Voeux("terrain", local1, prix12,Surface2));
    					System.out.println("le voeux de "+chosen.getNom()+"a ete mis a jour");
    					ok=true;
    					if(this.listBienImmobillier.size()>0) {
    			    		for(BienImmobilier b:this.listBienImmobillier) {
    			    			if(this.listBienVendu.indexOf(b)==-1)
    			    				this.checkVoeux(b);
    			    		}
    			    	}
    					break;
    				default:
    					System.out.println("selectionner le type de bien immobilier que vous souhaitez \n appart/maison/terrain");
    					break;
    				}
        		}
    		}else {
    			System.out.println("aucun acheteur correspondant");
    		}
    	}
		
	}

	/**
     *Menu permettant d'inscrire une personne Morale ou physique sur la liste des acheteurs potentiels 
     *@see Morale
     *@see Physique
     */
    private void menuAchat() {
		// TODO Auto-generated method stub
    	System.out.println("Bienvenue sur le menu d'Inscription a la vente");
    	String menu="**********************\n";
    	menu+="Mt- Inscription d une personne morale interresée par un terrain\n";
    	menu+="Mm- Inscription d une personne morale interressée par  une maison\n";
    	menu+="Ma- Inscription d une personne morale interressee par un Appartement\n";
    	menu+="Pt- Inscription d'une personne Physique interressee par un terrain\n";
    	menu+="Pa- Inscription d'une personne Physique interressee par un appartement\n";
    	menu+="Pm- Inscription d'une personne Physique interresse par une maison\n";
    	menu+="Q- Quitter\n";
    	
    	boolean fin=false;
    	while(fin==false) {
    		System.out.println(menu);
    		Scanner sc=new Scanner(System.in);
    		String str=sc.nextLine();
    		String nom,adresse,tel,mail,formJuridique,localisation;
    		int siren,prix,nbPieces;
    		double charge,SurfacesSol,longueurFacade;
    		switch(str) {
    		case "Mt":
    			nom=this.enterString("nom");
    			adresse=this.enterString("adresse");
    			tel=this.enterString("telephone");
    			mail=this.enterString("mail");
    			formJuridique=this.enterString("Forme Juridique");
    			siren=this.enterInt("numero siren");
    			prix=this.enterInt("prix");
    			localisation=this.enterString("localisation");
    			SurfacesSol=this.entrerFraisDeVente("Surface au sol");
    			this.inscriptionPersonneInterresséTerrainMorale(nom, adresse, tel, mail, formJuridique, siren, prix, localisation, SurfacesSol);
    			break;
    		case "Ma":
    			nom=this.enterString("nom");
    			adresse=this.enterString("adresse");
    			tel=this.enterString("telephone");
    			mail=this.enterString("mail");
    			formJuridique=this.enterString("Forme Juridique");
    			siren=this.enterInt("numero siren");
    			prix=this.enterInt("prix");
    			nbPieces=this.enterInt("Nombre de pieces");
    			localisation=this.enterString("localisation");
    			this.inscriptionPersonneInterresséAppartMorale(nom, adresse, tel, mail, formJuridique, siren, prix, localisation, nbPieces);
    			break;
    		case "Pt":
    			nom=this.enterString("nom");
    			adresse=this.enterString("adresse");
    			tel=this.enterString("telephone");
    			mail=this.enterString("email");
    			prix=this.enterInt("prix du bien");
    			localisation=this.enterString("localisation");
    			SurfacesSol=this.entrerFraisDeVente("Surface au sol");
    			this.inscriptionPersonneInterresséTerrainPhysique(nom, adresse, tel, mail, prix, localisation, SurfacesSol);
    			break;
    		case "Mm":
    			nom=this.enterString("nom");
    			adresse=this.enterString("adresse");
    			tel=this.enterString("telephone");
    			mail=this.enterString("mail");
    			formJuridique=this.enterString("Forme Juridique");
    			siren=this.enterInt("numero siren");
    			prix=this.enterInt("prix");
    			nbPieces=this.enterInt("Nombre de pieces");
    			localisation=this.enterString("localisation");
    			SurfacesSol=this.entrerFraisDeVente("Surface de sol");
    			this.inscriptionPersonneInterresséMaisonMorale(nom, adresse, tel, mail, formJuridique, siren, prix, localisation, nbPieces, SurfacesSol);
    			break;
    		case "Pa":
    			nom=this.enterString("nom");
    			adresse=this.enterString("adresse");
    			tel=this.enterString("telephone");
    			mail=this.enterString("adresse email");
    			prix=this.enterInt("prix du bien");
    			localisation=this.enterString("localisation du bien");
    			nbPieces=this.enterInt("nombre de pieces");
    			this.inscriptionPersonneInterresséAppartPhysique(nom, adresse, tel, mail, prix, localisation, nbPieces);
    			break;
    		case "Pm":
    			nom=this.enterString("nom");
    			adresse=this.enterString("adresse");
    			tel=this.enterString("telephone");
    			mail=this.enterString("adresse email");
    			prix=this.enterInt("prix du bien");
    			localisation=this.enterString("localisation du bien");
    			nbPieces=this.enterInt("nombre de pieces");
    			SurfacesSol=this.entrerFraisDeVente("Surface au sol");
    			this.inscriptionPersonneInterresséMaisonPhysique(nom, adresse, tel, mail, prix, localisation, nbPieces, SurfacesSol);
    			break;
    		case "Q":
    			fin=true;
    			break;
    		default:
    			System.out.println("error rentrez une des lettres du menu");
    		}
    		//System.out.println(this.toString());
    	}
    }

	/**
	 *Menu d'inscription a la vente.Permet d'inscrire une personne morale ou physique a la vente
	 *@see Morale
	 *@see Physique
	 */
	private void menuInscriptionVente() {
		// TODO Auto-generated method stub
    	System.out.println("Bienvenue sur le menu d'Inscription a la vente");
    	String menu="**********************\n";
    	menu+="Mt- Inscription d une personne morale pour un terrain\n";
    	menu+="Mm- Inscription d une personne morale pour une maison\n";
    	menu+="Ma- Inscription d une personne morale pour un Appartement\n";
    	menu+="Pt- Inscription d'une personne Physique pour un terrain\n";
    	menu+="Pa- Inscription d'une personne Physique pour un appartement\n";
    	menu+="Pm- Inscription d'une personne Physique pour une maison\n";
    	menu+="Q- Quitter\n";
    	boolean fin=false;
    	while(fin==false) {
    		System.out.println(menu);
    		Scanner sc=new Scanner(System.in);
    		String str=sc.nextLine();
    		switch(str) {
    		case "Pa":
    			String nom=this.enterString("nom");
    			String adresse=this.enterString("adresse");
    			String mail=this.enterString("mail");
    			String tel=this.enterString("telephone");
    			String localisation=this.enterString("localisation du bien");
    			String orientation=this.enterString("orientation");
    			Date dateDispo=this.entrerDate("Disponibilité ");
    			Date dateDeVenteSouhaitee=this.entrerDate("Date de vente SOuhaitee");
    			int prix=this.enterInt("prix");
    			int nbPieces=this.enterInt("nbPieces");
    			int etages=this.enterInt("Etage de l'appartement");
    			double charges=this.entrerFraisDeVente("carges");
    			Date dateFinMandat=this.entrerDate("Fin de Mandat");
    			Date dateRDV=this.entrerDate("RDV");
    			this.inscriptionVenteAppartPersonne(nom, adresse, tel, mail, prix,localisation, dateDeVenteSouhaitee, dateDispo, orientation, nbPieces, etages, charges, dateFinMandat, dateRDV);
    			//this.inscriptionPersonneInterresséAppartPhysique(nom, adresse, tel, mail, prix, localistation, nbPieces);
    			break;
    		case "Ma":
    			String nom1=this.enterString("nom");
    			String adresse1=this.enterString("adresse");
    			String mail1=this.enterString("mail");
    			String tel1=this.enterString("telephone");
    			String localisation1=this.enterString("localisation du bien");
    			String orientation1=this.enterString("orientation");
    			Date dateDispo1=this.entrerDate("Disponiblité");
    			Date dateDeVenteSouhaitee1=this.entrerDate("Date de vente souhaité");
    			int prix1=this.enterInt("prix");
    			int nbPieces1=this.enterInt("nbPieces");
    			int etages1=this.enterInt("Etage de l'appartement");
    			double charges1=this.entrerFraisDeVente("charges");
    			Date dateFinMandat1=this.entrerDate("Date de fin de mandat");
    			Date dateRDV1=this.entrerDate("Date de RDV");
    			String formJuridique=this.enterString("Forme Juridique");
    			int siren=this.enterInt("numero Siret");
    			this.inscriptionVenteAppartPersonneMorale(nom1, adresse1, tel1, mail1, formJuridique, siren, prix1, localisation1, dateDeVenteSouhaitee1, dateDispo1, orientation1, nbPieces1, etages1, charges1, dateFinMandat1, dateRDV1);
    			break;
    		case "Pt":
    			String nom2=this.enterString("nom");
    			String adresse2=this.enterString("adresse");
    			String mail2=this.enterString("mail");
    			String tel2=this.enterString("telephone");
    			String localisation2=this.enterString("localisation du bien");
    			String orientation2=this.enterString("orientation");
    			Date dateDispo2=this.entrerDate("Date de disponibilité");
    			Date dateDeVenteSouhaitee2=this.entrerDate("Date de vente souhaité");
    			int prix2=this.enterInt("prix");
    			double SurfaceSol2=this.entrerFraisDeVente("surface au sol");
    			double longueurFacade2=this.entrerFraisDeVente("longueur de la facade");
    			Date dateFinMandat2=this.entrerDate("DATE De Fin de mandat");
    			Date dateRDV2=this.entrerDate("Date de RDV");
    			this.inscriptionVenteTerrainPersonnePhysique(nom2, adresse2, tel2, mail2, prix2, localisation2, dateDeVenteSouhaitee2, dateDispo2, orientation2,SurfaceSol2, longueurFacade2, dateFinMandat2, dateRDV2);
    			break;
    		case "Mt":
    			String nom3=this.enterString("nom");
    			String adresse3=this.enterString("adresse");
    			String mail3=this.enterString("mail");
    			String tel3=this.enterString("telephone");
    			String localisation3=this.enterString("localisation du bien");
    			String orientation3=this.enterString("orientation");
    			Date dateDispo3=this.entrerDate("Disponibilité");
    			int siren2=this.enterInt("numero siren");
    			String formJuridique2=this.enterString("Forme Juridique");
    			Date dateDeVenteSouhaitee3=this.entrerDate("Date de vente");
    			int prix3=this.enterInt("prix");
    			double SurfaceSol3=this.entrerFraisDeVente("surface au sol");
    			double longueurFacade3=this.entrerFraisDeVente("longueur de la facade");
    			Date dateFinMandat3=this.entrerDate("Fin de mandat");
    			Date dateRDV3=this.entrerDate("de RDV");
    			this.inscriptionVenteTerrainPersonneMorale(nom3, adresse3, tel3, mail3, formJuridique2, siren2, prix3, localisation3, dateDeVenteSouhaitee3, dateDispo3, orientation3, SurfaceSol3, longueurFacade3, dateFinMandat3, dateRDV3);
    			break;
    		case "Pm":
    			String nom4=this.enterString("nom");
    			String adresse4=this.enterString("adresse");
    			String mail4=this.enterString("mail");
    			String tel4=this.enterString("telephone");
    			String localisation4=this.enterString("localisation du bien");
    			String orientation4=this.enterString("orientation");
    			Date dateDispo4=this.entrerDate("Disponibilité");
    			Date dateDeVenteSouhaitee4=this.entrerDate("Vente souhaité");
    			int prix4=this.enterInt("prix");
    			int nbPieces4=this.enterInt("nbPieces");
    			int nbEtages4=this.enterInt("Etage de l'appartement");
    			double SurfacesSol4=this.entrerFraisDeVente("surface au sol");
    			double longueurFacade4=this.entrerFraisDeVente("longueur de la facade)");
    			String moyenChauffage=this.enterString("moyen de chauffage");
    			Date dateFinMandat4=this.entrerDate("Fin de Mandat");
    			Date dateRDV4=this.entrerDate("De RDV");
    			this.inscriptionVenteMaisonPersonne(nom4, adresse4, tel4, mail4, prix4, localisation4, dateDeVenteSouhaitee4, dateDispo4, orientation4, nbPieces4, nbEtages4, SurfacesSol4, longueurFacade4, moyenChauffage, dateFinMandat4, dateRDV4);
    			break;
    		case "Mm":
    			String nom5=this.enterString("nom");
    			String adresse5=this.enterString("adresse");
    			String mail5=this.enterString("mail");
    			String tel5=this.enterString("telephone");
    			String localisation5=this.enterString("localisation du bien");
    			String orientation5=this.enterString("orientation");
    			String formJuridique5=this.enterString("Forme Juridique");
    			int siren5=this.enterInt("numero siren");
    			Date dateDispo5=this.entrerDate("Disponibilité");
    			Date dateDeVenteSouhaitee5=this.entrerDate(" de Vente Souhaité");
    			int prix5=this.enterInt("prix");
    			int nbPieces5=this.enterInt("nbPieces");
    			int nbEtages5=this.enterInt("Etage de l'appartement");
    			double SurfacesSol5=this.entrerFraisDeVente("Surface au sol");
    			double longueurFacade5=this.entrerFraisDeVente("longueur de la facade");
    			String moyenChauffage5=this.enterString("moyen de chauffage");
    			Date dateFinMandat5=this.entrerDate("Fin de mandat");
    			Date dateRDV5=this.entrerDate("RDV");
    			this.inscriptionVenteMaisonPersonneMorale(nom5, adresse5, tel5, mail5, formJuridique5, siren5, prix5, localisation5, dateDeVenteSouhaitee5, dateDispo5, orientation5, nbPieces5, nbEtages5, SurfacesSol5, longueurFacade5, moyenChauffage5, dateFinMandat5, dateRDV5);
    			break;
    		case "Q":
    			fin=true;
    			break;
    		default:
    			System.out.println("error rentrez une des lettres du menu");
    		}
    		//System.out.println(this.toString());
    	}
	}
	
	/**
	 * Fonction permettant de recuperer la saisie par l utilisateur d'un int superieur a 0 correspondant au string passe en parametre
	 * @param string la categorie
	 * @return k l'int saisie par l'utilisateur
	 */
	private int enterInt(String string) {
		// TODO Auto-generated method stub
		Scanner s=new Scanner(System.in);
		int k=-1;
		boolean b=false;
		while(b==false) {
			System.out.println("Entrer "+string);
			k=s.nextInt();
			if(k>0) {
				b=true;
			}
		}
		return k;
	}

	/**
     * teste parmis la liste des acheteurs potentiels si son voeux correspond au nouveau bien
     * si cela correspond un rendez vous pour une visite est pris
     *
     * @param b le bien Immobillier
     * @see Agence#prendreRDVVisite(Personne, BienImmobilier, Date)
     * @see Agence#entrerDate(String)
     */
	private void checkVoeuxMaison(BienImmobilier b) {
		// TODO Auto-generated method stub
    	Maison m=(Maison) b;
    	int borneBasse=0;
    	int borneHaute=0;
    	Voeux v=new Voeux();
    	if(this.listAcheteurPotentiel.size()>0) {
        	for(Personne p:this.listAcheteurPotentiel) {
        		if(p.getBienSoumis().indexOf(b)==-1) {
            		v=p.getVoeux();
            		if(m.getLocalistation()==v.getLocalisation()&&m.getNbPieces()==v.getNbPieces()&&m.getSurfaceSol()==v.getSurfacesSol()) {
            			borneBasse=v.getPrixSouhaité();
            			borneBasse=borneBasse-(borneBasse/10);
            			borneHaute=v.getPrixSouhaité();
            			borneHaute=borneHaute+(borneHaute/10);
            			if(m.getPrix()>=borneBasse&&m.getPrix()<=borneHaute) {
            				Date d=entrerDate("Date du RDV");
            				p.getBienSoumis().add(b);
            				System.out.println("on lance une visite");
            				this.prendreRDVVisite(p, b, d);
            			}
            		}
        		}
        	}
    	}
	}
	 /**
	  * methode permettant de recuperer la saisie d'une date correspondante au string passé en parametre au format jj/mm/aaaa superieure a la date du jour par l'utilisateur
	  * @return d Date la date saisie
     */
    private Date entrerDate(String sd) {
		// TODO Auto-generated method stub
    	Scanner s=new Scanner(System.in);
    	String st="";
    	Date date=new Date();
    	Date d=date;
    	SimpleDateFormat dateFormat=new SimpleDateFormat("dd/MM/yyyy");
    	dateFormat.setLenient(false);
    	boolean dateOk=false;
    	while(dateOk==false) {
    		System.out.println("Entrer la date de "+sd+" au format dd/MM/yyyy");
    		st=s.nextLine();
    		try {
    			d=dateFormat.parse(st);
    			if(d.compareTo(date)>0) {
    				dateOk=true;
    			}else {
    				System.out.println("rentrez une date superieur a la date du jour");
    			}
    		}catch(ParseException e) {
    			e.printStackTrace();
    		} catch (java.text.ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    	}
		return d;
	}
    /**
     * teste parmis la liste des acheteurs potentiels si son voeux correspond au nouveau bien
     * si cela correspond un rendez vous pour une visite est pris
     *
     * @param b le bien Immobillier
     * @see Agence#prendreRDVVisite(Personne, BienImmobilier, Date)
     * @see Agence#entrerDate(String)
     */
	private void checkVoeuxAppart(BienImmobilier b) {
		// TODO Auto-generated method stub
		Appart a=(Appart) b;
    	int borneBasse=0;
    	int borneHaute=0;
    	Voeux v=new Voeux();
    	if(this.listAcheteurPotentiel.size()>0) {
        	for(Personne p:this.listAcheteurPotentiel) {
        		if(p.getBienSoumis().indexOf(b)==-1) {
            		v=p.getVoeux();
            		if(a.getLocalistation().equals(v.getLocalisation())&&a.getNbPieces()==v.getNbPieces()) {
            			borneBasse=v.getPrixSouhaité();
            			borneBasse=borneBasse-(borneBasse/10);
            			borneHaute=v.getPrixSouhaité();
            			borneHaute=borneHaute+(borneHaute/10);
            			if(a.getPrix()>=borneBasse&&a.getPrix()<=borneHaute) {
            				System.out.println("on programme une visite");
            				Date d=entrerDate("Date RDV Visite");
            				p.getBienSoumis().add(b);
            				this.prendreRDVVisite(p, b, d);
            			}
            		}
        		}
        	}
    	}
	}
	 /**
     * teste parmis la liste des acheteurs potentiels si son voeux correspond au nouveau bien
     * si cela correspond un rendez vous pour une visite est pris
     *
     * @param b le bien Immobillier
     * @see Agence#prendreRDVVisite(Personne, BienImmobilier, Date)
     * @see Agence#entrerDate(String)
     */
    private void checkVoeuxTerrain(BienImmobilier b) {
		// TODO Auto-generated method stub
    	Terrain t=(Terrain) b;
    	int borneBasse=0;
    	int borneHaute=0;
    	Voeux v=new Voeux();
    	if(this.listAcheteurPotentiel.size()>0) {
        	for(Personne p:this.listAcheteurPotentiel) {
        		if(p.getBienSoumis().indexOf(t)==-1){
        			v=p.getVoeux();
            		if(t.getLocalistation().equals(v.getLocalisation())&&t.getSurfacesSol()==v.getSurfacesSol()) {
            			borneBasse=v.getPrixSouhaité();
            			borneBasse=borneBasse-(borneBasse/10);
            			borneHaute=v.getPrixSouhaité();
            			borneHaute=borneHaute+(borneHaute/10);
            			if(t.getPrix()>=borneBasse&&t.getPrix()<=borneHaute) {
            				System.out.println("on programme une visite");
            				Date d=entrerDate("Date RDV Visite");
            				p.getBienSoumis().add(b);
            				this.prendreRDVVisite(p, b, d);
            			}
            		}	
        		}
        	}
    	}
	}
    /**
     * methode permettant a l acheteur de prendre un rdv achat et de signer une promesse de vente
     *
     * @param p l'acheteur
     * @param b le bien immobillier concerné
     * @see BienImmobilier#signerPromesseDeVente(Personne, double, String, Date, Double)
     * @see Agence#prendreRDVAchat(Date, BienImmobilier, Personne, Personne, String)
     */
    public void decisionAcheteur(Personne p,BienImmobilier b) {
    	if(this.listAcheteurPotentiel.indexOf(p)!=-1) {
    		if(this.listBienImmobillier.contains(b)) {
    			Personne acheteur=b.getVendeur();
    			Scanner sc=new Scanner(System.in);
    			String adresseNotaire=sc.nextLine();
    			System.out.println("Entrer la date de vente prevue");
    			Date dateVente=entrerDate("Date de Vente");
    			Double fraisDeVente=entrerFraisDeVente("Frais de ventes");
    			b.signerPromesseDeVente(acheteur, b.getPrix(), adresseNotaire, dateVente, fraisDeVente);
    			this.prendreRDVAchat(dateVente,b,p,b.getVendeur(),"Vente");
    		}
    	}
    	
    }

    /**
     * methode permettant de prendre un rdv Achat pour un bien immobilier et de l ajouter a la liste des biens immobiliers de l'agence
     * @param string 
     * @param personne 
     * @param p 
     * @param b 
     * @param dateVente 
     */
	private void prendreRDVAchat(Date dateVente, BienImmobilier b, Personne p, Personne personne, String string) {
		// TODO Auto-generated method stub
		this.listRDV.add(new RDV(dateVente,b,p,personne,string));
		System.out.println("la vente a été conclue o/n");
		String rep=this.getConclusionVente();
		if(rep.equals("o")) {
			this.conclureVente(b,p);
		}else {
			this.checkVoeux(b);
			System.out.print("vente annulé");
		}
	}
	
	 /**Fonction permettant de recuperer la conclusion ou l'annulation de la vente
	 * @return rep la conclusion de la vente
	 */
	private String getConclusionVente() {
		// TODO Auto-generated method stub
		 Scanner s=new Scanner(System.in);
		 String rep=s.nextLine();
		 boolean k=false;
		 while(k==false) {
			 if(rep.equals("o")||rep.equals("n")) {
				 k=true;
			 }else {
				 s=new Scanner(System.in);
				 System.out.println("error vous devez valider o ou annuler n la vente");
				 rep=s.nextLine();
			 }
		 }
		return rep;
	}

	/**
	 * Fonction concluant la vente.Elle change le proprietaire du bien immobilier et retire le bien immobilier de la liste des bien a vendre
	 * @param b
	 * @param p
	 */
	private void conclureVente(BienImmobilier b, Personne p) {
		// TODO Auto-generated method stub
		b.vendeur=p;
		this.listBienVendu.add(b);
		//this.listBienImmobillier.remove(b);
	}

	/**
	  * methode permettant pour un string passé en parametre de recuperer la saisie de l'utilisateur pour cette valeur et de la retourner au format double
	  * @return d double la valeur saisie par l'utilisateur
     */
	private Double entrerFraisDeVente(String f) {
		// TODO Auto-generated method stub
		Scanner s=new Scanner(System.in);
		Double d=(double) 0;
		boolean b=false;
		while(b==false) {
			System.out.println("Entrer le double "+f);
			String t=s.nextLine();
			try {
				d=Double.parseDouble(t);
				if(d>0) {
					b=true;
				}
			} catch (Exception e) {
				e.printStackTrace();
				// TODO: handle exception
			}
			
		}
		return d;
	}

    /**
     * inscrit une personne Morale pour la vente d un appartement.
     * Ajoute la personne a la liste des vendeurs le terrain dans la liste des bien et creer un rdv pour signer un mandat et l ajoute a la liste des RDV
     *
     * @param nom                  nom de la personne morale
     * @param adresse              adresse de la personne morale
     * @param tel                  telephone de la personne moral
     * @param mail                 adresse mail de la personne morale
     * @param formJuridique        forme juridique de la personne morale
     * @param siren                numero siret de la personne morale
     * @param localistation        localisation du bien a vendre
     * @param prix                 prix du bien a vendre
     * @param dateDeVenteSouhaitee date de vente du bien souhaité
     * @param dateDispo            date a partir de laquelle le bien eest disponibe
     * @param orientation          l'orientation du bien
     * @param nbPieces             le nombre de pieces de l'appartement
     * @param etages               l etage de l'appartement
     * @param charges              les charges de l'appartement
     * @param dateFinMandat        date de fin mandat souhaité pour le terrain
     * @param dateRDV              date du rdv pour signer le mandat
     * @see Agence#prendreRDVMandat(Date, BienImmobilier, Personne, Date)
     * @see Appart
     * @see Agence#checkVoeux(BienImmobilier)
     */
	void inscriptionVenteAppartPersonneMorale(String nom,String adresse,String tel,String mail,String formJuridique, int siren, int prix, String localistation, Date dateDeVenteSouhaitee, Date dateDispo, String orientation, int nbPieces, int etages, double charges,Date dateFinMandat,Date dateRDV){
		Personne p=new Morale(nom,adresse,tel,mail,formJuridique,siren);
    	listPersonneVendeur.add(p);
    	this.lastId=this.lastId+1;
    	BienImmobilier b=new Appart(this.lastId,prix, localistation,dateDeVenteSouhaitee,dateDispo,orientation,p,nbPieces,etages,charges);
    	p.AjouterBienAVendre(b);
    	this.prendreRDVMandat(dateRDV,b,p,dateFinMandat);
    	this.listBienImmobillier.add(b);
    	this.checkVoeux(b);
    }

    /**
     * inscrit une personne Morale pour la vente d un terrain.
     * Ajoute la personne a la liste des vendeurs le terrain dans la liste des bien et creer un rdv pour signer un mandat et l ajoute a la liste des RDV
     *
     * @param nom                  nom de la personne morale
     * @param adresse              adresse de la personne morale
     * @param tel                  telephone de la personne moral
     * @param mail                 adresse mail de la personne morale
     * @param formJuridique        forme juridique de la personne morale
     * @param siren                numero siret de la personne morale
     * @param prix                 prix du bien
     * @param localistation        localisation du bien
     * @param dateDeVenteSouhaitee la date de vente souhaité du bien
     * @param dateDispo la date a partir du quel le bien est disponible
     * @param orientation l'orientation du bien
     * @param nbPieces le nombre de piece de la maison
     * @param nbEtages le nombre d 'étage de la maison
     * @param surfaceSol la surface au sol de la maison
     * @param longueurFacade la longueur de la facade de la maison
     * @param moyenDeChauffages le moyen de chaffage de la maison
     * @param dateFinMandat la date de fin de mandat du bien
     * @param dateRDV date du rdv
     * @see Agence#prendreRDVMandat(Date, BienImmobilier, Personne, Date)
     * @see Maison
     * @see Agence#checkVoeux(BienImmobilier)
     */
    void inscriptionVenteMaisonPersonneMorale(String nom,String adresse,String tel,String mail,String formJuridique, int siren, int prix, String localistation, Date dateDeVenteSouhaitee, Date dateDispo, String orientation, int nbPieces, int nbEtages, double surfaceSol, double longueurFacade, String moyenDeChauffages,Date dateFinMandat,Date dateRDV){
    	Personne p=new Morale(nom,adresse,tel,mail,formJuridique,siren);
    	listPersonneVendeur.add(p);
    	this.lastId=this.lastId+1;
    	BienImmobilier b=new Maison(this.lastId,prix, localistation,dateDeVenteSouhaitee,dateDispo,orientation,p,nbPieces,nbEtages,surfaceSol,longueurFacade,moyenDeChauffages);
    	p.AjouterBienAVendre(b);
    	this.listBienImmobillier.add(b);
    	this.prendreRDVMandat(dateRDV,b,p,dateFinMandat);
    	this.checkVoeux(b);
    }

    /**
     * inscrit une personne Morale pour la vente d un terrain.
     * Ajoute la personne a la liste des vendeurs le terrain dans la liste des bien et creer un rdv pour signer un mandat et l ajoute a la liste des RDV
     *
     * @param nom                  nom de la personne morale
     * @param adresse              adresse de la personne morale
     * @param tel                  telephone de la personne moral
     * @param mail                 adresse mail de la personne morale
     * @param prix                 prix du bien
     * @param localistation        localisation du bien
     * @param dateDeVenteSouhaitee la date de vente souhaité du bien
     * @param dateDispo            la date a partir du quel le bien est disponible
     * @param orientation          l'orientation du bien
     * @param longueurFacade       la longueur de la facade de la maison
     * @param surfacesSol          la surface au sol de la maison
     * @param dateFinMandat        la date de fin de mandat du bien
     * @param dateRDV
     * @see Agence#prendreRDVMandat(Date, BienImmobilier, Personne, Date)
     * @see Maison
     * @see Agence#checkVoeux(BienImmobilier)
     */
    void inscriptionVenteTerrainPersonnePhysique(String nom,String adresse,String tel,String mail,int prix, String localistation, Date dateDeVenteSouhaitee, Date dateDispo, String orientation,  double surfacesSol, double longueurFacade,Date dateFinMandat,Date dateRDV){
    	Personne p=new Physique(nom,adresse,tel,mail);
    	listPersonneVendeur.add(p);
    	this.lastId=this.lastId+1;
    	BienImmobilier b=new Terrain(this.lastId,prix,localistation,dateDeVenteSouhaitee,dateDispo,orientation,p,surfacesSol,longueurFacade);
    	p.AjouterBienAVendre(b);
    	this.listBienImmobillier.add(b);
    	this.prendreRDVMandat(dateRDV,b,p,dateFinMandat);
    	this.checkVoeux(b);
    }

    /**
     * inscrit une personne Morale pour la vente d un terrain.
     * Ajoute la personne a la liste des vendeurs le terrain dans la liste des bien et creer un rdv pour signer un mandat et l ajoute a la liste des RDV
     *
     * @param nom                  nom de la personne morale
     * @param adresse              adresse de la personne morale
     * @param tel                  telephone de la personne moral
     * @param mail                 adresse mail de la personne morale
     * @param prix                 prix du bien
     * @param localistation        localisation du bien
     * @param dateDeVenteSouhaitee la date de vente souhaité du bien
     * @param dateDispo la date a partir du quel le bien est disponible
     * @param orientation l'orientation du bien
     * @param nbPieces le nombre de pieces de l'appartement
     * @param etages l'etage de l'appartement
     * @param charges les charges du bien
     * @param dateRDV la date du rdv
     * @param dateFinMandat la date de fin de mandat du bien
     * @see Agence#prendreRDVMandat(Date, BienImmobilier, Personne, Date)
     * @see Appart
     * @see Agence#checkVoeux(BienImmobilier)
     */
    void inscriptionVenteAppartPersonne(String nom,String adresse,String tel,String mail, int prix, String localistation, Date dateDeVenteSouhaitee, Date dateDispo, String orientation, int nbPieces, int etages, double charges,Date dateFinMandat,Date dateRDV){
    	Personne p=new Physique(nom,adresse,tel,mail);
    	listPersonneVendeur.add(p);
    	this.lastId=this.lastId+1;
    	BienImmobilier b=new Appart(this.lastId,prix, localistation,dateDeVenteSouhaitee,dateDispo,orientation,p,nbPieces,etages,charges);
    	p.AjouterBienAVendre(b);
    	this.listBienImmobillier.add(b);
    	this.prendreRDVMandat(dateRDV,b,p,dateFinMandat);
    	this.checkVoeux(b);
    }

    /**
     * inscrit une personne Morale pour la vente d un terrain.
     * Ajoute la personne a la liste des vendeurs le terrain dans la liste des bien et creer un rdv pour signer un mandat et l ajoute a la liste des RDV
     *
     * @param nom                  nom de la personne morale
     * @param adresse              adresse de la personne morale
     * @param tel                  telephone de la personne moral
     * @param mail                 adresse mail de la personne morale
     * @param prix                 prix du bien
     * @param localistation        localisation du bien
     * @param dateDeVenteSouhaitee la date de vente souhaité du bien
     * @param dateDispo            la date a partir du quel le bien est disponible
     * @param orientation          l'orientation du bien
     * @param nbPieces             le nombre de piece du bien
     * @param nbEtages             le nombre d'etage de la maison
     * @param surfaceSol           la surface au sol de la maison
     * @param longueurFacade       la longueure de la facade du bien
     * @param moyenDeChauffages    le moyen de chauffage du bien
     * @param dateRDV              la date du rdv
     * @param dateFinMandat        la date de fin de mandat du bien
     * @see Agence#prendreRDVMandat(Date, BienImmobilier, Personne, Date)
     * @see Maison
     * @see Agence#checkVoeux(BienImmobilier)
     */
    void inscriptionVenteMaisonPersonne(String nom,String adresse,String tel,String mail, int prix, String localistation, Date dateDeVenteSouhaitee, Date dateDispo, String orientation, int nbPieces, int nbEtages, double surfaceSol, double longueurFacade, String moyenDeChauffages,Date dateFinMandat,Date dateRDV){
    	Personne p=new Physique(nom,adresse,tel,mail);
    	listPersonneVendeur.add(p);
    	this.lastId=this.lastId+1;
    	BienImmobilier b=new Maison(this.lastId,prix, localistation,dateDeVenteSouhaitee,dateDispo,orientation,p,nbPieces,nbEtages,surfaceSol,longueurFacade,moyenDeChauffages);
    	p.AjouterBienAVendre(b);
    	this.listBienImmobillier.add(b);
    	this.prendreRDVMandat(dateRDV,b,p,dateFinMandat);
    	this.checkVoeux(b);
    }

    /**
     * methode pour prendre un rdv visite a une date d ,avec un acheteur a et un bien b elle gere aussi la suite du rendez vous si la visite aboutit a une promesse de vente elle appelle prendreRDVAchat
     * @param p l'acheteur potentiel
     * @param b le bien concerné
     * @param d la date du RDV
     * @see Agence#prendreRDVAchat(Date, BienImmobilier, Personne, Personne, String)
     */
    void prendreRDVVisite(Personne p,BienImmobilier b,Date d){
    	this.listRDV.add(new RDV(d,b,"Visite",p));
    	boolean finVisite=false;
    	while(finVisite==false) {
    		System.out.println("la visite aboutit elle sur une prmoesse de vente ? o/n");
    		Scanner s=new Scanner(System.in);
    		String st=s.nextLine();
    		switch(st) {
    		case "o":
    			Date dateVente=this.entrerDate("Date de vente");
    			finVisite=true;
    			this.prendreRDVAchat(dateVente, b, p, b.getVendeur(), "Vente");
    			break;
    		case "n":
    			finVisite=true;
    			break;
    		default:
    			System.out.println("reponder par o ou par n");
    		}
    	}
    }

    /**
     * methode pour inscrire une personne interresé par un terrain
     *
     * @param nom
     * @param adresse
     * @param tel
     * @param mail
     * @param prix
     * @param localistation
     * @param surfacesSol
     * @see Personne
     * @see Personne#AjoutVoeux(Voeux)
     */
    void inscriptionPersonneInterresséTerrainPhysique(String nom,String adresse,String tel,String mail,int prix, String localistation, double surfacesSol) {
    	Personne p=new Physique(nom,adresse,tel,mail);
    	listAcheteurPotentiel.add(p);  
    	p.AjoutVoeux(new Voeux("terrain",localistation,prix,surfacesSol));
    	if(this.listBienImmobillier.size()>0) {
    		for(BienImmobilier b:this.listBienImmobillier) {
    			if(this.listBienVendu.indexOf(b)==-1)
    				this.checkVoeux(b);
    		}
    	}
    }

    /**
     * methode pour inscrire une personne interresee par un appart
     *
     * @param nom
     * @param adresse
     * @param tel
     * @param mail
     * @param prix
     * @param localistation
     * @param nbPieces
     * @see Personne
     * @see Personne#AjoutVoeux(Voeux)
     */
    void inscriptionPersonneInterresséAppartPhysique(String nom,String adresse,String tel,String mail,int prix, String localistation, int nbPieces) {
    	Personne p=new Physique(nom,adresse,tel,mail);
    	listAcheteurPotentiel.add(p);  
    	p.AjoutVoeux(new Voeux("appart",localistation, prix, nbPieces));
    	if(this.listBienImmobillier.size()>0) {
    		for(BienImmobilier b:this.listBienImmobillier) {
    			if(this.listBienVendu.indexOf(b)==-1)
    				this.checkVoeux(b);
    		}
    	}
    }

    /**
     * methode pour inscrire une personne interresé par un terrain
     *
     * @param nom
     * @param adresse
     * @param tel
     * @param mail
     * @param prix
     * @param localistation
     * @param nbPieces
     * @param surfacesSol
     * @see Personne
     * @see Personne#AjoutVoeux(Voeux)
     */
    void inscriptionPersonneInterresséMaisonPhysique(String nom,String adresse,String tel,String mail,int prix, String localistation, int nbPieces,double surfacesSol) {
    	Personne p=new Physique(nom,adresse,tel,mail);
    	listAcheteurPotentiel.add(p);  
    	p.AjoutVoeux(new Voeux("maison",localistation,prix,nbPieces));
    	if(this.listBienImmobillier.size()>0) {
    		for(BienImmobilier b:this.listBienImmobillier) {
    			if(this.listBienVendu.indexOf(b)==-1)
    				this.checkVoeux(b);
    		}
    	}
    }

    /**
     * methode pour inscrire une personne interresé par un terrain
     *
     * @param nom
     * @param adresse
     * @param tel
     * @param mail
     * @param prix
     * @param localistation
     * @param surfacesSol
     * @see Personne
     * @see Personne#AjoutVoeux(Voeux)
     */
    void inscriptionPersonneInterresséTerrainMorale(String nom,String adresse,String tel,String mail,String formJuridique, int siren,int prix, String localistation, double surfacesSol) {
    	Personne p=new Physique(nom,adresse,tel,mail);
    	listAcheteurPotentiel.add(p);  
    	p.AjoutVoeux(new Voeux("terrain",localistation,prix,surfacesSol));
    	if(this.listBienImmobillier.size()>0) {
    		for(BienImmobilier b:this.listBienImmobillier) {
    			if(this.listBienVendu.indexOf(b)==-1)
    				this.checkVoeux(b);
    		}
    	}
    }

    /**
     * methode pour inscrire une personne interresee par un appart
     *
     * @param nom
     * @param adresse
     * @param tel
     * @param mail
     * @param prix
     * @param localistation
     * @param nbPieces
     * @see Personne
     * @see Personne#AjoutVoeux(Voeux)
     */
    void inscriptionPersonneInterresséAppartMorale(String nom,String adresse,String tel,String mail,String formJuridique, int siren,int prix, String localistation, int nbPieces) {
    	Personne p=new Physique(nom,adresse,tel,mail);
    	listAcheteurPotentiel.add(p);  
    	Voeux v=new Voeux("appart",localistation,prix,nbPieces);
    	p.AjoutVoeux(v);
    	if(this.listBienImmobillier.size()>0) {
    		for(BienImmobilier b:this.listBienImmobillier) {
    			if(this.listBienVendu.indexOf(b)==-1)
    				this.checkVoeux(b);
    		}
    	}
    }

    /**
     * methode permettant de recuperer la saisie d'un string non null par l'utilisateur pour la categorie nom passé en parametre
     * @param nom la categorie
     * @return re la valeur de la categorie nom
     */
    private String enterString(String nom) {
    	Scanner s=new Scanner(System.in);
    	boolean fin=false;
    	String re="";
    	while(fin==false) {
    		System.out.println("Entrer "+nom);
    		re=s.nextLine();
    		if(!re.equals(null)&&!re.equals("")) {
    			fin=true;
    		}
    	}
    	return re;
    }
    /**    
 * methode pour inscrire une personne interresé par un terrain
     * @param nom
     * @param adresse
     * @param tel
     * @param mail
     * @param prix
     * @param localistation
     * @param surfacesSol
     * @param formJuridique
     * @param siren
     * @param nbPieces
     * @see Personne
     * @see Personne#AjoutVoeux(Voeux)
     */
    void inscriptionPersonneInterresséMaisonMorale(String nom,String adresse,String tel,String mail,String formJuridique, int siren,int prix, String localistation, int nbPieces,double surfacesSol) {
    	Personne p=new Physique(nom,adresse,tel,mail);
    	listAcheteurPotentiel.add(p);  
    	p.AjoutVoeux(new Voeux("maison",localistation,prix,nbPieces));
    	if(this.listBienImmobillier.size()>0) {
    		for(BienImmobilier b:this.listBienImmobillier) {
    			this.checkVoeux(b);
    		}
    	}
    	
    }
    public static void main(String [] args) {
        try {
    	ArrayList<RDV> lRDV = new ArrayList<>();
        ArrayList<Annonce> lAnnonce = new ArrayList<>();
        ArrayList<BienImmobilier> lBI = new ArrayList<>();
        DateFormat df = new SimpleDateFormat("dd/MM/yy");
        Date dv = df.parse("10/01/2020");
        Date dd = df.parse("10/01/2020");
        Personne p = new Physique("pierre antoine","bordeaux","0644559988","tt@tt.com");
        BienImmobilier terrain = new Terrain(1, 50000, "Toulouse", dv, dd, "Ouest",p,500,500);
        lBI.add(terrain);
        Date drdv = df.parse("09/01/2019");
        Date dfm = df.parse("09/01/2019");
        lRDV.add(new RDV(drdv, terrain, p, "Mandat",dfm));
        lAnnonce.add(new Annonce());
        Agence a = new Agence(lAnnonce, lRDV, lBI);
    	a.Menu();
        }catch(Exception e){
        	System.out.print(e.getMessage());
        }
    }
    
    @Override
    public String toString() {
    	String s=" Agence \n";
    	s+="liste des bien immobiliers :\n";
    	if(this.listBienImmobillier.size()>0) {
    		for(BienImmobilier b :this.listBienImmobillier) {
    			s+=b.toString()+"\n";
    		}
    	}
    	s+="liste des vendeurs \n";
    	if(this.listPersonneVendeur.size()>0) {
    		for(Personne vendeur:this.listPersonneVendeur) {
    			s+=vendeur.toString()+"\n";
    		}
    	}
    	s+="liste des acheteurs \n";
    	if(this.listAcheteurPotentiel.size()>0) {
    		for(Personne vendeur:this.listAcheteurPotentiel) {
    			s+=vendeur.toString()+"\n";
    		}
    	}
    	s+="liste des annonces \n";
    	if(this.listAnnonce.size()>0) {
    		for(Annonce a:this.listAnnonce) {
    			s+=a.toString()+"\n";
    		}
    	}
    	s+="liste des rendez vous\n";
    	if(this.listRDV.size()>0) {
    		for(RDV rd:this.listRDV) {
    			s+=rd.toString()+"\n";
    		}
    	}
    	s+="///////////////////////////////////////////\n";
		return s;
    	
    }
}
