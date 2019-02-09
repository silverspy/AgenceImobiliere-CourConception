import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.junit.Assert.*;

public class MaisonTest {

    @org.junit.Test
    public void getNbPieces() {
        DateFormat df = new SimpleDateFormat("dd/MM/yy");
        Personne pp1 = new Physique("antoine dupuis", "2 avenue de la rue","0644568978","test@test.com");

        try {
            Date dv = new Date().setTime(df.parse("10/01/2020"));
            Date dd = new Date().setTime(df.parse("10/06/2019"));
            BienImmobilier appart = new Appart(1,10000,"Toulouse", dv, dd, "nord",pp1,6,2,50);
        } catch (Exception e){
            assertTrue(e.toString(),false);
        }
    }

    @org.junit.Test
    public void getNbEtages() {
    }

    @org.junit.Test
    public void getSurfaceSol() {
    }

    @org.junit.Test
    public void getLongueurFacade() {
    }

    @org.junit.Test
    public void getMoyenDeChauffages() {
    }
}