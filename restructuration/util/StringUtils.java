package fr.laposte.serca.u15.restructuration.util;

import fr.laposte.serca.u15.restructuration.object.Ligne;
/**
 * Classe contenant toutes les m�thodes de gestion et de modification des objets String manipul�s
 * dans l'application MASCADIA.
 * 
 * @author S�l�france SA
 * @version de base : MASCADIA-20040806
 */

public class StringUtils{

  	/**
  	 * Methode permettant de recuperer une ligne 1 a partir de la ligne 2 ou d'une autre ligne 
  	 * */
  	public static String[] getLigne1ApartirLigne2(String ligneATraiter){
  		String[] result = {"",""};
  		
  		Ligne ligne2 = new Ligne(ligneATraiter,2);
  		
  		for(int i=0;i<ligne2.getLr().size();i=i+1){
  			String lignaAajouterL1 = "";
  			String lignaAajouterL2 = "";
  			boolean ajouteLigne1 = false;

  			for(int j=0;j<ligne2.getLr().elementAt(i).getVmot().size();j=j+1){
  				if(ligne2.getLr().elementAt(i).getVmot().elementAt(j).getType()=='F'){
  					if(ligne2.getLr().elementAt(i).getVmot().elementAt(j).getChaineAb().equals("M")
  							|| ligne2.getLr().elementAt(i).getVmot().elementAt(j).getChaineAb().equals("MM")
  							|| ligne2.getLr().elementAt(i).getVmot().elementAt(j).getChaineAb().equals("MME")
  							|| ligne2.getLr().elementAt(i).getVmot().elementAt(j).getChaineAb().equals("MMES")
  							|| ligne2.getLr().elementAt(i).getVmot().elementAt(j).getChaineAb().equals("MLLE")
  							|| ligne2.getLr().elementAt(i).getVmot().elementAt(j).getChaineAb().equals("MLLES")
  							){
  						ajouteLigne1 = true;
  					}
  				}
  				
  				if(ajouteLigne1==true){
  					lignaAajouterL1 = (lignaAajouterL1 + " " + ligne2.getLr().elementAt(i).getVmot().elementAt(j).getChaine()).trim();
  				}else{
  					lignaAajouterL2 = (lignaAajouterL2 + " " + ligne2.getLr().elementAt(i).getVmot().elementAt(j).getChaine()).trim();
  				}
  			}
 			
			result[0] = (result[0] + " " + lignaAajouterL1).trim();
			result[1] = (result[1] + " " + lignaAajouterL2).trim();
  		}

  		return result;
  	}
}
