import org.junit.Test;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.junit.Assert.*;

public class BienImmobilierTest {

    BienImmobilier appart;
    BienImmobilier maison;
    BienImmobilier terrain;

    @org.junit.Before
    public void setUp() {
        DateFormat df = new SimpleDateFormat("dd/MM/yy");
        Personne pp1 = new Physique("antoine dupuis", "2 avenue de la rue", "0644568978", "test@test.com");

        pp1.getVoeux();

        try {
            Date dv = df.parse("10/01/2020");
            Date dd = df.parse("10/06/2019");
            dd.toString();
            appart = new Appart(1, 10000, "Toulouse", dv, dd, "nord", pp1, 6, 2, 50);
            maison = new Maison(2, 10000, "Toulouse", dv, dd, "nord", pp1, 6, 2, 300, 150,"bois");
            terrain = new Terrain(3, 10000, "Toulouse", dv, dd, "nord", pp1, 300, 150);


        } catch (Exception e) {
            fail(e.toString());
        }
    }

    @Test
    public void getPrix() {
        assertTrue("getPrix must return 10000", appart.getPrix() == 10000);
        assertTrue("getPrix must return 10000", terrain.getPrix() == 10000);
        assertTrue("getPrix must return 10000", maison.getPrix() == 10000);
    }

    @Test
    public void getLocalistation() {
        assertTrue("getLocalisation",appart.getLocalistation() == "Toulouse");
        assertTrue("getLocalisation",terrain.getLocalistation() == "Toulouse");
        assertTrue("getLocalisation",maison.getLocalistation() == "Toulouse");
    }

    @Test
    public void getIdBien() {
        assertTrue("getidBien",appart.getIdBien() == 1);
        assertTrue("getidBien",maison.getIdBien() == 2);
        assertTrue("getidBien",terrain.getIdBien() == 3);

    }

    //Test Maison
    @org.junit.Test
    public void getNbPieces() {
        assertTrue("getNBpieces must return 6", ((Appart) appart).getNbPieces() == 6);
    }

    //Appart & maison
    @Test
    public void getNbEtages() {
        assertTrue("getNbEtages must return 2", ((Appart) appart).getNbEtages() == 2);
        assertTrue("getNbEtages must return 2", ((Maison) maison).getNbEtages() == 2);
    }

    //Maison
    @Test
    public void getSurfaceSol() {
        assertTrue("getNbEtages must return 2", ((Maison) maison).getSurfaceSol() == 300);
    }

    //Maison et terrain
    @Test
    public void getLongueurFacade() {
        assertTrue("getLongeur face must return 300 but return " + (((Maison) maison).getLongueurFacade()) ,((Maison) maison).getLongueurFacade() == 150);
        assertTrue("getLongeur face must return 300 but return " + (((Terrain) terrain).getLongueurFacade()) ,((Terrain) terrain).getLongueurFacade() == 150);
    }

    //Maison
    @Test
    public void getMoyenDeChauffages() {
        assertTrue("getMoyenDeChauffages must return Bois", ((Maison) maison).getMoyenDeChauffages() == "bois");
    }






    @Test
    public void inscrire() {
    }

    @Test
    public void signermandat() {
        try{
            DateFormat df = new SimpleDateFormat("dd/MM/yy");
            Date dm = df.parse("10/01/2020");
            maison.signermandat(dm);
            Mandat mdt = maison.getMandat();
            assertTrue("error mandat", mdt.dateFinMendat.equals(dm) && mdt.prix == maison.getPrix());
        } catch (Exception e){
            fail(e.toString());
        }
    }

    @Test
    public void signerPromesseDeVente() {
        Personne p2 = new Physique("mathieu roux", "toulouse", "0644161299", "toto@titi.com");
        try {
            DateFormat df = new SimpleDateFormat("dd/MM/yy");
            Date dv = df.parse("10/01/2020");
            maison.signerPromesseDeVente(p2, 10000, "bordeaux", dv , 500.5);
            assertTrue("Error creating promesse vente", maison.getPromesseVente().dateVente == dv && maison.getPromesseVente().adresseNotaire == "bordeaux");
        } catch (Exception e){
            fail(e.toString());
        }
    }

    @Test
    public void getVendeur() {
    }
}