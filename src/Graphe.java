import java.util.ArrayList;

public class Graphe {

    private ArrayList<Sommet> sommets ;
    private ArrayList<Arc> arcs ;
    private ArrayList<Integer> tabCouts ;

    public Graphe(){
        this.sommets = new ArrayList<>() ;
        this.arcs = new ArrayList<>() ;
        this.tabCouts = new ArrayList<>() ;
    }

    public void addSommet(Sommet s) {
        this.sommets.add(s) ;
        this.tabCouts.add((-1)) ;
    }

    public void addArc(Arc a){
        this.arcs.add(a);
    }

    /*Supprime tous les arcs mobiles du graphe*/
    public void delArcMobile(){
        for (Arc a : this.arcs){
            if (a.getMobile()==true) {
                this.arcs.remove(a) ;
            }
        }
    }

    public void resetCouts(){
        for(Integer i : tabCouts){
            i=(-1) ;
        }
    }

    public void printf_tabCouts(){
        for(Integer i : tabCouts){
            System.out.println(i);

        }

    }

    /*Trouve en arc en lui donnant en argument un sommet source*/
    public Arc findArcWithSource(Sommet source){
        Arc retour = null;
        for(Arc a : arcs){
            if(a.getSource()==source){
                retour = a ;
            }
        }
        if(retour==null){
            System.out.println("Problème dans le recherche d'un arc lors de la mise a jour dans les choix");
        }
        return retour ;
    }

    /*Renvoie -2 si on détecte une boucle dans le graphe)*/
    public Integer Cmax (Sommet fin){

        if(fin.getId()==0){
            return 0;
        }
        else if (tabCouts.get(fin.getId())!=(-1)){
            return tabCouts.get(fin.getId()) ;
        }
        else{
            Integer intermediaire = 0;
            tabCouts.set(fin.getId(), (-2)) ;
            for (Arc a : arcs) {
                Integer coutActuel = 0;
                if (a.getDest() == fin) {
                    Integer retour_appel = Cmax(a.getSource()) ;
                    if(retour_appel == (-2)){
                        return(-2) ;
                    }
                    coutActuel = a.getCost() + retour_appel;
                    if (coutActuel > intermediaire) {
                        intermediaire= coutActuel ;
                    }
                }
            }
            tabCouts.set(fin.getId(), intermediaire) ;

            return tabCouts.get(fin.getId()) ;

        }


    }

    public ArrayList<Sommet> getSommets() {
        return sommets;
    }

    public ArrayList<Arc> getArcs() {
        return arcs;
    }
}
