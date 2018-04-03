import java.util.ArrayList;

public class Jobs {

    private ArrayList<Job> jobs ;

    public Jobs (){
        this.jobs = new ArrayList<>() ;
    }

    private void addJob(Job j){
        this.jobs.add(j) ;

    }
}
