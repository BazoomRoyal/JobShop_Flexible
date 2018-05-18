public class Arc {

    private Integer cost ;
    private Sommet source ;
    private Sommet dest ;
    private Boolean mobile ;

    public Arc (Integer c , Sommet s , Sommet d, Boolean b){
        this.cost = c ;
        this.source = s ;
        this.dest = d ;
        this.mobile = b ;
    }

    public Integer getCost() {
        return cost;
    }

    public Sommet getSource() {
        return source;
    }

    public Sommet getDest() {
        return dest;
    }

    public Boolean getMobile() {
        return mobile;
    }

    public void setCost(Integer cost) {
        this.cost = cost;
    }
}
