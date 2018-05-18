import java.util.ArrayList;

public class Sommets {

    private ArrayList<Sommet> List_Sommet ;

    public Sommets (){
        this.List_Sommet = new ArrayList<>() ;

    }

    private void ajouter_Sommet(Sommet s){
        List_Sommet.add(s);
    }


}
