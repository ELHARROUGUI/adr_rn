package restructuration.object;

import java.util.Collections;
import java.util.Comparator;
import java.util.Vector;

import restructuration.data.DataMotscles;
import restructuration.data.DataNormalise;
import restructuration.mascadia.TraitementAdresse;

/**
 * Classe permettant : <BR>
 * 1 - le stockage d'une ligne <BR>
 * 2 - de traiter cette ligne ( ponctuation, d�termination des rubriques, ... )
 * <BR>
 * 3 - de renvoyer des infos du traitement de cette ligne <BR>
 * <BR>
 * Pour cela, la classe ligne stocke les donn�es sous la forme d'un vecteur de
 * rubrique : Lr. <BR>
 * <BR>
 * Vu leur grand nombre, les m�thodes de cette classe sont class�es en 2
 * cat�gories : <BR> - niveau 1 : m�thode travaillant sur la variable "chaine"
 * (String) <BR> - niveau 2 : m�thode travaillant sur la variable "Lr" (Vecteur
 * de rubriques)
 * 
 * @see restructuration.object.Mot Mot
 * @see restructuration.object.Rubrique Rubrique
 */
public class Ligne {
	private String chaine; // chaine de caract�re repr�sentant la ligne

	// "numeroLigne".

	private String chaineMemo; // chaine de caract�re repr�sentant la ligne

	// "numeroLigne" non restructur�e.

	private int numeroLigne; // num�ro de la ligne.

	private Vector<Rubrique> Lr = null; // Vecteur de rubriques, chaque rubrique poss�de

	// un vecteur de mots

	private String Msg; // message d'erreur

	// MCR
	/**
	 * Liste des bits positionn�s sur la ligne lors du contr�le ligne
	 */
	private String errorBin = "";

	// variables de sauvegarde
	private String chaine_save;

	private String Msg_save;

	private Vector<Rubrique> Lr_save = null;
	
	private Vector<Rubrique> LrNonTriee = null;
	
	private boolean flagRestructuration = false;
	
	private boolean flagNumero = false;

	public boolean isFlagRestructuration(){
		return flagRestructuration;
	}
	
	public void setFlagRestructuration(boolean flag){
		this.flagRestructuration = flag;
	}
	
	public void setFlagRestructurationOn(){
		this.flagRestructuration = true;
	}

	public void setFlagNumeroOn(){
		this.flagNumero = true;
	}

	// Caract�res autoris�s
	//jpc on test sans (la methode trCharAllow n'etant pas utilis�e) 
	private String authChar_L2_L3 = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789/-";
	
	// � partir de la version Mascadia 02.02.00 la L4 peut contenir le /
	private String authChar_L4 = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789/-";
		
	private String authChar_L5 = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";

	/**
	 * Constructeur. Par d�faut il permet de <BR>
	 * 1 - traiter la ponctuation <BR>
	 * 2 - normaliser la chaine <BR>
	 * 3 - construit le vecteur Rubrique
	 * 
	 * @see restructuration.data.DataNormalise DataNormalise
	 */
	public Ligne(String a_chaine, int num) {
		numeroLigne = num;
		this.Msg = null;
		Lr = new Vector<Rubrique>();
		LrNonTriee = new Vector<Rubrique>();
		if (a_chaine == null){
			this.chaine = "";
		}else{
			this.chaine = a_chaine;
		}
		this.chaine = this.chaine.trim();
		this.chaineMemo = this.chaine;
		// Traitement de la ponctuation
		if (chaine != null && chaine.compareTo("") != 0) {
			this.chaine = TraitementAdresse.enlevePonctuation(this.chaine, 1);
			// On met la chaine en majuscule
			this.chaine = this.chaine.toUpperCase();
			// Normalisation de la ligne
			this.trCharAllow();
			// suppression des espaces crees par la methode trCharAllow
			this.chaine = this.chaine.trim();
			// Ligne saisie m�moris�e.
			chaineMemo = this.chaine;
			
			//fev 1.4.1 08
			if(num>=2 && num<=5){
				this.separeMotCleEtNombre();
			}
			
			if (this.chaine.compareTo("") != 0) {
				this.chaine = TraitementAdresse.supprimerEspaces(this.chaine.trim());
				chaineMemo = this.chaine;
				normaliseLigne();
				this.chaine = TraitementAdresse.supprimerEspaces(this.chaine.trim());
				construitVecteurRubrique();
			}
		}
	}

	/**
	 * fev 1.4.1 08
	 */
	private void separeMotCleEtNombre(){
		if(this.chaine!=null){
			boolean flagPostTraitement = false;
			String[] chaineSplitee = this.chaine.split(" ");
			for(int i=0;i<chaineSplitee.length;i=i+1){
				if(chaineSplitee[i].matches("[A-Z]+[0-9]+") || chaineSplitee[i].matches("[0-9]+[A-Z]+")){
					boolean startParUnNombre = false;

					if(chaineSplitee[i].substring(0,1).matches("[0-9]")){
						startParUnNombre = true;
					}
					String nombre = "";
					String motCle = "";
					for(int j=0;j<chaineSplitee[i].length();j=j+1){
						if(chaineSplitee[i].substring(j,j+1).matches("[0-9]")){
							nombre = nombre + chaineSplitee[i].substring(j,j+1);
						}else{
							motCle = motCle + chaineSplitee[i].substring(j,j+1);
						}
					}
					boolean flagMotCleTrouve = false;
					for(int j=0;j<DataMotscles.tab.length;j=j+1){
						if(motCle.equals(DataMotscles.tab[j][1])){
							flagMotCleTrouve = true;
							break;
						}
					}
					if(flagMotCleTrouve==true){
						flagPostTraitement = true;
						if(startParUnNombre==true){
							chaineSplitee[i] = nombre + " " + motCle;
						}else{
							chaineSplitee[i] = motCle + " " + nombre;
						}
					}
				}
			}
			if(flagPostTraitement==true){
				this.chaine = "";
				for(int i=0;i<chaineSplitee.length;i=i+1){
					this.chaine = this.chaine + " " + chaineSplitee[i];
				}
				this.chaine = this.chaine.trim();
			}
		}
	}
	
	/** ******************************************************************************************** */
	/* RUBRIQUES */
	/** ******************************************************************************************** */

	/**
	 * Niveau 2 : Controle suppl�mentaire (toutes les lignes). r�alis� avant la
	 * r�organisation des rubriques s'il existe une rubrique compos�e uniquement
	 * d'1 mot cl� autre que HLM, RDC, ZUP, ZAC, ZI, ZA, ZAD,CCAL,
	 * FOYER#LOGEMENT, CITE#HLM et qu'elle est en fin de ligne la rattacher � la
	 * rubrique qui pr�c�de.
	 */
	public void controleRubrique() {
		String tab[] = { "HLM", "RDC", "ZUP", "ZAC", "ZI", "ZA", "ZAD", "CCAL",	"FOYER#LOGEMENT", "CITE#HLM" };

		if (Lr.size() >= 2) {
			int i = Lr.size() - 1;
			Vector<Mot> V = ((Rubrique) Lr.elementAt(i)).getVmot();
			if ((V.size() == 1) && (((Mot) V.elementAt(0)).getType() == 'C')){
				// 1 seul mot dans la rubrique et c'est un mot cl�
				// rattacher ce mot � la rubrique pr�c�dente.
				int flag = 0;
				for (int j = 0; j < tab.length; j++) {
					if (((Mot) V.elementAt(0)).getChaine().compareTo(tab[j]) == 0){
						flag = 1;
					}
				}
				
				//cas ou le mot n'est pas de la meme ligne quue la rubrique precedente
				if(V.elementAt(0).getLigne()!=Lr.elementAt(i-1).getVmot().elementAt(0).getLigne()){
					flag = 1;
				}
				
				if (flag == 0) {
					this.ajouteMot(((Mot) V.elementAt(0)).getChaine(), i - 1,-1);
					Lr.remove(i);
				}
			}
		}
	}

