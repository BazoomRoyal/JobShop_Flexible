import java.util.ArrayList;

public class Ordre {

    private Integer machine ;
    private ArrayList<Integer> classement ;

    public Ordre (Integer m){
        this.machine = m ;
        this.classement = new ArrayList<>() ;
    }

    public void addClassement(Integer a){
        this.classement.add(a) ;
    }

    public ArrayList<Integer> getClassement() {
        return classement;
    }
}
