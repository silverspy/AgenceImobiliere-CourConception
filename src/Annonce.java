import java.util.ArrayList;
import java.util.List;

import org.omg.CORBA.PUBLIC_MEMBER;


public class Annonce {
private  Media media;

private List<Description> ldescription;

/**
 * Constructeur d'une annonce
 */
public Annonce() {
	this.media=null;
	this.ldescription=null;
}

/** Constructeur d'une annonce dans un media m
 * @param m le type de media
 */
public Annonce(Media m) {

}
public String toString() {
	return "Annonce";
}
}