	/**
	 * Niveau 1 et 2 : Construction du vecteur de rubrique Lr. La construction
	 * se fait � partir de L.
	 */
	public void construitVecteurRubrique() {
		Vector<Mot> L = new Vector<Mot>();
		StringBuffer sb = new StringBuffer(0);
		Vector<Mot> L_temp = new Vector<Mot>();
		int p;
		int pprec = -1;
		//decoupage de la chaine
		String[] chaines = this.chaine.split(" ");
		for(int i=0;i<chaines.length;i=i+1){
			//traitement des numero + extensions coll�s
			String regex = "^([0-9]+)[/-]*([A-Z]+)$";/*ai3_010201_03_FEV.doc*/
			java.util.regex.Matcher matcher = java.util.regex.Pattern.compile(regex).matcher(chaines[i]);
			if (matcher.find()){
				String mot1 = matcher.group(1);
				String mot2 = matcher.group(2);
				if(mot2.length()==1 || ("BIS").equals(mot2) || ("TER").equals(mot2) || ("QUARTER").equals(mot2) || ("QUINQUIES").equals(mot2)){
					L.add(new Mot(mot1));
					L.add(new Mot(mot2));
				}else{
					L.add(new Mot(mot1+mot2));
				}
			}else{
				//ai3_010201_03_FEV.doc
				regex = "^[0-9]+[/-]+[0-9]+$";
				matcher = java.util.regex.Pattern.compile(regex).matcher(chaines[i]);
				if(!matcher.find() && (chaines[i].indexOf("-")>-1 || chaines[i].indexOf("/")>-1 )){
					chaines[i] = chaines[i].replaceAll("[/-]+"," ");
					String[] chaineBis = chaines[i].split(" ");
					for(int j=0;j<chaineBis.length;j=j+1){
						if(chaineBis[j].trim().length()!=0){
							L.add(new Mot(chaineBis[j]));
						}
					}
				}else if(chaines[i].trim().length()!=0){
					L.add(new Mot(chaines[i]));
				}
				//ai3_010201_03_FEV.doc
			}
		}
		
//une fois le decoupage effectu�, on regarde si on a un vecteur rempli (il peut arriv� qu'avec une chaine "-", on ai un vecteur vide
//si le vecteur est vide, on sort , et ainsi, on a pas de rubrique vide
		if(L.size()==0){
			return;
		}

		// changement du poids des mots cl�s si il y en a un identique (pour 4.2.1.3.8)
		// solution pour avoir deux rubriques avec le meme mot cle ...
		// a voir si c'est valide par rapport a "l'esprit" de l'appli
		java.util.Hashtable<Integer,Integer> tablePoids = new java.util.Hashtable<Integer,Integer>();
		for(int i=0;i<L.size();i=i+1){
			if(L.elementAt(i).getPoids()>0){
				int poids = L.elementAt(i).getPoids();
				while(tablePoids.get(Integer.valueOf(poids))!=null){
					poids = poids + 1;
				}
				L.elementAt(i).setPoids(poids);
				tablePoids.put(Integer.valueOf(poids),Integer.valueOf(poids));
			}
		}


		// Algo BP. L est rempli. (idem TSA)
		for (int i = 0; i < L.size(); i++) {
			if ( L.elementAt(i).getChaine().compareTo("BP") == 0 || L.elementAt(i).getChaine().compareTo("TSA") == 0) {
				// s'il n'est pas suivi d'un nombre ou de "NUMERO", c'est un mot
				// simple.
				if (i != (L.size() - 1) && this.numeroLigne!=5) {
					if ( L.elementAt(i + 1).getType() != 'N'&& L.elementAt(i + 1).getChaine().compareTo("NUMERO") != 0) {
						L.elementAt(i).setType('M');
					}
				}else{
					L.elementAt(i).setType('C');
					L.elementAt(i).setLigne(this.numeroLigne);
				}
			}
			
			// rajout le 01/09/2019 : un lieu dit contient un mot cle ligne 4 
			//si ce dernier a �t� saisi en ligne 5 et si le mot qui le precede est de type mot alors on considere que c'est une information ligne 5
			// am�lioration : s'assurer qu'il existe une rubrique ligne 4  
			if ( L.elementAt(i).getChaine().compareTo("BOIS") == 0) {
				//if (i != (L.size() - 1) && this.numeroLigne==5) {
				if (i > 0  && this.numeroLigne==5) {
					if ( L.elementAt(i - 1).getType() == 'M') {
						L.elementAt(i).setType('M');
						L.elementAt(i).setLigne(this.numeroLigne);
					}
				}
				
			}
			
		}

		// algo de l'article 'L'
		StringBuffer monsb = new StringBuffer(0);
		monsb = new StringBuffer(0);
		for (int i = 0; i < L.size(); i++) {
			if (((((Mot) L.elementAt(i)).getChaineIE().compareTo("L") == 0) || (((Mot) L.elementAt(i)).getChaineIE().compareTo("L'") == 0))
					&& (((Mot) L.elementAt(i)).getType() == 'L')) {
				if (i != 0) {
					// l'article n'est pas en d�but de ligne
					if (((Mot) L.elementAt(i - 1)).getChaineIE().compareTo("DE") != 0) {
						// l'article ne suit pas "DE"
						if ((((Mot) L.elementAt(i - 1)).getType() != 'C')
								|| ((((Mot) L.elementAt(i - 1)).getType() == 'C') && (((Mot) L.elementAt(i - 1)).getChaineIE().compareTo("BATIMENT") == 0))) {
							// l'article n'est pas suivi d'un mot cl�
							((Mot) L.elementAt(i)).setType('M');
						}
					}
				}
			}
			monsb.append(((Mot) L.elementAt(i)).getType());
		}
		// System.out.println("Ligne.construitVecteurRubrique() : apres algo L
		// --> " + monsb.toString());

		// determine le poids de tous les mots du vecteur L
		int flagMC = 0; // indique qu'un mot cl� a d�j� �t� trait�
		int indice = -1; // indice du dernier mot-cl� trait�
		for (int i = 0; i < L.size(); i++) {
			// si c'est un mot cl�
			if (L.elementAt(i).getType() == 'C') {
				flagMC = 1;
				indice = i;
				
				if(/*exception "BP" et "TSA" far 02.04.00_23*/L.elementAt(i).getChaine().compareTo("BP")!=0 && L.elementAt(i).getChaine().compareTo("TSA")!=0){
					if (i == 1
							&& ( L.elementAt(i - 1).getType() == 'N' || L.elementAt(i - 1).getType() == 'I'	|| L.elementAt(i - 1).getType() == 'A'
							|| L.elementAt(i - 1).getType() == 'L' || ( L.elementAt(i - 1).getType() == 'M' && (L.elementAt(i - 1).getChaine().charAt(0) >= '0' && L.elementAt(i - 1).getChaine().charAt(0) <= '9')))) {
						L.elementAt(i - 1).setPoids( L.elementAt(i).getPoids());
						L.elementAt(i - 1).setLigne( L.elementAt(i).getLigne());
					} else if ((i == 2)
							&& L.elementAt(i - 2).getChaineIE().compareTo("NUMERO") == 0
							&& L.elementAt(i - 1).getType() == 'N') {
						L.elementAt(i - 1).setPoids(L.elementAt(i).getPoids());
						L.elementAt(i - 1).setLigne(L.elementAt(i).getLigne());
						L.elementAt(i - 2).setPoids(L.elementAt(i).getPoids());
						L.elementAt(i - 2).setLigne(L.elementAt(i).getLigne());
					} else if ((i > 1)
							&& L.elementAt(i - 1).getType() == 'N'
							&& ( L.elementAt(i - 2).getType() == 'N' || L.elementAt(i - 2).getType() == 'M' 
								|| L.elementAt(i - 2).getType() == 'A')) {//ajout pour la fae 1.3.1 13
						L.elementAt(i - 1).setPoids(L.elementAt(i).getPoids());
						L.elementAt(i - 1).setLigne(L.elementAt(i).getLigne());
						/*((Mot) L.elementAt(i - 2)).setPoids(((Mot) L.elementAt(i)).getPoids());//far 020400_22
						((Mot) L.elementAt(i - 2)).setLigne(((Mot) L.elementAt(i)).getLigne());*/
					} else if (i > 2
							&& L.elementAt(i - 1).getType() == 'N'
							&& L.elementAt(i - 2).getChaineIE().compareTo("NUMERO") == 0
							&& (L.elementAt(i - 3).getType() == 'M' || L.elementAt(i - 3).getType() == 'N')) {
						L.elementAt(i - 1).setPoids(L.elementAt(i).getPoids());
						L.elementAt(i - 1).setLigne(L.elementAt(i).getLigne());
						L.elementAt(i - 2).setPoids(L.elementAt(i).getPoids());
						L.elementAt(i - 2).setLigne(L.elementAt(i).getLigne());
					} else if (i > 0
							&& (L.elementAt(i - 1).getType() == 'A' || L.elementAt(i - 1).getType() == 'L')) {
						L.elementAt(i).setPoids(L.elementAt(i - 1).getPoids());
						L.elementAt(i).setLigne(L.elementAt(i - 1).getLigne());
						L.elementAt(i).setType('M');
					} else if ((i > 1)
							&& ((L.elementAt(i - 1).getType() == 'N' || L.elementAt(i - 1).getType() == 'I') && L.elementAt(i - 2).getType() == 'L')) {
						L.elementAt(i).setPoids(L.elementAt(i - 1).getPoids());
						L.elementAt(i).setLigne(L.elementAt(i - 1).getLigne());
						L.elementAt(i).setType('M');
					} else if ((i > 0)
							&& L.elementAt(i - 1).getType() == 'I') {
						L.elementAt(i - 1).setPoids(L.elementAt(i).getPoids());
						L.elementAt(i - 1).setLigne(L.elementAt(i).getLigne());
					} else if (i > 1
							&& (L.elementAt(i - 1).getType() == 'M' && L.elementAt(i - 1).getChaine().length() == 1)
							&& L.elementAt(i - 2).getType() == 'N') {
						L.elementAt(i - 1).setPoids(L.elementAt(i).getPoids());
						L.elementAt(i - 1).setLigne(L.elementAt(i).getLigne());
						L.elementAt(i - 2).setPoids(L.elementAt(i).getPoids());
						L.elementAt(i - 2).setLigne(L.elementAt(i).getLigne());
					} else if (i == 2
							&& L.elementAt(i - 1).getType() == 'E'
							&& L.elementAt(i - 2).getType() == 'N') {
						L.elementAt(i - 1).setPoids(L.elementAt(i).getPoids());
						L.elementAt(i - 1).setLigne(L.elementAt(i).getLigne());
						L.elementAt(i - 2).setPoids(L.elementAt(i).getPoids());
						L.elementAt(i - 2).setLigne(L.elementAt(i).getLigne());
					}else if(i>0 && (L.elementAt(i - 1).getType() == 'F' || L.elementAt(i - 1).getType() == 'C')){//gestion de commissariat (type F) central (type C) , sauf pour les mots cles ligne 2 et 3
						if(((L.elementAt(i-1).getLigne()==2 || L.elementAt(i-1).getLigne()==8) && L.elementAt(i).getLigne()!=2 && L.elementAt(i).getLigne()!=8)
							|| ((L.elementAt(i-1).getLigne()==3 || L.elementAt(i-1).getLigne()==9) && L.elementAt(i).getLigne()!=3 && L.elementAt(i).getLigne()!=9)
							|| (L.elementAt(i-1).getLigne()!=2 && L.elementAt(i-1).getLigne()!=3 && L.elementAt(i-1).getLigne()!=8 && L.elementAt(i-1).getLigne()!=9)){
							L.elementAt(i).setPoids(L.elementAt(i - 1).getPoids());
							L.elementAt(i).setLigne(L.elementAt(i - 1).getLigne());
							L.elementAt(i).setType('M');
							//fae 1.4.1 13
							L.elementAt(i).setChaineIE(L.elementAt(i).getChaine());
						}
					}
				}
			// ce n'est pas un mot cl�
			}else {
				if (i >= 1 && L.elementAt(i).getType() == 'N'
						&& L.elementAt(i - 1).getType() == 'C') {
					L.elementAt(i).setPoids(L.elementAt(i - 1).getPoids());
					L.elementAt(i).setLigne(L.elementAt(i - 1).getLigne());
					flagMC = 0;//fae 1.3.1 5
				} else if (flagMC == 1) {
						L.elementAt(i).setPoids(L.elementAt(indice).getPoids());
						L.elementAt(i).setLigne(L.elementAt(indice).getLigne());
				} else {
					L.elementAt(i).setPoids(0);
					L.elementAt(i).setLigne(this.numeroLigne);
				}
			}
		}
		// Regroupement de mot-cl�s

		for (int i = 0; i < L.size() - 1; i++) {
			// Le mot est un mot cl�
			if (((Mot) L.elementAt(i)).getType() == 'C') {
				// il doit appartenir � certains mots
				if ((((Mot) L.elementAt(i)).getChaineIE().compareTo("RESIDENCE") == 0)
						|| (((Mot) L.elementAt(i)).getChaineIE().compareTo("PARC") == 0)
						|| (((Mot) L.elementAt(i)).getChaineIE().compareTo("LOGIS") == 0)
						|| (((Mot) L.elementAt(i)).getChaineIE().compareTo("LOTISSEMENT") == 0)
						|| (((Mot) L.elementAt(i)).getChaineIE().compareTo("CITE") == 0)
						|| (((Mot) L.elementAt(i)).getChaineIE().compareTo("QUARTIER") == 0)
						|| (((Mot) L.elementAt(i)).getChaineIE().compareTo("CLOS") == 0)) {
					// il doit etre suivi d'un mot cl� de L3
					for (int j = 0; j < DataMotscles.tab.length; j++) {
						if (j != (DataMotscles.tab.length - 1)) {
							// suivi d'un mot cl� de L3 (ou L9)
							if (((DataMotscles.tab[j + 1][4].compareTo("9") == 0)
									|| (DataMotscles.tab[j + 1][4].compareTo("3") == 0)
									|| (DataMotscles.tab[j + 1][4].compareTo("2") == 0) || (DataMotscles.tab[j + 1][4].compareTo("8") == 0))
									&& (((Mot) L.elementAt(i + 1)).getChaineIE().compareTo(DataMotscles.tab[j + 1][2]) == 0)) {
								// il faut rattacher tous les mots qui suivent
								// dont le poids est
								// ((Mot)L.elementAt(i+1)).getPoids()
								int k = i + 1;
								int pds = ((Mot) L.elementAt(i + 1)).getPoids();
								while (k < L.size()) {
									if (((Mot) L.elementAt(k)).getPoids() == pds) {
										((Mot) L.elementAt(k)).setType('M');
										((Mot) L.elementAt(k)).setPoids(((Mot) L.elementAt(i)).getPoids());
									}
									k++;
								}
							}
						}
					}
				}
			}
		}

		// construction de Lr, construction du vecteur Rubrique
		// Lr = new Vector();

		for (int i = 0; i < L.size(); i++) {
			p = L.elementAt(i).getPoids();
			if (pprec == -1) {
				pprec = p;
				L_temp.add(L.elementAt(i));
			} else if (pprec != p) {
				pprec = p;
				Lr.add(new Rubrique(L_temp, this.numeroLigne));
				L_temp.clear();
				L_temp.add(L.elementAt(i));
			} else {
				L_temp.add(L.elementAt(i));
			}
		}
		// cr�er la derni�re rubrique
		Lr.add(new Rubrique(L_temp, this.numeroLigne));

		// algo '/' et '-'
		if (Lr.size() >= 1) {
			Vector<Mot> Vmot = ((Rubrique) Lr.elementAt(0)).getVmot();
			if (Vmot.size() >= 2) {
				int index1;
				int index2;
				// Vector Vmot = ((Rubrique)Lr.elementAt(0)).getVmot();
				if ((((Rubrique) Lr.elementAt(0)).getLigne() == 4)
						|| (((Mot) Vmot.elementAt(1)).getChaine().compareTo("R") == 0)) {
					Mot Mot1 = (Mot) Vmot.elementAt(0);
					Mot Mot2 = (Mot) Vmot.elementAt(1);
					if (((Mot1.getType() == 'M') && (Mot2.getType() == 'C'))
							|| (Mot2.getChaine().compareTo("R") == 0)) {
						index1 = Mot1.getChaine().indexOf("/");
						index2 = Mot1.getChaine().indexOf("-");
						if ((index1 != -1) && (index1 != 0)
								&& (index1 != Mot1.getChaine().length() - 1)) {
							// mot_gauche
							String gauche = Mot1.getChaine().substring(0,
									index1);
							Mot m_gauche = new Mot(gauche);
							String droite = Mot1.getChaine().substring(
									index1 + 1, Mot1.getChaine().length());
							Mot m_droite = new Mot(droite);
							if ((m_droite.getType() == 'N')
									&& (m_gauche.getType() == 'N')) {
								// affecter poids et ligne
								m_gauche.setLigne(4);
								m_droite.setLigne(4);
								m_droite.setPoids(Mot2.getPoids());
								m_gauche.setPoids(Mot2.getPoids());
								Vmot.removeElementAt(0);
								Vmot.add(0, m_droite);
								Vmot.add(0, m_gauche);
							}
						} else if ((index2 != -1) && (index2 != 0)
								&& (index2 != Mot1.getChaine().length() - 1)) {
							// mot_droite
							String gauche = Mot1.getChaine().substring(0,index2);
							Mot m_gauche = new Mot(gauche);
							String droite = Mot1.getChaine().substring(index2 + 1, Mot1.getChaine().length());
							Mot m_droite = new Mot(droite);
							if ((m_droite.getType() == 'N')
									&& (m_gauche.getType() == 'N')) {
								// affecter poids et ligne
								m_gauche.setLigne(4);
								m_droite.setLigne(4);
								m_droite.setPoids(Mot2.getPoids());
								m_gauche.setPoids(Mot2.getPoids());
								Vmot.removeElementAt(0);
								Vmot.add(0, m_droite);
								Vmot.add(0, m_gauche);
							}
						}
					}
				}
			}
		}
		
		// Algo mot "R"
		for (int i = 0; i < Lr.size(); i++) {
			if (((Rubrique) Lr.elementAt(i)).getType() == 1) {
				// rubrique sans mot-cl�
				Vector<Mot> Vmot = ((Rubrique) Lr.elementAt(i)).getVmot();
				for (int j = 0; j < Vmot.size(); j++) {
					if (Vmot.elementAt(j).getChaine().compareTo("R") == 0) {

						// s'il est pr�c�d� d'une extension et d'un nombre
						boolean condition1 = false;
						if (j > 1) {
							if (Vmot.elementAt(j - 1).getType() == 'E'
									&& Vmot.elementAt(j - 2).getType() == 'N'
									&& j == 2) {
								condition1 = true;
							}
						}
						// System.out.println("Ligne.construitVecteurRubrique()
						// : ALGO RUE ");
						// s'il est pr�c�d� d'un nombre
						boolean condition2 = (j > 0)
								&& Vmot.elementAt(j - 1).getType() == 'N'
								&& j == 1;
						// ou s'il est pr�c�d� d'une lettre et d'un nombre
						boolean condition3 = j > 1
								&& Vmot.elementAt(j - 1).getType() == 'M'
								&& Vmot.elementAt(j - 1).getChaine().length() == 1
								&& Vmot.elementAt(j - 2).getType() == 'N'
								&& j == 2;
						// ou qu'il est pr�c�d� de 2 nombre en d�but de ligne
						boolean condition4 = j > 1
								&& Vmot.elementAt(j - 2).getType() == 'N'
								&& Vmot.elementAt(j - 1).getType() == 'N'
								&& j == 2;
						// ou s'il est en d�but de ligne
						if (j == 0 || (condition1 == true)
								|| (condition2 == true) || (condition3 == true)
								|| (condition4 == true)) {
							// le poids et la ligne de tous les mots du vecteur
							// changent
							Vmot.removeElementAt(j);
							Vmot.add(j, new Mot("RUE"));
							int pds = ((Mot) Vmot.elementAt(j)).getPoids();
							int lgn = ((Mot) Vmot.elementAt(j)).getLigne();
							for (int l = 0; l < Vmot.size(); l++) {
								((Mot) Vmot.elementAt(l)).setLigne(lgn);
								((Mot) Vmot.elementAt(l)).setPoids(pds);
							}
							Rubrique rub = new Rubrique(Vmot, this.numeroLigne);
							Lr.removeElementAt(i);
							Lr.add(i, rub);
						}
					}
				}
			}
		}

		// Algo mot "LD"
		for (int i = 0; i < Lr.size(); i++) {
			// recup du vecteur de mot.
			Vector<Mot> Vmot = ((Rubrique) Lr.elementAt(i)).getVmot();
			// s'il n'y a qu'un mot dans la rubrique et c'est LD
			if ((Vmot.size() == 1)
					&& (((Mot) Vmot.elementAt(0)).getChaine().compareTo("LD") == 0)) {
				// System.out.println("Ligne.construitVecteurRubrique() : ALGO
				// LD ");
				// s'il y a une rubrique apres.
				if (i != (Lr.size() - 1)) {
					int pds = ((Mot) Vmot.elementAt(0)).getPoids();
					int lgn = ((Mot) Vmot.elementAt(0)).getLigne();
					// rattacher la rubrique � la rubrique en cours.
					Vector<Mot> Vmot_suiv = ((Rubrique) Lr.elementAt(i + 1)).getVmot();
					for (int j = 0; j < Vmot_suiv.size(); j++) {
						// recup du poids et de la ligne. s'il y a un mot cl� il
						// perd sa valeur de mot cl�
						if (((Mot) Vmot_suiv.elementAt(j)).getType() == 'C'){
							((Mot) Vmot_suiv.elementAt(j)).setType('M');
						}
						((Mot) Vmot_suiv.elementAt(j)).setPoids(pds);
						((Mot) Vmot_suiv.elementAt(j)).setLigne(lgn);
						Vmot.add(Vmot_suiv.elementAt(j));
					}
					// suppression de l'�l�ment suivant de Lr
					Lr.removeElementAt(i + 1);
				}
			}
		}
		
		//gestion des nombres en ligne 4
		if (Lr.size() >= 2) {
			//MCR 10082007 si L4 et la 1ere rubrique est compos�e de +sieurs nombre
			//on ne conserve que le  1er qui est ensuite rattach�e au motcl� de la 2eme rubrique
			//en remplacant tout eventuel numero devant le mot-cl�
			boolean typeN = true;
			Vector<Mot> Vmot = Lr.elementAt(0).getVmot();
			if (Lr.elementAt(1).getLigne() == 4 || Lr.elementAt(1).getLigne()==8 || Lr.elementAt(1).getLigne()==9) {
				//parcourir tous les mots pour voir si ce sont des num�ros
				for (int i = 0; i < Vmot.size(); i++) {
					if (Vmot.elementAt(i).getType() != 'N' && Vmot.elementAt(i).getType() != 'E' && Vmot.elementAt(i).getType() != 'I') {
						typeN = false;
					}
				}
			}else{
				typeN = false;
			}

			if (typeN){
				if (Lr.elementAt(1).getLigne()==4 || Lr.elementAt(1).getLigne()==8 || Lr.elementAt(1).getLigne()==9) {
					Vector<Mot> V1 = ((Rubrique) Lr.elementAt(1)).getVmot();
					if (V1.size() > 0) {
						// ajouter a la 1ere rubrique
						while(Vmot.size()>0){
							if(Vmot.elementAt(Vmot.size()-1).getType()=='N'){
								Vmot.elementAt(Vmot.size()-1).setLigne(4);
								Vmot.elementAt(Vmot.size()-1).setPoids(((Mot) V1.elementAt(0)).getPoids());
								V1.add(0, (Mot) Vmot.elementAt(Vmot.size()-1));
								Vmot.removeElementAt(Vmot.size()-1);
							}else if(((Vmot.elementAt(Vmot.size()-1).getType()=='E') || (Vmot.elementAt(Vmot.size()-1).getType()=='I')) && Vmot.size()>1
									&& Vmot.elementAt(Vmot.size()-2).getType()=='N'){
								Vmot.elementAt(Vmot.size()-1).setLigne(4);
								Vmot.elementAt(Vmot.size()-1).setPoids(((Mot) V1.elementAt(0)).getPoids());
								V1.add(0, (Mot) Vmot.elementAt(Vmot.size()-1));
								Vmot.removeElementAt(Vmot.size()-1);
								Vmot.elementAt(Vmot.size()-1).setLigne(4);
								Vmot.elementAt(Vmot.size()-1).setPoids(((Mot) V1.elementAt(0)).getPoids());
								V1.add(0, (Mot) Vmot.elementAt(Vmot.size()-1));
								Vmot.removeElementAt(Vmot.size()-1);
							}else{
								Vmot.removeElementAt(Vmot.size()-1);
							}
						}
						Lr.removeElementAt(0);
					}
				}
			}
		} else if (Lr.size() == 1) {
			int indexCar1;
			int indexCar2;
			Vector<Mot> V = ((Rubrique) Lr.elementAt(0)).getVmot();
			if (V.size() > 2) {
				if ((((Mot) V.elementAt(0)).getType() == 'N')
						&& (((Mot) V.elementAt(1)).getType() == 'N')
						&& (((Mot) V.elementAt(2)).getType() == 'C')) {
					if (((Rubrique) Lr.elementAt(0)).getLigne() == 4) {
						//V.removeElementAt(1);
						return;
					}
				}
			}
		}

		//fae 01.05.00 ??
		this.recalculNumeroLigneSurLesMots();
		
		if(this.numeroLigne>=2 && this.numeroLigne<=5){
			this.controleRubrique();
		}
		// MCR 12072007 Tri sur positions apres construction du vecteur
		this.LrNonTriee = new Vector<Rubrique>(this.Lr);
		triVecteurRubrique();
	}

