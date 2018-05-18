
import java.util.ArrayList;

public class Choix {

    private ArrayList<Attribution> A ;
    private ArrayList<Ordre> M ;

    public Choix (){
        this.A = new ArrayList<>() ;
        this.M = new ArrayList<>() ;
    }

    public void addAttribution(Attribution a){
        A.add(a) ;
    }

    public void addOrdre(Ordre ordre){
        M.add(ordre) ;
    }

    public ArrayList<Attribution> getA() {
        return A;
    }

    public ArrayList<Ordre> getM() {
        return M;
    }
}
