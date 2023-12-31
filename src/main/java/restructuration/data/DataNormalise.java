package restructuration.data;

/**
 * Classe de donn�es statiques contenant toutes les variables normalis�es r�f�renc�es par le document webAdresse.doc
  * @version 02.02.00
 * @author La Poste - Service National de l'Adresse
*/
public class DataNormalise{

	public static final String tab[][] = {
		//ai3_010000_03_FEV
		/*{" N0"," NUMERO 0"},
		{" N1"," NUMERO 1"},
		{" N2"," NUMERO 2"},
		{" N3"," NUMERO 3"},
		{" N4"," NUMERO 4"},
		{" N5"," NUMERO 5"},
		{" N6"," NUMERO 6"},
		{" N7"," NUMERO 7"},
		{" N8"," NUMERO 8"},
		{" N9"," NUMERO 9"},
		{" N 0"," NUMERO 0"},
		{" N 1"," NUMERO 1"},
		{" N 2"," NUMERO 2"},
		{" N 3"," NUMERO 3"},
		{" N 4"," NUMERO 4"},
		{" N 5"," NUMERO 5"},
		{" N 6"," NUMERO 6"},
		{" N 7"," NUMERO 7"},
		{" N 8"," NUMERO 8"},
		{" N 9"," NUMERO 9"},
		{" NUME "," NUMERO "},
		{" NUM "," NUMERO "},
		{" NO "," NUMERO "},*/
		{"1 ERE ","1ERE "},
		{"1 IER ","1ER "},
		{"1 ER ","1ER "},
		{" 2 ND "," 2E "},
		{" 2ND "," 2E "},
		{"0 E ","0E "},
		{"0EME ","0E "},
		{"0IEME ","0E "},
		{"0 EM ","0E "},
		{"0 EMME ","0E "},
		{"0 EME ","0E "},
		{"0 IEME ","0E "},
		{"0B ","0 BIS "},
		{"0T ","0 TER "},
		{"0Q ","0 QUATER "},
		{"0C ","0 QUINQUIES "},
		{"1 E ","1E "},
		{"1EME ","1E "},
		{"1IEME ","1E "},
		{"1 EM ","1E "},
		{"1 EMME ","1E "},
		{"1 EME ","1E "},
		{"1 IEME ","1E "},
		{"1B ","1 BIS "},
		{"1T ","1 TER "},
		{"1Q ","1 QUATER "},
		{"1C ","1 QUINQUIES "},
		{"2 E ","2E "},
		{"2EME ","2E "},
		{"2IEME ","2E "},
		{"2ND ","2E "},
		{"2 EM ","2E "},
		{"2 EMME ","2E "},
		{"2 EME ","2E "},
		{"2 IEME ","2E "},
		{"2 ND ","2E "},
		{"2B ","2 BIS "},
		{"2T ","2 TER "},
		{"2Q ","2 QUATER "},
		{"2C ","2 QUINQUIES "},
		{"3 E ","3E "},
		{"3EME ","3E "},
		{"3IEME ","3E "},
		{"3 EM ","3E "},
		{"3 EMME ","3E "},
		{"3 EME ","3E "},
		{"3 IEME ","3E "},
		{"3B ","3 BIS "},
		{"3T ","3 TER "},
		{"3Q ","3 QUATER "},
		{"3C ","3 QUINQUIES "},
		{"4 E ","4E "},
		{"4EME ","4E "},
		{"4IEME ","4E "},
		{"4 EM ","4E "},
		{"4 EMME ","4E "},
		{"4 EME ","4E "},
		{"4 IEME ","4E "},
		{"4B ","4 BIS "},
		{"4T ","4 TER "},
		{"4Q ","4 QUATER "},
		{"4C ","4 QUINQUIES "},
		{"5 E ","5E "},
		{"5EME ","5E "},
		{"5IEME ","5E "},
		{"5 EM ","5E "},
		{"5 EMME ","5E "},
		{"5 EME ","5E "},
		{"5 IEME ","5E "},
		{"5B ","5 BIS "},
		{"5T ","5 TER "},
		{"5Q ","5 QUATER "},
		{"5C ","5 QUINQUIES "},
		{"6 E ","6E "},
		{"6EME ","6E "},
		{"6IEME ","6E "},
		{"6 EM ","6E "},
		{"6 EMME ","6E "},
		{"6 EME ","6E "},
		{"6 IEME ","6E "},
		{"6B ","6 BIS "},
		{"6T ","6 TER "},
		{"6Q ","6 QUATER "},
		{"6C ","6 QUINQUIES "},
		{"7 E ","7E "},
		{"7EME ","7E "},
		{"7IEME ","7E "},
		{"7 EM ","7E "},
		{"7 EMME ","7E "},
		{"7 EME ","7E "},
		{"7 IEME ","7E "},
		{"7B ","7 BIS "},
		{"7T ","7 TER "},
		{"7Q ","7 QUATER "},
		{"7C ","7 QUINQUIES "},
		{"8 E ","8E "},
		{"8EME ","8E "},
		{"8IEME ","8E "},
		{"8 EM ","8E "},
		{"8 EMME ","8E "},
		{"8 EME ","8E "},
		{"8 IEME ","8E "},
		{"8B ","8 BIS "},
		{"8T ","8 TER "},
		{"8Q ","8 QUATER "},
		{"8C ","8 QUINQUIES "},
		{"9 E ","9E "},
		{"9EME ","9E "},
		{"9IEME ","9E "},
		{"9 EM ","9E "},
		{"9 EMME ","9E "},
		{"9 EME ","9E "},
		{"9 IEME ","9E "},
		{"9B ","9 BIS "},
		{"9T ","9 TER "},
		{"9Q ","9 QUATER "},
		{"9C ","9 QUINQUIES "},
		{" PREMIER "," 1ER "},
		{" PREMIERE "," 1ERE "},
		{" SECOND "," 2E "},
		{" SECONDE "," 2E "},
		{" DEUXIEME "," 2E "},
		{" TROISIEME "," 3E "},
		{" QUATRIEME "," 4E "},
		{" CINQUIEME "," 5E "},
		{" SIXIEME "," 6E "},
		{" SEPTIEME "," 7E "},
		{" HUITIEME "," 8E "},
		{" NEUVIEME "," 9E "},
		{" DIXIEME "," 10E "},
		{" R D C "," RDC "},
		{" R D Z "," RDC "},
		{" R DE C "," RDC "},
		{" R DE CHAUSSEE "," RDC "},
		{" REZ DE CHAUSSEE "," RDC "},
		{" CTRE COMMERCIAL "," CCAL "},
		{" CENTRE COMMERCIAL "," CCAL "},
		{" C CIAL "," CCAL "},
		{" CTRE CIAL "," CCAL "},
		{" C COMMERCIAL "," CCAL "},
		{" C CAL "," CCAL "},
		{" H L M "," HLM "},
		{" Z I "," ZI "},
		{" Z A "," ZA "},
		{" Z U P "," ZUP "},
		{" Z A D "," ZAD "},
		{" Z A C "," ZAC "},
		{" C Z "," CZ "},
		{" C O "," CO "},
		{" ZONE D AMENAGEMENT CONCERTE "," ZAC "},
		{" ZONE D AMENAGEMENT DIFFERE "," ZAD "},
		{" ZONE D'AMENAGEMENT CONCERTE "," ZAC "},
		{" ZONE D'AMENAGEMENT DIFFERE "," ZAD "},
		{" ZONE A URBANISER EN PRIORITE "," ZUP "},
		{" ZONE D'ACTIVITE "," ZA "},
		{" ZONE INDUSTRIELLE "," ZI "},
		//{" EDEN PARC "," EDEN PARK "},
		{" LIEU DIT "," LD "},
		{" LIEUDIT "," LD "},
		{" L D "," LD "},
		{" L-D "," LD "},
		{" FOYER LOGEMENT "," FOYER#LOGEMENT "},
		{" FOYERS LOGEMENTS "," FOYERS#LOGEMENTS "},
		{" CENTRAL PARC "," CENTRAL#PARC "},
		{" CITE HLM "," CITE#HLM "},
		{" BOITE POSTALE "," BP "},
		{" BOIT POSTALE "," BP "},
		{" BOITE POSTAL "," BP "},
		{" BOIT POSTAL "," BP "},
		{" B P "," BP "},
		/*{" BOITE "," BAL "},*/
		{" BOITE LETTRE "," BAL "},
		{" BOITE LETTRES "," BAL "},
		{" BOITE AUX LETTRES "," BAL "},
		{" BOITE A LETTRE "," BAL "},
		/*{" BTE "," BAL "},*/
		{" BOITE AU LETTRE "," BAL "},
		{" TRI SERVICE ARRIVEE "," TSA "},
		{" POSTE RESTANTE "," POSTE#RESTANTE "},
		{" GARDE COURRIER "," GARDE#COURRIER "},
		{" COURSE SPECIALE "," COURSE#SPECIALE "}
	};
}