	private void recalculNumeroLigneSurLesMots(){
		if(this.numeroLigne<2 || this.numeroLigne>5){
			return;
		}
		
		
		for(int i=0;i<Lr.size();i=i+1){
			int numeroDuMotCle = -1;
			Vector<Mot> mots = Lr.elementAt(i).getVmot();
			for(int j=0;j<mots.size();j=j+1){
				if(numeroDuMotCle==-1 && mots.elementAt(j).getType()=='C'){
					numeroDuMotCle = mots.elementAt(j).getLigne();
					break;
				}
			}
			if(numeroDuMotCle>0){
				for(int j=0;j<mots.size();j=j+1){
					mots.elementAt(j).setLigne(numeroDuMotCle);
				}
			}
		}
		
	}
	
	/**
	 * Niveau 2 : Ajoute une rubrique au vecteur Lr et positionne le message
	 * signifiant qu'une rubrique a �t� d�plac�e sur cette ligne.
	 * 
	 * @param R
	 * @param type
	 *            <BR>
	 *            true : il s'agit d'une rubrique d�plac�e : r�organisation
	 *            (donc message) <BR>
	 *            false : il s'agit d'une rubrique juste ajout� (pas de message)
	 */
	public void ajouteRubrique(Rubrique R, boolean type) {
		Lr.add(0, R);
		LrNonTriee.add(0, R);
		R.setMsg(this.numeroLigne, type);
		triVecteurRubrique();
	}

	/** jpc pour ajouter a la fin
	 * Niveau 2 : Ajoute une rubrique au vecteur Lr et positionne le message
	 * signifiant qu'une rubrique a �t� d�plac�e sur cette ligne.
	 * 
	 * @param R
	 * @param type
	 *            <BR>
	 *            true : il s'agit d'une rubrique d�plac�e : r�organisation
	 *            (donc message) <BR>
	 *            false : il s'agit d'une rubrique juste ajout� (pas de message)
	 */
	public void ajouteRubriqueALaFin(Rubrique R, boolean type) {
		Lr.add(R);
		LrNonTriee.add(R);
		R.setMsg(this.numeroLigne, type);
		triVecteurRubrique();
	}
	
