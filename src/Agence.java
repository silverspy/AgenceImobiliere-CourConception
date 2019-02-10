import com.sun.org.apache.xerces.internal.impl.xpath.regex.ParseException;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

/**
 * Classe Agence immobiliere
 * une agence est constitue d une liste de RDV,d annonce,de bien a vendre ainsi que d'une liste
 * de ses acheteurs potentiel et des vendeurs
 */
public class Agence {

    private List<Annonce> listAnnonce;
    private List<RDV> listRDV;
    private List<BienImmobilier> listBienImmobillier;
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
    public Agence(ArrayList<Annonce> lA, ArrayList<RDV> lRDV, ArrayList<BienImmobilier> lBI) {
        if (lBI.size() >= 1 && lRDV.size() >= 1) {
            this.listAnnonce = lA;
            this.listBienImmobillier = lBI;
            this.listAcheteurPotentiel=new ArrayList<>();
            this.listPersonneVendeur=new ArrayList<>();
            for (BienImmobilier b : lBI) {
                this.listPersonneVendeur.add(b.getVendeur());
            }
            this.listRDV = lRDV;
            this.lastId = 0;
        } else {
            if (lRDV.size() < 1) {
                throw new IllegalArgumentException("Agence initialisee sans rendez vous");
            }
            if (lBI.size() < 1) {
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
        // TODO Auto-generated method stub
        if (b instanceof Maison) {
            checkVoeuxMaison(b);
        } else if (b instanceof Appart) {
            checkVoeuxAppart(b);
        } else {
            checkVoeuxTerrain(b);
        }

    }

    /**
     * teste parmis la liste des acheteurs potentiels si son voeux correspond au nouveau bien
     * si cela correspond un rendez vous pour une visite est pris
     *
     * @param b le bien Immobillier
     * @see Agence#prendreRDVVisite(Personne, BienImmobilier, Date)
     * @see Agence#entrerDate()
     */
    private void checkVoeuxMaison(BienImmobilier b) {
        // TODO Auto-generated method stub
        Maison m = (Maison) b;
        int borneBasse = 0;
        int borneHaute = 0;
        Voeux v = new Voeux();
        for (Personne p : this.listAcheteurPotentiel) {
            v = p.getVoeux();
            if (m.getLocalistation() == v.getLocalisation() && m.getNbEtages() == v.getNbPieces() && m.getSurfaceSol() == v.getSurfacesSol()) {
                borneBasse = v.getPrixSouhaité();
                borneBasse = borneBasse - (borneBasse / 10);
                borneHaute = v.getPrixSouhaité();
                borneHaute = borneHaute + (borneHaute / 10);
                if (m.getPrix() >= borneBasse && m.getPrix() <= borneHaute) {
                    Date d = entrerDate();
                    this.prendreRDVVisite(p, b, d);
                }
            }
        }

    }

    /**
     * methode permettant de saisir la date d'un rdv
     *
     * @return d Date
     */
    private Date entrerDate() {
        // TODO Auto-generated method stub
        Scanner s = new Scanner(System.in);
        String st = "";
        Date date = new Date();
        Date d = date;
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MMM-yyyy");
        boolean dateOk = false;
        while (dateOk == false) {
            System.out.println("Taper la date du RDV");
            st = s.nextLine();
            try {
                d = dateFormat.parse(st);
                if (d.compareTo(date) > 0) {
                    dateOk = true;
                } else {
                    System.out.println("rentrez une date superieur a la date du jour");
                }
            } catch (ParseException e) {
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
     * @see Agence#entrerDate()
     */
    private void checkVoeuxAppart(BienImmobilier b) {
        // TODO Auto-generated method stub
        Appart a = (Appart) b;
        int borneBasse = 0;
        int borneHaute = 0;
        Voeux v = new Voeux();
        for (Personne p : this.listAcheteurPotentiel) {
            v = p.getVoeux();
            if (a.getLocalistation() == v.getLocalisation() && a.getNbPieces() == v.getNbPieces()) {
                borneBasse = v.getPrixSouhaité();
                borneBasse = borneBasse - (borneBasse / 10);
                borneHaute = v.getPrixSouhaité();
                borneHaute = borneHaute + (borneHaute / 10);
                if (a.getPrix() >= borneBasse && a.getPrix() <= borneHaute) {
                    Date d = entrerDate();
                    this.prendreRDVVisite(p, b, d);
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
     * @see Agence#entrerDate()
     */
    private void checkVoeuxTerrain(BienImmobilier b) {
        // TODO Auto-generated method stub
        Terrain t = (Terrain) b;
        int borneBasse = 0;
        int borneHaute = 0;
        Voeux v = new Voeux();
        for (Personne p : this.listAcheteurPotentiel) {
            v = p.getVoeux();
            if (t.getLocalistation() == v.getLocalisation() && t.getSurfacesSol() == v.getSurfacesSol()) {
                borneBasse = v.getPrixSouhaité();
                borneBasse = borneBasse - (borneBasse / 10);
                borneHaute = v.getPrixSouhaité();
                borneHaute = borneHaute + (borneHaute / 10);
                if (t.getPrix() >= borneBasse && t.getPrix() <= borneHaute) {
                    Date d = entrerDate();
                    this.prendreRDVVisite(p, b, d);
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
     * @see Agence#prendreRDVAchat()
     */
    public void decisionAcheteur(Personne p, BienImmobilier b) {
        if (this.listAcheteurPotentiel.indexOf(p) != -1) {
            if (this.listBienImmobillier.contains(b)) {
                Personne acheteur = b.getVendeur();
                Scanner sc = new Scanner(System.in);
                String adresseNotaire = sc.nextLine();
                System.out.println("Entrer la date de vente prevue");
                Date dateVente = entrerDate();
                Double fraisDeVente = entrerFraisDeVente();
                b.signerPromesseDeVente(acheteur, b.getPrix(), adresseNotaire, dateVente, fraisDeVente);
                this.prendreRDVAchat();
            }
        }

    }

    /**
     * methode permettant de prendre un rdv Achat pour un bien immobilier et de l ajouter a la liste des biens immobiliers de l'agence
     */
    private void prendreRDVAchat() {
        // TODO Auto-generated method stub

    }

    /**
     * methode permettant d'entrer les frais de ventes pour une vente
     *
     * @return d double
     */
    private Double entrerFraisDeVente() {
        // TODO Auto-generated method stub
        Scanner s = new Scanner(System.in);
        Double d = (double) 0;
        boolean b = false;
        while (b == false) {
            String t = s.nextLine();
            try {
                d = Double.parseDouble(t);
                if (d > 0) {
                    b = true;
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
     * @param vendeur              le vendeur du bien
     * @param nbPieces             le nombre de pieces de l'appartement
     * @param etages               l etage de l'appartement
     * @param charges              les charges de l'appartement
     * @param dateFinMandat        date de fin mandat souhaité pour le terrain
     * @param dateRDV              date du rdv pour signer le mandat
     * @see Agence#prendreRDVMandat(Date, BienImmobilier, Personne, Date)
     * @see Appart
     * @see Agence#checkVoeux(BienImmobilier)
     */
    void inscriptionVenteAppartPersonneMorale(String nom, String adresse, String tel, String mail, String formJuridique, int siren, int prix, String localistation, Date dateDeVenteSouhaitee, Date dateDispo, String orientation, Personne vendeur, int nbPieces, int etages, double charges, Date dateFinMandat, Date dateRDV) {
        Personne p = new Morale(nom, adresse, tel, mail, formJuridique, siren);
        listPersonneVendeur.add(p);
        this.lastId = this.lastId + 1;
        BienImmobilier b = new Appart(this.lastId, prix, localistation, dateDeVenteSouhaitee, dateDispo, orientation, p, nbPieces, etages, charges);
        p.AjouterBienAVendre(b);
        this.prendreRDVMandat(dateRDV, b, p, dateFinMandat);
        this.listBienImmobillier.add(b);
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
     * @param dateDispo            la date a partir du quel le bien est disponible
     * @param orientation          l'orientation du bien
     * @param vendeur              le vendeur du bien
     * @param nbPieces             le nombre de piece de la maison
     * @param nbEtages             le nombre d 'étage de la maison
     * @param surfaceSol           la surface au sol de la maison
     * @param longueurFacade       la longueur de la facade de la maison
     * @param moyenDeChauffages    le moyen de chaffage de la maison
     * @param dateFinMandat        la date de fin de mandat du bien
     * @param dateRDV              date du rdv
     * @see Agence#prendreRDVMandat(Date, BienImmobilier, Personne, Date)
     * @see Maison
     * @see Agence#checkVoeux(BienImmobilier)
     */
    void inscriptionVenteMaisonPersonneMorale(String nom, String adresse, String tel, String mail, String formJuridique, int siren, int prix, String localistation, Date dateDeVenteSouhaitee, Date dateDispo, String orientation, Personne vendeur, int nbPieces, int nbEtages, double surfaceSol, double longueurFacade, String moyenDeChauffages, Date dateFinMandat, Date dateRDV) {
        Personne p = new Morale(nom, adresse, tel, mail, formJuridique, siren);
        listPersonneVendeur.add(p);
        this.lastId = this.lastId + 1;
        BienImmobilier b = new Maison(this.lastId, prix, localistation, dateDeVenteSouhaitee, dateDispo, orientation, p, nbPieces, nbEtages, surfaceSol, longueurFacade, moyenDeChauffages);
        p.AjouterBienAVendre(b);
        this.prendreRDVMandat(dateRDV, b, p, dateFinMandat);
        this.listBienImmobillier.add(b);

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
    void inscriptionVenteTerrainPersonnePhysique(String nom, String adresse, String tel, String mail, int prix, String localistation, Date dateDeVenteSouhaitee, Date dateDispo, String orientation, double surfacesSol, double longueurFacade, Date dateFinMandat, Date dateRDV) {
        Personne p = new Physique(nom, adresse, tel, mail);
        listPersonneVendeur.add(p);
        this.lastId = this.lastId + 1;
        BienImmobilier b = new Terrain(this.lastId, prix, localistation, dateDeVenteSouhaitee, dateDispo, orientation, p, surfacesSol, longueurFacade);
        p.AjouterBienAVendre(b);
        this.prendreRDVMandat(dateRDV, b, p, dateFinMandat);
        this.listBienImmobillier.add(b);
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
     * @param vendeur              le vendeur du bien
     * @param nbPieces             le nombre de pieces de l'appartement
     * @param etages               l'etage de l'appartement
     * @param charges              les charges du bien
     * @param dateRDV              la date du rdv
     * @param dateFinMandat        la date de fin de mandat du bien
     * @see Agence#prendreRDVMandat(Date, BienImmobilier, Personne, Date)
     * @see Appart
     * @see Agence#checkVoeux(BienImmobilier)
     */
    void inscriptionVenteAppartPersonne(String nom, String adresse, String tel, String mail, int prix, String localistation, Date dateDeVenteSouhaitee, Date dateDispo, String orientation, Personne vendeur, int nbPieces, int etages, double charges, Date dateFinMandat, Date dateRDV) {
        Personne p = new Physique(nom, adresse, tel, mail);
        listPersonneVendeur.add(p);
        this.lastId = this.lastId + 1;
        BienImmobilier b = new Appart(this.lastId, prix, localistation, dateDeVenteSouhaitee, dateDispo, orientation, p, nbPieces, etages, charges);
        p.AjouterBienAVendre(b);
        this.prendreRDVMandat(dateRDV, b, p, dateFinMandat);
        this.listBienImmobillier.add(b);
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
     * @param vendeur              le vendeur du bien
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
    void inscriptionVenteMaisonPersonne(String nom, String adresse, String tel, String mail, int prix, String localistation, Date dateDeVenteSouhaitee, Date dateDispo, String orientation, Personne vendeur, int nbPieces, int nbEtages, double surfaceSol, double longueurFacade, String moyenDeChauffages, Date dateFinMandat, Date dateRDV) {
        Personne p = new Physique(nom, adresse, tel, mail);
        listPersonneVendeur.add(p);
        this.lastId = this.lastId + 1;
        BienImmobilier b = new Maison(this.lastId, prix, localistation, dateDeVenteSouhaitee, dateDispo, orientation, p, nbPieces, nbEtages, surfaceSol, longueurFacade, moyenDeChauffages);
        p.AjouterBienAVendre(b);
        this.prendreRDVMandat(dateRDV, b, p, dateFinMandat);
        this.listBienImmobillier.add(b);
    }

    /**
     * methode pour prendre un rdv visite a une date d ,avec un acheteur a et un bien b
     *
     * @param p l'acheteur potentiel
     * @param b le bien concerné
     * @param d la date du RDV
     */
    void prendreRDVVisite(Personne p, BienImmobilier b, Date d) {
        this.listRDV.add(new RDV(d, b, "Visite", p));
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
     * @see Personne#ajouterVoeux(String, String, int, double)
     */
    void inscriptionPersonneInterresséTerrainPhysique(String nom, String adresse, String tel, String mail, int prix, String localistation, double surfacesSol) {
        Personne p = new Physique(nom, adresse, tel, mail);
        listAcheteurPotentiel.add(p);
        p.ajouterVoeux("terrain", localistation, prix, surfacesSol);
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
     * @see Personne#ajouterVoeux(String, String, int, double)
     */
    void inscriptionPersonneInterresséAppartPhysique(String nom, String adresse, String tel, String mail, int prix, String localistation, int nbPieces) {
        Personne p = new Physique(nom, adresse, tel, mail);
        listAcheteurPotentiel.add(p);
        p.ajouterVoeux("appart", localistation, prix, nbPieces);
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
     * @see Personne#ajouterVoeux(String, String, int, double)
     */
    void inscriptionPersonneInterresséMaisonPhysique(String nom, String adresse, String tel, String mail, int prix, String localistation, int nbPieces, double surfacesSol) {
        Personne p = new Physique(nom, adresse, tel, mail);
        listAcheteurPotentiel.add(p);
        p.ajouterVoeux("maison", localistation, prix, nbPieces);
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
     * @see Personne#ajouterVoeux(String, String, int, double)
     */
    void inscriptionPersonneInterresséTerrainMorale(String nom, String adresse, String tel, String mail, String formJuridique, int siren, int prix, String localistation, double surfacesSol) {
        Personne p = new Physique(nom, adresse, tel, mail);
        listAcheteurPotentiel.add(p);
        p.ajouterVoeux("terrain", localistation, prix, surfacesSol);
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
     * @see Personne#ajouterVoeux(String, String, int, double)
     */
    void inscriptionPersonneInterresséAppartMorale(String nom, String adresse, String tel, String mail, String formJuridique, int siren, int prix, String localistation, int nbPieces) {
        Personne p = new Physique(nom, adresse, tel, mail);
        listAcheteurPotentiel.add(p);
        p.ajouterVoeux("appart", localistation, prix, nbPieces);
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
     * @param formJuridique
     * @param siren
     * @param nbPieces
     * @see Personne
     * @see Personne#ajouterVoeux(String, String, int, double)
     */
    void inscriptionPersonneInterresséMaisonMorale(String nom, String adresse, String tel, String mail, String formJuridique, int siren, int prix, String localistation, int nbPieces, double surfacesSol) {
        Personne p = new Physique(nom, adresse, tel, mail);
        listAcheteurPotentiel.add(p);
        p.ajouterVoeux("maison", localistation, prix, nbPieces);
        Voeux v = p.getVoeux();

    }
}
