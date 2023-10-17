package restructuration.object;

import restructuration.data.DataAdjectifs;
import restructuration.data.DataArticles;
import restructuration.data.DataExtensions;
import restructuration.data.DataMotscles;
import restructuration.data.DataTitre;

/**
 * Classe permettant le stockage des diff�rents type de mot :
 *  <BR>-C : Mots cl�.
 *  <BR>-A : Adjectif.
 *  <BR>-L : Article.
 *  <BR>-I : nombre en IEME.
 *  <BR>-N : Nombre.
 *  <BR>-M : Mot normal.
 *  <BR>-E : Extension.
 *  <BR>-F : Mot cl� de la ligne 0
 * <BR><BR>Si le mot est un mot-cl�, les chaines abr�g�e et in extenso sont stock�es.
 * Le poids, la ligne d'appartenance sont �galement stock�s.
 */
public class Mot{

	private char type;
	private String chaine;
	private String chaineInExtenso;
	private String chaineAbbregee;
	private int poids;
	private int position; //position permet de reorganiser les rubriques d'une ligne dans l'ordre indiqu�
	private int ligne;
	private int norme;

	public Mot(String chaine){
		this.chaine = chaine;
		this.norme = 38;
		trouveMot();
	}

	/**
	 * retourne la cha�ne inextenso
	 */
	public String getChaineIE(){
		return chaineInExtenso;
	}
	
	/**
	 * retourne la cha�ne abr�g�e
	 */
	public String getChaineAb(){
		return chaineAbbregee;
	}

	/**
	 * fae 1.4.1 07
	 * affectation de la chaine in extenso, utile pour transformer, ou plutot forcer lotissement en lot
	 */
	public void setChaineIE(String data){
		this.chaineInExtenso = data;
	}
	
	/**
	 * fae 1.4.1 07
	 * affectation de la chaine in extenso, utile pour transformer, ou plutot forcer lotissement en lot
	 */
	public void setChaine(String data){
		this.chaine = data;
	}
	
	/**
	 * fae 1.4.1 07
	 * affectation de la chaine in extenso, utile pour transformer, ou plutot forcer lotissement en lot
	 */
	public void setChaineAb(String data){
		this.chaineAbbregee = data;
	}
	
	/**
	 * retourne le type du mot.
	 */
	public char getType(){
		return type;
	}

	/**
	 * positionne le type du mot.
	 */
	public void setType(char type){
		this.type = type;
	}

	/**
	 * retourne le poids du mot.
	 */
	public int getPoids(){
		return poids;
	}

	/**
	 * positionne le poids du mot
	 */
	public void setPoids(int poids){
		this.poids = poids;
	}

	/**
	 * retourne la chaine IE du mot
	 */
	public String getChaine(){
		return chaine;
	}

	/**
	 * retourne la ligne (d'adresse) associ� � l'objet Mot
	 */
	public int getLigne(){
		return this.ligne;
	}

	/**
	 * positionne le num�ro de la ligne (d'adresse) associ� � l'objet Mot
	 */
	public void setLigne(int ligne){
		this.ligne = ligne;
	}
	
