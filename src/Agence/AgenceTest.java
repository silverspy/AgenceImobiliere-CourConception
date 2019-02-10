package Agence;

import Annonce.Annonce;
import Annonce.Media;
import BienImmobilier.BienImmobilier;
import BienImmobilier.Maison;
import BienImmobilier.Terrain;
import Personne.Personne;
import Personne.Physique;
import org.junit.Before;
import org.junit.Test;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

public class AgenceTest {

    Agence a;


    @Before
    public void setUp() throws Exception {
        try {
            ArrayList<RDV> lRDV = new ArrayList<>();
            ArrayList<Annonce> lAnnonce = new ArrayList<>();
            ArrayList<BienImmobilier> lBI = new ArrayList<>();
            DateFormat df = new SimpleDateFormat("dd/MM/yy");
            Date dv = df.parse("10/01/2020");
            Date dd = df.parse("10/01/2020");
            Personne p = new Physique("pierre antoine", "bordeaux", "0644559988", "tt@tt.com");
            BienImmobilier terrain = new Terrain(1, 50000, "Toulouse", dv, dd, "Ouest", p, 500, 500);
            lBI.add(terrain);
            Date drdv = df.parse("09/01/2019");
            Date dfm = df.parse("09/01/2019");
            lRDV.add(new RDV(drdv, terrain, p, "Agence.Mandat", dfm));
            lAnnonce.add(new Annonce());
            a = new Agence(lAnnonce, lRDV, lBI);
        } catch (Exception e) {
            fail(e.toString());
        }
    }

    @Test
    public void setAnnonce() {
        ArrayList<Annonce> ann = new ArrayList<>();
        ann.add(new Annonce(Media.Presse));
        a.setAnnonce(ann);
    }

    @Test
    public void setRDV() {
    }

    @Test
    public void setBienImmobilier() {
    }

    @Test
    public void prendreRDVMandat() {
        try {
            DateFormat df = new SimpleDateFormat("dd/MM/yy");
            Date dv = df.parse("10/01/2020");
            Date dg = df.parse("10/01/2020");
            Date dd = df.parse("10/06/2019");
            dd.toString();
            Personne p = new Physique("pierre jean", "bordeaux", "0644559988", "tt@tt.com");
            BienImmobilier maison = new Maison(2, 10000, "Toulouse", dv, dd, "nord", p, 6, 2, 300, 150, "bois");
            a.prendreRDVMandat(dv, maison, maison.vendeur, dg);
            assertTrue("Erreur listRDV devrait avoir 2 elements", a.getRDV().size() == 2);
        } catch (Exception e) {
            fail(e.toString());
        }
    }

    @Test
    public void inscriptionVenteTerrainPersonneMorale() {
        try {
            DateFormat df = new SimpleDateFormat("dd/MM/yy");
            Date dd = df.parse("10/02/2019");
            Date dv = df.parse("10/01/2020");
            Date dm = df.parse("11/01/2019");
            Date drdv = df.parse("10/02/2019");
            a.inscriptionVenteTerrainPersonneMorale("jean pascal", "paris", "0644163256", "tt@tt.com", "sociétée", 12345, 2000, "bordeaux", dd, dv, "Ouest", 200, 50, dm, drdv);
            assertTrue("BienImmobilier.BienImmobilier must have 2 items", a.getBienImmobillier().size() == 2);
        } catch (Exception e) {
            fail(e.toString());
        }

    }

    @Test
    public void decisionAcheteur() { //Not implemented yet
        try {
            DateFormat df = new SimpleDateFormat("dd/MM/yy");
            Date dg = df.parse("10/01/2020");
            Date dd = df.parse("10/06/2019");
            Personne p = new Physique("remi xavier", "Lyon", "0644589654", "tt@tt.com");
            BienImmobilier maison = new Maison(2, 10000, "Toulouse", dg, dd, "nord", p, 6, 2, 300, 150, "bois");
            a.decisionAcheteur(p, maison);
            assertTrue("Agence.RDV must contains 2 items but contain " + a.getRDV().size(), a.getRDV().size() == 2);
        } catch (Exception e) {
            fail(e.toString());
        }
    }

    @Test
    public void inscriptionVenteAppartPersonneMorale() {
        try {
            DateFormat df = new SimpleDateFormat("dd/MM/yy");
            Date dv = df.parse("10/01/2020");
            Date dd = df.parse("10/06/2019");
            Date dfm = df.parse("10/06/2019");
            Date drdv = df.parse("10/06/2019");
            a.inscriptionVenteTerrainPersonneMorale("Bastien didier", "Toulouse", "0655669988", "bastien@didier.com", "sarl", 0654455, 1000, "toulouse", dv, dd, "ouest", 1000, 500, dfm, drdv);
            assertTrue("agence must have 2 BienImmobilier.BienImmobilier & 2 rdv ", a.getBienImmobillier().size() == 2 && a.getRDV().size() == 2);
        } catch (Exception e) {
            fail(e.toString());
        }
    }

