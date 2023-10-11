package restructuration.mascadia;

import java.util.Vector;

import restructuration.data.DataMotscles;
import restructuration.data.DataExtensions;
import restructuration.object.Mot;
import restructuration.object.Rubrique;
import restructuration.object.Ligne;

/**
 * @version 02.02.00
 * @author La Poste - Service National de l'Adresse
   */
/*
 * Date de creation : 03/01/2002 <BR>
 * Derniere date de modification : RJA_20040929 <BR>
 * 04/07/2002 : Correction d'un bug. L3 et L4 vide, on allait pas cherch� la
 * 1ere rub. de la L2 (si 1 rub). <BR>
 * 30/06/2002 : Ajout d'une m�thode surcharg�e traitement(params) Javabean
 * permettant le traitement des lignes L2 � L5 Ce traitement s'appuie sur le
 * document WebAdresse (Eric Despujols) Le process d'appel est le suivant : <BR>
 * 1 - Positionner les chaines de caract�res des lignes 2 � 5 : setLx() ou
 * setLignes() <BR>
 * 2 - Positionner �ventuellement la norme des adresses : setNorme(int) (32 ou
 * 38 caract�res) <BR>
 * 3 - Appeler la m�thode traitement() pour lancer le traitement sur les lignes
 * L2 � L5 <BR>
 * 4 - R�cup�rer les nouvelles valeurs des lignes (getLx()), des messages
 * d'erreur (getMsgx()), et du message de retour g�n�ral (getMsg())
 * 
 * RJA_20040929 : BUG existant dans ANET java et pas dans ANET VB ("DAILLY LAURE /
 * numero 91 / batiment d rez de chaussee / res pal de la mediterranee / 11560
 * fleury d aude" ==> java.lang.ArrayIndexOutOfBoundException: 1 >= 1).
 * JFA_20041109 : Modification suite mas_26_far.doc
 */

public class BeanControleLigne {
	private String s_erreur[];

	private int s_error;

	private Ligne L2 = null;

	private Ligne L3 = null;

	private Ligne L4 = null;

	private Ligne L5 = null;
	
	private Ligne L6 = null;
	
	private Vector<String> rubErreur = null; // noms des rubriques appartenants � une

	private int norme;

	private int retour = 0;

	/**
	 * Date de creation : 03/01/2002 <BR>
	 * Derniere date de modification : 21/02/2002 <BR>
	 * 21/02/2002 : Messages d'erreur mis dans actxControleGrille.ini <BR>
	 * Le constructeur permet de fixer la norme par d�faut � 38.
	 * 
	 * @since Anet Version 1 JavaCrypt
	 */
	public BeanControleLigne() {
		Vector<String> V = new Vector<String>();
		/*
		 * int i; ResourceBundle res =
		 * ResourceBundle.getBundle("...mascadia.grille"); String
		 * StrFicIni = res.getString("anet_ini");
		 * 
		 * V = UtilIniFile.getParams(StrFicIni, "actxControleLigne");
		 */
		s_erreur = new String[V.size()];
		for (int i = 0; i < V.size(); i++){
			s_erreur[i] = (String) V.elementAt(i);
		}
		this.norme = 38;
	}

	/**
	 * Date de creation : 03/01/2002 <BR>
	 * Derniere date de modification : 03/01/2002
	 * 
	 * @return retourne les rubriques (sous la forme de String) en erreur, dans
	 *         le cas d'une ou plusieurs lignes trop longues.
	 * @since Anet Version 1 JavaCrypt
	 */
	public Vector<String> getRubErreur() {
		return rubErreur;
	}