	/**
	 * Niveau 2 : Ajoute les rubriques contenues dans V au vecteur Lr
	 * 
	 * @param V
	 *            vecteur contenant les rubriques � ajouter.
	 * @param type
	 *            <BR>
	 *            true : il s'agit d'une rubrique d�plac�e : r�organisation <BR>
	 *            false : il s'agit d'une rubrique juste ajout�
	 */
	public void ajouteRubrique(Vector<Rubrique> V, boolean type) {
		int i;
		for (i = 0; i < V.size(); i++) {
			// mise � jour de Lr
			Lr.add(Lr.size(), (Rubrique) V.elementAt(i));
			LrNonTriee.add(LrNonTriee.size(), (Rubrique) V.elementAt(i));
			((Rubrique) V.elementAt(i)).setMsg(this.numeroLigne, type);
		}
		triVecteurRubrique();
	}

	/**
	 * Niveau 2 : ajoute le mot pass� en param�tre. x est la nieme rubrique de
	 * la ligne et y est le nieme mot du vecteur Vmot de la rubrique. Si y = -1,
	 * le mot est ajout� � la fin du vecteur de mots. Le mot est ajout� avant
	 * une r�organisation : on ne positionne pas de message.
	 */
	public void ajouteMot(String word, int x, int y) {
		Vector<Mot> V_mot = ((Rubrique) Lr.elementAt(x)).getVmot();
		if (y == -1)
			V_mot.add(new Mot(word));
		else
			V_mot.add(y, new Mot(word));
	}

	/**
	 * Niveau 2 : Supprime la rubrique dont l'indice est pass� en param�tre
	 * 
	 * @param indice
	 *            Indice de la rubrique a supprimer
	 */
	public void supprimeRubrique(int indice) {
		// suppression de Lr.
		Lr.removeElementAt(indice);
		LrNonTriee.removeElementAt(indice);
	}

	/**
	 * Niveau 2 : Supprime les rubriques contenues dans le vecteur V
	 * 
	 * @param V
	 *            <BR>
	 *            true : la chaine est reconstruite avec le libell� in extenso
	 *            <BR>
	 *            false : la chaine est reconstruite avec le libell� abr�g�
	 */
	public void supprimeRubrique(Vector<Rubrique> V) {
		int k = 0;
		int indice[] = new int[100];
		for (int i = 0; i < V.size(); i++) {
			for (int j = 0; j < Lr.size(); j++) {
				if (Lr.elementAt(j).equals( V.elementAt(i))) {
					indice[k++] = j;
				}
			}
		}

		// supprimer pour l'ensemble des indices
		k--;
		while (k >= 0)
			supprimeRubrique(indice[k--]);
	}

	// MCR 13072007
	/**
	 * Tri les rubriques de l'objet Lr selon la position d�fini pour chaque
	 * mot-cl�
	 */
	private void triVecteurRubrique() {
		Collections.sort(Lr, new Comparator<Object>() {
			public int compare(Object o1, Object o2) {
				return ((Rubrique) o1).getM_position() - ((Rubrique) o2).getM_position();
			}
		});
	}

	/**
	 * Niveau 2 : R�organise le vecteur Lr de l'objet Ligne. La variable
	 * "chaine" n'est pas modifi�e. Les rubriques n'appartenant pas � cette
	 * ligne sont renvoy�es sous la forme d'un vecteur.
	 * 
	 * @return Vecteur contenant les rubriques n'appartenant pas � cette ligne.
	 */
	public Vector<Rubrique> reorganiseVecteurRubrique() {		
		Vector<Rubrique> Lr_Out = new Vector<Rubrique>();
/*for(int i=0;i<Lr.size();i=i+1){
	Vector<Mot> mots = Lr.elementAt(i).getVmot();
	String ligne = "";
	for(int j=0;j<mots.size();j=j+1){
		ligne = ligne + " " + mots.elementAt(j).getChaine();
	}
	System.err.println(Lr.elementAt(i).getLigne()+"-"+mots.elementAt(0).getLigne()+"-"+ligne);
}*/
		
		int j = 0;
		int indice[] = new int[Lr.size()];
		for (int i = 0; i < Lr.size(); i++) {
			Vector<Mot> V_mot = ((Rubrique) Lr.elementAt(i)).getVmot();

		
			//4.2.1.1.7
			//si PR ou GC, en ligne 2 a 4, on laisse, en ligne 5, on laisse en ligne 5
			if(("GARDE#COURRIER").compareTo(V_mot.elementAt(0).getChaineAb())==0 || ("POSTE#RESTANTE").compareTo(V_mot.elementAt(0).getChaineAb())==0){
				V_mot.elementAt(0).setLigne(this.numeroLigne);
			}
			
			//if(("ST BOIS").compareTo(this.getLigneMemo())==0 && this.numeroLigne== '5' ){
				//V_mot.elementAt(0).setLigne(this.numeroLigne);
			//}
			
			// il faut tester le mot cle BOIS si ce dernier est pr�sent en ligne 5 contenant un autre mot il ne faut pas le remonter en ligne 4
			// il existe un lieu dit ST BOIS donc il faut qu'il soit pr�sent
			//if (("BOIS").compareTo(V_mot.elementAt(0).getChaineAb())==0 && (numeroLigne == 5)) {
				//if (i != (Lr.size() - 1) && this.numeroLigne==5) {
					//if ( V_mot.elementAt(0).getType() == 'M') {
						//V_mot.elementAt(0).setLigne(this.numeroLigne);
					//}
				//}
			//}
			
			// la ligne est la meme pour toute la rubrique
			// cr�er une rubrique � chaque fois
			//4.2.1.2.7, a revoir ...
			boolean condition1 = ((((Mot) V_mot.elementAt(0)).getLigne() == 9) && (this.numeroLigne == 3));
			boolean condition2 = ((((Mot) V_mot.elementAt(0)).getLigne() == 8) && (this.numeroLigne == 2));
			boolean condition3 = ((Mot) V_mot.elementAt(0)).getLigne() == 0;
			
			if ((V_mot.elementAt(0).getLigne() != this.numeroLigne)
					&& (condition1 == false) && (condition2 == false)
					&& (condition3 == false)) {

				// ajouter la rubrique dans Lr_out		
				//pour test 4.2.4.1.6
				Lr_Out.add(new Rubrique(V_mot,V_mot.elementAt(0).getLigne()));
				indice[j++] = i;
			}
		}
		// effacer les Rubriques du vecteur Lr :
		for (int i = (j - 1); i >= 0; i--) {
			this.Lr.remove(indice[i]);
		}

		// MCR 12072007 Tri sur positions apres reorganisation
		triVecteurRubrique();

		return Lr_Out;
	}

	/**
	 * Niveau 2 : R�organisation de la ligne 4. cette m�thode peut �tre utilis�e
	 * par n'importe quelle ligne (aucun controle), mais est destin�e � la ligne
	 * 4.
	 */
	public Vector<Rubrique> reorganiseVecteurRubriqueL4() {
		int nbRubL4 = 0;
		int k = 0;
		int nbRub = Lr.size();
		Vector<Rubrique> V_temp = new Vector<Rubrique>();
		int indice[] = new int[20];
		// int flag1 = 0, flag2 = 0;
		int flag1 = -1, flag2 = -1;
		
		//on cherche a identifier le 1er 

		if (Lr.size() > 1) {
			for (int i = 0; i < Lr.size(); i++) {
				// if ( (((Rubrique)Lr.elementAt(i)).getType() == 0) && (flag1
				// == 0) )
				if ((((Rubrique) Lr.elementAt(i)).getType() == 0)
						&& (flag1 == -1)) {
					flag1 = i;
				}
				// else if ( (((Rubrique)Lr.elementAt(i)).getType() == 1) &&
				// (flag2 == 0) )
				else if ((((Rubrique) Lr.elementAt(i)).getType() == 1)
						&& (flag2 == -1)) {
					flag2 = i;
				}
			}
		}

		// if (flag1 != 0)
		if (flag1 != -1) {
			for (int i = 0; i < Lr.size(); i++) {
				if (flag1 != i)
					V_temp.add(Lr.elementAt(i));
			}
			for (int i = Lr.size() - 1; i >= 0; i--) {
				if (i != flag1)
					Lr.removeElementAt(i);
			}
		}
		// else if (flag2 != 0)
		else if (flag2 != -1) {
			for (int i = 0; i < Lr.size(); i++) {
				if (flag2 != i)
					V_temp.add(Lr.elementAt(i));
			}
			for (int i = Lr.size() - 1; i >= 0; i--) {
				if (i != flag2)
					Lr.removeElementAt(i);
			}
		}

		return V_temp;
	}

	/**
	 * Niveau 2 : Contr�le Suppl�mentaire : Si la ligne ne contient qu'un
	 * nombre, ajouter "NUMERO" devant.
	 */
	/*public void controleSupp_1() {
		int count = 0;
		int flag = 0;

		if (Lr.size() == 1) {
			Vector<Mot> V_mot = ((Rubrique) Lr.elementAt(0)).getVmot();
			if (V_mot.size() == 1) {
				if (((Mot) V_mot.elementAt(0)).getType() == 'N') {
					// la ligne ne contient qu'un nombre
					this.ajouteMot("NUMERO", 0, 0);
					this.chaine = "NUMERO "+this.chaine;
				}
			}
		}
	}*/

	/**
	 * Niveau 2 : Controle suppl�mentaire (seulement Ligne 2 et 3). si ces
	 * lignes ne contiennent que un nombre : ne pas supprimer mais affecter le flag 5
	 */
	public void controleSupp_2() {
		if(this.chaine!=null && this.chaine.matches("([0-9]+\\p{Space}*)+")){
			this.setAjoutErrorBin("5");
		}
	}

	/**
	 * Niveau 2 : Controle Suppl�mentaire (seulement Ligne 2 et 3). Si ces
	 * lignes ne contiennent qu'une extension, on affecte le flag
	 */
	public void controleSupp_3() {
		String tab[] = { "BIS", "TER", "QUATER", "QUINQUIES" };
		String maChaine = null;

		int count = 0;
		int flag = 0;
		if (Lr.size() == 1) {
			if (((Rubrique) Lr.elementAt(0)).getVmot().size() == 1) {
				String motUnique = ((Mot) ((Rubrique) Lr.elementAt(0))
						.getVmot().elementAt(0)).getChaineIE();
				for (int j = 0; j < tab.length; j++) {
					if (motUnique.compareTo(tab[j]) == 0) {
						count++;
					}
				}
				if ((motUnique.length() == 1)
						&& ((motUnique.charAt(0) >= 'A') && (motUnique.charAt(0) <= 'Z'))) {
					count++;
				}
			}
		}

		// la ligne ne contient qu'un element : ne "plus le" supprimer, affecter le flag 7
		if ((count == 1) && (flag == 0)) {
			Lr.elementAt(0).setType(0);
			this.setAjoutErrorBin("7");
		}
	}

	/**
	 * Niveau 2 : Controle Suppl�mentaire (seulement Ligne 2 et 3). Si ces
	 * lignes ne contiennent qu'un ou plusieurs chiffres ou commence par "numero", on renvoie true
	 */
	public boolean controleSupp_4() {//FAR 70 -> modifier la regex
		String ligne = this.getLigne();
		boolean flag = false;
		if(ligne!=null){
			flag = ligne.matches("(((NUMERO\\p{Space})?[0-9]+(\\p{Space}[A-Z])?)\\p{Space}?)+|(((NUME\\p{Space})?[0-9]+(\\p{Space}[A-Z])?)\\p{Space}?)+|(((NO\\p{Space})?[0-9]+(\\p{Space}[A-Z])?)\\p{Space}?)+|(((N\\p{Space})?[0-9]+(\\p{Space}[A-Z])?)\\p{Space}?)+");//detection format NUMERO xx ext
			
			if(flag==false){
				flag = ligne.matches("([0-9]+/?)+");//detection format xx/yy
			}
		}
		return flag;
	}
	