    @Test
    public void inscriptionVenteMaisonPersonneMorale() {
        try {
            DateFormat df = new SimpleDateFormat("dd/MM/yy");
            Date dv = df.parse("10/01/2020");
            Date dd = df.parse("10/06/2019");
            Date dfm = df.parse("10/06/2019");
            Date drdv = df.parse("10/06/2019");
            a.inscriptionVenteTerrainPersonneMorale("Bastien didier", "Toulouse", "0655669988", "bastien@didier.com", "sarl", 0654455, 1000, "toulouse", dv, dd, "ouest", 1000, 500, dfm, drdv);
            assertTrue("agence must have 2 BienImmobilier.BienImmobilier & 2 rdv ", a.getBienImmobillier().size() == 2 && a.getRDV().size() == 2);
        } catch (Exception e) {
            fail(e.toString());
        }
    }

    @Test
    public void inscriptionVenteTerrainPersonnePhysique() {
        try {
            DateFormat df = new SimpleDateFormat("dd/MM/yy");
            Date dv = df.parse("10/01/2020");
            Date dd = df.parse("10/06/2019");
            Date dfm = df.parse("10/06/2019");
            Date drdv = df.parse("10/06/2019");
            a.inscriptionVenteTerrainPersonneMorale("Bastien didier", "Toulouse", "0655669988", "bastien@didier.com", "sarl", 0654455, 1000, "toulouse", dv, dd, "ouest", 1000, 500, dfm, drdv);
            assertTrue("agence must have 2 BienImmobilier.BienImmobilier & 2 rdv ", a.getBienImmobillier().size() == 2 && a.getRDV().size() == 2);
        } catch (Exception e) {
            fail(e.toString());
        }
    }

    @Test
    public void inscriptionVenteAppartPersonne() {
        try {
            DateFormat df = new SimpleDateFormat("dd/MM/yy");
            Date dv = df.parse("10/01/2020");
            Date dd = df.parse("10/06/2019");
            Date dfm = df.parse("10/06/2019");
            Date drdv = df.parse("10/06/2019");
            a.inscriptionVenteTerrainPersonneMorale("Bastien didier", "Toulouse", "0655669988", "bastien@didier.com", "sarl", 0654455, 1000, "toulouse", dv, dd, "ouest", 1000, 500, dfm, drdv);
            assertTrue("agence must have 2 BienImmobilier.BienImmobilier & 2 rdv ", a.getBienImmobillier().size() == 2 && a.getRDV().size() == 2);
        } catch (Exception e) {
            fail(e.toString());
        }
    }

    @Test
    public void inscriptionVenteMaisonPersonne() {
        try {
            DateFormat df = new SimpleDateFormat("dd/MM/yy");
            Date dv = df.parse("10/01/2020");
            Date dd = df.parse("10/06/2019");
            Date dfm = df.parse("10/06/2019");
            Date drdv = df.parse("10/06/2019");
            a.inscriptionVenteTerrainPersonneMorale("Bastien didier", "Toulouse", "0655669988", "bastien@didier.com", "sarl", 0654455, 1000, "toulouse", dv, dd, "ouest", 1000, 500, dfm, drdv);
            assertTrue("agence must have 2 BienImmobilier.BienImmobilier & 2 rdv ", a.getBienImmobillier().size() == 2 && a.getRDV().size() == 2);
        } catch (Exception e) {
            fail(e.toString());
        }
    }

    @Test
    public void prendreRDVVisite() {
        try {
            Personne p = new Physique("jean francois", "Montauban", "0566998877", "test@test.com");
            DateFormat df = new SimpleDateFormat("dd/MM/yy");
            Date dg = df.parse("10/01/2020");
            Date dd = df.parse("10/06/2019");
            Date dc = df.parse("10/06/2019");
            BienImmobilier maison = new Maison(2, 10000, "Toulouse", dg, dd, "nord", p, 6, 2, 300, 150, "bois");
            a.prendreRDVVisite(p, maison, dc);
            assertTrue("Agence.Agence must have 2 Agence.RDV", a.getRDV().size() == 2);
        } catch (Exception e) {
            fail(e.toString());
        }
    }

    @Test
    public void inscriptionPersonneInterresséTerrainPhysique() {
        a.inscriptionPersonneInterresséTerrainPhysique("paul jacques", "toulouse", "0589665533", "tt@tt.com", 25000, "bordeaux", 250);
    }

    @Test
    public void inscriptionPersonneInterresséAppartPhysique() {
        a.inscriptionPersonneInterresséTerrainPhysique("paul jacques", "toulouse", "0589665533", "tt@tt.com", 25000, "bordeaux", 250);
    }

    @Test
    public void inscriptionPersonneInterresséMaisonPhysique() {
        a.inscriptionPersonneInterresséTerrainPhysique("paul jacques", "toulouse", "0589665533", "tt@tt.com", 25000, "bordeaux", 250);
    }

    @Test
    public void inscriptionPersonneInterresséTerrainMorale() {
        a.inscriptionPersonneInterresséTerrainPhysique("paul jacques", "toulouse", "0589665533", "tt@tt.com", 25000, "bordeaux", 250);
    }

    @Test
    public void inscriptionPersonneInterresséAppartMorale() {
        a.inscriptionPersonneInterresséTerrainPhysique("paul jacques", "toulouse", "0589665533", "tt@tt.com", 25000, "bordeaux", 250);
    }

    @Test
    public void inscriptionPersonneInterresséMaisonMorale() {
        a.inscriptionPersonneInterresséTerrainPhysique("paul jacques", "toulouse", "0589665533", "tt@tt.com", 25000, "bordeaux", 250);
    }
}