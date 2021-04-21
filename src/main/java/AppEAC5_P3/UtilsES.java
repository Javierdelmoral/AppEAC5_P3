/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AppEAC5_P3;

import java.util.Scanner;

/**
 *
 * @author Javier
 */
public class UtilsES {

    static final String MISSATGE_LINIA_SEPARACIO = "------------------------------------------";
    static final String MISSATGE_ERROR_LECTURA = "Error de lectura";
    static final short NOM_CIUTAT = 0;
    static final short CODI_CIUTAT = 1;

    static public void mostrarMenu(String[] menu) {
        System.out.println();
        System.out.println(MISSATGE_LINIA_SEPARACIO);
        System.out.println(menu[0]);
        System.out.println(MISSATGE_LINIA_SEPARACIO);
        for (int pos = 1; pos < menu.length; pos++) {
            System.out.println(menu[pos]);
        }
    }

    //Mètode per demanar a user el codi de ciutat
    public static String demanaCodiOpcions(String missatge, String[][] arrayCodis) {
        int resp;

        for (int pos = 0; pos < arrayCodis.length; pos++) {
            System.out.println(pos + 1 + "." + arrayCodis[pos][NOM_CIUTAT]);
        }

        do {
            resp = demanarEnter(missatge, MISSATGE_ERROR_LECTURA);
        } while (resp < 0 || resp > arrayCodis.length);

        return arrayCodis[resp - 1][CODI_CIUTAT];
    }

    //Mètode per demanar a user un any
    public static String demanaAnyRang(String missatge, int anyMin, int anyMax) {
        int resp;

        do {
            resp = demanarEnter(missatge, MISSATGE_ERROR_LECTURA);
        } while (resp < anyMin || resp > anyMax);

        return Integer.toString(resp);
    }

    //Mètode per demanar a user un enter
    public static int demanarEnter(String missatge, String mError) {
        Scanner scanner = new Scanner(System.in);
        int ret;
        boolean correcte = false;

        do {
            System.out.print(missatge + "\n");
            correcte = scanner.hasNextInt();
            if (!correcte) {
                scanner.next();
                System.out.println(mError + "\nCal que entris un enter si us plau.");
            }
        } while (!correcte);

        ret = scanner.nextInt();
        scanner.nextLine();

        return ret;
    }

    //Mètode per demanar a user un float
    public static float demanarFloat(String missatge, String mError) {
        Scanner scanner = new Scanner(System.in);
        float ret;
        boolean correcte = false;

        do {
            System.out.print(missatge + "\n");
            correcte = scanner.hasNextFloat();
            if (!correcte) {
                scanner.next();
                System.out.println("\nCal que entris un float si us plau.");
            }
        } while (!correcte);

        ret = scanner.nextFloat();
        scanner.nextLine();

        return ret;
    }

    public static void mostrarMissatge(String missatge) {
        System.out.println(missatge);
    }

    //Mètode per demanar a user una ciutat
    public static String demanaCiutat(String[][] ciutats, String missatge) {
        int resp;

        for (int pos = 0; pos < ciutats.length; pos++) {
            System.out.println(pos + 1 + "." + ciutats[pos][NOM_CIUTAT]);
        }

        do {
            resp = demanarEnter(missatge, MISSATGE_ERROR_LECTURA);
        } while (resp < 1 || resp > ciutats.length);

        return ciutats[resp - 1][CODI_CIUTAT];
    }
}
