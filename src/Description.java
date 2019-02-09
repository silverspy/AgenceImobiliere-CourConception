import java.util.ArrayList;

/**
 * Classe Description represente la description d'une annonce pour l'agence bancaire
 * une description comporte un ensemble de Documents
 */
public   class Description {

    private ArrayList<Docs> docs;
    
    /**Constructeur d'une description necessite un media
     * @param str
     */
    public Description(Media str){
        if(str.equals(Media.Web)){

        }
        if(str.equals(Media.Presse)){

        }
    }

    
    /** getteur retourne la liste des Doc
     * @return docs
     */
    public ArrayList<Docs> getListDoc(){
        return this.docs;
    }
     /** Setteur de liste des documents
     * @param d
     */
    public void setDoc(Docs d){
 
     }

}

