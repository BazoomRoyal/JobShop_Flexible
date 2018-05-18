public class Main {

    public static void main(String[] args) {
        Main launch = new Main();
        launch.go();
    }

    private void go(){
        /*
        Sommet sa = new Sommet(1, 0, 1);
        Sommet sb = new Sommet(2, 0, 1);
        Sommet sc = new Sommet(3, 0, 1);
        Sommet sd = new Sommet(4, 0, 1);
        Sommet se = new Sommet(5, 0, 1);
        Sommet sf = new Sommet(6, 0, 1);
        Sommet sg = new Sommet(7, 0, 1);
        Sommet sh = new Sommet(8, 0, 1);
        Sommet si = new Sommet(9, 0, 1);
        Sommet sj = new Sommet(10, 0, 1);
        Sommet s0 = new Sommet(0, 0, 1);
        Sommet s46 = new Sommet(11, 0, 1);

        Graphe gr_test = new Graphe() ;

        gr_test.addSommet(s0);
        gr_test.addSommet(sa);
        gr_test.addSommet(sb);
        gr_test.addSommet(sc);
        gr_test.addSommet(sd);
        gr_test.addSommet(se);
        gr_test.addSommet(sf);
        gr_test.addSommet(sg);
        gr_test.addSommet(sh);
        gr_test.addSommet(si);
        gr_test.addSommet(sj);
        gr_test.addSommet(s46);

        Arc a0 = new Arc(0, s0 , sj ,false) ;
        Arc a1 = new Arc(16, sj , sc ,false) ;
        Arc a2 = new Arc(6, sj , sg ,false) ;
        Arc a3 = new Arc(10, sj , sb ,false) ;
        Arc a4 = new Arc(6, sj , si ,false) ;
        Arc a5 = new Arc(2, sc , se ,false) ;
        Arc a6 = new Arc(4, sc , sf ,false) ;
        Arc a7 = new Arc(4, se , sh ,false) ;
        Arc a8 = new Arc(10, sf , sh ,false) ;
        Arc a9 = new Arc(4, sg , sf ,false) ;
        Arc a10 = new Arc(8, sg , sd ,false) ;
        Arc a11 = new Arc(2, sf , sd ,false) ;
        Arc a12 = new Arc(16, sb , si ,false) ;
        Arc a13 = new Arc(20, sh , si ,false) ;
        Arc a14 = new Arc(20, sh , sa ,false) ;
        Arc a15 = new Arc(12, sd , sa ,false) ;
        Arc a16 = new Arc(4, sa , s46 ,false) ;
        Arc a17 = new Arc(10, si , s46 ,false) ;

        gr_test.addArc(a0);
        gr_test.addArc(a1);
        gr_test.addArc(a2);
        gr_test.addArc(a3);
        gr_test.addArc(a4);
        gr_test.addArc(a5);
        gr_test.addArc(a6);
        gr_test.addArc(a7);
        gr_test.addArc(a8);
        gr_test.addArc(a9);
        gr_test.addArc(a10);
        gr_test.addArc(a11);
        gr_test.addArc(a12);
        gr_test.addArc(a13);
        gr_test.addArc(a14);
        gr_test.addArc(a15);
        gr_test.addArc(a16);
        gr_test.addArc(a17);

        gr_test.Cmax(s46) ;
        gr_test.printf_tabCouts();*/
        Integer i ;
        for(Integer j=0 ; j<10 ; j++) {
            i = 1 + (int) (Math.random() * (10));
            System.out.println(i);
        }


    }
}