	/**
	 * MCR 062007 - Gestion des numeros separes par / sur la ligne 4
	 * 
	 */
	private void gererSlashLigne4() {
		// Si la ligne 4 est renseigne, recherche si il existe
		// xx/yy/zz ou xx/yy num�riques en d�but de chaine
		if (L4.getLr().size() != 0) {
			// on se sert de la ligne 4 memo car le numero est deja reformate
			// par le constructeur et on n'a plus xx/yy/zz dans getLigne
			String chaineL4 = L4.getLigneMemo();
			String chaineL4Reste = L4.getLigneMemo();
			
			if(chaineL4.indexOf("/")>-1 || chaineL4.indexOf("-")>-1){
				chaineL4 = chaineL4.replaceAll("-","/");
				String[] numeroTrouve = chaineL4.split("/");
				for(int i=0;i<numeroTrouve.length;i=i+1){
					numeroTrouve[i] = numeroTrouve[i].trim();
				}

				if(numeroTrouve.length>0){
					String[] resultTmp = numeroTrouve[numeroTrouve.length-1].split(" ");
					numeroTrouve[numeroTrouve.length-1] = resultTmp[0];
					chaineL4Reste = "";
					for(int i=1;i<resultTmp.length;i=i+1){
						chaineL4Reste = chaineL4Reste + " " + resultTmp[i];
					}  
				}
				
				String[] numeroTraite = new String[numeroTrouve.length];
				String[] extensionTraite = new String[numeroTrouve.length];
				
				/**
				 * SERCA-2 - Jira SERC-518
				 * Control si il y a plus de 2 numeros de voie
				 * 
				 **/ 
				if(numeroTrouve.length <= 2){
				
				/**
				* SERCA-2 - Jira SERC-518
				**/
				
				
				for(int i=0;i<numeroTrouve.length;i=i+1){
					String numeroTmp = "";
					String extensionTmp = "";
					for(int j=0;j<numeroTrouve[i].length();j=j+1){
						if(Character.isDigit(numeroTrouve[i].charAt(j))){
							numeroTmp = numeroTmp + numeroTrouve[i].charAt(j);
						}else{
							extensionTmp = numeroTrouve[i].substring(j);
							break;
						}
					}

					//verification de l'extension
					if(numeroTmp.length()==0){
						extensionTmp = "";
					}
					if(extensionTmp.length()>0){
						boolean flagExtensionOk = false;
						for(int j=0;j<DataExtensions.tab.length;j=j+1){
							if(extensionTmp.equals(DataExtensions.tab[j][0]) || 
								extensionTmp.equals(DataExtensions.tab[j][1])){
								
								flagExtensionOk = true;
								extensionTmp = DataExtensions.tab[j][1];
								break;
							}
						}
						if(flagExtensionOk==false){
							extensionTmp = "";
						}
					}

					numeroTraite[i] = numeroTmp;
					extensionTraite[i] = extensionTmp;
				}
				
				//on replace les numeros dans le tableau
				int nbElementsValides = 0;
				for(int i=0;i<numeroTraite.length;i=i+1){
					if(numeroTraite[i].length()>0){
						nbElementsValides = nbElementsValides+1;
					}
				}

				if(nbElementsValides>1){
					int[] numeroTraiteInt = new int[nbElementsValides];
					String[] extensionOk = new String[nbElementsValides];
					int j=0;
					for(int i=0;i<numeroTraite.length;i=i+1){
						if(numeroTraite[i].length()>0){
							numeroTraiteInt[j] = Integer.parseInt(numeroTraite[i]);
							extensionOk[j] = extensionTraite[i];
							j=j+1;
						}
					}
					
					//Traitement du 2eme numero(pour entree)
					if(nbElementsValides>1){
						// controle de parite
						int pariteNumero0 = numeroTraiteInt[0] % 2; // modulo 2
						int pariteNumero1 = numeroTraiteInt[1] % 2; // modulo 2
	
						// si numero de m�me parit� et 2eme>1er + 10
						// ou numero de parite differente
						// ou 2eme numero <= au 1er numero
						// on remonte yy en L3
						
						//SERCA-2
						//String chaine = numeroTraiteInt[1].concat(Integer.parseInt(extensionTraite[1])));
						
						//numeroTraiteInt[1] + Integer.parseStr(extensionTraite[1]);
						
						//int comp1 = numeroTraiteInt[1]+ Integer.parseInt(extensionTraite[1]);
						//int comp2 = numeroTraiteInt[0]+ Integer.parseInt(extensionTraite[0]);
						
						 String num2 = Integer.toString(numeroTraiteInt[1]).concat(extensionTraite[1]);
						 String num1 = Integer.toString(numeroTraiteInt[0]).concat(extensionTraite[0]);						 					
						 int comp = num2.compareTo(num1);
						
						    /*if(comparison < 0) {
						        System.out.println("inf�rieur");
						      } else if(comparison > 0) {
						        System.out.println("sup�rieur");
						      } else { // donc exactement 0
						        System.out.println("identique");
						      }*/
						 	    
												
						if (((pariteNumero0 == pariteNumero1) && (numeroTraiteInt[1] > (numeroTraiteInt[0] + 10)))
								|| (pariteNumero0 != pariteNumero1)
								//|| (numeroTraiteInt[1] <= numeroTraiteInt[0])) {
								|| (comp <= 0)){
							

							
	
							// si L3 comporte ENTREE on remonte yy pr�c�d� de EXT
							// sinon pr�c�d� de ENTREE
							String chaineModifie = "";
							if (L3.getLr().size() != 0) {
								chaineModifie = L3.getLigne();
								// on parcourt le tableau des mots cles pour voir si
								// il ya a ENTREEou equivalent
								// dans la L2
								boolean motCleTrouve = false;
								int i=0;
								while ((!motCleTrouve)
										&& (i < DataMotscles.tab.length)) {
									if ((DataMotscles.tab[i][2].compareTo("ENTREE") == 0)
											&& (chaineModifie.indexOf(DataMotscles.tab[i][1]) >= 0)) {
										motCleTrouve = true;
									}
									i++;
								}
								//4.2.1.2.2.1 plus de ext
								if (motCleTrouve) {
									//chaineModifie = chaineModifie + " EXT";
								} else {
									chaineModifie = chaineModifie + " ENTREE";
								}
							} else {
								chaineModifie = "ENTREE";
								
							}
							chaineModifie = chaineModifie + " " + numeroTraiteInt[1];
							if(extensionOk[1].length()>0){
								chaineModifie = chaineModifie + " " + extensionOk[1];
							}
							L3.clearLr();
							L3.setLigne(chaineModifie);
							L3.construitVecteurRubrique();
							L3.setFlagRestructurationOn();
							L4.setFlagRestructurationOn();
							//4.2.1.2.2.1 ajout du bit L4 - 9
							L4.setAjoutErrorBin("9");
							L3.setAjoutErrorBin("12");
						}
					}
					if(nbElementsValides>0){
						L4.clearLr();
						String errorBin = L4.getErrorBin();
						boolean flagRestructurationL4 = L4.isFlagRestructuration();
						L4 = new Ligne(numeroTraiteInt[0] + " " + extensionOk[0] + chaineL4Reste,4);
						L4.setFlagRestructuration(flagRestructurationL4);
						L4.setErrorBin(errorBin);
					}
				}
				
			/**
			 * SERCA-2 - Jira SERC-518
			 * Control si il y a plus de 2 numeros de voie
			 * 
			**/ 
			}
				
			/**
			* SERCA-2 - Jira SERC-518
			**/
				
			}
			
			
		}
	}

	/*
	 * Suppression des lignes identiques.
	 * 
	 * @since Anet Version 1 JavaCrypt
	 */
	private void supprimerLignesIdentiques() {
		// Comparaison de L2 avec L3,L4,L5
		if (L2.getLr().size() != 0) {
			if (L3.getLr().size() != 0) {
				if (L2.getLigne().compareTo(L3.getLigne()) == 0) {
					// L2 et L3 identiques
					L2.clearLr();
				}
			}
		}

		if (L2.getLr().size() != 0) {
			if (L4.getLr().size() != 0) {
				if (L2.getLigne().compareTo(L4.getLigne()) == 0) {
					// L2 et L4 identiques
					L2.clearLr();
				}
			}
		}
		if (L2.getLr().size() != 0) {
			if (L5.getLr().size() != 0) {
				if (L2.getLigne().compareTo(L5.getLigne()) == 0) {
					// L2 et L5 identiques
					L2.clearLr();
				}
			}
		}

		// Comparaison de L3 avec L4,L5
		if (L3.getLr().size() != 0) {
			if (L4.getLr().size() != 0) {
				if (L3.getLigne().compareTo(L4.getLigne()) == 0) {
					// L3 et L4 identiques
					L3.clearLr();
				}
			}
		}
		if (L3.getLr().size() != 0) {
			if (L5.getLr().size() != 0) {
				if (L3.getLigne().compareTo(L5.getLigne()) == 0) {
					// L3 et L5 identiques
					L3.clearLr();
				}
			}
		}

		// Comparaison de L4 avec L5
		if (L4.getLr().size() != 0) {
			if (L5.getLr().size() != 0) {
				if (L4.getLigne().compareTo(L5.getLigne()) == 0) {
					// L4 et L5 identiques
					L4.clearLr();
				}
			}
		}
	}

	/**
	 * Date de creation : 03/01/2002 <BR>
	 * Derniere date de modification : 03/01/2002 <BR>
	 * Positionne la ligne 2. <BR>
	 * Notons que cette m�thode r�initialise plusieurs variables. (Messages,
	 * rubriques d'erreur ...)
	 * 
	 * @param chaine :
	 *            chaine de caract�res de la ligne 2
	 * @since Anet Version 1 JavaCrypt
	 */
	public void setL2(String chaine) {
		 
		this.rubErreur = null;
		this.retour = 0;
		L2 = new Ligne(chaine, 2);
	}

	/**
	 * Date de creation : 03/01/2002 <BR>
	 * Derniere date de modification : 03/01/2002 <BR>
	 * Positionne la ligne 3.
	 * 
	 * @param chaine :
	 *            chaine de caract�res de la ligne 3
	 * @since Anet Version 1 JavaCrypt
	 */

