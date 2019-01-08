import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class RDV {
    private Date dateRDV;
    private BienImmobilier bien;
    private List<Personne> lsPersonne;

    public RDV(Date d, BienImmobilier b, ArrayList<Personne> lsP){
        this.dateRDV=d;
        this.bien=b;
        this.lsPersonne=lsP;
    }

    Date getDateRDV(){
     return this.dateRDV;
    }
    BienImmobilier getBien(){
        return  this.bien;
    }
    List<Personne> getLsPersonne(){
        return this.lsPersonne;
    }

    void setDateRDV(Date d){
        this.dateRDV=d;
    }

    void setBien(BienImmobilier b){
        this.bien=b;
    }
    void setLsPersonne(ArrayList<Personne> l){
        this.lsPersonne=l;
    }

}
