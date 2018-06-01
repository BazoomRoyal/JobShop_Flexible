import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws FileNotFoundException {
        Main launch = new Main();
        launch.go();
    }



    private void go() throws FileNotFoundException {

        //final File file = new File("./TextData/Monaldo/Fjsp/Job_Data/Brandimarte_Data/Text/Mk01.fjs");
        final File file = new File("./test.fjs");

        Scanner sc = new Scanner(file);

        Integer nbJob=sc.nextInt();

        Integer nbTotMach=sc.nextInt();

        Integer nbMachAct=sc.nextInt();

        Jobs tabJob = new Jobs(nbTotMach) ;
        for (Integer x = 1;x <= nbJob; x++){

            sc.nextLine();
            Job y = new Job("Job"+x);
            //on vient prendre le premier element de la ligne du job x qui correspond au nb d'activités
             Integer nbActivity = sc.nextInt() ;

            for (Integer i = 1; i<=nbActivity; i++){
                Integer idAct = x*10+i ;
                Activity A = new Activity(idAct);
                Integer nbMachPourAct = sc.nextInt();

                for (Integer j=1; j<=nbMachPourAct; j++){
                    Integer machine = sc.nextInt();
                    //machine-- ;//mettre -1 si on test a partir des jeux de donnees
                    Integer cout = sc.nextInt();
                    A.addMachine(machine, cout) ;
                }

                y.addActivity(A);

            }

            tabJob.addJob(y);
        }
        tabJob.printJobs();
/*
        Job j1 = new Job("job1") ;
        Activity A11 = new Activity(11) ;
        A11.addMachine(0, 3);
        Activity A12 = new Activity(12) ;
        A12.addMachine(1,2);
        A12.addMachine(2,4);
        Activity A13 = new Activity(13) ;
        A13.addMachine(1,5);
        A13.addMachine(2,5);
        j1.addActivity(A11);
        j1.addActivity(A12);
        j1.addActivity(A13);

        Job j2 = new Job("job2") ;
        Activity A21 = new Activity(21) ;
        A21.addMachine(1, 4);
        Activity A22 = new Activity(22) ;
        A22.addMachine(0,2);
        Activity A23 = new Activity(23) ;
        A23.addMachine(0,2);
        A23.addMachine(1,4);
        A23.addMachine(2,2);
        j2.addActivity(A21);
        j2.addActivity(A22);
        j2.addActivity(A23);

        Job j3 = new Job("job3") ;
        Activity A31 = new Activity(31) ;
        A31.addMachine(0, 2);
        A31.addMachine(2, 2);
        Activity A32 = new Activity(32) ;
        A32.addMachine(1,3);
        A32.addMachine(2,5);
        j3.addActivity(A31);
        j3.addActivity(A32);

        Jobs jobs = new Jobs(3) ;
        jobs.addJob(j1);
        jobs.addJob(j2);
        jobs.addJob(j3);*/

        Algo monJoliAlgo = new Algo() ;
        Integer premierRes =(-1) ;
        premierRes = monJoliAlgo.testBoucle(tabJob);
        System.out.println("Résultat final et incroyable : " + premierRes);

    }
}