	public void setL3(String chaine) {
		L3 = new Ligne(chaine, 3);
	}

	/**
	 * Date de creation : 03/01/2002 <BR>
	 * Derniere date de modification : 03/01/2002 <BR>
	 * Positionne la ligne 4.
	 * 
	 * @param chaine :
	 *            chaine de caract�res de la ligne 4
	 * @since Anet Version 1 JavaCrypt
	 */
	public void setL4(String chaine) {
		L4 = new Ligne(chaine, 4);
	}

	/**
	 * Date de creation : 03/01/2002 <BR>
	 * Derniere date de modification : 03/01/2002 <BR>
	 * Positionne la ligne 5.
	 * 
	 * @param chaine :
	 *            chaine de caract�res de la ligne 5
	 * @since Anet Version 1 JavaCrypt
	 */
	public void setL5(String chaine) {
		L5 = new Ligne(chaine, 5);
	}
	
	/**
	 * Positionne la ligne 6.
	 * 
	 * @param chaine :
	 *            chaine de caract�res de la ligne 6
	 */
	public void setL6(String chaine) {
		L6 = new Ligne(chaine, 6);
	}

	/**
	 * Date de creation : 03/01/2002 <BR>
	 * Derniere date de modification : 03/01/2002 <BR>
	 * Positionne les lignes 2,3,4,5 + la norme.
	 * 
	 * @param	  ligne2 : chaine de caract�res de la ligne 2 <BR>
	 *            ligne3 : chaine de caract�res de la ligne 3 <BR>
	 *            ligne4 : chaine de caract�res de la ligne 4 <BR>
	 *            ligne5 : chaine de caract�res de la ligne 5 <BR>
	 *            ligne6 : chaine de caract�res de la ligne 6 <BR>
	 *            norme : 32 ou 38 (norme de l'adresse)
	 * @since Anet Version 2 JavaCrypt
	 */
	public void setLignes(String ligne2, String ligne3, String ligne4,
			String ligne5,String ligne6, int norme) {
		L2 = new Ligne(ligne2, 2);
		L3 = new Ligne(ligne3, 3);
		L4 = new Ligne(ligne4, 4);
		L5 = new Ligne(ligne5, 5);
		L6 = new Ligne(ligne6, 6);
		this.norme = norme;
	}
	
	/**
	 * Date de creation : 03/01/2002 <BR>
	 * Derniere date de modification : 03/01/2002 <BR>
	 * Retourne la ligne 2 apr�s restructuration des lignes 2 � 5
	 * 
	 * @return Ligne 2 apr�s restructuration
	 * @since Anet Version 1 JavaCrypt
	 */
	public String getL2() {
		return L2.getL(this.norme);
	}

	/**
	 * Date de creation : 03/01/2002 <BR>
	 * Derniere date de modification : 03/01/2002 <BR>
	 * Retourne la ligne 3 apr�s restructuration des lignes 2 � 5
	 * 
	 * @return Ligne 3 apr�s restructuration
	 * @since Anet Version 1 JavaCrypt
	 */
	public String getL3() {
		return L3.getL(this.norme);
	}

	/**
	 * Date de creation : 03/01/2002 <BR>
	 * Derniere date de modification : 03/01/2002 <BR>
	 * Retourne la ligne 4 apr�s restructuration des lignes 2 � 5
	 * 
	 * @return Ligne 4 apr�s restructuration
	 * @since Anet Version 1 JavaCrypt
	 */
	public String getL4() {
		return L4.getL(this.norme);
	}

	/**
	 *  Retourne la ligne 4 abreg�e
	 */
	public String getL4Ab() {
		return L4.getLigneAb(this.norme);
	}

	/**
	 *  Retourne la ligne 4 in extenso
	 */
	public String getL4IE() {
		return L4.getLigne();
	}

	/**
	 *  Retourne la ligne 4 m�moris�e
	 */
	public String getL4Memo() {
		return L4.getLigneMemo();
	}

	/**
	 * Date de creation : 03/01/2002 <BR>
	 * Derniere date de modification : 03/01/2002 <BR>
	 * Retourne la ligne 5 apr�s restructuration des lignes 2 � 5
	 * 
	 * @return Ligne 5 apr�s restructuration
	 * @since Anet Version 1 JavaCrypt
	 */
	public String getL5() {
		return L5.getL(this.norme);
	}
	
	public String getL6() {
		return L6.getL(this.norme);
	}

	/**
	 *  Retourne la ligne 6 m�moris�e
	 */
	public String getL6Memo() {
		return L6.getLigneMemo();
	}
	
	/**
	 * Date de creation : 03/01/2002 <BR>
	 * Derniere date de modification : 03/01/2002 <BR>
	 * Retourne les lignes 2,3,4,5 apr�s restructuration des lignes 2 � 5
	 * 
	 * @return Lignes 2 � 5 sous la forme de cha�nes de caract�res s�par�es par
	 *         des points virgules
	 * @since Anet Version 2 JavaCrypt
	 */
	public String getLignes() {
		return L2.getL(this.norme) + ";" + L3.getL(this.norme) + ";"
				+ L4.getL(this.norme) + ";" + L5.getL(this.norme);
	}

	/**
	* retourne la ligne 2
	*/
	public Ligne getLigne2(){
		return L2;
	}
	
	/**
	* retourne la ligne 3
	*/
	public Ligne getLigne3(){
		return L3;
	}
	
	/**
	* retourne la ligne 4
	*/
	public Ligne getLigne4(){
		return L4;
	}
	
	/**
	* retourne la ligne 5
	*/
	public Ligne getLigne5(){
		return L5;
	}
	
	/**
	 * Date de creation : 03/01/2002 <BR>
	 * Derniere date de modification : 03/01/2002
	 * 
	 * @since Anet Version 1 JavaCrypt
	 */
	public String getMsg5() {
		return L5.getMsg();
	}

	/**
	 * Date de creation : 03/01/2002 <BR>
	 * Derniere date de modification : 03/01/2002
	 * 
	 * @since Anet Version 1 JavaCrypt
	 */
	public String getMsg4() {
		return L4.getMsg();
	}

	/**
	 * Date de creation : 03/01/2002 <BR>
	 * Derniere date de modification : 03/01/2002
	 * 
	 * @since Anet Version 1 JavaCrypt
	 */
	public String getMsg3() {
		return L3.getMsg();
	}

	/**
	 * Date de creation : 03/01/2002 <BR>
	 * Derniere date de modification : 03/01/2002
	 * 
	 * @since Anet Version 1 JavaCrypt
	 */
	public String getMsg2() {
		return L2.getMsg();
	}

	/**
	 * Renvoie les bits positionnes lors du controle de ligne 2
	 * 
	 * @return liste des bits positionn�s s�par�s par ,
	 */
	public String getError2() {
		return L2.getErrorBin();
	}