	/**
	 * Niveau 2 : Controle suppl�mentaire (seulement Ligne 4). si cette ligne
	 * commence par plusieurs chiffres cons�cutifs , on supprime le chiffre si le precedent
	 * - a la meme parite
	 * - est superieur 
	 * - a un ecart <= 10
	 */
	/*public void controleSupp_5() {
		if(Lr.size()>0 && Lr.elementAt(0).getLigne()==4){
			for(int i=0;i<Lr.size();i=i+1){
				Vector<Mot> vMot = Lr.elementAt(i).getVmot();
				int lastInt = -1;
				for(int j=0;j<vMot.size()-1;j=j+1){
					if(lastInt==-1 && vMot.elementAt(j).getType()=='N'){
						lastInt = Integer.parseInt(vMot.elementAt(j).getChaine());
					}else{
						if(vMot.elementAt(j).getType()=='N'){
							if(Integer.parseInt(vMot.elementAt(j).getChaine())>=lastInt 
								&& Integer.parseInt(vMot.elementAt(j).getChaine())%2==lastInt%2
								&& Integer.parseInt(vMot.elementAt(j).getChaine())-lastInt<10){
								vMot.removeElementAt(j);
								this.setAjoutErrorBin("10");
								if(j<vMot.size() && (vMot.elementAt(j).getType()=='E' || vMot.elementAt(j).getType()=='I')){
									vMot.removeElementAt(j);
									this.setAjoutErrorBin("10");
									//j=j-1;
								}
								j=j-1;
							}else{
								lastInt = Integer.parseInt(vMot.elementAt(j).getChaine());
							}
						}else if(vMot.elementAt(j).getType()!='N' && vMot.elementAt(j).getType()!='E' && vMot.elementAt(j).getType()!='I'){
							break;
						}
					}
				}
			}
		}
	}*/

	/**
	 * Niveau 2 : Controle Suppl�mentaire (seulement Ligne 4). 
	 * si cette ligne ne commence pas 1 nombre et contient un type de voie , renvoie true
	 */
	public boolean controleSupp_6() {
		boolean flagNombre = false;
		boolean flagNomVoie = false;
		
		if(Lr.size()>0){//test du nombre en premiere position
			 Vector<Mot> vMot = ((Rubrique)Lr.elementAt(0)).getVmot();
			 if(vMot.size()>0 && (vMot.elementAt(0).getChaine().compareTo("NUMERO")==0 || vMot.elementAt(0).getType()=='N'
			 || vMot.elementAt(0).getChaine().matches("[0-9]+[/[0-9]]+"))){
			 	flagNombre = true; //on a le premier element, un nombre en premiere position
			 }
		}
		
		for(int i=0;i<Lr.size();i=i+1){
			if(((Rubrique)Lr.elementAt(i)).getLigne()==4){
				flagNomVoie = true;
			}
		}
		
		return !flagNombre && flagNomVoie;
	}
	
	/**
	 * Niveau 2 : Controle suppl�mentaire (seulement Ligne 4). si cette ligne
	 * commence "NUMERO" plus un nombre, on supprime le NUMERO (4.2.1.3.5)
	 */
	public void controleSupp_7() {
		if(Lr.size()>0){
			Vector<Mot> vMot = Lr.elementAt(0).getVmot();
			if(vMot.size()>0 && vMot.elementAt(0).getChaine().compareTo("NUMERO")==0){
				vMot.remove(0);
				this.setFlagRestructurationOn();
				this.setFlagNumeroOn();
			}
		}
	}
	
	/**
	 * Niveau 2 : Controle suppl�mentaire (seulement Ligne 2 et 3)
	 * on supprime les caracteres - et / APRES restructuration
	 */
	public void controleSupp_8() {
		for(int i=0;i<this.Lr.size();i=i+1){
			for(int j=0;j<this.Lr.elementAt(i).getVmot().size();j=j+1){
				String chaineTmp = this.Lr.elementAt(i).getVmot().elementAt(j).getChaine();
				this.chaine = this.chaine.replaceAll("/"," ");
				this.chaine = this.chaine.replaceAll("-"," ");
				//fae 1.4.1 13
				/*chaineTmp = chaineTmp.replaceAll("/"," ");
				chaineTmp = chaineTmp.replaceAll("-"," ");
				this.Lr.elementAt(i).getVmot().setElementAt(new Mot(chaineTmp),j);*/
			}
		}
	}
	
	/**
	 * Niveau 2 : Controle suppl�mentaire (seulement Ligne 4). si cette ligne
	 * commence par un zero, on l'enleve (avec son extension)
	 */
	public void controleSupp_9() {
		if(this.Lr.size()>0 && this.Lr.elementAt(0).getLigne()==4){
			for(int i=0;i<Lr.size();i=i+1){
				Vector<Mot> vMot = this.Lr.elementAt(i).getVmot();
				long lastInt = -1;
				int endIndex = 0;
				for(int j=0;j<vMot.size()-1;j=j+1){
					if(vMot.elementAt(j).getType()=='N'){
						
						/*si on saisi un num�ro trop long on prend les 15 premiers carac*/
						if (vMot.elementAt(j).getChaine().length() > 15) {
							endIndex = 15;
						}else{
							endIndex = vMot.elementAt(j).getChaine().length();
						}
						
						/* le 3 mars : remplace la fonction Integer.parseInt par Long.parseLong 
						 * permet de traiter le cas ou le numero contient un nombre de chiffre trop important */
						if(Long.parseLong(vMot.elementAt(j).getChaine().substring(0, endIndex))==0 && lastInt==-1){
							vMot.removeElementAt(j);
							this.setFlagRestructurationOn();
							if(j<vMot.size() && (vMot.elementAt(j).getType()=='E' || vMot.elementAt(j).getType()=='I')){
								vMot.removeElementAt(j);
								//j=j-1;
							}
							j=j-1;
						}else{
							/* le 3 mars : remplace la fonction Integer.parseInt par Long.parseLong 
							 * permet de traiter le cas ou le numero contient un nombre de chiffre trop important */
							//lastInt = Integer.parseInt(vMot.elementAt(j).getChaine());
							lastInt = Long.parseLong(vMot.elementAt(j).getChaine().substring(0, endIndex));
						}
					}

					if(vMot.elementAt(j+1).getType()!='N' && vMot.elementAt(j+1).getType()!='E' && vMot.elementAt(j+1).getType()!='I'){
						break;
					}
				}
			}
		}
	}
	
	/**
	 * Niveau 2 : Controle suppl�mentaire (seulement Ligne 4).
	 * on remplace les nombre de type 011 par 11
	 */
	public void controleSupp_10() {
		String regex = "^0+([1-9][0-9]*)";
		if(Lr.size()>0 && Lr.elementAt(0).getLigne()==4){
			for(int i=0;i<Lr.size();i=i+1){
				Vector<Mot> vMot = Lr.elementAt(i).getVmot();
				for(int j=0;j<vMot.size();j=j+1){
					if(vMot.elementAt(j).getType()=='N'){
						java.util.regex.Matcher matcher = java.util.regex.Pattern.compile(regex).matcher(vMot.elementAt(j).getChaine());
						if (matcher.find()) {
							vMot.setElementAt(new Mot(matcher.group(1)),j);
							this.setFlagRestructurationOn();
						}
					}
				}
			}
		}
	}
	
	/**
	 * on remplace les nombres expression 2 E en 2E
	 */
	public void controleSupp_11() {
		for(int i=0;i<this.getLr().size();i=i+1){
			if(this.getLr().elementAt(i).getVmot().size()>1){
				Vector<Mot> vMots = this.getLr().elementAt(i).getVmot();
				for(int j=vMots.size()-1;j>0;j=j-1){
					if(vMots.elementAt(j).getChaine().equals("E") && vMots.elementAt(j-1).getType()=='N'){
						Mot mot = vMots.elementAt(j-1);
						mot = new Mot(vMots.elementAt(j-1).getChaine()+vMots.elementAt(j).getChaine());
						vMots.setElementAt(mot, j-1);
						vMots.removeElementAt(j);
						this.setFlagRestructurationOn();
					}
				}
			}
		}
	}
	
	//fae 1.4.1 07
	public void controleSupLotissementPlusChiffre(){
		boolean flagModification = false;
		for(int i=0;i<this.getLr().size();i=i+1){
			Vector<Mot> vMots = this.getLr().elementAt(i).getVmot();
			for(int j=0;j<vMots.size()-1;j=j+1){
				if((vMots.elementAt(j).getChaineIE().equals("LOT") || vMots.elementAt(j).getChaineIE().equals("LOTISSEMENT")) && vMots.elementAt(j+1).getChaineIE().matches("[0-9]+")){
					vMots.elementAt(j).setChaineIE("LOT");
					vMots.elementAt(j).setChaine("LOT");
					vMots.elementAt(j).setChaineAb("LOT");
					flagModification = true;
				}
			}
		}
		if(flagModification==true){
			this.chaine = "";
			for(int i=0;i<this.getLr().size();i=i+1){
				Vector<Mot> vMots = this.getLr().elementAt(i).getVmot();
				for(int j=0;j<vMots.size();j=j+1){
					this.chaine = this.chaine + " " + vMots.elementAt(j).getChaine();
				}
			}
			this.chaine = this.chaine.trim();
		}
	}
	
	/**
	 * Niveau 2 : Methode utilis�e uniquement en ligne 2. Lors de la partie
	 * abr�viation, si la L2 est trop longue, alors on essaie de d�placer des
	 * rubriques en L3.
	 * 
	 * Le r�le de cette m�thode est donc de r�cup�rer un nombre suffisant de
	 * m�thodes afin de les d�placer en L3
	 * 
	 * Le Vecteur renvoy� contient les rubriques � d�placer.
	 */

	public Vector<Rubrique> deplacerRubriques(int norme) {
		Vector<Rubrique> V = new Vector<Rubrique>();

		if ((Lr.size() == 1) || (Lr.size() == 0))
			return null;

		for (int i = Lr.size() - 1; i >= 1; i--) {
			V.add(((Rubrique) Lr.elementAt(i)));
			this.Lr.removeElementAt(i);
			// tester la taille de la ligne
			String chainetmp = this.getLigneAb(norme);
			if (chainetmp.length() <= norme)
				return V;
		}
		return null;

	}

	/** ******************************************************************************************** */
	/* CHAINE */
	/** ******************************************************************************************** */

	/**
	 * Compare la chaine de l'objet courant avec la chaine de l'objet Ligne
	 * pass� en param�tre.
	 */
	public boolean egal(Ligne L) {
		if (getLigne().compareTo(L.getLigne()) != -1)
			return true;
		else
			return false;
	}

	/** ******************************************************************************************** */
	/* SERIALISATION */
	/** ******************************************************************************************** */

	/**
	 * Sauvegarde la ligne dans l'objet L_save
	 */
	 //@SuppressWarnings({"unchecked"}) //a utiliser si on utilise Lr.close() ...
	public void saveObjet() {
		if (Lr != null)
			//Lr_save = (Vector<Rubrique>) Lr.clone();
			//pour supprimer le warning
			Lr_save = new Vector<Rubrique>(Lr);
		else
			Lr_save = null;
		// chaine_save = new String(chaine);
		if (Msg != null)
			Msg_save = new String(Msg);
		else
			Msg_save = null;
	}

	/**
	 * restaure la ligne avec l'objet L_save
	 */
	public void restaureObjet() {
		Lr = Lr_save;
		// chaine = chaine_save;
		Msg = Msg_save;
	}

	/** ******************************************************************************************** */
	/* ACCESSEURS et methodes priv�es */
	/** ******************************************************************************************** */

	/**
	 * Niveau 2
	 * 
	 * @return true si l'objet Ligne est vide. false sinon.
	 */
	public boolean vide() {
		return (Lr.size() == 0);
	}

