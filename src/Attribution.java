public class Attribution {

    private Activity activity ;
    private Integer machine ;
    private Integer cout ;

    public Attribution (Activity act, Integer m, Integer c){
        this.activity = act ;
        this.machine =m ;
        this.cout =c ;

    }

    public Activity getActivite() {
        return activity;
    }

    public Integer getMachine() {
        return this.machine;
    }

    public Integer getCout() {
        return cout;
    }

    public void setMachine(Integer m){
        this.machine = m ;
    }
}
