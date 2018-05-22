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

    public void delClassement(Integer index){
        this.classement.remove(index) ;
    }

    public ArrayList<Integer> getClassement() {
        return classement;
    }

    public void setClassement(Integer index, Integer value){
        this.classement.set(index, value) ;
    }

    public Integer getIndexWithActivity(Integer act){

        return classement.indexOf(act) ;
    }
}