	/**
	 * Niveau 2 : retourne la chaine repr�sentant la ligne. La chaine retourn�e
	 * est fabriqu� � partir du vecteur Lr et des lib. IE.
	 */
	public String getLigne() {
		chaine = null;
		if (Lr != null) {
			for (int i = 0; i < Lr.size(); i++) {
				Vector<Mot> V_mot = ((Rubrique) Lr.elementAt(i)).getVmot();
				for (int j = 0; j < V_mot.size(); j++) {
					if ((j == 0) && (i == 0)){
						chaine = V_mot.elementAt(j).getChaineIE();
					}else{
						chaine = chaine + " "+ V_mot.elementAt(j).getChaineIE();
					}
				}
			}
			if (Lr.size() != 0 && this.chaine!=null){
				this.chaine = this.chaine.replace('#', ' ');
			}
		}
		
		//System.out.println( this.chaine);
		return this.chaine;
		
		
		
	}

	/**
	 * Niveau 2 : retourne la chaine repr�sentant la ligne. La chaine retourn�e
	 * est fabriqu� � partir du vecteur Lr et des lib. IE.
	 */
	public String getLigneNonTriee() {
		chaine = null;
		if (LrNonTriee != null) {
			for (int i = 0; i < LrNonTriee.size(); i++) {
				Vector<Mot> V_mot = ((Rubrique) LrNonTriee.elementAt(i)).getVmot();
				for (int j = 0; j < V_mot.size(); j++) {
					if ((j == 0) && (i == 0)){
						chaine = ((Mot) V_mot.elementAt(j)).getChaine();
					}else{
						chaine = chaine + " "+ ((Mot) V_mot.elementAt(j)).getChaine();
					}
				}
			}
			if (LrNonTriee.size() != 0 && this.chaine!=null){
				this.chaine = this.chaine.replace('#', ' ');
			}
		}
		//System.out.println( this.chaine);
		return this.chaine;
	}
	/**
	 * Niveau 2 : retourne la chaine repr�sentant la ligne. La chaine retourn�e
	 * est fabriqu� � partir du vecteur Lr et des lib. IE.
	 */
	public String getLigneMemo() {
		return chaineMemo;
	}

	/**
	 * Niveau 2 : retourne la chaine repr�sentant la ligne avec les mots-cl�s
	 * abr�g�s jusqu'� obtenir une chaine de norme caract�res maximum.
	 * 
	 * Si c'est pas suffisant, abr�ge les titres
	 * 
	 * Si c'est toujours pas suffisant, supprime les articles
	 */
	public String getLigneAb(int norme){
		String ligneAb =  this.getLigneAbPhase1(norme);
		ligneAb = this.getLigneAbPhase2(ligneAb,norme);
		return ligneAb;
	}
	
	/**
	 * phase de reduction de la ligne
	 * @param norme
	 */
	public String getLigneAbPhase1(int norme) {
		String chaineTmp = "";
		int nbmot = 0;
		Vector<Rubrique> localLr = this.Lr;
		
		//
		// Abr�viation des mots cl�s
		//
		//fae 1.4.1 02 reecriture de la methode d'abreviation
		for(int j=0;j<localLr.size();j=j+1){
			Vector<Mot> V_mot = localLr.elementAt(j).getVmot();

			for(int k=0;k<V_mot.size();k=k+1){
				chaineTmp = chaineTmp + " " + V_mot.elementAt(k).getChaineIE();
			}
		}
		chaineTmp = chaineTmp.trim();

		if(chaineTmp.length()<=norme){
			return chaineTmp.trim();
		}
		
		for(int i=0;i<localLr.size();i=i+1){
			chaineTmp = "";
			for(int j=0;j<i+1;j=j+1){
				Vector<Mot> V_mot = localLr.elementAt(j).getVmot();
				for(int k=0;k<V_mot.size();k=k+1){
					if (V_mot.elementAt(k).getType() == 'C'){
						chaineTmp = chaineTmp + " " + V_mot.elementAt(k).getChaineAb();
					}else{
						chaineTmp = chaineTmp + " " + V_mot.elementAt(k).getChaineIE();
					}
				}
			}
			for(int j=i+1;j<localLr.size();j=j+1){
				Vector<Mot> V_mot = localLr.elementAt(j).getVmot();
				for(int k=0;k<V_mot.size();k=k+1){
					chaineTmp = chaineTmp + " " + V_mot.elementAt(k).getChaineIE();
				}
			}

			chaineTmp = chaineTmp.trim();
			if(chaineTmp.length()<=norme){
				return chaineTmp.trim();
			}
		}
		/*if (localLr != null) {
			for (int i = 0; i < localLr.size(); i++) {
				Vector<Mot> V_mot = localLr.elementAt(i).getVmot();
				for (int j = 0; j < V_mot.size(); j++) {
					if (V_mot.elementAt(j).getChaineAb().compareTo(V_mot.elementAt(j).getChaineIE()) != 0
							&& V_mot.elementAt(j).getType() == 'C')
						nbmot++;
				}
			}

			if (nbmot == 0 || nbmot == 1) {
				// construire chaine et la retourner
				for (int i = 0; i < localLr.size(); i++) {
					Vector<Mot> V_mot = localLr.elementAt(i).getVmot();
					for (int j = 0; j < V_mot.size(); j++) {
						if ((j == 0) && (i == 0)) {
							if (V_mot.elementAt(j).getType() == 'C')
								chaineTmp = V_mot.elementAt(j).getChaineAb();
							else
								chaineTmp = V_mot.elementAt(j).getChaineIE();
						} else {
							if (V_mot.elementAt(j).getType() == 'C')
								chaineTmp = chaineTmp + " "	+ V_mot.elementAt(j).getChaineAb();
							else
								chaineTmp = chaineTmp + " "	+ V_mot.elementAt(j).getChaineIE();
						}
					}
				}
			} else{ // plus d'1 rubrique modifiable
				int nb = 0;
				
				while (nb < nbmot) {
					int n = 0;
					String chaineFinale = "";
					//for (int i = 0; i < Lr.size(); i++) {
					//modification de la methode (d'ou la presence des variables chaineFinale et chaineTmp2)
					//pour permettre la gestion des abreviations dans le bon ordre ...
					for (int i = localLr.size()-1; i>=0; i=i-1) {
						String chaineTmp2 = "";
						Vector<Mot> V_mot = localLr.elementAt(i).getVmot();
						for (int j = 0; j < V_mot.size(); j++) {
							if (j == 0 && i == localLr.size()-1) {
								if (V_mot.elementAt(j).getChaineAb().compareTo(V_mot.elementAt(j).getChaineIE()) != 0
										&& V_mot.elementAt(j).getType() == 'C') {
									n++;
									chaineTmp = V_mot.elementAt(j).getChaineAb();
									chaineTmp2 = V_mot.elementAt(j).getChaineAb();
								} else {
									chaineTmp = V_mot.elementAt(j).getChaineIE();
									chaineTmp2 = V_mot.elementAt(j).getChaineIE();
								}
							} else {
								if (n <= nb) {
									if (V_mot.elementAt(j).getChaineAb().compareTo(V_mot.elementAt(j).getChaineIE()) != 0
											&& V_mot.elementAt(j).getType() == 'C') {
										n++;
										chaineTmp = chaineTmp + " "	+ V_mot.elementAt(j).getChaineAb();
										chaineTmp2 = chaineTmp2 + " " + V_mot.elementAt(j).getChaineAb();
									} else {
										chaineTmp = chaineTmp + " "	+ V_mot.elementAt(j).getChaineIE();
										chaineTmp2 = chaineTmp2 + " " + V_mot.elementAt(j).getChaineIE();
									}
								} else {
									chaineTmp = chaineTmp + " "	+ V_mot.elementAt(j).getChaineIE();
									chaineTmp2 = chaineTmp2 + " " + V_mot.elementAt(j).getChaineIE();
								}
							}
						}
						chaineFinale = chaineTmp2.trim() + " " + chaineFinale;
					}

					nb++;
					if (chaineTmp.length() <= norme){
						nb = nbmot;
						chaineTmp = chaineFinale;
					}
				}
			}
			if (chaineTmp != null) {
				if (chaineTmp.trim().length() <= norme) {
					chaineTmp = chaineTmp.replace('#', ' ');
					return chaineTmp.trim();
				}
			}

		}*/

		Ligne ligneTmp = new Ligne(chaineTmp,this.numeroLigne);
		localLr = ligneTmp.Lr;
		chaineTmp = "";
		if (localLr.size() != 0) {
			//
			// Abr�viation des Titres
			//
			for (int i = 0; i < localLr.size(); i++) {
				Vector<Mot> V_mot = ((Rubrique) localLr.elementAt(i)).getVmot();
				for (int j = 0; j < V_mot.size(); j++) {
					if ((j == 0) && (i == 0))
						chaineTmp = ((Mot) V_mot.elementAt(j)).getChaineAb();
					else
						chaineTmp = chaineTmp + " "
								+ ((Mot) V_mot.elementAt(j)).getChaineAb();
				}
			}
			
			if (chaineTmp.trim().length() <= norme) {
				chaineTmp = chaineTmp.replace('#', ' ');
				return chaineTmp.trim();
			}

			//
			// Suppression des articles
			//
			ligneTmp = new Ligne(chaineTmp,this.numeroLigne);
			localLr = ligneTmp.Lr;
			chaineTmp = "";
			for (int i = 0; i < localLr.size(); i++) {
				Vector<Mot> V_mot = ((Rubrique) localLr.elementAt(i)).getVmot();
				for (int j = 0; j < V_mot.size(); j++) {
					if (((Mot) V_mot.elementAt(j)).getType() != 'L') {
						if ((j == 0) && (i == 0))
							chaineTmp = ((Mot) V_mot.elementAt(j)).getChaineAb();
						else
							chaineTmp = chaineTmp + " "	+ ((Mot) V_mot.elementAt(j)).getChaineAb();
					}
				}
			}

			//on colle l'extension si on est en ligne 4
			if(chaineTmp.length()>norme && this.numeroLigne==4){
				String[] testChaine = chaineTmp.split(" ");
				String result = "";
				if(testChaine.length>0){
					result = testChaine[0];
				}
				for(int i=1;i<testChaine.length;i=i+1){
					if(testChaine[i-1].matches("[0-9]+") && testChaine[i].matches("[A-Z]")){
						result = result + testChaine[i];
					}else{
						result = result + " " + testChaine[i];
					} 
				}
				chaineTmp = result;
			}
			//
			
			chaineTmp = chaineTmp.replace('#', ' ');
			return chaineTmp.trim();
		}else{
			return null;
		}
	}
	
	/**
	 * phase ou on cherche a reetendre les motcle (fev 1.5.0 05)
	 * @param norme
	 */
	public String getLigneAbPhase2(String ligneAEtendre, int norme) {
		String ligneEnCours = ligneAEtendre;
		Ligne testLigne = new Ligne(ligneAEtendre,numeroLigne);

		for(int i=0;i<testLigne.Lr.size();i=i+1){
			String chaineTmp = "";
			for(int j=0;j<i+1;j=j+1){
				Vector<Mot> V_mot = testLigne.Lr.elementAt(j).getVmot();
				for(int k=0;k<V_mot.size();k=k+1){
					if (V_mot.elementAt(k).getType() == 'C'){
						chaineTmp = chaineTmp + " " + V_mot.elementAt(k).getChaineIE();
					}else{
						chaineTmp = chaineTmp + " " + V_mot.elementAt(k).getChaine();
					}
				}
			}
			for(int j=i+1;j<testLigne.Lr.size();j=j+1){
				Vector<Mot> V_mot = testLigne.Lr.elementAt(j).getVmot();
				for(int k=0;k<V_mot.size();k=k+1){
					chaineTmp = chaineTmp + " " + V_mot.elementAt(k).getChaine();
				}
			}

			
			if(chaineTmp.trim().length()<=norme){
				ligneEnCours = chaineTmp.trim();
			}
		}
		return ligneEnCours;
	}
	