	/**
	 * Renvoie les bits positionnes lors du controle de ligne 3
	 * 
	 * @return liste des bits positionn�s s�par�s par ,
	 */
	public String getError3() {
		return L3.getErrorBin();
	}

	/**
	 * Renvoie les bits positionnes lors du controle de ligne 4
	 * 
	 * @return liste des bits positionn�s s�par�s par ,
	 */
	public String getError4() {
		return L4.getErrorBin();
	}

	/**
	 * Renvoie les bits positionnes lors du controle de ligne 5
	 * 
	 * @return liste des bits positionn�s s�par�s par ,
	 */
	public String getError5() {
		return L5.getErrorBin();
	}

	/**
	 * Date de creation : 03/01/2002 <BR>
	 * Derniere date de modification : 21/02/2002 21/02/2002 : Messages d'erreur
	 * dans le fichier actxControleGrille.ini <BR>
	 * Retourne le message d'erreur associ� au traitement de la restructuration
	 * 
	 * @return Message d'erreur
	 * @since Anet Version 1 JavaCrypt
	 */
	public String getMsg() {
		return s_erreur[java.lang.Math.abs(s_error)];
	}

	/**
	 * Date de creation : 03/01/2002 <BR>
	 * Derniere date de modification : 03/01/2002 <BR>
	 * Positionne la norme (32 ou 38 caract�res).
	 * 
	 * @param norme :
	 *            32 ou 38
	 * @since Anet Version 1 JavaCrypt.
	 */
	public void setNorme(int norme) {
		if (this.norme == 32 || this.norme == 38){
			this.norme = norme;
		}
	}

	/**
	 * Date de creation : 30/06/2002 <BR>
	 * Derniere date de modification : 30/06/2002 <BR>
	 * Traitement principal de la restructuration
	 * 
	 * @param ligne2 : chaine de caract�res de la ligne 2 <BR>
	 *            ligne3 : chaine de caract�res de la ligne 3 <BR>
	 *            ligne4 : chaine de caract�res de la ligne 4 <BR>
	 *            ligne5 : chaine de caract�res de la ligne 5 <BR>
	 *            ligne6 : chaine de caract�res de la ligne 6 <BR>
	 *            norme : 32 ou 38 (norme de l'adresse)
	 * @return 0 : OK. <BR>
	 *         -1 : Les lignes ont �t� restructur�es. <BR>
	 *         -2 : Une ligne est trop longue.
	 */

	public int controle(String ligne2, String ligne3, String ligne4,
			String ligne5,String ligne6, int norme) {
		this.setLignes(ligne2, ligne3, ligne4, ligne5, ligne6, norme);
		return this.controle();
	}

	/**
	 * Date de creation : 03/01/2002 <BR>
	 * Derniere date de modification : 03/01/2002 <BR>
	 * Traitement principal de la restructuration
	 * 
	 * @return 0 : OK. <BR>
	 *         -1 : Les lignes ont �t� restructur�es. <BR>
	 *         -2 : Une ligne est trop longue.
	 */

