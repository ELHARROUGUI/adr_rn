package fr.laposte.serca.u15.restructuration.mascadia;

/**
 * Classe statique traitant les lignes d'adresses et plus generalement les chaines de caractere.
 */
public class TraitementAdresse {

    // Caractères autorisés
    private static String[] authChar =
            {"", "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789", // ligne 1
                    "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789", // ligne 2
                    "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789", // ligne 3
                    "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789/", // ligne 4
                    "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789", // ligne 5
                    "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789", // ligne 6
                    "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ",// ligne 7
            }; // pour plus de lisibilité, on utilise des indices de 1 à 7 (l'indice 0 n'est donc
               // pas utilisé ...)

    /**
     * détermine si la chaine 'ligne' contient le mot 'mot' au sens SNA : mot est un mot du type
     * _LOT_. Le problème est que ce mot ne peut être trouvé s'il est le premier de la ligne
     *
     * si index = -1 : le mot n'est pas trouvé. si index = 0 : le mot est trouvé au début. si index
     * > 0 : le mot est trouvé et commence à la position index.
     */
    public static int strAppartient(String ligne, String mot) {
        int index = -1;
        if ((index = ligne.indexOf(mot)) != -1) {
            return index;
        } else if (mot.charAt(0) == ' ') {
            // il faut enlever les espaces après (si le mot est à la fin)
            // c'est un mot du type "_N1" ou "_N1_"
            if (mot.charAt(mot.length() - 1) == ' ') {
                // type "_N1_"
                // le mot est au début ?
                if (ligne.startsWith(mot.trim() + " ") == true) {
                    return 0;
                } else if (ligne.endsWith(" " + mot.trim()) == true) {// le mot est à la fin ?
                    return ligne.indexOf(" " + mot.trim());
                }
            } else {
                // type "_N1"
                if (ligne.startsWith(mot.trim()) == true) {
                    return 0;
                }
                if (ligne.endsWith(mot) == true) { // le mot est à la fin
                    return ligne.indexOf(mot);
                }
                // le mot peut représenter l'ensemble de la ligne
                if (ligne.compareTo(mot.trim()) == 0) {
                    return 0;
                }
            }
        } else {
            // il s'agit d'un mot du type "7EME_" ou "CITE HLM"
            if (mot.charAt(mot.length() - 1) == ' ') {
                // type "7EME_"
                index = ligne.indexOf(mot);
                if (index != -1) {
                    // le mot est au debut ou au milieu
                    return index;
                } else {
                    // le mot est peut etre à la fin
                    index = ligne.indexOf(mot.trim());
                    if (index == (ligne.length() - mot.trim().length())) {
                        return index;
                    } else {
                        return -1;
                    }
                }
            } else {
                // mot du type "CITE HLM"
                return ligne.indexOf(mot.trim());
            }
        }
        return -1;
    }

    /**
     * supprime les caracteres de ponctuation. si type = 1 alors seuls les caracteres accentués sont
     * pris en compte
     */
    public static String enlevePonctuation(String chaine, int type) {
        int tab_conv[] = new int[256];
        int i;

        // fev ai3_010000_02_FEV
        chaine = chaine.replaceAll("Œ", "OE");
        chaine = chaine.replaceAll("œ", "oe");

        char maChaine[] = chaine.toCharArray();
        /* on initialise le tableau des codes ASCII */

        for (i = 0; i < 256; i++) {
            tab_conv[i] = i;
        }

        /* on précise toutes les conversion à mettre en place */
        tab_conv['â'] = 'A';
        tab_conv['à'] = 'A';
        tab_conv['ä'] = 'A';

        tab_conv['ç'] = 'C';

        tab_conv['é'] = 'E';
        tab_conv['è'] = 'E';
        tab_conv['ë'] = 'E';
        tab_conv['ê'] = 'E';

        tab_conv['î'] = 'I';
        tab_conv['ï'] = 'I';

        tab_conv['ô'] = 'O';
        tab_conv['ö'] = 'O';

        tab_conv['û'] = 'U';
        tab_conv['ü'] = 'U';
        tab_conv['ù'] = 'U';

        tab_conv['ÿ'] = 'Y';

        tab_conv['Ä'] = 'A';
        tab_conv['À'] = 'A';
        tab_conv['Â'] = 'A';

        tab_conv['Ç'] = 'C';

        tab_conv['É'] = 'E';
        tab_conv['È'] = 'E';
        tab_conv['Ë'] = 'E';
        tab_conv['Ê'] = 'E';

        tab_conv['Î'] = 'I';
        tab_conv['Ï'] = 'I';

        tab_conv['Ô'] = 'O';
        tab_conv['Ö'] = 'O';

        tab_conv['Û'] = 'U';
        tab_conv['Ü'] = 'U';
        tab_conv['Ù'] = 'U';

        if (type == 0) {
            tab_conv['²'] = '2';
            tab_conv['°'] = '0';
            tab_conv['\''] = ' ';
            tab_conv['\"'] = ' ';
            tab_conv['&'] = ' ';
            tab_conv['£'] = ' ';
            tab_conv['$'] = ' ';
            tab_conv['_'] = ' ';
            tab_conv['-'] = ' ';
            tab_conv['('] = ' ';
            tab_conv[')'] = ' ';
            tab_conv['+'] = ' ';
            tab_conv['='] = ' ';
            tab_conv['*'] = ' ';
            tab_conv['%'] = ' ';
            tab_conv['<'] = ' ';
            tab_conv['>'] = ' ';
            tab_conv['!'] = ' ';
            tab_conv['?'] = ' ';
            tab_conv[','] = ' ';
            tab_conv['.'] = ' ';
            tab_conv[';'] = ' ';
            tab_conv[':'] = ' ';
            tab_conv['/'] = ' ';
            tab_conv['{'] = ' ';
            tab_conv['}'] = ' ';
            tab_conv['['] = ' ';
            tab_conv[']'] = ' ';
            tab_conv['@'] = ' ';
            tab_conv['^'] = ' ';
            tab_conv['#'] = ' ';
            tab_conv['|'] = ' ';
            tab_conv['`'] = ' ';
            tab_conv['\\'] = ' ';
            tab_conv['µ'] = ' ';
            tab_conv['§'] = ' ';
        }
        /*
         * on remplace toutes les ponctuations par des espaces ou par l'équivalent sans accent
         */

        for (i = 0; i < maChaine.length; i++) {
            try {
                maChaine[i] = (char) tab_conv[maChaine[i]];
            } catch (java.lang.ArrayIndexOutOfBoundsException auiobe) {
                maChaine[i] = ' ';
            }
        }

        chaine = String.valueOf(maChaine);
        return chaine;
    }