	public String getLigneAbVersion1_2_1(int norme) {
		String chaineTmp = null;
		int nbmot = 0;
		
		//
		// Abr�viation des mots cl�s
		//
		if (this.Lr != null) {
			for (int i = 0; i < this.Lr.size(); i++) {
				Vector<Mot> V_mot = this.Lr.elementAt(i).getVmot();
				for (int j = 0; j < V_mot.size(); j++) {
					if (V_mot.elementAt(j).getChaineAb().compareTo(V_mot.elementAt(j).getChaineIE()) != 0
							&& V_mot.elementAt(j).getType() == 'C')
						nbmot++;
				}
			}

			if (nbmot == 0 || nbmot == 1) {
				// construire chaine et la retourner
				for (int i = 0; i < this.Lr.size(); i++) {
					Vector<Mot> V_mot = this.Lr.elementAt(i).getVmot();
					for (int j = 0; j < V_mot.size(); j++) {
						if ((j == 0) && (i == 0)) {
							if (V_mot.elementAt(j).getType() == 'C')
								chaineTmp = V_mot.elementAt(j).getChaineAb();
							else
								chaineTmp = V_mot.elementAt(j).getChaineIE();
						} else {
							if (V_mot.elementAt(j).getType() == 'C')
								chaineTmp = chaineTmp + " "	+ V_mot.elementAt(j).getChaineAb();
							else
								chaineTmp = chaineTmp + " "	+ V_mot.elementAt(j).getChaineIE();
						}
					}
				}
			} else{ // plus d'1 rubrique modifiable
				int nb = 0;
				
				while (nb < nbmot) {
					int n = 0;
					String chaineFinale = "";
					//for (int i = 0; i < Lr.size(); i++) {
					//modification de la methode (d'ou la presence des variables chaineFinale et chaineTmp2)
					//pour permettre la gestion des abreviations dans le bon ordre ...
					for (int i = this.Lr.size()-1; i>=0; i=i-1) {
						String chaineTmp2 = "";
						Vector<Mot> V_mot = this.Lr.elementAt(i).getVmot();
						for (int j = 0; j < V_mot.size(); j++) {
							if (j == 0 && i == this.Lr.size()-1) {
								if (V_mot.elementAt(j).getChaineAb().compareTo(V_mot.elementAt(j).getChaineIE()) != 0
										&& V_mot.elementAt(j).getType() == 'C') {
									n++;
									chaineTmp = V_mot.elementAt(j).getChaineAb();
									chaineTmp2 = V_mot.elementAt(j).getChaineAb();
								} else {
									chaineTmp = V_mot.elementAt(j).getChaineIE();
									chaineTmp2 = V_mot.elementAt(j).getChaineIE();
								}
							} else {
								if (n <= nb) {
									if (V_mot.elementAt(j).getChaineAb().compareTo(V_mot.elementAt(j).getChaineIE()) != 0
											&& V_mot.elementAt(j).getType() == 'C') {
										n++;
										chaineTmp = chaineTmp + " "	+ V_mot.elementAt(j).getChaineAb();
										chaineTmp2 = chaineTmp2 + " " + V_mot.elementAt(j).getChaineAb();
									} else {
										chaineTmp = chaineTmp + " "	+ V_mot.elementAt(j).getChaineIE();
										chaineTmp2 = chaineTmp2 + " " + V_mot.elementAt(j).getChaineIE();
									}
								} else {
									chaineTmp = chaineTmp + " "	+ V_mot.elementAt(j).getChaineIE();
									chaineTmp2 = chaineTmp2 + " " + V_mot.elementAt(j).getChaineIE();
								}
							}
						}
						chaineFinale = chaineTmp2.trim() + " " + chaineFinale;
					}
					nb++;
					if (chaineTmp.length() <= norme){
						nb = nbmot;
						chaineTmp = chaineFinale;
					}
				}
			}
			if (chaineTmp != null) {
				if (chaineTmp.length() <= norme) {
					chaineTmp = chaineTmp.replace('#', ' ');
					return chaineTmp;
				}
			}

		}
		
		if (this.Lr.size() != 0) {
			//
			// Abr�viation des Titres
			//
			for (int i = 0; i < this.Lr.size(); i++) {
				Vector<Mot> V_mot = ((Rubrique) this.Lr.elementAt(i)).getVmot();
				for (int j = 0; j < V_mot.size(); j++) {
					if ((j == 0) && (i == 0))
						chaineTmp = ((Mot) V_mot.elementAt(j)).getChaineAb();
					else
						chaineTmp = chaineTmp + " "
								+ ((Mot) V_mot.elementAt(j)).getChaineAb();
				}
			}
			
			if (chaineTmp.length() <= norme) {
				chaineTmp = chaineTmp.replace('#', ' ');
				return chaineTmp;
			}

			//
			// Suppression des articles
			//
			for (int i = 0; i < this.Lr.size(); i++) {
				Vector<Mot> V_mot = ((Rubrique) this.Lr.elementAt(i)).getVmot();
				for (int j = 0; j < V_mot.size(); j++) {
					if (((Mot) V_mot.elementAt(j)).getType() != 'L') {
						if ((j == 0) && (i == 0))
							chaineTmp = ((Mot) V_mot.elementAt(j)).getChaineAb();
						else
							chaineTmp = chaineTmp + " "	+ ((Mot) V_mot.elementAt(j)).getChaineAb();
					}
				}
			}
			
			//on colle l'extension si on est en ligne 4
			if(chaineTmp.length()>norme && this.numeroLigne==4){
				String[] testChaine = chaineTmp.split(" ");
				String result = "";
				if(testChaine.length>0){
					result = testChaine[0];
				}
				for(int i=1;i<testChaine.length;i=i+1){
					if(testChaine[i-1].matches("[0-9]+") && testChaine[i].matches("[A-Z]")){
						result = result + testChaine[i];
					}else{
						result = result + " " + testChaine[i];
					} 
				}
				chaineTmp = result;
			}
			//
			chaineTmp = chaineTmp.replace('#', ' ');
			return chaineTmp;
		}else{
			return null;
		}
	}

	/**
	 * Niveau 2 : retourne la chaine repr�sentant la ligne en la tronquant si
	 * n�cessaire � norme caract�res
	 */
	public String getL(int norme) {
		String maChaine = null;
		// si la ligne fait plus de 32/38 caracteres : on la tronque
		// et si on est sur la ligne 4 ou 5 on traite une nouvelle fois la
		// ponctuation
		this.chaine = this.getLigne();

		if (this.chaine != null) {
			if (chaine.compareTo("") != 0) {
				if (this.chaine.length() > norme) {
					if (this.getLigneAb(norme).length() > norme) {
						maChaine = getLigneAb(norme).substring(0, norme);
						if ((this.numeroLigne == 4) || (this.numeroLigne == 5)) {
							maChaine = TraitementAdresse.enlevePonctuation(maChaine, 0);
							// il se peut que la suppression d'un caract�re
							// accentu� fasse apparaitre un espace supp.
							maChaine = TraitementAdresse.supprimerEspaces(maChaine);
						}
						return maChaine;
					} else {
						maChaine = getLigneAb(norme);
						if ((this.numeroLigne == 4) || (this.numeroLigne == 5)) {
							maChaine = TraitementAdresse.enlevePonctuation(maChaine, 0);
							// il se peut que la suppression d'un caract�re
							// accentu� fasse apparaitre un espace supp.
							maChaine = TraitementAdresse.supprimerEspaces(maChaine);
						}
						return maChaine;
					}
				}
			}
		}
		maChaine = getLigne();
		if (((this.numeroLigne == 4) || (this.numeroLigne == 5)) && (maChaine != null)) {
			maChaine = TraitementAdresse.enlevePonctuation(maChaine, 0);
			maChaine = TraitementAdresse.supprimerEspaces(maChaine.trim());
		}
		
		if(maChaine==null){
			maChaine = "";
		}
		
		return maChaine;
	}

	/**
	 * Niveau 1 : positionne la chaine repr�sentant la ligne.
	 */
	public void setLigne(String chaine) {
		if (chaine == null)
			this.chaine = "";
		else
			this.chaine = chaine;
	}

	/**
	 * Niveau 1 : Cette m�thode remplace tous les caract�res non autoris�s par
	 * un espace
	 */
	private void trCharAllow() {
		char tmpChaine[] = this.chaine.toCharArray();
		if ((this.numeroLigne == 2) || (this.numeroLigne == 3)) {
			for (int i = 0; i < this.chaine.length(); i++) {
				int flag = 0;
				for (int j = 0; j < this.authChar_L2_L3.length(); j++) {
					if (tmpChaine[i] == this.authChar_L2_L3.charAt(j))
						flag = 1;
				}
				if (flag == 0)
					tmpChaine[i] = ' ';
			}
		} else if (this.numeroLigne == 4) {
			for (int i = 0; i < this.chaine.length(); i++) {
				int flag = 0;
				for (int j = 0; j < this.authChar_L4.length(); j++) {
					if (tmpChaine[i] == this.authChar_L4.charAt(j))
						flag = 1;
				}
				if (flag == 0)
					tmpChaine[i] = ' ';
			}
		} else {
			for (int i = 0; i < this.chaine.length(); i++) {
				int flag = 0;
				for (int j = 0; j < this.authChar_L5.length(); j++) {
					if (tmpChaine[i] == this.authChar_L5.charAt(j))
						flag = 1;
				}
				if (flag == 0)
					tmpChaine[i] = ' ';
			}
		}

		this.chaine = String.valueOf(tmpChaine);
	}

