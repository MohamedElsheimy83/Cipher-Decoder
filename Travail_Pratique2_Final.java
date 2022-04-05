package travail_pratique2_final;

import java.util.ArrayList;

import java.util.Scanner;

public class Travail_Pratique2_Final {

    /**
     * Procedure principale qui demarre le logiciel.
     * @param args Options passees au logiciel.
     */

    public static void main(String[] args) {

        Scanner scan = new Scanner(System.in);

        String texte = ("ATITBEHSTHRTGXHTHSTYTPCQPEIXHITRATBTCIFJPCSCDJHRWPCITGDCHATITBEHSTHRTGXHTHTIVPXGDHHXVCDATIBTGATBDFJTJGHTGDCIIDJHTCUTITATHQTAATHPJGDCIAPUDAXTTCITITTIATHPBDJGTJMSJHDATXAPJRDTJGFJPCSCDJHRWPCITGDCHATITBEHSTHRTGXHTHHXUUATGPQXTCBXTJMATBTGATBDFJTJGBPXHXATHIQXTCRDJGIATITBEHSTHRTGXHTHDJADCHTCKPSTJMRJTXAAXGTCGTKPCISTHETCSPCIHSDGTXAATRTGXHTHSPBDJGPJMGDQTHKTGBTXAATHIDBQPCIHDJHAPUTJXAATTCVDJIITHSTHPCVBPXHXATHIQXTCRDJGIATITBEHSTHRTGXHTHETCSPCIHSTRDGPXAFJDCRJTXAATTCGTKPCIFJPCSKDJHHTGTOPJITBEHSTHRTGXHTHHXKDJHPKTOETJGSTHRWPVGXCHSPBDJGTKXITOATHQTAATHBDXFJXCTRGPXCHEPHATHETXCTHRGJTAATHYTCTKXKGPXEDXCIHPCHHDJUUGXGJCYDJGFJPCSKDJHTCHTGTOPJITBEHSTHRTGXHTHKDJHPJGTOPJHHXSTHRWPVGXCHSPBDJGYPXBTGPXIDJYDJGHATITBEHSTHRTGXHTHRTHISTRTITBEHAPFJTYTVPGSTPJRDTJGJCTEAPXTDJKTGITTISPBTUDGIJCTTCBTIPCIDUUTGITCTEDJGGPYPBPXHRPABTGBPSDJATJGYPXBTGPXIDJYDJGHATITBEHSTHRTGXHTHTIATHDJKTCXGFJTYTVPGSTPJRDTJG");
        int texte_taille = texte.length();
        int menu_choix = 0;
        int valeur_decalage = 0;

        do {

            // Afficher le menu principal

            System.out.println("********   Le chiffre de Cesar   ********");
            System.out.println("1- Faire l'analyse frequentielle du texte");
            System.out.println("2- Decrypter le texte selon une valeur de decalage specifique");
            System.out.println("3- Faire l'analyse frequentielle et automatiquement Decrypter le texte");
            System.out.println("4- Quitter le logiciel");
            System.out.print("Votre choix:  ");
            if (scan.hasNextInt())
                menu_choix = scan.nextInt();

            // On vide le tampon
            scan.nextLine();

            // Si le choix est 1, on appelle la fonction qui analyse le texte

            if (menu_choix == 1) {
                valeur_decalage = analyse_frequentielle(texte, texte_taille);
            }

            // Si le choix est 2, on appelle la procedure qui decrypte le texte

            else if (menu_choix == 2) {
                System.out.print("Entrez la valeur de decalage: ");
                if (scan.hasNextInt())
                    valeur_decalage = scan.nextInt();
                texte_decrypteur(texte, texte_taille, valeur_decalage);
                System.out.print('\n');
            }

            // Si le choix est 3, on appelle la fonction qui analyse le texte et a partir
            // de valeur du decalage obtenue, on appelle la procedure qui decrypte le texte

            else if (menu_choix == 3) {
                valeur_decalage = analyse_frequentielle(texte, texte_taille);
                texte_decrypteur(texte, texte_taille, valeur_decalage);
                System.out.print('\n');
            }

            else if (menu_choix != 4)
                System.out.print("Votre choix n'est pas valide");
            System.out.print('\n');

        } while (menu_choix != 4);

        // Si le choix est 4, on quitte le logiciel

        System.out.print("----------Fin de program----------");

    }

    /**
     * Fonction qui faire l'analyse frequentielle du texte encrypte.
     * @param texte_analyse les chaines de caracteres du texte originale.
     * @param lettres_nombre le nombre de lettres dans le texte originale.
     * @return la valeur de decalage calculee.
     */

    public static int analyse_frequentielle(String texte_analyse, int lettres_nombre) {

        // Obtenir le texte et sa taille
        // creation d'une tableaux dynamique qui va contenir les lettres d'alphabet

        ArrayList<Character> tableau_lettres = new ArrayList<Character>();

        // creation d'une tableaux dynamique qui va contenir le nombre de fois que l'on
        // peut retrouver chaque lettre dans le texte

        ArrayList<Integer> lettres_frequence = new ArrayList<Integer>();

        int compteur = 0;
        char character_plus_frequent;
        int decalage_calcul = 0;
        int max = 0;
        char character_plus_frequent_pos = ' ';

        // Remplier les deux tableaux avec ses valeurs en utilisant les lettres
        // d'alphabet et un compteur

        for (char i = 'A'; i <= 'Z'; i++) {
            compteur = 0;
            for (int j = 0; j < lettres_nombre; j++) {
                character_plus_frequent = texte_analyse.charAt(j);
                if (character_plus_frequent == i)
                    compteur++;
                if (compteur > max) {
                    max = compteur;

                  // Obtenir la position de la lettre plus frequent

                    character_plus_frequent_pos = texte_analyse.charAt(j);
                }
            }
            
            tableau_lettres.add(i);

            // Calculer la valeur de decalage par rapport a la lettre plus freuente dans un
            // texte francais(E)

            decalage_calcul = 4 - (tableau_lettres.indexOf(character_plus_frequent_pos));

            lettres_frequence.add(compteur);

        }

        // Afficher le contenu des tableaux

        for (int i = 0; i < tableau_lettres.size(); i++)
            System.out.println(tableau_lettres.get(i) + " : " + lettres_frequence.get(i));

        return decalage_calcul;
    }

    /**
     * Procedure qui decrypte le texte encrypte selon une valeur de decalage fournie.
     * @param texte_analyse les chaines de caracteres du texte originale.
     * @param lettres_nombre le nombre de lettres dans le texte originale.
     * @param decalage la valeur de decalage fournie.
     */

    public static void texte_decrypteur(String texte_analyse, int lettres_nombre, int decalage) {

        // Obtenir le texte et sa taille et la valeur de decalage

        char lettre_originale;
        int valeur_lettre;
        char lettre_decalee;

        for (int i = 0; i < lettres_nombre; i++) {

            // Transformer les lettres en valeur numerique pour etre capable de faire le
            // decalage des lettres

            lettre_originale = texte_analyse.charAt(i);
            valeur_lettre = (int) lettre_originale;

            // Calculer la valeur de la lettre apres le decalage pour determiner la lettre originale

            valeur_lettre = valeur_lettre + decalage;
            lettre_decalee = (char) valeur_lettre;
            if (lettre_decalee > 90)
                lettre_decalee -= 26;
            else if (lettre_decalee < 65)
                lettre_decalee += 26;

            // Afficher le text decryptee

            System.out.print(lettre_decalee);
        }

    }
}
