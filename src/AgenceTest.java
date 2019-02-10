import org.junit.Before;
import org.junit.Test;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import static org.junit.Assert.*;

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
            Personne p = new Physique("pierre antoine","bordeaux","0644559988","tt@tt.com");
            BienImmobilier terrain = new Terrain(1, 50000, "Toulouse", dv, dd, "Ouest",p,500,500);
            lBI.add(terrain);
            Date drdv = df.parse("09/01/2019");
            Date dfm = df.parse("09/01/2019");
            lRDV.add(new RDV(drdv, terrain, p, "Mandat",dfm));
            lAnnonce.add(new Annonce());
            a = new Agence(lAnnonce, lRDV, lBI);
        } catch (Exception e){
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
            Personne p = new Physique("pierre jean","bordeaux","0644559988","tt@tt.com");
            BienImmobilier maison = new Maison(2, 10000, "Toulouse", dv, dd, "nord", p, 6, 2, 300, 150,"bois");
            a.prendreRDVMandat(dv,maison,maison.vendeur,dg);
            assertTrue("Erreur listRDV devrait avoir 2 elements",a.getRDV().size() == 2);
        } catch (Exception e){
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
            a.inscriptionVenteTerrainPersonneMorale("jean pascal","paris","0644163256","tt@tt.com","sociétée",12345,2000,"bordeaux",dd,dv,"Ouest",200,50,dm,drdv);
            assertTrue("BienImmobilier must have 2 items",a.getBienImmobillier().size() == 2);
        } catch (Exception e){
            fail(e.toString());
        }

    }

    @Test
    public void decisionAcheteur() {
    }

    @Test
    public void inscriptionVenteAppartPersonneMorale() {
    }

    @Test
    public void inscriptionVenteMaisonPersonneMorale() {
    }

    @Test
    public void inscriptionVenteTerrainPersonnePhysique() {
    }

    @Test
    public void inscriptionVenteAppartPersonne() {
    }

    @Test
    public void inscriptionVenteMaisonPersonne() {
    }

    @Test
    public void prendreRDVVisite() {
    }

    @Test
    public void inscriptionPersonneInterresséTerrainPhysique() {
    }

    @Test
    public void inscriptionPersonneInterresséAppartPhysique() {
    }

    @Test
    public void inscriptionPersonneInterresséMaisonPhysique() {
    }

    @Test
    public void inscriptionPersonneInterresséTerrainMorale() {
    }

    @Test
    public void inscriptionPersonneInterresséAppartMorale() {
    }

    @Test
    public void inscriptionPersonneInterresséMaisonMorale() {
    }
}