	public int controle() {
		int indice;
		// JFA_20041109
		int flag;

		// System.out.println("BCT.controle() : Debut du traitement ...");
		// 1- suppression des lignes identiques
		supprimerLignesIdentiques();
		// MCR 062007 - Gestion des numeros separes par / sur la ligne 4
		this.gererSlashLigne4();

		// System.out.println("BCT.controle() : Suppression des lignes
		// identiques OK");
		//

		// 2- r�organisation des vecteurs L de chaque ligne

		// r�organisation Ligne 2
		Vector<Rubrique> Lr_retour = L2.reorganiseVecteurRubrique();
		// System.out.println("BCT.traitement() : reorganiseVecteur Ligne 2");
		// analyse de Lr_retour :
		if (Lr_retour.size() != 0) {
			for (int i = 0; i < Lr_retour.size(); i++) {
				Rubrique maRubrique = Lr_retour.elementAt(i);
				switch (maRubrique.getLigne()) {
				case 3:
					L3.ajouteRubrique(maRubrique, true);
					L3.setAjoutErrorBin("13");
					L3.setFlagRestructurationOn();
					L2.setFlagRestructurationOn();
					break;
				case 9:
					L3.ajouteRubrique(maRubrique, true);
					L3.setAjoutErrorBin("13");
					L3.setFlagRestructurationOn();
					L2.setFlagRestructurationOn();
					break;
				case 4:
					//jpc modif 4.2.1.3.9
					L4.ajouteRubriqueALaFin(maRubrique, true);
					L4.setFlagRestructurationOn();
					L2.setFlagRestructurationOn();
					break;
				case 5:
					L5.ajouteRubriqueALaFin(maRubrique, true);
					if(maRubrique.getType()==1){
						L5.setAjoutErrorBin("8");
					}else if(maRubrique.getType()==0){
						L5.setAjoutErrorBin("11");
					}
					L5.setFlagRestructurationOn();
					L2.setFlagRestructurationOn();
					break;
				}
			}
		}

		// r�organisation Ligne 3
		Lr_retour.clear();
		Lr_retour = L3.reorganiseVecteurRubrique();
		// System.out.println("BCT.traitement() : reorganiseVecteur Ligne 3");
		// analyse de L_retour :
		if (Lr_retour.size() != 0) {
			for (int i = 0; i < Lr_retour.size(); i++) {
				Rubrique maRubrique = Lr_retour.elementAt(i);
				switch (maRubrique.getLigne()) {
				case 2:
					L2.ajouteRubrique(maRubrique, true);
					L2.setAjoutErrorBin("13");
					L2.setFlagRestructurationOn();
					L3.setFlagRestructurationOn();
					break;
				case 8:
					L2.ajouteRubrique(maRubrique, true);
					L2.setAjoutErrorBin("13");
					L2.setFlagRestructurationOn();
					L3.setFlagRestructurationOn();
					break;
				case 4:
					//jpc modif 4.2.1.3.9
					L4.ajouteRubriqueALaFin(maRubrique, true);
					L4.setFlagRestructurationOn();
					L3.setFlagRestructurationOn();
					break;
				case 5:
					L5.ajouteRubriqueALaFin(maRubrique, true);
					if(maRubrique.getType()==1){
						L5.setAjoutErrorBin("8");
					}else if(maRubrique.getType()==0){
						L5.setAjoutErrorBin("11");
					}
					L5.setFlagRestructurationOn();
					L3.setFlagRestructurationOn();
					break;
				}
			}
		}

		// r�organisation Ligne 4
		Lr_retour.clear();
		Lr_retour = L4.reorganiseVecteurRubrique();
		// System.out.println("BCT.traitement() : reorganiseVecteur Ligne 4");
		// analyse de Lr_retour :
		if (Lr_retour.size() != 0) {
			for (int i = 0; i < Lr_retour.size(); i++) {
				Rubrique maRubrique = Lr_retour.elementAt(i);
				switch (maRubrique.getLigne()) {
				case 2:
					L2.ajouteRubrique(maRubrique, true);
					L2.setAjoutErrorBin("9");
					L4.setFlagRestructurationOn();
					L2.setFlagRestructurationOn();
					break;
				case 8:
						L4.ajouteRubrique(maRubrique, true);
					break;
				case 3:
					L3.ajouteRubrique(maRubrique, true);
					L3.setAjoutErrorBin("12");
					L4.setFlagRestructurationOn();
					L3.setFlagRestructurationOn();
					break;
				case 9:
						L4.ajouteRubrique(maRubrique, true);
					break;
				case 5:
					L5.ajouteRubriqueALaFin(maRubrique, true);
					if(maRubrique.getType()==1){
						L5.setAjoutErrorBin("8");
					}else if(maRubrique.getType()==0){
						L5.setAjoutErrorBin("11");
					}
					L4.setFlagRestructurationOn();
					L5.setFlagRestructurationOn();
					break;
				}
			}
		}

		// r�organisation Ligne 5
		Lr_retour.clear();
		Lr_retour = L5.reorganiseVecteurRubrique();
		// System.out.println("BCT.traitement() : reorganiseVecteur Ligne 5");
		// analyse de L_retour :
		if (Lr_retour != null) {
			for (int i = 0; i < Lr_retour.size(); i++) {
				Rubrique maRubrique = Lr_retour.elementAt(i);
				switch (maRubrique.getLigne()) {
				case 2:
					L2.ajouteRubrique(maRubrique, true);
					L2.setAjoutErrorBin("8");
					L5.setFlagRestructurationOn();
					L2.setFlagRestructurationOn();
					break;
				case 8:
					L2.ajouteRubrique(maRubrique, true);
					L2.setAjoutErrorBin("8");
					L5.setFlagRestructurationOn();
					L2.setFlagRestructurationOn();
					break;
				case 3:
					L3.ajouteRubrique(maRubrique, true);
					L3.setAjoutErrorBin("8");
					L5.setFlagRestructurationOn();
					L3.setFlagRestructurationOn();
					break;
				case 9:
					L3.ajouteRubrique(maRubrique, true);
					L3.setAjoutErrorBin("8");
					L5.setFlagRestructurationOn();
					L3.setFlagRestructurationOn();
					break;
				case 4:
					L4.ajouteRubriqueALaFin(maRubrique, true);
					//L5.ajouteRubrique(maRubrique, true); // peut etre pour le traitement des ld ?
					L5.setFlagRestructurationOn();
					L4.setFlagRestructurationOn();
					break;
				}
			}
		}

		//test reorganisation ligne 6
		// r�organisation Ligne 6
		Lr_retour.clear();
		Lr_retour = L6.reorganiseVecteurRubrique();
		// System.out.println("BCT.traitement() : reorganiseVecteur Ligne 5");
		// analyse de L_retour :
		if (Lr_retour != null) {
			for (int i = 0; i < Lr_retour.size(); i++) {
				Rubrique maRubrique =  Lr_retour.elementAt(i);
				switch (maRubrique.getLigne()) {
				case 2:
				case 8:
				case 3:
				case 9:
				case 4:
					//jpc on replace la rubrique ici : cas du mot cle BOURG ...
					L6.ajouteRubriqueALaFin(maRubrique, true);
					break;
				case 5:
					L5.ajouteRubrique(maRubrique, true);
					L5.setAjoutErrorBin("11");
					L6.setFlagRestructurationOn();
					L5.setFlagRestructurationOn();
					break;
				}
			}
		}

		//La ligne 5 a t'elle �t� normalis�e ?
		if(!L5.vide() && L5.getLigneMemo()!=null && !L5.getLigneMemo().equals(L5.getLigne()) && L5.getLigneMemo().length()>0){
			L5.setAjoutErrorBin("12");
		}
		
		L2.controleSupp_3();
		L3.controleSupp_3();
		
		// 20050916 modif suite DFB_evo_0509
		// si la ligne 4 est vide :
		if (L4.vide() == true) {

			Vector<Rubrique> L2R = L2.getLr();
			Vector<Rubrique> L3R = L3.getLr();
			// si la ligne 3 est vide :
			if (L3.vide() == true) {
				if (L2R.size() >= 1) {
					// on cherche une rubrique (sur L2) de type LG = 4 puis LG =
					// 8 ou sinon pas de mot-cl�
					flag = 0;
					int max = L2R.size();
					int i = 0;
					while (i < max && flag == 0) {
						// rubrique de type LG = 4
						if (L2R.elementAt(i).getLigne() == 4) {
							flag = 1;
							// ajouter cette rubrique � L4
							L4.ajouteRubrique( L2R.elementAt(i),true);
							// et supprimer cette rubrique � L2
							L2.supprimeRubrique(i);
							L2.setFlagRestructurationOn();
							L4.setFlagRestructurationOn();
						}
						i++;
					}

					i = 0;
					while (i < max && flag == 0) {
						// rubrique de type LG = 8
						if ( L2R.elementAt(i).getType() == 4) {
							flag = 1;
							// ajouter cette rubrique � L4
							L4.ajouteRubrique(L2R.elementAt(i),	true);
							// et supprimer cette rubrique � L2
							L2.supprimeRubrique(i);
							L2.setFlagRestructurationOn();
							L4.setFlagRestructurationOn();
						}
						i++;
					}
					i = 0;
					while (i < max && flag == 0) {
						// rubrique sans mot cl� de type LG = 2
						// Modif LD 19/10/05
						if ((L2R.elementAt(i).getType() >= 0 && L2R.elementAt(i).getLigne() != 2) || ( L2R.elementAt(i).getType() == 1)) {
							// Fin modif LD
							flag = 1;
							// ajouter cette rubrique � L4
							L4.ajouteRubrique(L2R.elementAt(i),	true);
							L2.supprimeRubrique(i);
							L2.setFlagRestructurationOn();
							L4.setFlagRestructurationOn();
						}
						i++;
					}
				}
			} else {
				// la ligne 3 n'est pas vide
				// on cherche une rubrique (sur L3) de type LG = 4 puis LG = 9
				// ou sinon pas de mot-cl�
				flag = 0;
				int max = L3R.size();
				int i = 0;
				while (i < max && flag == 0) {
					// rubrique de type LG = 4
					if (L3R.elementAt(i).getLigne() == 4) {
						flag = 1;
						// ajouter cette rubrique � L4
						L4.ajouteRubrique( L3R.elementAt(i), true);
						// et supprimer cette rubrique � L3
						L3.supprimeRubrique(i);
						L3.setFlagRestructurationOn();
						L4.setFlagRestructurationOn();
					}
					i++;
				}
				i = 0;
				while (i < max && flag == 0) {
					// rubrique de type LG = 9
					if ( L3R.elementAt(i).getType() == 2) {
						flag = 1;
						// ajouter cette rubrique � L4
						L4.ajouteRubrique(L3R.elementAt(i), true);
						// et supprimer cette rubrique � L3
						L3.supprimeRubrique(i);
						L3.setFlagRestructurationOn();
						L4.setFlagRestructurationOn();
					}
					i++;
				}
				i = 0;
				while (i < max && flag == 0) {
					// rubrique sans mot cl�
					if (L3R.elementAt(i).getType() == 1) {
						flag = 1;
						// ajouter cette rubrique � L4
						L4.ajouteRubrique(L3R.elementAt(i), true);
						L3.supprimeRubrique(i);
						L3.setFlagRestructurationOn();
						L4.setFlagRestructurationOn();
					}
					i++;
				}
				// on a rien trouv� en L3 on cherche en L2
				if (L2R.size() >= 1 && flag == 0) {
					i = 0;
					max = L2R.size();
					while (i < max && flag == 0) {
						// rubrique de type LG = 8
						if (L2R.elementAt(i).getType() == 4) {
							flag = 1;
							// ajouter cette rubrique � L4
							L4.ajouteRubrique( L2R.elementAt(i),true);
							// et supprimer cette rubrique � L2
							L2.supprimeRubrique(i);
							L2.setFlagRestructurationOn();
							L4.setFlagRestructurationOn();
						}
						i++;
					}
					i = 0;
					while (i < max && flag == 0) {
						// rubrique sans mot cl� de type LG = 2
						// Modif LD 19/10/05
						if ((L2R.elementAt(i).getType() >= 0 && L2R.elementAt(i).getLigne() != 2)
								|| ( L2R.elementAt(i).getType() == 1)) {
							flag = 1;
							// ajouter cette rubrique � L4
							L4.ajouteRubrique(L2R.elementAt(i),true);
							// pas de supp
						}
						i++;
					}
				}
			}
		}

		L4.controleSupp_9();
		L4.controleSupp_10();

		/*4.2.1.3.5 jpc*/
		/*si l3 ne contient que des nombres etc...*/
		if(L3.controleSupp_4()){
			if(L4.controleSupp_6()){
				Vector<Rubrique> vLigne = L3.getLr();
				String numeros = "";
				for(int i=0;i<vLigne.size();i=i+1){
					for(int j=0;j<vLigne.elementAt(i).getVmot().size();j=j+1){
						if(!"NUMERO".equals(vLigne.elementAt(i).getVmot().elementAt(j).getChaine())){
							numeros = numeros + vLigne.elementAt(i).getVmot().elementAt(j).getChaine() +" ";
						}
					}
				}
				L3 = new Ligne("",3);
				L4 = new Ligne(numeros+L4.getLigne(),4);
				L3.setFlagRestructurationOn();
				L4.setFlagRestructurationOn();
				this.gererSlashLigne4();
				L4.setAjoutErrorBin("23");
			}
		}

		if(L2.controleSupp_4()){
			if(L4.controleSupp_6()){
				Vector<Rubrique> vLigne = L2.getLr();
				String numeros = "";
				for(int i=0;i<vLigne.size();i=i+1){
					for(int j=0;j<vLigne.elementAt(i).getVmot().size();j=j+1){
						if(!"NUMERO".equals(vLigne.elementAt(i).getVmot().elementAt(j).getChaine())){
							numeros = numeros + vLigne.elementAt(i).getVmot().elementAt(j).getChaine() +" ";
						}
					}
				}
				L2 = new Ligne("",2);
				L4 = new Ligne(numeros+L4.getLigne(),4);
				L2.setFlagRestructurationOn();
				L4.setFlagRestructurationOn();
				gererSlashLigne4();
				L4.setAjoutErrorBin("23");
			}
		}
		
		L4.controleSupp_7();//on supprime le Mot NUMERO en ligne 4 (4.2.1.3.5)
		L2.controleSupp_8();
		L3.controleSupp_8();
		L2.controleSupp_2();
		L3.controleSupp_2();
		//on transforme les 2 E en 2E pour les lignes 2 et 3 ...
		//FAE ai3_010201_08_FAE
		L2.controleSupp_11();
		L3.controleSupp_11();

		/***********************************************************************
		 * Gestion des Abr�viations
		 **********************************************************************/
/*
System.out.println("J-F.D. test :");
if (L2.getLigne() != null) {
	System.out.println("L2 : "+L2.getLigne());
}
if (L3.getLigne() != null) {
	System.out.println("L3 : "+L3.getLigne());
}
if (L4.getLigne() != null) {
	System.out.println("L4 : "+L4.getLigne());
}*/
/*if (L5.getLigne() != null) {
	System.out.println("L5 : "+L5.getLigne());
}*/

		// Gestion des abr�viations
		// Ligne 5
		int retour1 = -100;
		if (L5.getLigne() != null) {
			if (L5.getLigne().length() > this.norme) {
				// System.out.println("BCT.traitement() : Ligne 5 trop longue");
				// on teste l'abr�viation
				if (L5.getLigneAb(norme).length() > this.norme) {
					if ((retour1 = traitementAbreviationLigne5()) == -2) {

						L5.setMsg();
						if (rubErreur == null)
							rubErreur = new Vector<String>();
						Vector<Rubrique> L_temp = L5.getLr();
						for (int i = 0; i < L_temp.size(); i++)
							rubErreur.add( L_temp.elementAt(i).getMotCle());
						//on continue le traitement meme si la longueur depasse la norme
						//retour = -2;
						
					}
				}
			}
		}

		// Ligne 4
		/*fae 1.3.0 32 1 supprimer les lignes concernant les ligne 4 a 7*/
		if (L4.getLigne() != null) {
			Vector<Rubrique> rubLigne4 = L4.getLr();
			for(int j=0;j<rubLigne4.size();j=j+1){
				Vector<Mot> vmot = rubLigne4.elementAt(j).getVmot();
				String ligneMots = "";
				for(int k=0;k<vmot.size();k=k+1){
					if(ligneMots.length()>0){
						ligneMots = ligneMots + " ";
					}
					ligneMots = ligneMots + vmot.elementAt(k).getChaine();
				}
				Ligne ligneTmp = new Ligne(ligneMots,4);
				if (ligneTmp.getLigne().length() > this.norme) {
					// on teste l'abr�viation
					if (ligneTmp.getLigneAb(norme).length() > this.norme) {
						if ((retour1 = traitementAbreviationLigne4()) == -2) {
							L4.setMsg();
							if (rubErreur == null)
								rubErreur = new Vector<String>();
							Vector<Rubrique> L_temp = ligneTmp.getLr();
							for (int i = 0; i < L_temp.size(); i++){
								rubErreur.add( L_temp.elementAt(i).getMotCle());
							}
							retour = -2;
							break;
						}
					}
				}
			}
		}
		
		// Ligne 3
		if (L3.getLigne() != null) {
			if (L3.getLigne().length() > this.norme) {
				// on teste l'abr�viation
				if (L3.getLigneAb(norme).length() > this.norme) {
					if ((retour1 = traitementAbreviationLigne3()) == -2) {
						//MCR positionner le bit ligne trop longue
						L3.setAjoutErrorBin("30"); // d�passement de capacit�
//System.out.println("D�passement capacit� ligne 3");
						if (rubErreur == null)
							rubErreur = new Vector<String>();
						Vector<Rubrique> L_temp = L3.getLr();
						for (int i = 0; i < L_temp.size(); i++){
							rubErreur.add( L_temp.elementAt(i).getMotCle());
						}
						retour = -2;
					}
				}
			}
		}

		// Ligne 2
		// le comportement de la ligne 2 est diff�rent :
		// si la ligne 2 est trop longue, on essaie de redescendre des rubriques
		// en Ligne 3.
		if (L2.getLigne() != null) {
			if (L2.getLigneAb(norme).length() > this.norme) {
				// sauver L2
				L2.saveObjet();
				L3.saveObjet();
				Vector<Rubrique> L_temp = L2.deplacerRubriques(norme);
				if (L_temp != null) {
					L3.ajouteRubrique(L_temp, false);
					if (L3.getLigneAb(norme).length() > norme) {
						// impossible de redescendre en L3
						L2.restaureObjet();
						L3.restaureObjet();
						// MCR positionner le bit ligne trop longue
						L2.setAjoutErrorBin("30"); // d�passement capacit�
//System.out.println("D�passement capacit� ligne 2");
						L2.setMsg();
						if (rubErreur == null)
							rubErreur = new Vector<String>();
						Vector<Rubrique> Ltmp = L2.getLr();
						for (int i = 0; i < Ltmp.size(); i++){
							rubErreur.add(Ltmp.elementAt(i).getMotCle());
						}
						retour = -2;
					}
				} else {
					// impossible de redescendre en L3
					L2.restaureObjet();
					L3.restaureObjet();
					// MCR positionner le bit ligne trop longue
					L2.setAjoutErrorBin("30"); // d�passement de capacit�
//System.out.println("D�passement capacit� ligne 2 cas 2");
					L2.setMsg();

					if (rubErreur == null)
						rubErreur = new Vector<String>();
					Vector<Rubrique> Ltmp = L2.getLr();
					for (int i = 0; i < Ltmp.size(); i++){
						rubErreur.add( Ltmp.elementAt(i).getMotCle());
					}
					retour = -2;
				}
			}
		}

		//fae 1.4.1 07
		L2.controleSupLotissementPlusChiffre();
		L3.controleSupLotissementPlusChiffre();
		L4.controleSupLotissementPlusChiffre();
		L5.controleSupLotissementPlusChiffre();
		
		if (retour == -2) {
			s_error = -2;
		} else if ((L2.getMsg() != null) || (L3.getMsg() != null)
				|| (L4.getMsg() != null) || (L5.getMsg() != null)) {
			s_error = -1;
		} else {
			s_error = 0;
		}
		return s_error;
	}