	/**
	 * positionne les variables d'instances en interrogeant les tableaux DataArticles, DataMotsCles et DataAdjectifs
	 */
	private void trouveMot(){
		// le mot est-il un article.
		for (int i = 0; i < DataArticles.tab.length ;i++){
			if (this.chaine.compareTo(DataArticles.tab[i]) == 0){
				type = 'L';
				chaineInExtenso = chaine;
				chaineAbbregee = chaine;
	
				//correction pour "A", "L", etc...
				if(this.isExtension()){
					type = 'E';
				}
				return;
			}
		}
	
		// le mot est il un adjectif.
		for (int i = 0; i < DataAdjectifs.tab.length ;i++){
			 if (this.chaine.compareTo(DataAdjectifs.tab[i][0]) == 0 || 
			 	(this.norme==32 && this.chaine.compareTo(DataAdjectifs.tab[i][1])==0) || 
			 	(this.norme==38 && this.chaine.compareTo(DataAdjectifs.tab[i][2])==0)){
			 	type = 'A';
				chaineInExtenso = chaine;
				if(this.norme==32){
					chaineAbbregee = DataAdjectifs.tab[i][1];//ancienne norme
				}else if(this.norme==38){
					chaineAbbregee = DataAdjectifs.tab[i][2];//nouvelle norme
				}else{
					chaineAbbregee = "Erreur,norme inconnue !!!!!!";//norme inconnue
				}
				return;
			}
		}
	
	// le mot est-il une extension
		for (int i = 0; i < DataExtensions.tab.length ;i++){
			if (this.chaine.compareTo(DataExtensions.tab[i][0]) == 0){
				type = 'E';
				chaineInExtenso = DataExtensions.tab[i][1];
				chaineAbbregee = DataExtensions.tab[i][1];
				return;
			}
		}
		
		// le mot est-il un nombre en IEME.
		String tab[] = {
		"1ERE",
		"1ER",
		"0E",
		"1E",
		"2E",
		"3E",
		"4E",
		"5E",
		"6E",
		"7E",
		"8E",
		"9E"
		};
		for (int i = 0; i < tab.length;i++){
			if (this.chaine.endsWith(tab[i]) == true){
				type = 'I';
				chaineInExtenso = chaine;
				chaineAbbregee = chaine;
				return;
			}
		}

		// le mot est-il un chiffre romain
		/*for (int i = 0; i < DataChiffreRomain.Tab.length ;i++){
			if (this.chaine.compareTo(DataChiffreRomain.Tab[i][0]) == 0){
				type = 'N';
				chaineInExtenso = DataChiffreRomain.Tab[i][1];
				chaineAbbregee = DataChiffreRomain.Tab[i][1];
				return;
			}
		}*/

	// le mot est-il un mot cl�.
		for (int i = 0; i < DataMotscles.tab.length ;i++){
			if (this.chaine.compareTo(DataMotscles.tab[i][1]) == 0){
				if (DataMotscles.tab[i][5].compareTo("0") == 0){
					type = 'F';
				}else{
					type = 'C';
				}
				this.poids = Integer.parseInt(DataMotscles.tab[i][0]);
		
				this.ligne = Integer.parseInt(DataMotscles.tab[i][5]);
				this.chaineInExtenso = DataMotscles.tab[i][2].replaceAll("#"," ");
				if(this.norme==32){
					this.chaineAbbregee = DataMotscles.tab[i][3];
				}else if(this.norme==38){
					this.chaineAbbregee = DataMotscles.tab[i][4];
				} else {
					this.chaineAbbregee = "Erreur,norme inconnue !!!!!!";//norme inconnue
				}
				if(this.chaineAbbregee.trim().length()==0){
					this.chaineAbbregee = this.chaineInExtenso;
				}
				//MCR
				//La position n'est pas renseignee pour toutes les lignes
				if (!DataMotscles.tab[i][6].equals("")) {
					this.position = Integer.parseInt(DataMotscles.tab[i][5]); 
				}
			 	return;
			}
		}
		
		// le mot est-il un nombre ou un mot normal
		int flag = 0;
		for (int i = 0;i < this.chaine.length();i++){
			int a = (int)this.chaine.charAt(i);
			if ( (a < (int)'0') || (a > (int)'9') ){
				flag = 1;
			}
		}

		// c'est un mot normal
	
			if (flag == 1){
				int flagTitre = 1;
				// le mot est-il dans le tableau DataTitre ?
				for (int i = 0; i < DataTitre.tab.length ;i++){
				// c'est un mot
					if (this.chaine.compareTo(DataTitre.tab[i][0]) == 0){
						if(this.norme==32){
							chaineAbbregee = DataTitre.tab[i][1];
						}else if(this.norme==38){
							chaineAbbregee = DataTitre.tab[i][2];
						}else{
							chaineAbbregee = "Erreur,norme inconnue !!!!!!";//norme inconnue
						}
						chaineInExtenso = chaine;
						flagTitre = 0;
						type = 'M';
					}
				}
				
				if (flagTitre == 1){
					type = 'M';
					chaineInExtenso = chaine;
					chaineAbbregee = chaine;
				}
				return;
			}

		// c'est un nombre
		if (flag == 0){
			type = 'N';
			chaineInExtenso = chaine;
			chaineAbbregee = chaine;
			return;
		}
	}
	
	//MCR
	/**
	 * Retourne la position du mot dans la ligne
	 * @return int : position 
	 */
	public int getPosition() {
		return position;
	}
	//MCR
	/** Affecta la position du mot dans la ligne
	 * @param position
	 */
	public void setPosition(int position) {
		this.position = position;
	}
	
	/** Cette methode est utilisee pour tester si le mot est une extension
	* "A" par exemple est un article mais peut aussi etre une extension ...
	*/
	public boolean isExtension(){
		boolean result = false;
		// le mot est-il aussi une extension
		for (int i = 0; i < DataExtensions.tab.length ;i++){
			if (this.chaine.compareTo(DataExtensions.tab[i][0]) == 0){
				result = true;
			}
		}
		return result;
	}
}
