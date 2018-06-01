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
        ArrayList<Arc> newArcs = new ArrayList<>() ;
        for (Arc a : this.arcs){
            if (a.getMobile()==false) {
                newArcs.add(a) ;
            }

        }
        this.arcs = newArcs ;

    }

    public void resetCouts(){
        Integer i = 0 ;

        for(i=0 ; i<tabCouts.size() ; i++){
            this.tabCouts.set(i, (-1)) ;
        }
    }

    public void printf_tabCouts(){
        for(Integer i : tabCouts){
            System.out.println(i);

        }

    }

    /*Trouve en arc fixe en lui donnant en argument un sommet source*/
    public Arc findArcFixeWithSource(Sommet source){
        Arc retour = null;
        for(Arc a : arcs){
            if((a.getSource().getIdActivity()==source.getIdActivity()) && (a.getMobile()==false)){
                retour = a ;
            }
        }
        if(retour==null){
            System.out.println("Problème dans le recherche d'un arc lors de la mise a jour dans les choix avec le sommet" + source.getId());
        }
        return retour ;
    }

    public void printGraphe(){
        for(Sommet s : sommets){
            System.out.println("Sommet : " + s.getId() + " Activité : " + s.getIdActivity()+ " Cout : "+ s.getDate()+ " Machine : "+ s.getMachine());
        }
        for(Arc a : arcs){
            System.out.println("Arc = > Source : " + a.getSource().getId() + " Dest : " + a.getDest().getId() + " Cout : " + a.getCost() + " Mobile : " + a.getMobile());
        }

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

    public Sommet getSommetWithId(Integer id){
        Sommet retour ;
        for(Sommet s : this.sommets){
            if(s.getId()==id){
                return s ;
            }
        }
        return null ;
    }

    public Sommet getSommetWithIdActivity(Integer id){
        Sommet retour ;
        for(Sommet s : this.sommets){
            if(s.getIdActivity()==id){
                return s ;
            }
        }
        return null ;
    }

    public void afficherSommets(){
        for(Sommet s : sommets){
            System.out.println("ID Sommet : " + s.getId() + "ID activité : " + s.getIdActivity());
        }
    }

    public void afficherSommets2(){
        Integer i ;
        for(i=0; i<sommets.size() ; i++){
            Sommet s = getSommetWithId(i) ;
            System.out.println("ID Sommet : " + s.getId() + "ID activité : " + s.getIdActivity());
        }
    }
}
