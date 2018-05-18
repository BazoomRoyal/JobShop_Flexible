public class Sommet {

    private Integer id ;
    private Integer date ;
    private Integer machine ;
    private Integer idActivity ;

    public Sommet (Integer id_sommet, Integer date_sommet, Integer machine_sommet, Integer act){
        this.id = id_sommet ;
        this.date = date_sommet ;
        this.machine = machine_sommet ;
        this.idActivity = act ;
    }

    public Integer getId() {
        return id;
    }

    public Integer getDate() {
        return date;
    }

    public Integer getMachine() {
        return machine;
    }

    public Integer getIdActivity() {
        return idActivity;
    }

    public void setMachine(Integer machine) {
        this.machine = machine;
    }

    public void setDate(Integer date) {
        this.date = date;
    }

}
