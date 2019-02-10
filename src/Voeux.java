/**
 *classe voeux un voeux correspond au bien souhaité par une personne
 *un voeux dispose comme attribut du type de bien de sa localisation
 *du prix souhaité du nombre de pieces ainsi que de la surface au sol
 */
public class Voeux {

    private String typeDeBien, localisation;
    private int prixSouhaité, nbPieces;
    private double surfacesSol;

    /*
        Constructeur pour un terrain
     */
    /**Initialise un voeux avec les attributs suivants
     * @param typeDeBien le type du bien
     * @param localisation la localisation du bien
     * @param prixSouhaité le prix souhaité
     * @param surfacesSol la surface au sol souhaité
     * @throws IllegalArgumentException
     */
    public Voeux(String typeDeBien, String localisation, int prixSouhaité, double surfacesSol) throws IllegalArgumentException {
        if (typeDeBien == "terrain"){
            this.typeDeBien = typeDeBien;
            this.setLocalisation(localisation);
            this.setPrixSouhaité(prixSouhaité);
            this.setSurfacesSol(surfacesSol);
        } else {
            throw new IllegalArgumentException("Type de bien non valide");
        }
    }

    /*
        Constructeur Maison
     */

    /**
     * initie un voeux avec les attributs suivants
     * @param typeDeBien le type de bien
     * @param localisation sa localisation
     * @param prixSouhaité son prix souhaité
     * @param nbPieces le nombre de piece souhaité
     * @param surfacesSol la surface au sol souhaité
     * @throws IllegalArgumentException
     */
    public Voeux(String typeDeBien, String localisation, int prixSouhaité, int nbPieces, double surfacesSol) throws IllegalArgumentException {
        if (typeDeBien == "maison") {
            this.typeDeBien = typeDeBien;
            this.setLocalisation(localisation);
            this.setPrixSouhaité(prixSouhaité);
            this.setNbPieces(nbPieces);
            this.setSurfacesSol(surfacesSol);
        } else {
            throw new IllegalArgumentException("Type de bien non valide");
        }
    }

    /*
        Constructeur Appart
     */

    /**
     * initie un voeux avec les attributs suivants
     * @param typeDeBien le type de bien souhaité
     * @param localisation la localisation souhaité
     * @param prixSouhaité le prix souhaité
     * @param nbPieces le nombre de piece souhaité
     * @throws IllegalArgumentException
     */
    public Voeux(String typeDeBien, String localisation, int prixSouhaité, int nbPieces) throws IllegalArgumentException{
        if (typeDeBien == "appart") {
            this.typeDeBien = typeDeBien;
            this.setLocalisation(localisation);
            this.setPrixSouhaité(prixSouhaité);
            this.setNbPieces(nbPieces);
        } else {
            throw new IllegalArgumentException("Type de bien non valide");
        }
    }

	/**
	 * initie un voeux avec tout ses attributs a null
	 */
	public Voeux() {
		// TODO Auto-generated constructor stub
	}

	/** getteur de la localisation du voeux
	 * @return localisation
	 */
	public String getLocalisation() {
		return localisation;
	}
	/** setteur de la localisation du voeux
	 * @param localisation nouvelle localisation
	 */
	public void setLocalisation(String localisation) {
		this.localisation = localisation;
	}

	/**getteur du nombre de pieces du voeux
	 * @return le nombre de piece
	 */
	public int getNbPieces() {
		return nbPieces;
	}

	/**setteur du nombre de pieces du voeux
	 * @param nbPieces le nombre de pieces souhaité
	 */
	public void setNbPieces(int nbPieces) {
		this.nbPieces = nbPieces;
	}

	/**getter de la surface au sol du voeux
	 * @return surfaceSol la surface au sol du voeux
	 */
	public double getSurfacesSol() {
		return surfacesSol;
	}
	/**setter de la surface au sol du voeux
	 * @param surfacesSol la surface au sol du voeux
	 */
	public void setSurfacesSol(double surfacesSol) {
		this.surfacesSol = surfacesSol;
	}

	/** getteur du prix souhaité du voeux
	 * @return prixSouhaité le prix souhaité du voeux
	 */
	public int getPrixSouhaité() {
		return prixSouhaité;
	}
	/** setteur du prix souhaité du voeux
	 * @param prixSouhaité le prix souhaité du voeux
	 */
	public void setPrixSouhaité(int prixSouhaité) {
		this.prixSouhaité = prixSouhaité;
	}
	public String toString() {
		String s="type de bien: "+this.typeDeBien+" prix: "+this.prixSouhaité+" localisation :"+this.localisation;
		return s;
	}
}
