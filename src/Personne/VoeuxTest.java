package Personne;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.fail;

public class VoeuxTest {

    @Before
    public void setUp() throws Exception {
    }

    @Test
    public void testContructor() {
        Voeux vmaison = new Voeux("maison", "paris", 10000, 3, 50);
        Voeux vterrain = new Voeux("terrain", "toulouse", 1500, 100.0);
        Voeux vappart = new Voeux("appart", "paris", 1000, 2);
    }

    @Test
    public void testMauvaistypedebien() {
        try {
            Voeux vappart = new Voeux("teeztze", "paris", 1000, 2);
            fail("creation of a Personne.Voeux with wrong type must throw exception");
        } catch (Exception e) {
        }
    }
}