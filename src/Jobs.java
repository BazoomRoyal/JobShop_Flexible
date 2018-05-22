import java.util.ArrayList;

public class Jobs {

    private ArrayList<Job> jobs ;
    private Integer nbTotMach ;

    public Jobs (Integer nbMach){
        this.jobs = new ArrayList<>() ;
        this.nbTotMach = nbMach ;
    }

    public void addJob(Job j){
        this.jobs.add(j) ;

    }

    public Integer getNbTotMach() {
        return nbTotMach;
    }

    public ArrayList<Job> getJobs() {
        return jobs;
    }
}