	/**
	 * Abbr�viation, traitement ligne 5 Return values : "-2" , LA LIGNE EST TROP
	 * LONGUE : il n'y a aucune rubrique � enlever ou alors le nombre de
	 * rubriques � supprimer n'est pas suffisant. "-1" , LA LIGNE A ETE CORRIGEE :
	 * la ligne a �t� amput� d'une ou plusieurs rubriques, ces rubriques ont �t�
	 * ajout� � la Ligne 5
	 */
	private int traitementAbreviationLigne5() {
		int nbRub_R_type = 0;
		int indice = -1;
		Vector<Rubrique> L5R;
		Vector<Rubrique> L5R_type = new Vector<Rubrique>();

		L5R = L5.getLr();
		// calcul du nombre de rubrique de type R_type, puis r�cup�ration des
		// rubriques
		for (int i = 0; i < L5R.size(); i++) {
			if ( L5R.elementAt(i).getType() != 0) {
				nbRub_R_type++;
				indice = i;
				L5R_type.add(L5R.elementAt(i));
			}
		}

		// System.out.println("BCL.traitementAbreviationLigne5() : nbre rubrique
		// RType = " + nbRub_R_type);
		// test du nombre de rubriques et traitement
		// si nbRub_R_type == 0 alors la ligne est trop longue : aucun
		// traitement possible
		if (nbRub_R_type == 1) {
			// calculer la taille de la ligne 5 sans cette rubrique et voir si
			// la taille <= norme.
			if ((L5.getLigneAb(norme).length() - L5R_type.elementAt(0).getLongueurAb()) > this.norme) {
				// la ligne est trop longue
				return -2;
			} else {
				// System.out.println("BCL.traitementAbreviationLigne5() : 1
				// rubrique r_type");
				L5.supprimeRubrique(indice);
				// on ajoute la rubrique en ligne 4
				L4.ajouteRubrique( L5R_type.elementAt(0), true);
				return -1;
			}
		} else if (nbRub_R_type > 1) {
			// L5R_type contient les rubriques � effacer.
			Vector<Rubrique> V = new Vector<Rubrique>();
			for (int i = 0; i < nbRub_R_type; i++) // nbre de rubriques �
			// effacer
			{
				// tester si l'effacement de ces rubriques entraine une longueur
				// correcte
				int taille = 0;
				// System.out.println("BCL.traitementAbreviationLigne5() : i = "
				// + i);
				V.add(L5R_type.elementAt(i));
				for (int j = 0; j <= i; j++) {
					if (j == 0)
						taille +=  V.elementAt(j).getLongueurAb();
					else
						taille +=  V.elementAt(j).getLongueurAb() + 1;
				}
				// System.out.println("BCL.traitementAbreviationLigne5() :
				// taille = " + taille);
				if ((L5.getLigneAb(norme).length() - taille) <= norme) {
					// les rubriques enlev�es font pass� la taille � - de 38
					// caract�res
					// System.out.println("BCL.traitementAbreviationLigne5() :
					// suppression des rubriques");
					L5.supprimeRubrique(V);
					if (V != null) {
						// System.out.println("BCL.traitementAbreviationLigne5()
						// : ajout des rubriques dans L4");
						L4.ajouteRubrique(V, true); // cette methode doit
						// ajouter les rubriques
						// restantes
						return -1;
					} else
						return -2;
				}
			}
		}
		// sinon il n'y a rien � faire
		else
			return -2;
		return -2;
	}

