import java.util.ArrayList;

public class Activity {

    private String id ;
    private ArrayList<Integer> machines ;
    private ArrayList<Integer> costs ;

    public Activity (String id_activity){
        this.id = id_activity ;
        this.machines = new ArrayList<>();
        this.costs = new ArrayList<>() ;
    }

    private void addMachine(Integer m){
        machines.add(m);
    }

    private void addCost(Integer c){
        costs.add(c);
    }

    public String getId() {
        return id;
    }
}
