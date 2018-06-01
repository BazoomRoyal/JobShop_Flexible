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

    public void delClassement(Integer sommet){
        ArrayList<Integer> newClassement = new ArrayList<>() ;
        for(Integer i : this.classement){
            if(i != sommet){
                newClassement.add(i) ;
            }
        }
        this.classement = newClassement ;

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

    public Integer getMachine() {
        return machine;
    }
}