	/**
	 * Abbr�viation, traitement ligne 4 Return values : "-2" , LA LIGNE EST TROP
	 * LONGUE : il n'y a aucune rubrique � enlever ou alors le nombre de
	 * rubriques � supprimer n'est pas suffisant. "-1" , LA LIGNE A ETE CORRIGEE :
	 * la ligne a �t� amput� d'une ou plusieurs rubriques, ces rubriques ont �t�
	 * ajout� � la Ligne 4
	 */
	private int traitementAbreviationLigne4() {
		int nbRub_R_type = 0;
		int indice = -1;
		Vector<Rubrique> L4R;
		Vector<Rubrique> L4R_type = new Vector<Rubrique>();

		L4R = L4.getLr();
		// calcul du nombre de rubrique de type R_type, puis r�cup�ration des
		// rubriques
		for (int i = 0; i < L4R.size(); i++) {
			if ( L4R.elementAt(i).getType() != 0) {
				nbRub_R_type++;
				indice = i;
				L4R_type.add(L4R.elementAt(i));
			}
		}

		// System.out.println("BCL.traitementAbreviationLigne4() : nbre rubrique
		// RType = " + nbRub_R_type);
		// test du nombre de rubriques et traitement
		// si nbRub_R_type == 0 alors la ligne est trop longue : aucun
		// traitement possible
		if (nbRub_R_type == 1) {
			// calculer la taille de la ligne 4 sans cette rubrique et voir si
			// la taille <= norme.
			if ((L4.getLigneAb(norme).length() -  L4R_type.elementAt(0).getLongueurAb()) > this.norme) {
				// la ligne est trop longue
				return -2;
			} else {
				// System.out.println("BCL.traitementAbreviationLigne4() : 1
				// rubrique r_type");
				L4.supprimeRubrique(indice);
				// on ajoute la rubrique en ligne 3
				L3.ajouteRubrique( L4R_type.elementAt(0), true);
				return -1;
			}
		} else if (nbRub_R_type > 1) {
			// L4R_type contient les rubriques � effacer.
			Vector<Rubrique> V = new Vector<Rubrique>();
			for (int i = 0; i < nbRub_R_type; i++) // nbre de rubriques �
			// effacer
			{
				// tester si l'effacement de ces rubriques entraine une longueur
				// correcte
				int taille = 0;
				// System.out.println("BCL.traitementAbreviationLigne4() : i = "
				// + i);
				V.add(L4R_type.elementAt(i));
				for (int j = 0; j <= i; j++) {
					if (j == 0){
						taille += V.elementAt(j).getLongueurAb();
					}else{
						taille += V.elementAt(j).getLongueurAb() + 1;
					}
				}
				// System.out.println("BCL.traitementAbreviationLigne4() :
				// taille = " + taille);
				if ((L4.getLigneAb(norme).length() - taille) <= norme) {
					// les rubriques enlev�es font pass� la taille � - de 38
					// caract�res
					// System.out.println("BCL.traitementAbreviationLigne4() :
					// suppression des rubriques");
					L4.supprimeRubrique(V);
					if (V != null) {
						// System.out.println("BCL.traitementAbreviationLigne4()
						// : ajout des rubriques dans L3");
						L3.ajouteRubrique(V, true); // cette methode doit
						// ajouter les rubriques
						// restantes
						return -1;
					} else
						return -2;
				}
			}
		} else
			return -2;
		return -2;
	}

