import java.util.ArrayList;
import java.util.Random;

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
        Sommet sommetFin = new Sommet(compteur, 0, 0, 99);
        graphe.addSommet(sommetFin);
        graphe.afficherSommets() ;
        graphe.afficherSommets2() ;
    }

    private void creationArcs(Jobs jobs) {
        Integer nb;
        Integer cptj = 1;
        Integer cpts = 1;
        for (Job j : jobs.getJobs()) {
            nb = j.getNbActivities();
            Arc arcDebut = new Arc(graphe.getSommets().get(0).getDate(), graphe.getSommets().get(0), graphe.getSommets().get(cptj), false);
            graphe.addArc(arcDebut);
            System.out.println("Création de l'arc avec comme source" + arcDebut.getSource().getIdActivity() + " et comme dest" + arcDebut.getDest().getIdActivity());

            for (Integer i = 1; i < nb; i++) {
                Arc a = new Arc(graphe.getSommets().get(cpts).getDate(), graphe.getSommets().get(cpts), graphe.getSommets().get(cpts + 1), false);
                graphe.addArc(a);
                System.out.println("Création de l'arc avec comme source" + a.getSource().getIdActivity() + " et comme dest" + a.getDest().getIdActivity());

                cpts++;
            }
            Arc arcFin = new Arc(graphe.getSommets().get(cpts).getDate(), graphe.getSommets().get(cpts), graphe.getSommetWithId(graphe.getSommets().size()-1), false);
            graphe.addArc(arcFin);
            System.out.println("Création de l'arc avec comme source" + arcFin.getSource().getId() + " et comme dest" + arcFin.getDest().getId());
            cpts++ ;
            cptj=cpts ;
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
            System.out.println("J'ai fait une modif");
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


        Integer compteur = 1;
        for(Job j : jobs.getJobs()){
            for(Activity a : j.getActivities()){
                /*Générer un nb random avec une seed pour toujours avoir les meme nb random*/
                Random rnd = new Random();
                rnd.setSeed(1);
                Integer i = 0 ;
                i = rnd.nextInt(a.getNbmach()) ;
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

    private void modifChoix(Jobs jobs){
        Random rnd = new Random();
        rnd.setSeed(1);
        Integer i = 0 ;
        i = rnd.nextInt(2) ;
        //SI le random est à 0 on fait une modif de M
        if(i==0){
            //Premier random
            Random rnd2 = new Random();
            rnd2.setSeed(jobs.getNbTotMach());
            Integer j = 0 ;
            j = rnd.nextInt(jobs.getNbTotMach() +1) ;

            //Deuxieme random
            Random rnd3 = new Random();
            rnd3.setSeed(choix.getM().get(j).getClassement().size());
            Integer k = 0 ;
            k = rnd.nextInt(choix.getM().get(j).getClassement().size() +1) ;

            //Troisième random
            Random rnd4 = new Random();
            rnd4.setSeed(choix.getM().get(j).getClassement().size());
            Integer r = 0 ;
            r = rnd.nextInt(choix.getM().get(j).getClassement().size() +1) ;

            Integer activity = choix.getM().get(j).getClassement().get(k) ;
            Integer activity2 = choix.getM().get(j).getClassement().get(r) ;
            choix.getM().get(j).getClassement().set( k, activity2) ;
            choix.getM().get(j).getClassement().set( r, activity) ;
        }
        //sinon on fait une modif de A
        else{
            //Premier random
            Random r = new Random();
            r.setSeed(choix.getA().size());
            Integer rand = 0 ;
            rand = rnd.nextInt(choix.getA().size() +1) ;
            Activity activity = choix.getA().get(rand).getActivite() ;
            Integer oldMachine = choix.getA().get(rand).getMachine() ;

            //deuxieme random
            Random r2 = new Random();
            r2.setSeed(activity.getNbmach());
            Integer rand2 = 0 ;
            rand2 = rnd.nextInt(activity.getNbmach() +1) ;
            Integer newMachine = choix.getA().get(rand2).getMachine();

            choix.getA().get(rand).setMachine(newMachine);

            Integer index = choix.getM().get(oldMachine).getIndexWithActivity(activity.getId()) ;
            choix.getM().get(oldMachine).delClassement(index);

            choix.getM().get(newMachine).addClassement(activity.getId());
        }
        this.graphe.delArcMobile();
        modifArcWithOrdre(choix.getM());
        modifSommetArcWithAttribution(choix.getA());
    }

    public Integer testFonctionnement(Jobs jobs){
        creationGraphe(jobs);
        creationArcs(jobs);
        initChoix(jobs);
        Integer i = 0 ;
        Integer res = (-1);
        for(i=0 ; i<graphe.getSommets().size(); i++) {
            Sommet sommetStart = this.graphe.getSommetWithId(i);
            res = this.graphe.Cmax(sommetStart);
            System.out.println("Cout du Sommet " + i + " : "+ res);
        }
        return res ;
    }
}