    /**
     * Tous les espaces contigus de la chaine passée en paramètre sont supprimés de manière qu'un
     * seul espace ne sépare 2 mots.
     */
    public static String supprimerEspaces(String chaine) {
        int flag = 0;
        StringBuffer newChaine = new StringBuffer(0);
        // Rajout fredo
        chaine = chaine.trim();

        for (int i = 0; i < chaine.length(); i++) {
            if (chaine.charAt(i) == ' ') {
                if (flag == 0) {
                    newChaine.append(chaine.charAt(i));
                    flag = 1;
                }
            } else {
                flag = 0;
                newChaine.append(chaine.charAt(i));
            }
        }
        return newChaine.toString();
    }



    /**
     * Détermine si le "mot2" suit le "mot1" dans la chaine "chaine". "Mot2" et "mot1" sont
     * débarassés de leurs espaces
     */
    public static boolean SUIT(String chaine, String mot1, String mot2) {
        StringBuffer buf1 = new StringBuffer(0);

        // on enleve les espaces avant et après sur mot 1 et mot 2.
        int i = 0;
        for (i = 0; i < mot1.length(); i++) {
            if (mot1.charAt(i) != ' ') {
                buf1.append(mot1.charAt(i));
            }
        }

        mot1 = buf1.toString();

        buf1 = new StringBuffer(0);
        for (i = 0; i < mot2.length(); i++) {
            if (mot2.charAt(i) != ' ') {
                buf1.append(mot2.charAt(i));
            }
        }
        mot2 = buf1.toString();

        int index1 = chaine.indexOf(mot1);
        int index2 = chaine.indexOf(mot2);

        if (index1 == -1 || index2 == -1) {
            return false;
        }

        if (index2 > index1) {
            if (index2 == (index1 + mot1.length() + 1)) {
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    /**
     * Détermine si le "mot2" précède le "mot1" dans la chaine "chaine". "Mot2" et "mot1" sont
     * débarassés de leurs espaces
     */
    public static boolean PRECEDE(String chaine, String mot1, String mot2) {
        StringBuffer buf1 = new StringBuffer(0);

        // on enleve les espaces de mot 1 et mot 2.
        int i = 0;
        for (i = 0; i < mot1.length(); i++) {
            if (mot1.charAt(i) != ' ') {
                buf1.append(mot1.charAt(i));
            }
        }
        mot1 = buf1.toString();

        buf1 = new StringBuffer(0);
        for (i = 0; i < mot2.length(); i++) {
            if (mot2.charAt(i) != ' ') {
                buf1.append(mot2.charAt(i));
            }
        }
        mot2 = buf1.toString();

        int index1 = chaine.indexOf(mot1);
        int index2 = chaine.indexOf(mot2);

        if (index1 == -1 || index2 == -1) {
            return false;
        }

        if (index2 < index1) {
            if (index1 == (index2 + mot2.length() + 1)) {
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    /**
     * Niveau 1 : Cette méthode remplace tous les caractères non autorisés par un espace
     */
    public static String trCharAllow(int numeroLigne, String ch) {
        String resultat = "";

        if (numeroLigne >= 1 && numeroLigne <= 7) {
            resultat = ch.replaceAll("[^" + authChar[numeroLigne] + "]", " ");
        } else {
            System.err.println("numeroLigne " + numeroLigne + " invalide");
        }

        // ligne de test
        // System.out.println("test
        // TraitementAdresse.trCharAllow="+numeroLigne+","+ch+","+resultat);

        return resultat;
    }

    /**
     * Supprime les doubles espaces et les remplace par la chaine 'remplace'
     * 
     * @param destinataire
     */
    public static String pretraitementDestinataire(String destinataire) {
        destinataire = destinataire.trim();
        String strNewStr = "";
        String strChar = "";
        String strCharOld = "";
        String remplace = " ";
        for (int i = 0; i <= destinataire.length() - 1; i++) {
            strCharOld = strChar;
            strChar = destinataire.substring(i, i + 1);
            if (!strChar.equalsIgnoreCase(remplace) || !strCharOld.equalsIgnoreCase(remplace)) {
                strNewStr = strNewStr + strChar;
            }
        }
        return strNewStr;
    }
}

