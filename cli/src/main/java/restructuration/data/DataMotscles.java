package restructuration.data;



/**
 * Classe de donn�es statiques contenant tous les mots cl�s r�f�renc�s par le document webAdresse.doc
 *
  * @version 02.02.00
 * @author La Poste - Service National de l'Adresse
 */
// MCR 062007 : ajout d'une colonne POS au tableau qui d�signe la position du mot-cl� 
// au sein de la ligne d'adresse
public class DataMotscles{
    
	public static final String tab[][] = {
		{"1","AEORODROME","AEORODROME","AER","AER","0","0"},
		{"2","AEROGARE","AEROGARE","AERG","AERG","0","0"},
		//{"3","AER","AERODROME","AER","AER","0","0"},
		//{"4","AERG","AEROGARE","AERG","AERG","0","0"},
		//{"5","AERN","AERONAUTIQUE","AERON","AERN","0","0"},
		{"6","AERONAUTIQUE","AERONAUTIQUE","AERON","AERN","0","0"},
		{"7","AEROPORT","AEROPRT","AERP","AERP","0","0"},
		//{"8","AERP","AEROPRT","AERP","AERP","0","0"},
		//{"9","AGCE","AGENCE","AGCE","AGCE","0","0"},
		//{"10","AGEN","AGENCE","AGCE","AGCE","0","0"},
		{"11","AGENCE","AGENCE","AGCE","AGCE","0","0"},
		{"12","AGENCES","AGENCES","AGCE","AGCE","0","0"},
		{"13","AGRICOLE","AGRICOLE","AGRIC","AGRIC","0","0"},
		{"14","ANCIENNEMENT","ANCIENNEMENT","","ANC","0","0"},
		{"15","ARMEMENT","ARMEMENT","ARMT","ARMT","0","0"},
		//{"16","ARMT","ARMEMENT","ARMT","ARMT","0","0"},
		//{"17","ARDT","ARRONDISSEMENT","ARDT","ARDT","0","0"},
		{"18","ARRONDISSEMENT","ARRONDISSEMENT","ARDT","ARDT","0","0"},
		//{"19","ART","ARTILLERIE","ART","","0","0"},//version 1.5.4
		//{"20","ARTILLERIE","ARTILLERIE","ART","","0","0"},//version 1.5.4
		//{"21","ASS","ASSOCIATION","ASSOC","ASSOC","0","0"},
		//{"22","ASSO","ASSOCIATION","ASSOC","ASSOC","0","0"},
		//{"23","ASSOC","ASSOCIATION","ASSOC","ASSOC","0","0"},
		//{"24","ASSOCIAT","ASSOCIATION","ASSOC","ASSOC","0","0"},
		{"25","ASSOCIATION","ASSOCIATION","ASSOC","ASSOC","0","0"},
		{"26","ASSOCIATIONS","ASSOCIATIONS","ASSOC","ASSOC","0","0"},
		//{"27","AT ","ATELIER","AT","AT","0","0"},
		{"28","ATELIER","ATELIER","AT","AT","0","0"},
		{"29","BARAQUEMENT","BARAQUEMENT","BRQ","BRQ","0","0"},
		//{"30","BRQ","BARAQUEMENT","BRQ","BRQ","0","0"},
		{"31","BATAILLON","BATAILLON","BTN","BTN","0","0"},
		{"32","BATAILLONS","BATAILLONS","BTN","BTN","0","0"},
		//{"33","BLC","BLOC","BLOC","","0","0"},
		{"34","BLOC","BLOC","BLOC","","0","0"},
		{"35","BLOCS","BLOC","BLOC","","0","0"},
		{"36","CABINET","CABINET","","CBN","0","0"},
		//{"37","CBN","CABINET","","CBN","0","0"},
		//{"38","CALL","CALLADA","CALL","","0","0"},
		{"39","CALLADA","CALLADA","CALL","","0","0"},
		{"40","CANAL","CANAL","CAN","","0","0"},
		{"41","CANAUX","CANAUX","CAN","","0","0"},
		{"42","CANTON","CANTON","CANT","CANT","0","0"},
		{"43","CANTONAL","CANTONAL","CANT","CANT","0","0"},
		//{"44","CARR","CARROI","CARR","","0","0"},
		{"45","CARROI","CARROI","CARR","","0","0"},
		{"46","CASERNE","CASERNE","CASR","","0","0"},
		//{"47","CASR","CASERNE","CASR","","0","0"},
		//{"48","CHA","CHASSE","CHA","","0","0"},
		{"49","CHASSE","CHASSE","CHA","","0","0"},
		{"50","CITADELLE","CITADELLE","CTD","CTD","0","0"},
		//{"51","CTD","CITADELLE","CTD","CTD","0","0"},
		//{"52","COLL","COLLEGE","COLL","COLL","0","0"},
		{"53","COLLEGE","COLLEGE","COLL","COLL","0","0"},
		{"54","COLONIE","COLONIE","COLON","COLO","0","0"},
		//{"55","COMB","COMBATTANT","COMB","","0","0"},
		{"56","COMBATTANT","COMBATTANT","COMB","","0","0"},
		{"57","COMBATTANTS","COMBATTANTS","COMB","","0","0"},
		{"58","COMITE","COMITE","CTE","CTE","0","0"},
		{"59","COMITES","COMITES","CTE","CTE","0","0"},
		//{"60","CIAT","COMMISSARIAT","CIAT","","0","0"},
		{"61","COMMISSARIAT","COMMISSARIAT","CIAT","","0","0"},
		//{"62","COMM","COMMISSION","COMM","","0","0"},
		{"63","COMMISSION","COMMISSION","COMM","","0","0"},
		{"64","COMMUNAL","COMMUNAL","COM","COM","0","0"},
		{"65","COMMUNAUX","COMMUNAUX","COM","COM","0","0"},
		{"66","COMMUNE","COMMUNE","COM","COM","0","0"},
		//{"67","CIE","COMPAGNIE","CIE","CIE","0","0"},
		{"68","COMPAGNIE","COMPAGNIE","CIE","CIE","0","0"},
		{"69","COMPAGNIES","COMPAGNIES","CIE","CIE","0","0"},
		{"70","COMPAGNON","COMPAGNON","COMPAG","COMP","0","0"},
		{"71","COMPAGNONS","COMPAGNONS","COMPAG","COMP","0","0"},
		//{"72","COOP","COOPERATIVE","COOP","","0","0"},
		{"73","COOPERATIVE","COOPERATIVE","COOP","","0","0"},
		{"74","COOPERATIVES","COOPERATIVES","COOP","","0","0"},
		{"75","CROIX","CROIX","CRX","","0","0"},
	//	{"76","CRX","CROIX","CRX","","0","0"},
		{"77","DELEGATION","DELEGATION","","DELEG","0","0"},
		{"78","DEPARTEMENT","DEPARTEMENT","DEP","DEP","0","0"},
		{"79","DEPARTEMENTAL","DEPARTEMENTAL","DEP","DEP","0","0"},
		{"80","DEPARTEMENTAUX","DEPARTEMENTAUX","DEP","DEP","0","0"},
		{"81","DIRECTION","DIRECTION","DIR","DIR","0","0"},
		//{"82","DIV","DIVISION","DIV","DIV","0","0"},
		{"83","DIVISION","DIVISION","DIV","DIV","0","0"},
		//{"84","ECA","ECART","ECA","","0","0"},
		{"85","ECART","ECART","ECA","","0","0"},
		//{"86","EC","ECOLE","EC","","0","0"},
		{"87","ECOLE","ECOLE","EC","","0","0"},
		{"88","ECONOMIE","ECONOMIE","","ECO","0","0"},
		{"89","ECONOMIQUE","ECONOMIQUE","ECON","ECO","0","0"},
		//{"90","EMP","EMPLACEMENT","EMP","","0","0"},
		{"91","EMPLACEMENT","EMPLACEMENT","EMP","","0","0"},
		{"92","ENSEIGNEMENT","ENSEIGNEMENT","ENST","ENST","0","0"},
		//{"93","ENS","ENSEMBLE","ENS","ENS","0","0"},
		//{"94","ENSE","ENSEMBLE","ENS","ENS","0","0"},
		{"95","ENSEMBLE","ENSEMBLE","ENS","ENS","0","0"},
		{"96","ENSEMBLES","ENSEMBLES","ENS","ENS","0","0"},
		//{"97","ENTR","ENTREPRISE","ENTR","ENTR","0","0"},
		{"98","ENTREPRISE","ENTREPRISE","ENTR","ENTR","0","0"},
		{"99","EPOUSE","EPOUSE","EP","EP","0","0"},
		{"100","EPOUX","EPOUX","EP","EP","0","0"},
	//	{"101","ETAB","ETABLISSEMENT","ETS","ETS","0","0"},
		//{"102","ETABLIS","ETABLISSEMENT","ETS","ETS","0","0"},
		{"103","ETABLISSEMENT","ETABLISSEMENT","ETS","ETS","0","0"},
		{"104","ETABLISSEMT","ETABLISSEMENT","ETS","ETS","0","0"},
		//{"105","ETABLT","ETABLISSEMENT","ETS","ETS","0","0"},
		//{"106","ETS","ETABLISSEMENT","ETS","ETS","0","0"},
		{"107","ETABLISSEMENTS","ETABLISSEMENTS","ETS","ETS","0","0"},
		{"108","EUROPE","EUROPE","EUR","","0","0"},
		{"109","EUROPEEN","EUROPEEN","EUR","","0","0"},
		{"110","FAC","FACULTE","FAC","FAC","0","0"},
		{"111","FACULTE","FACULTE","FAC","FAC","0","0"},
		{"112","FORESTIER","FORESTIER","FOR","FOR","0","0"},
		{"113","FORET","FORET","FOR","FOR","0","0"},
		{"114","FOYER#LOGEMENT","FOYER#LOGEMENT","FOYER#LOGEMENT","","0","0"},
		{"115","FOYERS#LOGEMENTS","FOYERS#LOGEMENTS","FOYER#LOGEMENT","","0","0"},
		{"116","FRANCAIS","FRANCAIS","FR","FR","0","0"},
		{"117","FRANCAISE","FRANCAISE","FR","FR","0","0"},
		{"118","FUSILIERS","FUSILIERS","","FUS","0","0"},
		//{"119","GEND","GENDARMERIE","GEND","GEND","0","0"},
		{"120","GENDARMERIE","GENDARMERIE","GEND","GEND","0","0"},
		{"121","GOUVERNEMENTAL","GOUVERNEMENTAL","GOUV","GOUV","0","0"},
		{"121","GROUPE#SCOLAIRE","GROUPE#SCOLAIRE","GROUPE#SCOLAIRE","","3","0"},
		{"121","GROUPE#SCOLAIRE","GROUPE#SCOLAIRE","GROUPE#SCOLAIRE","","3","0"},
		{"122","HALAGE","HALAGE","HLG","","0","0"},
		//{"123","HLG","HALAGE","HLG","","0","0"},
		//{"124","HOP","HOPITAL","HOP","HOP","0","0"},
		{"125","HOPITAL","HOPITAL","HOP","HOP","0","0"},
		{"126","HOPITAUX","HOPITAUX","HOP","HOP","0","0"},
		{"127","HOSPICE","HOSPICE","HOSP","HOSP","0","0"},
	//	{"128","HOT","HOTEL","HOT","HOT","0","0"},
		{"129","HOTEL","HOTEL","HOT","HOT","0","0"},
		{"130","ILOT","ILOT","ILOT","","0","0"},
		{"131","INFANTERIE","INFANTERIE","INF","INFANT","0","0"},
		//{"132","INST","INSTITUT","INST","INST","0","0"},
		{"133","INSTITUT","INSTITUT","INST","INST","0","0"},
		{"134","INTERNATIONAL","INTERNATIONAL","INT","INTERN","0","0"},
		{"135","INTERNATIONALE","INTERNATIONALE","INT","INTERN","0","0"},
	//	{"136","LABO","LABORATOIRE","LABO","LABO","0","0"},
		{"137","LABORATOIRE","LABORATOIRE","LABO","LABO","0","0"},
		//{"138","LOGI","LOGIS","LOGI","","0","0"},
		{"139","LOGIS","LOGIS","LOGI","","0","0"},
		{"140","LYCEE","LYCEE","LYC","","0","0"},
		{"141","MADAME","MADAME","MME","MME","0","0"},
		{"142","ME","MADAME","MME","MME","0","0"},
		{"143","MME","MADAME","MME","MME","0","0"},
		{"144","MADEMOISELLE","MADEMOISELLE","MLLE","MLLE","0","0"},
		//{"145","MELLE","MADEMOISELLE","MLLE","MLLE","0","0"},
		{"146","MLE","MADEMOISELLE","MLLE","MLLE","0","0"},
		{"147","MLLE","MADEMOISELLE","MLLE","MLLE","0","0"},
		{"148","MESDEMOISELLES","MADEMOISELLES","MLLES","MLLES","0","0"},
		{"149","MLES","MADEMOISELLES","MLLES","MLLES","0","0"},
		{"149","MADEMOISELLES","MADEMOISELLES","MLLES","MLLES","0","0"},
		{"150","MLLES","MADEMOISELLES","MLLES","MLLES","0","0"},
		//{"151","MAG","MAGASIN","MAG","MAG","0","0"},
		{"152","MAGASIN","MAGASIN","MAG","MAG","0","0"},
		{"153","MAIRIE","MAIRIE","MRI","","0","0"},
		//{"154","MAIS","MAISON","MAIS","MAIS","0","0"},
		{"155","MAISON","MAISON","MAIS","MAIS","0","0"},
		{"156","MAISONS","MAISONS","MAIS","MAIS","0","0"},
		{"157","MARAIS","MARAIS","MRA","","0","0"},
		//{"158","MRA","MARAIS","MRA","","0","0"},
		{"159","MARITIME","MARITIME","MARIT","MAR","0","0"},
		{"160","MEDICAL","MEDICAL","MED","MED","0","0"},
		{"161","MESDAMES","MESDAMES","MMES","MMES","0","0"},
		{"162","MMES","MESDAMES","MMES","MMES","0","0"},
		{"163","MESSIEURS","MESSIEURS","MM","MM","0","0"},
		{"164","MM","MESSIEURS","MM","MM","0","0"},
		{"165","MRS","MESSIEURS","MM","MM","0","0"},
		//{"166","MIN","MINISTERE","MIN","MIN","0","0"},
		{"167","MINISTERE","MINISTERE","MIN","MIN","0","0"},
		{"168","MONSIEUR","MONSIEUR","M","M","0","0"},
		{"169","MR","MONSIEUR","M","M","0","0"},
		//{"170","MUN","MUNICIPAL","MUN","MUN","0","0"},
		{"171","MUNICIPAL","MUNICIPAL","MUN","MUN","0","0"},
		//{"172","MUT","MUTUEL","MUT","MUT","0","0"},
		{"173","MUTUEL","MUTUEL","MUT","MUT","0","0"},
		{"174","NATIONAL","NATIONAL","NAT","NAL","0","0"},
		{"175","NORD","NORD","NORD","","0","0"},
		//{"176","ND","NOTRE DAME","ND","ND","0","0"},
		{"177","NOTRE DAME","NOTRE DAME","ND","ND","0","0"},
		//{"178","OBS","OBSERVATOIRE","OBS","OBS","0","0"},
		{"179","OBSERVATOIRE","OBSERVATOIRE","OBS","OBS","0","0"},
		/*{"180","POL","POLICE","POL","POL","0","0"},//fae 1.5.1_07
		{"181","POLICE","POLICE","POL","POL","0","0"},*/
		{"182","PREFECTURE","PREFECTURE","PREF","PREF","0","0"},
		{"183","PRESBYTERE","PRESBYTERE","PRBY","","0","0"},
		{"184","PROFESSIONNEL","PROFESSIONNEL","PROF","PROF","0","0"},
		{"185","PROFESSIONNELLE","PROFESSIONNELLE","PROF","PROF","0","0"},
		{"186","PROLONGE","PROLONGE","","PROL","0","0"},
		{"187","PROLONGEE","PROLONGEE","","PROL","0","0"},
		//{"188","PROP","PROPRIETE","PROP","PROP","0","0"},
		{"189","PROPRIETE","PROPRIETE","PROP","PROP","0","0"},
		{"190","REGIMENT","REGIMENT","RGT","RGT","0","0"},
		{"191","RGT","REGIMENT","RGT","RGT","0","0"},
		{"191","RI","REGIMENT#D#INFANTERIE","RI","RI","0","0"},
		//{"192","REGION","REGION","REG","REG","0","0"},
		{"193","REGIONAL","REGIONAL","REG","REG","0","0"},
		{"194","REP","REPUBLIQUE","REP","REP","0","0"},
		//{"195","REPUBLIQUE","REPUBLIQUE","REP","REP","0","0"},
		{"196","REST","RESTAURANT","REST","REST","0","0"},
		//{"197","RESTAURANT","RESTAURANT","REST","REST","0","0"},
		{"198","SAINTES","SAINTES","STES","STES","0","0"},
		{"199","STES","SAINTES","STES","STES","0","0"},
		{"200","SAINTS","SAINTS","STS","STS","0","0"},
		{"201","STS","SAINTS","STS","STS","0","0"},
		{"202","SANA","SANATORIUM","SANA","SANA","0","0"},
		//{"203","SANATORIUM","SANATORIUM","SANA","SANA","0","0"},
		{"204","SCI","SCIENTIFIQUE","SCI","","0","0"},
		//{"205","SCIENTIFIQUE","SCIENTIFIQUE","SCI","","0","0"},
		{"206","SEMINAIRE","SEMINAIRE","SEMI","","0","0"},
		{"207","SEMINAIRE","SEMINAIRE","SEMI","","0","0"},
		{"208","SCE","SERVICE","SCE","SCE","0","0"},
		//{"209","SERVICE","SERVICE","SCE","SCE","0","0"},
		{"210","SOC","SOCIETE","SOC","SOC","0","0"},
		//{"211","SOCIETE","SOCIETE","SOC","SOC","0","0"},
		{"212","SOCIETES","SOCIETES","SOC","SOC","0","0"},
		{"213","SC","SOUS COUVERT","SC","SC","0","0"},
		//{"214","SOUS SOUVERT","SOUS COUVERT","SC","SC","0","0"},
		{"215","SOUS PREFECTURE","SOUS PREFECTURE","SPREF","SPREF","0","0"},
		{"216","SUD","SUD","SUD","","0","0"},
		{"217","SYNDICAT","SYNDICAT","SYND","SYND","0","0"},
		{"218","TECHNIQUE","TECHNIQUE","TECH","TECH","0","0"},
		{"219","TER","TER","T","T","0","0"},
		{"220","TIR","TIRAILLEUR","TIR","","0","0"},
		//{"221","TIRAILLEURS","TIRAILLEURS","TIR","","0","0"},
		{"222","TIRAILLEUR","TIRALLEUR","TIR","","0","0"},
		{"223","TUN","TUNNEL","TUN","TUN","0","0"},
		//{"224","TUNEL","TUNNEL","TUN","TUN","0","0"},
		{"225","UNIV","UNIVERSITE","UNIV","UNIV","0","0"},
		//{"226","UNIVERSITE","UNIVERSITE","UNIV","UNIV","0","0"},
		{"227","USI","USINE","USI","","0","0"},
		//{"228","USINE","USINE","USI","","0","0"},
		{"229","VELODROME","VELODROME","VELO","VELOD","0","0"},
		{"230","VEUVE","VEUVE","VVE","VVE","0","0"},
		{"231","VVE","VEUVE","VVE","VVE","0","0"},
		//{"232","VILLE","VILLE","VIL","","0","0"},
		{"233","VILLES","VILLES","VIL","","0","0"},
		{"234","C/O","CHEZ","CHEZ","","8","8"},
		{"235","CHEZ","CHEZ","CHEZ","","8","8"},
		{"236","CO","CHEZ","CHEZ","","8","8"},
		{"237","CZ","CHEZ","CHEZ","","8","8"},
		{"238","PORTE","PORTE","PTE","","8","9"},
		{"239","PT","PORTE","PTE","","8","9"},
		{"240","PTE","PORTE","PTE","","8","9"},
		{"241","PCE","PIECE","PIECE","","8","10"},//fev 010500_03
		{"242","PIECE","PIECE","PIECE","","8","10"},//fev 010500_03
		{"243","PIECES","PIECES","PIECE","","8","10"},//fev 010500_03
		{"244","LGMT","LOGEMENT","LOGT","","2","11"},
		{"245","LGT","LOGEMENT","LOGT","","8","11"},//fev 010500_03
		{"246","LOG","LOGEMENT","LOGT","","8","11"},//fev 010500_03
		{"247","LOGEMENT","LOGEMENT","LOGT","","8","11"},//fev 010500_03
		{"248","LOGEMT","LOGEMENT","LOGT","","8","11"},//fev 010500_03
		{"249","LOGT","LOGEMENT","LOGT","","8","11"},//fev 010500_03
		{"250","LOGEMENTS","LOGEMENTS","LOGT","","8","11"},//fev 010500_03
		{"251","APP","APPARTEMENT","APP","APP","2","12"},
		{"252","APPA","APPARTEMENT","APP","APP","2","12"},
		{"253","APPART","APPARTEMENT","APP","APP","2","12"},
		{"254","APPARTEMENT","APPARTEMENT","APP","APP","2","12"},
		{"255","APPRT","APPARTEMENT","APP","APP","2","12"},
		{"256","APPT","APPARTEMENT","APP","APP","2","12"},
		{"257","APT","APPARTEMENT","APP","APP","2","12"},
		{"257","AP","APPARTEMENT","APP","APP","2","12"},
		{"258","APPARTEMENTS","APPARTEMENTS","APP","APP","2","12"},
		{"259","CHAM","CHAMBRE","CHBR","CHBR","8","13"},//fev 010500_03
		{"260","CHAMB","CHAMBRE","CHBR","CHBR","8","13"},//fev 010500_03
		{"261","CHAMBRE","CHAMBRE","CHBR","CHBR","8","13"},//fev 010500_03
		{"262","CHB","CHAMBRE","CHBR","CHBR","8","13"},//fev 010500_03
		{"263","CHBR","CHAMBRE","CHBR","CHBR","8","13"},//fev 010500_03
		{"264","CHBRE","CHAMBRE","CHBR","CHBR","8","13"},//fev 010500_03
		{"265","CHAMBRES","CHAMBRES","CHBR","CHBR","8","13"},//fev 010500_03
		{"266","CLR","COULOIR","CLR","","8","14"},//fev 010500_03
		{"267","COUL","COULOIR","CLR","","8","14"},//fev 010500_03
		{"268","COULOIR","COULOIR","CLR","","8","14"},//fev 010500_03
		{"269","COULOIRS","COULOIRS","CLR","","8","14"},//fev 010500_03
		{"270","ETAG","ETAGE","ETG","ETG","8","15"},//fev 010500_03//ai3_010000_03_FEV (on echange les poids)
		{"271","ETAGE","ETAGE","ETG","ETG","8","15"},//fev 010500_03
		{"272","ETG","ETAGE","ETG","ETG","8","15"},//fev 010500_03
		{"273","ETGE","ETAGE","ETG","ETG","8","15"},//fev 010500_03
		{"274","ETAGES","ETAGES","ETG","ETG","8","15"},//fev 010500_03
		{"275","DEG","DEGRE","DEG","","0","16"},//fev 1.3.2 2
		{"276","DEGRE","DEGRE","DEG","","0","16"},//fev 1.3.2 2
		{"277","DEGRES","DEGRES","DEG","","0","16"},//fev 1.3.2 2
		{"278","PAL","PALIER","PALIER","","8","17"},//FAE 1.5.0 21
		{"279","PALI","PALIER","PALIER","","8","17"},//FAE 1.5.0 21
		{"280","PALIER","PALIER","PALIER","","8","17"},//FAE 1.5.0 21
		{"281","PLR","PALIER","PALIER","","8","17"},//FAE 1.5.0 21
		{"282","PALIERS","PALIERS","PALIER","","8","17"},//FAE 1.5.0 21
		{"283","CIDEX","CIDEX","CIDEX","CIDEX","2","18"},
		{"284","BAL","BAL","BAL","BAL","8","19"},//fev 010500_03
		{"285","BOITE#A#LETTRE","BAL","BAL","BAL","8","19"},//fev 010500_03
		{"286","BOITE#A#LETTRES","BAL","BAL","BAL","8","19"},//fev 010500_03
		{"287","BOITE#AUX#LETTRES","BAL","BAL","BAL","8","19"},//fev 010500_03
		{"288","BOITE#LETTRE","BAL","BAL","BAL","8","19"},//fev 010500_03
		{"289","BOITE#LETTRES","BAL","BAL","BAL","8","19"},//fev 010500_03
		{"290","BTE","BOITE","","","8","20"},//fev 010500_03
		{"291","BOITE","BOITE","","","8","20"},//fev 010500_03
		{"291","RCH","REZ#DE#CHAUSSEE","RDC","","2","21"},
		{"292","RDC","REZ#DE#CHAUSSEE","RDC","","2","21"},
		{"293","RDZ","REZ#DE#CHAUSSEE","RDC","","2","21"},
		{"294","REZ#DE#CHAUSSEE","REZ#DE#CHAUSSEE","RDC","","2","21"},
		{"295","FOYER","FOYER","FOYR","","9","22"},
		{"296","FOYR","FOYER","FOYR","","9","22"},
		{"297","FOYERS","FOYERS","FOYR","","9","22"},
		{"298","ESC","ESCALIER","ESC","","8","15"},//ai3_010000_03_FEV
		{"299","ESCA","ESCALIER","ESC","","8","15"},
		{"300","ESCAL","ESCALIER","ESC","","8","15"},
		{"301","ESCALIER","ESCALIER","ESC","","8","15"},
		{"302","ESCALIERS","ESCALIERS","ESC","","8","15"},
		{"303","MF","MAISON FORESTIERE","MF","","4","31"},
		{"304","MAN","MANOIR","MAN","","0","32"},//fev 1.3.2 2
		{"305","MANOIR","MANOIR","MAN","","0","32"},//fev 1.3.2 2
		{"306","VIA","VIA","VIA","","4","33"},
		{"307","VILL","VILLA","VLA","","9","34"},
		{"308","VILLA","VILLA","VLA","","9","34"},
		{"309","VLA","VILLA","VLA","","9","34"},
		{"310","VLA","VILLA","VLA","","9","34"},
		{"311","VIL","VILLA ","VLA","","9","34"},
		{"312","VILLAS","VILLAS","VLA","","9","34"},
		{"313","MUS","MUSEE","MUS","","0","35"},//fev 1.3.2 2
		{"314","MUSEE","MUSEE","MUS","","0","35"},//fev 1.3.2 2
		{"315","MLN","MOULIN","MLN","","4","36"},
		{"316","MOULIN","MOULIN","MLN","","4","36"},
		{"317","MOULINS","MOULINS","MLN","","4","36"},
		{"318","ENT","ENTREE","ENT","ENT","9","37"},//fev 010500_03
		{"319","ENTR","ENTREE","ENT","ENT","9","37"},//fev 010500_03
		{"320","ENTREE","ENTREE","ENT","ENT","9","37"},//fev 010500_03
		{"321","ENTREES","ENTREES","ENT","ENT","9","37"},//fev 010500_03
		{"322","BAT","BATIMENT","BAT","BAT","9","38"},//fev 010500_03
		{"323","BATI","BATIMENT","BAT","BAT","9","38"},//fev 010500_03
		{"324","BATIMENT","BATIMENT","BAT","BAT","9","38"},//fev 010500_03
		{"325","BATIMNT","BATIMENT","BAT","BAT","9","38"},//fev 010500_03
		{"326","BATIMT","BATIMENT","BAT","BAT","9","38"},//fev 010500_03
		{"327","BT","BATIMENT","BAT","BAT","9","38"},//fev 010500_03
		{"328","BATIMENTS","BATIMENTS","BAT","BAT","9","38"},//fev 010500_03
		{"329","PAV","PAVILLON","PAV","","0","39"},//fev 1.3.2 2
		{"330","PAVI","PAVILLON","PAV","","0","39"},//fev 1.3.2 2
		{"331","PAVILL","PAVILLON","PAV","","0","39"},//fev 1.3.2 2
		{"332","PAVILLON","PAVILLON","PAV","","0","39"},//fev 1.3.2 2
		{"333","PAVILLONS","PAVILLONS","PAV","","0","39"},//fev 1.3.2 2
		{"333","PAVS","PAVILLONS","PAV","","0","39"},//fev 1.3.2 2
		{"334","IMBLE","IMMEUBLE","IMM","IMM","9","41"},
		{"335","IMM","IMMEUBLE","IMM","IMM","9","41"},
		{"336","IMME","IMMEUBLE","IMM","IMM","9","41"},
		{"337","IMMEUB","IMMEUBLE","IMM","IMM","9","41"},
		{"338","IMMEUBLE","IMMEUBLE","IMM","IMM","9","41"},
		{"339","IMMEUBLES","IMMEUBLES","IMM","IMM","9","41"},
		{"340","HLM","HLM","HLM","","9","42"},
		{"341","HLMS","HLMS","HLM","","9","42"},
		{"342","TOUR","TOUR","TOUR","","9","43"},
		{"343","TR","TOUR","TOUR","","9","43"},
		{"344","TOURS","TOURS","TOUR","","9","43"},
		{"345","RCE","RESIDENCE","RES","RES","9","44"},
		{"346","RDCE","RESIDENCE","RES","RES","9","44"},
		{"347","RES","RESIDENCE","RES","RES","9","44"},
		{"348","RESD","RESIDENCE","RES","RES","9","44"},
		{"349","RESI","RESIDENCE","RES","RES","9","44"},
		{"350","RESID","RESIDENCE","RES","RES","9","44"},
		{"351","RESIDENCE","RESIDENCE","RES","RES","9","44"},
		{"352","RSE","RESIDENCE","RES","RES","9","44"},
		{"353","RESIDENCES","RESIDENCES","RES","","9","44"},
		{"354","CITE#HLM","CITE#HLM","CITE#HLM","","9","46"},
		{"355","CHP","CHAPELLE","CHP","","0","48"},//fev 1.3.2 2
		{"356","CHAPELLE","CHAPELLE","CHP","","0","48"},//fev 1.3.2 2
		{"357","CHAPELLES","CHAPELLES","CHP","","0","48"},//fev 1.3.2 2
		{"358","EGL","EGLISE","EGL","","4","49"},
		{"359","EGLISE","EGLISE","EGL","","0","49"},//fev 1.3.2 2
		{"360","EGLISES","EGLISES","EGL","","0","49"},//fev 1.3.2 2
		{"361","CHAT","CHATEAU","CHT","","4","50"},
		{"362","CHATEAU","CHATEAU","CHT","","4","50"},
		{"363","CHT","CHATEAU","CHT","","4","50"},
		{"364","CHATEAUX","CHATEAUX","CHT","","4","50"},
		{"365","PALAIS","PALAIS","PAL","","4","51"},
		{"366","CASTEL","CASTEL","CST","","4","52"},
		{"367","CST","CASTEL","CST","","4","52"},
		{"368","CHALET","CHALET","CHL","","9","53"},
		{"369","CHL","CHALET","CHL","","9","53"},
		{"370","CHALETS","CHALETS","CHL","","9","53"},
		/*{"371","EN","ENCEINTE","EN","","4","55"},*/
		{"372","ENCEINTE","ENCEINTE","EN","","0","55"},//fev 1.3.2 2
		{"373","FORT","FORT","FORT","","0","56"},//fev 1.3.2 2
		{"374","ENC","ENCLOS","ENC","","0","57"},//fev 1.3.2 2
		{"375","ENCLOS","ENCLOS","ENC","","0","57"},//fev 1.3.2 2
		{"376","GRI","GRILLE","GRI","","0","58"},//fev 1.3.2 2
		{"377","GRILLE","GRILLE","GRI","","0","58"},//fev 1.3.2 2
		{"378","GRILLES","GRILLES","GRI","","0","58"},//fev 1.3.2 2
		{"379","GARENNE","GARENNE","GARN","","0","59"},//fev 1.3.2 2
		{"380","GARENNES","GARENNE","GARN","","0","59"},//fev 1.3.2 2
		{"381","GARN","GARENNE","GARN","","0","59"},//fev 1.3.2 2
		{"382","ENCLAV","ENCLAVE","ENV","","0","60"},//fev 1.3.2 2
		{"383","ENCLAVE","ENCLAVE","ENV","","0","60"},//fev 1.3.2 2
		{"384","ENV","ENCLAVE","ENV","","0","60"},//fev 1.3.2 2
		{"385","ENCLAVES","ENCLAVES","ENV","","0","60"},//fev 1.3.2 2
		{"386","CENTRAL","CENTRAL","CTRE","","8","61"},//fae 1.4.2 01
		{"387","CENTRE","CENTRE","CTRE","CTRE","8","61"},//fae 1.4.2 01
		{"388","CTRE","CENTRE","CTRE","CTRE","8","61"},//fae 1.4.2 01
		{"389","CENTR","CENTRE ","CTRE","CTRE","8","61"},//fae 1.4.2 01
		{"390","CCAL","CENTRE#COMMERCIAL","CCAL","CCAL","4","62"},
		{"391","CCIAL","CENTRE#COMMERCIAL","CCAL","CCAL","4","62"},
		{"392","CENTRE#COMMERCIAL","CENTRE#COMMERCIAL","CCAL","CCAL","4","62"},
		{"393","GPE","GROUPE","GPE","","0","65"},//fev 1.3.2 2
		{"394","GROUP","GROUPE","GPE","","0","65"},//fev 1.3.2 2
		{"395","GROUPE","GROUPE","GPE","","0","65"},//fev 1.3.2 2
		{"396","GROUPES","GROUPES","GPE","","0","65"},//fev 1.3.2 2
		{"397","GPT","GROUPEMENT","GPT","","0","66"},//fev 1.3.2 2
		{"398","GROUPEMENT","GROUPEMENT","GPT","","0","66"},//fev 1.3.2 2
		{"399","GROUPEMENTS","GROUPEMENTS","GPT","","0","66"},//fev 1.3.2 2
		{"400","GA","GALERIE","GAL","","0","67"},//fev 1.3.2 2
		{"401","GAL","GALERIE","GAL","","0","67"},//fev 1.3.2 2
		{"402","GALERIE","GALERIE","GAL","","0","67"},//fev 1.3.2 2
		{"403","GALERIES","GALERIES","GAL","","0","67"},//fev 1.3.2 2
		{"404","STA","STATION","STA","","0","68"},//fev 1.3.2 2
		{"405","STATION","STATION","STA","","0","68"},//fev 1.3.2 2
		{"406","STATIONS","STATIONS","STA","","0","68"},//fev 1.3.2 2
		{"407","GARE","GARE","GARE","","0","69"},//fev 1.3.2 2
		{"408","GARES","GARES","GARE","","0","69"},//fev 1.3.2 2
		{"409","HALLE","HALLE","HLE","","9","71"},
		{"410","HLE","HALLE","HLE","","9","71"},
		{"411","HALLES","HALLES","HLE","","9","71"},
		{"412","TE","TERRASSE","TSSE","","0","72"},//fev 1.3.2 2
		{"413","TERR","TERRASSE","TSSE","","0","72"},//fev 1.3.2 2
		{"414","TERRASSE","TERRASSE","TSSE","","0","72"},//fev 1.3.2 2
		{"415","TSSE","TERRASSE","TSSE","","0","72"},//fev 1.3.2 2
		{"416","TERRASSES","TERRASSES","TSSE","","0","72"},//fev 1.3.2 2
		{"417","TERRAIN","TERRAIN","TRN","","4","73"},
		{"418","TRN","TERRAIN","TRN","","4","73"},
		{"419","DESCENT","DESCENTE","DSC","","0","74"},//fev 1.3.2 2
		{"420","DESCENTE","DESCENTE","DSC","","0","74"},//fev 1.3.2 2
		{"421","DSC","DESCENTE","DSC","","0","74"},//fev 1.3.2 2
		{"422","DESCENTES","DESCENTES","DSC","","0","74"},//fev 1.3.2 2
		{"423","RAID","RAIDILLON","RAID","","0","75"},//fev 1.3.2 2
		{"424","RAIDILLON","RAIDILLON","RAID","","0","75"},//fev 1.3.2 2
		{"425","RAIDILLONS","RAIDILLONS","RAID","","0","75"},//fev 1.3.2 2
		{"426","RAMPE","RAMPE","RPE","","0","76"},//fev 1.3.2 2
		{"427","RPE","RAMPE","RPE","","0","76"},//fev 1.3.2 2
		{"428","RAMPES","RAMPES","RPE","","0","76"},//fev 1.3.2 2
		{"429","MONTEE","MONTEE","MTE","","4","77"},
		{"430","MTE","MONTEE","MTE","","4","77"},
		{"431","MTEE","MONTEE","MTE","","4","77"},
		{"432","MONTEES","MONTEES","MTE","","4","77"},
		{"433","GRIM","GRIMPETTE","GRIM","","0","78"},//fev 1.3.2 2
		{"434","GRIMPETTE","GRIMPETTE","GRIM","","0","78"},//fev 1.3.2 2
		{"435","LEVE","LEVEE","LEVE","","0","79"},//fev 1.3.2 2
		{"436","LEVEE","LEVEE","LEVE","","0","79"},//fev 1.3.2 2
		{"437","MAR","MARCHE","MAR","","0","80"},//fev 1.3.2 2
		{"438","MARCHE","MARCHE","MAR","","0","80"},//fev 1.3.2 2
		{"439","MARCHES","MARCHES","MAR","","0","80"},//fev 1.3.2 2
		{"440","MET","METRO","MET","","0","81"},//fev 1.3.2 2
		{"441","METRO","METRO","MET","","0","81"},//fev 1.3.2 2
		{"442","TPL","TERRE PLEIN","TPL","","0","82"},//fev 1.3.2 2
		{"443","TERTRE","TERTRE","TRT","","0","83"},//fev 1.3.2 2
		{"444","TRT","TERTRE","TRT","","0","83"},//fev 1.3.2 2
		{"445","TERTRES","TERTRES","TRT","","0","83"},//fev 1.3.2 2
		//{"446","BUT","BUTTE","BUT","","0","84"},//fev 1.3.2 2
		//{"447","BUTT","BUTTE","BUT","","0","84"},//fev 1.3.2 2
		//{"448","BUTTE","BUTTE","BUT","","0","84"},//fev 1.3.2 2
		//{"449","BUTTES","BUTTES","BUT","","0","84"},//fev 1.3.2 2
		{"450","ROC","ROC","ROC","","4","85"},
		{"451","COL","COL","COL","","4","86"},
		{"452","COLI","COLLINE","COLI","","0","87"},//fev 1.3.2 2
		{"453","COLINE","COLLINE","COLI","","0","87"},//fev 1.3.2 2
		{"454","COLLINE","COLLINE","COLI","","0","87"},//fev 1.3.2 2
		{"455","COLLINES","COLLINES","COLI","","0","87"},//fev 1.3.2 2
		{"456","VAL","VAL","VAL","","0","88"},//fev 1.3.2 2
		{"457","VALLEE","VALLEE","VAL","","0","88"},//fev 1.3.2 2
		{"458","VALLEES","VALLEES","VAL","","0","88"},//fev 1.3.2 2
		{"459","VALLON","VALLON","VAL","","0","88"},//fev 1.3.2 2
		{"460","VALLONS","VALLONS","VAL","","0","88"},//fev 1.3.2 2
		{"461","COR","CORNICHE","COR","","0","89"},//fev 1.3.2 2
		{"462","CORNICH","CORNICHE","COR","","0","89"},//fev 1.3.2 2
		{"463","CORNICHE","CORNICHE","COR","","0","89"},//fev 1.3.2 2
		{"464","CORNICHES","CORNICHES","COR","","0","89"},//fev 1.3.2 2
		{"465","COTE","COTE","COTE","","4","90"},
		{"466","COTEAU","COTEAU","COTE","","0","90"},//fev 1.3.2 2
		{"467","COTTEAU","COTEAU","COTE","","0","90"},//fev 1.3.2 2
		{"468","COTEAUX","COTEAUX","COTE","","0","90"},//fev 1.3.2 2
		{"469","COTTEAUX","COTEAUX","COTE","","0","90"},//fev 1.3.2 2
		{"470","COTT","COTTAGE","COTT","","4","91"},
		{"471","COTTAGE","COTTAGE","COTT","","4","91"},
		{"472","COTTAGES","COTTAGES","COTT","","4","91"},
		{"473","CHAUSSE","CHAUSSEE","CHS","","4","93"},
		{"474","CHAUSSEE","CHAUSSEE","CHS","","4","93"},
		{"475","CHS","CHAUSSEE","CHS","","4","93"},
		{"476","CHAUSSEES","CHAUSSEES","CHS","","4","93"},
		{"477","CHEMINEMENT","CHEMINEMENT","CHEM","","0","94"},//fev 1.3.2 2
		{"478","CHEMINEMENTS","CHEMINEMENTS","CHEM","","0","94"},//fev 1.3.2 2
		{"479","PASS","PASSE","PASS","","0","95"},//fev 1.3.2 2
		{"480","PASSE","PASSE","PASS","","0","95"},//fev 1.3.2 2
		{"481","PASSES","PASSES","PASS","","0","95"},//fev 1.3.2 2
		{"482","PAS","PASSAGE","PAS","","4","96"},
		{"483","PASSAGE","PASSAGE","PAS","PAS","4","96"},
		{"484","PASSAGES","PASSAGE","PAS","PAS","4","96"},
		{"485","PGE","PASSAGE","PAS","","4","96"},
		{"486","PASSAGE#A#NIVEAU","PASSAGE#A#NIVEAU","PN","","4","97"},
		{"487","PASSERELLE","PASSERELLE","PLE","","4","98"},
		{"488","PLE","PASSERELLE","PLE","","4","98"},
		{"489","REMPARTS","REMPARTS","REM","","0","99"},//fev 1.3.2 2
		{"490","REM","REMPART","REM","","0","99"},//fev 1.3.2 2
		{"491","REMPART","REMPART","REM","","0","99"},//fev 1.3.2 2
		{"492","PARVIS","PARVIS","PRV","","0","100"},//fev 1.3.2 2
		{"493","PRV","PARVIS","PRV","","0","100"},//fev 1.3.2 2
		{"494","ESP","ESPLANADE","ESP","","4","101"},
		{"495","ESPLANAD","ESPLANADE","ESP","","4","101"},
		{"496","ESPLANADE","ESPLANADE","ESP","","4","101"},
		{"497","ESPLANADES","ESPLANADES","ESP","","0","101"},//fev 1.3.2 2
		{"498","PRO","PROMENADE","PROM","","4","102"},
		{"499","PROM","PROMENADE","PROM","","4","102"},
		{"500","PROMENADE","PROMENADE","PROM","","4","102"},
		{"501","PLACIS","PLACIS","PLCI","","4","103"},
		{"502","PLCI","PLACIS","PLCI","","4","103"},
		{"503","MAIL","MAIL","MAIL","","4","104"},
		{"504","ML","MAIL","MAIL","","4","104"},
		{"505","PAT","PATIO","PAT","","0","105"},//fev 1.3.2 2
		{"506","PATIO","PATIO","PAT","","0","105"},//fev 1.3.2 2
		{"507","FORM","FORUM","FORM","","0","106"},//fev 1.3.2 2
		{"508","FORUM","FORUM","FORM","","0","106"},//fev 1.3.2 2
		{"509","FORUMS","FORUMS","FORM","","0","106"},//fev 1.3.2 2
		{"510","FOS","FOSSE","FOS","","0","107"},//fev 1.3.2 2
		{"511","FOSSE","FOSSE","FOS","","0","107"},//fev 1.3.2 2
		{"512","FOSSES","FOSSES","FOS","","0","107"},//fev 1.3.2 2
		{"513","AIRE","AIRE","AIRE","","0","108"},//fev 1.3.2 2
		{"514","AIRES","AIRES","AIRE","","0","108"},//fev 1.3.2 2
		{"515","ESPA","ESPACE","ESPA","","0","109"},//fev 1.3.2 2
		{"516","ESPAC","ESPACE","ESPA","","0","109"},//fev 1.3.2 2
		{"517","ESPACE","ESPACE","ESPA","","0","109"},//fev 1.3.2 2
		{"518","ESPACES","ESPACES","ESPA","","0","109"},//fev 1.3.2 2
		{"519","STADE","STADE","STDE","","0","110"},//fev 1.3.2 2
		{"520","STDE","STADE","STDE","","0","110"},//fev 1.3.2 2
		{"521","HIP","HIPPODROME","HIP","","0","111"},//fev 1.3.2 2
		{"522","HIPPODROME","HIPPODROME","HIP","","0","111"},//fev 1.3.2 2
		{"523","ETANG","ETANG","ETNG","","0","112"},//fev 1.3.2 2
		{"524","ETNG","ETANG","ETNG","","0","112"},//fev 1.3.2 2
		{"525","ETANGS","ETANGS","ETNG","","0","112"},//fev 1.3.2 2
		{"526","ANSE","ANSE","ANSE","","0","113"},//fev 1.3.2 2
		{"527","CARE","CARRIERE","CARE","","0","114"},//fev 1.3.2 2
		{"528","CARIERE","CARRIERE","CARE","","0","114"},//fev 1.3.2 2
		{"529","CARRIERE","CARRIERE","CARE","","0","114"},//fev 1.3.2 2
		{"530","CARRIERES","CARRIERES","CARE","","0","114"},//fev 1.3.2 2
		{"531","PLAINE","PLAINE","PLN","","0","115"},//fev 1.4.1 14
		{"532","PLN","PLAINE","PLN","","0","115"},//fev 1.4.1 14
		{"533","PLAN","PLAN","PLAN","","4","116"},
		{"534","PLATEAU","PLATEAU","PLT","","0","117"},//fev 1.3.2 2
		{"535","PLT","PLATEAU","PLT","","0","117"},//fev 1.3.2 2
		{"536","PLATEAUX","PLATEAUX","PLT","","0","117"},//fev 1.3.2 2
		{"537","CARR","CARRE ","CARR","","0","118"},//fev 1.3.2 2
		{"538","CARRE","CARRE ","CARR","","0","118"},//fev 1.3.2 2
		{"539","CARREAU","CARREAU","CAU","","0","119"},//fev 1.3.2 2
		{"540","CAU","CARREAU","CAU","","0","119"},//fev 1.3.2 2
		/*{"541","ARC","ARCADE","ARC","","4","120"},
		{"542","ARCAD","ARCADE","ARC","","4","120"},
		{"543","ARCADE","ARCADE","ARC","","4","120"},
		{"544","ARCADES","ARCADES","ARC","","4","120"},*/ // fev 1.3.1 2
		{"545","PORQ","PORTIQUE","PORQ","","0","121"},//fev 1.3.2 2
		{"546","PORTIQUE","PORTIQUE","PORQ","","0","121"},//fev 1.3.2 2
		{"547","PORTIQUES","PORTIQUES","PORQ","","0","121"},//fev 1.3.2 2
		{"548","POCH","PORCHE","POCH","","0","122"},//fev 1.3.2 2
		{"549","PORCHE","PORCHE","POCH","","0","122"},//fev 1.3.2 2
		{"550","PORCHES","PORCHES","POCH","","0","122"},//fev 1.3.2 2
		{"551","PERISTYLE","PERISTYLE","PSTY","","0","123"},//fev 1.3.2 2
		{"552","PERISTYLES","PERISTYLE","PSTY","","0","123"},//fev 1.3.2 2
		{"553","PSTY","PERISTYLE","PSTY","","0","123"},//fev 1.3.2 2
		{"554","CALE","CALE","CALE","","0","124"},//fev 1.3.2 2
		{"555","DARS","DARSE","DARS","","0","125"},//fev 1.3.2 2
		{"556","DARSE","DARSE","DARS","","0","125"},//fev 1.3.2 2
		{"557","BARIERE","BARRIERE","BRE","","0","126"},//fev 1.3.2 2
		{"558","BARRIER","BARRIERE","BRE","","0","126"},//fev 1.3.2 2
		{"559","BARRIERE","BARRIERE","BRE","","0","126"},//fev 1.3.2 2
		{"560","BRE","BARRIERE","BRE","","0","126"},//fev 1.3.2 2
		{"561","BARRIERES","BARRIERES","BRE","","0","126"},//fev 1.3.2 2
		{"562","CAR","CARREFOUR","CAR","","4","127"},
		{"563","CARREF","CARREFOUR","CAR","","4","127"},
		{"564","CARREFOUR","CARREFOUR","CAR","","4","127"},
		{"565","BCLE","BOUCLE","BCLE","","0","128"},//fev 1.3.2 2
		{"566","BOUCLE","BOUCLE","BCLE","","0","128"},//fev 1.3.2 2
		{"567","POUR","POURTOUR","POUR","","0","129"},//fev 1.3.2 2
		{"568","POURTOUR","POURTOUR","POUR","","0","129"},//fev 1.3.2 2
		{"569","POURTOURS","POURTOUR","POUR","","0","129"},//fev 1.3.2 2
		{"570","CONTOUR","CONTOUR","CTR","","0","130"},//fev 1.3.2 2
		{"571","CTR","CONTOUR","CTR","","0","130"},//fev 1.3.2 2
		{"572","CONTOURS","CONTOURS","CTR","","0","130"},//fev 1.3.2 2
		{"573","RAC","RACCOURCI","RAC","","0","131"},//fev 1.3.2 2
		{"574","RACCOURCI","RACCOURCI","RAC","","0","131"},//fev 1.3.2 2
		{"575","RACCOURCIS","RACCOURCIS","RAC","","0","131"},//fev 1.3.2 2
		//{"577","PERIPHERIQUE","PERIPHERIQUE","PERI","","0","132"},//fev 1.3.2 2//version 1.5.4
		//{"577","PERI","PERIPHERIQUE","PERI","","0","132"},//fev 1.3.2 2//version 1.5.4
		{"578","ROC","ROCADE","ROC","","0","133"},//fev 1.3.2 2
		{"579","ROCADE","ROCADE","ROC","","0","133"},//fev 1.3.2 2
		{"580","ROQT","ROQUET","ROQT","","0","134"},//fev 1.3.2 2
		{"581","ROQUET","ROQUET","ROQT","","0","134"},//fev 1.3.2 2
		{"582","ROTONDE","ROTONDE","RTD","","0","135"},//fev 1.3.2 2
		{"583","RTD","ROTONDE","RTD","","0","135"},//fev 1.3.2 2
		{"584","PAR","PARC","PARC","","9","136"},
		{"585","PARC","PARC","PARC","","9","136"},
		{"586","PRC","PARC","PARC","","9","136"},
		{"587","PARCS","PARCS","PARC","","9","136"},
		{"588","PARKINGS","PARGING","PKG","","0","137"},//fev 1.3.2 2
		{"589","PARKING","PARKING","PKG","","0","137"},//fev 1.3.2 2
		{"590","PKG","PARKING","PKG","","0","137"},//fev 1.3.2 2
		{"591","BOIS","BOIS","BOIS","","4","138"},
		{"592","BER","BERGE","BER","","0","139"},//fev 1.3.2 2
		{"593","BERGE","BERGE","BER","","0","139"},//fev 1.3.2 2
		{"594","BERGES","BERGES","BER","","0","139"},//fev 1.3.2 2
		{"595","PLAG","PLAGE","PLAG","","0","140"},
		{"596","PLAGE","PLAGE","PLAG","","0","140"},
		{"597","PLAGES","PLAGES","PLAG","","0","140"},
		{"598","PNT","POINTE","PNT","","0","141"},//fev 1.3.2 2
		{"599","POINTE","POINTE","PNT","","0","141"},//fev 1.3.2 2
		{"600","JETEE","JETEE","JTE","","0","142"},//fev 1.3.2 2
		{"601","JTE","JETEE","JTE","","0","142"},//fev 1.3.2 2
		{"602","JETEES","JETEES","JTE","","0","142"},//fev 1.3.2 2
		{"603","PORT","PORT","PORT","","0","143"},//fev 1.3.2 2
		{"604","PONT","PONT","PONT","","4","144"},
		{"605","PONTS","PONTS","PONT","","4","144"},
		{"606","DIG","DIGUE","DIG","","0","145"},//fev 1.3.2 2
		{"607","DIGUE","DIGUE","DIG","","0","145"},//fev 1.3.2 2
		{"608","DIGUES","DIGUES","DIG","","0","145"},//fev 1.3.2 2
		//{"609","ECL","ECLUSE","ECL","","4","146"}, //v1.5.3
		//{"610","ECLUSE","ECLUSE","ECL","","4","146"}, //v1.5.3
		//{"611","ECLUSES","ECLUSES","ECL","","4","146"}, //v1.5.3
		{"612","CHARMILLE","CHARMILLE","CHI","","0","147"},//fev 1.3.2 2
		{"613","CHI","CHARMILLE","CHI","","0","147"},//fev 1.3.2 2
		{"614","FON","FONTAINE","FON","","0","148"},//fev 1.3.2 2
		{"615","FONTAIN","FONTAINE","FON","","0","148"},//fev 1.3.2 2
		{"616","FONTAINE","FONTAINE","FON","","0","148"},//fev 1.3.2 2
		{"617","FONTAINES","FONTAINES","FON","","0","148"},//fev 1.3.2 2
		{"618","PRE","PRE","PRE","","4","149"},
		{"619","JARD","JARDIN","JARD","","0","150"},//fev 1.3.2 2
		{"620","JARDIN","JARDIN","JARD","","0","150"},//fev 1.3.2 2
		{"621","JARDINS","JARDINS","JARD","","0","150"},//fev 1.3.2 2
		{"622","QRT","QUARTIER","QUA","","4","151"},
		{"623","QUA","QUARTIER","QUA","","4","151"},
		{"624","QUAR","QUARTIER","QUA","","4","151"},
		{"625","QUART","QUARTIER","QUA","","4","151"},
		{"626","QUARTIER","QUARTIER","QUA","","4","151"},
		{"627","QUATIER","QUARTIER","QUA","","4","151"},
		{"628","QUARTIERS","QUARTIERS","QUA","","4","151"},
		{"629","DOM","DOMAINE","DOM","","9","152"},
		{"630","DOMAIN","DOMAINE","DOM","","9","152"},
		{"631","DOMAINE","DOMAINE","DOM","","9","152"},
		{"632","DOMAINES","DOMAINES","DOM","","9","152"},
		{"633","LOT","LOTISSEMENT","LOT","LOT","9","153"},
		{"634","LOTIS","LOTISSEMENT","LOT","LOT","9","153"},
		{"635","LOTISS","LOTISSEMENT","LOT","LOT","9","153"},
		{"636","LOTISSEMENT","LOTISSEMENT","LOT","LOT","9","153"},
		{"637","LOTISSEMNT","LOTISSEMENT","LOT","LOT","9","153"},
		{"638","LOTISSEMENTS","LOTISSEMENTS","LOT","LOT","9","153"},
		{"639","CAMP","CAMP","CAMP","","4","154"},
		{"640","CAMPS","CAMPS","CAMP","","0","154"},//fev 1.3.2 2
		{"641","CAMPAGNE","CAMPAGNE","CGNE","","4","155"},
		{"642","CGNE","CAMPAGNE","CGNE","","4","155"},
		{"643","CAMPAGNES","CAMPAGNES","CGNE","","4","155"},
		{"644","FERME","FERME","FRM","","4","156"},
		{"645","FRM","FERME","FRM","","4","156"},
		{"646","FERMES","FERMES","FRM","","4","156"},
		{"647","CAMPING","CAMPING","CPG","","0","157"},//fev 1.3.2 2
		{"648","CPG","CAMPING","CPG","","0","157"},//fev 1.3.2 2
		{"649","MAS","MAS","MAS","","9","158"},
		{"650","VGE","VILLAGE","VGE","VLGE","4","159"},
		{"651","VILLAGE","VILLAGE","VGE","VLGE","4","159"},
		{"652","VLGE","VILLAGE","VGE","VLGE","4","159"},
		{"653","VILLAGES","VILLAGES","VGE","","4","159"},
		{"654","AGGL","AGGLOMERATION","AGL","","0","160"},//fev 1.3.2 2
		{"655","AGGLOMERATION","AGGLOMERATION","AGL","","0","160"},//fev 1.3.2 2
		{"656","AGL","AGGLOMERATION","AGL","","0","160"},//fev 1.3.2 2
		{"657","FAUBOUR","FAUBOURG","FG","","4","161"},
		{"658","FAUBOURG","FAUBOURG","FG","","4","161"},
		{"659","FBG","FAUBOURG","FG","","4","161"},
		{"660","FG","FAUBOURG","FG","","4","161"},
		{"661","FAUBOURGS","FAUBOURGS","FG","","4","161"},
		{"662","BAST","BASTION","BAST","","0","162"},//fev 1.3.2 2
		{"663","BASTION","BASTION","BAST","","0","162"},//fev 1.3.2 2
		{"664","BEGI","BEGUINAGE","BEGI","","0","163"},//fev 1.3.2 2
		{"665","BEGUINAGE","BEGUINAGE","BEGI","","0","163"},//fev 1.3.2 2
		{"666","BEGUINAGES","BEGUINAGES","BEGI","","0","163"},//fev 1.3.2 2
		{"667","BOURG","BOURG","BRG","","4","164"},
		{"668","BRG","BOURG","BRG","","4","164"},
		{"669","ABBAYE","ABBAYE","ABE","","0","165"},//fev 1.3.2 2
		{"670","ABE","ABBAYE","ABE","","0","165"},//fev 1.3.2 2
		{"671","BASTIDE","BASTIDE","BSTD","","4","166"},
		{"672","BSTD","BASTIDE","BSTD","","4","166"},
		{"673","BASTIDES","BASTIDES","BSTD","","4","166"},
		{"674","CIT","CITE","CITE","","9","167"},
		{"675","CITE","CITE","CITE","","9","167"},
		{"676","CITES","CITES","CITE","","9","167"},
		{"677","CLOS","CLOS","CLOS","","4","168"},
		{"678","CLOI","CLOITRE","CLOI","","0","169"},//fev 1.3.2 2
		{"679","CLOITRE","CLOITRE","CLOI","","0","169"},//fev 1.3.2 2
		{"680","CLOITRES","CLOITRES","CLOI","","0","169"},//fev 1.3.2 2
		{"681","PRESQU ILE","PRESQU ILE","PRQ","","0","170"},//fev 1.3.2 2
		{"682","PRQ","PRESQU ILE","PRQ","","0","170"},//fev 1.3.2 2
		{"683","ILE","ILE","ILE","","0","171"},//fev 1.3.2 2
		{"684","ILES","ILES","ILE","","0","171"},//fev 1.3.2 2
		{"685","HAM","HAMEAU","HAM","","4","172"},
		{"686","HAME","HAMEAU","HAM","","4","172"},
		{"687","HAMEAU","HAMEAU","HAM","","4","172"},
		{"688","HAMEAUX","HAMEAUX","HAM","","4","172"},
		{"689","ZONE","ZONE","ZONE","","4","173"},
		{"690","ZONE#A#URBANISER#EN#PRIORITE","ZONE#A#URBANISER#EN#PRIORITE","ZUP","","9","174"},
		{"691","ZUP","ZONE#A#URBANISER#EN#PRIORITE","ZUP","","9","174"},
		{"692","ZONE#ARTISANALE","ZONE#ARTISANALE","ZA","","9","175"},
		{"693","ZA","ZONE#D#ACTIVITE","ZA","ZA","9","175"},
		{"694","ZONE#D#ACTIVITE","ZONE#D#ACTIVITE","ZA","ZA","9","175"},
		{"695","ZAC","ZONE#D#AMENAGEMENT#CONCERTE","ZAC","ZAC","9","176"},
		{"696","ZONE#D#AMENAGEMENT#CONCERTE","ZONE#D#AMENAGEMENT#CONCERTE","ZAC","ZAC","9","176"},
		{"697","ZAD","ZONE#D#AMENAGEMENT#DIFFERE","ZAD","ZAD","9","177"},
		{"698","ZONE#D#AMENAGEMENT#DIFFERE","ZONE#D#AMENAGEMENT#DIFFERE","ZAD","ZAD","9","177"},
		{"699","ZI","ZONE#INDUSTRIELLE","ZI","ZI","9","178"},
		{"700","ZONE#INDUSTRIELLE","ZONE#INDUSTRIELLE","ZI","ZI","9","178"},
		{"701","ALL","ALLEE","ALL","ALL","9","179"},
		{"702","ALLE","ALLEE","ALL","ALL","9","179"},
		{"703","ALLEE","ALLEE","ALL","ALL","9","179"},
		{"704","ALLEES","ALLEES","ALL","","9","179"},
		{"705","PETITE#ALLEE","PETITE#ALLEE","PTA","","4","180"},
		/*{"705","PTE#ALLEE","PETITE#ALLEE","PTA","","4","180"},*/
		{"706","PETITES#ALLEES","PETITES#ALLEES","PTA","","4","180"},
		{"706","PTES#ALLEES","PETITES#ALLEES","PTA","","4","180"},
		{"706","PTS#ALLEES","PETITES#ALLEES","PTA","","4","180"},
		{"706","PT#ALLEES","PETITES#ALLEES","PTA","","4","180"},
		{"707","PTA","PETITE#ALLEE","PTA","","4","180"},
		{"707","PTE#ALLEE","PETITE#ALLEE","PTA","","4","180"},
		{"707","PT#ALLEE","PETITE#ALLEE","PTA","","4","180"},
		{"707","PTE#ALL","PETITE#ALLEE","PTA","","4","180"},
		{"707","PT#ALL","PETITE#ALLEE","PTA","","4","180"},
		{"708","AV","AVENUE","AV","AV","4","181"},
		{"709","AVE","AVENUE","AV","AV","4","181"},
		{"710","AVENU","AVENUE","AV","AV","4","181"},
		{"711","AVENUE","AVENUE","AV","AV","4","181"},
		{"712","AVENUES","AVENUE","AV","","4","181"},
		{"713","PAE","PETITE#AVENUE","PAE","","4","182"},
		{"713","PT#AVENUE","PETITE#AVENUE","PAE","","4","182"},
		{"713","PTE#AVENUE","PETITE#AVENUE","PAE","","4","182"},
		{"713","PT#AV","PETITE#AVENUE","PAE","","4","182"},
		{"713","PTE#AV","PETITE#AVENUE","PAE","","4","182"},
		{"714","PETITE#AVENUE","PETITE#AVENUE","PAE","","4","182"},
		{"715","BOULEVARD","BOULEVARD","BD","BD","4","183"},
		{"715","BD","BOULEVARD","BD","BD","4","183"},
		{"716","BLD","BOULEVARD","BD","BD","4","183"},
		{"717","BLV","BOULEVARD","BD","BD","4","183"},
		{"718","BLVD","BOULEVARD","BD","BD","4","183"},
		{"719","BOUL","BOULEVARD","BD","BD","4","183"},
		{"720","BOULEVRD","BOULEVARD","BD","BD","4","183"},
		{"721","BOULV","BOULEVARD","BD","BD","4","183"},
		{"722","BOULVARD","BOULEVARD","BD","BD","4","183"},
		{"723","BOULVD","BOULEVARD","BD","BD","4","183"},
		{"724","BRD","BOULEVARD","BD","BD","4","183"},
		{"725","BV","BOULEVARD","BD","BD","4","183"},
		{"726","BVD","BOULEVARD","BD","BD","4","183"},
		{"727","IMP","IMPASSE","IMP","IMP","4","184"},
		{"728","IMPA","IMPASSE","IMP","IMP","4","184"},
		{"729","IMPAS","IMPASSE","IMP","IMP","4","184"},
		{"730","IMPASE","IMPASSE","IMP","IMP","4","184"},
		{"731","IMPASSE","IMPASSE","IMP","IMP","4","184"},
		{"732","IMPASSES","IMPASSES","IMP","","4","184"},
		{"733","PETITE#IMPASSE","PETITE#IMPASSE","PIM","","4","185"},
		{"733","PT#IMPASSE","PETITE#IMPASSE","PIM","","4","185"},
		{"733","PTE#IMPASSE","PETITE#IMPASSE","PIM","","4","185"},
		{"734","PIM","PETITE#IMPASSE","PIM","","4","185"},
		{"735","LD","LIEU#DIT","LD","LD","4","186"},
		{"736","LDIT","LIEU#DIT","LD","LD","4","186"},
		{"737","LDT","LIEU#DIT","LD","LD","4","186"},
		{"738","LIEU#DIT","LIEU#DIT","LD","LD","4","186"},
		{"739","LIEUDIT","LIEU#DIT","LD","LD","4","186"},
		{"740","PL","PLACE","PL","PL","4","187"},
		{"741","PLA","PLACE","PL","PL","4","187"},
		{"742","PLACE","PLACE","PL","PL","4","187"},
		{"743","PLCE","PLACE","PL","PL","4","187"},
		{"744","ROND POINT","ROND#POINT","RPT","RPT","4","188"},
		{"745","RP","ROND#POINT","RPT","RPT","4","188"},
		{"746","RPT","ROND#POINT","RPT","RPT","4","188"},
		{"747","ROUT","ROUTE","RTE","RTE","4","189"},
		{"748","ROUTE","ROUTE","RTE","RTE","4","189"},
		{"749","RT","ROUTE","RTE","RTE","4","189"},
		{"750","RTE","ROUTE","RTE","RTE","4","189"},
		{"751","ROUTES","ROUTES","RTE","","4","189"},
		{"752","AUT","AUTOROUTE","AUT","","4","190"},
		{"753","AUTOROUTE","AUTOROUTE","AUT","","4","190"},
		//{"754","ANCIENNE#ROUTE","ANCIENNE#ROUTE","ART","","4","191"},//version 1.5.4
		//{"755","ART","ANCIENNE#ROUTE","ART","","4","191"},//version 1.5.4
		//{"756","ANCIENNES#ROUTES","ANCIENNES#ROUTES","ART","","4","191"},//version 1.5.4
		{"757","NOUVELLE#ROUTE","NOUVELLE#ROUTE","NTE","","4","192"},
		{"758","NTE","NOUVELLE#ROUTE","NTE","","4","192"},
		{"759","PETITE#ROUTE","PETITE#ROUTE","PRT","","4","193"},
		{"759","PTE#ROUTE","PETITE#ROUTE","PRT","","4","193"},
		{"759","PT#ROUTE","PETITE#ROUTE","PRT","","4","193"},
		{"759","PT#RTE","PETITE#ROUTE","PRT","","4","193"},
		{"759","PTE#RTE","PETITE#ROUTE","PRT","","4","193"},
		{"760","PRT","PETITE#ROUTE","PRT","","4","193"},
		{"761","VIEILLE#ROUTE","VIEILLE#ROUTE","VTE","","4","194"},
		{"761","V#RTE","VIEILLE#ROUTE","VTE","","4","194"},
		{"762","VTE","VIEILLE#ROUTE","VTE","","4","194"},
		{"763","SQ","SQUARE","SQ","SQ","4","195"},
		{"764","SQAURE","SQUARE","SQ","SQ","4","195"},
		{"765","SQE","SQUARE","SQ","SQ","4","195"},
		{"766","SQR","SQUARE","SQ","SQ","4","195"},
		{"767","SQRE","SQUARE","SQ","SQ","4","195"},
		{"768","SQU","SQUARE","SQ","SQ","4","195"},
		{"769","SQUARE","SQUARE","SQ","SQ","4","195"},
		{"770","SENTE","SENTE","SEN","","4","196"},
		{"771","SENTES","SENTES","SEN","","4","196"},
		{"772","SEN","SENTIER","SEN","","4","196"},
		{"773","SENTIER","SENTIER","SEN","","4","196"},
		{"774","SENTIERS","SENTIERS","SEN","","4","196"},
		{"775","CH","CHEMIN","CHE","","4","197"},
		{"776","CHE","CHEMIN","CHE","","4","197"},
		{"777","CHEM","CHEMIN","CHE","","4","197"},
		{"778","CHEMIN","CHEMIN","CHE","","4","197"},
		{"779","CHM ","CHEMIN","CHE","","4","197"},
		{"780","CHEMINS","CHEMINS","CHE","","4","197"},
		/*{"781","CR","CHEMIN#RURAL","","","4","198"},//FEV 010300_05*/
		{"782","CHEMIN#RURAL","CHEMIN#RURAL","","","4","198"},
		{"783","CHEMIN#VICINAL","CHEMIN#VICINAL","CHV","","4","198"},
		{"784","CHV","CHEMIN#VICINAL","CHV","","4","198"},
		{"785","CHEMINS#VICINAUX","CHEMINS#VICINAUX","CHV","","4","198"},
		{"786","ACH","ANCIEN#CHEMIN","ACH","","4","199"},
		{"787","ANCIEN#CHEMIN","ANCIEN#CHEMIN","ACH","","4","199"},
		{"788","ANCIENS#CHEMINS","ANCIENS#CHEMINS","ACH","","4","199"},
		{"789","BAS#CHEMIN","BAS#CHEMIN","BCH","","4","200"},
		{"790","BCH","BAS#CHEMIN","BCH","","4","200"},
		{"791","PCH","PETIT#CHEMIN","PCH","","4","201"},
		{"791","PT#CHEMIN","PETIT#CHEMIN","PCH","","4","201"},
		{"791","PT#CHE","PETIT#CHEMIN","PCH","","4","201"},
		{"791","PT#CH","PETIT#CHEMIN","PCH","","4","201"},
		{"792","PETIT#CHEMIN","PETIT#CHEMIN","PCH","","4","201"},
		{"793","HAUT#CHEMIN","HAUT#CHEMIN","HCH","","4","202"},
		{"794","HCH","HAUT#CHEMIN","HCH","","4","202"},
		{"795","HAUTS#CHEMINS","HAUTS#CHEMINS","HCH","","4","202"},
		{"796","VCHE","VIEUX#CHEMIN","VCHE","","4","203"},
		{"797","VIEUX#CHEMIN","VIEUX#CHEMIN","VCHE","","4","203"},
		{"797","VX#CH","VIEUX#CHEMIN","VCHE","","4","203"},
		{"797","VX#CHE","VIEUX#CHEMIN","VCHE","","4","203"},
		{"797","V#CH","VIEUX#CHEMIN","VCHE","","4","203"},
		{"797","V#CHE","VIEUX#CHEMIN","VCHE","","4","203"},
		{"798","CAV","CAVEE","CAV","","0","204"},//fev 1.3.2 2
		{"799","CAVE","CAVEE","CAV","","0","204"},//fev 1.3.2 2
		{"800","CAVEE","CAVEE","CAV","","0","205"},//fev 1.3.2 2
		{"801","COUR","COUR","COUR","","4","205"},
		{"802","COURS","COURS","CRS","","4","205"},
		/*{"803","CR","COURS","CRS","","4","206"},*/
		{"804","CRS","COURS","CRS","","4","206"},
		/*{"805","CS","COURS","CRS","","4","206"},*/
		{"806","R","RUE","R","RUE","4","206"},
		{"807","RUE","RUE","R","RUE","4","206"},
		{"808","GR","GRANDE#RUE","GR","","4","207"},
		{"808","GR#RUE","GRANDE#RUE","GR","","4","207"},
		{"808","GRAN#RUE","GRANDE#RUE","GR","","4","207"},
		{"808","GRDE#RUE","GRANDE#RUE","GR","","4","207"},
		{"809","GRAND#RUE","GRANDE#RUE","GR","","4","207"},
		{"810","RUES","RUES","R","","4","207"},
		{"811","GRANDE#RUE","GRANDE#RUE","GR","","4","208"},
		{"812","GRANDES#RUES","GRANDES#RUES","GR","","4","208"},
		{"813","PETITE#RUE","PETITE#RUE","PTR","","4","209"},
		{"813","PTE#RUE","PETITE#RUE","PTR","","4","209"},
		{"813","PT#RUE","PETITE#RUE","PTR","","4","209"},
		{"814","PTR","PETITE#RUE","PTR","","4","209"},
		{"815","RLE","RUELLE","RLE","","4","210"},
		{"816","RUEL","RUELLE","RLE","","4","210"},
		{"817","RUELE","RUELLE","RLE","","4","210"},
		{"818","RUELL","RUELLE","RLE","","4","210"},
		{"819","RUELLE","RUELLE","RLE","","4","210"},
		{"820","RUELLES","RUELLES","RLE","","4","210"},
		{"821","QU","QUAI","QU","","4","211"},
		{"822","QUAI","QUAI","QU","","4","211"},
		{"823","QUAIS","QUAIS","QU","","4","211"},
		{"824","QUA","QUARTIER","QUA","","4","212"},
		{"825","QUARTIER","QUARTIER","QUA","","4","212"},
		{"826","TRA","TRAVERSE","TRA","","4","213"},
		{"827","TRAV","TRAVERSE","TRA","","4","213"},
		{"828","TRAVERSE","TRAVERSE","TRA","","4","213"},
		{"829","TRAVERSES","TRAVERSE","TRA","","4","213"},
		{"830","TRS","TRAVERSE","TRA","","4","213"},
		{"831","TRSE","TRAVERSE","TRA","","4","213"},
		{"832","VEN","VENELLE","VEN","","4","214"},
		{"833","VENEL","VENELLE","VEN","","4","214"},
		{"834","VENELL","VENELLE","VEN","","4","214"},
		{"835","VENELLE","VENELLE","VEN","","4","214"},
		{"836","VENELLES","VENELLES","VEN","","4","214"},
		{"837","VO","VOIE","VOI","","4","215"},
		{"838","VOI","VOIE","VOI","","4","215"},
		{"839","VOIE","VOIE","VOI","","4","215"},
		{"840","VOIES","VOIES","VOI","","4","215"},
		{"1","B#P","BP","BP","BP","5","216"},//modification du no d'ordre pour toujours avoir bp en premier
		{"1","BOIT#POSTAL","BP","BP","BP","5","216"},//modification du no d'ordre pour toujours avoir bp en premier
		{"1","BOITE#POSTAL","BP","BP","BP","5","216"},//modification du no d'ordre pour toujours avoir bp en premier
		{"1","BOIT#POSTALE","BP","BP","BP","5","216"},//modification du no d'ordre pour toujours avoir bp en premier
		{"1","BOITE#POSTALE","BP","BP","BP","5","216"},//modification du no d'ordre pour toujours avoir bp en premier
		{"1","BP","BP","BP","BP","5","216"},//modification du no d'ordre pour toujours avoir bp en premier
		{"846","TRI#SERVICE#ARRIVEE ","TSA","TSA","TSA","5","217"},
		{"847","TSA","TSA","TSA","TSA","5","217"},
		{"848","PR","POSTE#RESTANTE","PR","PR","5","218"},//FAE 1.5.0 22
		{"849","POSTE#RESTANTE","POSTE#RESTANTE","PR","PR","5","218"},//FAE 1.5.0 22
		{"850","GARDE#COURRIER","GARDE#COURRIER","GARDE#COURRIER","GARDE#COURRIER","5","219"},
		{"851","GC","GARDE#COURRIER","GARDE#COURRIER","GARDE#COURRIER","5","219"},
		{"852","COURSE#SPECIALE","CS","","CS","5","220"},
		{"853","CS","CS","","CS","5","220"},
		//{"854","ST BOIS","ST BOIS","ST BOIS","ST BOIS","5","220"}
     };	
}
