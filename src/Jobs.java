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

    public void printJobs(){
        System.out.println("############################");
        for(Job j : this.jobs){
            System.out.println("Job : " +j.getId());
            for(Activity a : j.getActivities()){
                System.out.println("ID Activity : "+ a.getId() + "  Nb machines : "+ a.getNbmach()+ "  ");
                Integer i = 0 ;
                for(i=0 ; i< a.getMachines().size() ; i++){
                    System.out.println("Machine "+ a.getMachines().get(i)+ " cout " + a.getCosts().get(i));
                }
            }
        }
    }
}
