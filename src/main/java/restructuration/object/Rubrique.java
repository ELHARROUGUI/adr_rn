package restructuration.object;

import java.util.Vector;

/**
 * La classe Rubrique vient en compl�ment de la classe Mot. Un objet Rubrique est compos� de Mot
 * ayant tous le m�me poids. (vecteur Vmot)
 *
 * Type d'objet rubrique : (variable typeRubrique)
 *  <BR>0 --> normal. (LG = 2,3,4,5)
 *  <BR>1 --> R_type : Rubrique sans mot cl� (tableau 8 Mots cl�s).
 *  <BR>2 --> R_type : Rubrique (LG = 9).
 *  <BR>3 --> R_type : Rubrique (LG = 0).
 *  <BR>4 --> R_type : Rubrique (LG = 8).
 *  <BR>conclusion : typeRubrique > 0 : rubrique R_type
 *  type R_type (notion logique) = rubrique susceptible d'�tre d�plac�e par l'algo.
 * 
 * 
 * MASCADIA-RJA-20040813 : correctif bug emp�chant l'alimentation des champs "m_motCle" et "m_motCleAb".
*/

public class Rubrique implements java.lang.Cloneable
{
	/** Type de la rubrique. */
	private int m_type;
	/** Vecteur des "Mot" (les mots composants la rubrique). */
	private Vector<Mot> m_vMot;
	/**  */
	private String m_msg = null;
	/**  */
	private String m_motCle;
	/**  */
	private String m_motCleAb;
	/** Ligne ou la rubrique doit �tre mise. */
	private int m_ligne = -1;
	/** Ligne d'origine (de saisie) de la rubrique. */
	/**
	 * 
	 */
	private int m_ligneOrigine = -1;
	//MCR
	/** Position dans la ligne de la rubrique  */
	private int m_position;

	// Accesseurs.
	/** Retourne le mot cl� de la rubrique (si elle en a un). */
	public String getMotCle() { return m_motCle; }
	/** Retourne le mot cl� abr�g� de la rubrique (si elle en a un). */
	public String getMotCleAB() { return m_motCleAb; }
	/** Retourne le type de la rubrique. */
	public int getType() { return m_type; }
	public void setType(int data) { m_type = data; }
	/** Retourne le vecteur Vmot. */
	public Vector<Mot> getVmot() { return m_vMot; }
	/** Retourne la ligne � laquelle est associ�e la rubrique. */
	public int getLigne() { return m_ligne; }
	 

	/**
	 * Constructeur.
	 * 
	 * MASCADIA-RJA-20040813 : correctif bug emp�chant l'alimentation des champs "m_motCle" et "m_motCleAb".
	 */
	public Rubrique(Vector<Mot>vMot, int origine){
		int flagMC = 0;
		m_motCle = "";
		m_motCleAb = "";
		m_ligneOrigine = origine;
		m_vMot = new Vector<Mot>();
		 
		//
	    for (int i = 0;i < vMot.size();i++)
	    {
	     	m_vMot.add(vMot.elementAt(i));
	     	// il s'agit d'un mot cl�
			if ( ((Mot)vMot.elementAt(i)).getType() ==  'C' )
			{
				flagMC = 1;
				if (("").compareTo(m_motCle)==0){// MASCADIA-RJA-20040813
					m_motCle = ((Mot)vMot.elementAt(i)).getChaineIE();
					m_motCleAb = ((Mot)vMot.elementAt(i)).getChaineAb();
					//m_position = ((Mot)vMot.elementAt(i)).getPosition(); //MCR recupere position de la rubrique a partir position du mot cle
					m_position = ((Mot)vMot.elementAt(i)).getPoids(); //JPC correction pour prendre en compte le poids du mot cl�
				}
			}
			m_ligne = ((Mot)vMot.elementAt(i)).getLigne();
		}
		//

		if (flagMC == 0){
		  m_type = 1;
		}else if (m_ligne == 9){
		  m_type = 2;
		}else if (m_ligne == 0){
		  m_type = 3;
		}else if (m_ligne == 8){
		  m_type = 4;
		}else{
		  m_type = 0;
		}
		
		//correction far 020400_60 a suivre
		if(m_position==0){
			m_position=9999;
		}
  }

	/**
	 * Retourne la longueur de la rubrique abr�g�e
	 */
	  public int getLongueurAb(){
	    int longueur = 0;
	    // calcul de la taille de la rubrique ( avec les mots-cl�s abr�g�s )
	    for (int i = 0;i < m_vMot.size();i++)
	    {
	      if (i == 0)
	        longueur = ((Mot)m_vMot.elementAt(i)).getChaineAb().length();
	      else
	        longueur = longueur + " ".length() + ((Mot)m_vMot.elementAt(i)).getChaineAb().length();
	    }
	    return longueur;
	  }

		  
	  
	/**
	 * positionne la variable Msg
	 */
	  public void setMsg(int ligne,boolean type)
	  {
	    for (int i = 0;i < m_vMot.size();i++)
	    {
	      if ( (((Mot)m_vMot.elementAt(i)).getType() == 'C') && (type == true) )
	      {
	        m_ligne = ligne;
	        m_msg = "Le mot-cl� " + ((Mot)m_vMot.elementAt(i)).getChaineIE() + " a �t� d�plac� sur sa ligne de r�f�rence";
	      }
	    }
	  }
	
	/**
	 * retourne la variable Msg
	 */
	  public String getMsg() {
	    if (m_ligne != m_ligneOrigine){
	      return m_msg;
	    }else{
	      return null;
	    }
	  }
	public int getM_position() {
		return m_position;
	}
	public void setM_position(int m_position) {
		this.m_position = m_position;
	}
	/**
	 * @return num�ro de ligne d'origine de la rubrique
	 */
	public int getM_ligneOrigine() {
		return m_ligneOrigine;
	}
	
}
