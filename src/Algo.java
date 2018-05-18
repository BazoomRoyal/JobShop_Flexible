import java.util.ArrayList;

public class Algo {

    private Graphe graphe;
    private Choix choix;

    public Algo(){
        this.graphe = new Graphe() ;
        this.choix = new Choix() ;
    }



    public void launch(){

    }

    private void creationGraphe(Jobs jobs){
        Sommet sommetDebut = new Sommet(0 , 0, 0,00) ;
        graphe.addSommet(sommetDebut);
        Integer compteur = 1;
        for(Job j: jobs.getJobs()){
            for(Activity a : j.getActivities()){
                Sommet s = new Sommet(compteur,(-1), (-1), a.getId());
                graphe.addSommet(s);
                compteur++;
            }
        }
        Sommet sommetFin = new Sommet(46, 0, 0, 99);
    }

    private void creationArcs(Jobs jobs) {
        Integer nb;
        Integer cpt = 1;
        for (Job j : jobs.getJobs()) {
            nb = j.getNbActivities();
            Arc arcDebut = new Arc(graphe.getSommets().get(0).getDate(), graphe.getSommets().get(0), graphe.getSommets().get(cpt), false);
            graphe.addArc(arcDebut);
            for (Integer i = 1; i < nb; i++) {
                Arc a = new Arc(graphe.getSommets().get(cpt).getDate(), graphe.getSommets().get(cpt), graphe.getSommets().get(cpt + 1), false);
                graphe.addArc(a);
                cpt++;
            }
            Arc arcFin = new Arc(graphe.getSommets().get(cpt).getDate(), graphe.getSommets().get(cpt), graphe.getSommets().get(46), false);
            graphe.addArc(arcFin);
        }
    }

    /* Trouve un ID sommet en fonction de l'activité qu'il execute*/
    private Integer findSommet(Integer activity){
        Sommet retour = null;
        for(Sommet s : graphe.getSommets()){
            if(s.getIdActivity()==activity){
                retour = s ;
            }
        }
        if(retour==null){
            System.out.println("Problème dans le recherche d'un sommet lors de la mise a jour dans les choix");
        }
        return retour.getId() ;
    }

    /* Prend un tableau d'attributions et modifie les sommets et les arcs fixes en leur ajoutant des valeurs*/
    private void modifSommetArcWithAttribution(ArrayList<Attribution> attributions){
        for(Attribution attr : attributions){

            /*Mise à jour Sommet*/
            graphe.getSommets().get(findSommet(attr.getActivite().getId())).setDate(attr.getCout());
            graphe.getSommets().get(findSommet(attr.getActivite().getId())).setMachine(attr.getMachine());
            /*Mise à jour Arc*/
            graphe.findArcWithSource(graphe.getSommets().get(findSommet(attr.getActivite().getId()))).setCost(attr.getCout());
        }

    }

    private void modifArcWithOrdre(ArrayList<Ordre> ordres){
        for(Ordre o : ordres){
            Integer classSize = o.getClassement().size() ;
            Integer i;
            for(i=0; i<(classSize-1) ; i++){
                Integer cout = graphe.getSommets().get(o.getClassement().get(i)).getDate() ;
                Sommet source = graphe.getSommets().get(o.getClassement().get(i)) ;
                Sommet dest = graphe.getSommets().get(o.getClassement().get(i+1)) ;
                Arc a = new Arc(cout ,source ,dest,true ) ;
                graphe.addArc(a);
            }
        }

    }



    private void initChoix(Jobs jobs){
        /* Partie A des choix, attribution activité/machine    */
        /* Modification des valeurs des sommets */

        Integer i ;
        Integer compteur = 1;
        for(Job j : jobs.getJobs()){
            for(Activity a : j.getActivities()){
                /*Générer un nb random avec une seed pour toujours avoir les meme nb random*/
                i = 1 + (int)(Math.random() * (a.getNbmach()));
                /*On retrouve le coût*/
                Integer cost = a.getCosts().get(i) ;
                Attribution attribution = new Attribution(a, a.getMachines().get(i), cost) ;
                choix.addAttribution(attribution);
            }
        }
        modifSommetArcWithAttribution(choix.getA());

        /* Partie M des choix, ordre */
        Integer m ;
        for(m=1; m <jobs.getNbTotMach(); m++){
            Ordre ordre = new Ordre(m);
            for(Sommet sommet : graphe.getSommets()){
                if(sommet.getMachine()==m){
                    ordre.addClassement(sommet.getId());

                }
            }
            choix.addOrdre(ordre);
            for(Integer s : ordre.getClassement()){

            }
        }
        modifArcWithOrdre(choix.getM());

    }
}
