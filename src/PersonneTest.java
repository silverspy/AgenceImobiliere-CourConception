import org.junit.Before;
import org.junit.Test;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.junit.Assert.*;

public class PersonneTest {

    Personne physique;
    Personne morale;

    @Before
    public void setUp() throws Exception {
        physique = new Physique("Celia lopm","Paris","0544669988","tt@tt.com");
        morale = new Morale("Celia lopm","Paris","0544669988","tt@tt.com","sarl",15635);
    }

    @Test
    public void ajouterVoeux() {
        physique.ajouterVoeux("terrain","toulouse",1500,200);
        morale.ajouterVoeux("terrain","toulouse",1500,200);
        assertTrue("physique and morale must have Voeux",physique.getVoeux() != null && morale.getVoeux() != null );
    }

    @Test
    public void ajouterBienAVendre() {
        try{
            DateFormat df = new SimpleDateFormat("dd/MM/yy");
            Date dv = df.parse("10/01/2020");
            Date dd = df.parse("10/06/2019");
            Personne p = new Physique("pierre jean","bordeaux","0644559988","tt@tt.com");
            BienImmobilier maison = new Maison(2, 10000, "Toulouse", dv, dd, "nord", p, 6, 2, 300, 150,"bois");
            physique.AjouterBienAVendre(maison);
            morale.AjouterBienAVendre(maison);
            assertTrue("Bien a vendre must contain 2 items",physique.getBienAVendre().size() == 1 && morale.getBienAVendre().size() == 1);
        } catch (Exception e){
            fail(e.toString());
        }
    }

    @Test
    public void ajouterBienAAcheter() {
        try{
            DateFormat df = new SimpleDateFormat("dd/MM/yy");
            Date dv = df.parse("10/01/2020");
            Date dd = df.parse("10/06/2019");
            Personne p = new Physique("pierre jean","bordeaux","0644559988","tt@tt.com");
            BienImmobilier maison = new Maison(2, 10000, "Toulouse", dv, dd, "nord", p, 6, 2, 300, 150,"bois");
            physique.AjouterBienAAcheter(maison);
            morale.AjouterBienAAcheter(maison);
            assertTrue("Bien a vendre must contain 2 items",physique.getBienAAcheter().size() == 1 && morale.getBienAAcheter().size() == 1);
        } catch (Exception e){
            fail(e.toString());
        }
    }
}