	/**
	 * Niveau 1 : Normalisation de la chaine. Tous les mots conbtenus dans le
	 * tableau DataNormalise.data sont remplac�s par un synonime.
	 * 
	 * @see restructuration.data.DataNormalise DataNormalise
	 */
	private void normaliseLigne() {
		int index;
		int index_debut = 0;
		int index_fin = 0;
		String tab[] = { "1ERE", "1ER", "0E", "1E", "2E", "3E", "4E", "5E","6E", "7E", "8E", "9E" };

		for (int i = 0; i < DataNormalise.tab.length; i++) {
			// si une chaine est trouv�e
			if ((index = TraitementAdresse.strAppartient(this.chaine,
					DataNormalise.tab[i][0])) != -1) {
				// si le mot occupe toute la ligne
				if (this.chaine.compareTo(DataNormalise.tab[i][0].trim()) == 0) {
					this.chaine = DataNormalise.tab[i][1].trim();
				}
				// le mot cl� n'est pas en d�but de ligne
				else if (index != 0) {
					// cas 1 : mot du type "7EME_"
					// cas 2 : mot du type "_7EME_"
					// cas 3 : mot du type "_N1"
					String mot = String.valueOf(DataNormalise.tab[i][0]);
					if (this.chaine.charAt(index) == ' ')
						index++;
					if (mot.charAt(0) == ' ') {
						// cas 2 ou 3
						String strDebut = this.chaine.substring(0, index);
						if (DataNormalise.tab[i][0].trim().length() + index == this.chaine.length()) {
							// le mot est � la fin
							this.chaine = strDebut
									+ String.valueOf(DataNormalise.tab[i][1]);
							// System.out.println("1.1");
						} else {
							String strFin = this.chaine.substring(index	+ String.valueOf(DataNormalise.tab[i][0]).length() - 1, this.chaine.length());
							this.chaine = strDebut.trim()
									+ String.valueOf(DataNormalise.tab[i][1])
									+ strFin;
							// System.out.println("1.2");
						}
					} else {
						String strDebut = this.chaine.substring(0, index);
						// cas 1
						if (DataNormalise.tab[i][0].trim().length() + index == this.chaine
								.length()) {
							// le mot est � la fin
							this.chaine = strDebut
									+ String.valueOf(DataNormalise.tab[i][1]);
							// System.out.println("1.3");
						} else {
							// le mot n'est pas � la fin
							if (index - 1 >= 0) {
								if (this.chaine.charAt(index - 1) == ' ') {
									// cas 1 : "_7EME"
									String strFin = this.chaine.substring(index
											+ String.valueOf(DataNormalise.tab[i][0]).length() - 1, this.chaine.length());
									this.chaine = strDebut.trim()
											+ " "
											+ String.valueOf(DataNormalise.tab[i][1])
											+ strFin;
									// System.out.println("1.4.1");
								} else {
									// cas 2 : "_47EME"
									String strFin = this.chaine.substring(index
											+ String.valueOf(DataNormalise.tab[i][0]).length() - 1, this.chaine.length());
									this.chaine = strDebut.trim()
											+ String.valueOf(DataNormalise.tab[i][1])
											+ strFin;
									// System.out.println("1.4.2");
								}
							}
						}
					}
				}
				// le mot cl� est en d�but de ligne
				else {
					// soit le mot est du type "N1_" soit "N1"
					if (String.valueOf(DataNormalise.tab[i][1])
							.charAt(
									String.valueOf(DataNormalise.tab[i][1]).length() - 1) == ' ') {
						// au depart soit le mot est du type "N1_", soit
						// "_PREMIER_"
						// if
						this.chaine = String.valueOf(DataNormalise.tab[i][1]).trim()
								+ " "
								+ this.chaine.substring(String.valueOf(DataNormalise.tab[i][0]).trim().length()).trim();
						// System.out.println("2.1");
					} else {
						// on est dans le cas "N1_RUE MACHIN"
						// System.out.println("valeur : " +
						// String.valueOf(DataNormalise.Tab[i][0]).trim().length());
						// System.out.println("donnee : " +
						// DataNormalise.Tab[i][0]);
						if (this.chaine.charAt(String.valueOf(DataNormalise.tab[i][0]).trim().length()) == ' ') {
							this.chaine = String.valueOf(
									DataNormalise.tab[i][1]).trim()
									+ " "
									+ this.chaine.substring(String.valueOf(
											DataNormalise.tab[i][0]).length());
							// System.out.println("2.2");
						}
						// on est dans le cas "N15_RUE MACHIN"
						else {
							this.chaine = String.valueOf(
									DataNormalise.tab[i][1]).trim()
									+ this.chaine.substring(String.valueOf(DataNormalise.tab[i][0]).length() - 1);
							// System.out.println("2.3");
						}
					}
				}
			}
		}

		//normalisation sur les mots cles, ajout de "#" sur les mots compos�s
		String chaineTest = this.chaine.replaceAll(" ","#");
		for(int i=0;i<DataMotscles.tab.length;i=i+1){
			if(chaineTest.indexOf(DataMotscles.tab[i][1])!=-1 && DataMotscles.tab[i][1].indexOf("#")!=-1){
				int indexDebut = chaineTest.indexOf(DataMotscles.tab[i][1]);
				int indexFin = chaineTest.indexOf(DataMotscles.tab[i][1])+DataMotscles.tab[i][1].length()-1;
				if(((indexDebut>0 && chaine.substring(indexDebut-1,indexDebut).compareTo(" ")==0) || indexDebut==0) 
					&& (indexFin==chaine.length()-1 || (chaine.substring(indexFin+1,indexFin+2).compareTo(" ")==0))){
					this.chaine = this.chaine.substring(0,indexDebut)+DataMotscles.tab[i][2]+this.chaine.substring(indexFin+1);
				}
			}
		}

		this.chaine = TraitementAdresse.supprimerEspaces(this.chaine.trim());
		// //System.out.println("Ligne.normaliseLigne() : Algo lot et");
		// Algorithme "_LOT_"
		if ((index = TraitementAdresse.strAppartient(this.chaine, " LOT ")) != -1) {
			// //System.out.println("Ligne.normaliseLigne() : Passage 1");
			// il n'est pas le dernier de la ligne.
			if (this.chaine.endsWith(" LOT") == false) {
				// il n'est pas suivi d'un nombre.
				// //System.out.println("Ligne.normaliseLigne() : Passage 2");
				int a = (int) this.chaine.charAt(this.chaine.indexOf(" LOT ")
						+ " LOT ".length());
				int b = (int) this.chaine.charAt(this.chaine.indexOf("LOT ")
						+ "LOT ".length());
				if (((a < (int) '0') || (a > (int) '9'))
						|| ((b < (int) '0') || (b > (int) '9'))) {
					// il n'est pas suivi de " LOT NUMERO X" ou "LOT NUMERO X".
					// //System.out.println("Ligne.normaliseLigne() : Passage
					// 3");
					int index1 = 0, index2 = 0;
					if (((index1 = this.chaine.indexOf(" LOT NUMERO ")) != -1)
							|| ((index2 = this.chaine.indexOf("LOT NUMERO ")) != -1)) {
						// //System.out.println("Ligne.normaliseLigne() :
						// Passage 4");
						index = (index1 == -1) ? (index2 + "LOT NUMERO "
								.length()) : (index1 + " LOT NUMERO ".length());
						// la chaine " LOT NUMERO " existe, un numero suit ?
						if (((int) this.chaine.charAt(index) >= (int) '0')
								&& ((int) this.chaine.charAt(index) <= (int) '9')) {
							// il est suivi de "NUMERO X"
							return;
						}
					}
					// il n'est pas pr�c�d� d'un nombre en i�me.
					int flag = 0;
					for (int i = 0; i < tab.length; i++) {
						if (TraitementAdresse.PRECEDE(this.chaine, "LOT",
								tab[i]) == true)
							flag = 1;
					}
					if (flag == 0) {
						// ON REMPLACE LOT par LOTISSEMENT
						// System.out.println("Ligne.normaliseLigne() : Passage
						// 6");
						index = TraitementAdresse.strAppartient(this.chaine,
								" LOT ");
						if (index != 0) {
							// Remplacement de la chaine
							String strDebut = this.chaine.substring(0, index);
							String strFin = this.chaine.substring(index
									+ " LOT ".length());
							this.chaine = strDebut + " LOTISSEMENT " + strFin;
						} else {
							String strFin = this.chaine.substring(index
									+ "LOT ".length());
							this.chaine = "LOTISSEMENT " + strFin;
						}
						// System.out.println("Ligne.normaliseLigne() : Passage
						// 6 : " + this.chaine);
					}
				}
			}
		}

		// algorithme " ET " = usine � gaz donc abandonn�
		int flag = 0;
		if ((this.numeroLigne == 2) || (this.numeroLigne == 3)) {
			// on est en ligne 2 ou 3
			if ((index = TraitementAdresse.strAppartient(this.chaine, " ET ")) >= 0) {
				Mot mot_avant = null;
				Mot mot_apres = null;
				char carAVANT;
				char carAPRES;
				StringBuffer motAVANT = new StringBuffer(0);
				StringBuffer motAPRES = new StringBuffer(0);
				index_debut = 0;
				index_fin = 0;

				if (this.chaine.charAt(index) == ' ')
					index++;

				// if (index != 0)
				// index++; // on se place sur le 'E' de "ET"
				// d�terminer mot_avant et mot_apres
				int avant = index - 2;
				int apres = index + 3;

				// construction du mot avant "ET"
				if (avant >= 0) {
					index_fin = avant;
					carAVANT = this.chaine.charAt(avant);
					while ((avant >= 0) && (carAVANT != ' ')) {
						motAVANT.append(carAVANT);
						avant--;
						if (avant >= 0)
							carAVANT = this.chaine.charAt(avant);
					}
					if (motAVANT.charAt(motAVANT.length() - 1) == ' ') {
						motAVANT.deleteCharAt(motAVANT.length() - 1);
						mot_avant = new Mot(motAVANT.reverse().toString());
						index_debut = avant + 2;
					} else {
						mot_avant = new Mot(motAVANT.reverse().toString());
						index_debut = avant + 1;
					}
				}

				if (mot_avant != null) {
					if ((mot_avant.getType() == 'N')
							|| (mot_avant.getType() == 'I'))
						flag = -1;
				}

				if (flag == -1) {
					if (index_debut == 0) {
						// la chaine est peut etre la seule de la ligne
						if (this.chaine.compareTo(this.chaine.substring(
								index_debut, index_fin + 1)
								+ " ET") == 0)
							this.chaine = this.chaine.substring(index_debut,
									index_fin + 1)
									+ " ETAGE";
						else {
							String strDebut = this.chaine.substring(
									index_debut, index_fin + 1);
							String strFin = this.chaine.substring(index
									+ "ET".length() + 1);
							this.chaine = strDebut + " ETAGE " + strFin;
						}
					} else if ((index + "ET".length()) == this.chaine.length()) {
						String strDebut = this.chaine.substring(0,
								index_fin + 1);
						this.chaine = strDebut + " ETAGE";
					} else
						flag = 0;
				}

				// construction du mot apres "ET"
				if (flag == 0) {
					if (apres < this.chaine.length()) {
						index_debut = apres;
						carAPRES = this.chaine.charAt(apres);
						while ((apres < this.chaine.length())
								&& (carAPRES != ' ')) {
							motAPRES.append(carAPRES);
							apres++;
							if (apres < this.chaine.length())
								carAPRES = this.chaine.charAt(apres);
						}
						mot_apres = new Mot(motAPRES.toString());
						index_fin = apres - 1;
						flag = 1;
					}
				}

				if (flag == 1) {
					if ((mot_apres.getType() == 'N')
							|| (mot_apres.getType() == 'I')) {
						// System.out.println("taille de la chaine : " +
						// this.chaine.length());
						if (index == 0) {
							this.chaine = "ETAGE "
									+ this.chaine.substring(index_debut,
											this.chaine.length());
						} else if (index_fin == this.chaine.length() - 1) {
							String strDebut = this.chaine.substring(0, index);
							String strFin = this.chaine.substring(index_debut,
									index_fin + 1);
							this.chaine = strDebut + "ETAGE " + strFin;
						}
					} else if (mot_apres.getChaineIE().compareTo("NUMERO") == 0) {
						if ((index_debut = TraitementAdresse.strAppartient(
								this.chaine, "NUMERO")) >= 0) {
							apres = index_debut + "NUMERO".length() + 1;
							if (apres < this.chaine.length()) {
								carAPRES = this.chaine.charAt(apres);
								motAPRES = new StringBuffer(0);
								while ((apres < this.chaine.length())
										&& (carAPRES != ' ')) {
									motAPRES.append(carAPRES);
									apres++;
									if (apres < this.chaine.length())
										carAPRES = this.chaine.charAt(apres);
								}
								mot_apres = new Mot(motAPRES.toString());
								index_fin = apres - 1;
								if ((mot_apres.getType() == 'N')
										|| (mot_apres.getType() == 'I')) {
									if (index_fin == this.chaine.length() - 1) {
										String strDebut = this.chaine
												.substring(0, index);
										String strFin = this.chaine.substring(
												index_debut, index_fin + 1);
										this.chaine = strDebut + "ETAGE "
												+ strFin;
									} else if (index == 0) {
										this.chaine = "ETAGE"
												+ this.chaine.substring("ET"
														.length());
									}
								}
							}
						}
					}
				}
			}
		}
	}

	/**
	 * Niveau 2 : Renvoi le vecteur Lr
	 */
	public Vector<Rubrique> getLr() {
		return Lr;
	}

	/**
	 * Niveau 2 : Positionne le vecteur Lr
	 */
	public void clearLr() {
		Lr.clear();
	}

	/**
	 * Renvoi les messages d'erreur
	 */
	public String getMsg() {
		String MsgRetour = null;
		if (this.Msg != null)
			MsgRetour = this.Msg;
		for (int i = 0; i < Lr.size(); i++) {
			if (((Rubrique) Lr.elementAt(i)).getMsg() != null) {
				if (MsgRetour == null)
					MsgRetour = ((Rubrique) Lr.elementAt(i)).getMsg();
				else
					MsgRetour = MsgRetour + "<BR>"
							+ ((Rubrique) Lr.elementAt(i)).getMsg();
			}
		}
		return MsgRetour;
	}

	/**
	 * Une ligne a �t� trouv�e trop longue : setMsg positionne le message
	 */
	public void setMsg() {
		this.Msg = "La ligne est trop longue";
	}

	public String getErrorBin() {
		return errorBin;
	}

	public void setErrorBin(String errorBin) {
		this.errorBin = errorBin;
	}

 	/**
	 * Ajout d'un bit � la liste des bits positionn�s S_errorbin, le separateur
	 * virgule est ajout� par la m�thode
	 * 
	 * @param s_errorbin
	 *            contenant un bit
	 */
	public void setAjoutErrorBin(String s_errorbin) {
		if (this.errorBin.compareTo("")==0) {
			this.errorBin = s_errorbin;
		} else {
			this.errorBin = this.errorBin + "," + s_errorbin;
		}
	}

}
