package restructuration;

import restructuration.mascadia.BeanControleLigne;
import restructuration.util.StringUtils;

public class Main {

    public static void main(String[] args) {
        System.out.println( "restructuration start" );
        
        // System.out.println( ">Arg1: " + args[0] );
        // System.out.println( ">Arg2: " + args[1] );
        
        BeanControleLigne beanControleLigne = new BeanControleLigne();
        beanControleLigne.setLignes("Mme Nath APPT 1 RES MARIE ROSE 33500 Libourne FRANCE", "", "1 rue du loup", "", "33000 bordeaux",
                38);
        beanControleLigne.controle();
        String[] lignes12 = restructuration.util.StringUtils.getLigne1ApartirLigne2(beanControleLigne.getL2());
        
        System.out.println(lignes12[0]);
        System.out.println(lignes12[1]);
        System.out.println(beanControleLigne.getL3());

        System.out.println( "restructuration end" );

    }
}
