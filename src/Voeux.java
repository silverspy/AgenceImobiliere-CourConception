public class Voeux {

    String typeDeBien, localisation;
    int prixSouhaité, nbPieces;
    double surfacesSol;

    /*
        Constructeur pour un terrain
     */
    public Voeux(String typeDeBien, String localisation, int prixSouhaité, double surfacesSol) throws IllegalArgumentException {
        if (typeDeBien == "terrain"){
            this.typeDeBien = typeDeBien;
            this.localisation = localisation;
            this.prixSouhaité = prixSouhaité;
            this.surfacesSol = surfacesSol;
        } else {
            throw new IllegalArgumentException("Type de bien non valide");
        }
    }

    /*
        Constructeur Maison
     */

    public Voeux(String typeDeBien, String localisation, int prixSouhaité, int nbPieces, double surfacesSol) throws IllegalArgumentException {
        if (typeDeBien == "maison") {
            this.typeDeBien = typeDeBien;
            this.localisation = localisation;
            this.prixSouhaité = prixSouhaité;
            this.nbPieces = nbPieces;
            this.surfacesSol = surfacesSol;
        } else {
            throw new IllegalArgumentException("Type de bien non valide");
        }
    }

    /*
        Constructeur Appart
     */

    public Voeux(String typeDeBien, String localisation, int prixSouhaité, int nbPieces) throws IllegalArgumentException{
        if (typeDeBien == "appart") {
            this.typeDeBien = typeDeBien;
            this.localisation = localisation;
            this.prixSouhaité = prixSouhaité;
            this.nbPieces = nbPieces;
        } else {
            throw new IllegalArgumentException("Type de bien non valide");
        }
    }
}
