import java.util.ArrayList;

public class Activity {

    private Integer id ;
    private ArrayList<Integer> machines ;
    private ArrayList<Integer> costs ;
    private Integer nbmach ;

    public Activity (Integer id_activity){
        this.id = id_activity ;
        this.machines = new ArrayList<>();
        this.costs = new ArrayList<>() ;
        this.nbmach=0;
    }

    private void addMachine(Integer m, Integer c){
        machines.add(m);
        costs.add(c);
        nbmach += 1;
    }

    private void addCost(Integer c){
        costs.add(c);
    }

    public Integer getId() {
        return id;
    }

    public Integer getNbmach() {
        return nbmach;
    }

    public ArrayList<Integer> getMachines() {
        return machines;
    }

    public ArrayList<Integer> getCosts() {
        return costs;
    }
}