	/**
	 * Abbr�viation, traitement ligne 3 Return values : "-2" , LA LIGNE EST TROP
	 * LONGUE : il n'y a aucune rubrique � enlever ou alors le nombre de
	 * rubriques � supprimer n'est pas suffisant. "-1" , LA LIGNE A ETE CORRIGEE :
	 * la ligne a �t� amput� d'une ou plusieurs rubriques, ces rubriques ont �t�
	 * ajout� � la Ligne 2
	 */
	private int traitementAbreviationLigne3() {
		int nbRub_R_type = 0;
		int indice = -1;
		Vector<Rubrique> L3R;
		Vector<Rubrique> L3R_type = new Vector<Rubrique>();

		L3R = L3.getLr();
		// calcul du nombre de rubrique de type R_type, puis r�cup�ration des
		// rubriques
		
		for (int i = 0; i < L3R.size(); i++) {
			if ( L3R.elementAt(i).getType() != 0) {
				nbRub_R_type++;
				indice = i;
				L3R_type.add(L3R.elementAt(i));
			}
		}

		// System.out.println("BCL.traitementAbreviationLigne3() : nbre rubrique
		// RType = " + nbRub_R_type);
		// test du nombre de rubriques et traitement
		// si nbRub_R_type == 0 alors la ligne est trop longue : aucun
		// traitement possible
		if (nbRub_R_type == 1) {
			// calculer la taille de la ligne 3 sans cette rubrique et voir si
			// la taille <= norme.
			if ((L3.getLigneAb(norme).length() -  L3R_type.elementAt(0).getLongueurAb()) > this.norme) {
				// la ligne est trop longue
				return -2;
			} else {
				//MCR 08082008
				//on ne deplace en L2 que si la L2 apres deplacement reste < norme
				int tailleL2 = 0;
				if (L2.getLigneAb(norme) != null) {
					tailleL2 = L2.getLigneAb(norme).length();
				}

				if ((tailleL2 +  L3R_type.elementAt(0).getLongueurAb()) > this.norme) {
					// pas de place en ligne 2
					return -2;

				} else {
					L3.supprimeRubrique(indice);
					// on ajoute la rubrique en ligne 2
					L2.ajouteRubrique( L3R_type.elementAt(0), true);
					return -1;
				}
			}
		} else if (nbRub_R_type > 1) {
			// L3R_type contient les rubriques � effacer.
			Vector<Rubrique> V = new Vector<Rubrique>();
			for (int i = 0; i < nbRub_R_type; i++) // nbre de rubriques �
			// effacer
			{
				// tester si l'effacement de ces rubriques entraine une longueur
				// correcte
				int taille = 0;
				V.add(L3R_type.elementAt(i));
				for (int j = 0; j <= i; j++) {
					if (j == 0)
						taille +=  V.elementAt(j).getLongueurAb();
					else
						taille +=  V.elementAt(j).getLongueurAb() + 1;
				}
				if ((L3.getLigneAb(norme).length() - taille) <= norme) {
					// les rubriques enlev�es font pass� la taille � - de 38
					// caract�res
					//MCR 08082008
					//on ne deplace en L2 que si la L2 apres deplacement reste < norme
					int tailleL2 = 0;
					if (L2.getLigneAb(norme) != null) {
						tailleL2 = L2.getLigneAb(norme).length();
					}
					if ((tailleL2 +  V.elementAt(0).getLongueurAb()) > this.norme) {
						// pas de place en ligne 2
						return -2;
					} else {
						L3.supprimeRubrique(V);
						if (V != null) {
							L2.ajouteRubrique(V, true); // cette methode doit
							// ajouter les rubriques
							// restantes
							return -1;
						} else
							return -2;
					}
				}
			}
		} else{
			return -2;
		}
		return -2;
	}
}
