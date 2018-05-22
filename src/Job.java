import java.util.ArrayList;

public class Job {

    private String id ;
    private ArrayList<Activity> activities ;


    public Job (String id_job){
        this.id = id_job ;
        this.activities = new ArrayList<>() ;
    }

    public void addActivity (Activity a){
        this.activities.add(a);
    }

    public String getId() {
        return id;
    }

    public ArrayList<Activity> getActivities() {
        return activities;
    }

    public Integer getNbActivities(){
        return activities.size() ;
    }
}
