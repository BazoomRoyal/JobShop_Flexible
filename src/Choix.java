
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

    public void printChoix(){
        Integer i =0 ;
        for(Attribution a : A){
            System.out.println("Attribution "+ i + " : ");
            System.out.println("Activité : "+ a.getActivite().getId()+ " Machine : " + a.getMachine());
            i++ ;
        }
        i=0 ;
        for(Ordre o : M){
            System.out.println("Ordre "+ i + " : ");
            System.out.println("Machine : "+ o.getMachine());


            for(Integer id : o.getClassement() ){
                System.out.println("id sommet : " + id);
            }

        }
    }
}
