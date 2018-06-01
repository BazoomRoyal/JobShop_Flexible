import java.util.ArrayList;
import java.util.Random;
import java.util.Collections ;

public class Algo {

    private Graphe graphe;
    private Choix choix;
    private Integer randInit ;
    private Integer randType_Choix ;
    private Integer randM_Machine ;
    private Integer randM_Activity ;
    private Integer randM_Activity2 ;
    private Integer randA_Machine ;
    private Integer randA_Activity ;


    public Algo(){
        this.graphe = new Graphe() ;
        this.choix = new Choix() ;
        this.randInit = 0 ;
        this.randType_Choix = 0 ;
        this.randM_Machine = 0 ;
        this.randM_Activity = 0 ;
        this.randM_Activity2 = 0 ;
        this.randA_Machine = 0 ;
        this.randA_Activity = 0 ;

    }



    public void launch(){

    }

    private void creationGraphe(Jobs jobs){
        Sommet sommetDebut = new Sommet(0 , 0, (-1),0) ;
        graphe.addSommet(sommetDebut);
        Integer compteur = 1;
        for(Job j: jobs.getJobs()){
            for(Activity a : j.getActivities()){
                Sommet s = new Sommet(compteur,(-1), (-1), a.getId());
                graphe.addSommet(s);
                compteur++;
            }
        }
        Sommet sommetFin = new Sommet(compteur, 0, (-1), 10000);
        graphe.addSommet(sommetFin);
        graphe.afficherSommets() ;
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
            graphe.findArcFixeWithSource(graphe.getSommets().get(findSommet(attr.getActivite().getId()))).setCost(attr.getCout());
            //System.out.println("On modifie l'arc qui a pour source : "+ graphe.getSommets().get(findSommet(attr.getActivite().getId()))+ " et on lui attribue le cout : " +attr.getCout());
        }

    }

    public static int randInt(int min, int max) {

        // NOTE: This will (intentionally) not run as written so that folks
        // copy-pasting have to think about how to initialize their
        // Random instance.  Initialization of the Random instance is outside
        // the main scope of the question, but some decent options are to have
        // a field that is initialized once and then re-used as needed or to
        // use ThreadLocalRandom (if using at least Java 1.7).
        //
        // In particular, do NOT do 'Random rand = new Random()' here or you
        // will get not very good / not very random results.
        Random rand = new Random();

        // nextInt is normally exclusive of the top value,
        // so add 1 to make it inclusive
        int randomNum = rand.nextInt((max - min) + 1) + min;

        return randomNum;
    }

    private void modifArcWithOrdre(ArrayList<Ordre> ordres){

        for(Ordre o : ordres){
            Integer classSize = o.getClassement().size() ;
            Integer i;
            for(i=0; i<(classSize-1) ; i++){
                //System.out.println("GROS PRINT DES FAMILLES JE SUIS AL : "+ o.getClassement().get(i));
                Integer cout = graphe.getSommets().get(o.getClassement().get(i)).getDate() ;
                Sommet source = graphe.getSommets().get(o.getClassement().get(i)) ;
                Sommet dest = graphe.getSommets().get(o.getClassement().get(i+1)) ;
/*                Integer cout = graphe.getSommetWithId(o.getClassement().get(i)).getDate() ;
                Sommet source = graphe.getSommetWithId(o.getClassement().get(i)) ;
                Sommet dest = graphe.getSommetWithId(o.getClassement().get(i+1)) ;*/
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
                /*Random rnd = new Random();
                rnd.setSeed(1);
                Integer i = 0 ;
                i = rnd.nextInt(a.getNbmach()) ;*/
                randInit = randInt(0, a.getNbmach()-1);
                /*On retrouve le coût*/
                Integer cost = a.getCosts().get(randInit) ;
                Attribution attribution = new Attribution(a, a.getMachines().get(randInit), cost) ;
                choix.addAttribution(attribution);
            }
        }
        modifSommetArcWithAttribution(choix.getA());

        /* Partie M des choix, ordre */
        Integer m ;
        for(m=0; m <jobs.getNbTotMach(); m++){
            Ordre ordre = new Ordre(m);
            for(Sommet sommet : graphe.getSommets()){
                if(sommet.getMachine()==m){
                    ordre.addClassement(sommet.getId());

                }
            }
            choix.addOrdre(ordre);
            for(Integer s : ordre.getClassement()){

            }
            //Collections.shuffle(ordre.getClassement());
        }
        modifArcWithOrdre(choix.getM());

    }

    private Choix modifChoix(Choix choixBase, Jobs jobs){
        Choix newChoix = new Choix() ;
        newChoix = choixBase ;
        /*Random rnd = new Random();
        rnd.setSeed(1);
        Integer i = 0 ;
        i = rnd.nextInt(2) ;*/
        randType_Choix = randInt(0, 1) ;
        //SI le random est à 0 on fait une modif de M
        if(randType_Choix==0){


            randM_Machine = randInt(0, (jobs.getNbTotMach())-1) ;

            //System.out.println("taille du classement qu'on modifie : "+ newChoix.getM().get(randM_Machine).getClassement().size());
            if(newChoix.getM().get(randM_Machine).getClassement().size() > 1) {
                //A decommenter en cas de probleme
               /* randM_Activity = randInt(0, newChoix.getM().get(randM_Machine).getClassement().size() - 1);
                randM_Activity2 = randInt(0, newChoix.getM().get(randM_Machine).getClassement().size() - 1);
                //randM_Activity2 = randInt(0, 1);


                Integer activity = newChoix.getM().get(randM_Machine).getClassement().get(randM_Activity);
                Integer activity2 = newChoix.getM().get(randM_Machine).getClassement().get(randM_Activity2);


                //System.out.println("ON SWAP L'ACTIVITE : " + activity + " ET L'ACTIVITE : " + activity2 + "DE LA MACHINE : " + randM_Machine);
                newChoix.getM().get(randM_Machine).getClassement().set(randM_Activity, activity2);
                newChoix.getM().get(randM_Machine).getClassement().set(randM_Activity2, activity);*/
                randM_Activity = randInt(0, newChoix.getM().get(randM_Machine).getClassement().size() - 1);
                Integer activity = newChoix.getM().get(randM_Machine).getClassement().get(randM_Activity) ;
                Integer activity2 = 0 ;

                if(randM_Activity==0){
                    randM_Activity2 = randM_Activity+1 ;
                    activity2 = newChoix.getM().get(randM_Machine).getClassement().get(randM_Activity2) ;
                }
                else if(randM_Activity==(newChoix.getM().get(randM_Machine).getClassement().size() - 1) ){
                    randM_Activity2 = randM_Activity -1 ;
                    activity2 = newChoix.getM().get(randM_Machine).getClassement().get(randM_Activity2) ;
                }
                else{
                    randM_Activity2 = randInt(0, 1);
                    if(randM_Activity2==1){
                        randM_Activity2 = randM_Activity+1 ;
                        activity2 = newChoix.getM().get(randM_Machine).getClassement().get(randM_Activity2) ;
                    }
                    else{
                        randM_Activity2 = randM_Activity -1 ;
                        activity2 = newChoix.getM().get(randM_Machine).getClassement().get(randM_Activity2) ;
                    }
                }
                System.out.println("ON SWAP L'ACTIVITE : " + activity + " ET L'ACTIVITE : " + activity2 + "DE LA MACHINE : " + randM_Machine);
                newChoix.getM().get(randM_Machine).getClassement().set(randM_Activity, activity2);
                newChoix.getM().get(randM_Machine).getClassement().set(randM_Activity2, activity);

                this.graphe.delArcMobile();
                modifSommetArcWithAttribution(newChoix.getA());
                modifArcWithOrdre(newChoix.getM());
            }
        }
        //sinon on fait une modif de A
        else{
            //Premier random
            /*Random r = new Random();
            r.setSeed(newChoix.getA().size());
            Integer rand = 0 ;
            rand = rnd.nextInt(newChoix.getA().size() +1) ;*/
            this.randA_Activity = randInt(0, newChoix.getA().size()-1);
            Activity activity = newChoix.getA().get(randA_Activity).getActivite() ;
            Integer oldMachine = newChoix.getA().get(randA_Activity).getMachine() ;

            //deuxieme random
            /*Random r2 = new Random() ;
            r2.setSeed(activity.getNbmach());
            Integer rand2 = 0 ;
            rand2 = rnd.nextInt(activity.getNbmach() +1) ;*/
            this.randA_Machine = randInt(0, activity.getNbmach()-1);
            //System.out.println("RANDOOOOM " + randA_Machine);
            Integer newMachine = activity.getMachines().get(randA_Machine) ;
                    //newChoix.getA().get(rand2).getMachine();
            //System.out.println("modif act " + activity.getId() + "de la mach " + oldMachine + "vers la " + newMachine);
            if(oldMachine != newMachine){
                newChoix.getA().get(randA_Activity).setMachine(newMachine);
                newChoix.getA().get(randA_Activity).setCout(activity.getCosts().get(randA_Machine));

                Integer index = newChoix.getM().get(oldMachine).getIndexWithActivity(graphe.getSommetWithIdActivity(activity.getId()).getId()) ;
                //System.out.println("INDEEEEEEEEEEX " + index);
                //System.out.println("New Machine : " + newMachine + " autre : " + graphe.getSommetWithIdActivity(activity.getId()).getId());

                newChoix.getM().get(oldMachine).delClassement(graphe.getSommetWithIdActivity(activity.getId()).getId());
                newChoix.getM().get(newMachine).addClassement(graphe.getSommetWithIdActivity(activity.getId()).getId());
                this.graphe.delArcMobile();

                modifSommetArcWithAttribution(newChoix.getA());
                modifArcWithOrdre(newChoix.getM());
            }


        }
        /*this.graphe.delArcMobile();
        modifSommetArcWithAttribution(newChoix.getA());
        modifArcWithOrdre(newChoix.getM());*/
        return newChoix ;
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

    public Integer testBoucle(Jobs jobs){
        Integer plateau = 0 ;
        creationGraphe(jobs);
        creationArcs(jobs);
        initChoix(jobs);
        Integer res = (-1) ;
        Integer newRes = (-1) ;
        Sommet sommetStart = this.graphe.getSommetWithId(graphe.getSommets().size()-1) ;
        Choix memChoix = this.choix ;
        //System.out.println("Premier Choix :");
        //memChoix.printChoix();
        //System.out.println("Premier Graphe :");
        //this.graphe.printGraphe();

        res = this.graphe.Cmax(sommetStart) ;
        this.graphe.resetCouts();

        System.out.println("PREMIER RESULTAT"+ res);
        Integer i = 0 ;
        while(true){
            if(plateau ==100){
                System.out.println("Il aura fallu "+ i + " itérations pour obtenir un résultat !");
                return res ;
            }
            else {
                Choix newChoix = modifChoix(memChoix, jobs);
                //System.out.println("Nouveau choix :");
                newChoix.printChoix();
                //System.out.println("Nouveau choix :");
                //this.graphe.printGraphe();
                this.choix = newChoix;
                newRes = this.graphe.Cmax(sommetStart);
                this.graphe.resetCouts();
                System.out.println("Itération n° " + i);
                System.out.println("Res = " + res + " newRes = " + newRes);
               // System.out.println("############################################################");
                if ((newRes < res) && (newRes != (-2))) {
                    memChoix = newChoix;
                    res = newRes;
                    plateau = 0;
                }
                else if(newRes != (-2)){
                    plateau++ ;
                }

            }
            i++ ;
        }
        //return res ;

    }
}
