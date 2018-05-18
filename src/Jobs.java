import java.util.ArrayList;

public class Jobs {

    private ArrayList<Job> jobs ;
    private Integer nbTotMach ;

    public Jobs (){
        this.jobs = new ArrayList<>() ;
    }

    private void addJob(Job j){
        this.jobs.add(j) ;

    }

    public Integer getNbTotMach() {
        return nbTotMach;
    }

    public ArrayList<Job> getJobs() {
        return jobs;
    }
}
