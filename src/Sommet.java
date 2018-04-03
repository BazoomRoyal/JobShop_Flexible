public class Sommet {

    private String id ;
    private Integer date ;
    private Integer machine ;

    public Sommet (String id_sommet, Integer date_sommet, Integer machine_sommet){
        this.id = id_sommet ;
        this.date = date_sommet ;
        this.machine = machine_sommet ;
    }

    public String getId() {
        return id;
    }

    public Integer getDate() {
        return date;
    }

    public Integer getMachine() {
        return machine;
    }
}
