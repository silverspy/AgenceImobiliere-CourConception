import java.util.ArrayList;
import java.util.List;

public class Agence {

    private List<Annonce> listAnnonce;
    private List<RDV> listRDV;
    private List<BienImmobilier> listBienImmobillier;

    public Agence(ArrayList<Annonce> lA, ArrayList<RDV> lRDV, ArrayList<BienImmobilier> lBI){
        this.listAnnonce=lA;
        this.listBienImmobillier=lBI;
        this.listRDV=lRDV;
    }
    public Agence(){
        this.listAnnonce=new ArrayList<>();
        this.listBienImmobillier=new ArrayList<>();
        this.listRDV=new ArrayList<>();
    }

    List<Annonce>  getAnnonces(){
        return this.listAnnonce;
    }

    List<RDV>  getRDV(){
        return this.listRDV;
    }

    List<BienImmobilier>  getBienImmobillier(){
        return this.listBienImmobillier;
    }

    void setAnnonce(ArrayList<Annonce> lA){
        this.listAnnonce=lA;
    }

    void setRDV(ArrayList<RDV> RDV){
        this.listRDV=RDV;
    }
    void setBienImmobilier(ArrayList<BienImmobilier> lBI){
        this.listBienImmobillier=lBI;
    }



}
